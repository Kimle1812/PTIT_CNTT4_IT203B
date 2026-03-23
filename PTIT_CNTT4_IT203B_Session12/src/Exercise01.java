// Phần 1: Phân tích
// Lỗi: Nối chuỗi SQL trực tiếp với dữ liệu người dùng khiến hacker có thể chèn mã độc (SQL Injection)
// Ví dụ nhập ' OR '1'='1 sẽ làm điều kiện luôn đúng và đăng nhập trái phép
// Phần 2: Thực thi
import java.sql.*;

public class Exercise01 {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/session12";
        String user = "root";
        String password = "12345678";

        String code = "doctor01";   // giả lập input
        String pass = "123456";     // thử đổi thành: ' OR '1'='1

        String sql = "SELECT * FROM Doctors WHERE code = ? AND pass = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Gán giá trị an toàn
            ps.setString(1, code);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Đăng nhập thành công");
            } else {
                System.out.println("Sai tài khoản hoặc mật khẩu");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}