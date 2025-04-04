create table ThongTin (
    cccd            varchar(255) not null, 
    anhThongTin     varchar(255), 
    email           varchar(255), 
    gioiTinh        varchar(255), 
    ho              varchar(255), 
    maTaiKhoan      varchar(255), 
    matKhau         varchar(255), 
    ngaySinh        date, 
    ten             varchar(255), 
    tenDangNhap     varchar(255), 
    cccdQuanTriVien varchar(255), 
    primary key (cccd)); 

create table BangLaiXe (cccd varchar(255) not null, bangLaiXe varchar(255)) 
create table BaoGom (maDon varchar(255) not null, maMonAnGioHang varchar(255) not null) 
create table ChiNhanh (maChiNhanh varchar(255) not null, diaChi varchar(255), moTa varchar(255), tenChiNhanh varchar(255), thoiGianDongCua date, thoiGianMoCua date, trangThaiHoatDong varchar(255), cccdQuanTriVien varchar(255), primary key (maChiNhanh)) 
create table ChuaMonAn (maMonAn varchar(255) not null, maUuDai varchar(255) not null) 
create table ChuaThongBao (maThongBao varchar(255) not null, cccd varchar(255) not null) 
create table DanhGia (maDanhGia varchar(255) not null, cccdKhachHang varchar(255), maMonAn varchar(255), noiDung varchar(255), thoiGian date, primary key (maDanhGia)) 
create table DonGiaoHang (maDonGiaoHang varchar(255) not null, diaChiNhan varchar(255), ghiChu varchar(255), khoangCach decimal(38,2), phiVanChuyen decimal(38,2), tenNguoiNhan varchar(255), thoiGianGiaoDuKien date, thoiGianNhanThucTe date, tinhTrangDonHang varchar(255), maDon varchar(255), primary key (maDonGiaoHang)) 
create table DonKhieuNai (maDonKhieuNai varchar(255) not null, noiDung varchar(255), thoiGian date, cccdKhachHang varchar(255), cccdNhanVienQuanLy varchar(255), donMonAn varchar(255), primary key (maDonKhieuNai)) 
create table DonMonAn (maDon varchar(255) not null, phuongThucThanhToan varbinary(255), thoiGianDat date, tinhTrangThanhToan varchar(255), tongGiaTien decimal(38,2), cccdKhachHang varchar(255), cccdNhanVienThuNgan varchar(255), maChiNhanh varchar(255), primary key (maDon)) 
create table DonTaiQuan (maDonTaiQuan varchar(255) not null, yeuCauCuaKhachHang varchar(255), maDon varchar(255), maNhanVienPhucVu varchar(255), primary key (maDonTaiQuan)) 
create table KhachHang (
    maKhachHang     varchar(255) not null, 
    loaiKhachHang   varchar(255), 
    ngayThamGia     date, 
    soDonDaDat      integer not null, 
    soDonDaHuy      integer not null, 
    cccd            varchar(255), primary key (maKhachHang));
create table MaKhuyenMai (idKhuyenMai varchar(255) not null, dieuKienDung varchar(255), giaTriGiamGia decimal(38,2), moTa varchar(255), maChiNhanh varchar(255), maDon varchar(255), primary key (idKhuyenMai)) 
create table MaKhuyenMaiKhachHang (maMaKhuyenMaiKhachHang varchar(255) not null, soLuong integer not null, maKhuyenMai varchar(255), primary key (maMaKhuyenMaiKhachHang)) 
create table MonAn (maMonAn varchar(255) not null, anhMonAn varchar(255), gia decimal(38,2), khauPhan varchar(255), loaiMonAn varchar(255), moTa varchar(255), tenMonAn varchar(255), thoiGianHoanTat varchar(255), primary key (maMonAn)) 
create table MonAnChiNhanh (maMonAnChiNhanh varchar(255) not null, soLuongMonAn integer not null, monAn varchar(255), primary key (maMonAnChiNhanh)) 
create table MonAnGioHang (maMonAnGioHang varchar(255) not null, soLuong integer not null, monAn varchar(255), primary key (maMonAnGioHang)) 
create table MonAnThuocVe (maChiNhanh varchar(255) not null, maMonAnChiNhanh varchar(255) not null) 
create table NhanVien (maNhanVien varchar(255) not null, luong decimal(38,2), ngayVaoLam date, cccd varchar(255), cccdNhanVienQuanLy varchar(255), maChiNhanh varchar(255), primary key (maNhanVien)) 
create table NhanVienGiaoHang (maNhanVienGiaoHang varchar(255) not null, tinhTrangHoatDong varchar(255), cccd varchar(255), primary key (maNhanVienGiaoHang)) 
create table NhanVienPhucVu (maNhanVienPhucVu varchar(255) not null, maNhanVien varchar(255), primary key (maNhanVienPhucVu)) 
create table NhanVienQuanLy (maNhanVienQuanLy varchar(255) not null, cccd varchar(255), chiNhanh varchar(255), primary key (maNhanVienQuanLy)) 
create table NhanVienThuNgan (cccd varchar(255) not null, primary key (cccd)) 
create table NhanVienTongDai (maNhanVienTongDai varchar(255) not null, cccd varchar(255), primary key (maNhanVienTongDai)) 
create table PhuongTien (bienSoXe varchar(255) not null, giayphepSoHuu varchar(255), loaiPhuongTien varchar(255), thoiGianDangKy date, thoiGianHetHan date, thongTinDangKy varchar(255), primary key (bienSoXe)) 
create table QuanTriVien (cccd varchar(255) not null, moTaVaiTro varchar(255), primary key (cccd)) 
create table SoDienThoai (cccd varchar(255) not null, soDienThoai varchar(255)) 
create table SoHuu (cccd varchar(255) not null, maMaKhuyenMaiKhachHang varchar(255) not null) 
create table SuDung (cccd varchar(255) not null, bienSoXe varchar(255) not null) 
create table SuKienUuDai (maUuDai varchar(255) not null, giaTriGiamGia decimal(38,2), loaiMa varchar(255), maChiNhanh varchar(255), primary key (maUuDai)) 
create table ThoiGianSuKien (maUuDai varchar(255) not null, thoiGianSuKien varchar(255)) 
create table ThongBao (maThongBao varchar(255) not null, noiDungThongBao varchar(255), thoiGian datetime(6), cccdNhanVienQuanLy varchar(255), cccdQuanTriVien varchar(255), primary key (maThongBao)) 

