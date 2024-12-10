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
    if exists ( select 1 from ThongTin where cccd_Arg = ThongTin.cccd  ) 
    then 
       SIGNAL SQLSTATE '45000'
       SET MESSAGE_TEXT = 'Căn cước đã tồn tại trong hệ thống, vui lòng kiểm tra lại số căn cước'; 
    end if;
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

    if (maTaiKhoan_Arg = 'user')
    then (
      if (ngaySinh_Arg > CURDATE())
      then
       SIGNAL SQLSTATE '45000'
       SET MESSAGE_TEXT = 'Ngày sinh phải lớn hơn ngày hiện tại';    
    )
    else
    then
       if ()
    end if; 

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





INSERT INTO ThongTin VALUES
('000000038', 'https://cdn-icons-png.flaticon.com/512/1144/1144760.png', 'minhphuc@gmail.com', 'M', 'Nguyen', 'user', 'User123@', '1997-01-05', 'Minh Phuc', 'minhphuc', '999999999'),
('000000039', 'https://cdn-icons-png.flaticon.com/512/1144/1144760.png', 'huynhmy@gmail.com', 'F', 'Le',      'user', 'User123@', '1998-04-12', 'Huynh My', 'huynhmy', '999999999'),
('000000040', 'https://cdn-icons-png.flaticon.com/512/1144/1144760.png', 'truongson@gmail.com', 'M', 'Ho',    'user', 'User123@', '2000-09-22', 'Truong Son', 'truongson', '999999999'),
('000000041', 'https://cdn-icons-png.flaticon.com/512/1144/1144760.png', 'ngocanh@gmail.com', 'F', 'Pham',    'user', 'User123@', '1999-03-14', 'Ngoc Anh', 'ngocanh', '999999999'),
('000000042', 'https://cdn-icons-png.flaticon.com/512/1144/1144760.png', 'huykhanh@gmail.com', 'M', 'Tran',   'user', 'User123@', '1996-10-30', 'Huy Khanh', 'huykhanh', '999999999'),
('000000043', 'https://cdn-icons-png.flaticon.com/512/1144/1144760.png', 'myhanh@gmail.com', 'F', 'Vo',       'user', 'User123@', '2002-07-21', 'My Hanh', 'myhanh', '999999999'),
('000000044', 'https://cdn-icons-png.flaticon.com/512/1144/1144760.png', 'daihai@gmail.com', 'M', 'Vu',       'user', 'User123@', '1995-05-10', 'Dai Hai', 'daihai', '999999999'),
('000000045', 'https://cdn-icons-png.flaticon.com/512/1144/1144760.png', 'thanhthu@gmail.com', 'F', 'Ngo',    'user', 'User123@', '1998-11-08', 'Thanh Thu', 'thanhthu', '999999999'),
('000000046', 'https://cdn-icons-png.flaticon.com/512/1144/1144760.png', 'hongan@gmail.com', 'M', 'Pham',     'user', 'User123@', '2001-02-15', 'Hong An', 'hongan', '999999999'),
('000000047', 'https://cdn-icons-png.flaticon.com/512/1144/1144760.png', 'maihoa@gmail.com', 'F', 'Nguyen',   'user', 'User123@', '1997-06-05', 'Mai Hoa', 'maihoa', '999999999'),
('000000048', 'https://cdn-icons-png.flaticon.com/512/1144/1144760.png', 'minhhieu@gmail.com', 'M', 'Le',     'user', 'User123@', '1994-09-14', 'Minh Hieu', 'minhhieu', '999999999'),
('000000049', 'https://cdn-icons-png.flaticon.com/512/1144/1144760.png', 'lanphuong@gmail.com', 'F', 'Bui',   'user', 'User123@', '2000-03-26', 'Lan Phuong', 'lanphuong', '999999999'),
('000000050', 'https://cdn-icons-png.flaticon.com/512/1144/1144760.png', 'hoanglong@gmail.com', 'M', 'Tran',  'user', 'User123@', '1995-12-02', 'Hoang Long', 'hoanglong', '999999999'),
('000000051', 'https://cdn-icons-png.flaticon.com/512/1144/1144760.png', 'khanhvy@gmail.com', 'F', 'Ho',      'user', 'User123@', '1999-08-19', 'Khanh Vy', 'khanhvy', '999999999'),
('000000052', 'https://cdn-icons-png.flaticon.com/512/1144/1144760.png', 'quocdat@gmail.com', 'M', 'Vu',      'user', 'User123@', '1996-07-11', 'Quoc Dat', 'quocdat', '999999999'),
('000000053', 'https://cdn-icons-png.flaticon.com/512/1144/1144760.png', 'minhthu@gmail.com', 'F', 'Ngo',     'user', 'User123@', '2002-01-28', 'Minh Thu', 'minhthu', '999999999'),
('000000054', 'https://cdn-icons-png.flaticon.com/512/1144/1144760.png', 'ngochieu@gmail.com', 'M', 'Nguyen', 'user', 'User123@', '1998-04-16', 'Ngoc Hieu', 'ngochieu', '999999999'),
('000000055', 'https://cdn-icons-png.flaticon.com/512/1144/1144760.png', 'thuytrang@gmail.com', 'F', 'Pham',  'user', 'User123@', '1997-03-18', 'Thuy Trang', 'thuytrang', '999999999'),
('000000056', 'https://cdn-icons-png.flaticon.com/512/1144/1144760.png', 'thanhdat@gmail.com', 'M', 'Le',     'user', 'User123@', '1995-06-14', 'Thanh Dat', 'thanhdat', '999999999'),
('000000057', 'https://cdn-icons-png.flaticon.com/512/1144/1144760.png', 'hoangngoc@gmail.com', 'F', 'Tran',  'user', 'User123@', '2001-12-19', 'Hoang Ngoc', 'hoangngoc', '999999999');
