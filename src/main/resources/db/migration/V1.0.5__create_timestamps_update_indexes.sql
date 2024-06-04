ALTER TABLE app_user DROP CONSTRAINT app_user_name_key,
  DROP CONSTRAINT app_user_email_key,
  ADD COLUMN created_at TIMESTAMP,
  ADD COLUMN updated_at TIMESTAMP,
  ADD COLUMN deleted_at TIMESTAMP;

CREATE UNIQUE INDEX app_user_name_key 
  ON app_user (name)
  WHERE deleted_at IS NOT NULL;
CREATE UNIQUE INDEX app_user_email_key 
  ON app_user (email)
  WHERE deleted_at IS NOT NULL;
