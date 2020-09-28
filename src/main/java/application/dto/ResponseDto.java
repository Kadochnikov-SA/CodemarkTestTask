package application.dto;


/**
 *
 * This public class describes server response object if
 * the operation was performed without errors and returns
 * status success: true or false
 *
 * @author Sergey Kadochnikov
 */

public class ResponseDto {

    protected boolean success;

    public ResponseDto(boolean success) {
        this.success = success;
    }

    public ResponseDto() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
