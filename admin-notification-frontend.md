# Admin Notification Frontend Plan

## Context
Backend is Spring Boot with STOMP WebSocket on `/ws`. CORS configured for `http://localhost:5173`. JWT auth via `Authorization: Bearer <token>`. Frontend is separate Vite app.

## Actual Stack
- React 19.2.4
- TypeScript 5.9.3
- Vite 7.1.12 (port 5173)
- `react-router` 7.13.0
- Tailwind CSS 4.1.12 with `@tailwindcss/vite`
- `motion` 12.38.0 (animations)
- `lucide-react` 0.487.0 (icons)
- Vitest 4.1.1 + `@testing-library/react` 16.3.2 + jsdom 29.0.1 (testing)

No global state library installed. Use React Context + `useReducer`.

---

## 1. Install WebSocket Dependencies

```bash
npm install @stomp/stompjs sockjs-client
npm install -D @types/sockjs-client
```

---

## 2. WebSocket Client Service

File: `src/services/websocket.ts`

```typescript
import { Client, IMessage, StompSubscription } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

const WS_URL = import.meta.env.VITE_API_BASE_URL + '/ws';

let stompClient: Client | null = null;
let subscription: StompSubscription | null = null;

export function connectWebSocket(token: string, onMessage: (msg: AdminNotification) => void) {
  if (stompClient?.active) return;

  stompClient = new Client({
    webSocketFactory: () => new SockJS(WS_URL),
    connectHeaders: {
      Authorization: `Bearer ${token}`,
    },
    debug: (str) => {
      if (import.meta.env.DEV) console.log(str);
    },
    reconnectDelay: 5000,
    heartbeatIncoming: 4000,
    heartbeatOutgoing: 4000,
    onConnect: () => {
      subscription = stompClient!.subscribe('/topic/analytics/submissions', (message: IMessage) => {
        const body = JSON.parse(message.body);
        onMessage(body);
      });
    },
    onStompError: (frame) => {
      console.error('Broker error: ' + frame.headers['message']);
    },
  });

  stompClient.activate();
}

export function disconnectWebSocket() {
  subscription?.unsubscribe();
  stompClient?.deactivate();
  subscription = null;
  stompClient = null;
}
```

**Why SockJS:** Spring Boot STOMP endpoint registered with `.withSockJS()`. Frontend must use SockJS to match.

**Reconnect:** `reconnectDelay` handles backend restarts or transient failures.

---

## 3. Type Definitions

File: `src/types/notification.ts`

```typescript
export interface AnswerDetail {
  questionId: number;
  questionText: string;
  answerText: string;
}

export interface AdminNotification {
  submissionId: number;
  missionId: number;
  missionName: string;
  roomId: number;
  roomName: string;
  submittedAt: string; // ISO-8601
  answers: AnswerDetail[];
}
```

---

## 4. State Management (React Context + useReducer)

No Zustand/Redux in `package.json`. Keep it vanilla.

File: `src/context/NotificationContext.tsx`

```typescript
import { createContext, useContext, useReducer, ReactNode } from 'react';
import { AdminNotification } from '../types/notification';

type State = {
  notifications: AdminNotification[];
  unreadCount: number;
};

type Action =
  | { type: 'ADD'; payload: AdminNotification }
  | { type: 'MARK_ALL_READ' }
  | { type: 'REMOVE'; payload: number };

const initialState: State = { notifications: [], unreadCount: 0 };

function reducer(state: State, action: Action): State {
  switch (action.type) {
    case 'ADD': {
      const exists = state.notifications.some((n) => n.submissionId === action.payload.submissionId);
      if (exists) return state;
      return {
        notifications: [action.payload, ...state.notifications],
        unreadCount: state.unreadCount + 1,
      };
    }
    case 'MARK_ALL_READ':
      return { ...state, unreadCount: 0 };
    case 'REMOVE':
      return {
        notifications: state.notifications.filter((n) => n.submissionId !== action.payload),
        unreadCount: Math.max(0, state.unreadCount - 1),
      };
    default:
      return state;
  }
}

const NotificationContext = createContext<{
  state: State;
  dispatch: React.Dispatch<Action>;
} | null>(null);

export function NotificationProvider({ children }: { children: ReactNode }) {
  const [state, dispatch] = useReducer(reducer, initialState);
  return (
    <NotificationContext.Provider value={{ state, dispatch }}>
      {children}
    </NotificationContext.Provider>
  );
}

export function useNotification() {
  const ctx = useContext(NotificationContext);
  if (!ctx) throw new Error('useNotification must be inside NotificationProvider');
  return ctx;
}
```

