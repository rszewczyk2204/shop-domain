CREATE TABLE item
(
    id              UUID PRIMARY KEY NOT NULL,
    author_id       UUID NOT NULL,
    author_type     TEXT DEFAULT 'USER'::TEXT NOT NULL,
    modifier_id     UUID NOT NULL,
    modifier_type   TEXT DEFAULT 'USER'::TEXT,
    name            TEXT NOT NULL,
    DESCRIPTION     TEXT,
    QUANTITY        INT NOT NULL DEFAULT 0,
    DELETED         BOOLEAN NOT NULL DEFAULT FALSE,
    VERSION         INT NOT NULL
);