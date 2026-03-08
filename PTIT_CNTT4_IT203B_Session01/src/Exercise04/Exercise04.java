package Exercise04;

import java.io.IOException;

public class Exercise04 {
    public static void saveToFile(String data) throws IOException {
        throw new IOException("Lỗi khi ghi dữ liệu xuống file!");
    }

    public static void processUserData(String data) throws IOException {
        saveToFile(data);
    }

    public static void main(String[] args) {
        try {
            processUserData("Thông tin người dùng");
            System.out.println("Lưu dữ liệu thành công!");
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
        }
    }
}