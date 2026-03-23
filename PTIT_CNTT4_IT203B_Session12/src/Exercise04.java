// Phần 1: Phân tích
// Lỗi: Mỗi lần lặp đều tạo Statement mới -> DB phải parse và tạo execution plan lại nhiều lần -> rất chậm
// PreparedStatement giúp biên dịch 1 lần, tái sử dụng nhiều lần -> giảm tải DB, tăng tốc độ đáng kể
// Phần 2: Thực thi

import java.sql.*;
import java.util.List;

class TestResult {
    public String getData() {
        return "sample_data";
    }
}

public class Exercise04 {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/session12";
        String user = "root";
        String password = "12345678";

        List<TestResult> list = List.of(new TestResult(), new TestResult(), new TestResult());

        String sql = "INSERT INTO Results(data) VALUES(?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (TestResult tr : list) {
                ps.setString(1, tr.getData());
                ps.executeUpdate(); // dùng lại PreparedStatement
            }

            System.out.println("Insert thành công");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}