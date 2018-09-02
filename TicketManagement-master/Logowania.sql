DROP TABLE IF EXISTS Last_logins CASCADE ;

CREATE TABLE Last_logins(
	ID NUMERIC,
	Last_logins VARCHAR(250)
);

INSERT INTO Last_logins VALUES
(0, '2018-1-10'),
(1, '2018-2-10');

UPDATE Last_logins
SET Last_logins = to_char(current_timestamp, 'YYYY-MM-DD HH:MM:SS') || Last_logins
WHERE ID = 0;

UPDATE Last_logins
SET Last_logins = substring(Last_logins, 0, 19)
WHERE ID = 0;
