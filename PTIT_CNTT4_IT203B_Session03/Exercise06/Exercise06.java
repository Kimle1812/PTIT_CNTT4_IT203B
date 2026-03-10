package Exercise06;

import java.util.*;
import java.util.stream.Collectors;

public class Exercise06 {
    record Post(String title, List<String> tags) {}

    public static void main(String[] args) {
        // Tạo danh sách bài viết với nhiều tag
        List<Post> posts = List.of(
            new Post("Java", List.of("java", "backend")),
            new Post("Python", List.of("python", "data"))
        );

        // Dùng flatMap để làm phẳng danh sách tag
        List<String> allTags = posts.stream()
            .flatMap(post -> post.tags().stream())
            .collect(Collectors.toList());

        // In ra kết quả
        System.out.println(allTags);
    }
}