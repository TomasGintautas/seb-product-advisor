package lt.seb.productadvisor.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerRequest {

    @JsonProperty("incomeRange")
    private String incomeRange;

    @JsonProperty("ageRange")
    private String ageRange;

    @JsonProperty("isStudent")
    private Boolean isStudent;

    @Override
    public String toString() {
        return "AnswerRequest(" +
                "incomeRange=" + incomeRange +
                ", ageRange=" + ageRange +
                ", isStudent=" + isStudent + ')';
    }
}
