create table DonGiaoHang (
    maDon 			VARCHAR(100)PRIMARY KEY,
    tenNguoiNhan 		VARCHAR(100),
    thoiGianGiaoDuKien 	DATE,
    thoiGianNhanThucTe 	DATE,
    ghiChu 			VARCHAR(100),
    tinhTrangDonHang 	VARCHAR(100),
    diaChiNhan 		VARCHAR(100),
    phiVanChuyen 		DECIMAL(15,2),
    cccdThuNgan 		VARCHAR(100) , 
    khoangCach DECIMAL(15,2)
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
    cccdTongDaiVien 		VARCHAR(100)
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
    loaiMonAn VARCHAR(100)
);
CREATE TABLE MaKhuyenMai (
    idKhuyenMai 		VARCHAR(100) PRIMARY KEY,
    moTa 			VARCHAR(100),
    giaTriGiamGia 		DECIMAL(15,2),
    dieuKienDung 		VARCHAR(100),
    maDon 			VARCHAR(100), 
    ten            VARCHAR(100), 
    maChiNhanh 		VARCHAR(100),
    loaiMa VARCHAR(100)
);


ALTER TABLE MaKhuyenMai ADD CONSTRAINT fk_MaKhuyenMai_DonMonAn
FOREIGN KEY(maDon) REFERENCES DonMonAn(maDon);

ALTER TABLE MaKhuyenMai ADD CONSTRAINT fk_MaKhuyenMai_maChiNhanh
FOREIGN KEY(maChiNhanh) REFERENCES ChiNhanh(maChiNhanh);






CREATE TABLE SuKienUuDai (
    maUuDai 			VARCHAR(100),
    maChiNhanh 		VARCHAR(100),
    loaiMa 			VARCHAR(100),
    giaTriGiamGia 		DECIMAL(15,2),
    PRIMARY KEY (maUuDai, maChiNhanh)
);

ALTER TABLE SuKienUuDai ADD CONSTRAINT fk_SuKienUuDai_maChiNhanh
FOREIGN KEY(maChiNhanh) REFERENCES ChiNhanh(maChiNhanh);







CREATE TABLE ThongTin (
    cccd 		VARCHAR(100) 	PRIMARY KEY,
	tenDangNhap 		VARCHAR(100) NOT NULL,
	matKhau 			VARCHAR(100) NOT NULL,
	ho 				VARCHAR(100),
	ten 				VARCHAR(100),
	ngaySinh 			DATE,
	email 				VARCHAR(100),
	gioiTinh 			VARCHAR(1),
	soDienThoai 			VARCHAR(100),
	maTaiKhoan 			VARCHAR(100),
	cccdQuanTriVien 		VARCHAR(100)
	-- FOREIGN KEY (cccd_QuanTriVien) REFERENCES ThongTin(cccd)
);
ALTER TABLE ThongTin
MODIFY COLUMN tenDangNhap VARCHAR(100) COLLATE utf8mb4_bin;

CREATE TABLE KhachHang (
    cccd VARCHAR(100) PRIMARY KEY,
	ngayThamGia DATE,
	loaiKhachHang VARCHAR(100),
    soDonDaDat int,
    soDonDaHuy int,
	FOREIGN KEY (cccd) REFERENCES ThongTin(cccd)
);






CREATE TABLE QuanTriVien (
	cccd 			VARCHAR(100) PRIMARY KEY,
	moTaVaiTro 		VARCHAR(100)
	-- FOREIGN KEY (cccd) REFERENCES ThongTin(cccd)
);

CREATE TABLE NhanVien (
	cccd 			VARCHAR(100) PRIMARY KEY,
	ngayVaoLam 		DATE,
	luong 			DECIMAL(15, 2),
	cccd_quan_ly 		VARCHAR(100),
	maChiNhanh 		VARCHAR(100)
	-- FOREIGN KEY (cccd) REFERENCES ThongTin(cccd),
	-- FOREIGN KEY (QL_cccd) REFERENCES ThongTin(cccd)
);


CREATE TABLE NhanVienQuanLy (
	cccd 			VARCHAR(100) PRIMARY KEY,
	maChiNhanh 		VARCHAR(100)
	-- FOREIGN KEY (cccd) REFERENCES NhanVien(cccd)
);
CREATE TABLE NhanVienPhucVu (
	cccd 				VARCHAR(100) PRIMARY KEY
	-- FOREIGN KEY (cccd) REFERENCES NhanVien(cccd)
);

CREATE TABLE NhanVienThuNgan (
	cccd 				VARCHAR(100) PRIMARY KEY
	-- FOREIGN KEY (cccd) REFERENCES NhanVien(cccd)
);

CREATE TABLE NhanVienTongDai (
	cccd 				VARCHAR(100) PRIMARY KEY
	-- FOREIGN KEY (cccd) REFERENCES NhanVien(cccd)
);

CREATE TABLE NhanVienGiaoHang (
	cccd 				VARCHAR(100) PRIMARY KEY,
	tinhTrangHoatDong 		VARCHAR(100)
	-- FOREIGN KEY (cccd) REFERENCES NhanVien(cccd)
);

CREATE TABLE PhuongTien (
	bienSoXe VARCHAR(100) PRIMARY KEY,
	loaiPhuongTien VARCHAR(100) NOT NULL, 
    giayPhepSoHuu VARCHAR(100), 
    thongTinDangKy VARCHAR(100), 
    thoiGianDangKy DATE, 
    thoiGianHetHan DATE
);







CREATE TABLE ThongBao(
  maThongBao VARCHAR(100) PRIMARY KEY, 
  noiDungThongBao   VARCHAR(100), 
 thoiGian DATE , 
 cccdThongTin VARCHAR(100), 
 cccdQuanLy VARCHAR(100),  
 cccdQuanTriVien VARCHAR(100) 
 );