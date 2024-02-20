package sample.quiz.app.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "questions")
public class Question implements Serializable {

    @Id
    @SequenceGenerator(
            name = "question_id",
            sequenceName = "question_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "question_sequence"
    )
    @Column(
            name = "id",
            nullable = false,
            updatable = false
    )
    private Long id;
    @Column(
            name = "question",
            updatable = true,
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String question;
    private String category;
    private String Option1;
    private String Option2;
    private String Option3;
    private String Option4;
    @Column(
            name = "right_option"
    )
    private String rightOption;
    private String difficultyLevel;

    public Question() {
    }
}
