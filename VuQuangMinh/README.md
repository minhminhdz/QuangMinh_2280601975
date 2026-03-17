# Course Registration Application - Hệ thống Đăng ký Học phần

Ứng dụng quản lý và đăng ký học phần cho sinh viên, được xây dựng bằng **Spring Boot**.

## 📋 Yêu cầu hệ thống

- Java 17 hoặc cao hơn
- MySQL 8.0 hoặc cao hơn
- Maven 3.6+

## 🚀 Hướng dẫn cài đặt

### 1. Cấu hình Database

Tạo database MySQL:
```sql
CREATE DATABASE course_registration;
```

### 2. Cấu hình application.properties

Mở file `src/main/resources/application.properties` và cập nhật thông tin database:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/course_registration?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

### 3. Cấu hình Google OAuth2 (Câu 9)

Để sử dụng đăng nhập bằng Google:

1. Truy cập [Google Cloud Console](https://console.cloud.google.com/)
2. Tạo project mới
3. Bật Google+ API
4. Tạo OAuth 2.0 credentials
5. Thêm Authorized redirect URIs: `http://localhost:8080/login/oauth2/code/google`
6. Cập nhật trong `application.properties`:

```properties
spring.security.oauth2.client.registration.google.client-id=YOUR_CLIENT_ID
spring.security.oauth2.client.registration.google.client-secret=YOUR_CLIENT_SECRET
```

### 4. Chạy ứng dụng

```bash
mvn spring-boot:run
```

Hoặc chạy từ IDE (IntelliJ IDEA, Eclipse):
- Chạy class `CourseRegistrationApplication.java`

Ứng dụng sẽ chạy tại: **http://localhost:8080**

## 👤 Tài khoản mẫu

Hệ thống tự động tạo sẵn các tài khoản:

**ADMIN:**
- Username: `admin`
- Password: `admin123`

**STUDENT:**
- Username: `student1`
- Password: `student123`

## ✅ Các chức năng đã hoàn thành

### Câu 1 (2.5đ): Trang Home
- ✅ Hiển thị danh sách học phần (tên, số tín chỉ, giảng viên, hình ảnh)
- ✅ Phân trang (5 học phần/trang)

### Câu 2 (1.5đ): CRUD học phần cho ADMIN
- ✅ Create Course
- ✅ Update Course
- ✅ Delete Course
- URL: `/admin/courses`

### Câu 3 (1đ): Đăng ký tài khoản
- ✅ Form đăng ký (username, password, email)
- ✅ Quyền mặc định là STUDENT
- URL: `/register`

### Câu 4 (0.5đ): Spring Security
- ✅ `/admin/**` - chỉ ADMIN
- ✅ `/courses` - tất cả người dùng
- ✅ `/enroll/**` - chỉ STUDENT

### Câu 5 (0.5đ): Đăng nhập
- ✅ Login bằng username/password
- ✅ Redirect đến `/home` sau khi đăng nhập
- URL: `/login`

### Câu 6 (1đ): Đăng ký học phần
- ✅ Nút "Enroll" trong danh sách học phần
- ✅ Chỉ STUDENT mới được đăng ký

### Câu 7 (1đ): My Courses
- ✅ Hiển thị học phần đã đăng ký
- URL: `/enroll/my-courses`

### Câu 8 (0.5đ): Tìm kiếm
- ✅ Tìm kiếm học phần theo tên trên Header
- ✅ Kết quả hiển thị đúng từ khóa

### Câu 9 (1đ): OAuth2 Login
- ✅ Đăng nhập bằng Google
- ✅ Tự động tạo tài khoản với quyền STUDENT

### Câu 10 (0.5đ): Giao diện
- ✅ Giao diện đẹp với Bootstrap 5
- ✅ Responsive trên mobile/tablet/desktop
- ✅ Icons với Font Awesome

## 📁 Cấu trúc dự án

```
src/main/java/com/courseregistration/
├── config/
│   ├── DataInitializer.java          # Khởi tạo dữ liệu mẫu
│   └── SecurityConfig.java            # Cấu hình bảo mật
├── controller/
│   ├── AdminController.java           # CRUD học phần
│   ├── AuthController.java            # Đăng ký/Đăng nhập
│   ├── EnrollmentController.java      # Đăng ký học phần
│   └── HomeController.java            # Trang chủ
├── dto/
│   └── StudentRegistrationDto.java    # DTO đăng ký
├── entity/
│   ├── Category.java
│   ├── Course.java
│   ├── Enrollment.java
│   ├── Role.java
│   └── Student.java
├── repository/
│   ├── CategoryRepository.java
│   ├── CourseRepository.java
│   ├── EnrollmentRepository.java
│   ├── RoleRepository.java
│   └── StudentRepository.java
├── security/
│   ├── CustomOAuth2UserService.java   # OAuth2 service
│   └── CustomUserDetailsService.java  # User details service
└── service/
    ├── CategoryService.java
    ├── CourseService.java
    ├── EnrollmentService.java
    └── StudentService.java

src/main/resources/
├── templates/
│   ├── admin/
│   │   ├── courses.html               # Quản lý học phần
│   │   └── course-form.html           # Form thêm/sửa
│   ├── home.html                      # Trang chủ
│   ├── login.html                     # Đăng nhập
│   ├── my-courses.html                # Học phần đã đăng ký
│   └── register.html                  # Đăng ký tài khoản
└── application.properties             # Cấu hình
```

## 🎯 Các tính năng nổi bật

1. **Bảo mật với Spring Security**
   - Authentication & Authorization
   - Password encryption với BCrypt
   - OAuth2 integration

2. **Responsive Design**
   - Bootstrap 5
   - Mobile-friendly
   - Font Awesome icons

3. **Database**
   - JPA/Hibernate
   - MySQL
   - Auto schema generation

4. **User Experience**
   - Flash messages
   - Form validation
   - Pagination
   - Search functionality

## 📝 Ghi chú

- Tất cả 10 câu hỏi đã được hoàn thành đầy đủ
- Dữ liệu mẫu tự động được tạo khi chạy lần đầu
- Giao diện responsive, hoạt động tốt trên mọi thiết bị
- Code được tổ chức rõ ràng, dễ mở rộng

## 🔧 Troubleshooting

**Lỗi kết nối database:**
- Kiểm tra MySQL đã chạy chưa
- Kiểm tra username/password trong `application.properties`

**OAuth2 không hoạt động:**
- Kiểm tra client-id và client-secret
- Kiểm tra redirect URI đã đúng chưa

## 📧 Liên hệ

Sinh viên: Nguyễn Đỗ Hoàng Phúc
MSSV: 2280602442
