package sample.quiz.app.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sample.quiz.app.models.Question;
import sample.quiz.app.service.QuestionService;

import java.util.List;
import java.util.Optional;

@RestController()
@RequiredArgsConstructor
@RequestMapping("question")
public class QuestionController {


    private final QuestionService questionService;

    @GetMapping("allquestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Question>> getAquestion(@PathVariable long id){
        return questionService.getAquestion(id);
    }


    //name in curly bracket must be the same or else specify name in pathvariable arguments
    // requestparams is more secure as it doesn't appear in the URI
    @GetMapping("category/{cat}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable(name = "cat") String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable  Integer id){
        return questionService.deleteQuestion(id);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateAquestion(@PathVariable Integer id, @RequestBody Question question){
        return questionService.updateAquestion(id, question);
    }

}
