INSERT INTO security.users (name, password)
SELECT *
FROM (SELECT 'gregory', '1') AS tmp
WHERE NOT EXISTS(
        SELECT name FROM security.users WHERE name = 'gregory' AND password = '1'
    ) LIMIT 1;

INSERT INTO security.users (name, password)
SELECT *
FROM (SELECT 'manager', '1') AS tmp
WHERE NOT EXISTS(
        SELECT name FROM security.users WHERE name = 'manager' AND password = '1'
    ) LIMIT 1;

INSERT INTO security.roles (role)
SELECT *
FROM (SELECT 'guest') AS tmp
WHERE NOT EXISTS(
        SELECT role FROM security.roles WHERE role = 'guest'
    ) LIMIT 1;

INSERT INTO security.roles (role)
SELECT *
FROM (SELECT 'manager') AS tmp
WHERE NOT EXISTS(
        SELECT role FROM security.roles WHERE role = 'manager'
    ) LIMIT 1;

INSERT INTO security.user_roles (user_id, role_id)
SELECT *
FROM (SELECT 1, 1) AS tmp
WHERE NOT EXISTS(
        SELECT user_id FROM security.user_roles WHERE user_id = 1 AND role_id = 1
    ) LIMIT 1;

INSERT INTO security.user_roles (user_id, role_id)
SELECT *
FROM (SELECT 2, 1) AS tmp
WHERE NOT EXISTS(
        SELECT user_id FROM security.user_roles WHERE user_id = 2 AND role_id = 1
    ) LIMIT 1;

INSERT INTO security.user_roles (user_id, role_id)
SELECT *
FROM (SELECT 2, 2) AS tmp
WHERE NOT EXISTS(
        SELECT user_id FROM security.user_roles WHERE user_id = 2 AND role_id = 2
    ) LIMIT 1;