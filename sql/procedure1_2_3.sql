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


-- Tổng số đơn hàng và số tiền đã dùng của mỗi khách hàng trong chi nhánh A trong nam nay
create procedure tongSoDonHangTrongNamNayTaiChiNhanh(
    in maChiNhanh_arg VARCHAR(255)
)
begin
    select   
        KhachHang.maKhachHang,
        ThongTin.ho,
        ThongTin.ten,
        count(DonMonAn.maDon) AS tongSoDonHang,
        sum(DonMonAn.tongGiaTien) AS tongSoTien
    from KhachHang
    inner join DonMonAn on KhachHang.maKhachHang = DonMonAn.cccdKhachHang
    inner join ThongTin ON KhachHang.cccd = ThongTin.cccd
    where DonMonAn.maChiNhanh = maChiNhanh_arg
    and MONTH(DonMonAn.thoiGianDat) = MONTH(CURDATE())
    and YEAR(DonMonAn.thoiGianDat) = YEAR(CURDATE())
    group by KhachHang.maKhachHang, ThongTin.ho, ThongTin.ten
    order by tongSoTien desc;
end;




