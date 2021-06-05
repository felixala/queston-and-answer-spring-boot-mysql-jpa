package com.felixlaura.qaa.controller;

import com.felixlaura.qaa.exception.ResourceNotFoundException;
import com.felixlaura.qaa.model.Answer;
import com.felixlaura.qaa.repository.AnswerRepository;
import com.felixlaura.qaa.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
public class AnswerController {

    private AnswerRepository answerRepository;
    private QuestionRepository questionRepository;

    @GetMapping("/questions/{questionId}/answers")
    public List<Answer> getAnswersByQuestion(@PathVariable Long questionId){
        return answerRepository.findByQuestionId(questionId);
    }

    @PostMapping("/questions/{questionId}/answers")
    public Answer addAnswer(@PathVariable Long questionId, @Valid @RequestBody Answer answer) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    answer.setQuestion(question);
                    return answerRepository.save(answer);
                }).orElseThrow(()->new ResourceNotFoundException("Question not found with id "+ questionId));
    }

    @PutMapping("/questions/{questionId}/answers/{answerId}")
    public Answer updateAnswer(@PathVariable Long questionId, @PathVariable Long answerId, @Valid @RequestBody Answer answerRequested){
        if(!questionRepository.existsById(questionId)) {
            throw new ResourceNotFoundException("Question not found with id " + questionId);
        }
        return answerRepository.findById(answerId).
            map(answer -> {
                answer.setText(answerRequested.getText());
                return answerRepository.save(answer);
            }).orElseThrow(()-> new ResourceNotFoundException("Answer not found with id " + answerId));
        }

    @DeleteMapping("/questions/{questionId}/answers/{answerId}")
    public ResponseEntity<?> deleteAnswer(@PathVariable Long questionId, @PathVariable Long answerId){
        if(!questionRepository.existsById(questionId)){
            throw new ResourceNotFoundException("Question not found with id " + questionId);
        }

        return answerRepository.findById(answerId).
                map(answer->{
                    answerRepository.delete(answer);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new ResourceNotFoundException("Answer not found with id " + answerId));

    }

}
