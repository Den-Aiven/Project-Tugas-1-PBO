//import service.*;
import util.DataStore;
import model.CustomerPortofolio;
import view.MenuView;
import view.CustomerView;
import view.AdminView;

public class Main {
    public static void main(String[] args) {

        DataStore.akun.put("admin", "admin123");
        DataStore.role.put("admin", "admin");
        DataStore.akun.put("user", "user123");
        DataStore.role.put("user", "customer");
        DataStore.portofolios.put("user", new CustomerPortofolio());

        while (true) {
            MenuView.showMainMenu();
            String pilihan = DataStore.scanner.nextLine();

            switch (pilihan) {
                case "1":
                    loginSebagaiAdmin();
                    break;
                case "2":
                    loginSebagaiCustomer();
                    break;
                case "0":
                    System.out.println("Keluar dari program...");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }


    private static void loginSebagaiAdmin() {
        System.out.print("Username Admin: ");
        String user = DataStore.scanner.nextLine();
        System.out.print("Password: ");
        String pass = DataStore.scanner.nextLine();

        if (DataStore.akun.containsKey(user) && DataStore.akun.get(user).equals(pass) && DataStore.role.get(user).equals("admin")) {
            AdminView.menuAdmin();
        } else {
            System.out.println("Login admin gagal!");
        }
    }


    private static void loginSebagaiCustomer() {
        System.out.print("Username Customer: ");
        String user = DataStore.scanner.nextLine();
        System.out.print("Password: ");
        String pass = DataStore.scanner.nextLine();

        if (DataStore.akun.containsKey(user) && DataStore.akun.get(user).equals(pass) && DataStore.role.get(user).equals("customer")) {
            DataStore.currentUser = user;
            CustomerView.showCustomerMenu();
        } else {
            System.out.println("Login customer gagal!");
        }
    }
}
