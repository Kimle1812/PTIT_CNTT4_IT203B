
package Exercise05;

interface UserActions {
    default void logActivity(String activity) {
        System.out.println("User log: " + activity);
    }
}
interface AdminActions {
    default void logActivity(String activity) {
        System.out.println("Admin log: " + activity);
    }
}
public class Exercise05 implements UserActions, AdminActions {

    @Override
    public void logActivity(String activity) {
        AdminActions.super.logActivity(activity);
    }
    public static void main(String[] args) {
        Exercise05 superadmin = new Exercise05();
        superadmin.logActivity("Login");
    }
}
