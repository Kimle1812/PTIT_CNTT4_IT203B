package Exercise06;

import java.time.LocalDate;

public class Logger {
    public static void error(String message, Exception e) {
        String log = String.format("[ERROR] %s - %s: %s",
                LocalDate.now(), e.getClass().getSimpleName(), message);
        System.out.println(log);
    }
}
