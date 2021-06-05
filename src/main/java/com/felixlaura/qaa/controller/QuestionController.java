package com.felixlaura.qaa.controller;

import com.felixlaura.qaa.exception.ResourceNotFoundException;
import com.felixlaura.qaa.model.Question;
import com.felixlaura.qaa.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
public class QuestionController {

    private QuestionRepository questionRepository;

    @GetMapping("/questions")
    public Page<Question> getQuestions(Pageable pageable){
        return questionRepository.findAll(pageable);
    }

    @PostMapping("/questions")
    public Question createQuestion(@Valid @RequestBody Question question){
        return questionRepository.save(question);
    }

    @PutMapping("/questions/{questionId}")
    public Question updateQuestion(@PathVariable Long questionId, @Valid @RequestBody Question questionRequested){
        return questionRepository.findById(questionId)
            .map(question -> {
                question.setTitle(questionRequested.getTitle());
                question.setDescription(questionRequested.getDescription());
                return questionRepository.save(question);
            }).orElseThrow(()->new ResourceNotFoundException("Question no found with id " + questionId));
    }

    @DeleteMapping("/questions/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId){
        return questionRepository.findById(questionId)
                .map(question -> {
                    questionRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()->new ResourceNotFoundException("Question not found with id " + questionId));
    }
}
