package Exercise03;

public class Exercise03 {
    public static void main(String[] args) {
        User user = new User();
        try {
            user.setAge(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
}