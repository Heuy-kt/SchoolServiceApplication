package sample.quiz.app.service;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sample.quiz.app.dao.QuestionDao;
import sample.quiz.app.models.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Data
public class QuestionService {


    private final QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions(){
        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
    }

    public ResponseEntity<String> addQuestion(Question question ) {
        questionDao.save(question);
        return new ResponseEntity<>("%s saved".formatted(question.getQuestion()), HttpStatus.CREATED);
    }

    public ResponseEntity<Optional<Question>> getAquestion(long id) {
        try{
            return new ResponseEntity<>(questionDao.findById(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(questionDao.findById(1l), HttpStatus.OK);
        }
    }

    public ResponseEntity<String> deleteQuestion(long id) {
        questionDao.deleteById(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    public ResponseEntity<String> updateAquestion(long id, Question question) {
        if (questionDao.existsById(id)) {
            questionDao.save(question);
            return new ResponseEntity<>("updated", HttpStatus.OK);
        } else {
            questionDao.save(question);
            return new ResponseEntity<>("Saved", HttpStatus.OK);
        }
    }
}
