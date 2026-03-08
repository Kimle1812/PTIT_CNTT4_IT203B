package Exercise02;

import java.util.Scanner;

public class Exercise02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập số người: ");
        int person = Integer.parseInt(sc.nextLine());
        System.out.print("Nhập số nhóm: ");
        int groups = Integer.parseInt(sc.nextLine());

        try {
            int result = person / groups;
            System.out.println("Mỗi nhóm có: " + result + " người");
        } catch (ArithmeticException e) {
            System.out.println("Không thể chia cho 0!");
        }
    }
}