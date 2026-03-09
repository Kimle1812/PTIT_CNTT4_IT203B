package Exercise01;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Exercise01 {
    public static void main(String[] args) {
        String sourcePath = "source.txt"; // Đường dẫn file nguồn
        String destPath = "copy.txt";     // Đường dẫn file đích

        // Sử dụng try-with-resources để tự động đóng luồng sau khi dùng xong
        try (FileInputStream fis = new FileInputStream(sourcePath);
             FileOutputStream fos = new FileOutputStream(destPath)) {

            byte[] buffer = new byte[1024]; // Tạo buffer để đọc dữ liệu theo khối
            int length;

            // Đọc dữ liệu từ file nguồn và ghi sang file đích
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }

            System.out.println("File copied successfully!"); // Thông báo copy thành công

        } catch (IOException e) {
            e.printStackTrace(); // In lỗi nếu có vấn đề khi đọc/ghi file
        }
    }
}
