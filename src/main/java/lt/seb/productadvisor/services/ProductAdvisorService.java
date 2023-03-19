package lt.seb.productadvisor.services;

import io.micrometer.common.util.StringUtils;
import lt.seb.productadvisor.exceptions.BadDataException;
import lt.seb.productadvisor.models.AnswerRequest;
import lt.seb.productadvisor.models.Product;
import lt.seb.productadvisor.utils.AgeUtil;
import lt.seb.productadvisor.utils.IncomeUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductAdvisorService {

    private final AgeUtil ageUtil;
    private final IncomeUtil incomeUtil;

    public ProductAdvisorService(AgeUtil ageUtil, IncomeUtil incomeUtil) {
        this.ageUtil = ageUtil;
        this.incomeUtil = incomeUtil;
    }

    public List<String> suggestProducts(AnswerRequest answers) {
        List<String> suggestedProducts = new ArrayList<>();
        try {
            if (StringUtils.isBlank(answers.getIncomeRange()) || StringUtils.isBlank(answers.getAgeRange())) {
                throw new BadDataException("Missing incomeRange or ageRange data: " + answers.toString());
            }
            if (ageUtil.isMinor(answers.getAgeRange())) {
                suggestedProducts.add(Product.JUNIOR_SAVER_ACCOUNT.toString());
            } else {
                if (!incomeUtil.isZeroIncome(answers.getIncomeRange())) {
                    suggestedProducts.add(Product.CURRENT_ACCOUNT.toString());
                }
                if (incomeUtil.isHighIncome(answers.getIncomeRange())) {
                    suggestedProducts.add(Product.CURRENT_ACCOUNT_PLUS.toString());
                    suggestedProducts.add(Product.GOLD_CREDIT_CARD.toString());
                }
                if (Boolean.TRUE.equals(answers.getIsStudent())) {
                    suggestedProducts.add(Product.STUDENT_ACCOUNT.toString());
                }
                if (ageUtil.isSenior(answers.getAgeRange())) {
                    suggestedProducts.add(Product.SENIOR_ACCOUNT.toString());
                }
                if (incomeUtil.isZeroIncome(answers.getIncomeRange()) || incomeUtil.isLowIncome(answers.getIncomeRange())) {
                    suggestedProducts.add(Product.DEBIT_CARD.toString());
                } else {
                    suggestedProducts.add(Product.CREDIT_CARD.toString());
                }
            }
            return suggestedProducts;
        } catch (NullPointerException e) {
            throw new BadDataException("Request data is wrong: " + answers.toString());
        }
    }
}
