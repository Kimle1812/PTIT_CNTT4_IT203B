package Exercise06;

import java.io.IOException;

public class Exercise06 {
    public static void main(String[] args) {
        User user = new User();

        try {
            user.setAge(-5);
        } catch (InvalidAgeException e) {
            Logger.error(e.getMessage(), e);
        }

        try {
            UserFileService.processUserData("Thông tin người dùng");
        } catch (IOException e) {
            Logger.error(e.getMessage(), e);
        }

        user.printName();
    }
}