package sample.quiz.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sample.quiz.app.dao.QuestionDao;
import sample.quiz.app.dao.QuizDao;
import sample.quiz.app.models.Question;
import sample.quiz.app.models.QuestionWrapper;
import sample.quiz.app.models.Quiz;
import sample.quiz.app.models.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizDao quizDao;
    private final QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int noQ, String title){
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, noQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizDao.save(quiz);
        if(quizDao.findById(quiz.getId()) != null){
            return new ResponseEntity<>("Created", HttpStatus.OK);
        }
        return new ResponseEntity<>("cant be found", HttpStatus.EXPECTATION_FAILED);
    }

    public ResponseEntity<List<QuestionWrapper>> fetchQuiz(long id) {
        try {
            Optional<Quiz> quiz = quizDao.findById(id);
            List<Question> questionsFromDB = quiz.get().getQuestions();
            List<QuestionWrapper> questionsForStudents = new ArrayList<>();

            questionsFromDB.forEach(q -> {
                QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestion(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
                questionsForStudents.add(qw);
            });

            return new ResponseEntity<>(questionsForStudents, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Integer> submitQuiz(Long id, List<Response> response){
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> quizQuestions = quiz.get().getQuestions();
        int score = 0;
        int i = 0;
        for (Response r : response){
            if(r.getResponse().equals(quizQuestions.get(i).getRightOption())){
                score++;
            }
            i++;
        };

        return new ResponseEntity<>(score, HttpStatus.OK);
    }
}
