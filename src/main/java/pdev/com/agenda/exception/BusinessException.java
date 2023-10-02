package pdev.com.agenda.exception;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BusinessException {
    String message;
    public BusinessException(String message) {
        this.message = message;
    }
}
