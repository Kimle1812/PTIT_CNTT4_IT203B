

//  Phần 1 - Phân tích:
//  - Nếu khởi tạo kết nối liên tục mà không đóng (close):
//  + Gây rò rỉ kết nối, chiếm hết tài nguyên hệ thống.
//  + Sau vài giờ, số lượng kết nối vượt quá giới hạn của MySQL → lỗi Communications link failure.
//  + Hệ thống bệnh viện cần hoạt động 24/7, nên việc treo/mất kết nối sẽ ảnh hưởng trực tiếp đến truy xuất hồ sơ bệnh nhân.
//  - Giải pháp:
//  + Quản lý kết nối tập trung (Connection Pool).
//  + Đảm bảo mọi truy vấn đều đóng kết nối trong khối finally.

// Phần 2 - Thực thi:
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Exercise01 {
    // Hằng số cấu hình
    private static final String URL = "jdbc:mysql://localhost:3306/session11";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    // Hàm lấy kết nối
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Ví dụ phương thức truy vấn SELECT với PreparedStatement
    public void getPatientById(int patientId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "SELECT * FROM Patients WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, patientId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("Patient Name: " + rs.getString("name"));
                System.out.println("DOB: " + rs.getDate("dob"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo đóng tài nguyên
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}


