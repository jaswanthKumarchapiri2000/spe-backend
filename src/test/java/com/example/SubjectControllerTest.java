package com.example;

import com.example.subject.Subject;
import com.example.subject.SubjectController;
import com.example.subject.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubjectControllerTest {

    @InjectMocks
    private SubjectController subjectController;

    @Mock
    private SubjectRepository subjectRepository;

    private List<Subject> subjectList;

    @BeforeEach
    void setUp() throws Exception {
        subjectList = new ArrayList<Subject>();

        Subject subject1 = new Subject();
        subject1.setName("Maths");

        Subject subject2 = new Subject();
        subject2.setName("Science");

        subjectList.add(subject1);
        subjectList.add(subject2);
    }

    @Test
    void testGetAllSubjects() {
        when(subjectRepository.findAll()).thenReturn(subjectList);
        List<Subject> result = subjectController.getAllSubjects();
        assertEquals(2, result.size());
    }

    @Test
    void testAddNewSubject() {
        Subject newSubject = new Subject();
        newSubject.setName("History");

        when(subjectRepository.save(any(Subject.class))).thenReturn(newSubject);
        Subject result = subjectController.addNewSubject(newSubject);
        assertEquals("History", result.getName());
    }

    @Test
    void testDeleteSubject() {
        when(subjectRepository.deleteByName("Maths")).thenReturn(1);
        String result = subjectController.deleteSubject("Maths");
        assertEquals("1 row get deleted", result);
    }
}
