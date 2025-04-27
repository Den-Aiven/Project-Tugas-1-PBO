package view;

import service.AdminService;
import util.Validasi;

public class AdminView {

    private static void showAdminMenu() {
        System.out.println("\n======================================");
        System.out.println("         ✨ ADMIN MENU ✨");
        System.out.println("======================================");
        System.out.println("1. ➤ Tambah Saham");
        System.out.println("2. ➤ Ubah Harga Saham");
        System.out.println("3. ➤ Tambah SBN");
        System.out.println("4. ➤ Lihat Daftar Produk Investasi");
        System.out.println("5. ➤ Hapus Produk Investasi");
        System.out.println("6. ➤ Logout");
        System.out.println("======================================");
        System.out.print("Pilih menu (1-6): ");
    }

    public static void menuAdmin() {
        boolean isRunning = true;
        while (isRunning) {
            showAdminMenu();
            System.out.print("Pilih menu: ");
            int pilihan = Validasi.inputInteger();
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
}