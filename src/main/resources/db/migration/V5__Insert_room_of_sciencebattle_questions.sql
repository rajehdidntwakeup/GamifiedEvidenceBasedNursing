-- V5__Insert_room_of_sciencebattle_questions.sql

-- Room of Science Battle questions for all 5 missions

-- ============================================================================
-- Mission 1: Wundversorgung bei Dekubitus
-- ============================================================================
INSERT INTO question (id, title, location_id)
VALUES (116, 'Führt eine Grobrecherche durch, um eine weitere Studie zu finden, die die bereits kritisch gewürdigte Intervention unterstützt.',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 116);
INSERT INTO document_question (document_id, question_id) VALUES (16, 116);


INSERT INTO question (id, title, location_id)
VALUES (117, 'Level of Evidence - Study A',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));

INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 117);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (478, 'Level III — Controlled Trials (no randomization)', false, 117);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (479, 'Level I — Systematic Reviews / Meta-analyses', true, 117);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (480, 'Level II — Randomized Controlled Trials', false, 117);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (481, 'Level IV — Case-Control / Cohort Studies', false, 117);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (482, 'Level V — Systematic Reviews of Descriptive Studies', false, 117);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (483, 'Level VI — Single Descriptive / Qualitative Study', false, 117);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (484, 'Level VII — Expert Opinion', false, 117);


INSERT INTO question (id, title, location_id)
VALUES (118, 'Level of Evidence - Study B',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));

INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 118);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (485, 'Level III — Controlled Trials (no randomization)', true, 118);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (486, 'Level I — Systematic Reviews / Meta-analyses', false, 118);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (487, 'Level II — Randomized Controlled Trials', false, 118);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (488, 'Level IV — Case-Control / Cohort Studies', false, 118);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (489, 'Level V — Systematic Reviews of Descriptive Studies', false, 118);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (490, 'Level VI — Single Descriptive / Qualitative Study', false, 118);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (491, 'Level VII — Expert Opinion', false, 118);


INSERT INTO question (id, title, location_id)
VALUES (119, 'Which study provides the better evidence for the intervention?',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 119);

INSERT INTO answer (id, text, is_correct, question_id) VALUES (492, 'Study A', true, 119);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (493, 'Study B', false, 119);


INSERT INTO question (id, title, location_id)
VALUES (120, 'Explain why your chosen study provides better evidence. Consider study design, level of evidence, sample size, methodological rigor, and clinical outcomes.',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 120);


INSERT INTO question (id, title, location_id)
VALUES (121, 'Compare the key methodological differences between the two studies (e.g., randomization, blinding, sample selection, compliance, outcome measures).',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), 121);


-- ============================================================================
-- Mission 2: Sturzprävention in der Geriatrie
-- ============================================================================
INSERT INTO question (id, title, location_id)
VALUES (122, 'Führt eine Grobrecherche durch, um eine weitere Studie zu finden, die die bereits kritisch gewürdigte Intervention unterstützt.',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 122);
INSERT INTO document_question (document_id, question_id) VALUES (17, 122);


INSERT INTO question (id, title, location_id)
VALUES (123, 'Level of Evidence - Study A',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));

INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 123);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (494, 'Level I — Systematic Reviews / Meta-analyses', true, 123);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (495, 'Level II — Randomized Controlled Trials', false, 123);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (496, 'Level III — Controlled Trials (no randomization)', false, 123);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (497, 'Level IV — Case-Control / Cohort Studies', false, 123);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (498, 'Level V — Systematic Reviews of Descriptive Studies', false, 123);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (499, 'Level VI — Single Descriptive / Qualitative Study', false, 123);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (500, 'Level VII — Expert Opinion', false, 123);


INSERT INTO question (id, title, location_id)
VALUES (124, 'Level of Evidence - Study B',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));

INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 124);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (501, 'Level I — Systematic Reviews / Meta-analyses', false, 124);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (502, 'Level II — Randomized Controlled Trials', true, 124);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (503, 'Level III — Controlled Trials (no randomization)', false, 124);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (504, 'Level IV — Case-Control / Cohort Studies', false, 124);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (505, 'Level V — Systematic Reviews of Descriptive Studies', false, 124);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (506, 'Level VI — Single Descriptive / Qualitative Study', false, 124);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (507, 'Level VII — Expert Opinion', false, 124);


