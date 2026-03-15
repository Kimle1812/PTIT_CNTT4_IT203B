package thuchanh;

import java.util.Random;

public class Bai2 {
    public static void main(String[] args) {
        String[] students = { "An", "Bình", "Chi", "Dũng", "Em", "Giang" };
        String[] arrdress = {"Hà Nội", "TP Hồ Chí Minh", "Đà Nẵng", "Thanh Hóa", "Nghệ An", "Hà Tĩnh"};
        Random random = new Random();
        while (true) {
            int index = random.nextInt(students.length);
            System.out.println("Tên ngẫu nhiên: " + students[index] + " - Địa chỉ: " + arrdress[index]);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