---

## 5. WebSocket Provider Hook

File: `src/hooks/useAdminWebSocket.ts`

```typescript
import { useEffect } from 'react';
import { connectWebSocket, disconnectWebSocket } from '../services/websocket';
import { useNotification } from '../context/NotificationContext';
import { useAuth } from '../context/AuthContext'; // or however auth is held

export function useAdminWebSocket() {
  const { token, isAdmin } = useAuth();
  const { dispatch } = useNotification();

  useEffect(() => {
    if (!token || !isAdmin) return;

    connectWebSocket(token, (msg) => {
      dispatch({ type: 'ADD', payload: msg });
    });

    return () => {
      disconnectWebSocket();
    };
  }, [token, isAdmin, dispatch]);
}
```

**Mount point:** Call `useAdminWebSocket()` inside admin layout or `App` so it lives for entire admin session.

---

## 6. Admin Dashboard UI

### Notification Bell Component
File: `src/components/NotificationBell.tsx`

```tsx
import { Bell } from 'lucide-react';
import { motion, AnimatePresence } from 'motion';
import { useNotification } from '../context/NotificationContext';

export function NotificationBell() {
  const { state, dispatch } = useNotification();

  return (
    <div className="relative">
      <button
        aria-label="notifications"
        className="relative p-2 rounded-full hover:bg-gray-100 transition-colors"
        onClick={() => dispatch({ type: 'MARK_ALL_READ' })}
      >
        <Bell className="w-5 h-5 text-gray-700" />
        <AnimatePresence>
          {state.unreadCount > 0 && (
            <motion.span
              initial={{ scale: 0 }}
              animate={{ scale: 1 }}
              exit={{ scale: 0 }}
              className="absolute -top-0.5 -right-0.5 bg-red-500 text-white rounded-full min-w-[1.25rem] h-5 flex items-center justify-center text-xs font-medium px-1"
            >
              {state.unreadCount}
            </motion.span>
          )}
        </AnimatePresence>
      </button>
    </div>
  );
}
```

### Submission Detail Panel
File: `src/components/SubmissionPanel.tsx`

```tsx
import { motion } from 'motion';
import { Check, X } from 'lucide-react';
import { AdminNotification } from '../types/notification';

interface Props {
  submission: AdminNotification;
  onValidate: (id: number) => void;
  onReject: (id: number) => void;
}

export function SubmissionPanel({ submission, onValidate, onReject }: Props) {
  return (
    <motion.div
      initial={{ opacity: 0, y: 12 }}
      animate={{ opacity: 1, y: 0 }}
      className="border border-gray-200 rounded-xl p-5 mb-4 bg-white shadow-sm"
    >
      <div className="flex justify-between items-start mb-3">
        <div>
          <h3 className="font-bold text-gray-900">{submission.missionName}</h3>
          <p className="text-sm text-gray-500">Mission ID: {submission.missionId} &middot; Room: {submission.roomName}</p>
        </div>
        <time className="text-xs text-gray-400" title={submission.submittedAt}>
          {new Date(submission.submittedAt).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })}
        </time>
      </div>

      <div className="space-y-3">
        {submission.answers.map((a) => (
          <div key={a.questionId} className="bg-gray-50 rounded-lg p-3">
            <p className="font-semibold text-sm text-gray-800">{a.questionText}</p>
            <p className="text-sm text-gray-600 mt-1 leading-relaxed">{a.answerText}</p>
          </div>
        ))}
      </div>

      <div className="flex gap-2 mt-4">
        <button
          onClick={() => onValidate(submission.submissionId)}
          className="inline-flex items-center gap-1.5 bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded-lg text-sm font-medium transition-colors"
        >
          <Check className="w-4 h-4" /> Validate
        </button>
        <button
          onClick={() => onReject(submission.submissionId)}
          className="inline-flex items-center gap-1.5 bg-red-600 hover:bg-red-700 text-white px-4 py-2 rounded-lg text-sm font-medium transition-colors"
        >
          <X className="w-4 h-4" /> Reject
        </button>
      </div>
    </motion.div>
  );
}
```

---

## 7. Admin Page

File: `src/pages/AdminDashboard.tsx`