INSERT INTO question (id, title, location_id)
VALUES (125, 'Which study provides the better evidence for the intervention?',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 125);

INSERT INTO answer (id, text, is_correct, question_id) VALUES (508, 'Study A', true, 125);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (509, 'Study B', false, 125);


INSERT INTO question (id, title, location_id)
VALUES (126, 'Explain why your chosen study provides better evidence. Consider study design, level of evidence, sample size, methodological rigor, and clinical outcomes.',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 126);


INSERT INTO question (id, title, location_id)
VALUES (127, 'Compare the key methodological differences between the two studies (e.g., randomization, blinding, sample selection, compliance, outcome measures).',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), 127);


-- ============================================================================
-- Mission 3: Schmerzmanagement in der postoperativen Pflege
-- ============================================================================
INSERT INTO question (id, title, location_id)
VALUES (128, 'Führt eine Grobrecherche durch, um eine weitere Studie zu finden, die die bereits kritisch gewürdigte Intervention unterstützt.',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 128);
INSERT INTO document_question (document_id, question_id) VALUES (18, 128);


INSERT INTO question (id, title, location_id)
VALUES (129, 'Level of Evidence - Study A',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));

INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 129);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (510, 'Level III — Controlled Trials (no randomization)', false, 129);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (511, 'Level I — Systematic Reviews / Meta-analyses', false, 129);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (512, 'Level II — Randomized Controlled Trials', false, 129);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (513, 'Level IV — Case-Control / Cohort Studies', true, 129);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (514, 'Level V — Systematic Reviews of Descriptive Studies', false, 129);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (515, 'Level VI — Single Descriptive / Qualitative Study', false, 129);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (516, 'Level VII — Expert Opinion', false, 129);


INSERT INTO question (id, title, location_id)
VALUES (130, 'Level of Evidence - Study B',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));

INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 130);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (517, 'Level III — Controlled Trials (no randomization)', true, 130);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (518, 'Level I — Systematic Reviews / Meta-analyses', false, 130);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (519, 'Level II — Randomized Controlled Trials', false, 130);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (520, 'Level IV — Case-Control / Cohort Studies', false, 130);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (521, 'Level V — Systematic Reviews of Descriptive Studies', false, 130);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (522, 'Level VI — Single Descriptive / Qualitative Study', false, 130);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (523, 'Level VII — Expert Opinion', false, 130);


INSERT INTO question (id, title, location_id)
VALUES (131, 'Which study provides the better evidence for the intervention?',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 131);

INSERT INTO answer (id, text, is_correct, question_id) VALUES (524, 'Study A', false, 131);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (525, 'Study B', true, 131);


INSERT INTO question (id, title, location_id)
VALUES (132, 'Explain why your chosen study provides better evidence. Consider study design, level of evidence, sample size, methodological rigor, and clinical outcomes.',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 132);


INSERT INTO question (id, title, location_id)
VALUES (133, 'Compare the key methodological differences between the two studies (e.g., randomization, blinding, sample selection, compliance, outcome measures).',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), 133);


-- ============================================================================
-- Mission 4: Ernährungsinterventionen bei Mangelernährung
-- ============================================================================
INSERT INTO question (id, title, location_id)
VALUES (134, 'Führt eine Grobrecherche durch, um eine weitere Studie zu finden, die die bereits kritisch gewürdigte Intervention unterstützt.',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 134);
INSERT INTO document_question (document_id, question_id) VALUES (19, 134);


INSERT INTO question (id, title, location_id)
VALUES (135, 'Level of Evidence - Study A',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));

INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 135);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (526, 'Level I — Systematic Reviews / Meta-analyses', true, 135);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (527, 'Level II — Randomized Controlled Trials', false, 135);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (528, 'Level III — Controlled Trials (no randomization)', false, 135);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (529, 'Level IV — Case-Control / Cohort Studies', false, 135);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (530, 'Level V — Systematic Reviews of Descriptive Studies', false, 135);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (531, 'Level VI — Single Descriptive / Qualitative Study', false, 135);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (532, 'Level VII — Expert Opinion', false, 135);


