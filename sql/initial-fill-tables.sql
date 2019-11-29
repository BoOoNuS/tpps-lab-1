INSERT INTO mark_types (name, unit, value_type) VALUES ('Load', 'Count of available hours', 'QUANTITATIVE');
INSERT INTO mark_types (name, unit, value_type) VALUES ('Qualification', 'Qualification level', 'QUALITATIVE');
INSERT INTO mark_types (name, unit, value_type) VALUES ('Reviews', 'Students Reviews', 'QUALITATIVE');

INSERT INTO marks (name, numeric_value, mark_type_id) VALUES ('Bilous Nataliya Valentinivna Load', 232, 1);
INSERT INTO marks (name, numeric_value, mark_type_id) VALUES ('Valenda Natalya Anatolyevna Load', 87, 1);
INSERT INTO marks (name, numeric_value, mark_type_id) VALUES ('Vechur Oleksandr Volodimirovich Load', 116, 1);
INSERT INTO marks (name, numeric_value, mark_type_id) VALUES ('Vlasenko Larisa Andreevna Load', 203, 1);
INSERT INTO marks (name, numeric_value, mark_type_id) VALUES ('Dudar Zoya Volodimirіvna Load', 116, 1);
INSERT INTO marks (name, numeric_value, mark_type_id) VALUES ('Erokhіn Andriy Leonidovich Load', 87, 1);

INSERT INTO marks (name, value, numeric_value, mark_type_id) VALUES ('Bilous Nataliya Valentinivna Professor', 'Professor', 3, 2);
INSERT INTO marks (name, value, numeric_value, mark_type_id) VALUES ('Valenda Natalya Anatolyevna Docent', 'Docent', 2, 2);
INSERT INTO marks (name, value, numeric_value, mark_type_id) VALUES ('Vechur Oleksandr Volodimirovich Docent', 'Docent', 2, 2);
INSERT INTO marks (name, value, numeric_value, mark_type_id) VALUES ('Vlasenko Larisa Andreevna Professor', 'Professor', 3, 2);
INSERT INTO marks (name, value, numeric_value, mark_type_id) VALUES ('Dudar Zoya Volodimirіvna Head of department', 'Head of department', 5, 2);
INSERT INTO marks (name, value, numeric_value, mark_type_id) VALUES ('Erokhіn Andriy Leonidovich Professor 0,5', 'Professor 0,5', 1, 2);

INSERT INTO marks (name, value, numeric_value, mark_type_id) VALUES ('Bilous Nataliya Valentinivna Bad', 'Bad', 1, 3);
INSERT INTO marks (name, value, numeric_value, mark_type_id) VALUES ('Valenda Natalya Anatolyevna Good', 'Good', 3, 3);
INSERT INTO marks (name, value, numeric_value, mark_type_id) VALUES ('Vechur Oleksandr Volodimirovich Normal', 'Normal', 2, 3);
INSERT INTO marks (name, value, numeric_value, mark_type_id) VALUES ('Vlasenko Larisa Andreevna Good', 'Good', 3, 3);
INSERT INTO marks (name, value, numeric_value, mark_type_id) VALUES ('Dudar Zoya Volodimirіvna Normal', 'Normal', 2, 3);
INSERT INTO marks (name, value, numeric_value, mark_type_id) VALUES ('Erokhіn Andriy Leonidovich Normal', 'Normal', 2, 3);

INSERT INTO comparing_types (name) VALUES ('Head of Diploma');

INSERT INTO criteria (name, weight, mark_type_id, mark_id) VALUES ('Load', 1, 1, 1);
INSERT INTO criteria (name, weight, mark_type_id, mark_id) VALUES ('Load', 1, 1, 2);
INSERT INTO criteria (name, weight, mark_type_id, mark_id) VALUES ('Load', 1, 1, 3);
INSERT INTO criteria (name, weight, mark_type_id, mark_id) VALUES ('Load', 1, 1, 4);
INSERT INTO criteria (name, weight, mark_type_id, mark_id) VALUES ('Load', 1, 1, 5);
INSERT INTO criteria (name, weight, mark_type_id, mark_id) VALUES ('Load', 1, 1, 6);

