package thuchanh;

import java.util.Random;

public class Bai1 {
    public static void main(String[] args) {
        String[] students = { "An", "Bình", "Chi", "Dũng", "Em", "Giang" };
        Random random = new Random();
        while (true) {
            int index = random.nextInt(students.length);
            System.out.println("Tên ngẫu nhiên: " + students[index]);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
