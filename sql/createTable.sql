create table DonGiaoHang (
    maDon 			VARCHAR(100)(100) PRIMARY KEY,
    tenNguoiNhan 		VARCHAR(100)(100),
    thoiGianGiaoDuKien 	DATE,
    thoiGianNhanThucTe 	DATE,
    ghiChu 			VARCHAR(100)(100),
    tinhTrangDonHang 	VARCHAR(100)(100),
    diaChiNhan 		VARCHAR(100)(100),
    phiVanChuyen 		DECIMAL(15,2),
    cccdThuNgan 		VARCHAR(100)(100)   
);




alter table DonGiaoHang add constraint fk_DonGiaoHang_cccdThuNgan
foreign key(cccdThuNgan) references NhanVienThuNgan (cccd);




create table ChiNhanh (
    maChiNhanh 		VARCHAR(100) PRIMARY KEY,
    diaChi 			VARCHAR(100),
    moTa 			VARCHAR(100),
    trangThaiHoatDong 	VARCHAR(100),
    tenChiNhanh 		VARCHAR(100),
    thoiGianMoCua 		DATE,
    thoiGianDongCua 		DATE,
    cccdQuanTriVien 		VARCHAR(100),
    doanhThu DECIMAL(15,2)
);



alter table DonGiaoHang add constraint fk_ChiNhanh_cccdQuanTriVien
foreign key(cccdQuanTriVien) references QuanTriVien(cccd);




create table DonMonAn (
    maDon 			VARCHAR(100) PRIMARY KEY,
    thoiGianDat 		DATE,
    tongGiaTien 		DECIMAL(15,2),
    phuongThucThanhToan 	VARCHAR(100),
    maChiNhanh 		VARCHAR(100),
    cccdKhachHang 		VARCHAR(100),
    cccdNhanVienThuNgan 	VARCHAR(100),
    tinhTrangThanhToan 	VARCHAR(100), 
    tinhTrangDonMonAn VARCHAR(1)
);






alter table DonMonAn add constraint fk_DonMonAn_maChiNhanh
foreign key(maChiNhanh) references ChiNhanh(maChiNhanh);


alter table DonMonAn add constraint fk_DonMonAn_cccdKhachHang
foreign key(cccdKhachHang) references KhachHang(cccd);


alter table DonMonAn add constraint fk_DonMonAn_cccdNhanVienThuNgan
foreign key(cccdNhanVienThuNgan) references NhanVienThuNgan(cccd);












create table PhuongThucThanhToan (
    maDon 			VARCHAR(100) ,
    phuongThucThanhToan 	VARCHAR(100) ,
    PRIMARY KEY(maDon,phuongThucThanhToan)
);


alter table PhuongThucThanhToan add constraint fk_PhuongThucThanhToan_maDon
foreign key(maDon) references DonMonAn(maDon);






create table DonTaiQuan (
   maDon 			VARCHAR(100) PRIMARY KEY,
   yeuCauCuaKhachHang 	VARCHAR(100),
   cccdPhucVu 			VARCHAR(100)
);


alter table DonTaiQuan add constraint fk_DonTaiQuan_maDon
foreign key(maDon) references DonMonAn(maDon);


alter table DonTaiQuan add constraint fk_DonTaiQuan_cccdPhucVu
foreign key(cccdPhucVu) references NhanVienPhucVu(cccd);




create table DonKhieuNai (
    maDonKhieuNai 		VARCHAR(100),
    noiDung 			VARCHAR(100),
    thoiGian 			DATE,
    maDon 			VARCHAR(100),
    cccdQuanLy 		VARCHAR(100),
    cccdKhachHang 		VARCHAR(100),
    cccdTongDaiVien 		VARCHAR(100),
);


alter table DonKhieuNai add constraint fk_DonKhieuNai_maDon
foreign key(maDon) references MonAn(maMonAn);


alter table DonKhieuNai add constraint fk_DonKhieuNai_cccdQuanLy
foreign key(cccdQuanLy) references NhanVienQuanLy(cccd);


alter table DonKhieuNai add constraint fk_DonKhieuNai_cccdKhachHang
foreign key(cccdKhachHang) references KhachHang(cccd);


alter table DonKhieuNai add constraint fk_DonKhieuNai_cccdTongDaiVien
foreign key(cccdTongDaiVien) references NhanVienTongDai(cccd);

