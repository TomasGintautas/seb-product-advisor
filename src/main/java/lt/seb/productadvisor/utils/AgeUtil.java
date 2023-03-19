package lt.seb.productadvisor.utils;

import org.springframework.stereotype.Component;

@Component
public class AgeUtil {

    private static final String MINOR = "minor";
    private static final String SENIOR = "senior";

    public boolean isMinor(String ageRange){
        return ageRange.equals(MINOR);
    }

    public boolean isSenior(String ageRange){
        return ageRange.equals(SENIOR);
    }
}
