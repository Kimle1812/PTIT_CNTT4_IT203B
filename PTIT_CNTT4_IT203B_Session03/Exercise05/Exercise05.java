
package Exercise05;

import java.util.*;
import java.util.stream.Collectors;

public class Exercise05 {
    // Khai báo record User
    record User(String username, String email, String status) {}

    public static void main(String[] args) {
        // Sinh viên tự định nghĩa danh sách users
        List<User> users = List.of(
            new User("alexander", "alexander@gmail.com", "ACTIVE"),
            new User("bob", "bob@yahoo.com", "INACTIVE"),
            new User("charlotte", "charlotte@gmail.com", "ACTIVE"),
            new User("Benjamin", "benjamin@gmail.com", "ACTIVE"),
            new User("Tom", "tom@gmail.com", "ACTIVE")
        );

        // Dùng Stream để sắp xếp theo độ dài username giảm dần và lấy 3 người đầu tiên
        List<User> top3 = users.stream()
            .sorted((u1, u2) -> Integer.compare(u2.username().length(), u1.username().length()))
            .limit(3)
            .collect(Collectors.toList());

        // In ra danh sách 3 người dùng có username dài nhất
        top3.forEach(u -> System.out.println(u.username()));
    }
}