/* ####START HUY#### */
CREATE TABLE MonAn (
    maMonAn 			VARCHAR(100) PRIMARY KEY,
    gia 				DECIMAL(15,2),
    tenMonAn 			VARCHAR(100),
    moTa 			VARCHAR(100),
);
CREATE TABLE MaKhuyenMai (
    idKhuyenMai 		VARCHAR(100) PRIMARY KEY,
    moTa 			VARCHAR(100),
    giaTriGiamGia 		DECIMAL(15,2),
    dieuKienDung 		VARCHAR(100),
    maDon 			VARCHAR(100), 
    maChiNhanh 		VARCHAR(100)
);

ALTER TABLE MaKhuyenMai ADD CONSTRAINT fk_MaKhuyenMai_DonMonAn
FOREIGN KEY(maDon) REFERENCES DonMonAn(maDon);

ALTER TABLE MaKhuyenMai ADD CONSTRAINT fk_MaKhuyenMai_maChiNhanh
FOREIGN KEY(maChiNhanh) REFERENCES ChiNhanh(maChiNhanh);

CREATE TABLE PhuongTien (
    bienSoXe 			VARCHAR(100) PRIMARY KEY,
    loaiPhuongTien 		VARCHAR(100),
    gioiPhepSoHuu 		VARCHAR(100),
    thongTinDangKy 		VARCHAR(100),
    thoiGianDangKy 		DATE,
    thoiGianHetHan 		DATE
);
CREATE TABLE SuKienUuDai (
    maUuDai 			VARCHAR(100),
    maChiNhanh 		VARCHAR(100),
    loaiMa 			VARCHAR(100),
    giaTriGiamGia 		DECIMAL(15,2),
    PRIMARY KEY (maUuDai, maChiNhanh)
);

ALTER TABLE SuKienUuDai ADD CONSTRAINT fk_SuKienUuDai_maChiNhanh
FOREIGN KEY(maChiNhanh) REFERENCES ChiNhanh(maChiNhanh);

/* ####END HUY #### */

/* Thông tin
	- Khách hàng
	- Quản trị viên
	- Nhân viên 
		- Nhân viên Quản lý
		- Nhân viên Phục vụ
		- Nhân viên Giao hàng
		- Nhân viên Tổng đài
		- Nhân viên Thu ngân
	- Số điện thoại (thuộc tính đa trị) */

CREATE TABLE ThongTin (
    cccd 		VARCHAR(100) 	PRIMARY KEY,
	tenDangNhap 		VARCHAR(100) NOT NULL,
	matKhau 			VARCHAR(100) NOT NULL,
	ho 				VARCHAR(100),
	ten 				VARCHAR(100),
	ngaySinh 			DATE,
	email 				VARCHAR(100),
	gioiTinh 			ENUM('M', 'F'),
	soDienThoai 			VARCHAR(100),
	maTaiKhoan 			VARCHAR(100),
	cccdQuanTriVien 		VARCHAR(100),
	FOREIGN KEY (CCCD_QuanTriVien) REFERENCES ThongTin(CCCD)
);
CREATE TABLE SoDienThoai (
	CCCD 				VARCHAR(100),
	SoDienThoai 			VARCHAR(100),
	PRIMARY KEY (CCCD, SoDienThoai),
	FOREIGN KEY (CCCD) REFERENCES ThongTin(CCCD)
);
CREATE TABLE KhachHang (
    cccd VARCHAR(100) PRIMARY KEY,
	ngayThamGia DATE,
	loaiKhachHang VARCHAR(100),
    soDonDaDat int,
    soDonDaHuy int,
	FOREIGN KEY (CCCD) REFERENCES ThongTin(CCCD)
);






CREATE TABLE QuanTriVien (
	CCCD 			VARCHAR(100) PRIMARY KEY,
	MoTaVaiTro 		VARCHAR(100),
	FOREIGN KEY (CCCD) REFERENCES ThongTin(CCCD)
);
CREATE TABLE NhanVien (
	CCCD 			VARCHAR(100) PRIMARY KEY,
	NgayVaoLam 		DATE,
	Luong 			DECIMAL(15, 2),
	QL_CCCD 		VARCHAR(100),
	MaChiNhanh 		VARCHAR(100),
	FOREIGN KEY (CCCD) REFERENCES ThongTin(CCCD),
	FOREIGN KEY (QL_CCCD) REFERENCES ThongTin(CCCD)
);

CREATE TABLE NhanVienQuanLy (
	CCCD 			VARCHAR(100) PRIMARY KEY,
	MaChiNhanh 		VARCHAR(100),
	FOREIGN KEY (CCCD) REFERENCES NhanVien(CCCD)
);
CREATE TABLE NhanVienPhucVu (
	CCCD 				VARCHAR(100) PRIMARY KEY,
	FOREIGN KEY (CCCD) REFERENCES NhanVien(CCCD)
);

