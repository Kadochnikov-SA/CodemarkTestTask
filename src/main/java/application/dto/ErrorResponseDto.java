package application.dto;


import java.util.List;

/**
 *
 * This public class describes server response object if
 * the operation was performed with errors
 *
 * @author Sergey Kadochnikov
 */

public class ErrorResponseDto extends ResponseDto {

    private List<String> errors;

    public ErrorResponseDto(boolean success, List<String> errors) {
        super(success);
        this.errors = errors;
    }

    public ErrorResponseDto(boolean success) {
        super(success);
    }

    public ErrorResponseDto(List<String> errors) {
        this.errors = errors;
    }


    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }


}

