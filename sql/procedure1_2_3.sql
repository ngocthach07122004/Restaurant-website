create procedure tongKhachHangTaiChiNhanh (
     in maChiNhanhArg varchar(100)
)
begin
   select count(distinct KhachHang.maKhachHang) as tongSoKhach from
   KhachHang join DonMonAn on KhachHang.maKhachHang = maKhachHang 
   where DonMonAn.maChiNhanh = maChiNhanhArg;
end; 


-- tổng số tiền một khách đã đặt hàng tại chi nhánh 

create procedure tongSoTienMotKhachDatTaiChiNhanh (
    in maKhachHangArg varchar(100) , in maChiNhanhArg varchar(100)
)
begin
   select concat(ThongTin.ho,' ',ThongTin.ten) as tenKhachHang from ThongTin NATURAL JOIN
   ( select  KhachHang.cccd,  sum (DonMonAn.tongGiaTien) as tongSoTien 
    from KhachHang join DonMonAn ON KhachHang.maKhachHang = DonMonAn.cccdKhachHang
    where DonMonAn.maChiNhanh = maChiNhanhArg and KhachHang.maKhachHang = maKhachHangArg
    group by KhachHang.maKhachHang
    ) as tableTongSoTien ;
end; 


-- create procedure hienCacMaKhuyenMaiTaiChiNhanhTheoNgay (
--     in ngayBatDauArg date , in ngayKetThucArg date, in maChiNhanhArg varchar(100)
-- )
-- begin
--      select ThoiGianMaKhuyenMai.thoiGianHetHan, ThoiGianMaKhuyenMai.thoiGianPhatHanh,  MaKhuyenMai.ten
--      from  MaKhuyenMai join ThoiGianMaKhuyenMai on MaKhuyenMai.idKhuyenMai = ThoiGianMaKhuyenMai.idKhuyenMai
--      where  MaKhuyenMai.maChiNhanh= maChiNhanhArg and thoiGianHetHan < ngayKetThucArg and ngayBatDauArg > thoiGianPhatHanh;
-- end; 


create procedure tongSoNhanVienLamViecHonMotThangTaiChiNhanh
(
     in maChiNhanhArg varchar(100)
)
begin
    select count(distinct NhanVien.cccd ) as tongSoNhanVien
    from NhanVien 
    where NhanVien.maChiNhanh = maChiNhanhArg and 
    TIMESTAMPDIFF(MONTH, ngayVaoLam, CURDATE()) >=1; 
end; 


