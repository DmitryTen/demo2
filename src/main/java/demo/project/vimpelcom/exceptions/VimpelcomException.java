package demo.project.vimpelcom.exceptions;

public class VimpelcomException extends RuntimeException {


    private VimpelcomErrorTypes errorType;

    public VimpelcomException(VimpelcomErrorTypes errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public VimpelcomErrorTypes getErrorType() {
        return errorType;
    }
}
