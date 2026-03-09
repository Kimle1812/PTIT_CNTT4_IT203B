package Exercise06;
// Functional Interface chỉ có một phương thức trừu tượng
@FunctionalInterface
interface UserProcessor {
    String process(User u);
}

// Class User với thuộc tính username
class User {
    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

// Lớp tiện ích với phương thức tĩnh
class UserUtils {
    // Phương thức tĩnh: trả về username viết hoa
    public static String convertToUpperCase(User u) {
        return u.getUsername().toUpperCase();
    }
}

// Chương trình chính
public class Exercise06 {
    public static void main(String[] args) {
        // Khai báo biến kiểu UserProcessor và gán bằng Method Reference
        UserProcessor processor = UserUtils::convertToUpperCase;

        // Tạo một đối tượng User cụ thể
        User user = new User("nguyen");

        // Gọi phương thức process và in kết quả
        String result = processor.process(user);
        System.out.println("Kết quả: " + result); // NGUYEN
    }
}
