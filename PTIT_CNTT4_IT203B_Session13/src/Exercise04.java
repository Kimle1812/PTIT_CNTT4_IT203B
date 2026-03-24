

import java.sql.*;

// Phần 1: Phân tích
// - Input / Output
//   + Input: yêu cầu từ Frontend → lấy danh sách patients đang nằm tại khoa cấp cứu trong ngày.
//   + Output: List<PatientDTO> trong đó mỗi PatientDTO gồm thông tin cơ bản của
//   bệnh nhân + List<Service> dịch vụ/thuốc đã sử dụng.
// - Đề xuất:
//   + Đề xuất 1: JOIN nhiều bảng trong một câu SQL
//      -> Viết câu SQL dùng LEFT JOIN giữa Patient và ServiceUsage
//      -> Ưu điểm: chỉ cần 1 query
//      -> Nhược điểm: dữ liệu trả về dạng “phẳng” (mỗi dòng = patient + 1 service). Phải xử lý gộp trên Java
//   + Đề xuất 2: Truy vấn 2 lần, tách riêng
//      -> Query 1: lấy danh sách patients.
//      -> Query 2: lấy toàn bộ services theo patientId
//      -> Ưu điểm: dễ code, dễ map dữ liệu
//      -> Nhược điểm: nhiều query hơn, có thể chậm khi số lượng bệnh nhân lớn.

// Phần 2: Thực thi
public class Exercise04 {
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
