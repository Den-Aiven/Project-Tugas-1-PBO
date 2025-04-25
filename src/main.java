import service.*;
import util.DataStore;
import model.CustomerPortofolio;

public class main {
    public static void main(String[] args) {
        DataStore.akun.put("admin", "admin123");
        DataStore.role.put("admin", "admin");
        DataStore.akun.put("user", "user123");
        DataStore.role.put("user", "customer");
        DataStore.portofolios.put("user", new CustomerPortofolio());

        while (true) {
            System.out.println("===== MENU UTAMA =====");
            System.out.print("Username: ");
            String user = DataStore.scanner.nextLine();
            System.out.print("Password: ");
            String pass = DataStore.scanner.nextLine();

            if (DataStore.akun.containsKey(user) && DataStore.akun.get(user).equals(pass)) {
                DataStore.currentUser = user;
                if (DataStore.role.get(user).equals("admin")) AdminService.menuAdmin();
                else CustomerService.menuCustomer();
            } else {
                System.out.println("Login gagal!");
            }
        }
    }
}
