package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.question.Question;
import com.example.question.QuestionController;
import com.example.question.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.Exam.Exam;
import com.example.Exam.ExamRepository;
import com.example.subject.Subject;
import com.example.subject.SubjectRepository;

public class QuestionControllerTest {

    @InjectMocks
    private QuestionController questionController;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private ExamRepository examRepository;

    @Mock
    private SubjectRepository subjectRepository;

    private List<Question> questionList;
    private Exam exam;
    private Subject subject;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        questionList = new ArrayList<>();

        Question question1 = new Question();
        question1.setId(1);
        question1.setQname("What is the capital of France?");
        question1.setOptionOne("Paris");
        question1.setOptionTwo("Madrid");
        question1.setOptionThree("Berlin");
        question1.setOptionFour("London");
        question1.setAnswer("Paris");

        Question question2 = new Question();
        question2.setId(2);
        question2.setQname("Who painted the Mona Lisa?");
        question2.setOptionOne("Vincent van Gogh");
        question2.setOptionTwo("Pablo Picasso");
        question2.setOptionThree("Leonardo da Vinci");
        question2.setOptionFour("Rembrandt van Rijn");
        question2.setAnswer("Leonardo da Vinci");

        questionList.add(question1);
        questionList.add(question2);

        subject = new Subject();

        subject.setName("History");

        exam = new Exam();
        exam.setId(1);
        exam.setName(subject);
        exam.setDesc("World History Exam");
        exam.setDate("2023-05-20");
        exam.setMarks("100");
        exam.setTotalQuestion("10");
        exam.setPassMarks("50");
        exam.setLevel("Easy");
        exam.setTime("60 min");
    }

    @Test
    void testGetAllQuestions() {
        when(questionRepository.findAll()).thenReturn(questionList);
        List<Question> result = questionController.getAllQuestion();
        assertEquals(2, result.size());
    }

    @Test
    void testAddNewQuestion() {
        when(questionRepository.save(any(Question.class))).thenReturn(questionList.get(0));
        Question newQuestion = new Question();
        newQuestion.setQname("What is the capital of Germany?");
        newQuestion.setOptionOne("Paris");
        newQuestion.setOptionTwo("Madrid");
        newQuestion.setOptionThree("Berlin");
        newQuestion.setOptionFour("London");
        newQuestion.setAnswer("Berlin");
        Question result = questionController.addNewQuestion(newQuestion);
        assertEquals(questionList.get(0).getId(), result.getId());
        assertEquals(newQuestion.getQname(), result.getQname());
    }


    @Test
    void testUpdateQuestion() {
        when(questionRepository.save(any(Question.class))).thenReturn(questionList.get(0));
        Question updatedQuestion = questionList.get(0);
        updatedQuestion.setQname("What is the capital of Germany?");
        Question result = questionController.updateQuestion(1, updatedQuestion);
        assertEquals(questionList.get(0).getId(), result.getId());
        assertEquals(updatedQuestion.getQname(), result.getQname());
    }

    @Test
    void testDeleteQuestion() {
        doNothing().when(questionRepository).deleteById(anyInt());
        questionController.deleteQuestion(1);
        verify(questionRepository, times(1)).deleteById(1);
    }




}
