package Exercise06;

import java.io.IOException;

public class UserFileService {
    public static void saveToFile(String data) throws IOException {
        throw new IOException("Lỗi khi ghi dữ liệu xuống file!");
    }

    public static void processUserData(String data) throws IOException {
        saveToFile(data);
    }
}