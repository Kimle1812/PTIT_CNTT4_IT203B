package Exercise05;

public class Exercise05 {
    public static void main(String[] args) {
        User user = new User();
        try {
            user.setAge(-10);
            System.out.println("Tuổi người dùng: " + user.getAge());
        } catch (InvalidAgeException e) {
            System.out.println("Lỗi nghiệp vụ: " + e.getMessage());
            e.printStackTrace();
        }
    }
}