alter table DonGiaoHang add constraint UKt8mi8tvehyi2sp429ouea2pa2 unique (maDon)
alter table DonTaiQuan drop index UKl7c0j5qn7c2ftjlurkmjsl5ts
alter table DonTaiQuan add constraint UKl7c0j5qn7c2ftjlurkmjsl5ts unique (maDon)
alter table KhachHang drop index UKbus2p18gnv7xytetx0wff4p05
alter table KhachHang add constraint UKbus2p18gnv7xytetx0wff4p05 unique (cccd)
alter table NhanVien drop index UKanbx41agsrtnmb0pg1eh618bu
alter table NhanVien add constraint UKanbx41agsrtnmb0pg1eh618bu unique (cccd)
alter table NhanVienGiaoHang drop index UKixrgp6e42dyycfuj39tlctqoj
alter table NhanVienGiaoHang add constraint UKixrgp6e42dyycfuj39tlctqoj unique (cccd)
alter table NhanVienPhucVu drop index UKo16ld1kr11t0cn3lui66g5332
alter table NhanVienPhucVu add constraint UKo16ld1kr11t0cn3lui66g5332 unique (maNhanVien)
alter table NhanVienQuanLy drop index UK36vfeuucuc2hj1n3u0vv8h02a
alter table NhanVienQuanLy add constraint UK36vfeuucuc2hj1n3u0vv8h02a unique (cccd)
alter table NhanVienTongDai drop index UKbnnyhwnyh2wsuvneqdkr1xtqv
alter table NhanVienTongDai add constraint UKbnnyhwnyh2wsuvneqdkr1xtqv unique (cccd)
alter table BangLaiXe add constraint FKlj9ko1jvi03qpf8qcnj7iq5pc foreign key (cccd) references NhanVienGiaoHang (maNhanVienGiaoHang)
alter table BaoGom add constraint FKknkuuxahliswu0flllr5t2eai foreign key (maMonAnGioHang) references MonAnGioHang (maMonAnGioHang)
alter table BaoGom add constraint FK63ptcenpjmi5uadwgx9lgm45h foreign key (maDon) references DonMonAn (maDon)
alter table ChiNhanh add constraint FK2jkypsjsjwa0ah2u75kldx1s foreign key (cccdQuanTriVien) references QuanTriVien (cccd)
alter table ChuaMonAn add constraint FKm8x7rf25u6wj4xuaplarkin2o foreign key (maUuDai) references SuKienUuDai (maUuDai)
alter table ChuaMonAn add constraint FKifom9ij87y70xm9in782m1kon foreign key (maMonAn) references MonAn (maMonAn)
alter table ChuaThongBao add constraint FKsmeugafw7uumvfrstm4y6q26k foreign key (cccd) references ThongTin (cccd)
alter table ChuaThongBao add constraint FKcatuvx3jmoji7wvf6u08g6shk foreign key (maThongBao) references ThongBao (maThongBao)
alter table DonGiaoHang add constraint FKqlux3slwhmfmfw010a012at4k foreign key (maDon) references DonMonAn (maDon)
alter table DonKhieuNai add constraint FKcc15r2u3xaecqm85oq8b5hc9g foreign key (cccdKhachHang) references KhachHang (maKhachHang)
alter table DonKhieuNai add constraint FKldvu1lihifpdwh3rkja6x25uo foreign key (cccdNhanVienQuanLy) references NhanVienQuanLy (maNhanVienQuanLy)
alter table DonKhieuNai add constraint FKo3fux1k4lulaojhfwo2ysweme foreign key (donMonAn) references DonMonAn (maDon)
alter table DonMonAn add constraint FKe1adt4sqmetxsoacpd2c38d4p foreign key (cccdKhachHang) references KhachHang (maKhachHang)
alter table DonMonAn add constraint FK64bm28s9dbivpla0ujbhy61se foreign key (cccdNhanVienThuNgan) references NhanVienThuNgan (cccd)
alter table DonMonAn add constraint FKcouqa2nl0f0t2jjr7r8gootyq foreign key (maChiNhanh) references ChiNhanh (maChiNhanh)
alter table DonTaiQuan add constraint FKbssppyr677n0pni4nnlgmdrjr foreign key (maDon) references DonMonAn (maDon)
alter table DonTaiQuan add constraint FK3nuax8d6qnsfdwqk03fqw111h foreign key (maNhanVienPhucVu) references NhanVienPhucVu (maNhanVienPhucVu)
alter table KhachHang add constraint FK15vxa81r1efnjdfwb33yxlt0w foreign key (cccd) references ThongTin (cccd)
alter table MaKhuyenMai add constraint FKslmvvkihd2xi410eigyfybnkr foreign key (maChiNhanh) references ChiNhanh (maChiNhanh)
alter table MaKhuyenMai add constraint FKs8wuiwqf8elog4xn3iwb0chot foreign key (maDon) references DonMonAn (maDon)
alter table MaKhuyenMaiKhachHang add constraint FKmbk8pfym9dlgwsy14hoetvmh6 foreign key (maKhuyenMai) references MaKhuyenMai (idKhuyenMai)
alter table MonAnChiNhanh add constraint FK5k53ixb7ktpwkltl6y0mlq5vt foreign key (monAn) references MonAn (maMonAn)
alter table MonAnGioHang add constraint FKpeugvv2i7vp3qcp1vk583xpsk foreign key (monAn) references MonAn (maMonAn)
alter table MonAnThuocVe add constraint FKb0at1mw1lht9bwgwxe8wt50tj foreign key (maMonAnChiNhanh) references MonAnChiNhanh (maMonAnChiNhanh)
alter table MonAnThuocVe add constraint FKfsoqdgrbd4lvr854mgs86i2rm foreign key (maChiNhanh) references ChiNhanh (maChiNhanh)
alter table NhanVien add constraint FKajgwrq2whir1ivv8l856gnu6q foreign key (cccd) references ThongTin (cccd)
alter table NhanVien add constraint FK2ityf7iuem3x1n91gldp9b5wg foreign key (cccdNhanVienQuanLy) references NhanVienQuanLy (maNhanVienQuanLy)
alter table NhanVien add constraint FK28hx44xuecl3m2k3428q5e1y foreign key (maChiNhanh) references ChiNhanh (maChiNhanh)
alter table NhanVienGiaoHang add constraint FKj8cf8svt3xc76t1dfxnmdthlr foreign key (cccd) references NhanVien (maNhanVien)
alter table NhanVienPhucVu add constraint FKbl6492qe87ueen0v7tlnql6t9 foreign key (maNhanVien) references NhanVien (maNhanVien)
alter table NhanVienQuanLy add constraint FKn4yyjif34aeogd72hncc0cbc4 foreign key (cccd) references NhanVien (maNhanVien)
alter table NhanVienQuanLy add constraint FKimonxfi0g460vdt9lf77078rj foreign key (chiNhanh) references ChiNhanh (maChiNhanh)
alter table NhanVienTongDai add constraint FK8d20wcmttid119jby0avwgcdx foreign key (cccd) references NhanVien (maNhanVien)
alter table SoDienThoai add constraint FKqi5ggq01r6jvmapdmvagnm9n2 foreign key (cccd) references ThongTin (cccd)
alter table SoHuu add constraint FKm0kgdfbxbno7teyp87jcieyy2 foreign key (maMaKhuyenMaiKhachHang) references MaKhuyenMaiKhachHang (maMaKhuyenMaiKhachHang)
alter table SoHuu add constraint FK6fm1peq5f8fl6t11olublcd0i foreign key (cccd) references KhachHang (maKhachHang)
alter table SuDung add constraint FKgmcjbh7qjggdu4dnwso4emo8j foreign key (bienSoXe) references PhuongTien (bienSoXe)
alter table SuDung add constraint FKq17l13pwmax8uqc5h5fgppjx1 foreign key (cccd) references NhanVienGiaoHang (maNhanVienGiaoHang)
alter table SuKienUuDai add constraint FKanrce0khp8hnsk4hw8olivl3p foreign key (maChiNhanh) references ChiNhanh (maChiNhanh)
alter table ThoiGianSuKien add constraint FK17hk5va7t7nug3mdr8gu3l14w foreign key (maUuDai) references SuKienUuDai (maUuDai)
alter table ThongBao add constraint FK7otoihjkegaekxj1us66q15xg foreign key (cccdNhanVienQuanLy) references NhanVienQuanLy (maNhanVienQuanLy)
alter table ThongBao add constraint FKn8d96pneihy47uqwidg60tpq0 foreign key (cccdQuanTriVien) references QuanTriVien (cccd)
