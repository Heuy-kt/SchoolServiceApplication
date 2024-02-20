package sample.quiz.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sample.quiz.app.models.Question;

import java.util.List;


@Repository
public interface QuestionDao extends JpaRepository<Question, Long> {

    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM questions q where q.category=:category ORDER BY RANDOM() LIMIT :noQ", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int noQ);
}
