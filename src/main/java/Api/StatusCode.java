package Api;


public enum StatusCode {
    OK(200),
    BAD_REQUEST(400),
    METHOD_NOT_ALLOWED(405);

    private int code;

    StatusCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
