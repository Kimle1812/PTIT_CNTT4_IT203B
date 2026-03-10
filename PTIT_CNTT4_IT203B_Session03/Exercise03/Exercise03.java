package Exercise03;

import java.util.List;
import java.util.Optional;

public class Exercise03 {
    // Khai báo record User
    record User(String username, String email, String status) {}

    // Class UserRepository
    static class UserRepository {
        private List<User> users;

        // Khởi tạo với danh sách user
        public UserRepository(List<User> users) {
            this.users = users;
        }

        // Tìm user theo username, trả về Optional<User>
        public Optional<User> findUserByUsername(String username) {
            return users.stream()
                        .filter(u -> u.username().equalsIgnoreCase(username))
                        .findFirst();
        }
    }

    public static void main(String[] args) {
        // Tạo danh sách user
        List<User> users = List.of(
            new User("alice", "alice@gmail.com", "ACTIVE"),
            new User("bob", "bob@yahoo.com", "INACTIVE"),
            new User("charlie", "charlie@gmail.com", "ACTIVE")
        );

        // Khởi tạo repository
        UserRepository repo = new UserRepository(users);

        // Tìm user "alice"
        Optional<User> result = repo.findUserByUsername("alice");

        // Kiểm tra kết quả
        if (result.isPresent()) {
            System.out.println("Welcome " + result.get().username());
        } else {
            System.out.println("Guest login");
        }
    }
}

