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
INSERT INTO mission (name) VALUES ('Wundversorgung bei Dekubitus');
INSERT INTO mission (name) VALUES ('Sturzprävention in der Geriatrie');
INSERT INTO mission (name) VALUES ('Schmerzmanagement in der postoperativen Pflege');
INSERT INTO mission (name) VALUES ('Ernährungsinterventionen bei Mangelernährung');
INSERT INTO mission (name) VALUES ('Vermeidung von Katheter-assoziierten Harnwegsinfektionen');

-- Update all existing questions to get 'Room of Knowledge' as Location
-- We need to find the id of 'Room of Knowledge' first.
-- Since the location table is using IDENTITY, we can query it.
UPDATE question SET location_id = (SELECT id FROM location WHERE name = 'Room of Knowledge');

-- Distribute Room of Knowledge questions among missions
INSERT INTO mission_question (mission_id, question_id)
SELECT (SELECT id FROM mission WHERE name = 'Wundversorgung bei Dekubitus'), id FROM question WHERE id BETWEEN 1 AND 20;

INSERT INTO mission_question (mission_id, question_id)
SELECT (SELECT id FROM mission WHERE name = 'Sturzprävention in der Geriatrie'), id FROM question WHERE id BETWEEN 1 AND 20;

INSERT INTO mission_question (mission_id, question_id)
SELECT (SELECT id FROM mission WHERE name = 'Schmerzmanagement in der postoperativen Pflege'), id FROM question WHERE id BETWEEN 1 AND 20;

INSERT INTO mission_question (mission_id, question_id)
SELECT (SELECT id FROM mission WHERE name = 'Ernährungsinterventionen bei Mangelernährung'), id FROM question WHERE id BETWEEN 1 AND 20;

INSERT INTO mission_question (mission_id, question_id)
SELECT (SELECT id FROM mission WHERE name = 'Vermeidung von Katheter-assoziierten Harnwegsinfektionen'), id FROM question WHERE id BETWEEN 1 AND 20;
