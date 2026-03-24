
import java.sql.*;

// Phần 1: Phân tích
// - AutoCommit phải tắt (conn.setAutoCommit(false)), chỉ commit khi cả 3 bước thành công.
// - Bẫy 1 – Thiếu tiền: Trước khi trừ tiền, phải SELECT balance từ patient_wallet.
// Nếu balance < tienVienPhi → ném Exception, rollback ngay.
// - Bẫy 2 – Row Affected = 0: Sau mỗi executeUpdate(), kiểm tra giá trị trả về.
// Nếu bằng 0 → nghĩa là không có dòng nào bị ảnh hưởng (mã bệnh nhân/giường không tồn tại).
// Phải ném Exception và rollback.

public class Exercise03 {
    public static void dischargeAndPayment(int patientId, double hospitalFee) throws Exception {
        String DB_URL = "jdbc:mysql://localhost:3306/rikkei_hospital";
        String USER = "root";
        String PASS = "12345678";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            connection.setAutoCommit(false);

            String sqlCheck = "select balance, bed_id from patient_wallet where patient_id = ?";
            statement = connection.prepareStatement(sqlCheck);
            statement.setInt(1, patientId);
            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                throw new Exception("Bệnh nhân không tồn tại!");
            }

            double balance = resultSet.getDouble("balance");
            int bedId = resultSet.getInt("bed_id");

            if (balance < hospitalFee) {
                throw new Exception("Số dư không đủ để thanh toán viện phí!");
            }

            String sql1 = "update patient_wallet set balance = balance - ? where patient_id = ?";
            statement = connection.prepareStatement(sql1);
            statement.setDouble(1, hospitalFee);
            statement.setInt(2, patientId);
            int affected1 = statement.executeUpdate();
            if (affected1 == 0) throw new Exception("Không cập nhật được số dư!");

            String sql2 = "update hospital_beds set status = 'trống' where bed_id = ?";
            statement = connection.prepareStatement(sql2);
            statement.setInt(1, bedId);
            int affected2 = statement.executeUpdate();
            if (affected2 == 0) throw new Exception("Không cập nhật được trạng thái giường!");

            String sql3 = "update patients set status = 'đã xuất viện' where patient_id = ?";
            statement = connection.prepareStatement(sql3);
            statement.setInt(1, patientId);
            int affected3 = statement.executeUpdate();
            if (affected3 == 0) throw new Exception("Không cập nhật được trạng thái bệnh nhân!");

            connection.commit();
            System.out.println("Thanh toán & xuất viện thành công!");

        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback(); // rollback bắt buộc
                    System.out.println("Giao dịch đã rollback do lỗi: " + e.getMessage());
                } catch (SQLException ex) {
                    System.out.println("Lỗi khi rollback: " + ex.getMessage());
                }
            }
            throw e;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            dischargeAndPayment(1, 500000);
        } catch (Exception e) {
            System.out.println("Thất bại: " + e.getMessage());
        }
    }
}