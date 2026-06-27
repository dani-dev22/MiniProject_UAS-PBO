# CineMax – Sistem Booking Tiket Bioskop

Aplikasi sistem pemesanan tiket bioskop berbasis console (CLI) yang dikembangkan menggunakan bahasa pemrograman Java dengan pendekatan Pemrograman Berorientasi Objek (OOP). Proyek ini dibuat sebagai tugas UAS Mata Kuliah Pemrograman Berorientasi Objek.

## Deskripsi

CineMax memungkinkan pengguna untuk melihat daftar film yang sedang tayang, memesan tiket secara individual maupun grup, mencetak tiket digital, membatalkan tiket, serta mengelola data film (tambah/hapus) melalui menu admin.

## Konsep OOP yang Diterapkan

- **Encapsulation** – Seluruh atribut pada class model bersifat private dengan akses melalui getter/setter
- **Inheritance** – `KursiRegular` dan `KursiVIP` mewarisi abstract class `Kursi`
- **Polymorphism** – Method `getKelas()` dan `getHarga()` di-override pada setiap subclass kursi
- **Abstraction** – Class `Kursi` dideklarasikan sebagai abstract class
- **Interface** – Interface `Printable` diimplementasikan oleh class `Tiket`
- **Exception Handling** – Custom exception `KursiSudahDipesanException`
- **Collection** – Penggunaan `ArrayList` untuk menyimpan data kursi, tiket, dan film

## Struktur Project
```
CineMax/
├── Main.java
├── model/
│   ├── Kursi.java
│   ├── KursiRegular.java
│   ├── KursiVIP.java
│   ├── Film.java
│   ├── Penonton.java
│   └── Tiket.java
├── service/
│   └── SistemBioskop.java
├── interfaces/
│   └── Printable.java
└── exception/
    └── KursiSudahDipesanException.java
```

## Cara Menjalankan

### Persyaratan
- Pastikan di laptop nya udah terinstall Java Development Kit (JDK) versi 25 atau lebih baru

### Kompilasi dan Eksekusi
```bash
# Compile semua file Java
javac Main.java model/*.java service/*.java interfaces/*.java exception/*.java

# Jalankan program
java Main
```

## Fitur Utama

1. Lihat film yang sedang tayang
2. Booking tiket (individual & grup)
3. Cetak / cek tiket berdasarkan kode booking
4. Batalkan tiket
5. Lihat semua tiket aktif
6. Tambah film baru (admin)
7. Hapus film (admin)

## Anggota Kelompok 6

- Diki Aryadi
- Hilal Shofar Falih
- Muhamad Dani
- Sesil Praya

## Mata Kuliah

Pemrograman Berorientasi Objek (PBO) – Semester Genap 2025/2026
Dosen Pengampu: Popon Dauni, S.T., M.Kom.
