create procedure handler_Insert_Table_ThongTin
(
     in cccd_Arg varchar(100),
in tenDangNhap_Arg varchar(100),
in matKhau_Arg varchar(100),
in ho_Arg varchar(100),
in ten_Arg varchar(100),
in ngaySinh_Arg date,
in email_Arg varchar(100),
in gioiTinh_Arg char(1),
in soDienThoai_Arg varchar(100),
in maTaiKhoan_Arg varchar(100),
in cccdQuanTriVien_Arg varchar(100)
)
begin 
    if cccd_Arg is null or cccd_Arg = ''
    then 
       SIGNAL SQLSTATE '45000'
       SET MESSAGE_TEXT = 'Trường này không được để trống, đây là khóa của dữ liệu'; 
    end if;

    if CHAR_LENGTH(tenDangNhap_Arg) <6 and tenDangNhap_Arg not like ' %'
    then 
       SIGNAL SQLSTATE '45000'
       SET MESSAGE_TEXT = 'Tên đăng nhập phải lớn hơn 5 ký tự'; 
    end if;

    if CHAR_LENGTH(matKhau_Arg) <8 
    then 
       SIGNAL SQLSTATE '45000'
       SET MESSAGE_TEXT = 'Mật khẩu phải lớn hơn hoặc bằng 8 ký tự'; 
    end if;

    
    if !(ngaySinh_Arg REGEXP '^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$')
    then 
       SIGNAL SQLSTATE '45000'
       SET MESSAGE_TEXT = 'Ngày sinh phải hợp lệ'; 
    end if;

    if !REGEXP_LIKE(email_Arg, '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$')
    then 
       SIGNAL SQLSTATE '45000'
       SET MESSAGE_TEXT = 'Email phải hợp lệ và đúng cú pháp định dạng email'; 
    end if;
    
    if soDienThoai_Arg is null 
    then 
       SIGNAL SQLSTATE '45000'
       SET MESSAGE_TEXT = 'Số điện thoại không được bỏ trống'; 
    end if;
    
    insert into ThongTin values( cccd_Arg,
tenDangNhap_Arg,
matKhau_Arg,
ho_Arg,
ten_Arg,
ngaySinh_Arg,
email_Arg,
gioiTinh_Arg,
soDienThoai_Arg,
maTaiKhoan_Arg,
cccdQuanTriVien_Arg); 
    



end; 

