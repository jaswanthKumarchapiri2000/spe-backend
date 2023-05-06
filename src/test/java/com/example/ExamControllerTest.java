package com.example;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.Exam.Exam;
import com.example.Exam.ExamController;
import com.example.Exam.ExamRepository;
import com.example.subject.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ExamControllerTest {

    @InjectMocks
    private ExamController examController;

    @Mock
    private ExamRepository examRepository;

    private List<Exam> examList;

    @BeforeEach
    void setUp() throws Exception {
        examList = new ArrayList<Exam>();

        Exam exam1 = new Exam();
        exam1.setId(1);
        exam1.setName(new Subject());
        exam1.setDesc("Desc1");
        exam1.setDate("2022-06-30");
        exam1.setMarks("100");
        exam1.setTotalQuestion("10");
        exam1.setPassMarks("50");
        exam1.setLevel("Easy");
        exam1.setTime("60");

        Exam exam2 = new Exam();
        exam2.setId(2);
        exam2.setName(new Subject());
        exam2.setDesc("Desc2");
        exam2.setDate("2022-07-30");
        exam2.setMarks("100");
        exam2.setTotalQuestion("10");
        exam2.setPassMarks("50");
        exam2.setLevel("Easy");
        exam2.setTime("60");

        examList.add(exam1);
        examList.add(exam2);
    }

    @Test
    void testGetAllExam() {
        when(examRepository.findAll()).thenReturn(examList);
        List<Exam> result = examController.getAllExam();
        assertEquals(2, result.size());
    }

    @Test
    void testGetParticularExam() {
        when(examRepository.findById(1)).thenReturn(Optional.of(examList.get(0)));
        Exam result = examController.getParticularExam(1);
        assertEquals("Desc1", result.getDesc());
    }

    @Test
    void testAddNewExam() {
        Exam newExam = new Exam();
        newExam.setId(3);
        newExam.setName(new Subject());
        newExam.setDesc("Desc3");
        newExam.setDate("2022-08-30");
        newExam.setMarks("100");
        newExam.setTotalQuestion("10");
        newExam.setPassMarks("50");
        newExam.setLevel("Easy");
        newExam.setTime("60");

        when(examRepository.save(any(Exam.class))).thenReturn(newExam);
        Exam result = examController.addNewExam(newExam);
        assertEquals(3, result.getId());
    }

    @Test
    void testDeleteExam() {
        doNothing().when(examRepository).deleteById(1);
        examController.deleteExam(1);
        verify(examRepository, times(1)).deleteById(1);

    }
}

