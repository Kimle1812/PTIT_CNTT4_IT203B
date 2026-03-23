
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

// Phần 1: Phân tích
//Trong JDBC, phương thức executeUpdate() trả về một số nguyên biểu thị số dòng (rows) trong bảng bị tác động bởi câu lệnh SQL.
//Nếu cập nhật thành công cho một giường, giá trị trả về sẽ là 1 (một dòng được thay đổi).
//Nếu mã giường không tồn tại trong bảng, câu lệnh UPDATE không tìm thấy bản ghi nào phù hợp, nên executeUpdate() trả về 0.

// Phần 2: Thực thi
public class Exercise03 {
    private static final String URL = "jdbc:mysql://localhost:3306/session11";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    public static void main (String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "UPDATE beds SET bed_status = 'Đang sử dụng' WHERE bed_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "B001");

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Cập nhật thành công: Giường đã chuyển sang trạng thái 'Đang sử dụng'.");
            } else {
                System.out.println("Lỗi: Mã giường không tồn tại trong hệ thống.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (Exception ignored) {}
            try {
                if (conn != null) conn.close();
            } catch (Exception ignored) {}
        }
    }
}