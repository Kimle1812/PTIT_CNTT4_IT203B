
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Phần 1: Phân tích
//- Khi dùng if (rs.next()), con trỏ của ResultSet chỉ di chuyển một lần từ vị trí ban đầu
// (trước dòng đầu tiên) sang dòng đầu tiên. Sau đó, khối lệnh trong if chỉ thực thi đúng một lần,
// nên chỉ in ra được bản ghi đầu tiên. Nếu bảng trống, rs.next() trả về false, khối if không chạy,
// dẫn đến không có dữ liệu in ra (thậm chí có thể gây lỗi nếu vẫn cố truy xuất cột).
//- Để in toàn bộ danh sách, ta cần một vòng lặp (while) để duyệt qua tất cả các dòng.
// Mỗi lần gọi rs.next(), con trỏ sẽ di chuyển sang bản ghi kế tiếp cho đến khi hết dữ liệu (trả về false).

// Phần 2: Thực thi
public class Exercise02 {
    private static final String URL = "jdbc:mysql://localhost:3306/session11";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT name, quantity FROM medicines";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            System.out.println("=== Danh mục thuốc trong kho ===");
            while (rs.next()) {
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                System.out.println("Tên thuốc: " + name + " | Số lượng tồn: " + quantity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (Exception ignored) {}
            try {
                if (stmt != null) stmt.close();
            } catch (Exception ignored) {}
            try {
                if (conn != null) conn.close();
            } catch (Exception ignored) {}
        }
    }
}
