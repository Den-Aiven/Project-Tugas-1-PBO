package util;

import java.util.Scanner;

public class Validasi {
    private static final Scanner sc = new Scanner(System.in);

    public static String inputString(String label) {
        System.out.print(label);
        return sc.nextLine();  // Langsung kembalikan hasil dari sc.nextLine()
    }

    public static int inputAngka(String label) {
        while (true) {
            System.out.print(label);
            String input = sc.nextLine();  // Hanya baca input sekali
            if (input.equals("0")) return 0;
            try {
                return Integer.parseInt(input);  // Langsung gunakan input
            } catch (NumberFormatException e) {
                System.out.println("⚠ Input harus berupa angka bulat.");
            }
        }
    }

    public static double inputDoublePositif(String label) {
        while (true) {
            System.out.print(label);
            String input = sc.nextLine();  // Hanya baca input sekali
            if (input.equals("0")) return -1;
            try {
                double value = Double.parseDouble(input);  // Langsung gunakan input
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

    public static boolean isKodeSahamExist(String kode) {
        return util.DataStore.daftarSaham.containsKey(kode);
    }

    public static boolean isNamaSBNExist(String nama) {
        return util.DataStore.daftarSBN.containsKey(nama);
    }

    public static double inputDouble() {
        try {
            return Double.parseDouble(DataStore.scanner.nextLine());  // Langsung parse input
        } catch (NumberFormatException e) {
            System.out.println("Peringatan: Input harus berupa angka desimal.");
            return -1;
        }
    }

    public static int inputInteger() {
        try {
            return Integer.parseInt(DataStore.scanner.nextLine());  // Langsung parse input
        } catch (NumberFormatException e) {
            System.out.println("Peringatan: Pilihan harus berupa angka.");
            return -1;
        }
    }
}
