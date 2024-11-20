

-- -Mức lương trả cho nhân viên ít nhất phải lớn hoặc bằng lương tối thiểu của khu vực 1, chưa tính các khoản tiền khác cộng thêm  (Lương tối thiểu khu vực 1 4.960.000 VN đồng) 
create trigger check_old_employee 
before insert on NhanVien 
for each row 
begin 
     declare cccdNhanVien varchar(100) default 'EMPTY'; 
     declare dobNhanVien date; 
     if new.cccd is null or new.cccd = ''
     then 
     SIGNAL SQLSTATE '45000' 
     SET MESSAGE_TEXT = 'Mã nhân viên không hợp lệ'; 
     end if; 

     select cccd into cccdNhanVien 
     where new.cccd = ThongTin.cccd; 

     if  cccdNhanVien = 'EMPTY'
     then 
     SIGNAL SQLSTATE '45000'
     SET MESSAGE_TEXT = 'Thông tin chưa tồn tại hoặc không được tại, vui lòng kiểm tra lại cccd';
     end if; 
     
     select ngaySinh into dobNhanVien 
     from ThongTin
     where ThongTin.cccd = new.cccd; 

     if TIMESTAMPDIFF(YEAR, dobNhanVien, CURDATE() ) <18
     then
     SIGNAL SQLSTATE '45000'
     SET MESSAGE_TEXT = 'Nhân viên chưa đủ tuổi'; 
     end if;

 

     if new.luong < 4960000 
     then 
     SIGNAL SQLSTATE '45000'
     SET MESSAGE_TEXT = 'Mức lương trả cho nhân viên tối thiểu phải lớn hơn hoặc bằng mức lương của khu vực 1'; 
     end if;

     insert into NhanVien values(new.cccd,new.ngayVaoLam,new.luong,new.cccd_quan_ly,new.maChiNhanh);

end; 


-- -Mức lương của nhân viên mỗi khi được cập nhập phải lớn hơn mức lương hiện tại, nếu không cập nhập lương sẽ không được thực hiện. 

create trigger check_update_salary_employee 
before update on NhanVien 
for each row
begin
  if new.luong < old.luong
  then
  SIGNAL SQLSTATE '45000'
  SET MESSAGE_TEXT = 'Mức lương mới phải lớn hơn mức lương ban đầu';
  end if; 
end; 



-- -Tổng số lượng món ăn không được vượt quá số lượng tồn kho cho phép ở mỗi chi nhánh ( số lượng tồn kho tối đa cho phép 50 món ăn) 


create trigger handler_insert_max_food_inventory_allow
before insert on MonAnThuocVe 
for each row
begin
     declare checkMonAn int default -1;
     declare tongMonAn int default 0;
     select MonAnThuocVe.soLuongMonAn into checkMonAn from MonAnThuocVe
     where new.maMonAn = MonAnThuocVe.maMonAn and new.maChiNhanh = MonAnThuocVe.maChiNhanh;
     if checkMonAn <> -1
     then
     SIGNAL SQLSTATE '45000'
     SET MESSAGE_TEXT = 'Món ăn này đã tồn này ở nhà hàng này, vui lòng cập nhập số lượng của món ăn'; 
     end if; 

    
     select sum(MonAnThuocVe.soLuongMonAn) into tongMonAn from MonAnThuocVe 
     where new.maChiNhanh = MonAnThuocVe.maChiNhanh; 

     if tongMonAn + new.soLuongMonAn > 50
     then
     SIGNAL SQLSTATE '45000'
     SET MESSAGE_TEXT = 'Tổng số lượng món ăn vượt quá số lượng tồn kho cho phép'; 
     end if; 
end; 

create trigger handler_update_max_food_inventory_allow
before update on MonAnThuocVe 
for each row
begin
    
     declare tongMonAn int default 0;
     select sum(MonAnThuocVe.soLuongMonAn) into tongMonAn from MonAnThuocVe 
     where new.maChiNhanh = MonAnThuocVe.maChiNhanh; 

     if tongMonAn + new.soLuongMonAn - old.soLuongMonAn> 50
     then
     SIGNAL SQLSTATE '45000'
     SET MESSAGE_TEXT = 'Tổng số lượng món ăn vượt quá số lượng tồn kho cho phép'; 
     end if; 
end; 



--  Đơn giao hàng phải trong phạm vi 15m.
create trigger handler_insert_max_distance_allow 
before insert on DonGiaoHang
for each row
begin 
        if not exists (select 1 from DonMonAn where DonMonAn.maDon = new.maDon)
        then 
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Vui lòng đặt món ăn hoặc kiểm tra đã chọn món hay chưa';
        end if;

        if new.khoangCach > 15 
        then
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Khoảng cách quá xa để giao tận nơi, vui lòng sử dụng dịch vụ tại quán';
        end if; 


end; 



create trigger handler_update_max_distance_allow 
before update on DonGiaoHang
for each row
begin 
        
        if new.khoangCach > 15 
        then
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Khoảng cách quá xa để giao tận nơi, vui lòng sử dụng dịch vụ tại quán';
        end if; 

        
end; 



-- -Để đảm bảo tốc độ giao hàng và chất lượng món ăn, mỗi nhân viên giao hàng chỉ được vận chuyển tối đa 10 đơn hàng trong 1 chuyến
create trigger handler_insert_max_order_allow
before insert on GiaoHang
for each row 
begin
    declare tongDonHang int default 0;
    select count(GiaoHang.maDon) into tongDonHang from GiaoHang
    where new.cccdGiaoHang = GiaoHang.cccdGiaoHang; 

    if tongDonHang +1 >10
    then
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Nhân viên không được giao quá 10 đơn hàng trong 1 chuyến xe';
    end if;
end; 