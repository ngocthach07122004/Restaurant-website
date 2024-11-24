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