INSERT INTO criteria (name, weight, mark_type_id, mark_id) VALUES ('Qualification', 2, 2, 7);
INSERT INTO criteria (name, weight, mark_type_id, mark_id) VALUES ('Qualification', 2, 2, 8);
INSERT INTO criteria (name, weight, mark_type_id, mark_id) VALUES ('Qualification', 2, 2, 9);
INSERT INTO criteria (name, weight, mark_type_id, mark_id) VALUES ('Qualification', 2, 2, 10);
INSERT INTO criteria (name, weight, mark_type_id, mark_id) VALUES ('Qualification', 2, 2, 11);
INSERT INTO criteria (name, weight, mark_type_id, mark_id) VALUES ('Qualification', 2, 2, 12);

INSERT INTO criteria (name, weight, mark_type_id, mark_id) VALUES ('Reviews', 5, 3, 13);
INSERT INTO criteria (name, weight, mark_type_id, mark_id) VALUES ('Reviews', 5, 3, 14);
INSERT INTO criteria (name, weight, mark_type_id, mark_id) VALUES ('Reviews', 5, 3, 15);
INSERT INTO criteria (name, weight, mark_type_id, mark_id) VALUES ('Reviews', 5, 3, 16);
INSERT INTO criteria (name, weight, mark_type_id, mark_id) VALUES ('Reviews', 5, 3, 17);
INSERT INTO criteria (name, weight, mark_type_id, mark_id) VALUES ('Reviews', 5, 3, 18);

INSERT INTO alternatives (name, comparing_type_id) VALUES ('Bilous Nataliya Valentinivna', 1);
INSERT INTO alternatives (name, comparing_type_id) VALUES ('Valenda Natalya Anatolyevna', 1);
INSERT INTO alternatives (name, comparing_type_id) VALUES ('Vechur Oleksandr Volodimirovich', 1);
INSERT INTO alternatives (name, comparing_type_id) VALUES ('Vlasenko Larisa Andreevna', 1);
INSERT INTO alternatives (name, comparing_type_id) VALUES ('Dudar Zoya Volodimirіvna', 1);
INSERT INTO alternatives (name, comparing_type_id) VALUES ('Erokhіn Andriy Leonidovich', 1);

INSERT INTO vectors (criteria_id, alternative_id) VALUES (1, 1);
INSERT INTO vectors (criteria_id, alternative_id) VALUES (7, 1);
INSERT INTO vectors (criteria_id, alternative_id) VALUES (13, 1);

INSERT INTO vectors (criteria_id, alternative_id) VALUES (2, 2);
INSERT INTO vectors (criteria_id, alternative_id) VALUES (8, 2);
INSERT INTO vectors (criteria_id, alternative_id) VALUES (14, 2);

INSERT INTO vectors (criteria_id, alternative_id) VALUES (3, 3);
INSERT INTO vectors (criteria_id, alternative_id) VALUES (9, 3);
INSERT INTO vectors (criteria_id, alternative_id) VALUES (15, 3);

INSERT INTO vectors (criteria_id, alternative_id) VALUES (4, 4);
INSERT INTO vectors (criteria_id, alternative_id) VALUES (10, 4);
INSERT INTO vectors (criteria_id, alternative_id) VALUES (16, 4);

INSERT INTO vectors (criteria_id, alternative_id) VALUES (5, 5);
INSERT INTO vectors (criteria_id, alternative_id) VALUES (11, 5);
INSERT INTO vectors (criteria_id, alternative_id) VALUES (17, 5);

INSERT INTO vectors (criteria_id, alternative_id) VALUES (6, 6);
INSERT INTO vectors (criteria_id, alternative_id) VALUES (12, 6);
INSERT INTO vectors (criteria_id, alternative_id) VALUES (18, 6);


