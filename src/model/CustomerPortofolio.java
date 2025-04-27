package model;

import java.util.*;

public class CustomerPortofolio {
    public Map<String, Integer> sahamDimiliki = new HashMap<>();
    public Map<String, Double> nominalPembelianSaham = new HashMap<>();
    public Map<String, Double> sbnDimiliki = new HashMap<>();
    public Map<String, Integer> jumlahSahamTerjual = new HashMap<>();
    public double saldo = 0;
}