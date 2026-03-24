package Exercise01;

import java.sql.*;

// Phần 1: Phân tích
// Mỗi câu lệnh SQL (executeUpdate()) được thực thi sẽ tự động commit ngay lập tức vào cơ sở dữ liệu
// Nếu viết 2 câu lệnh liên tiếp, thì câu lệnh đầu tiên (trừ thuốc trong Medicine_Inventory)
// đã được commit ngay khi chạy xong. Khi câu lệnh thứ hai (ghi vào Prescription_History)
// gặp lỗi, thì nó không được thực thi thành công. Nhưng vì câu lệnh đầu tiên đã commit rồi,
// nên không thể rollback lại.

// Phần 2: Thực thí
public class Main {
    public static void main(String[] args) {
        String DB_URL = "jdbc:mysql://localhost:3306/rikkei_hospital";
        String USER = "root";
        String PASS = "12345678";

        Prescription prescription = new Prescription(1, 101);

        Connection conn = null;
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(false);

            // 1. Trừ thuốc trong kho
            String sql1 = "UPDATE Medicine_Inventory SET quantity = quantity - 1 WHERE medicine_id = ?";
            stmt1 = conn.prepareStatement(sql1);
            stmt1.setInt(1, prescription.getMedicineId());
            stmt1.executeUpdate();

            // 2. Lưu lịch sử cấp phát thuốc
            String sql2 = "INSERT INTO Prescription_History(patient_id, medicine_id, date) VALUES (?, ?, ?)";
            stmt2 = conn.prepareStatement(sql2);
            stmt2.setInt(1, prescription.getPatientId());
            stmt2.setInt(2, prescription.getMedicineId());
            stmt2.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            stmt2.executeUpdate();

            // Nếu cả hai đều thành công
            conn.commit();
            System.out.println("Transaction thành công!");

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // rollback nếu có lỗi
                    System.out.println("Transaction thất bại, rollback!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
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