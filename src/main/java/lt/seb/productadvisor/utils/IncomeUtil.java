package lt.seb.productadvisor.utils;

import org.springframework.stereotype.Component;

@Component
public class IncomeUtil {

    private static final String ZERO_INCOME = "zeroIncome";
    private static final String LOW_INCOME = "lowIncome";
    private static final String HIGH_INCOME = "highIncome";

    public boolean isHighIncome(String incomeRange){
        return incomeRange.equals(HIGH_INCOME);
    }

    public boolean isZeroIncome(String incomeRange){
        return incomeRange.equals(ZERO_INCOME);
    }

    public boolean isLowIncome(String incomeRange){
        return incomeRange.equals(LOW_INCOME);
    }
}
