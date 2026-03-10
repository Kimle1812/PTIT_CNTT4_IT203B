package Exercise04;

import java.util.*;
import java.util.stream.Collectors;


public class Exercise04 {
    // Khai báo record User
    record User(String username, String email, String status) {}

    public static void main(String[] args) {
        // Danh sách có trùng lặp username
        List<User> users = List.of(
            new User("alice", "alice@gmail.com", "ACTIVE"),
            new User("bob", "bob@yahoo.com", "INACTIVE"),
            new User("alice", "alice@outlook.com", "ACTIVE"),
            new User("charlie", "charlie@gmail.com", "ACTIVE"),
            new User("bob", "bob@hotmail.com", "ACTIVE")
        );

        // Xử lý để loại bỏ trùng lặp theo username
        List<User> distinctUsers = users.stream()
            .collect(Collectors.toMap(
                User::username,   // key: username
                u -> u,           // value: chính user
                (u1, u2) -> u1    // nếu trùng thì giữ user đầu tiên
            ))
            .values()
            .stream()
            .toList();

        // In ra danh sách không trùng lặp
        distinctUsers.forEach(System.out::println);
    }
}


