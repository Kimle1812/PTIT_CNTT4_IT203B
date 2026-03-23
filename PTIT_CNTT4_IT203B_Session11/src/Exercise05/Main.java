
package Exercise05;

import java.util.Scanner;

// Phần 1: Các kịch bản lỗi có thể xảy ra
//- Nhập chuyên khoa quá dài: Ví dụ cột specialization trong DB chỉ cho phép 50 ký tự, nhưng người dùng nhập 100 ký tự → lỗi Data too long for column.
//- Nhập trùng mã bác sĩ (Primary Key): Nếu cột doctor_id là khóa chính, nhập lại một mã đã tồn tại sẽ gây lỗi Duplicate entry.
//- Sai định dạng ngày tháng (nếu có cột ngày sinh hoặc ngày vào làm): Nhập 31-02-2024 hoặc nhập chữ thay vì định dạng YYYY-MM-DD → lỗi Incorrect date value.
//- Kết nối DB bị gián đoạn: Nếu DB server ngắt kết nối, mọi thao tác sẽ báo lỗi Communications link failure.

// Phần 2: Thực thi
public class Main {
    public static void main(String[] args) {
        DoctorService service = new DoctorService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Menu Quản lý Bác sĩ Trực ca ===");
            System.out.println("1. Xem danh sách bác sĩ");
            System.out.println("2. Thêm bác sĩ mới");
            System.out.println("3. Thống kê chuyên khoa");
            System.out.println("4. Thoát");
            System.out.print("Chọn chức năng: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    service.showAllDoctors();
                    break;
                case 2:
                    System.out.print("Nhập mã bác sĩ: ");
                    String id = sc.nextLine();
                    System.out.print("Nhập họ tên: ");
                    String name = sc.nextLine();
                    System.out.print("Nhập chuyên khoa: ");
                    String specialization = sc.nextLine();
                    service.addDoctor(id, name, specialization);
                    break;
                case 3:
                    service.showStatistic();
                    break;
                case 4:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
