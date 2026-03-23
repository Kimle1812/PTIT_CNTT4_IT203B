
//  Bài tập: Tra cứu bệnh nhân & Hiểm họa SQL Injection
//  Phần 1 - Phân tích:
// Khi người dùng nhập thêm đoạn OR '1'='1', mệnh đề WHERE trở thành luôn đúng vì '1'='1' là một biểu thức logic đúng (true).
// Kết quả: truy vấn không chỉ trả về bệnh nhân có tên cụ thể, mà trả về toàn bộ bảng Patients.

// Phần 2 - Thực thi:
 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Exercise04 {
    private static final String URL = "jdbc:mysql://localhost:3306/session11";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    // Hàm lấy kết nối
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Hàm lọc input: loại bỏ ký tự nguy hiểm
    private String sanitizeInput(String input) {
        if (input == null) return "";
        return input.replaceAll("[';--]", "");
    }

    // Truy vấn bệnh nhân theo tên (an toàn)
    public void findPatientByName(String name) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String safeName = sanitizeInput(name);
            String sql = "SELECT * FROM Patients WHERE full_name = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, safeName);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("Patient: " + rs.getString("full_name"));
                System.out.println("DOB: " + rs.getDate("dob"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
