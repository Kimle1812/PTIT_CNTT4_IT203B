package Exercise06;

public class User {
    private int age;
    private String name;

    public void setAge(int age) throws InvalidAgeException {
        if (age < 0) {
            throw new InvalidAgeException("Tuổi không thể âm!");
        }
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printName() {
        if (name != null) {
            System.out.println("Tên người dùng: " + name);
        } else {
            System.out.println("Tên người dùng chưa được thiết lập.");
        }
    }
}