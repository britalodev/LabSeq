package pt.italo.labseq.advice;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorBean {
    private String error;
    private String message;

    public ErrorBean() {
    }

    public ErrorBean(String error, String message) {
        this.error = error;
        this.message = message;
    }


    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}