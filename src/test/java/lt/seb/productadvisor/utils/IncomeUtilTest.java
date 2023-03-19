package lt.seb.productadvisor.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class IncomeUtilTest {

    private IncomeUtil incomeUtil;

    @BeforeEach
    public void setUp() {
        incomeUtil = new IncomeUtil();
    }

    @Test
    void testIsZeroIncomeWhenIncomeRangeIsZeroIncome() {
        assertTrue(incomeUtil.isZeroIncome("zeroIncome"));
    }

    @Test
    void testIsLowIncomeWhenIncomeRangeIsLowIncome() {
        assertTrue(incomeUtil.isLowIncome("lowIncome"));
    }

    @Test
    void testIsHighIncomeWhenIncomeRangeIsHighIncome() {
        assertTrue(incomeUtil.isHighIncome("highIncome"));
    }
}
