package exception;

// =============================================
// Custom Exception: KursiSudahDipesanException
// Konsep OOP : Exception Handling
// Dilempar saat kursi yang dipilih sudah terisi
// =============================================
public class KursiSudahDipesanException extends Exception {

    public KursiSudahDipesanException(String kodeKursi) {
        super("Kursi " + kodeKursi + " sudah dipesan! Silakan pilih kursi lain.");
    }
}
