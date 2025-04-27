package util;

import java.util.Scanner;

public class Validasi {
    private static final Scanner scanner = new Scanner(System.in);

    // Method untuk input angka bulat (integer)
    public static int inputInteger(String label) {
        while (true) {
            System.out.print(label);
            String input = scanner.nextLine();
            if (input.equals("0")) return 0;  // Untuk kasus keluar dari input
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("⚠ Peringatan: Pilihan harus berupa angka.");
            }
        }
    }

    // Method untuk input angka desimal (positif)
    public static double inputDouble(String label) {
        while (true) {
            System.out.print(label);
            String input = scanner.nextLine();
            if (input.equals("0")) return -1;  // Untuk kasus keluar dari input
            try {
                double value = Double.parseDouble(input);
                if (value < 0) {
                    System.out.println("⚠ Peringatan: Input harus lebih dari 0.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("⚠ Peringatan: Input harus berupa angka desimal.");
            }
        }
    }

    // Method untuk input angka bulat (integer) dengan validasi positif
    public static int inputAngka(String label) {
        while (true) {
            System.out.print(label);
            String input = scanner.nextLine();
            if (input.equals("0")) return 0;
            try {
                int value = Integer.parseInt(input);
                if (value <= 0) {
                    System.out.println("⚠ Input harus berupa angka positif.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("⚠ Input harus berupa angka bulat.");
            }
        }
    }

    // Method untuk input angka desimal positif dengan validasi
    public static double inputDoublePositif(String label) {
        while (true) {
            System.out.print(label);
            String input = scanner.nextLine();
            if (input.equals("0")) return -1;
            try {
                double value = Double.parseDouble(input);
                if (value < 0) {
                    System.out.println("⚠ Input harus lebih dari 0.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("⚠ Input harus berupa angka desimal.");
            }
        }
    }

    // Method untuk input string
    public static String inputString(String label) {
        System.out.print(label);
        return scanner.nextLine();
    }

    // Validasi untuk memastikan nilai positif
//    public static boolean isValidPositiveAmount(double amount) {
//        return amount > 0;
//    }

    // Validasi untuk memastikan angka positif
//    public static boolean isValidPositiveInteger(int number) {
//        return number > 0;
//    }

    // Validasi untuk kode saham yang valid
    public static boolean isValidSahamCode(String kode) {
        return DataStore.daftarSaham.containsKey(kode);
    }

    // Validasi untuk nama SBN yang valid
//    public static boolean isValidSBNCode(String nama) {
//        return DataStore.daftarSBN.containsKey(nama);
//    }

    // Validasi untuk memeriksa apakah kode saham sudah terdaftar
//    public static boolean isValidSahamCodeExist(String kode) {
//        return DataStore.daftarSaham.containsKey(kode);
//    }


    // Validasi untuk memeriksa apakah nama SBN sudah terdaftar
//    public static boolean isValidSBNNameExist(String nama) {
//        return DataStore.daftarSBN.containsKey(nama);
//    }
}
