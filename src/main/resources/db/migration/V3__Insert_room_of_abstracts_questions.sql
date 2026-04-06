-- V3__Insert_room_of_abstracts_questions.sql

-- Room of Abstracts questions for all missions

-- Mission 1: Wundversorgung bei Dekubitus
INSERT INTO question (id, title, location_id)
VALUES (21, 'Analysieren diese Abstracts und identifiziert den höchsten Level of Evidence (LoE) nach der Evidenzpyramide wie auch der AHCPR Klassifikation und befühlt die Tabelle mit den richtigen Antworten',
        (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 21);
INSERT INTO document (id, path) VALUES (1, 'abstracts/mission1/1_Abstract_Expertenkommentar.PNG');
INSERT INTO document (id, path) VALUES (2, 'abstracts/mission1/2_Abstract_Santamarie_et_al_RCT.PNG');
INSERT INTO document (id, path) VALUES (3, 'abstracts/mission1/3_Abstract_Zhang_et_al.PNG');
INSERT INTO document_question (document_id, question_id) VALUES (1, 21);
INSERT INTO document_question (document_id, question_id) VALUES (2, 21);
INSERT INTO document_question (document_id, question_id) VALUES (3, 21);

-- Room of Abstracts questions for mission 1: first column
INSERT INTO question (id, title, location_id) VALUES (22, '1_1_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 22);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (81, 'J BLACK', true, 22);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (82, 'Joyce BLACK', true, 22);

INSERT INTO question (id, title, location_id) VALUES (23, '1_2_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 23);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (83, 'Santamaria', true, 23);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (84, 'Nick Santamaria', true, 23);

INSERT INTO question (id, title, location_id) VALUES (24, '1_3_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 24);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (85, 'Zhang', true, 24);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (86, 'Chunjin Zhang', true, 24);

-- Room of Abstracts questions for mission 1: second column
INSERT INTO question (id, title, location_id) VALUES (25, '2_1_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 25);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (87, 'Level I — Systematic Reviews / Meta-analyses', true, 25);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (157, 'Level II — Randomized Controlled Trials', false, 25);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (158, 'Level III — Controlled Trials (no randomization)', false, 25);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (159, 'Level IV — Case-Control / Cohort Studies', false, 25);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (160, 'Level V — Systematic Reviews of Descriptive Studies', false, 25);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (161, 'Level VI — Single Descriptive / Qualitative Study', false, 25);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (162, 'Level VII — Expert Opinion', false, 25);

INSERT INTO question (id, title, location_id) VALUES (26, '2_2_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 26);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (88, 'Level II — Randomized Controlled Trials', true, 26);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (163, 'Level I — Systematic Reviews / Meta-analyses', false, 26);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (164, 'Level III — Controlled Trials (no randomization)', false, 26);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (165, 'Level IV — Case-Control / Cohort Studies', false, 26);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (166, 'Level V — Systematic Reviews of Descriptive Studies', false, 26);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (167, 'Level VI — Single Descriptive / Qualitative Study', false, 26);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (168, 'Level VII — Expert Opinion', false, 26);

INSERT INTO question (id, title, location_id) VALUES (27, '2_3_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 27);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (89, 'Level VII — Expert Opinion', true, 27);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (169, 'Level I — Systematic Reviews / Meta-analyses', false, 27);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (170, 'Level II — Randomized Controlled Trials', false, 27);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (171, 'Level III — Controlled Trials (no randomization)', false, 27);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (172, 'Level IV — Case-Control / Cohort Studies', false, 27);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (173, 'Level V — Systematic Reviews of Descriptive Studies', false, 27);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (174, 'Level VI — Single Descriptive / Qualitative Study', false, 27);

-- Room of Abstracts questions for mission 1: third column
INSERT INTO question (id, title, location_id) VALUES (28, '3_1_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 28);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (90, 'Ia — Meta-analysis of RCTs', true, 28);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (300, 'Ib — At least one RCT', false, 28);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (301, 'IIa — Controlled study (no randomization)', false, 28);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (302, 'IIb — Quasi-experimental study', false, 28);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (303, 'III — Non-experimental descriptive studies', false, 28);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (304, 'IV — Expert committee reports / opinions', false, 28);

INSERT INTO question (id, title, location_id) VALUES (29, '3_2_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 29);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (91, 'Ib — At least one RCT', true, 29);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (305, 'Ia — Meta-analysis of RCTs', false, 29);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (306, 'IIa — Controlled study (no randomization)', false, 29);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (307, 'IIb — Quasi-experimental study', false, 29);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (308, 'III — Non-experimental descriptive studies', false, 29);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (309, 'IV — Expert committee reports / opinions', false, 29);

INSERT INTO question (id, title, location_id) VALUES (30, '3_3_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 30);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (92, 'IV — Expert committee reports / opinions', true, 30);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (310, 'Ia — Meta-analysis of RCTs', false, 30);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (311, 'Ib — At least one RCT', false, 30);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (312, 'IIa — Controlled study (no randomization)', false, 30);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (313, 'IIb — Quasi-experimental study', false, 30);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (314, 'III — Non-experimental descriptive studies', false, 30);

-- Room of Abstracts questions for mission 1: fourth column
INSERT INTO question (id, title, location_id) VALUES (31, '4_1_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 31);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (93, 'Systematic Review', true, 31);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (375, 'Meta-Analysis', false, 31);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (376, 'Randomized Controlled Trial (RCT)', false, 31);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (377, 'Retrospective cohort study', false, 31);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (378, 'Cross-Sectional Study', false, 31);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (379, 'Expert Opinion / Editorial', false, 31);

INSERT INTO question (id, title, location_id) VALUES (32, '4_2_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 32);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (94, 'Randomized Controlled Trial (RCT)', true, 32);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (380, 'Systematic Review', false, 32);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (381, 'Meta-Analysis', false, 32);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (382, 'Retrospective cohort study', false, 32);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (383, 'Cross-Sectional Study', false, 32);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (384, 'Expert Opinion / Editorial', false, 32);

INSERT INTO question (id, title, location_id) VALUES (33, '4_3_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 33);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (95, 'Expert Opinion / Editorial', true, 33);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (385, 'Systematic Review', false, 33);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (386, 'Meta-Analysis', false, 33);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (387, 'Randomized Controlled Trial (RCT)', false, 33);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (388, 'Retrospective cohort study', false, 33);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (389, 'Cross-Sectional Study', false, 33);


-- Mission 2: Sturzprävention in der Geriatrie
INSERT INTO question (id, title, location_id)
VALUES (34, 'Analysieren diese Abstracts und identifiziert den höchsten Level of Evidence (LoE) nach der Evidenzpyramide wie auch der AHCPR Klassifikation und befühlt die Tabelle mit den richtigen Antworten',
        (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 34);
INSERT INTO document (id, path) VALUES (4, 'abstracts/mission2/1_Abstract_Effects_of_VR_Games.PNG');
INSERT INTO document (id, path) VALUES (5, 'abstracts/mission2/2_Abstract_Bedrails_and_Falls_in_Nursing_Homes.PNG');
INSERT INTO document (id, path) VALUES (6, 'abstracts/mission2/3_Abstract_Expertenkommentar.PNG');
INSERT INTO document_question (document_id, question_id) VALUES (4, 34);
INSERT INTO document_question (document_id, question_id) VALUES (5, 34);
INSERT INTO document_question (document_id, question_id) VALUES (6, 34);

-- Room of Abstracts questions for mission 2: first column
INSERT INTO question (id, title, location_id) VALUES (35, '1_1_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 35);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (96, 'Yuanyuan', true, 35);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (97, 'Ren Yuanyuan', true, 35);

INSERT INTO question (id, title, location_id) VALUES (36, '1_2_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 36);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (98, 'Huynh', true, 36);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (99, 'David Huynh', true, 36);

INSERT INTO question (id, title, location_id) VALUES (37, '1_3_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 37);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (100, 'Heschl', true, 37);

-- TODO: INSERT INTO answer (id, text, is_correct, question_id) VALUES (101, 'David Huynh', true, 37);

-- Room of Abstracts questions for mission 2: second column
INSERT INTO question (id, title, location_id) VALUES (38, '2_1_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 38);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (102, 'Level I — Systematic Reviews / Meta-analyses', true, 38);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (175, 'Level II — Randomized Controlled Trials', false, 38);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (176, 'Level III — Controlled Trials (no randomization)', false, 38);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (177, 'Level IV — Case-Control / Cohort Studies', false, 38);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (178, 'Level V — Systematic Reviews of Descriptive Studies', false, 38);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (179, 'Level VI — Single Descriptive / Qualitative Study', false, 38);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (180, 'Level VII — Expert Opinion', false, 38);

INSERT INTO question (id, title, location_id) VALUES (39, '2_2_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 39);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (103, 'Level I — Systematic Reviews / Meta-analyses', true, 39);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (181, 'Level II — Randomized Controlled Trials', false, 39);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (182, 'Level III — Controlled Trials (no randomization)', false, 39);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (183, 'Level IV — Case-Control / Cohort Studies', false, 39);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (184, 'Level V — Systematic Reviews of Descriptive Studies', false, 39);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (185, 'Level VI — Single Descriptive / Qualitative Study', false, 39);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (186, 'Level VII — Expert Opinion', false, 39);

INSERT INTO question (id, title, location_id) VALUES (40, '2_3_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 40);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (104, 'Level VII — Expert Opinion', true, 40);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (187, 'Level I — Systematic Reviews / Meta-analyses', false, 40);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (188, 'Level II — Randomized Controlled Trials', false, 40);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (189, 'Level III — Controlled Trials (no randomization)', false, 40);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (190, 'Level IV — Case-Control / Cohort Studies', false, 40);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (191, 'Level V — Systematic Reviews of Descriptive Studies', false, 40);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (192, 'Level VI — Single Descriptive / Qualitative Study', false, 40);

-- Room of Abstracts questions for mission 2: third column
INSERT INTO question (id, title, location_id) VALUES (41, '3_1_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 41);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (105, 'Ia — Meta-analysis of RCTs', true, 41);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (315, 'Ib — At least one RCT', false, 41);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (316, 'IIa — Controlled study (no randomization)', false, 41);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (317, 'IIb — Quasi-experimental study', false, 41);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (318, 'III — Non-experimental descriptive studies', false, 41);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (319, 'IV — Expert committee reports / opinions', false, 41);

INSERT INTO question (id, title, location_id) VALUES (42, '3_2_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 42);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (106, 'Ia — Meta-analysis of RCTs', true, 42);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (320, 'Ib — At least one RCT', false, 42);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (321, 'IIa — Controlled study (no randomization)', false, 42);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (322, 'IIb — Quasi-experimental study', false, 42);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (323, 'III — Non-experimental descriptive studies', false, 42);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (324, 'IV — Expert committee reports / opinions', false, 42);

INSERT INTO question (id, title, location_id) VALUES (43, '3_3_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 43);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (107, 'IV — Expert committee reports / opinions', true, 43);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (325, 'Ia — Meta-analysis of RCTs', false, 43);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (326, 'Ib — At least one RCT', false, 43);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (327, 'IIa — Controlled study (no randomization)', false, 43);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (328, 'IIb — Quasi-experimental study', false, 43);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (329, 'III — Non-experimental descriptive studies', false, 43);

-- Room of Abstracts questions for mission 2: fourth column
INSERT INTO question (id, title, location_id) VALUES (44, '4_1_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 44);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (108, 'Systematic Review', true, 44);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (390, 'Meta-Analysis', false, 44);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (391, 'Randomized Controlled Trial (RCT)', false, 44);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (392, 'Retrospective cohort study', false, 44);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (393, 'Cross-Sectional Study', false, 44);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (394, 'Expert Opinion / Editorial', false, 44);

INSERT INTO question (id, title, location_id) VALUES (45, '4_2_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 45);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (109, 'Systematic Review', true, 45);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (395, 'Meta-Analysis', false, 45);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (396, 'Randomized Controlled Trial (RCT)', false, 45);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (397, 'Retrospective cohort study', false, 45);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (398, 'Cross-Sectional Study', false, 45);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (399, 'Expert Opinion / Editorial', false, 45);

INSERT INTO question (id, title, location_id) VALUES (46, '4_3_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 46);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (110, 'Expert Opinion / Editorial', true, 46);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (400, 'Systematic Review', false, 46);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (401, 'Meta-Analysis', false, 46);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (402, 'Randomized Controlled Trial (RCT)', false, 46);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (403, 'Retrospective cohort study', false, 46);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (404, 'Cross-Sectional Study', false, 46);

-- Mission 3: Schmerzmanagement in der postoperativen Pflege
INSERT INTO question (id, title, location_id)
VALUES (47, 'Analysieren diese Abstracts und identifiziert den höchsten Level of Evidence (LoE) nach der Evidenzpyramide wie auch der AHCPR Klassifikation und befühlt die Tabelle mit den richtigen Antworten',
        (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 47);
INSERT INTO document (id, path) VALUES (7, 'abstracts/mission3/2_Abstract_Documentaton_for_Assessing_Pain_inPostoperative.PNG');
INSERT INTO document (id, path) VALUES (8, 'abstracts/mission3/3_Abstract_Nursing_Music_Protocol_and_Postoperative_Pain.PNG');
INSERT INTO document (id, path) VALUES (9, 'abstracts/mission3/1_Abstract_PostoperstivePaintreatmentwithDementia.PNG');
INSERT INTO document_question (document_id, question_id) VALUES (7, 47);
INSERT INTO document_question (document_id, question_id) VALUES (8, 47);
INSERT INTO document_question (document_id, question_id) VALUES (9, 47);

-- Room of Abstracts questions for mission 3: first column
INSERT INTO question (id, title, location_id) VALUES (48, '1_1_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 48);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (111, 'Nobuo Sakata, Yasuyuki Okumura, Asao Ogawa', true, 48);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (112, 'Sakata, Okumura, Ogawa', true, 48);

INSERT INTO question (id, title, location_id) VALUES (49, '1_2_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 49);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (113, 'Huong Dang, Siv K. Stafseth', true, 49);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (114, 'Huong, Stafseth', true, 49);

INSERT INTO question (id, title, location_id) VALUES (50, '1_3_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 50);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (115, 'Michael J. Poulsen, Jeffrey Coto', true, 50);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (116, 'Poulsen, Coto', true, 50);

-- Room of Abstracts questions for mission 3: second column
INSERT INTO question (id, title, location_id) VALUES (51, '2_1_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 51);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (117, 'Level III — Controlled Trials (no randomization)', true, 51);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (193, 'Level I — Systematic Reviews / Meta-analyses', false, 51);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (194, 'Level II — Randomized Controlled Trials', false, 51);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (195, 'Level IV — Case-Control / Cohort Studies', false, 51);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (196, 'Level V — Systematic Reviews of Descriptive Studies', false, 51);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (197, 'Level VI — Single Descriptive / Qualitative Study', false, 51);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (198, 'Level VII — Expert Opinion', false, 51);

INSERT INTO question (id, title, location_id) VALUES (52, '2_2_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 52);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (118, 'Level IV — Case-Control / Cohort Studies', true, 52);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (199, 'Level I — Systematic Reviews / Meta-analyses', false, 52);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (200, 'Level II — Randomized Controlled Trials', false, 52);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (201, 'Level III — Controlled Trials (no randomization)', false, 52);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (202, 'Level V — Systematic Reviews of Descriptive Studies', false, 52);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (203, 'Level VI — Single Descriptive / Qualitative Study', false, 52);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (204, 'Level VII — Expert Opinion', false, 52);

INSERT INTO question (id, title, location_id) VALUES (53, '2_3_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 53);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (119, 'Level VII — Expert Opinion', true, 53);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (205, 'Level I — Systematic Reviews / Meta-analyses', false, 53);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (206, 'Level II — Randomized Controlled Trials', false, 53);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (207, 'Level III — Controlled Trials (no randomization)', false, 53);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (208, 'Level IV — Case-Control / Cohort Studies', false, 53);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (209, 'Level V — Systematic Reviews of Descriptive Studies', false, 53);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (210, 'Level VI — Single Descriptive / Qualitative Study', false, 53);

-- Room of Abstracts questions for mission 3: third column
INSERT INTO question (id, title, location_id) VALUES (54, '3_1_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 54);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (120, 'IIb — Quasi-experimental study', true, 54);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (330, 'Ia — Meta-analysis of RCTs', false, 54);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (331, 'Ib — At least one RCT', false, 54);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (332, 'IIa — Controlled study (no randomization)', false, 54);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (333, 'III — Non-experimental descriptive studies', false, 54);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (334, 'IV — Expert committee reports / opinions', false, 54);

INSERT INTO question (id, title, location_id) VALUES (55, '3_2_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 55);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (121, 'IIb — Quasi-experimental study', true, 55);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (335, 'Ia — Meta-analysis of RCTs', false, 55);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (336, 'Ib — At least one RCT', false, 55);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (337, 'IIa — Controlled study (no randomization)', false, 55);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (338, 'III — Non-experimental descriptive studies', false, 55);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (339, 'IV — Expert committee reports / opinions', false, 55);

INSERT INTO question (id, title, location_id) VALUES (56, '3_3_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 56);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (122, 'IV — Expert committee reports / opinions', true, 56);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (340, 'Ia — Meta-analysis of RCTs', false, 56);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (341, 'Ib — At least one RCT', false, 56);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (342, 'IIa — Controlled study (no randomization)', false, 56);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (343, 'IIb — Quasi-experimental study', false, 56);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (344, 'III — Non-experimental descriptive studies', false, 56);

-- Room of Abstracts questions for mission 3: fourth column
INSERT INTO question (id, title, location_id) VALUES (57, '4_1_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 57);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (123, 'Retrospective cohort study', true, 57);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (405, 'Systematic Review', false, 57);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (406, 'Meta-Analysis', false, 57);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (407, 'Randomized Controlled Trial (RCT)', false, 57);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (408, 'Cross-Sectional Study', false, 57);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (409, 'Expert Opinion / Editorial', false, 57);

INSERT INTO question (id, title, location_id) VALUES (58, '4_2_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 58);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (124, 'Pre-post intervention study', true, 58);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (410, 'Systematic Review', false, 58);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (411, 'Meta-Analysis', false, 58);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (412, 'Randomized Controlled Trial (RCT)', false, 58);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (413, 'Retrospective cohort study', false, 58);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (414, 'Cross-Sectional Study', false, 58);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (415, 'Expert Opinion / Editorial', false, 58);

INSERT INTO question (id, title, location_id) VALUES (59, '4_3_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 59);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (125, 'Expert Opinion / Editorial', true, 59);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (416, 'Systematic Review', false, 59);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (417, 'Meta-Analysis', false, 59);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (418, 'Randomized Controlled Trial (RCT)', false, 59);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (419, 'Retrospective cohort study', false, 59);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (420, 'Cross-Sectional Study', false, 59);


-- Mission 4: Ernährungsinterventionen bei Mangelernährung
INSERT INTO question (id, title, location_id)
VALUES (60, 'Analysieren diese Abstracts und identifiziert den höchsten Level of Evidence (LoE) nach der Evidenzpyramide wie auch der AHCPR Klassifikation und befühlt die Tabelle mit den richtigen Antworten',
        (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 60);
INSERT INTO document (id, path) VALUES (10, 'abstracts/mission4/3_Abstract_Gefahr_einer_Mangelernährung_Querschnittstudie.PNG');
INSERT INTO document (id, path) VALUES (11, 'abstracts/mission4/1_Abstract_Ten_Cate_et_al_Systematic_Review.PNG');
INSERT INTO document (id, path) VALUES (12, 'abstracts/mission4/2_Abstract_weerasag_et_al_RCT.PNG');
INSERT INTO document_question (document_id, question_id) VALUES (10, 60);
INSERT INTO document_question (document_id, question_id) VALUES (11, 60);
INSERT INTO document_question (document_id, question_id) VALUES (12, 60);


-- Room of Abstracts questions for mission 4: first column
INSERT INTO question (id, title, location_id) VALUES (61, '1_1_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 61);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (126, 'Debbie ten Cate', true, 61);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (127, 'ten Cate', true, 61);

INSERT INTO question (id, title, location_id) VALUES (62, '1_2_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 62);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (128, 'Weerasak Muangpaisan', true, 62);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (129, 'Muangpaisan', true, 62);

INSERT INTO question (id, title, location_id) VALUES (63, '1_3_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 63);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (130, 'Silvia Brunner', true, 63);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (131, 'Brunner', true, 63);

-- Room of Abstracts questions for mission 4: second column
INSERT INTO question (id, title, location_id) VALUES (64, '2_1_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 64);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (132, 'Level I — Systematic Reviews / Meta-analyses', true, 64);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (211, 'Level II — Randomized Controlled Trials', false, 64);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (212, 'Level III — Controlled Trials (no randomization)', false, 64);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (213, 'Level IV — Case-Control / Cohort Studies', false, 64);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (214, 'Level V — Systematic Reviews of Descriptive Studies', false, 64);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (215, 'Level VI — Single Descriptive / Qualitative Study', false, 64);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (216, 'Level VII — Expert Opinion', false, 64);

INSERT INTO question (id, title, location_id) VALUES (65, '2_2_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 65);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (133, 'Level II — Randomized Controlled Trials', true, 65);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (217, 'Level I — Systematic Reviews / Meta-analyses', false, 65);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (218, 'Level III — Controlled Trials (no randomization)', false, 65);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (219, 'Level IV — Case-Control / Cohort Studies', false, 65);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (220, 'Level V — Systematic Reviews of Descriptive Studies', false, 65);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (221, 'Level VI — Single Descriptive / Qualitative Study', false, 65);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (222, 'Level VII — Expert Opinion', false, 65);

INSERT INTO question (id, title, location_id) VALUES (66, '2_3_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 66);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (134, 'Level IV — Case-Control / Cohort Studies', true, 66);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (223, 'Level I — Systematic Reviews / Meta-analyses', false, 66);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (224, 'Level II — Randomized Controlled Trials', false, 66);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (225, 'Level III — Controlled Trials (no randomization)', false, 66);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (226, 'Level V — Systematic Reviews of Descriptive Studies', false, 66);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (227, 'Level VI — Single Descriptive / Qualitative Study', false, 66);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (228, 'Level VII — Expert Opinion', false, 66);

-- Room of Abstracts questions for mission 4: third column
INSERT INTO question (id, title, location_id) VALUES (67, '3_1_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 67);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (135, 'Ia — Meta-analysis of RCTs', true, 67);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (345, 'Ib — At least one RCT', false, 67);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (346, 'IIa — Controlled study (no randomization)', false, 67);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (347, 'IIb — Quasi-experimental study', false, 67);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (348, 'III — Non-experimental descriptive studies', false, 67);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (349, 'IV — Expert committee reports / opinions', false, 67);

INSERT INTO question (id, title, location_id) VALUES (68, '3_2_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 68);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (136, 'Ib — At least one RCT', true, 68);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (350, 'Ia — Meta-analysis of RCTs', false, 68);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (351, 'IIa — Controlled study (no randomization)', false, 68);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (352, 'IIb — Quasi-experimental study', false, 68);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (353, 'III — Non-experimental descriptive studies', false, 68);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (354, 'IV — Expert committee reports / opinions', false, 68);

INSERT INTO question (id, title, location_id) VALUES (69, '3_3_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 69);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (137, 'III — Non-experimental descriptive studies', true, 69);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (355, 'Ia — Meta-analysis of RCTs', false, 69);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (356, 'Ib — At least one RCT', false, 69);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (357, 'IIa — Controlled study (no randomization)', false, 69);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (358, 'IIb — Quasi-experimental study', false, 69);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (359, 'IV — Expert committee reports / opinions', false, 69);

-- Room of Abstracts questions for mission 4: fourth column
INSERT INTO question (id, title, location_id) VALUES (70, '4_1_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 70);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (138, 'Systematic Review', true, 70);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (421, 'Meta-Analysis', false, 70);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (422, 'Randomized Controlled Trial (RCT)', false, 70);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (423, 'Retrospective cohort study', false, 70);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (424, 'Cross-Sectional Study', false, 70);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (425, 'Expert Opinion / Editorial', false, 70);

INSERT INTO question (id, title, location_id) VALUES (71, '4_2_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 71);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (139, 'Randomized Controlled Trial (RCT)', true, 71);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (426, 'Systematic Review', false, 71);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (427, 'Meta-Analysis', false, 71);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (428, 'Retrospective cohort study', false, 71);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (429, 'Cross-Sectional Study', false, 71);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (430, 'Expert Opinion / Editorial', false, 71);

INSERT INTO question (id, title, location_id) VALUES (72, '4_3_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 72);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (141, 'Cross-Sectional Study', true, 72);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (431, 'Systematic Review', false, 72);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (432, 'Meta-Analysis', false, 72);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (433, 'Randomized Controlled Trial (RCT)', false, 72);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (434, 'Retrospective cohort study', false, 72);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (435, 'Expert Opinion / Editorial', false, 72);


-- Mission 5: Vermeidung von Katheter-assoziierten Harnwegsinfektionen
INSERT INTO question (id, title, location_id)
VALUES (73, 'Analysieren diese Abstracts und identifiziert den höchsten Level of Evidence (LoE) nach der Evidenzpyramide wie auch der AHCPR Klassifikation und befühlt die Tabelle mit den richtigen Antworten',
        (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 73);
INSERT INTO document (id, path) VALUES (13, 'abstracts/mission5/2_Abstract_Implementation_of_a_multi_modal_inervention_CAUTI2024.PNG');
INSERT INTO document (id, path) VALUES (14, 'abstracts/mission5/1_Abstract_Urin_Sampling_is_associated_with_reduced_CAUTI_2021.PNG');
INSERT INTO document (id, path) VALUES (15, 'abstracts/mission5/3_Abstracxt_PRactice_REcommendation_CAUTI2023.PNG');
INSERT INTO document_question (document_id, question_id) VALUES (13, 73);
INSERT INTO document_question (document_id, question_id) VALUES (14, 73);
INSERT INTO document_question (document_id, question_id) VALUES (15, 73);


-- Room of Abstracts questions for mission 5: first column
INSERT INTO question (id, title, location_id) VALUES (74, '1_1_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 74);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (142, 'Jennifer A. Frontera', true, 74);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (143, 'Frontera', true, 74);

INSERT INTO question (id, title, location_id) VALUES (75, '1_2_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 75);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (144, 'Lauren Fish', true, 75);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (145, 'Fish', true, 75);

INSERT INTO question (id, title, location_id) VALUES (76, '1_3_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 76);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (146, 'Payal K. Patel', true, 76);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (147, 'Patel', true, 76);

-- Room of Abstracts questions for mission 5: second column
INSERT INTO question (id, title, location_id) VALUES (77, '2_1_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 77);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (148, 'Level III — Controlled Trials (no randomization)', true, 77);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (229, 'Level I — Systematic Reviews / Meta-analyses', false, 77);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (230, 'Level II — Randomized Controlled Trials', false, 77);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (231, 'Level IV — Case-Control / Cohort Studies', false, 77);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (232, 'Level V — Systematic Reviews of Descriptive Studies', false, 77);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (233, 'Level VI — Single Descriptive / Qualitative Study', false, 77);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (234, 'Level VII — Expert Opinion', false, 77);

INSERT INTO question (id, title, location_id) VALUES (78, '2_2_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 78);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (149, 'Level III — Controlled Trials (no randomization)', true, 78);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (235, 'Level I — Systematic Reviews / Meta-analyses', false, 78);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (236, 'Level II — Randomized Controlled Trials', false, 78);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (237, 'Level IV — Case-Control / Cohort Studies', false, 78);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (238, 'Level V — Systematic Reviews of Descriptive Studies', false, 78);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (239, 'Level VI — Single Descriptive / Qualitative Study', false, 78);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (240, 'Level VII — Expert Opinion', false, 78);

INSERT INTO question (id, title, location_id) VALUES (79, '2_3_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 79);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (150, 'Level VII — Expert Opinion', true, 79);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (241, 'Level I — Systematic Reviews / Meta-analyses', false, 79);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (242, 'Level II — Randomized Controlled Trials', false, 79);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (243, 'Level III — Controlled Trials (no randomization)', false, 79);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (244, 'Level IV — Case-Control / Cohort Studies', false, 79);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (245, 'Level V — Systematic Reviews of Descriptive Studies', false, 79);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (246, 'Level VI — Single Descriptive / Qualitative Study', false, 79);

-- Room of Abstracts questions for mission 5: third column
INSERT INTO question (id, title, location_id) VALUES (80, '3_1_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 80);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (151, 'IIb — Quasi-experimental study', true, 80);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (360, 'Ia — Meta-analysis of RCTs', false, 80);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (361, 'Ib — At least one RCT', false, 80);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (362, 'IIa — Controlled study (no randomization)', false, 80);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (363, 'III — Non-experimental descriptive studies', false, 80);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (364, 'IV — Expert committee reports / opinions', false, 80);

INSERT INTO question (id, title, location_id) VALUES (81, '3_2_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 81);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (152, 'IIb — Quasi-experimental study', true, 81);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (365, 'Ia — Meta-analysis of RCTs', false, 81);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (366, 'Ib — At least one RCT', false, 81);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (367, 'IIa — Controlled study (no randomization)', false, 81);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (368, 'III — Non-experimental descriptive studies', false, 81);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (369, 'IV — Expert committee reports / opinions', false, 81);

INSERT INTO question (id, title, location_id) VALUES (82, '3_3_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 82);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (153, 'IV — Expert committee reports / opinions', true, 82);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (370, 'Ia — Meta-analysis of RCTs', false, 82);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (371, 'Ib — At least one RCT', false, 82);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (372, 'IIa — Controlled study (no randomization)', false, 82);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (373, 'IIb — Quasi-experimental study', false, 82);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (374, 'III — Non-experimental descriptive studies', false, 82);

-- Room of Abstracts questions for mission 5: fourth column
INSERT INTO question (id, title, location_id) VALUES (83, '4_1_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 83);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (154, 'Pre-post intervention study', true, 83);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (436, 'Systematic Review', false, 83);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (437, 'Meta-Analysis', false, 83);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (438, 'Randomized Controlled Trial (RCT)', false, 83);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (439, 'Retrospective cohort study', false, 83);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (440, 'Cross-Sectional Study', false, 83);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (441, 'Expert Opinion / Editorial', false, 83);

INSERT INTO question (id, title, location_id) VALUES (84, '4_2_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 84);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (155, 'Retrospective cohort study', true, 84);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (442, 'Systematic Review', false, 84);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (443, 'Meta-Analysis', false, 84);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (444, 'Randomized Controlled Trial (RCT)', false, 84);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (445, 'Cross-Sectional Study', false, 84);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (446, 'Expert Opinion / Editorial', false, 84);

INSERT INTO question (id, title, location_id) VALUES (85, '4_3_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 85);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (156, 'Expert Opinion / Editorial', true, 85);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (447, 'Systematic Review', false, 85);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (448, 'Meta-Analysis', false, 85);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (449, 'Randomized Controlled Trial (RCT)', false, 85);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (450, 'Retrospective cohort study', false, 85);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (451, 'Cross-Sectional Study', false, 85);
