package demo.project.vimpelcom.controllers.models;

public class ErrorResponse extends BasicApiResponse {

    private String errorCode;
    private String description;

    private String errorClass;
    private String errorMessage;

    public ErrorResponse(String errorCode, String description, String errorClass, String errorMessage) {
        this.errorCode = errorCode;
        this.description = description;
        this.errorClass = errorClass;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }

    public String getErrorClass() {
        return errorClass;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
