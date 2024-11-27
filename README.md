<!-- Nhớ thêm dòng này ở bảng ThongTin để so sánh chuỗi có phân biệt hoa thường -->

ALTER TABLE ThongTin
MODIFY COLUMN tenDangNhap VARCHAR(100) COLLATE utf8mb4_bin;

# run database

-pull mysql in docker hub

docker pull mysql

-start mysql server

1. docker run --name restaurant-service-db -e MYSQL_ROOT_PASSWORD=12345678 -p 3307:3306 -d mysql:latest

2. docker exec -it restaurant-service-db bash

3. mysql -u root -p  
   enter your password
4. create dabase restaurant_service_database_v1

# run database

CREATE TABLE ThongTin (
cccd VARCHAR(100) PRIMARY KEY,

    tenDangNhap 		VARCHAR(100) NOT NULL,
    matKhau 			VARCHAR(100) NOT NULL,
    ho 				VARCHAR(100),
    ten 				VARCHAR(100),
    ngaySinh 			DATE,
    email 				VARCHAR(100),
    gioiTinh 			VARCHAR(1),
    soDienThoai 			VARCHAR(100),
    maTaiKhoan 			VARCHAR(100),
    cccdQuanTriVien 		VARCHAR(100),
    anhThongTin          VARCHAR(800)
    -- FOREIGN KEY (cccd_QuanTriVien) REFERENCES ThongTin(cccd)

);

CREATE TABLE MonAn (
maMonAn VARCHAR(100) PRIMARY KEY,
gia DECIMAL(15,2),
tenMonAn VARCHAR(100),
moTa VARCHAR(100),
loaiMonAn VARCHAR(100),
anhMonAn VARCHAR(800)
);
