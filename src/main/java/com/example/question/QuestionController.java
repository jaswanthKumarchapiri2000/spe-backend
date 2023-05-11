package com.example.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {
   
	 @Autowired
	  private QuestionRepository questionRepository; 
	   
	   
	 //to show all question present in database
	 @GetMapping("/question")
	 @PreAuthorize("hasAnyAuthority('Admin')")
	 public List<Question> getAllQuestion(){
		 System.out.println("+++++ inside getall questions ++++++");
		 return (List<Question>) this.questionRepository.findAll(); 
	 }
	 
	
	 //add a question in a particular exam   
	 @PostMapping("/question")
	 @PreAuthorize("hasAnyAuthority('Admin')")
	 public Question addNewQuestion(@RequestBody Question question ){
		 return this.questionRepository.save(question); 
	 }
	 
	 
	 //to get details of all question of that particular exam   (ofcourse using exam_id)  
	 @GetMapping("/exam/{id}/question")
	 @PreAuthorize("hasAnyAuthority('Admin')")
	 public List<Question> getAllQuestionForExam(@PathVariable("id") int id){
		 return this.questionRepository.findByEnameId(id);
	 }
	 
	 
	 //edit a question in a particular exam
	 @PutMapping("/question/{id}")
	 @PreAuthorize("hasAnyAuthority('Admin')")
	 public Question updateQuestion(@PathVariable("id") int id , @RequestBody Question question) {
	  	   	 question.setId(id);
		  return this.questionRepository.save(question); 
	  }
	 
	 
	 // delete a question in a particular exam
	 @DeleteMapping("/question/{id}")
	 @PreAuthorize("hasAnyAuthority('Admin')")
	 public void deleteQuestion(@PathVariable("id") int id) {
		 this.questionRepository.deleteById(id);
	 }
	 
	 
}