INSERT INTO question (id, title, location_id)
VALUES (136, 'Level of Evidence - Study B',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));

INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 136);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (533, 'Level I — Systematic Reviews / Meta-analyses', false, 136);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (534, 'Level II — Randomized Controlled Trials', true, 136);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (535, 'Level III — Controlled Trials (no randomization)', false, 136);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (536, 'Level IV — Case-Control / Cohort Studies', false, 136);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (537, 'Level V — Systematic Reviews of Descriptive Studies', false, 136);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (538, 'Level VI — Single Descriptive / Qualitative Study', false, 136);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (539, 'Level VII — Expert Opinion', false, 136);


INSERT INTO question (id, title, location_id)
VALUES (137, 'Which study provides the better evidence for the intervention?',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 137);

INSERT INTO answer (id, text, is_correct, question_id) VALUES (540, 'Study A', true, 137);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (541, 'Study B', false, 137);


INSERT INTO question (id, title, location_id)
VALUES (138, 'Explain why your chosen study provides better evidence. Consider study design, level of evidence, sample size, methodological rigor, and clinical outcomes.',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 138);


INSERT INTO question (id, title, location_id)
VALUES (139, 'Compare the key methodological differences between the two studies (e.g., randomization, blinding, sample selection, compliance, outcome measures).',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), 139);


-- ============================================================================
-- Mission 5: Vermeidung von Katheter-assoziierten Harnwegsinfektionen
-- ============================================================================
INSERT INTO question (id, title, location_id)
VALUES (140, 'Führt eine Grobrecherche durch, um eine weitere Studie zu finden, die die bereits kritisch gewürdigte Intervention unterstützt.',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 140);
INSERT INTO document_question (document_id, question_id) VALUES (20, 140);


INSERT INTO question (id, title, location_id)
VALUES (141, 'Level of Evidence - Study A',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));

INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 141);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (542, 'Level III — Controlled Trials (no randomization)', true, 141);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (543, 'Level I — Systematic Reviews / Meta-analyses', false, 141);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (544, 'Level II — Randomized Controlled Trials', false, 141);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (545, 'Level IV — Case-Control / Cohort Studies', false, 141);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (546, 'Level V — Systematic Reviews of Descriptive Studies', false, 141);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (547, 'Level VI — Single Descriptive / Qualitative Study', false, 141);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (548, 'Level VII — Expert Opinion', false, 141);


INSERT INTO question (id, title, location_id)
VALUES (142, 'Level of Evidence - Study B',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));

INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 142);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (549, 'Level I — Systematic Reviews / Meta-analyses', false, 142);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (550, 'Level II — Randomized Controlled Trials', true, 142);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (551, 'Level III — Controlled Trials (no randomization)', false, 142);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (552, 'Level IV — Case-Control / Cohort Studies', false, 142);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (553, 'Level V — Systematic Reviews of Descriptive Studies', false, 142);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (554, 'Level VI — Single Descriptive / Qualitative Study', false, 142);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (555, 'Level VII — Expert Opinion', false, 142);


INSERT INTO question (id, title, location_id)
VALUES (143, 'Which study provides the better evidence for the intervention?',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 143);

INSERT INTO answer (id, text, is_correct, question_id) VALUES (556, 'Study A', true, 143);
INSERT INTO answer (id, text, is_correct, question_id) VALUES (557, 'Study B', false, 143);


INSERT INTO question (id, title, location_id)
VALUES (144, 'Explain why your chosen study provides better evidence. Consider study design, level of evidence, sample size, methodological rigor, and clinical outcomes.',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 144);


INSERT INTO question (id, title, location_id)
VALUES (145, 'Compare the key methodological differences between the two studies (e.g., randomization, blinding, sample selection, compliance, outcome measures).',
        (SELECT id FROM location WHERE name = 'Room of Science Battle'));
INSERT INTO mission_question (mission_id, question_id)
VALUES ((SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), 145);
