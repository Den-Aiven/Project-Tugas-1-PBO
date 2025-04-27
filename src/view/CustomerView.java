package view;

import service.CustomerService;
import util.DataStore;
import model.CustomerPortofolio;

public class CustomerView {
    public static void showCustomerMenu() {
        while (true) {
            System.out.println("\n======================================");
            System.out.println("         ✨ CUSTOMER MENU ✨");
            System.out.println("======================================");
            System.out.println("1. ➤ Beli Saham");
            System.out.println("2. ➤ Jual Saham");
            System.out.println("3. ➤ Beli SBN");
            System.out.println("4. ➤ Simulasi SBN");
            System.out.println("5. ➤ Lihat Portofolio");
            System.out.println("6. ➤ Setor Saldo");
            System.out.println("7. ➤ Tarik Saldo");
            System.out.println("8. ➤ Logout");
            System.out.println("======================================");
            System.out.print("Pilih menu (1-8): ");

            int pilih = inputInteger();
            if (pilih == -1) continue;

            CustomerPortofolio portofolio = DataStore.portofolios.get(DataStore.currentUser);

            switch (pilih) {
                case 1:
                    CustomerService.beliSaham(portofolio);
                    break;
                case 2:
                    CustomerService.jualSaham(portofolio);
                    break;
                case 3:
                    CustomerService.beliSBN(portofolio);
                    break;
                case 4:
                    CustomerService.simulasiSBN();
                    break;
                case 5:
                    CustomerService.lihatPortofolio(portofolio);
                    break;
                case 6:
                    CustomerService.setorSaldo(portofolio);
                    break;
                case 7:
                    CustomerService.tarikSaldo(portofolio);
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Peringatan: Pilihan tidak valid.");
                    break;
            }

        }
    }

    private static int inputInteger() {
        try {
            return Integer.parseInt(DataStore.scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Peringatan: Harus angka!");
            return -1;
        }
    }
}