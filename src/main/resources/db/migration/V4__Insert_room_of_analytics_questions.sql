-- V4__Insert_room_of_analytics_questions.sql

-- Room of Analytics questions for all 5 missions

-- ============================================================================
-- Mission 1: Wundversorgung bei Dekubitus
-- ============================================================================
INSERT INTO question (id, title, location_id)
VALUES (86, 'Würdigt die zugeteilte Studie kritisch mit einem Tool aus der Kammer des Schreckens.',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 86);
INSERT INTO document (id, path) VALUES (16, 'analytics/mission1/1_Zhang_et_al_SystematicReview.pdf');
INSERT INTO document_question (document_id, question_id) VALUES (16, 86);

INSERT INTO question (id, title, location_id)
VALUES (87, 'Methodology',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 87);


INSERT INTO question (id, title, location_id)
VALUES (88, 'The Results',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 88);


INSERT INTO question (id, title, location_id)
VALUES (89, 'Level of Evidence',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 89);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (443, 'Level III — Controlled Trials (no randomization)', false, 89);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (444, 'Level I — Systematic Reviews / Meta-analyses', true, 89);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (445, 'Level II — Randomized Controlled Trials', false, 89);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (446, 'Level IV — Case-Control / Cohort Studies', false, 89);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (447, 'Level V — Systematic Reviews of Descriptive Studies', false, 89);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (448, 'Level VI — Single Descriptive / Qualitative Study', false, 89);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (449, 'Level VII — Expert Opinion', false, 89);


INSERT INTO question (id, title, location_id)
VALUES (90, 'Strengths',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 90);


INSERT INTO question (id, title, location_id)
VALUES (91, 'Weaknesses',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 91);


-- ============================================================================
-- Mission 2: Sturzprävention in der Geriatrie
-- ============================================================================
INSERT INTO question (id, title, location_id)
VALUES (92, 'Würdigt die zugeteilte Studie kritisch mit einem Tool aus der Kammer des Schreckens.',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 92);
INSERT INTO document (id, path) VALUES (17, 'analytics/mission2/1_Effects_of_VR_Games_in_reducing_fall(2023).pdf');
INSERT INTO document_question (document_id, question_id) VALUES (17, 92);

INSERT INTO question (id, title, location_id)
VALUES (93, 'Methodology',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 93);


INSERT INTO question (id, title, location_id)
VALUES (94, 'The Results',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 94);


INSERT INTO question (id, title, location_id)
VALUES (95, 'Level of Evidence',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 95);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (450, 'Level I — Systematic Reviews / Meta-analyses', true, 95);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (451, 'Level II — Randomized Controlled Trials', false, 95);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (452, 'Level III — Controlled Trials (no randomization)', false, 95);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (453, 'Level IV — Case-Control / Cohort Studies', false, 95);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (454, 'Level V — Systematic Reviews of Descriptive Studies', false, 95);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (455, 'Level VI — Single Descriptive / Qualitative Study', false, 95);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (456, 'Level VII — Expert Opinion', false, 95);


INSERT INTO question (id, title, location_id)
VALUES (96, 'Strengths',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 96);


INSERT INTO question (id, title, location_id)
VALUES (97, 'Weaknesses',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 97);


-- ============================================================================
-- Mission 3: Schmerzmanagement in der postoperativen Pflege
-- ============================================================================
INSERT INTO question (id, title, location_id)
VALUES (98, 'Würdigt die zugeteilte Studie kritisch mit einem Tool aus der Kammer des Schreckens.',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 98);
INSERT INTO document (id, path) VALUES (18, 'analytics/mission3/1_Sakata_et_al_PainManagement_Retrospective.pdf');
INSERT INTO document_question (document_id, question_id) VALUES (18, 98);

INSERT INTO question (id, title, location_id)
VALUES (99, 'Methodology',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 99);


INSERT INTO question (id, title, location_id)
VALUES (100, 'The Results',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 100);


