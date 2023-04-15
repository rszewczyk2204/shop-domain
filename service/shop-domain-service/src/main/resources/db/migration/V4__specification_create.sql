ALTER TABLE item
    DROP COLUMN specification;

CREATE TABLE specification
(
    id UUID NOT NULL PRIMARY KEY,
    name TEXT NOT NULL,
    value TEXT NOT NULL,
    item_id UUID NOT NULL,
    version INTEGER NOT NULL
);

ALTER TABLE specification
    ADD CONSTRAINT "FK_specification__item"
        FOREIGN KEY (item_id) REFERENCES item
            ON UPDATE CASCADE ON DELETE CASCADE;