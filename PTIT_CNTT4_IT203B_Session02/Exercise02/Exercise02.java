package Exercise02;

@FunctionalInterface
interface PasswordValidator {
    boolean isValid(String password);
}

public class Exercise02 {
    public static void main(String[] args) {
        // Dùng Lambda rút gọn cú pháp để triển khai interface
        PasswordValidator validator = password -> password.length() >= 8;

        // Kiểm tra mật khẩu "12345678"
        System.out.println("12345678 -> " + validator.isValid("12345678"));

        // Kiểm tra mật khẩu "1234"
        System.out.println("1234 -> " + validator.isValid("1234"));
    }
}
