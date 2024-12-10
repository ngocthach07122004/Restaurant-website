create function tinhTongTienMonAnQuanLyBoiNhanVien ( 
     tenNhanVien varchar(0)
)
returns decimal(15,2)
DETERMINISTIC
begin
   declare tongGiaTien decimal(15,2) default 0; 
   declare maNhanVien varchar(100) default 'notExist';
   if tenNhanVien is null or tenNhanVien ='' then
   return tongGiaTien;
   end if; 
   
   
   select NhanVien.maNhanVien into maNhanVien 
   from NhanVien join ThongTin on NhanVien.cccd = ThongTin.cccd
   where ThongTin.ten = tenNhanVien
   limit 1; 
   if maNhanVien = 'notExist' 
   then return tongGiaTien;
   end if;
   
   select sum(MonAn.gia * MonAnChiNhanh.soLuongMonAn) into tongGiaTien from 
   ChiNhanh join MonAnThuocVe on ChiNhanh.maChiNhanh = MonAnThuocVe.maChiNhanh
   join MonAnChiNhanh on MonAnChiNhanh.maMonAnChiNhanh = MonAnThuocVe.maMonAnChiNhanh
   join MonAn on MonAnChiNhanh.monAn = MonAn.maMonAn 
   where ChiNhanh.maChiNhanh = (select NhanVien.maChiNhanh 
   from NhanVien where NhanVien.maNhanVien = maNhanVien ) 
   -- group by ChiNhanh.maChiNhanh;

   return tongGiaTien;
 
end; 

-- Tính tổng số tiền phí vận chuyển đặt hàng bởi khách hàng A, khách hàng phải lớn hơn 18 tuổi 
create function tongChiPhiVanChuyenCuaMotKhachHang (
   tenKhachHangArg varchar(100), tenDangNhapArg varchar(100)
) 
returns decimal(15,2)
DETERMINISTIC 
begin 
   declare tongChiPhi decimal(15,2) default 0; 
   declare maKhachHang varchar(100) default 'EMPTY';
   if tenKhachHangArg is null or tenKhachHangArg ='' or tenDangNhapArg is null or tenDangNhapArg = ''
   then return tongChiPhi;
   end if;

   select KhachHang.maKhachHang into maKhachHang 
   from KhachHang join ThongTin on maKhachHang.cccd = ThongTin.cccd 
   where ThongTin.ten =  tenKhachHangArg and tenDangNhapArg = ThongTin.tenDangNhap
   and TIMESTAMPDIFF(YEAR, ThongTin.ngaySinh, CURDATE()) >=18;
   if maKhachHang = 'EMPTY'
   then return tongChiPhi; 
   end if;

   select sum( DonGiaoHang.phiVanChuyen) into tongChiPhi 
   from DonMonAn join DonGiaoHang on
   DonMonAn.maDon = DonGiaoHang.maDon
   where DonMonAn.cccdKhachHang = maKhachHang; 
   return tongChiPhi;
end;


