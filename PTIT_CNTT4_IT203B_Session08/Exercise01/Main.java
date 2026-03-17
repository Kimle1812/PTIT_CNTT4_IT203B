package Exercise01;

import java.util.*;

interface Device {
    void turnOn();
    void turnOff();
}

class Light implements Device {
    @Override
    public void turnOn() {
        System.out.println("Đèn: Bật sáng.");
    }
    @Override
    public void turnOff() {
        System.out.println("Đèn: Tắt.");
    }
}

class Fan implements Device {
    @Override
    public void turnOn() {
        System.out.println("Quạt: Bật quay.");
    }
    @Override
    public void turnOff() {
        System.out.println("Quạt: Tắt.");
    }
}

class AirConditioner implements Device {
    @Override
    public void turnOn() {
        System.out.println("Điều hòa: Bật làm mát.");
    }
    @Override
    public void turnOff() {
        System.out.println("Điều hòa: Tắt.");
    }
}

class HardwareConnection {
    private static HardwareConnection instance;

    private HardwareConnection() {}

    public static HardwareConnection getInstance() {
        if (instance == null) {
            instance = new HardwareConnection();
            System.out.println("HardwareConnection: Đã kết nối phần cứng.");
        }
        return instance;
    }

    public void connect() {
        System.out.println("HardwareConnection: Đã kết nối.");
    }

    public void disconnect() {
        System.out.println("HardwareConnection: Ngắt kết nối.");
    }
}

abstract class DeviceFactory {
    public abstract Device createDevice();
}

class LightFactory extends DeviceFactory {
    @Override
    public Device createDevice() {
        System.out.println("LightFactory: Đã tạo đèn mới.");
        return new Light();
    }
}

class FanFactory extends DeviceFactory {
    @Override
    public Device createDevice() {
        System.out.println("FanFactory: Đã tạo quạt mới.");
        return new Fan();
    }
}

class AirConditionerFactory extends DeviceFactory {
    @Override
    public Device createDevice() {
        System.out.println("AirConditionerFactory: Đã tạo điều hòa mới.");
        return new AirConditioner();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HardwareConnection connection = null;
        List<Device> devices = new ArrayList<>();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Kết nối phần cứng");
            System.out.println("2. Tạo thiết bị mới");
            System.out.println("3. Bật thiết bị");
            System.out.println("4. Tắt thiết bị");
            System.out.println("5. Thoát");
            System.out.print("Chọn: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    connection = HardwareConnection.getInstance();
                    break;
                case 2:
                    System.out.println("Chọn loại: 1. Đèn, 2. Quạt, 3. Điều hòa");
                    int type = sc.nextInt();
                    DeviceFactory factory;
                    if (type == 1) factory = new LightFactory();
                    else if (type == 2) factory = new FanFactory();
                    else factory = new AirConditionerFactory();
                    devices.add(factory.createDevice());
                    break;
                case 3:
                    System.out.println("Chọn thiết bị vừa tạo (index bắt đầu từ 1): ");
                    int idxOn = sc.nextInt();
                    if (idxOn > 0 && idxOn <= devices.size()) {
                        devices.get(idxOn - 1).turnOn();
                    }
                    break;
                case 4:
                    System.out.println("Chọn thiết bị vừa tạo (index bắt đầu từ 1): ");
                    int idxOff = sc.nextInt();
                    if (idxOff > 0 && idxOff <= devices.size()) {
                        devices.get(idxOff - 1).turnOff();
                    }
                    break;
                case 5:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
