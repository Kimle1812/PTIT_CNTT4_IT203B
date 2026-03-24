
import java.sql.*;

// Phần 1: Phân tích
// Vi phạm nguyên tắc Transaction vì:
//- Transaction đang ở trạng thái “chưa hoàn tất” nhưng cũng chưa được hủy bỏ.
//- Kết nối vẫn giữ nguyên Transaction chưa commit, dẫn đến tình trạng “treo”.
//- Các tài nguyên bị chiếm giữ (lock trên bảng, kết nối chưa giải phóng) gây ảnh hưởng đến các thao tác khác.

public class Exercise02 {
    public static void main(String[] args) {
        String DB_URL = "jdbc:mysql://localhost:3306/rikkei_hospital";
        String USER = "root";
        String PASS = "12345678";

        int patientId = 1;
        int invoiceId = 1001;
        double amount = 500000;

        Connection conn = null;
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(false);

            // 1. Trừ tiền trong ví bệnh nhân
            String sql1 = "update patient_wallet set balance = balance - ? where patient_id = ?";
            stmt1 = conn.prepareStatement(sql1);
            stmt1.setDouble(1, amount);
            stmt1.setInt(2, patientId);
            stmt1.executeUpdate();

            // 2. Cập nhật trạng thái hóa đơn
            String sql2 = "update invoices set status = 'đã thanh toán' where invoice_id = ?";
            stmt2 = conn.prepareStatement(sql2);
            stmt2.setInt(1, invoiceId);
            stmt2.executeUpdate();

            // Nếu cả hai đều thành công
            conn.commit();
            System.out.println("Thanh toán thành công!");

        } catch (SQLException e) {
            System.out.println("Lỗi xảy ra: " + e.getMessage());
            if (conn != null) {
                try {
                    conn.rollback(); // rollback bắt buộc
                    System.out.println("Transaction đã rollback!");
                } catch (SQLException ex) {
                    System.out.println("Lỗi khi rollback: " + ex.getMessage());
                }
            }
        } finally {
            try {
                if (stmt1 != null) stmt1.close();
                if (stmt2 != null) stmt2.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}