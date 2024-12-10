create procedure handler_Insert_Table_ThongTin
(
   in cccd_Arg varchar(255),
   in anhThongTin_Arg varchar(255),
   in email_Arg varchar(255),
   in gioiTinh_Arg char(1),
   in ho_Arg varchar(255),
   in maTaiKhoan_Arg varchar(255),
   in matKhau_Arg varchar(255),
   in ngaySinh_Arg date,
   in ten_Arg varchar(255),
   in tenDangNhap_Arg varchar(255),
   in cccdQuanTriVien_Arg varchar(255)
)
begin
    if cccd_Arg is null or cccd_Arg = ''
    then 
       SIGNAL SQLSTATE '45000'
       SET MESSAGE_TEXT = 'Trường này không được để trống, đây là khóa của dữ liệu'; 
    end if;

     if exists ( select 1 from ThongTin where cccd_Arg = ThongTin.cccd  ) 
    then 
       SIGNAL SQLSTATE '45000'
       SET MESSAGE_TEXT = 'Căn cước đã tồn tại trong hệ thống, vui lòng kiểm tra lại số căn cước'; 
    end if;

    if CHAR_LENGTH(tenDangNhap_Arg) <6 and tenDangNhap_Arg not like ' %'
    then 
       SIGNAL SQLSTATE '45000'
       SET MESSAGE_TEXT = 'Tên đăng nhập phải lớn hơn 5 ký tự'; 
    end if;
    if exists ( select 1 from ThongTin where tenDangNhap_Arg like tenDangNhap ) 
    then
       SIGNAL SQLSTATE '45000'
       SET MESSAGE_TEXT = 'Tên đăng nhập đã tồn tại trong hệ thống, vui lòng chọn tên đăng nhập khác '; 
    end if;

    if exists ( select 1 from ThongTin where email_Arg like email ) 
    then
       SIGNAL SQLSTATE '45000'
       SET MESSAGE_TEXT = 'Email đã tồn tại trong hệ thống, vui lòng chọn một email khác'; 
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

     IF (maTaiKhoan_Arg = 'user') THEN
        IF (ngaySinh_Arg > CURDATE()) THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Ngày sinh phải nhỏ hơn hoặc bằng ngày hiện tại';
        ELSE
            IF (TIMESTAMPDIFF(YEAR, ngaySinh_Arg, CURDATE()) < 18) THEN
                SIGNAL SQLSTATE '45000'
                SET MESSAGE_TEXT = 'Bạn chưa đủ 18 tuổi để đăng ký thông tin';
            END IF;
        END IF;
    END IF;

    if !REGEXP_LIKE(email_Arg, '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$')
    then 
       SIGNAL SQLSTATE '45000'
       SET MESSAGE_TEXT = 'Email phải hợp lệ và đúng cú pháp định dạng email'; 
    end if;
    
   insert into ThongTin values( 
      cccd_Arg,
      anhThongTin_Arg,
      email_Arg,
      gioiTinh_Arg,
      ho_Arg,
      maTaiKhoan_Arg,
      matKhau_Arg,
      ngaySinh_Arg,
      ten_Arg,
      tenDangNhap_Arg,
      cccdQuanTriVien_Arg); 
end; 




CREATE PROCEDURE handler_Update_Table_ThongTin(
   IN cccd_Arg VARCHAR(255), -- Used as the unique identifier for the update
   IN anhThongTin_Arg VARCHAR(255),
   IN email_Arg VARCHAR(255),
   IN gioiTinh_Arg CHAR(1),
   IN ho_Arg VARCHAR(255),
   IN maTaiKhoan_Arg VARCHAR(255),
   IN matKhau_Arg VARCHAR(255),
   IN ngaySinh_Arg DATE,
   IN ten_Arg VARCHAR(255),
   IN tenDangNhap_Arg VARCHAR(255),
   IN cccdQuanTriVien_Arg VARCHAR(255)
)
BEGIN
    -- Validate that the record exists before updating
    IF NOT EXISTS (SELECT 1 FROM ThongTin WHERE cccd = cccd_Arg) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Không tìm thấy căn cước trong hệ thống để cập nhật.';
    END IF;

    -- Validate email uniqueness
    IF EXISTS (SELECT 1 FROM ThongTin WHERE email = email_Arg AND cccd != cccd_Arg) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Email đã tồn tại trong hệ thống, vui lòng chọn một email khác.';
    END IF;

    -- Validate username uniqueness
    IF EXISTS (SELECT 1 FROM ThongTin WHERE tenDangNhap = tenDangNhap_Arg AND cccd != cccd_Arg) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Tên đăng nhập đã tồn tại trong hệ thống, vui lòng chọn tên đăng nhập khác.';
    END IF;

    -- Validate minimum username length
    IF CHAR_LENGTH(tenDangNhap_Arg) < 6 OR tenDangNhap_Arg LIKE ' %' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Tên đăng nhập phải lớn hơn 5 ký tự và không chứa khoảng trắng.';
    END IF;

    -- Validate password length
    IF CHAR_LENGTH(matKhau_Arg) < 8 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Mật khẩu phải lớn hơn hoặc bằng 8 ký tự.';
    END IF;

    -- Validate email format
    IF NOT REGEXP_LIKE(email_Arg, '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$') THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Email phải hợp lệ và đúng cú pháp định dạng email.';
    END IF;

    -- Validate date format and age restriction for users
    IF NOT (ngaySinh_Arg REGEXP '^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$') THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ngày sinh phải hợp lệ.';
    END IF;

    IF maTaiKhoan_Arg = 'user' THEN
        IF ngaySinh_Arg > CURDATE() THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Ngày sinh phải nhỏ hơn hoặc bằng ngày hiện tại.';
        END IF;

        IF TIMESTAMPDIFF(YEAR, ngaySinh_Arg, CURDATE()) < 18 THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Bạn chưa đủ 18 tuổi để cập nhật thông tin.';
        END IF;
    END IF;

    -- Perform the update
    UPDATE ThongTin
    SET
        anhThongTin = anhThongTin_Arg,
        email = email_Arg,
        gioiTinh = gioiTinh_Arg,
        ho = ho_Arg,
        maTaiKhoan = maTaiKhoan_Arg,
        matKhau = matKhau_Arg,
        ngaySinh = ngaySinh_Arg,
        ten = ten_Arg,
        tenDangNhap = tenDangNhap_Arg,
        cccdQuanTriVien = cccdQuanTriVien_Arg
    WHERE cccd = cccd_Arg;
END;









--------------------------------------------------------------------

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
      where NhanVien.maNhanVien = maNhanVienArg;

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
      where NhanVien.maNhanVien = maNhanVienArg;
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




select * from `ThongTin`;