-- V1__Insert_room_of_knowledge_questions.sql

-- Frage 1: Welche Evidenzstufe hat die höchste Beweiskraft?
INSERT INTO question (id, title) VALUES (1, 'Welche Evidenzstufe hat die höchste Beweiskraft?');
INSERT INTO answer (id, text, is_correct, question_id) VALUES (1, 'Systematische Reviews und Meta-Analysen von randomisierten kontrollierten Studien (RCTs)', true, 1);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (2, 'Einzelne randomisierte kontrollierte Studien', false, 1);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (3, 'Kohortenstudien', false, 1);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (4, 'Expertenmeinungen', false, 1);

-- Frage 2: Was charakterisiert eine Level II-Evidenz?
INSERT INTO question (id, title) VALUES (2, 'Was charakterisiert eine Level II-Evidenz?');
INSERT INTO answer (id, text, is_correct, question_id) VALUES (5, 'Evidenz aus mindestens einer gut konzipierten RCT', true, 2);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (6, 'Evidenz aus systematischen Reviews', false, 2);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (7, 'Evidenz aus Fallstudien', false, 2);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (8, 'Evidenz aus Expertenmeinungen', false, 2);

-- Frage 3: Welche Art von Studie gehört zur Level IV-Evidenz?
INSERT INTO question (id, title) VALUES (3, 'Welche Art von Studie gehört zur Level IV-Evidenz?');
INSERT INTO answer (id, text, is_correct, question_id) VALUES (9, 'Fall-Kontroll-Studien oder Kohortenstudien', true, 3);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (10, 'Randomisierte kontrollierte Studien', false, 3);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (11, 'Systematische Reviews', false, 3);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (12, 'Qualitative Studien', false, 3);

-- Frage 4: Was ist das Hauptmerkmal von Level I-Evidenz?
INSERT INTO question (id, title) VALUES (4, 'Was ist das Hauptmerkmal von Level I-Evidenz?');
INSERT INTO answer (id, text, is_correct, question_id) VALUES (13, 'Geringeres Risiko von Bias', true, 4);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (14, 'Größere Stichprobengröße', false, 4);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (15, 'Längere Studiendauer', false, 4);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (16, 'Höhere Kosten', false, 4);

-- Frage 5: Welche Evidenzstufe haben Expertenmeinungen typischerweise?
INSERT INTO question (id, title) VALUES (5, 'Welche Evidenzstufe haben Expertenmeinungen typischerweise?');
INSERT INTO answer (id, text, is_correct, question_id) VALUES (17, 'Level VII', true, 5);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (18, 'Level I', false, 5);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (19, 'Level III', false, 5);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (20, 'Level V', false, 5);

-- Frage 6: Was ist ein Vorteil von höheren Evidenzleveln?
INSERT INTO question (id, title) VALUES (6, 'Was ist ein Vorteil von höheren Evidenzleveln?');
INSERT INTO answer (id, text, is_correct, question_id) VALUES (21, 'Weniger Risiko für Verzerrungen (Bias)', true, 6);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (22, 'Schnellere Durchführung der Studien', false, 6);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (23, 'Geringere Kosten', false, 6);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (24, 'Einfachere Rekrutierung von Teilnehmern', false, 6);

-- Frage 7: Welche Art von Studie liefert Level III-Evidenz?
INSERT INTO question (id, title) VALUES (7, 'Welche Art von Studie liefert Level III-Evidenz?');
INSERT INTO answer (id, text, is_correct, question_id) VALUES (25, 'Nicht-randomisierte kontrollierte Studien', true, 7);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (26, 'Randomisierte kontrollierte Studien', false, 7);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (27, 'Systematische Reviews', false, 7);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (28, 'Fallberichte', false, 7);

-- Frage 8: Was charakterisiert Level V-Evidenz?
INSERT INTO question (id, title) VALUES (8, 'Was charakterisiert Level V-Evidenz?');
INSERT INTO answer (id, text, is_correct, question_id) VALUES (29, 'Systematische Reviews von deskriptiven und qualitativen Studien', true, 8);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (30, 'Einzelne randomisierte kontrollierte Studien', false, 8);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (31, 'Kohortenstudien', false, 8);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (32, 'Expertenmeinungen', false, 8);

-- Frage 9: Welche Studie hat die niedrigste Evidenzstufe?
INSERT INTO question (id, title) VALUES (9, 'Welche Studie hat die niedrigste Evidenzstufe?');
INSERT INTO answer (id, text, is_correct, question_id) VALUES (33, 'Fallberichte', true, 9);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (34, 'Kohortenstudien', false, 9);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (35, 'Quasi-experimentelle Studien', false, 9);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (36, 'Systematische Reviews', false, 9);

-- Frage 10: Was ist ein Merkmal von Level VI-Evidenz?
INSERT INTO question (id, title) VALUES (10, 'Was ist ein Merkmal von Level VI-Evidenz?');
INSERT INTO answer (id, text, is_correct, question_id) VALUES (37, 'Einzelne deskriptive oder qualitative Studien', true, 10);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (38, 'Mehrere randomisierte kontrollierte Studien', false, 10);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (39, 'Systematische Reviews', false, 10);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (40, 'Fall-Kontroll-Studien', false, 10);

