package lt.seb.productadvisor.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AgeUtilTest {

    private AgeUtil ageUtil;

    @BeforeEach
    public void setUp() {
        ageUtil = new AgeUtil();
    }

    @Test
    void testIsMinorWhenAgeRangeIsMinor() {
        assertTrue(ageUtil.isMinor("minor"));
    }

    @Test
    void testIsMinorWhenAgeRangeIsNotMinor() {
        assertFalse(ageUtil.isMinor("adult"));
    }

    @Test
    void testIsSeniorWhenAgeRangeIsSenior() {
        assertTrue(ageUtil.isSenior("senior"));
    }
}