INSERT INTO question (id, title, location_id)
VALUES (101, 'Level of Evidence',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 101);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (457, 'Level IV — Case-Control / Cohort Studies', false, 101);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (458, 'Level I — Systematic Reviews / Meta-analyses', false, 101);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (459, 'Level II — Randomized Controlled Trials', false, 101);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (460, 'Level III — Controlled Trials (no randomization)', true, 101);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (461, 'Level V — Systematic Reviews of Descriptive Studies', false, 101);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (462, 'Level VI — Single Descriptive / Qualitative Study', false, 101);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (463, 'Level VII — Expert Opinion', false, 101);


INSERT INTO question (id, title, location_id)
VALUES (102, 'Strengths',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 102);


INSERT INTO question (id, title, location_id)
VALUES (103, 'Weaknesses',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 103);


-- ============================================================================
-- Mission 4: Ernährungsinterventionen bei Mangelernährung
-- ============================================================================
INSERT INTO question (id, title, location_id)
VALUES (104, 'Würdigt die zugeteilte Studie kritisch mit einem Tool aus der Kammer des Schreckens.',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 104);
INSERT INTO document (id, path) VALUES (19, 'analytics/mission4/1_tenCate_et_al_Malnutrition_SystematicReview.pdf');
INSERT INTO document_question (document_id, question_id) VALUES (19, 104);

INSERT INTO question (id, title, location_id)
VALUES (105, 'Methodology',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 105);


INSERT INTO question (id, title, location_id)
VALUES (106, 'The Results',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 106);


INSERT INTO question (id, title, location_id)
VALUES (107, 'Level of Evidence',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 107);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (464, 'Level I — Systematic Reviews / Meta-analyses', false, 107);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (465, 'Level II — Randomized Controlled Trials', true, 107);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (466, 'Level III — Controlled Trials (no randomization)', false, 107);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (467, 'Level IV — Case-Control / Cohort Studies', false, 107);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (468, 'Level V — Systematic Reviews of Descriptive Studies', false, 107);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (469, 'Level VI — Single Descriptive / Qualitative Study', false, 107);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (470, 'Level VII — Expert Opinion', false, 107);


INSERT INTO question (id, title, location_id)
VALUES (108, 'Strengths',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 108);


INSERT INTO question (id, title, location_id)
VALUES (109, 'Weaknesses',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 109);


-- ============================================================================
-- Mission 5: Vermeidung von Katheter-assoziierten Harnwegsinfektionen
-- ============================================================================
INSERT INTO question (id, title, location_id)
VALUES (110, 'Würdigt die zugeteilte Studie kritisch mit einem Tool aus der Kammer des Schreckens.',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 110);
INSERT INTO document (id, path) VALUES (20, 'analytics/mission5/1_Frontera_et_al_CAUTI_Intervention.pdf');
INSERT INTO document_question (document_id, question_id) VALUES (20, 110);

INSERT INTO question (id, title, location_id)
VALUES (111, 'Methodology',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 111);


INSERT INTO question (id, title, location_id)
VALUES (112, 'The Results',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 112);


INSERT INTO question (id, title, location_id)
VALUES (113, 'Level of Evidence',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 113);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (471, 'Level III — Controlled Trials (no randomization)', false, 113);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (472, 'Level I — Systematic Reviews / Meta-analyses', false, 113);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (473, 'Level II — Randomized Controlled Trials', true, 113);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (474, 'Level IV — Case-Control / Cohort Studies', false, 113);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (475, 'Level V — Systematic Reviews of Descriptive Studies', false, 113);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (476, 'Level VI — Single Descriptive / Qualitative Study', false, 113);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (477, 'Level VII — Expert Opinion', false, 113);


INSERT INTO question (id, title, location_id)
VALUES (114, 'Strengths',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 114);


INSERT INTO question (id, title, location_id)
VALUES (115, 'Weaknesses',
        (SELECT id FROM location WHERE name = 'Room of Analytics'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 115);
