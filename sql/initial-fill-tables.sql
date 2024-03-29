INSERT INTO mark_types (name, unit, value_type) VALUES ('Load', 'Count of available hours', 'QUANTITATIVE');
INSERT INTO mark_types (name, unit, value_type) VALUES ('Qualification', 'Qualification level', 'QUALITATIVE');
INSERT INTO mark_types (name, unit, value_type) VALUES ('Reviews', 'Students Reviews', 'QUALITATIVE');

INSERT INTO marks (value, numeric_value, mark_type_id) VALUES ('232', 232, 1);
INSERT INTO marks (value, numeric_value, mark_type_id) VALUES ('87', 87, 1);
INSERT INTO marks (value, numeric_value, mark_type_id) VALUES ('116', 116, 1);
INSERT INTO marks (value, numeric_value, mark_type_id) VALUES ('203', 203, 1);

INSERT INTO marks (value, numeric_value, mark_type_id) VALUES ('Professor', 3, 2);
INSERT INTO marks (value, numeric_value, mark_type_id) VALUES ('Docent', 2, 2);
INSERT INTO marks (value, numeric_value, mark_type_id) VALUES ('Head of department', 5, 2);
INSERT INTO marks (value, numeric_value, mark_type_id) VALUES ('Professor 0,5', 1, 2);

INSERT INTO marks (value, numeric_value, mark_type_id) VALUES ('Bad', 1, 3);
INSERT INTO marks (value, numeric_value, mark_type_id) VALUES ('Normal', 2, 3);
INSERT INTO marks (value, numeric_value, mark_type_id) VALUES ('Good', 3, 3);

INSERT INTO comparing_types (name) VALUES ('Head of Diploma');

INSERT INTO criteria (name, weight, mark_type_id, comparing_type_id) VALUES ('Load', 1, 1, 1);
INSERT INTO criteria (name, weight, mark_type_id, comparing_type_id) VALUES ('Qualification', 2, 2, 1);
INSERT INTO criteria (name, weight, mark_type_id, comparing_type_id) VALUES ('Reviews', 5, 3, 1);

INSERT INTO alternatives (name, comparing_type_id) VALUES ('Bilous Nataliya Valentinivna', 1);
INSERT INTO alternatives (name, comparing_type_id) VALUES ('Valenda Natalya Anatolyevna', 1);
INSERT INTO alternatives (name, comparing_type_id) VALUES ('Vechur Oleksandr Volodimirovich', 1);
INSERT INTO alternatives (name, comparing_type_id) VALUES ('Vlasenko Larisa Andreevna', 1);
INSERT INTO alternatives (name, comparing_type_id) VALUES ('Dudar Zoya Volodimirіvna', 1);
INSERT INTO alternatives (name, comparing_type_id) VALUES ('Erokhіn Andriy Leonidovich', 1);

INSERT INTO vectors (criterion_id, alternative_id, mark_id) VALUES (1, 1, 1);
INSERT INTO vectors (criterion_id, alternative_id, mark_id) VALUES (2, 1, 5);
INSERT INTO vectors (criterion_id, alternative_id, mark_id) VALUES (3, 1, 9);

INSERT INTO vectors (criterion_id, alternative_id, mark_id) VALUES (1, 2, 2);
INSERT INTO vectors (criterion_id, alternative_id, mark_id) VALUES (2, 2, 6);
INSERT INTO vectors (criterion_id, alternative_id, mark_id) VALUES (3, 2, 11);

INSERT INTO vectors (criterion_id, alternative_id, mark_id) VALUES (1, 3, 3);
INSERT INTO vectors (criterion_id, alternative_id, mark_id) VALUES (2, 3, 6);
INSERT INTO vectors (criterion_id, alternative_id, mark_id) VALUES (3, 3, 10);

INSERT INTO vectors (criterion_id, alternative_id, mark_id) VALUES (1, 4, 4);
INSERT INTO vectors (criterion_id, alternative_id, mark_id) VALUES (2, 4, 5);
INSERT INTO vectors (criterion_id, alternative_id, mark_id) VALUES (3, 4, 11);

INSERT INTO vectors (criterion_id, alternative_id, mark_id) VALUES (1, 5, 3);
INSERT INTO vectors (criterion_id, alternative_id, mark_id) VALUES (2, 5, 7);
INSERT INTO vectors (criterion_id, alternative_id, mark_id) VALUES (3, 5, 10);

INSERT INTO vectors (criterion_id, alternative_id, mark_id) VALUES (1, 6, 2);
INSERT INTO vectors (criterion_id, alternative_id, mark_id) VALUES (2, 6, 8);
INSERT INTO vectors (criterion_id, alternative_id, mark_id) VALUES (3, 6, 10);


