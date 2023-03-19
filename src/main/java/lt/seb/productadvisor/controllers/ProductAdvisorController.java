package lt.seb.productadvisor.controllers;

import lt.seb.productadvisor.models.AnswerRequest;
import lt.seb.productadvisor.services.ProductAdvisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1")
public class ProductAdvisorController {

    @Autowired
    ProductAdvisorService productAdvisorService;

    @GetMapping(value = "/suggestProducts", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity suggestProducts(@RequestBody AnswerRequest answerRequest){
        try{
            return ResponseEntity.ok(productAdvisorService.suggestProducts(answerRequest));
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
