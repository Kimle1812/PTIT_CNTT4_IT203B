import java.sql.*;

public class BankTransfer {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/thuc_hanh_session14";
        String user = "root";
        String password = "12345678";

        String fromAcc = "ACC01";
        String toAcc = "ACC02";
        double amount = 1000;

        try (
            Connection conn = DriverManager.getConnection(url, user, password)
        ) {
            conn.setAutoCommit(false);

            // 2. Kiểm tra số dư tài khoản gửi
            String checkSql = "SELECT Balance FROM Accounts WHERE AccountId = ?";
            try (PreparedStatement ps = conn.prepareStatement(checkSql)) {
                ps.setString(1, fromAcc);
                ResultSet rs = ps.executeQuery();

                if (!rs.next()) {
                    throw new Exception("Tài khoản gửi không tồn tại");
                }

                double balance = rs.getDouble("Balance");

                if (balance < amount) {
                    throw new Exception("Không đủ số dư");
                }
            }

            // 3. Gọi Stored Procedure
            String call = "{CALL sp_UpdateBalance(?, ?)}";

            try (CallableStatement cs = conn.prepareCall(call)) {

                // Trừ tiền
                cs.setString(1, fromAcc);
                cs.setDouble(2, -amount);
                cs.execute();

                // Cộng tiền
                cs.setString(1, toAcc);
                cs.setDouble(2, amount);
                cs.execute();
            }

            // 4. Commit
            conn.commit();
            System.out.println("Chuyển khoản thành công!");

            // 5. Hiển thị kết quả
            String resultSql = "SELECT * FROM Accounts WHERE AccountId IN (?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(resultSql)) {
                ps.setString(1, fromAcc);
                ps.setString(2, toAcc);

                ResultSet rs = ps.executeQuery();

                System.out.println("===== KẾT QUẢ SAU CHUYỂN =====");
                while (rs.next()) {
                    System.out.println(
                        rs.getString("AccountId") + " | " +
                        rs.getString("FullName") + " | " +
                        rs.getDouble("Balance")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi xảy ra");

            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}