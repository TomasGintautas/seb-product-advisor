package lt.seb.productadvisor.services;

import lt.seb.productadvisor.exceptions.BadDataException;
import lt.seb.productadvisor.models.AnswerRequest;
import lt.seb.productadvisor.models.Product;
import lt.seb.productadvisor.utils.AgeUtil;
import lt.seb.productadvisor.utils.IncomeUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductAdvisorService {

    private final AgeUtil ageUtil;
    private final IncomeUtil incomeUtil;

    public ProductAdvisorService(AgeUtil ageUtil, IncomeUtil incomeUtil) {
        this.ageUtil = ageUtil;
        this.incomeUtil = incomeUtil;
    }

    public List<Product> suggestProducts(AnswerRequest answers) {
        List<Product> suggestedProducts = new ArrayList<>();
        try {
            if (ageUtil.isMinor(answers.getAgeRange())) {
                suggestedProducts.add(Product.JUNIOR_SAVER_ACCOUNT);
            } else {
                if (!incomeUtil.isZeroIncome(answers.getIncomeRange())) {
                    suggestedProducts.add(Product.CURRENT_ACCOUNT);
                }
                if (incomeUtil.isHighIncome(answers.getIncomeRange())) {
                    suggestedProducts.add(Product.CURRENT_ACCOUNT_PLUS);
                    suggestedProducts.add(Product.GOLD_CREDIT_CARD);
                }
                if (Boolean.TRUE.equals(answers.getIsStudent())) {
                    suggestedProducts.add(Product.STUDENT_ACCOUNT);
                }
                if (ageUtil.isSenior(answers.getAgeRange())) {
                    suggestedProducts.add(Product.SENIOR_ACCOUNT);
                }
                if (incomeUtil.isZeroIncome(answers.getIncomeRange()) || incomeUtil.isLowIncome(answers.getIncomeRange())) {
                    suggestedProducts.add(Product.DEBIT_CARD);
                } else {
                    suggestedProducts.add(Product.CREDIT_CARD);
                }
            }
            return suggestedProducts;
        } catch (NullPointerException e) {
            throw new BadDataException("Request data is wrong: " + answers.toString());
        }
    }
}
