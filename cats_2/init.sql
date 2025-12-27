CREATE TABLE IF NOT EXISTS profile
(
    id       SERIAL PRIMARY KEY,
    login    TEXT NOT NULL UNIQUE,
    password_hash TEXT NOT NULL,
    salt     TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS cats
(
    id_cat     SERIAL PRIMARY KEY,
    name_cat   TEXT NOT NULL,
    age_cat    REAL,
    id_profile INTEGER NOT NULL,
    CONSTRAINT fk_cat_profile
        FOREIGN KEY (id_profile) REFERENCES profile (id)
);
