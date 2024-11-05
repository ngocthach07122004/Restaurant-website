package restaurant.example.restaurant.exception;

public enum ErrorCode {
         CHINHANH_NOT_EXIST ("1","Branch is not exist, please try again");
     private ErrorCode (String code , String message ) {
              this.code= code;
              this.message= message;
     }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    private final String code;
        private final String message;
}
