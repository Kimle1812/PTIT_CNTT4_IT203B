package Exercise01;

import java.util.List;

public class Exercise01 {
    record User(String username, String email, String status) {}

    public static void main(String[] args) {
        // Tạo 3 User
        User alice = new User("alice", "alice@example.com", "ACTIVE");
        User bob = new User("bob", "bob@example.com", "INACTIVE");
        User charlie = new User("charlie", "charlie@example.com", "ACTIVE");

        // Đưa vào danh sách
        List<User> users = List.of(alice, bob, charlie);

        // In danh sách bằng forEach
        users.forEach(System.out::println);
    }
}

