package lt.seb.productadvisor.services;

import lt.seb.productadvisor.exceptions.BadDataException;
import lt.seb.productadvisor.models.AnswerRequest;
import lt.seb.productadvisor.models.Product;
import lt.seb.productadvisor.utils.AgeUtil;
import lt.seb.productadvisor.utils.IncomeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductAdvisorServiceTest {

    private ProductAdvisorService productAdvisorService;

    @BeforeEach
    void setUp(){
        AgeUtil ageUtil = new AgeUtil();
        IncomeUtil incomeUtil = new IncomeUtil();
        productAdvisorService = new ProductAdvisorService(ageUtil, incomeUtil);
    }

    @Test
    void testSuggestProductsForMinorWithNoIncome() {
        AnswerRequest answerRequest = new AnswerRequest("zeroIncome", "minor", false);
        List<String> suggestedProducts = productAdvisorService.suggestProducts(answerRequest);
        assertEquals(1, suggestedProducts.size());
        assertTrue(suggestedProducts.contains(Product.JUNIOR_SAVER_ACCOUNT.toString()));
    }

    @Test
    void testSuggestProductsForAdultWithZeroIncome() {
        AnswerRequest answerRequest = new AnswerRequest("zeroIncome", "adult", false);
        List<String> suggestedProducts = productAdvisorService.suggestProducts(answerRequest);
        assertEquals(1, suggestedProducts.size());
        assertTrue(suggestedProducts.contains(Product.DEBIT_CARD.toString()));
    }

    @Test
    void testSuggestProductsForAdultWithHighIncome() {
        AnswerRequest answerRequest = new AnswerRequest("highIncome", "adult", false);
        List<String> suggestedProducts = productAdvisorService.suggestProducts(answerRequest);
        assertEquals(4, suggestedProducts.size());
        assertTrue(suggestedProducts.contains(Product.CURRENT_ACCOUNT.toString()));
        assertTrue(suggestedProducts.contains(Product.CURRENT_ACCOUNT_PLUS.toString()));
        assertTrue(suggestedProducts.contains(Product.GOLD_CREDIT_CARD.toString()));
        assertTrue(suggestedProducts.contains(Product.CREDIT_CARD.toString()));
    }

    @Test
    void testSuggestProductsForStudentWithNoIncome() {
        AnswerRequest answerRequest = new AnswerRequest("zeroIncome", "adult", true);
        List<String> suggestedProducts = productAdvisorService.suggestProducts(answerRequest);
        assertEquals(2, suggestedProducts.size());
        assertTrue(suggestedProducts.contains(Product.STUDENT_ACCOUNT.toString()));
        assertTrue(suggestedProducts.contains(Product.DEBIT_CARD.toString()));
    }

    @Test
    void testSuggestProductsForNullIncomeRange() {
        AnswerRequest answerRequest = new AnswerRequest(null, "senior", false);
        BadDataException exception = assertThrows(BadDataException.class, () -> productAdvisorService.suggestProducts(answerRequest));
        assertEquals("Missing incomeRange or ageRange data: AnswerRequest(incomeRange=null, ageRange=senior, isStudent=false)", exception.getMessage());
    }

}

