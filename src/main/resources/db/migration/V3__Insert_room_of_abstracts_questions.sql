-- V3__Insert_room_of_abstracts_questions.sql

-- Room of Abstracts questions for all missions

-- ============================================================================
-- Mission 1: Wundversorgung bei Dekubitus
-- ============================================================================
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
INSERT INTO question (id, title, location_id) VALUES (22, '1_1_Study_Title_And_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 22);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (81, 'Dressings as an adjunct to pressure ulver prevention: consensus panel recommendation, Authors: Black et al. (2015)', true, 22);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (82, 'Dressings as an adjunct to pressure ulver prevention: consensus panel recommendation, Authors: Santamaria et al. (2018)', false, 22);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (83, 'Dressings as an adjunct to pressure ulver prevention: consensus panel recommendation, Authors: Zhang et al. (2022)', false, 22);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (84, 'A randomised controlled trial of the clinical effectiveness of mulit-layer silicone foram dressings for the prevention of pressure injuries in high-risk aged care residents: The Border III Trial, Authors: Zhang et al. (2022)', false, 22);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (85, 'Efficacy of different types of dressings on pressure injuries: Systematic Review and network meta-analysis, Authors: Zhang et al. (2022)', false, 22);

INSERT INTO question (id, title, location_id) VALUES (23, '1_2_Study_Title_And_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 23);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (86, 'A randomised controlled trial of the clinical effectiveness of mulit-layer silicone foram dressings for the prevention of pressure injuries in high-risk aged care residents: The Border III Trial, Authors: Santamaria et al. (2018)', true, 23);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (87, 'A randomised controlled trial of the clinical effectiveness of mulit-layer silicone foram dressings for the prevention of pressure injuries in high-risk aged care residents: The Border III Trial, Authors: Black et al. (2015)', false, 23);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (88, 'A randomised controlled trial of the clinical effectiveness of mulit-layer silicone foram dressings for the prevention of pressure injuries in high-risk aged care residents: The Border III Trial, Authors: Zhang et al. (2022)', false, 23);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (89, 'Dressings as an adjunct to pressure ulver prevention: consensus panel recommendation, Authors: Black et al. (2015)', false, 23);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (90, 'Efficacy of different types of dressings on pressure injuries: Systematic Review and network meta-analysis, Authors: Black et al. (2015)', false, 23);

INSERT INTO question (id, title, location_id) VALUES (24, '1_3_Study_Title_And_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 24);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (91, 'Efficacy of different types of dressings on pressure injuries: Systematic Review and network meta-analysis, Authors: Zhang et al. (2022)', true, 24);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (92, 'Efficacy of different types of dressings on pressure injuries: Systematic Review and network meta-analysis, Authors: Black et al. (2015)', false, 24);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (93, 'Efficacy of different types of dressings on pressure injuries: Systematic Review and network meta-analysis, Authors: Santamaria et al. (2018)', false, 24);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (94, 'Dressings as an adjunct to pressure ulver prevention: consensus panel recommendation, Authors: Black et al. (2015)', false, 24);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (95, 'Dressings as an adjunct to pressure ulver prevention: consensus panel recommendation, Authors: Zhang et al. (2022)', false, 24);

-- Room of Abstracts questions for mission 1: second column
INSERT INTO question (id, title, location_id) VALUES (25, '2_1_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 25);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (96, 'Level I — Systematic Reviews / Meta-analyses', true, 25);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (97, 'Level II — Randomized Controlled Trials', false, 25);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (98, 'Level III — Controlled Trials (no randomization)', false, 25);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (99, 'Level IV — Case-Control / Cohort Studies', false, 25);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (100, 'Level V — Systematic Reviews of Descriptive Studies', false, 25);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (101, 'Level VI — Single Descriptive / Qualitative Study', false, 25);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (102, 'Level VII — Expert Opinion', false, 25);

INSERT INTO question (id, title, location_id) VALUES (26, '2_2_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 26);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (103, 'Level II — Randomized Controlled Trials', true, 26);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (104, 'Level I — Systematic Reviews / Meta-analyses', false, 26);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (105, 'Level III — Controlled Trials (no randomization)', false, 26);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (106, 'Level IV — Case-Control / Cohort Studies', false, 26);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (107, 'Level V — Systematic Reviews of Descriptive Studies', false, 26);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (108, 'Level VI — Single Descriptive / Qualitative Study', false, 26);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (109, 'Level VII — Expert Opinion', false, 26);

INSERT INTO question (id, title, location_id) VALUES (27, '2_3_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 27);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (110, 'Level VII — Expert Opinion', true, 27);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (111, 'Level I — Systematic Reviews / Meta-analyses', false, 27);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (112, 'Level II — Randomized Controlled Trials', false, 27);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (113, 'Level III — Controlled Trials (no randomization)', false, 27);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (114, 'Level IV — Case-Control / Cohort Studies', false, 27);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (115, 'Level V — Systematic Reviews of Descriptive Studies', false, 27);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (116, 'Level VI — Single Descriptive / Qualitative Study', false, 27);

-- Room of Abstracts questions for mission 1: third column
INSERT INTO question (id, title, location_id) VALUES (28, '3_1_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 28);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (117, 'Ia — Meta-analysis of RCTs', true, 28);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (118, 'Ib — At least one RCT', false, 28);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (119, 'IIa — Controlled study (no randomization)', false, 28);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (120, 'IIb — Quasi-experimental study', false, 28);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (121, 'III — Non-experimental descriptive studies', false, 28);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (122, 'IV — Expert committee reports / opinions', false, 28);

INSERT INTO question (id, title, location_id) VALUES (29, '3_2_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 29);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (123, 'Ib — At least one RCT', true, 29);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (124, 'Ia — Meta-analysis of RCTs', false, 29);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (125, 'IIa — Controlled study (no randomization)', false, 29);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (126, 'IIb — Quasi-experimental study', false, 29);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (127, 'III — Non-experimental descriptive studies', false, 29);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (128, 'IV — Expert committee reports / opinions', false, 29);

INSERT INTO question (id, title, location_id) VALUES (30, '3_3_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 30);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (129, 'IV — Expert committee reports / opinions', true, 30);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (130, 'Ia — Meta-analysis of RCTs', false, 30);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (131, 'Ib — At least one RCT', false, 30);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (132, 'IIa — Controlled study (no randomization)', false, 30);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (133, 'IIb — Quasi-experimental study', false, 30);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (134, 'III — Non-experimental descriptive studies', false, 30);

-- Room of Abstracts questions for mission 1: fourth column
INSERT INTO question (id, title, location_id) VALUES (31, '4_1_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 31);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (135, 'Systematic Review', true, 31);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (136, 'Meta-Analysis', false, 31);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (137, 'Randomized Controlled Trial (RCT)', false, 31);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (138, 'Retrospective cohort study', false, 31);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (139, 'Cross-Sectional Study', false, 31);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (140, 'Expert Opinion / Editorial', false, 31);

INSERT INTO question (id, title, location_id) VALUES (32, '4_2_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 32);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (141, 'Randomized Controlled Trial (RCT)', true, 32);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (142, 'Systematic Review', false, 32);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (143, 'Meta-Analysis', false, 32);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (144, 'Retrospective cohort study', false, 32);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (145, 'Cross-Sectional Study', false, 32);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (146, 'Expert Opinion / Editorial', false, 32);

INSERT INTO question (id, title, location_id) VALUES (33, '4_3_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 33);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (147, 'Expert Opinion / Editorial', true, 33);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (148, 'Systematic Review', false, 33);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (149, 'Meta-Analysis', false, 33);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (150, 'Randomized Controlled Trial (RCT)', false, 33);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (151, 'Retrospective cohort study', false, 33);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (152, 'Cross-Sectional Study', false, 33);


-- ============================================================================
-- Mission 2: Sturzprävention in der Geriatrie
-- ============================================================================
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
INSERT INTO question (id, title, location_id) VALUES (35, '1_1_Study_Title_And_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 35);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (153, 'Effectiveness of virtual reality games in improving physical function, balance and reducing falls in balance-impaired older adults: A systematic review and meta analyses, Authors: Yuanyuan et al. (2023)', true, 35);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (154, 'Effectiveness of virtual reality games in improving physical function, balance and reducing falls in balance-impaired older adults: A systematic review and meta analyses, Authors: Yuanyuan et al. (2021)', false, 35);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (155, 'Effectiveness of virtual reality games in improving physical function, balance and reducing falls in balance-impaired older adults: A systematic review and meta analyses, Authors: Heschl et al. (2021)', false, 35);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (156, 'Bedrails and Falls in Nursing Homes: A Systematic Review, Authors: Huynh et al. (2021)', false, 35);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (157, 'Sturzprophylaxe bei älteren Menschen, Authors: Heschl (2021)', false, 35);

INSERT INTO question (id, title, location_id) VALUES (36, '1_2_Study_Title_And_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 36);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (158, 'Bedrails and Falls in Nursing Homes: A Systematic Review, Authors: Huynh et al. (2021)', true, 36);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (159, 'Bedrails and Falls in Nursing Homes: A Systematic Review, Authors: Huynh et al. (2023)', false, 36);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (160, 'Bedrails and Falls in Nursing Homes: A Systematic Review, Authors: Yuanyuan et al. (2021)', false, 36);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (161, 'Sturzprophylaxe bei älteren Menschen, Authors: Heschl (2023)', false, 36);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (162, 'Effectiveness of virtual reality games in improving physical function, balance and reducing falls in balance-impaired older adults: A systematic review and meta analyses, Authors: Yuanyuan et al. (2023)', false, 36);

INSERT INTO question (id, title, location_id) VALUES (37, '1_3_Study_Title_And_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 37);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (163, 'Sturzprophylaxe bei älteren Menschen, Authors: Heschl (2023)', true, 37);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (164, 'Sturzprophylaxe bei älteren Menschen, Authors: Heschl (2021)', false, 37);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (165, 'Sturzprophylaxe bei älteren Menschen, Authors: Yuanyuan (2021)', false, 37);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (166, 'Bedrails and Falls in Nursing Homes: A Systematic Review, Authors: Yuanyuan et al. (2023)', false, 37);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (167, 'Effectiveness of virtual reality games in improving physical function, balance and reducing falls in balance-impaired older adults: A systematic review and meta analyses, Authors: Yuanyuan et al. (2021)', false, 37);


-- Room of Abstracts questions for mission 2: second column
INSERT INTO question (id, title, location_id) VALUES (38, '2_1_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 38);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (168, 'Level I — Systematic Reviews / Meta-analyses', true, 38);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (169, 'Level II — Randomized Controlled Trials', false, 38);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (170, 'Level III — Controlled Trials (no randomization)', false, 38);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (171, 'Level IV — Case-Control / Cohort Studies', false, 38);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (172, 'Level V — Systematic Reviews of Descriptive Studies', false, 38);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (173, 'Level VI — Single Descriptive / Qualitative Study', false, 38);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (174, 'Level VII — Expert Opinion', false, 38);

INSERT INTO question (id, title, location_id) VALUES (39, '2_2_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 39);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (175, 'Level I — Systematic Reviews / Meta-analyses', true, 39);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (176, 'Level II — Randomized Controlled Trials', false, 39);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (177, 'Level III — Controlled Trials (no randomization)', false, 39);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (178, 'Level IV — Case-Control / Cohort Studies', false, 39);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (179, 'Level V — Systematic Reviews of Descriptive Studies', false, 39);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (180, 'Level VI — Single Descriptive / Qualitative Study', false, 39);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (181, 'Level VII — Expert Opinion', false, 39);

INSERT INTO question (id, title, location_id) VALUES (40, '2_3_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 40);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (182, 'Level VII — Expert Opinion', true, 40);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (183, 'Level I — Systematic Reviews / Meta-analyses', false, 40);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (184, 'Level II — Randomized Controlled Trials', false, 40);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (185, 'Level III — Controlled Trials (no randomization)', false, 40);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (186, 'Level IV — Case-Control / Cohort Studies', false, 40);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (187, 'Level V — Systematic Reviews of Descriptive Studies', false, 40);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (188, 'Level VI — Single Descriptive / Qualitative Study', false, 40);

-- Room of Abstracts questions for mission 2: third column
INSERT INTO question (id, title, location_id) VALUES (41, '3_1_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 41);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (189, 'Ia — Meta-analysis of RCTs', true, 41);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (190, 'Ib — At least one RCT', false, 41);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (191, 'IIa — Controlled study (no randomization)', false, 41);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (192, 'IIb — Quasi-experimental study', false, 41);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (193, 'III — Non-experimental descriptive studies', false, 41);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (194, 'IV — Expert committee reports / opinions', false, 41);

INSERT INTO question (id, title, location_id) VALUES (42, '3_2_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 42);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (195, 'Ia — Meta-analysis of RCTs', true, 42);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (196, 'Ib — At least one RCT', false, 42);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (197, 'IIa — Controlled study (no randomization)', false, 42);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (198, 'IIb — Quasi-experimental study', false, 42);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (199, 'III — Non-experimental descriptive studies', false, 42);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (200, 'IV — Expert committee reports / opinions', false, 42);

INSERT INTO question (id, title, location_id) VALUES (43, '3_3_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 43);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (201, 'IV — Expert committee reports / opinions', true, 43);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (202, 'Ia — Meta-analysis of RCTs', false, 43);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (203, 'Ib — At least one RCT', false, 43);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (204, 'IIa — Controlled study (no randomization)', false, 43);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (205, 'IIb — Quasi-experimental study', false, 43);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (206, 'III — Non-experimental descriptive studies', false, 43);

-- Room of Abstracts questions for mission 2: fourth column
INSERT INTO question (id, title, location_id) VALUES (44, '4_1_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 44);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (207, 'Systematic Review', true, 44);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (208, 'Meta-Analysis', false, 44);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (209, 'Randomized Controlled Trial (RCT)', false, 44);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (210, 'Retrospective cohort study', false, 44);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (211, 'Cross-Sectional Study', false, 44);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (212, 'Expert Opinion / Editorial', false, 44);

INSERT INTO question (id, title, location_id) VALUES (45, '4_2_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 45);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (213, 'Systematic Review', true, 45);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (214, 'Meta-Analysis', false, 45);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (215, 'Randomized Controlled Trial (RCT)', false, 45);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (216, 'Retrospective cohort study', false, 45);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (217, 'Cross-Sectional Study', false, 45);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (218, 'Expert Opinion / Editorial', false, 45);

INSERT INTO question (id, title, location_id) VALUES (46, '4_3_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 46);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (219, 'Expert Opinion / Editorial', true, 46);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (220, 'Systematic Review', false, 46);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (221, 'Meta-Analysis', false, 46);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (222, 'Randomized Controlled Trial (RCT)', false, 46);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (223, 'Retrospective cohort study', false, 46);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (224, 'Cross-Sectional Study', false, 46);

-- ============================================================================
-- Mission 3: Schmerzmanagement in der postoperativen Pflege
-- ============================================================================
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
INSERT INTO question (id, title, location_id) VALUES (48, '1_1_Study_Title_And_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 48);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (225, 'Postoperative Paintreatment, Authors: Sakata, Okumura & Ogawa (2022)', true, 48);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (226, 'Postoperative Paintreatment, Authors: Sakata, Okumura & Ogawa (2023)', false, 48);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (227, 'Postoperative Paintreatment, Authors: Sakata, Okumura & Ogawa (2017)', false, 48);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (228, 'Nursing Music Protocol and Postoperative Pain, Authors: Poulsen & Coto (2017)', false, 48);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (229, 'Documentation-for Assessing Pain in Postoperative Pain Management Pre- and Post-intervention, Authors: Huong & Stafseth (2022)', false, 48);

INSERT INTO question (id, title, location_id) VALUES (49, '1_2_Study_Title_And_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 49);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (230, 'Documentation-for Assessing Pain in Postoperative Pain Management Pre- and Post-intervention, Authors: Huong & Stafseth (2023)', true, 49);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (231, 'Documentation-for Assessing Pain in Postoperative Pain Management Pre- and Post-intervention, Authors: Huong & Stafseth (2022)', false, 49);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (232, 'Documentation-for Assessing Pain in Postoperative Pain Management Pre- and Post-intervention, Authors: Huong & Stafseth (2017)', false, 49);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (233, 'Postoperative Paintreatment, Authors: Sakata, Okumura & Ogawa (2022)', false, 49);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (234, 'Nursing Music Protocol and Postoperative Pain, Authors: Poulsen & Coto (2023)', false, 49);

INSERT INTO question (id, title, location_id) VALUES (50, '1_3_Study_Title_And_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 50);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (235, 'Nursing Music Protocol and Postoperative Pain, Authors: Poulsen & Coto (2017)', true, 50);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (236, 'Nursing Music Protocol and Postoperative Pain, Authors: Poulsen & Coto (2022)', false, 50);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (237, 'Nursing Music Protocol and Postoperative Pain, Authors: Poulsen & Coto (2023)', false, 50);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (238, 'Postoperative Paintreatment, Authors: Sakata, Okumura & Ogawa (2017)', false, 50);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (239, 'Documentation-for Assessing Pain in Postoperative Pain Management Pre- and Post-intervention, Authors: Huong & Stafseth (2023)', false, 50);

-- Room of Abstracts questions for mission 3: second column
INSERT INTO question (id, title, location_id) VALUES (51, '2_1_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 51);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (240, 'Level III — Controlled Trials (no randomization)', true, 51);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (241, 'Level I — Systematic Reviews / Meta-analyses', false, 51);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (242, 'Level II — Randomized Controlled Trials', false, 51);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (243, 'Level IV — Case-Control / Cohort Studies', false, 51);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (244, 'Level V — Systematic Reviews of Descriptive Studies', false, 51);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (245, 'Level VI — Single Descriptive / Qualitative Study', false, 51);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (246, 'Level VII — Expert Opinion', false, 51);

INSERT INTO question (id, title, location_id) VALUES (52, '2_2_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 52);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (247, 'Level IV — Case-Control / Cohort Studies', true, 52);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (248, 'Level I — Systematic Reviews / Meta-analyses', false, 52);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (249, 'Level II — Randomized Controlled Trials', false, 52);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (250, 'Level III — Controlled Trials (no randomization)', false, 52);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (251, 'Level V — Systematic Reviews of Descriptive Studies', false, 52);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (252, 'Level VI — Single Descriptive / Qualitative Study', false, 52);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (253, 'Level VII — Expert Opinion', false, 52);

INSERT INTO question (id, title, location_id) VALUES (53, '2_3_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 53);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (254, 'Level VII — Expert Opinion', true, 53);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (255, 'Level I — Systematic Reviews / Meta-analyses', false, 53);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (256, 'Level II — Randomized Controlled Trials', false, 53);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (257, 'Level III — Controlled Trials (no randomization)', false, 53);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (258, 'Level IV — Case-Control / Cohort Studies', false, 53);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (259, 'Level V — Systematic Reviews of Descriptive Studies', false, 53);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (260, 'Level VI — Single Descriptive / Qualitative Study', false, 53);

-- Room of Abstracts questions for mission 3: third column
INSERT INTO question (id, title, location_id) VALUES (54, '3_1_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 54);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (261, 'IIb — Quasi-experimental study', true, 54);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (262, 'Ia — Meta-analysis of RCTs', false, 54);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (263, 'Ib — At least one RCT', false, 54);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (264, 'IIa — Controlled study (no randomization)', false, 54);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (265, 'III — Non-experimental descriptive studies', false, 54);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (266, 'IV — Expert committee reports / opinions', false, 54);

INSERT INTO question (id, title, location_id) VALUES (55, '3_2_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 55);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (267, 'IIb — Quasi-experimental study', true, 55);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (268, 'Ia — Meta-analysis of RCTs', false, 55);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (269, 'Ib — At least one RCT', false, 55);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (270, 'IIa — Controlled study (no randomization)', false, 55);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (271, 'III — Non-experimental descriptive studies', false, 55);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (272, 'IV — Expert committee reports / opinions', false, 55);

INSERT INTO question (id, title, location_id) VALUES (56, '3_3_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 56);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (273, 'IV — Expert committee reports / opinions', true, 56);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (274, 'Ia — Meta-analysis of RCTs', false, 56);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (275, 'Ib — At least one RCT', false, 56);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (276, 'IIa — Controlled study (no randomization)', false, 56);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (277, 'IIb — Quasi-experimental study', false, 56);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (278, 'III — Non-experimental descriptive studies', false, 56);

-- Room of Abstracts questions for mission 3: fourth column
INSERT INTO question (id, title, location_id) VALUES (57, '4_1_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 57);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (279, 'Retrospective cohort study', true, 57);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (280, 'Systematic Review', false, 57);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (281, 'Meta-Analysis', false, 57);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (282, 'Randomized Controlled Trial (RCT)', false, 57);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (283, 'Cross-Sectional Study', false, 57);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (284, 'Expert Opinion / Editorial', false, 57);

INSERT INTO question (id, title, location_id) VALUES (58, '4_2_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 58);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (285, 'Pre-post intervention study', true, 58);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (286, 'Systematic Review', false, 58);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (287, 'Meta-Analysis', false, 58);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (288, 'Randomized Controlled Trial (RCT)', false, 58);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (289, 'Retrospective cohort study', false, 58);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (290, 'Cross-Sectional Study', false, 58);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (291, 'Expert Opinion / Editorial', false, 58);

INSERT INTO question (id, title, location_id) VALUES (59, '4_3_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 59);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (292, 'Expert Opinion / Editorial', true, 59);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (293, 'Systematic Review', false, 59);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (294, 'Meta-Analysis', false, 59);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (295, 'Randomized Controlled Trial (RCT)', false, 59);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (296, 'Retrospective cohort study', false, 59);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (297, 'Cross-Sectional Study', false, 59);


-- ============================================================================
-- Mission 4: Ernährungsinterventionen bei Mangelernährung
-- ============================================================================
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
INSERT INTO question (id, title, location_id) VALUES (61, '1_1_Study_Title_And_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 61);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (298, 'Interventions to prevent and treat malnutrition in older adults to be carried out by nurses: A Systematic Review, Authors: ten Cate et al. (2019)', true, 61);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (299, 'Interventions to prevent and treat malnutrition in older adults to be carried out by nurses: A Systematic Review, Authors: ten Cate et al. (2022)', false, 61);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (300, 'Interventions to prevent and treat malnutrition in older adults to be carried out by nurses: A Systematic Review, Authors: ten Cate et al. (2024)', false, 61);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (301, 'Gefahr einer Mangelernährung älterer Patient_innen im Akutspital, Authors: Brunner et al. (2022)', false, 61);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (302, 'Effects of a specialized Oral Nutritional Supplement with Dietary Counseling on nutritional Outcomes in Community-Dwelling Older Adults at Risk of Malnutrition: A RCT, Authors: Muangpaisan et al. (2019)', false, 61);

INSERT INTO question (id, title, location_id) VALUES (62, '1_2_Study_Title_And_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 62);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (303, 'Effects of a specialized Oral Nutritional Supplement with Dietary Counseling on nutritional Outcomes in Community-Dwelling Older Adults at Risk of Malnutrition: A RCT, Authors: Muangpaisan et al. (2024)', true, 62);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (304, 'Effects of a specialized Oral Nutritional Supplement with Dietary Counseling on nutritional Outcomes in Community-Dwelling Older Adults at Risk of Malnutrition: A RCT, Authors: Muangpaisan et al. (2022)', false, 62);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (305, 'Effects of a specialized Oral Nutritional Supplement with Dietary Counseling on nutritional Outcomes in Community-Dwelling Older Adults at Risk of Malnutrition: A RCT, Authors: Muangpaisan et al. (2019)', false, 62);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (306, 'Interventions to prevent and treat malnutrition in older adults to be carried out by nurses: A Systematic Review, Authors: ten Cate et al. (2019)', false, 62);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (307, 'Gefahr einer Mangelernährung älterer Patient_innen im Akutspital, Authors: Brunner et al. (2019)', false, 62);

INSERT INTO question (id, title, location_id) VALUES (63, '1_3_Study_Title_And_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 63);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (308, 'Gefahr einer Mangelernährung älterer Patient_innen im Akutspital, Authors: Brunner et al. (2022)', true, 63);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (309, 'Gefahr einer Mangelernährung älterer Patient_innen im Akutspital, Authors: Brunner et al. (2024)', false, 63);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (310, 'Gefahr einer Mangelernährung älterer Patient_innen im Akutspital, Authors: Brunner et al. (2019)', false, 63);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (311, 'Interventions to prevent and treat malnutrition in older adults to be carried out by nurses: A Systematic Review, Authors: ten Cate et al. (2022)', false, 63);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (312, 'Effects of a specialized Oral Nutritional Supplement with Dietary Counseling on nutritional Outcomes in Community-Dwelling Older Adults at Risk of Malnutrition: A RCT, Authors: Muangpaisan et al. (2024)', false, 63);

-- Room of Abstracts questions for mission 4: second column
INSERT INTO question (id, title, location_id) VALUES (64, '2_1_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 64);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (313, 'Level I — Systematic Reviews / Meta-analyses', true, 64);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (314, 'Level II — Randomized Controlled Trials', false, 64);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (315, 'Level III — Controlled Trials (no randomization)', false, 64);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (316, 'Level IV — Case-Control / Cohort Studies', false, 64);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (317, 'Level V — Systematic Reviews of Descriptive Studies', false, 64);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (318, 'Level VI — Single Descriptive / Qualitative Study', false, 64);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (319, 'Level VII — Expert Opinion', false, 64);

INSERT INTO question (id, title, location_id) VALUES (65, '2_2_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 65);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (320, 'Level II — Randomized Controlled Trials', true, 65);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (321, 'Level I — Systematic Reviews / Meta-analyses', false, 65);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (322, 'Level III — Controlled Trials (no randomization)', false, 65);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (323, 'Level IV — Case-Control / Cohort Studies', false, 65);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (324, 'Level V — Systematic Reviews of Descriptive Studies', false, 65);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (325, 'Level VI — Single Descriptive / Qualitative Study', false, 65);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (326, 'Level VII — Expert Opinion', false, 65);

INSERT INTO question (id, title, location_id) VALUES (66, '2_3_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 66);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (327, 'Level IV — Case-Control / Cohort Studies', true, 66);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (328, 'Level I — Systematic Reviews / Meta-analyses', false, 66);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (329, 'Level II — Randomized Controlled Trials', false, 66);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (330, 'Level III — Controlled Trials (no randomization)', false, 66);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (331, 'Level V — Systematic Reviews of Descriptive Studies', false, 66);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (332, 'Level VI — Single Descriptive / Qualitative Study', false, 66);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (333, 'Level VII — Expert Opinion', false, 66);

-- Room of Abstracts questions for mission 4: third column
INSERT INTO question (id, title, location_id) VALUES (67, '3_1_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 67);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (334, 'Ia — Meta-analysis of RCTs', true, 67);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (335, 'Ib — At least one RCT', false, 67);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (336, 'IIa — Controlled study (no randomization)', false, 67);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (337, 'IIb — Quasi-experimental study', false, 67);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (338, 'III — Non-experimental descriptive studies', false, 67);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (339, 'IV — Expert committee reports / opinions', false, 67);

INSERT INTO question (id, title, location_id) VALUES (68, '3_2_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 68);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (340, 'Ib — At least one RCT', true, 68);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (341, 'Ia — Meta-analysis of RCTs', false, 68);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (342, 'IIa — Controlled study (no randomization)', false, 68);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (343, 'IIb — Quasi-experimental study', false, 68);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (344, 'III — Non-experimental descriptive studies', false, 68);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (345, 'IV — Expert committee reports / opinions', false, 68);

INSERT INTO question (id, title, location_id) VALUES (69, '3_3_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 69);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (346, 'III — Non-experimental descriptive studies', true, 69);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (347, 'Ia — Meta-analysis of RCTs', false, 69);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (348, 'Ib — At least one RCT', false, 69);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (349, 'IIa — Controlled study (no randomization)', false, 69);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (350, 'IIb — Quasi-experimental study', false, 69);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (351, 'IV — Expert committee reports / opinions', false, 69);

-- Room of Abstracts questions for mission 4: fourth column
INSERT INTO question (id, title, location_id) VALUES (70, '4_1_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 70);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (352, 'Systematic Review', true, 70);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (353, 'Meta-Analysis', false, 70);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (354, 'Randomized Controlled Trial (RCT)', false, 70);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (355, 'Retrospective cohort study', false, 70);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (356, 'Cross-Sectional Study', false, 70);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (357, 'Expert Opinion / Editorial', false, 70);

INSERT INTO question (id, title, location_id) VALUES (71, '4_2_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 71);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (358, 'Randomized Controlled Trial (RCT)', true, 71);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (359, 'Systematic Review', false, 71);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (360, 'Meta-Analysis', false, 71);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (361, 'Retrospective cohort study', false, 71);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (362, 'Cross-Sectional Study', false, 71);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (363, 'Expert Opinion / Editorial', false, 71);

INSERT INTO question (id, title, location_id) VALUES (72, '4_3_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 72);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (364, 'Cross-Sectional Study', true, 72);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (365, 'Systematic Review', false, 72);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (366, 'Meta-Analysis', false, 72);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (367, 'Randomized Controlled Trial (RCT)', false, 72);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (368, 'Retrospective cohort study', false, 72);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (369, 'Expert Opinion / Editorial', false, 72);


-- ============================================================================
-- Mission 5: Vermeidung von Katheter-assoziierten Harnwegsinfektionen
-- ============================================================================
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
INSERT INTO question (id, title, location_id) VALUES (74, '1_1_Study_Title_And_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 74);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (370, 'Protocolized Urine Sampling is Associated with Reduced Catheter-associated Urinary Tract Infections: A Pre- & Postintervention Study, Authors: Frontera et al. (2021)', true, 74);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (371, 'Protocolized Urine Sampling is Associated with Reduced Catheter-associated Urinary Tract Infections: A Pre- & Postintervention Study, Authors: Frontera et al. (2024)', false, 74);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (372, 'Protocolized Urine Sampling is Associated with Reduced Catheter-associated Urinary Tract Infections: A Pre- & Postintervention Study, Authors: Frontera et al. (2023)', false, 74);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (373, 'Strategies to prevent catheter-associated urinary tract infections in acute-care hospitals: 2022 Update, Authors: Patel et al. (2023)', false, 74);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (374, 'Implementation of a multi-modal intervention adopting new technologies, clinical services, and feedback improves catheter-associated urinary tract infections, Authors: Fish et al. (2021)', false, 74);

INSERT INTO question (id, title, location_id) VALUES (75, '1_2_Study_Title_And_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 75);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (375, 'Implementation of a multi-modal intervention adopting new technologies, clinical services, and feedback improves catheter-associated urinary tract infections, Authors: Fish et al. (2024)', true, 75);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (376, 'Implementation of a multi-modal intervention adopting new technologies, clinical services, and feedback improves catheter-associated urinary tract infections, Authors: Fish et al. (2021)', false, 75);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (377, 'Implementation of a multi-modal intervention adopting new technologies, clinical services, and feedback improves catheter-associated urinary tract infections, Authors: Fish et al. (2023)', false, 75);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (378, 'Protocolized Urine Sampling is Associated with Reduced Catheter-associated Urinary Tract Infections: A Pre- & Postintervention Study, Authors: Frontera et al. (2021)', false, 75);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (379, 'Strategies to prevent catheter-associated urinary tract infections in acute-care hospitals: 2022 Update, Authors: Patel et al. (2021)', false, 75);

INSERT INTO question (id, title, location_id) VALUES (76, '1_3_Study_Title_And_Autor?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 76);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (380, 'Strategies to prevent catheter-associated urinary tract infections in acute-care hospitals: 2022 Update, Authors: Patel et al. (2023)', true, 76);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (381, 'Strategies to prevent catheter-associated urinary tract infections in acute-care hospitals: 2022 Update, Authors: Patel et al. (2024)', false, 76);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (382, 'Strategies to prevent catheter-associated urinary tract infections in acute-care hospitals: 2022 Update, Authors: Patel et al. (2021)', false, 76);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (383, 'Protocolized Urine Sampling is Associated with Reduced Catheter-associated Urinary Tract Infections: A Pre- & Postintervention Study, Authors: Frontera et al. (2023)', false, 76);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (384, 'Implementation of a multi-modal intervention adopting new technologies, clinical services, and feedback improves catheter-associated urinary tract infections, Authors: Fish et al. (2024)', false, 76);

-- Room of Abstracts questions for mission 5: second column
INSERT INTO question (id, title, location_id) VALUES (77, '2_1_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 77);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (385, 'Level III — Controlled Trials (no randomization)', true, 77);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (386, 'Level I — Systematic Reviews / Meta-analyses', false, 77);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (387, 'Level II — Randomized Controlled Trials', false, 77);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (388, 'Level IV — Case-Control / Cohort Studies', false, 77);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (389, 'Level V — Systematic Reviews of Descriptive Studies', false, 77);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (390, 'Level VI — Single Descriptive / Qualitative Study', false, 77);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (391, 'Level VII — Expert Opinion', false, 77);

INSERT INTO question (id, title, location_id) VALUES (78, '2_2_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 78);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (392, 'Level III — Controlled Trials (no randomization)', true, 78);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (393, 'Level I — Systematic Reviews / Meta-analyses', false, 78);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (394, 'Level II — Randomized Controlled Trials', false, 78);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (395, 'Level IV — Case-Control / Cohort Studies', false, 78);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (396, 'Level V — Systematic Reviews of Descriptive Studies', false, 78);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (397, 'Level VI — Single Descriptive / Qualitative Study', false, 78);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (398, 'Level VII — Expert Opinion', false, 78);

INSERT INTO question (id, title, location_id) VALUES (79, '2_3_Pyramide (LoE)?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 79);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (399, 'Level VII — Expert Opinion', true, 79);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (400, 'Level I — Systematic Reviews / Meta-analyses', false, 79);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (401, 'Level II — Randomized Controlled Trials', false, 79);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (402, 'Level III — Controlled Trials (no randomization)', false, 79);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (403, 'Level IV — Case-Control / Cohort Studies', false, 79);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (404, 'Level V — Systematic Reviews of Descriptive Studies', false, 79);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (405, 'Level VI — Single Descriptive / Qualitative Study', false, 79);

-- Room of Abstracts questions for mission 5: third column
INSERT INTO question (id, title, location_id) VALUES (80, '3_1_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 80);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (406, 'IIb — Quasi-experimental study', true, 80);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (407, 'Ia — Meta-analysis of RCTs', false, 80);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (408, 'Ib — At least one RCT', false, 80);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (409, 'IIa — Controlled study (no randomization)', false, 80);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (410, 'III — Non-experimental descriptive studies', false, 80);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (411, 'IV — Expert committee reports / opinions', false, 80);

INSERT INTO question (id, title, location_id) VALUES (81, '3_2_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 81);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (412, 'IIb — Quasi-experimental study', true, 81);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (413, 'Ia — Meta-analysis of RCTs', false, 81);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (414, 'Ib — At least one RCT', false, 81);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (415, 'IIa — Controlled study (no randomization)', false, 81);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (416, 'III — Non-experimental descriptive studies', false, 81);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (417, 'IV — Expert committee reports / opinions', false, 81);

INSERT INTO question (id, title, location_id) VALUES (82, '3_3_AHCPR?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 82);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (418, 'IV — Expert committee reports / opinions', true, 82);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (419, 'Ia — Meta-analysis of RCTs', false, 82);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (420, 'Ib — At least one RCT', false, 82);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (421, 'IIa — Controlled study (no randomization)', false, 82);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (422, 'IIb — Quasi-experimental study', false, 82);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (423, 'III — Non-experimental descriptive studies', false, 82);

-- Room of Abstracts questions for mission 5: fourth column
INSERT INTO question (id, title, location_id) VALUES (83, '4_1_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 83);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (424, 'Pre-post intervention study', true, 83);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (425, 'Systematic Review', false, 83);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (426, 'Meta-Analysis', false, 83);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (427, 'Randomized Controlled Trial (RCT)', false, 83);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (428, 'Retrospective cohort study', false, 83);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (429, 'Cross-Sectional Study', false, 83);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (430, 'Expert Opinion / Editorial', false, 83);

INSERT INTO question (id, title, location_id) VALUES (84, '4_2_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 84);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (431, 'Retrospective cohort study', true, 84);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (432, 'Systematic Review', false, 84);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (433, 'Meta-Analysis', false, 84);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (434, 'Randomized Controlled Trial (RCT)', false, 84);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (435, 'Cross-Sectional Study', false, 84);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (436, 'Expert Opinion / Editorial', false, 84);

INSERT INTO question (id, title, location_id) VALUES (85, '4_3_Study Design?', (SELECT id FROM location WHERE name = 'Room of Abstracts'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 85);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (437, 'Expert Opinion / Editorial', true, 85);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (438, 'Systematic Review', false, 85);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (439, 'Meta-Analysis', false, 85);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (440, 'Randomized Controlled Trial (RCT)', false, 85);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (441, 'Retrospective cohort study', false, 85);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (442, 'Cross-Sectional Study', false, 85);
