package Exercise02;

import java.util.List;

public class Exercise02 {
    record User(String username, String email, String status) {}

    public static void main(String[] args) {
        // Tạo danh sách User
        List<User> users = List.of(
            new User("alice", "alice@gmail.com", "ACTIVE"),
            new User("bob", "bob@yahoo.com", "INACTIVE"),
            new User("charlie", "charlie@gmail.com", "ACTIVE")
        );

        // Dùng Stream để lọc email gmail.com và in ra username
        users.stream()
             .filter(u -> u.email().endsWith("@gmail.com"))
             .forEach(u -> System.out.println(u.username()));
    }
}

