package view;

import service.AdminService;
import util.DataStore;

public class AdminView {

    private static void showAdminMenu() {
        System.out.println("\n=== Admin Menu ===");
        System.out.println("1. Tambah Saham");
        System.out.println("2. Ubah Harga Saham");
        System.out.println("3. Tambah SBN");
        System.out.println("4. Lihat Daftar Produk Investasi");
        System.out.println("5. Hapus Produk Investasi");
        System.out.println("6. Logout");
    }

    public static void menuAdmin() {
        boolean isRunning = true;
        while (isRunning) {
            showAdminMenu();
            System.out.print("Pilih menu: ");
            int pilihan = inputInteger();

            switch (pilihan) {
                case 1:
                    AdminService.tambahSaham();
                    break;
                case 2:
                    AdminService.ubahHargaSaham();
                    break;
                case 3:
                    AdminService.tambahSBN();
                    break;
                case 4:
                    AdminService.lihatDaftarProduk();
                    break;
                case 5:
                    AdminService.hapusProduk();
                    break;
                case 6:
                    System.out.println("👋 Logout berhasil. Kembali ke menu utama.");
                    isRunning = false;
                    break;
                default:
                    System.out.println("⚠ Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    private static int inputInteger() {
        try {
            return Integer.parseInt(DataStore.scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("⚠ Input harus berupa angka!");
            return -1;
        }
    }
}
