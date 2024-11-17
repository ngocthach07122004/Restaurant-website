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







create procedure handler_Update_NhanVien (in maNhanVienArg varchar(100), in newSalary decimal(15,2))
begin
      declare oldSalary decimal(15,2) default -1;
      if maNhanVienArg is null or maNhanVienArg =''
      then
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Mã nhân viên không hợp lệ';
      end if;
      select NhanVien.luong into oldSalary
      from NhanVien 
      where NhanVien.cccd = maNhanVienArg;

      if oldSalary = -1
      then 
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Mã nhân viên không tồn tại';
      end if;
     
      if newSalary < oldSalary 
      then 
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Lương mới phải lớn hơn hoặc bằng lương cũ';
      end if;

      update NhanVien 
      set NhanVien.luong = newSalary
      where NhanVien.cccd = maNhanVienArg;
        
      
end;





create procedure handler_Delete_ChiNhanh (
   in maChiNhanhArg varchar(100)
)
begin 
  if maChiNhanhArg is null or maChiNhanhArg = ''
  then 
  SIGNAL SQLSTATE  '45000'
  SET MESSAGE_TEXT ='Mã chi nhánh không hợp lệ';
  end if;

  if exists (select 1 from MonAnThuocVe
  where MonAnThuocVe.maChiNhanh =maChiNhanhArg)
  then 
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Chi nhánh này đang chứa các món ăn';
   elseif exists (select 1 from SuKienUuDai where SuKienUuDai.maChiNhanh = maChiNhanhArg )
    then 
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Chi nhánh này đang tổ chức các sự kiện ưu đãi';
   elseif exists (select 1 from NhanVienQuanLy where NhanVienQuanLy.maChiNhanh = maChiNhanhArg)
   then 
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Chi nhánh này đang có nhân viên quản lý';
   elseif exists (select 1 from DonMonAn where DonMonAn.maChiNhanh =maChiNhanhArg )
   then 
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Chi nhánh này đang xử lý đơn món ăn';
   elseif exists (select 1 from NhanVien where NhanVien.maChiNhanh = maChiNhanhArg) 
   then 
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Chi nhánh này đang có nhân viên làm việc';
   end if;
   delete from ChiNhanh
   where ChiNhanh.maChiNhanh = maChiNhanhArg;
end;
