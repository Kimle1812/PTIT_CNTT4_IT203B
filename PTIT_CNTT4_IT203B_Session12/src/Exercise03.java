// Phần 1: Phân tích
// Phải gọi registerOutParameter() để JDBC biết tham số nào là OUTPUT và kiểu dữ liệu của nó
// Nếu không đăng ký sẽ lỗi "column index is out of range"; kiểu DECIMAL trong SQL dùng Types.DECIMAL trong Java
// Phần 2: Thực thi
import java.sql.*;

public class Exercise03 {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/session12";
        String user = "root";
        String password = "12345678";

        int surgeryId = 505;

        String sql = "{call GET_SURGERY_FEE(?, ?)}";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             CallableStatement cs = conn.prepareCall(sql)) {

            // IN parameter
            cs.setInt(1, surgeryId);

            // OUT parameter (DECIMAL)
            cs.registerOutParameter(2, Types.DECIMAL);

            cs.execute();

            double cost = cs.getDouble(2);

            System.out.println("Chi phí phẫu thuật: " + cost);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}