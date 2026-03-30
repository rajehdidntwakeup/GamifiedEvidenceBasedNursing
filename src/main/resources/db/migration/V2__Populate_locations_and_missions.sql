-- V2__Populate_locations_and_missions.sql

-- Insert Locations
INSERT INTO location (name, location_key, timer) VALUES ('Start', null, 0);
INSERT INTO location (name, location_key, timer) VALUES ('Room of Knowledge', 'E-C', 10);
INSERT INTO location (name, location_key, timer) VALUES ('Room of Abstracts', 'F-O', 20);
INSERT INTO location (name, location_key, timer) VALUES ('Room of Analytics', 'L-R', 70);
INSERT INTO location (name, location_key, timer) VALUES ('Room of Science Battle', 'N-E', 45);
INSERT INTO location (name, location_key, timer) VALUES ('Last Quiz', null, 0);
INSERT INTO location (name, location_key, timer) VALUES ('End State', null, 0);

-- Insert Missions
INSERT INTO mission (name) VALUES ('WOUND_CARE_FOR_PRESSURE_ULCERS');
INSERT INTO mission (name) VALUES ('FALL_PREVENTION_IN_GERIATRICS');
INSERT INTO mission (name) VALUES ('PAIN_MANAGEMENT_IN_POSTOPERATIVE_CARE');
INSERT INTO mission (name) VALUES ('NUTRITIONAL_INTERVENTIONS_FOR_MALNUTRITION');
INSERT INTO mission (name) VALUES ('PREVENTION_OF_CATHETER_ASSOCIATED_URINARY_TRACT_INFECTIONS');

-- Update all existing questions to get 'Room of Knowledge' as Location
-- We need to find the id of 'Room of Knowledge' first.
-- Since the location table is using IDENTITY, we can query it.
UPDATE question SET location_id = (SELECT id FROM location WHERE name = 'Room of Knowledge');

-- Distribute Room of Knowledge questions among missions
INSERT INTO mission_question (mission_id, question_id)
SELECT (SELECT id FROM mission WHERE name = 'WOUND_CARE_FOR_PRESSURE_ULCERS'), id FROM question WHERE id BETWEEN 1 AND 20;

INSERT INTO mission_question (mission_id, question_id)
SELECT (SELECT id FROM mission WHERE name = 'FALL_PREVENTION_IN_GERIATRICS'), id FROM question WHERE id BETWEEN 1 AND 20;

INSERT INTO mission_question (mission_id, question_id)
SELECT (SELECT id FROM mission WHERE name = 'PAIN_MANAGEMENT_IN_POSTOPERATIVE_CARE'), id FROM question WHERE id BETWEEN 1 AND 20;

INSERT INTO mission_question (mission_id, question_id)
SELECT (SELECT id FROM mission WHERE name = 'NUTRITIONAL_INTERVENTIONS_FOR_MALNUTRITION'), id FROM question WHERE id BETWEEN 1 AND 20;

INSERT INTO mission_question (mission_id, question_id)
SELECT (SELECT id FROM mission WHERE name = 'PREVENTION_OF_CATHETER_ASSOCIATED_URINARY_TRACT_INFECTIONS'), id FROM question WHERE id BETWEEN 1 AND 20;
