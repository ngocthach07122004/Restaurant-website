use restaurant_service_database_v2;
show tables;

describe ChiNhanh;
  SET FOREIGN_KEY_CHECKS = 0;

SET FOREIGN_KEY_CHECKS = 1;
insert into ChiNhanh(maChiNhanh,diaChi,moTa,tenChiNhanh,thoiGianDongCua,thoiGianMoCua,trangThaiHoatDong,cccdQuanTriVien)
values("1111","HCM", "CN","LAU NUONG","2030-12-07","2024-12-07","TOT","123");

insert into KhachHang values("2222","KH","2024-12-07",0,0, "123456");

insert into NhanVienThuNgan values("3333");

insert into MaKhuyenMai values("KM1","CHO",50,"MT","1111",null);
insert into MaKhuyenMai values("KM2","CHO",100,"MT","1111",null);
insert into MaKhuyenMai values("KM3","CHO",150,"MT","1111",null);


insert into MonAn values("MA1","ANH",100000,"2 NGUOI","MON MAN","MON RAT NGON","GA RAN","10P");
insert into MonAn values("MA2","ANH",100000,"2 NGUOI","MON MAN","MON RAT NGON","GA RAN","10P");
insert into MonAn values("MA3","ANH",100000,"2 NGUOI","MON MAN","MON RAT NGON","GA RAN","10P");
insert into MonAn values("MA4","ANH",100000,"2 NGUOI","MON MAN","MON RAT NGON","GA RAN","10P");


select * from MonAn;

describe BaoGom;
drop table BaoGom;


show tables;


use restaurant_service_database_v2;

select * from QuanTriVien;

describe QuanTriVien;

insert into QuanTriVien values("999999999","Quản Trị Viên");

describe MaKhuyenMai;

SELECT * FROM MaKhuyenMai;

insert into MaKhuyenMai values("MKM01","CHO ")