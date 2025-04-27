# Program Investasi Saham & SBN - Tugas 1 PBO
Hi! Ini adalah Program investasi Saham dan SBN berbasis console yang dibangun menggunakan Java untuk menyelesaikan Tugas 1 mata kuliah Pemrograman Berorientasi Objek (PBO). Program ini dirancang untuk mempermudah dua jenis pengguna Admin dan Customer dalam melakukan transaksi, simulasi, serta pengelolaan portofolio investasi, baik dalam bentuk Saham maupun Surat Berharga Negara (SBN). Seluruh proses berjalan tanpa menggunakan database (non-persistent), dan semua interaksi dilakukan melalui antarmuka berbasis teks (console). Program ini juga dilengkapi dengan fitur simulasi hasil investasi dan pengelolaan portofolio secara menyeluruh.

---

##  Jenis Pengguna
Program mendukung dua jenis user: 
- Fitur Admin
  - Menambah & menghapus produk investasi (saham/SBN)
  - Mengubah harga saham
  - Melihat daftar semua produk investasi
- Fitur Customer
  - Membeli & menjual saham
  - Membeli SBN
  - Simulasi investasi SBN
  - Melihat portofolio
  - Setor dan tarik saldo
 
## Struktur Program

- `model/`
  - `Saham`
  - `SuratBerhargaNegara`
  - `CustomerPortofolio`
- `service/`
  - `AdminService` – logika untuk fitur admin
  - `CustomerService` – logika untuk fitur customer
- `util/`
  - `DataStore` – penyimpanan data sementara
  - `Validasi` – utilitas untuk validasi input
- `view/`
  - `AdminView`
  - `CustomerView`
  - `MenuView`

## Identitas Pengembang
- **I Made Ivan Ari Mahayana**  
  NIM: 2405551015  
  Kelas: PBO (E)

- **I Putu Weda Sidhi Putra**  
  NIM: 2405551164  
  Kelas: PBO (E)

## Bagaimana Program Dijalankan

Program ini dijalankan dengan menerapkan prinsip-prinsip **Object Oriented Programming (OOP)**, antara lain:

- **Penggunaan Class dan Object**: Program dibagi ke dalam berbagai class sesuai tanggung jawabnya seperti `Saham`, `SBN`, `CustomerPortofolio`, dsb.
- **Flow Control**: Menggunakan `while`, `try`, `if-else`, dan `switch-case` untuk pengaturan alur logika program.
- **ArrayList / HashMap**: Digunakan untuk menyimpan daftar produk saham, SBN, dan portofolio pengguna secara dinamis menggunakan `Map<String, T>`.
- **Nested Class**: Beberapa class ditulis secara terstruktur dan modular untuk menjaga keterpisahan logika.
- **Encapsulation**: Properti dari objek disembunyikan (private) dan diakses melalui getter-setter untuk keamanan dan modularitas.

