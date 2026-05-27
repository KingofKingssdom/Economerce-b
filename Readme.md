# 🛠️ Backend API — Spring Boot

Đây là dự án Backend được xây dựng bằng **Spring Boot**, sử dụng **Spring Security**, **MySQL**, và tích hợp thanh toán **VNPAY Sandbox**.

---

## 🚀 Công nghệ sử dụng

- ☕ **Java 17 (JDK 17)**
- 🌱 **Spring Boot 3.5.5**
- 🔐 **Spring Security**
- 🧩 **Spring Data JPA**
- 🗄️ **MySQL**
- 💳 **VNPay Sandbox**


---
Cấu hình database
- CREATE DATABASE ecommerce;
- Import file database.sql vào MySQL
  mysql -u root -p ecommerce < src/demo/database.sql

👤 Tài khoản test:
email: vanan@gmail.com
Password: 123
---
⚙️Tạo File .env Cho Backend
Trong thư mục gốc backend, tạo file .env
Cấu hình file .env
VNPAY_TMN_CODE= <your_vnpay_tmn_code>
VNPAY_HASH_SECRET=<your_vnpay_hash_secret>

DATASOURCE_URL= jdbc:mysql://localhost:<your_port>/economerce
DATASOURCE_USERNAME=<your_user>
DATASOURCE_PASSWORD=<your_password>
---
💳 IV. Hướng Dẫn Tạo Tài Khoản & Sử Dụng VNPay Sandbox
🌍 1. Thông tin thanh toán VNPay môi trường test
🔧 Đăng ký tài khoản sandbox

Truy cập:

👉 https://sandbox.vnpayment.vn/devreg

Điền các thông tin:

Tên website: demothanhtoanonline123

Địa chỉ url: demothanhtoanonline123.com

Email đăng ký: nhập email bạn đang dùng

Mật khẩu: tuỳ bạn tạo

📩 2. Kiểm tra email

Sau khi đăng ký, VNPay gửi email chứa:
* vnp_TmnCode
* Secret Key
---
🏦 Thông tin test giao dịch VNPay
Ngân hàng       : NCB
Số thẻ          : 9704198526191432198
Tên chủ thẻ     : NGUYEN VAN A
Ngày phát hành  : 07/15
Mật khẩu OTP    : 123456
---
## 📂 Cấu trúc dự án

```bash
src/
├── main/
│   ├── java/...          # Code nguồn backend
│   ├── resources/
│   │   ├── application.properties 
│   │   ├── static/
│   │   └── templates/
└── data/
    └── demo_database.sql     # File demo database (MySQL)

