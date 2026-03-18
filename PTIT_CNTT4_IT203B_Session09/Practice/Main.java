package PTIT_CNTT4_IT203B_Session09.Practice;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductDatabase db = ProductDatabase.getInstance();
        int choice;
        do {
            System.out.println("==========Menu============");
            System.out.println("1. Thêm mới sản phẩm");
            System.out.println("2. Xem danh sách sản phẩm");
            System.out.println("3. Cập nhật thông tin sản phẩm");
            System.out.println("4. Xoá sản phẩm");
            System.out.println("5. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = sc.nextInt();
            sc.nextLine(); // bỏ ký tự xuống dòng

            switch (choice) {
                case 1: // Thêm mới sản phẩm
                    System.out.print("Nhập loại (1: Physical, 2: Digital): ");
                    int type = sc.nextInt();
                    sc.nextLine();
                    if (type != 1 && type != 2) {
                        System.out.println("Không có loại bạn nhập!");
                        break;
                    }
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Tên: ");
                    String name = sc.nextLine();
                    System.out.print("Giá: ");
                    double price = sc.nextDouble();
                    System.out.print(type == 1 ? "Trọng lượng: " : "Dung lượng (MB): ");
                    double extra = sc.nextDouble();

                    Product p = ProductFactory.createProduct(type, id, name, price, extra);
                    db.addProduct(p);
                    break;

                case 2: // Xem danh sách sản phẩm
                    for (Product prod : db.getProducts()) {
                        prod.displayInfo();
                    }
                    break;

                case 3: // Cập nhật thông tin sản phẩm
                    System.out.print("Nhập ID sản phẩm cần cập nhật: ");
                    String updateId = sc.nextLine();
                    if (!updateId.trim().isEmpty()) {
                        System.out.print("Loại (1: Physical, 2: Digital): ");
                        int updateType = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Tên mới: ");
                        String newName = sc.nextLine();
                        System.out.print("Giá mới: ");
                        double newPrice = sc.nextDouble();
                        System.out.print(updateType == 1 ? "Trọng lượng mới: " : "Dung lượng mới (MB): ");
                        double newExtra = sc.nextDouble();

                        Product newProd = ProductFactory.createProduct(updateType, updateId, newName, newPrice, newExtra);
                        db.updateProduct(updateId, newProd);
                    } else {
                        System.out.println("Không được để trống!");
                    }
                    break;

                case 4: // Xoá sản phẩm
                    System.out.print("Nhập ID sản phẩm cần xóa: ");
                    String deleteId = sc.nextLine();
                    db.deleteProduct(deleteId);
                    break;

                case 5: // Thoát
                    System.out.println("Thoát chương trình.");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ.");
                    break;
            }
        } while (choice != 5);
    }
}
