ALTER TABLE app_user DROP COLUMN deleted_at,
  ADD COLUMN deleted boolean default false;

CREATE UNIQUE INDEX app_user_name_key
  ON app_user (name)
  WHERE deleted = false;

CREATE UNIQUE INDEX app_user_email_key 
  ON app_user (email)
  WHERE deleted = false;
