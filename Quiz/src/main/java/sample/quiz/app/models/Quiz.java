package sample.quiz.app.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "quizzes")
@Data
public class Quiz {

    public Quiz() {
    }

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            name = "titles"
    )
    private String title;

    @ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    @Column(name = "questions")
    private List<Question> questions;

}
