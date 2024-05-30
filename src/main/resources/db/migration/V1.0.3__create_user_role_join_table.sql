CREATE TABLE user_role (
  user_id uuid NOT NULL REFERENCES app_user (id),
  role_id INT NOT NULL REFERENCES role (id), 
  PRIMARY KEY (user_id, role_id)
)
