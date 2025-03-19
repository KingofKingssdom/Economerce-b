Đây là backend của dự án E-Commerce, được phát triển bằng Spring Boot với MySQL.

## Công nghệ sử dụng
- Java 17
- Spring Boot 3.x
- Spring Security 
- MySQL
- Hibernate

## Cấu hình database 
- CREATE DATABASE ecommerce;
- Import file database.sql vào MySQL
  mysql -u root -p ecommerce < src/main/resources/database/database.sql

## Cấu hình file application.properties
- Mở file src/main/resources/application.properties
- Cập nhật thông tin kết nối MySQL
  spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
  spring.datasource.username=root
  spring.datasource.password=yourpassword
- mvn spring-boot:run

## Thông tin thanh toán VNPay trong môi trường test
- Chọn thanh toán nội địa theo ngân hàng
  - Điền thông tin chuyển khoản như sau:
    Ngân hàng     : NCB
    Số thẻ        : 9704198526191432198
    Tên chủ thẻ   : NGUYEN VAN A
    Ngày phát hành: 07/15
    Mật khẩu OTP  : 123456