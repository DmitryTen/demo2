package demo.project.vimpelcom.exceptions;

public enum VimpelcomErrorTypes {


    UNKNOWN_ERROR("SYS_001", "Unhandled exception"),

    BUSYNESS_ERROR("BSNS_001", "Just an example of some business error"),

    REMOTE_SYSTEM_ERROR("RMT_001", "Remote system failed"),

    ;

    private String errorCode;
    private String descr;

    VimpelcomErrorTypes(String errorCode, String descr) {
        this.errorCode = errorCode;
    }


    public String getErrorCode() {
        return errorCode;
    }


    public String getDescr() {
        return descr;
    }
}