-- Frage 11: Welche Art von Studie bietet die beste Evidenz für Kausalität?
INSERT INTO question (id, title) VALUES (11, 'Welche Art von Studie bietet die beste Evidenz für Kausalität?');
INSERT INTO answer (id, text, is_correct, question_id) VALUES (41, 'Randomisierte kontrollierte Studien', true, 11);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (42, 'Kohortenstudien', false, 11);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (43, 'Fall-Kontroll-Studien', false, 11);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (44, 'Querschnittsstudien', false, 11);

-- Frage 12: Was ist ein Nachteil von Studien mit niedrigem Evidenzlevel?
INSERT INTO question (id, title) VALUES (12, 'Was ist ein Nachteil von Studien mit niedrigem Evidenzlevel?');
INSERT INTO answer (id, text, is_correct, question_id) VALUES (45, 'Höheres Risiko für Bias', true, 12);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (46, 'Zu große Stichproben', false, 12);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (47, 'Zu lange Studiendauer', false, 12);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (48, 'Zu hohe Kosten', false, 12);

-- Frage 13: Welche Evidenzstufe haben gut konzipierte Kohortenstudien?
INSERT INTO question (id, title) VALUES (13, 'Welche Evidenzstufe haben gut konzipierte Kohortenstudien?');
INSERT INTO answer (id, text, is_correct, question_id) VALUES (49, 'Level IV', true, 13);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (50, 'Level I', false, 13);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (51, 'Level II', false, 13);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (52, 'Level VII', false, 13);

-- Frage 14: Was ist ein Merkmal von Level III-Evidenz?
INSERT INTO question (id, title) VALUES (14, 'Was ist ein Merkmal von Level III-Evidenz?');
INSERT INTO answer (id, text, is_correct, question_id) VALUES (53, 'Kontrollierte Studien ohne Randomisierung', true, 14);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (54, 'Unkontrollierte Studien', false, 14);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (55, 'Einzelne RCTs', false, 14);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (56, 'Expertenmeinungen', false, 14);

-- Frage 15: Welche Art von Studie gehört zur Level V-Evidenz?
INSERT INTO question (id, title) VALUES (15, 'Welche Art von Studie gehört zur Level V-Evidenz?');
INSERT INTO answer (id, text, is_correct, question_id) VALUES (57, 'Systematische Reviews von qualitativen Studien', true, 15);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (58, 'Einzelne RCTs', false, 15);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (59, 'Kohortenstudien', false, 15);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (60, 'Fallberichte', false, 15);

-- Frage 16: Was ist der Hauptunterschied zwischen Level I und Level II Evidenz?
INSERT INTO question (id, title) VALUES (16, 'Was ist der Hauptunterschied zwischen Level I und Level II Evidenz?');
INSERT INTO answer (id, text, is_correct, question_id) VALUES (61, 'Level I beinhaltet systematische Reviews, Level II einzelne RCTs', true, 16);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (62, 'Level I hat größere Stichproben als Level II', false, 16);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (63, 'Level II-Studien dauern länger als Level I-Studien', false, 16);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (64, 'Es gibt keinen Unterschied', false, 16);

-- Frage 17: Welche Evidenzstufe haben gut konzipierte Fall-Kontroll-Studien?
INSERT INTO question (id, title) VALUES (17, 'Welche Evidenzstufe haben gut konzipierte Fall-Kontroll-Studien?');
INSERT INTO answer (id, text, is_correct, question_id) VALUES (65, 'Level IV', true, 17);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (66, 'Level II', false, 17);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (67, 'Level III', false, 17);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (68, 'Level VI', false, 17);

-- Frage 18: Was charakterisiert Level VII-Evidenz?
INSERT INTO question (id, title) VALUES (18, 'Was charakterisiert Level VII-Evidenz?');
INSERT INTO answer (id, text, is_correct, question_id) VALUES (69, 'Meinungen von Autoritäten und/oder Expertenkomitees', true, 18);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (70, 'Einzelne RCTs', false, 18);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (71, 'Systematische Reviews', false, 18);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (72, 'Kohortenstudien', false, 18);

-- Frage 19: Welche Art von Studie bietet die beste Evidenz für Prognosen?
INSERT INTO question (id, title) VALUES (19, 'Welche Art von Studie bietet die beste Evidenz für Prognosen?');
INSERT INTO answer (id, text, is_correct, question_id) VALUES (73, 'Kohortenstudien', true, 19);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (74, 'Fall-Kontroll-Studien', false, 19);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (75, 'Querschnittsstudien', false, 19);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (76, 'Fallberichte', false, 19);

-- Frage 20: Was ist ein Vorteil von Studien mit niedrigerem Evidenzlevel?
INSERT INTO question (id, title) VALUES (20, 'Was ist ein Vorteil von Studien mit niedrigerem Evidenzlevel?');
INSERT INTO answer (id, text, is_correct, question_id) VALUES (77, 'Können Hypothesen für weitere Forschung generieren', true, 20);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (78, 'Haben immer größere Stichproben', false, 20);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (79, 'Sind immer kostengünstiger', false, 20);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (80, 'Haben ein geringeres Risiko für Bias', false, 20);