```tsx
import { useAdminWebSocket } from '../hooks/useAdminWebSocket';
import { useNotification } from '../context/NotificationContext';
import { SubmissionPanel } from '../components/SubmissionPanel';

export function AdminDashboard() {
  useAdminWebSocket();
  const { state, dispatch } = useNotification();

  const handleValidate = (id: number) => {
    // TODO: Implement backend endpoint: POST /api/admin/submissions/{id}/validate
    // Currently, submissionId is the ID of the first answer in the batch.
    console.log('Validating submission:', id);
    dispatch({ type: 'REMOVE', payload: id });
  };

  const handleReject = (id: number) => {
    // TODO: Implement backend endpoint: POST /api/admin/submissions/{id}/reject
    console.log('Rejecting submission:', id);
    dispatch({ type: 'REMOVE', payload: id });
  };

  return (
    <div className="max-w-3xl mx-auto p-6">
      <h1 className="text-2xl font-bold text-gray-900 mb-6">Analytics Submissions</h1>
      {state.notifications.length === 0 ? (
        <p className="text-gray-500 text-center py-12">No new submissions.</p>
      ) : (
        state.notifications.map((n) => (
          <SubmissionPanel key={n.submissionId} submission={n} onValidate={handleValidate} onReject={handleReject} />
        ))
      )}
    </div>
  );
}
```

---

## 8. Environment Config

File: `.env`

```
VITE_API_BASE_URL=http://localhost:8080
```

File: `.env.production`

```
VITE_API_BASE_URL=https://api.yourdomain.com
```

---

## 9. Tailwind v4 Notes

Tailwind CSS 4.1.12 uses CSS-first configuration. No `tailwind.config.js` needed for basic setup. Import Tailwind in `src/index.css`:

```css
@import "tailwindcss";
```

Theme customization lives in CSS using `@theme`:

```css
@theme {
  --color-primary: #0ea5e9;
}
```

All utility classes in components above use Tailwind v4 syntax.

---

## 10. Error Handling & Edge Cases

| Case | Handling |
|------|----------|
| Token expired during session | Backend closes WS. Frontend detects `onDisconnect`, triggers re-login or token refresh before reconnect. |
| Admin opens multiple tabs | Each tab holds own WS connection and duplicate notifications. Acceptable. If dedup needed, key notifications by `submissionId` in store. |
| Backend down | SockJS auto-reconnects every 5s. Show offline banner if `onWebSocketClose` fires. |
| Non-admin tries to connect | Backend rejects subscription or handshake. Frontend should not attempt connection if `isAdmin === false`. |
| Large submission payloads | STOMP default payload limit is high. If answers contain long text, no issue. If images, use URLs not base64 in WS. |

---

## 11. Testing Strategy

1. **Manual:** Open admin dashboard in browser, submit answers from player client, verify notification appears within 1s.
2. **Component tests (Vitest):** Mock `NotificationContext`, render `SubmissionPanel`, assert validate button calls handler.
3. **Integration tests:** Use `mock-socket` or real Node WS client to simulate STOMP server, assert `connectWebSocket` subscribes and delivers messages to context.

Vitest and testing libraries already installed. Example test:

File: `src/components/SubmissionPanel.test.tsx`

```tsx
import { describe, it, expect, vi } from 'vitest';
import { render, screen, fireEvent } from '@testing-library/react';
import { SubmissionPanel } from './SubmissionPanel';

const mockSubmission = {
  submissionId: 1,
  missionId: 10,
  missionName: 'Mission Alpha',
  roomId: 5,
  roomName: 'Analytics Room',
  submittedAt: '2026-04-30T10:00:00Z',
  answers: [{ questionId: 1, questionText: 'Q1', answerText: 'A1' }],
};

describe('SubmissionPanel', () => {
  it('calls onValidate when validate clicked', () => {
    const onValidate = vi.fn();
    render(<SubmissionPanel submission={mockSubmission} onValidate={onValidate} onReject={vi.fn()} />);
    fireEvent.click(screen.getByText(/validate/i));
    expect(onValidate).toHaveBeenCalledWith(1);
  });
});
```

---

## Summary Checklist

| Step | File |
|------|------|
| Install WS deps | `package.json` |
| WS client service | `src/services/websocket.ts` |
| Type definitions | `src/types/notification.ts` |
| State context | `src/context/NotificationContext.tsx` |
| Hook | `src/hooks/useAdminWebSocket.ts` |
| UI components | `src/components/NotificationBell.tsx`, `SubmissionPanel.tsx` |
| Page | `src/pages/AdminDashboard.tsx` |
| Env config | `.env`, `.env.production` |
| Tests | `src/components/SubmissionPanel.test.tsx` |
