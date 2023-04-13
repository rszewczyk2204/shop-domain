CREATE TABLE item_linked_item
(
    item_id        UUID NOT NULL,
    linked_item_id UUID NOT NULL
);

ALTER TABLE item_linked_item
    ADD CONSTRAINT "FK_linked_item__item"
        FOREIGN KEY (linked_item_id) REFERENCES item
            ON UPDATE CASCADE ON DELETE CASCADE;