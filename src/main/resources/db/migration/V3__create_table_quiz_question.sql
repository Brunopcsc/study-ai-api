CREATE TABLE tb_quiz_question (
  id UUID PRIMARY KEY,
  content_id UUID NOT NULL,
  question_text TEXT NOT NULL,
  options JSONB NOT NULL,
  correct_answer_index INTEGER NOT NULL,
  explanation TEXT,
  CONSTRAINT fk_study_content FOREIGN KEY (content_id) REFERENCES tb_study_content(id) ON DELETE CASCADE
);