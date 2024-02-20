package sample.quiz.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sample.quiz.app.models.QuestionWrapper;
import sample.quiz.app.models.Response;
import sample.quiz.app.service.QuizService;

import java.util.List;

@RestController
@RequestMapping("quiz")
@RequiredArgsConstructor
public class QuizController {


    private final QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int noQ, @RequestParam String title ){
        return quizService.createQuiz(category, noQ, title);
    }

    @GetMapping("fetch_quiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizzes(@PathVariable long id){
        return quizService.fetchQuiz(id);
    }

    @PostMapping("submit/{quizId}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Long quizId, @RequestBody List<Response> responses){
        return quizService.submitQuiz(quizId, responses);
    }
}
