CREATE TABLE tb_study_content (
  id UUID PRIMARY KEY,
  topic_name VARCHAR(255) NOT NULL,
  summary_markdown TEXT NOT NULL,
  key_points JSONB,
  created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  user_id UUID,
  CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES tb_user(id)
);