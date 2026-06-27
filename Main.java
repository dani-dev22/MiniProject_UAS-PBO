
// =============================================
// Class Main — Entry Point Aplikasi
// Menampilkan menu & menerima input dari user
// =============================================
import java.util.ArrayList;
import java.util.Scanner;

import model.*;
import service.*;
import interfaces.*;
import exception.*;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static SistemBioskop sistem = new SistemBioskop();

    // =============================================
    // MAIN METHOD
    // =============================================
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║     Selamat Datang di CineMax                ║");
        System.out.println("║        Sistem Booking Tiket Bioskop          ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        boolean jalan = true;
        while (jalan) {
            tampilkanMenu();
            int pilihan = inputAngka(">> Pilih menu: ");
            switch (pilihan) {
                case 1:
                    sistem.tampilkanFilm();
                    break;
                case 2:
                    menuBooking();
                    break;
                case 3:
                    menuCetakTiket();
                    break;
                case 4:
                    menuBatalkan();
                    break;
                case 5:
                    sistem.tampilkanSemuaTiket();
                    break;
                case 6:
                    menuTambahFilm();
                    break;
                case 7:
                    menuHapusFilm();
                    break;
                case 0:
                    System.out.println("\nSelamat menikmati film! Terima kasih telah menggunakan layanan kami.");
                    jalan = false;
                    break;
                default:
                    System.out.println("✗ Pilihan tidak valid, coba lagi.");
            }
        }
        sc.close();
    }

    // =============================================
    // Tampilkan Menu Utama
    // =============================================
    static void tampilkanMenu() {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║           MENU UTAMA             ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("║  1. Lihat Film Tersedia          ║");
        System.out.println("║  2. Booking Tiket                ║");
        System.out.println("║  3. Cetak / Cek Tiket            ║");
        System.out.println("║  4. Batalkan Tiket               ║");
        System.out.println("║  5. Lihat Semua Tiket Aktif      ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("║  ── Kelola Film (Admin) ──       ║");
        System.out.println("║  6. Tambah Film Baru             ║");
        System.out.println("║  7. Hapus Film                   ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("║  0. Keluar                       ║");
        System.out.println("╚══════════════════════════════════╝");
    }

    // =============================================
    // Menu Tambah Film Baru ← FITUR BARU
    // =============================================
    static void menuTambahFilm() {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║         TAMBAH FILM BARU         ║");
        System.out.println("╚══════════════════════════════════╝");

        System.out.print("Judul film       : ");
        String judul = sc.nextLine().trim();
        if (judul.isEmpty()) {
            System.out.println("✗ Judul tidak boleh kosong.");
            return;
        }

        System.out.print("Genre            : ");
        String genre = sc.nextLine().trim();

        // Validasi rating
        String rating = "";
        while (true) {
            System.out.print("Rating (SU/R13/R17/R21): ");
            rating = sc.nextLine().trim().toUpperCase();
            if (rating.equals("SU") || rating.equals("R13")
                    || rating.equals("R17") || rating.equals("R21")) {
                break;
            }
            System.out.println("⚠  Rating tidak valid! Pilih: SU, R13, R17, atau R21.");
        }

        int durasi = 0;
        while (durasi <= 0) {
            durasi = inputAngka("Durasi (menit)   : ");
            if (durasi <= 0)
                System.out.println("⚠  Durasi harus lebih dari 0.");
        }

        // Input jadwal
        int jumlahJadwal = 0;
        while (jumlahJadwal < 1 || jumlahJadwal > 6) {
            jumlahJadwal = inputAngka("Jumlah jadwal tayang (1-6): ");
            if (jumlahJadwal < 1 || jumlahJadwal > 6)
                System.out.println("⚠  Masukkan jumlah jadwal antara 1 hingga 6.");
        }

        String[] jadwals = new String[jumlahJadwal];
        System.out.println("Masukkan jadwal dalam format HH:MM (contoh: 13:00):");
        for (int i = 0; i < jumlahJadwal; i++) {
            while (true) {
                System.out.printf("  Jadwal ke-%d : ", i + 1);
                String jam = sc.nextLine().trim();
                if (jam.matches("^([01]\\d|2[0-3]):[0-5]\\d$")) {
                    jadwals[i] = jam;
                    break;
                }
                System.out.println("  ⚠  Format salah! Gunakan HH:MM (contoh: 14:30).");
            }
        }

        // Preview sebelum konfirmasi
        System.out.println("\n  ┌──────────────────────────────────────┐");
        System.out.println("  │         PREVIEW FILM BARU            │");
        System.out.println("  ├──────────────────────────────────────┤");
        System.out.printf("  │  Judul  : %-28s│%n", judul);
        System.out.printf("  │  Genre  : %-28s│%n", genre);
        System.out.printf("  │  Rating : %-28s│%n", rating);
        System.out.printf("  │  Durasi : %-28s│%n", durasi + " menit");
        System.out.print("  │  Jadwal : ");
        StringBuilder jadwalStr = new StringBuilder();
        for (int i = 0; i < jadwals.length; i++) {
            jadwalStr.append(jadwals[i]);
            if (i < jadwals.length - 1)
                jadwalStr.append(", ");
        }
        System.out.printf("%-28s│%n", jadwalStr.toString());
        System.out.println("  └──────────────────────────────────────┘");

        System.out.print("\nKonfirmasi tambah film? (y/n): ");
        if (!sc.nextLine().trim().equalsIgnoreCase("y")) {
            System.out.println("Penambahan film dibatalkan.");
            return;
        }

        Film filmBaru = new Film(judul, genre, rating, durasi, jadwals);
        boolean berhasil = sistem.tambahFilm(filmBaru);

        if (berhasil) {
            System.out.println("✓ Film \"" + judul + "\" berhasil ditambahkan!");
        } else {
            System.out.println("✗ Film dengan judul \"" + judul + "\" sudah ada di daftar.");
        }
    }

    // =============================================
    // Menu Hapus Film ← FITUR BARU
    // =============================================
    static void menuHapusFilm() {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║            HAPUS FILM            ║");
        System.out.println("╚══════════════════════════════════╝");

        sistem.tampilkanFilm();

        ArrayList<Film> films = sistem.getDaftarFilm();
        if (films.isEmpty()) {
            System.out.println("Tidak ada film yang bisa dihapus.");
            return;
        }

        int pilihan = inputAngka("\nPilih nomor film yang ingin dihapus (0 = batal): ");
        if (pilihan == 0) {
            System.out.println("Penghapusan dibatalkan.");
            return;
        }

        String namaFilm = sistem.getNamaFilm(pilihan);
        if (namaFilm.isEmpty()) {
            System.out.println("✗ Nomor film tidak valid.");
            return;
        }

        System.out.print("Yakin ingin menghapus film \"" + namaFilm + "\"? (y/n): ");
        if (!sc.nextLine().trim().equalsIgnoreCase("y")) {
            System.out.println("Penghapusan dibatalkan.");
            return;
        }

        int hasil = sistem.hapusFilm(pilihan);
        switch (hasil) {
            case 0:
                System.out.println("✓ Film \"" + namaFilm + "\" berhasil dihapus dari daftar.");
                break;
            case 1:
                System.out.println("✗ Nomor film tidak valid.");
                break;
            case 2:
                System.out.println("✗ Film \"" + namaFilm + "\" tidak dapat dihapus.");
                System.out.println("  Masih terdapat tiket aktif untuk film ini.");
                System.out.println("  Batalkan semua tiket terkait terlebih dahulu.");
                break;
        }
    }

    // =============================================
    // Menu Booking — alur lengkap (mendukung grup)
    // =============================================
    static void menuBooking() {

        sistem.tampilkanFilm();
        ArrayList<Film> films = sistem.getDaftarFilm();

        if (films.isEmpty()) {
            System.out.println("✗ Belum ada film yang tersedia untuk di-booking.");
            return;
        }

        int pilihanFilm = inputAngka("Pilih film (1-" + films.size() + "): ");
        if (pilihanFilm < 1 || pilihanFilm > films.size()) {
            System.out.println("✗ Pilihan film tidak valid.");
            return;
        }
        Film film = films.get(pilihanFilm - 1);

        System.out.println("\nJadwal tersedia untuk \"" + film.getJudul() + "\":");
        String[] jadwals = film.getJadwal();
        for (int i = 0; i < jadwals.length; i++)
            System.out.println("  " + (i + 1) + ". " + jadwals[i]);

        int pilihanJadwal = inputAngka("Pilih jadwal (1-" + jadwals.length + "): ");
        if (pilihanJadwal < 1 || pilihanJadwal > jadwals.length) {
            System.out.println("✗ Pilihan jadwal tidak valid.");
            return;
        }
        String jadwal = jadwals[pilihanJadwal - 1];

        System.out.println("\n-- INPUT DATA PEMESAN --");
        System.out.print("Nama lengkap : ");
        String nama = sc.nextLine();
        System.out.print("No. HP       : ");
        String noHp = sc.nextLine();
        System.out.print("Email        : ");
        String email = sc.nextLine();
        Penonton penonton = new Penonton(nama, noHp, email);

        int jumlahPenonton = inputAngka("\nJumlah penonton (1-40): ");
        if (jumlahPenonton < 1 || jumlahPenonton > 40) {
            System.out.println("✗ Jumlah penonton tidak valid (masukkan 1-40).");
            return;
        }

        sistem.tampilkanDenah();
        String[] kodeKursiList = new String[jumlahPenonton];
        System.out.println("\n-- PILIH KURSI (" + jumlahPenonton + " kursi diperlukan) --");
        for (int i = 0; i < jumlahPenonton; i++) {
            System.out.printf("  Kursi penonton ke-%d (contoh: A1, C5, D3): ", i + 1);
            kodeKursiList[i] = sc.nextLine().trim();
        }

        java.text.NumberFormat nf = java.text.NumberFormat
                .getCurrencyInstance(new java.util.Locale("id", "ID"));

        System.out.println("\n  ┌──────────────────────────────────────────────┐");
        System.out.println("  │             RINGKASAN PESANAN                │");
        System.out.println("  ├──────────────────────────────────────────────┤");
        System.out.printf("  │  Film    : %-34s│%n", film.getJudul());
        System.out.printf("  │  Jadwal  : %-34s│%n", jadwal);
        System.out.printf("  │  Pemesan : %-34s│%n", nama);
        System.out.printf("  │  Jumlah  : %-34s│%n", jumlahPenonton + " orang");
        System.out.println("  ├──────────────────────────────────────────────┤");
        System.out.printf("  │  %-5s  %-9s  %-8s  %-18s│%n",
                "No.", "Kursi", "Kelas", "Harga");
        System.out.println("  │  ─────────────────────────────────────────   │");

        double estimasiTotal = 0;
        boolean adaKursiInvalid = false;

        for (int i = 0; i < jumlahPenonton; i++) {
            String kode = kodeKursiList[i].toUpperCase();
            Kursi k = sistem.getKursi(kode);
            if (k == null) {
                System.out.printf("  │  %-5d  %-9s  %-8s  %-18s│%n",
                        i + 1, kode, "-", "⚠ Tidak Valid");
                adaKursiInvalid = true;
            } else {
                System.out.printf("  │  %-5d  %-9s  %-8s  %-18s│%n",
                        i + 1, kode, k.getKelas(), nf.format(k.getHarga()));
                estimasiTotal += k.getHarga();
            }
        }

        System.out.println("  ├──────────────────────────────────────────────┤");

        if (adaKursiInvalid) {
            System.out.println("  └──────────────────────────────────────────────┘");
            System.out.println("✗ Terdapat kode kursi tidak valid. Ulangi proses booking.");
            return;
        }

        System.out.printf("  │  TOTAL HARGA (%d tiket)  :  %-18s│%n",
                jumlahPenonton, nf.format(estimasiTotal));
        System.out.println("  └──────────────────────────────────────────────┘");

        System.out.print("\nKonfirmasi booking? (y/n): ");
        String konfirmasi = sc.nextLine().trim();
        if (!konfirmasi.equalsIgnoreCase("y")) {
            System.out.println("Booking dibatalkan.");
            return;
        }

        try {
            ArrayList<Tiket> tikets = sistem.bookMultipleTikets(
                    penonton, kodeKursiList, film, jadwal);

            if (tikets != null && !tikets.isEmpty()) {
                System.out.println("\n✓ Booking berhasil untuk " + tikets.size()
                        + " tiket! Simpan kode booking Anda.");
                sistem.tampilkanRingkasanGrup(tikets);

                System.out.print("\nCetak detail semua tiket? (y/n): ");
                if (sc.nextLine().trim().equalsIgnoreCase("y")) {
                    for (Tiket t : tikets)
                        t.cetakTiket();
                }
            }
        } catch (KursiSudahDipesanException e) {
            System.out.println("✗ " + e.getMessage());
            System.out.println("  Silakan ulangi proses booking dan pilih kursi lain.");
        }
    }

    // =============================================
    // Menu Cetak / Cek Tiket
    // =============================================
    static void menuCetakTiket() {
        System.out.print("Masukkan kode booking (contoh: CNX-0001): ");
        String kode = sc.nextLine().trim();
        sistem.cetakTiket(kode);
    }

    // =============================================
    // Menu Batalkan Tiket
    // =============================================
    static void menuBatalkan() {
        System.out.print("Masukkan kode booking yang ingin dibatalkan: ");
        String kode = sc.nextLine().trim();
        System.out.print("Yakin ingin membatalkan tiket " + kode + "? (y/n): ");
        String konfirmasi = sc.nextLine().trim();
        if (konfirmasi.equalsIgnoreCase("y")) {
            sistem.batalkanTiket(kode);
        } else {
            System.out.println("Pembatalan dibatalkan.");
        }
    }

    // =============================================
    // Helper — Input angka dengan validasi
    // =============================================
    static int inputAngka(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠  Input harus berupa angka! Coba lagi.");
            }
        }
    }
}
