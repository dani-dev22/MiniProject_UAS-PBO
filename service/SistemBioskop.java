package service;

import model.Film;
import model.Kursi;
import model.KursiRegular;
import model.KursiVIP;
import model.Penonton;
import model.Tiket;
import exception.KursiSudahDipesanException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

// =============================================
// Class SistemBioskop
// Konsep OOP : Encapsulation, Collection (ArrayList)
// Berisi semua logika bisnis aplikasi bioskop:
// kelola film, kursi, booking, pembatalan, cetak
// =============================================
public class SistemBioskop {

    private ArrayList<Kursi> daftarKursi;
    private ArrayList<Tiket> daftarTiket;
    private ArrayList<Film> daftarFilm;
    private int counterBooking;

    // =============================================
    // Constructor
    // =============================================
    public SistemBioskop() {
        daftarKursi = new ArrayList<>();
        daftarTiket = new ArrayList<>();
        daftarFilm = new ArrayList<>();
        counterBooking = 1;
        inisialisasiKursi();
        inisialisasiFilm();
    }

    // Buat kursi Studio 1 — 40 kursi total
    private void inisialisasiKursi() {
        char[] barisRegular = { 'A', 'B', 'C' };
        char[] barisVIP = { 'D', 'E' };

        for (char b : barisRegular)
            for (int i = 1; i <= 8; i++)
                daftarKursi.add(new KursiRegular(b, i));

        for (char b : barisVIP)
            for (int i = 1; i <= 8; i++)
                daftarKursi.add(new KursiVIP(b, i));
    }

    // Data film yang sedang tayang
    private void inisialisasiFilm() {
        daftarFilm.add(new Film(
                "Avengers: Doomsday", "Action / Superhero", "R13", 150,
                new String[] { "13:00", "16:00", "19:00", "21:30" }));
        daftarFilm.add(new Film(
                "Jumbo", "Animasi / Keluarga", "SU", 95,
                new String[] { "11:00", "13:30", "16:00", "18:30" }));
        daftarFilm.add(new Film(
                "A Minecraft Movie", "Animasi / Adventure", "SU", 110,
                new String[] { "12:00", "14:30", "17:00", "19:30" }));
        daftarFilm.add(new Film(
                "Pengepungan di Bukit Duri", "Thriller / Drama", "R17", 120,
                new String[] { "14:00", "17:00", "20:00", "22:00" }));
    }

