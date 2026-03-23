//Phần 1: Phân tích
// Lỗi: Nối chuỗi trực tiếp khiến số thực (double) bị sai định dạng theo vùng miền (37,5 vs 37.5)
// Dùng PreparedStatement với setDouble(), setInt() giúp DB tự xử lý đúng định dạng, không bị lỗi
//Phần 2: Thực thi
import java.sql.*;

public class Exercise02 {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/session12";
        String user = "root";
        String password = "12345678";

        double temp = 37.5;   // nhiệt độ
        int heartRate = 80;   // nhịp tim
        int patientId = 1;

        String sql = "UPDATE Vitals SET temperature = ?, heart_rate = ? WHERE p_id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, temp);
            ps.setInt(2, heartRate);
            ps.setInt(3, patientId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Cập nhật thành công");
            } else {
                System.out.println("Không tìm thấy bệnh nhân");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}