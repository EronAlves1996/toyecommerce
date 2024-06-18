ALTER TABLE product 
  ADD COLUMN image_name VARCHAR(256),
  ADD COLUMN created_at TIMESTAMP,
  ADD COLUMN updated_at TIMESTAMP,
  ADD COLUMN deleted boolean default false,
  DROP CONSTRAINT product_name_key;

CREATE UNIQUE INDEX product_name_key 
  ON product(name)
  WHERE deleted = false;