# UML
![UML drawio](https://github.com/user-attachments/assets/94f9d592-9428-465e-beaa-10f5cd658e5e)


# Penggunaan Program Saham SBN
Berikut ini adalah penjelasan mengenai penggunaan program Investasi Saham SBN beserta hasil screenshot yang sesuai. Beberapa penjelasan berikut menunjukkan bagaimana kode program berjalan.

## Login Admin dan customer:
![image](https://github.com/user-attachments/assets/339255cc-8162-45e9-bb89-e1b4c37cc348)
### Berikut ini merupakan tampilan menu Customer:
![image](https://github.com/user-attachments/assets/8dc58b89-afff-405e-b993-403b760edc2f)
### Berikut ini merupakan tampilan menu Admin:
![image](https://github.com/user-attachments/assets/539c3429-c3d2-4d82-865e-4599a89a557c)

## Percobaan Admin pada Menu 1: Menambahkan Saham
### Gambar di bawah ini menunjukkan admin yang mencoba menambahkan saham melalui menu pertama.
![image](https://github.com/user-attachments/assets/1b947f8d-47e0-4e73-9abf-4e5a25025914)

## Percobaan Admin pada Menu 2: Mengubah Harga Saham
### Gambar berikut menunjukkan percobaan admin untuk mengubah harga saham melalui menu kedua.
![image](https://github.com/user-attachments/assets/5e6e4b17-0e22-4764-baae-3e2d52056425)

## Percobaan Admin pada Menu 3: Menambahkan SBN
### Gambar di bawah ini menunjukkan percobaan admin untuk menambahkan SBN melalui menu ketiga.
![image](https://github.com/user-attachments/assets/b22f1bf7-ec4f-4d46-83ba-48037d284436)

## Percobaan Admin pada Menu 4: Melihat Daftar Investasi
### Gambar berikut menunjukkan admin yang mencoba melihat daftar produk investasi yang sudah tersedia.
![image](https://github.com/user-attachments/assets/0a4173d6-42a2-4991-9d97-95785204cacf)

## Percobaan Menghapus Saham dan SBN
### Gambar di bawah ini menunjukkan dua percobaan yang saya lakukan untuk mencoba menghapus saham dan SBN. Gambar pertama menunjukkan percobaan untuk menghapus saham, dan gambar kedua menunjukkan percobaan untuk menghapus SBN.
![image](https://github.com/user-attachments/assets/7149217a-dd88-4f40-bbdd-9cea38e84164)
![image](https://github.com/user-attachments/assets/a91cef72-617f-4be8-8da3-bfdac8165c4c)

## UI Menu Customer
### Gambar berikut ini menunjukkan tampilan UI pada menu customer.
![image](https://github.com/user-attachments/assets/a3434e75-ce75-45f6-8270-b3a40a0c64a7)

## Percobaan Pembelian Saham yang Gagal
### Gambar di bawah ini menunjukkan percobaan ketika mencoba melakukan pembelian saham, namun gagal karena saldo masih kosong.
![image](https://github.com/user-attachments/assets/8f72f810-cf9f-4eb3-b851-8304f1a41d5b)

## Setor Saldo Sebelum Pembelian
### Gambar di bawah ini menunjukkan langkah-langkah melakukan setoran saldo terlebih dahulu untuk bisa melakukan pembelian saham sebagai customer.
![image](https://github.com/user-attachments/assets/780ff3da-b0dc-4b22-98e1-8a49cd1fe370)

## Percobaan Pembelian Saham yang Berhasil
### Gambar di bawah ini menunjukkan percobaan yang berhasil melakukan pembelian saham setelah saldo mencukupi.
![image](https://github.com/user-attachments/assets/d49b721c-0080-4bb1-a913-1959d893cab9)

## Melihat Portofolio
### Gambar di bawah ini menunjukkan percobaan saat mencoba melihat portofolio untuk memeriksa apakah sudah terisi atau belum.
![image](https://github.com/user-attachments/assets/dc00380e-ac71-4065-87da-f802691d66d6)

## Menjual Saham
### Gambar di bawah ini menunjukkan percobaan saat mencoba menjual sebagian dari jumlah lembar saham yang dimiliki.
![image](https://github.com/user-attachments/assets/ac45329a-02a3-43c5-9470-e7278d809477)

## Percobaan Pembelian SBN
### Gambar di bawah ini menunjukkan percobaan pada menu ketiga yang di mana untuk membeli SBN.
![image](https://github.com/user-attachments/assets/3b04a7b3-4bde-4bb2-968a-4d5039e40a2a)

## Simulasi Pembelian SBN
### Gambar di bawah ini menunjukkan simulasi pembelian SBN, di mana program akan menampilkan pilihan SBN yang akan saya beli beserta nominal investasi yang saya pilih. Program kemudian menghitung total hasil yang saya peroleh setelah 3 tahun (tergantung pada jangka waktu yang dipilih), serta keuntungan yang saya peroleh.
![image](https://github.com/user-attachments/assets/4d5e1fbc-1db6-474d-861b-68ba5227fc8b)

## Melihat Portofolio Terakhir
### Gambar di bawah ini menunjukkan percobaan saya saat memeriksa portofolio terakhir untuk mengetahui berapa sisa saldo yang dimiliki.
![image](https://github.com/user-attachments/assets/3c9815b9-91ac-47ae-b45e-9e856dbddcac)

## Penarikan Saldo
### Gambar berikut ini menunjukkan percobaan saya ketika mencoba melakukan penarikan dari sisa saldo yang telah saya periksa sebelumnya di portofolio.
![image](https://github.com/user-attachments/assets/ebef1ed6-f10e-434b-a44f-7dc52f9f876e)

