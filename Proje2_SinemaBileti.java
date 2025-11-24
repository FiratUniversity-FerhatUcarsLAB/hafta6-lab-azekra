/*
 * Ad Soyad: [ENES ZUKRA]
 * Ogrenci No: [250541616]
 * Tarih: [24.11.2025] 
 * Aciklama: Gorev 2 - SinemaBileti
 */
import java.util.Scanner;

public class SinemaBileti {

    // 1 Hafta sonu kontrolü
    public static boolean isWeekend(int gun) {
        return gun == 6 || gun == 7; // Cumartesi=6, Pazar=7
    }

    // 2 Matine kontrolü
    public static boolean isMatinee(int saat) {
        return saat < 12; // 12:00 öncesi matine
    }

    // 3) Temel fiyat hesaplama
    public static double calculateBasePrice(int gun, int saat) {
        boolean weekend = isWeekend(gun);
        boolean matinee = isMatinee(saat);

        if (!weekend && matinee) return 45;        // Hafta içi matine
        else if (!weekend && !matinee) return 65;   // Hafta içi normal
        else if (weekend && matinee) return 55;      // Hafta sonu matine
        else return 85;                               // Hafta sonu normal
    }

    // 4 İndirim hesaplama
    public static double calculateDiscount(int yas, int meslek, int gun) {
        double indirimOrani = 0;

        // Yaş indirimleri (her gün geçerli)
        if (yas >= 65) {
            indirimOrani = 0.30;
        } else if (yas < 12) {
            indirimOrani = 0.25;
        } else {
            // Meslek indirimleri
            switch (meslek) {
                case 1: // Öğrenci
                    if (gun >= 1 && gun <= 4) indirimOrani = 0.20; // Pzt-Perş
                    else indirimOrani = 0.15;                      // Cuma-Pazar
                    break;

                case 2: // Öğretmen
                    if (gun == 3) indirimOrani = 0.35; // Çarşamba
                    break;

                case 3: // Diğer
                    indirimOrani = 0;
                    break;
            }
        }

        return indirimOrani;
    }

    public static double getFormatExtra(int tur) {
        switch (tur) {
            case 2: return 25;  // 3D
            case 3: return 35;  // IMAX
            case 4: return 50;  // 4DX
            default: return 0;  // 2D
        }
    }

    public static double calculateFinalPrice(
            int gun, int saat, int yas, int meslek, int tur) {

        double base = calculateBasePrice(gun, saat);
        double discountRate = calculateDiscount(yas, meslek, gun);
        double priceAfterDiscount = base - (base * discountRate);
        double extra = getFormatExtra(tur);

        return priceAfterDiscount + extra;
    }


    public static void generateTicketInfo(
            int gun, int saat, int yas, int meslek, int tur) {

        double base = calculateBasePrice(gun, saat);
        double discountRate = calculateDiscount(yas, meslek, gun);
        double discounted = base - (base * discountRate);
        double extra = getFormatExtra(tur);
        double total = calculateFinalPrice(gun, saat, yas, meslek, tur);

        System.out.println("\n=== SINEMA BILETI ===");
        System.out.printf("Temel Fiyat: %.2f TL\n", base);
        System.out.printf("Indirim Orani: %.0f%%\n", discountRate * 100);
        System.out.printf("Indirimli Fiyat: %.2f TL\n", discounted);
        System.out.printf("Format Ucreti: %.2f TL\n", extra);
        System.out.println("----------------------");
        System.out.printf("TOPLAM FIYAT: %.2f TL\n", total);
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Gun (1=Pzt ... 7=Paz): ");
        int gun = sc.nextInt();

        System.out.print("Saat (8-23): ");
        int saat = sc.nextInt();

        System.out.print("Yas: ");
        int yas = sc.nextInt();

        System.out.print("Meslek (1=Ogrenci, 2=Ogretmen, 3=Diger): ");
        int meslek = sc.nextInt();

        System.out.print("Film Turu (1=2D, 2=3D, 3=IMAX, 4=4DX): ");
        int tur = sc.nextInt();

        generateTicketInfo(gun, saat, yas, meslek, tur);
    }
}

