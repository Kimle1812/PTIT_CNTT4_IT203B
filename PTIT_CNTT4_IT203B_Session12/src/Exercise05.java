import java.sql.*;
import java.util.Scanner;

public class Exercise05 {

    static final String URL = "jdbc:mysql://localhost:3306/session12";
    static final String USER = "root";
    static final String PASS = "12345678";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== RHMS MENU =====");
            System.out.println("1. Danh sách bệnh nhân");
            System.out.println("2. Tiếp nhận bệnh nhân mới");
            System.out.println("3. Cập nhật bệnh án");
            System.out.println("4. Xuất viện & tính phí");
            System.out.println("5. Thoát");
            System.out.print("Chọn: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: listPatients(); break;
                case 2: addPatient(sc); break;
                case 3: updatePatient(sc); break;
                case 4: discharge(sc); break;
                case 5:
                    System.out.println("Thoát...");
                    return;
                default:
                    System.out.println("Chọn sai!");
            }
        }
    }

    // 1. Danh sách bệnh nhân
    static void listPatients() {
        String sql = "SELECT id, name, age, department FROM Patients";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n--- Danh sách bệnh nhân ---");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getInt("age") + " | " +
                        rs.getString("department"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 2. Thêm bệnh nhân (PreparedStatement chống SQL Injection)
    static void addPatient(Scanner sc) {
        String sql = "INSERT INTO Patients(name, age, department, disease) VALUES(?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            System.out.print("Tên: ");
            String name = sc.nextLine(); // xử lý được L'Oréal

            System.out.print("Tuổi: ");
            int age = sc.nextInt();
            sc.nextLine();

            System.out.print("Khoa: ");
            String dept = sc.nextLine();

            System.out.print("Bệnh: ");
            String disease = sc.nextLine();

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, dept);
            ps.setString(4, disease);

            ps.executeUpdate();
            System.out.println("Thêm thành công!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 3. Cập nhật bệnh án
    static void updatePatient(Scanner sc) {
        String sql = "UPDATE Patients SET disease = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            System.out.print("Nhập ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Bệnh mới: ");
            String disease = sc.nextLine();

            ps.setString(1, disease);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Cập nhật thành công!");
            else
                System.out.println("Không tìm thấy bệnh nhân!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 4. Xuất viện + gọi Stored Procedure
    static void discharge(Scanner sc) {
        String sql = "{call CALCULATE_DISCHARGE_FEE(?, ?)}";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             CallableStatement cs = conn.prepareCall(sql)) {

            System.out.print("Nhập ID bệnh nhân: ");
            int id = sc.nextInt();

            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.DOUBLE);

            cs.execute();

            double fee = cs.getDouble(2);

            System.out.println("Tổng viện phí: " + fee);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}