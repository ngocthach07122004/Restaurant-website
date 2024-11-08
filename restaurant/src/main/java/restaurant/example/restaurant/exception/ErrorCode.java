package restaurant.example.restaurant.exception;

public enum ErrorCode {
         CHINHANH_NOT_EXIST ("1","CHINHANH is not exist, please try again"),
         DONKHIEUNAI_NOT_EXIST ("2","DONKHIEUNAI is not exist, please try again"),
         DONMONAN_NOT_EXIST ("3","DONMONAN is not exist, please try again"),
         MAKHUYENMAI_NOT_EXIST ("4","MAKHUYENMAI is not exist, please try again"),
         MONAN_NOT_EXIST ("5","MONAN is not exist, please try again"),
         PHUONGTIEN_NOT_EXIST ("6","PHUONGTIEN is not exist, please try again"),
         SUKIENUUDAI_NOT_EXIST ("7","SUKIENUUDAI is not exist, please try again"),
         THONGBAO_NOT_EXIST ("8","THONGBAO is not exist, please try again"),
         THONGTIN_NOT_EXIST ("9","THONGTIN is not exist, please try again");
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
