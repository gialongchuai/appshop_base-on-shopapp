import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-register',
  standalone: false,
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  errorMessage: string = '';
  loading: boolean = false;
  
  // Inject HttpClient để gọi API
  constructor(private http: HttpClient) {}

  onRegister() {
    // Dữ liệu cứng tương ứng với JSON bạn cung cấp
    const registerData = {
      fullname: "Nguyen Van A",
      phoneNumber: "114",
      address: "123 Đường ABC, Quận 1, TP.HCM",
      password: "123123",
      createdAt: "2025-03-05",
      updatedAt: "2025-03-05",
      isActive: true,
      dateOfBirth: "1995-06-15",
      facebookAccountId: 1,
      googleAccountId: 1
    };

    this.loading = true;
    this.errorMessage = '';

    // Gọi API
    this.http.post('http://localhost:8080/shopapp/users/registration', registerData)
      .subscribe({
        next: (response) => {
          this.loading = false;
          console.log('Đăng ký thành công:', response);
          // Có thể thêm thông báo thành công nếu muốn
        },
        error: (error) => {
          this.loading = false;
          // Xử lý lỗi từ backend
          if (error.error && error.error.message) {
            this.errorMessage = error.error.message;
          } else {
            this.errorMessage = 'Đã xảy ra lỗi khi đăng ký. Vui lòng thử lại.';
          }
          console.error('Lỗi đăng ký:', error);
        }
      });
  }
}