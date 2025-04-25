package util;

import model.*;
import java.util.*;

public class DataStore {
    public static Map<String, Saham> daftarSaham = new HashMap<>();
    public static Map<String, SuratBerhargaNegara> daftarSBN = new HashMap<>();
    public static Map<String, CustomerPortofolio> portofolios = new HashMap<>();
    public static Map<String, String> akun = new HashMap<>();
    public static Map<String, String> role = new HashMap<>();
    public static String currentUser = "";
    public static Scanner scanner = new Scanner(System.in);

static {
    daftarSaham.put("BBCA", new Saham("BBCA", "Bank Central Asia", 9500));
    daftarSaham.put("BBRI", new Saham("BBRI", "Bank Rakyat Indonesia", 4800));
    daftarSaham.put("BBNI", new Saham("BBNI", "Bank Negara Indonesia", 5100));
    daftarSaham.put("BMRI", new Saham("BMRI", "Bank Mandiri", 6800));

    daftarSBN.put("ORI021", new SuratBerhargaNegara("ORI021", 6.35, 3, "2027-11-15", 100000000));
    daftarSBN.put("SR017", new SuratBerhargaNegara("SR017", 6.25, 3, "2027-03-10", 100000000));
    daftarSBN.put("SBR013", new SuratBerhargaNegara("SBR013", 6.15, 2, "2026-07-10", 75000000));
    daftarSBN.put("ST011", new SuratBerhargaNegara("ST011", 6.5, 2, "2026-03-15", 50000000));
}
}