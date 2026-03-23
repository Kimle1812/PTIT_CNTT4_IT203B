package Exercise05;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/session11";
    private static final String USER = "root";
    private static final String PASSWORD = "mona32006";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT doctor_id, name, specialization FROM doctors";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Doctor(
                        rs.getString("doctor_id"),
                        rs.getString("name"),
                        rs.getString("specialization")));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách bác sĩ: " + e.getMessage());
        }
        return list;
    }

    public void insertDoctor(Doctor doctor) {
        String sql = "INSERT INTO doctors (doctor_id, name, specialization) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, doctor.getDoctorId());
            stmt.setString(2, doctor.getName());
            stmt.setString(3, doctor.getSpecialization());
            stmt.executeUpdate();
            System.out.println("Thêm bác sĩ thành công!");
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm bác sĩ: " + e.getMessage());
        }
    }

    public void statisticBySpecialization() {
        String sql = "SELECT specialization, COUNT(*) AS total FROM doctors GROUP BY specialization";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("=== Thống kê chuyên khoa ===");
            while (rs.next()) {
                System.out.println(rs.getString("specialization") + " : " + rs.getInt("total"));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi thống kê: " + e.getMessage());
        }
    }
}