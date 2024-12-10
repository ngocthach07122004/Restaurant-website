create trigger tinh_TongGiaTien_DonMonAn
before insert on DonMonAn 
for each row
begin 
     declare tongGia decimal(15,2) default 0 ;
     select sum(BaoGom.soLuongMonAn*MonAn.gia) into tongGia from 
     MonAn join BaoGom on MonAn.maMonAn = MonAn.maMonAn 
     join DonMonAn on BaoGom.maDon = DonMonAn.maDon
     group by MonAn.maMonAn;

     update DonMonAn
     set tongGiaTien = tongGia 
     where maDon = new.maDon;
end; 

create trigger tinh_TongGiaTien_DonMonAn 
before insert on DonMonAn 
for each row 
begin
    declare tongGia decimal(15,2) default 0 ;




create trigger tinh_DoanhThu_DonMonAn 
after insert on ChiNhanh 
for each row
begin 
    declare tongDoanhThu decimal(15,2);
    select sum(DonMonAn.tongGiaTien) into tongDoanhThu
    from ChiNhanh join DonMonAn on ChiNhanh.maChiNhanh = DonMonAn.maChiNhanh
    group by ChiNhanh.maChiNhanh;
    update ChiNhanh
    set ChiNhanh.doanhThu = tongDoanhThu
    where ChiNhanh.maChiNhanh = new.maChiNhanh; 
    
end; 


create trigger tinhToan_Don_Dat_Huy_KhachHang 
after insert on KhachHang
for each row
begin 
   declare tongDonDat int default 0;
   declare tongDonHuy int default 0; 

    SELECT SUM(CASE WHEN DonMonAn.tinhTrangDonMonAn = 'Y' THEN 1 ELSE 0 END)
    INTO tongDonDat
    FROM DonMonAn
    WHERE DonMonAn.cccdKhachHang = NEW.cccd;

    SELECT SUM(CASE WHEN DonMonAn.tinhTrangDonMonAn = 'N' THEN 1 ELSE 0 END)
    INTO tongDonHuy
    FROM DonMonAn
    WHERE DonMonAn.cccdKhachHang = NEW.cccd;
   
   update KhachHang
   set KhachHang.soDonDaDat = tongDonDat , 
   KhachHang.soDonDaHuy = tongDonHuy 
   where KhachHang.cccd = new.cccd; 
   
end; 

-- Xử lý doanh thu mỗi khi cập nhập hoặc xóa DonMonAn

create trigger capNhap_DoanhThu_KhiXoa_DonMonAn 
before delete on DonMonAn
for each row 
begin
    update ChiNhanh 
    set ChiNhanh.doanhThu = ChiNhanh.doanhThu - old.tongGiaTien
    where ChiNhanh.maChiNhanh = DonMonAn.maChiNhanh; 
end; 


create trigger capNhap_DoanhThu_KhiUpdate_DonMonAn 
after update on DonMonAn
for each row 
begin
    update ChiNhanh 
    set ChiNhanh.doanhThu = ChiNhanh.doanhThu - old.tongGiaTien + new.tongGiaTien
    where ChiNhanh.maChiNhanh = DonMonAn.maChiNhanh; 
end; 