CREATE TABLE NhanVienThuNgan (
	CCCD 				VARCHAR(100) PRIMARY KEY,
	FOREIGN KEY (CCCD) REFERENCES NhanVien(CCCD)
);

CREATE TABLE NhanVienTongDai (
	CCCD 				VARCHAR(100) PRIMARY KEY,
	FOREIGN KEY (CCCD) REFERENCES NhanVien(CCCD)
);

CREATE TABLE NhanVienGiaoHang (
	CCCD 				VARCHAR(100) PRIMARY KEY,
	TinhTrangHoatDong 		VARCHAR(100),
	FOREIGN KEY (CCCD) REFERENCES NhanVien(CCCD)
);

CREATE TABLE PhuongTien (
	BienSoXe VARCHAR(100) PRIMARY KEY,
	LoaiPhuongTien VARCHAR(100) NOT NULL
);

CREATE TABLE BangLai (
	CCCD VARCHAR(100),
	BangLaiXe VARCHAR(100),
	PRIMARY KEY (CCCD, BangLaiXe),
	FOREIGN KEY (CCCD) REFERENCES NhanVienGiaoHang(CCCD)
);

-- Table for SuDung
CREATE TABLE SuDung (
	CCCD 			VARCHAR(100),
	BienSoXe 		VARCHAR(100),
	PRIMARY KEY (CCCD, BienSoXe),
	FOREIGN KEY (CCCD) REFERENCES NhanVienGiaoHang(CCCD),
	FOREIGN KEY (BienSoXe) REFERENCES PhuongTien(BienSoXe)
);





CREATE TABLE ThongBao(
  maThongBao VARCHAR(100) PRIMARY KEY, 
  noiDungThongBao   VARCHAR(100), 
 thoiGian DATE , 
 cccdThongTin VARCHAR(100), 
 cccdQuanLy VARCHAR(100),  
 cccdQuanTriVien VARCHAR(100) 
 );



 create table DanhGia(
      maDanhGia VARCHAR(100) ,
      cccdKhachHang VARCHAR(100),
      maMonAn VARCHAR(100),
      noiDung VARCHAR(100),
      thoiGian DATE
 ); 
 -- alter table DanhGia add constraint fk_DanhGia_cccdKhachHang
 -- foreign key(cccdKhachHang) references KhachHang (cccdKhachHang);

 -- alter table DanhGia add constraint fk_DanhGia_maMonAn
 -- foreign key(maMonAn) references MonAn (maMonAn);

 create table BaoGom( 
     maMonAn VARCHAR(100),
     maDon VARCHAR(100),
     soLuongMonAn int
); 


-- alter table BaoGom add constraint fk_BaoGom_maMonAn
-- foreign key(maMonAn) references  MonAn (maMonAn);

-- alter table BaoGom add constraint fk_BaoGom_maDon
-- foreign key(maDon) references DonMonAn (maDon);

create table GiaoHang(

     maDon VARCHAR(100), 
     cccdKhachHang VARCHAR(100),
     cccdGiaoHang VARCHAR(100) 
);
-- alter table GiaoHang add constraint fk_GiaoHang_cccdKhachHang
-- foreign key(cccdKhachHang) references   KhachHang (cccdKhachHang);

-- alter table GiaoHang add constraint fk_GiaoHang_cccdGiaoHang
-- foreign key(cccdGiaoHang) references GiaoHang (cccdGiaoHang);


create table MonAnThuocVe(
     maMonAn VARCHAR(100),
     maChiNhanh VARCHAR(100)
);

-- alter table MonAnThuocVe add constraint fk_MonAnThuocVe_maMonAn
-- foreign key(maMonAn) references   MonAn (maMonAn);

-- alter table MonAnThuocVe add constraint fk_MonAnThuocVe_maChiNhanh
-- foreign key(maChiNhanh) references ChiNhanh (maChiNhanh);

create table ThoiGianMaKhuyenMai (
     idKhuyenMai VARCHAR(100), 
     thoiGianHetHan DATE,
     thoiGianPhatHanh DATE

); 


-- alter table ThoiGianMaKhuyenMai add constraint fk_ThoiGianMaKhuyenMai_idKhuyenMai
-- foreign key(idKhuyenMai) references   MaKhuyenMai (idKhuyenMai);





create table SoHuu (
   cccdKhachHang VARCHAR(100) ,
   idKhuyenMai VARCHAR(100)
);


-- alter table SoHuu add constraint fk_SoHuu_cccdKhachHang
-- foreign key(maMonAn) references    KhachHang (cccdKhachHang);

-- alter table SoHuu add constraint fk_SoHuu_idKhuyenMai
-- foreign key(maChiNhanh) references ChiNhanh (maChiNhanh);