    // =============================================
    // FITUR 1 — Tampilkan Film yang Sedang Tayang
    // =============================================
    public void tampilkanFilm() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║              FILM YANG SEDANG TAYANG                   ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        if (daftarFilm.isEmpty()) {
            System.out.println("║  (Belum ada film yang ditambahkan)                  ║");
        } else {
            for (int i = 0; i < daftarFilm.size(); i++) {
                daftarFilm.get(i).tampilkanInfo(i + 1);
                if (i < daftarFilm.size() - 1)
                    System.out.println("  ─────────────────────────────────────────────────────");
            }
        }
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.printf("║  Harga: Regular (Baris A-C) : %-23s║%n", nf.format(40_000));
        System.out.printf("║         VIP     (Baris D-E) : %-23s║%n", nf.format(75_000));
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }

    public ArrayList<Film> getDaftarFilm() {
        return daftarFilm;
    }

    // =============================================
    // Helper publik — Ambil kursi berdasarkan kode
    // =============================================
    public Kursi getKursi(String kodeKursi) {
        return cariKursi(kodeKursi.toUpperCase());
    }

    // =============================================
    // FITUR BARU — Tambah Film
    // =============================================
    public boolean tambahFilm(Film film) {
        for (Film f : daftarFilm) {
            if (f.getJudul().equalsIgnoreCase(film.getJudul())) {
                return false;
            }
        }
        daftarFilm.add(film);
        return true;
    }

    // =============================================
    // FITUR BARU — Hapus Film
    // =============================================
    public int hapusFilm(int nomorFilm) {
        if (nomorFilm < 1 || nomorFilm > daftarFilm.size()) {
            return 1;
        }
        Film filmTarget = daftarFilm.get(nomorFilm - 1);
        for (Tiket t : daftarTiket) {
            if (t.isAktif() && t.getFilm().getJudul()
                    .equalsIgnoreCase(filmTarget.getJudul())) {
                return 2;
            }
        }
        daftarFilm.remove(nomorFilm - 1);
        return 0;
    }

    public String getNamaFilm(int nomorFilm) {
        if (nomorFilm < 1 || nomorFilm > daftarFilm.size())
            return "";
        return daftarFilm.get(nomorFilm - 1).getJudul();
    }

    // =============================================
    // FITUR 2 — Tampilkan Denah Kursi Studio
    // =============================================
    public void tampilkanDenah() {
        System.out.println();
        System.out.println("  ┌───────────────── LAYAR ──────────────────┐");
        System.out.println("  │                                           │");
        System.out.println("  └───────────────────────────────────────────┘");
        System.out.println();
        System.out.println("        [ 1 ][ 2 ][ 3 ][ 4 ][ 5 ][ 6 ][ 7 ][ 8 ]");
        System.out.println("        ──── ──── ──── ──── ──── ──── ──── ────");

        char[] semuaBaris = { 'A', 'B', 'C', 'D', 'E' };
        for (char b : semuaBaris) {
            if (b == 'D') {
                System.out.println("        ──── ──── ──── ──── ──── ──── ──── ────  <-- VIP");
            }
            System.out.printf("  %c   | ", b);
            for (Kursi k : daftarKursi) {
                if (k.getBaris() == b)
                    System.out.print(k.tampil());
            }
            String label = (b == 'D' || b == 'E') ? "  VIP" : "  REGULAR";
            System.out.println(label);
        }

        System.out.println();
        System.out.println("  [###] = Sudah Dipesan");
        System.out.println("  Format input kursi : A1, B3, C7, D2, E5, dst.");
    }

    // =============================================
    // FITUR 3A — Booking Tiket Tunggal
    // =============================================
    public Tiket bookTiket(Penonton penonton, String kodeKursi,
            Film film, String jadwal)
            throws KursiSudahDipesanException {

        Kursi kursi = cariKursi(kodeKursi.toUpperCase());

        if (kursi == null) {
            System.out.println("✗ Kode kursi tidak valid. Gunakan format: A1, B3, D5.");
            return null;
        }

        if (kursi.isTerisi()) {
            throw new KursiSudahDipesanException(kodeKursi.toUpperCase());
        }

        kursi.setTerisi(true);
        String kodeBooking = String.format("CNX-%04d", counterBooking++);
        Tiket tiket = new Tiket(kodeBooking, penonton, kursi, film, jadwal);
        daftarTiket.add(tiket);
        return tiket;
    }

    // =============================================
    // FITUR 3B — Booking Tiket Grup
    // =============================================
    public ArrayList<Tiket> bookMultipleTikets(Penonton penonton,
            String[] kodeKursiList,
            Film film, String jadwal)
            throws KursiSudahDipesanException {

        ArrayList<Kursi> kursiDipesan = new ArrayList<>();

        for (String kode : kodeKursiList) {
            String kodeUpper = kode.toUpperCase();

            Kursi kursi = cariKursi(kodeUpper);
            if (kursi == null) {
                System.out.println("✗ Kode kursi \"" + kodeUpper
                        + "\" tidak valid. Gunakan format: A1, B3, D5.");
                return null;
            }

            if (kursiDipesan.contains(kursi)) {
                System.out.println("✗ Kursi " + kodeUpper
                        + " dipilih lebih dari sekali dalam satu pemesanan.");
                return null;
            }

            if (kursi.isTerisi()) {
                throw new KursiSudahDipesanException(kodeUpper);
            }

            kursiDipesan.add(kursi);
        }

        ArrayList<Tiket> tikets = new ArrayList<>();
        for (Kursi kursi : kursiDipesan) {
            kursi.setTerisi(true);
            String kodeBooking = String.format("CNX-%04d", counterBooking++);
            Tiket tiket = new Tiket(kodeBooking, penonton, kursi, film, jadwal);
            daftarTiket.add(tiket);
            tikets.add(tiket);
        }

        return tikets;
    }

    // =============================================
    // FITUR 3C — Ringkasan Grup + Total Harga
    // =============================================
    public void tampilkanRingkasanGrup(ArrayList<Tiket> tikets) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        double total = 0;

        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║           RINGKASAN PEMESANAN GRUP                  ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.printf("║  %-4s  %-10s  %-9s  %-8s  %-13s║%n",
                "No.", "Kode", "Kursi", "Kelas", "Harga");
        System.out.println("║  ────  ──────────  ─────────  ────────  ─────────────║");

        for (int i = 0; i < tikets.size(); i++) {
            Tiket t = tikets.get(i);
            Kursi k = t.getKursi();
            double harga = k.getHarga();
            total += harga;
            System.out.printf("║  %-4d  %-10s  %-9s  %-8s  %-13s║%n",
                    i + 1,
                    t.getKodeBooking(),
                    k.getKodeKursi(),
                    k.getKelas(),
                    nf.format(harga));
        }

        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.printf("║  Jumlah Penonton : %-33s║%n", tikets.size() + " orang");
        System.out.printf("║  TOTAL HARGA     : %-33s║%n", nf.format(total));
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }

    // =============================================
    // FITUR 4 — Batalkan Tiket
    // =============================================
    public void batalkanTiket(String kodeBooking) {
        for (Tiket t : daftarTiket) {
            if (t.getKodeBooking().equalsIgnoreCase(kodeBooking)) {
                if (!t.isAktif()) {
                    System.out.println("⚠  Tiket ini sudah dibatalkan sebelumnya.");
                    return;
                }
                t.getKursi().setTerisi(false);
                t.setAktif(false);
                System.out.println("✓ Tiket " + kodeBooking + " berhasil dibatalkan.");
                System.out.println("  Kursi " + t.getKursi().getKodeKursi()
                        + " (" + t.getKursi().getKelas() + ") kini tersedia kembali.");
                return;
            }
        }
        System.out.println("✗ Kode booking tidak ditemukan.");
    }

    // =============================================
    // FITUR 5 — Cetak / Cek Tiket
    // =============================================
    public void cetakTiket(String kodeBooking) {
        for (Tiket t : daftarTiket) {
            if (t.getKodeBooking().equalsIgnoreCase(kodeBooking)) {
                t.cetakTiket();
                return;
            }
        }
        System.out.println("✗ Kode booking tidak ditemukan.");
    }

    // =============================================
    // FITUR 6 — Lihat Semua Tiket Aktif
    // =============================================
    public void tampilkanSemuaTiket() {
        ArrayList<Tiket> aktifList = new ArrayList<>();
        for (Tiket t : daftarTiket) {
            if (t.isAktif())
                aktifList.add(t);
        }

        if (aktifList.isEmpty()) {
            System.out.println("Belum ada tiket aktif.");
            return;
        }

        System.out.println("\n===== SEMUA TIKET AKTIF (" + aktifList.size() + " tiket) =====");
        for (Tiket t : aktifList)
            t.cetakTiket();
    }

    // =============================================
    // Helper privat — Cari kursi berdasarkan kode
    // =============================================
    private Kursi cariKursi(String kodeKursi) {
        for (Kursi k : daftarKursi) {
            if (k.getKodeKursi().equals(kodeKursi))
                return k;
        }
        return null;
    }
}
