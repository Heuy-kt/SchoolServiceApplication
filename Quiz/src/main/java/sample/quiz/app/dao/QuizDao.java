package sample.quiz.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.quiz.app.models.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Long> {

}
