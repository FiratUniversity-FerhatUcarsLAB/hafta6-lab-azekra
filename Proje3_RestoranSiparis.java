/*
 * Ad Soyad: [ENES ZUKRA]
 * Ogrenci No: [250541616]
 * Tarih: [24.11.2025] 
 * Aciklama: Gorev 3 - RestoranSipars
 */



import java.util.Scanner;

public class RestoranSistemi {

    // 1) Ana yemek fiyatı
    public static double getMainDishPrice(int secim) {
        switch (secim) {
            case 1: return 85;   // Izgara Tavuk
            case 2: return 120;  // Adana Kebap
            case 3: return 110;  // Levrek
            case 4: return 65;   // Mantı
            default: return 0;
        }
    }

    // 2) Başlangıç fiyatı
    public static double getAppetizerPrice(int secim) {
        switch (secim) {
            case 1: return 25;   // Çorba
            case 2: return 45;   // Humus
            case 3: return 55;   // Sigara Böreği
            default: return 0;
        }
    }

    // 3) İçecek fiyatı
    public static double getDrinkPrice(int secim) {
        switch (secim) {
            case 1: return 15;   // Kola
            case 2: return 12;   // Ayran
            case 3: return 35;   // Meyve Suyu
            case 4: return 25;   // Limonata
            default: return 0;
        }
    }

    // 4) Tatlı fiyatı
    public static double getDessertPrice(int secim) {
        switch (secim) {
            case 1: return 65;   // Künefe
            case 2: return 55;   // Baklava
            case 3: return 35;   // Sütlaç
            default: return 0;
        }
    }

    // 5) Combo sipariş kontrolü
    public static boolean isComboOrder(boolean anaVar, boolean icecekVar, boolean tatliVar) {
        return anaVar && icecekVar && tatliVar;
    }

    // 6) Happy Hour kontrolü
    public static boolean isHappyHour(int saat) {
        return saat >= 14 && saat <= 17;
    }

    // 7) Tüm indirimleri hesapla
    public static double calculateDiscount(double tutar, boolean combo, boolean ogrenci, int saat, int gun, double icecekFiyat) {
        double toplamIndirim = 0;

        // COMBO İNDİRİMİ
        if (combo) {
            toplamIndirim += tutar * 0.15;
        }

        // HAPPY HOUR (SADECE İÇECEKTE %20)
        if (isHappyHour(saat) && icecekFiyat > 0) {
            toplamIndirim += icecekFiyat * 0.20;
        }

        // ÖĞRENCİ İNDİRİMİ (HAFTA İÇİ %10)
        if (ogrenci) {
            if (gun >= 1 && gun <= 5) {  // Pzt–Cuma
                toplamIndirim += (tutar - toplamIndirim) * 0.10;
            }
        }

        // 200 TL ÜSTÜ %10 EK İNDİRİM
        if (tutar - toplamIndirim > 200) {
            toplamIndirim += (tutar - toplamIndirim) * 0.10;
        }

        return toplamIndirim;
    }

    // 8) Bahşiş önerisi (tutar %10)
    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }

    // ================== MAiN Metotu ==================

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // --- Kullanıcı seçimleri ---
        System.out.print("Ana Yemek (1-4, 0=Yok): ");
        int ana = sc.nextInt();

        System.out.print("Baslangic (0-3): ");
        int bas = sc.nextInt();

        System.out.print("Icecek (0-4): ");
        int icecek = sc.nextInt();

        System.out.print("Tatli (0-3): ");
        int tatli = sc.nextInt();

        System.out.print("Saat (8-23): ");
        int saat = sc.nextInt();

        System.out.print("Ogrenci misiniz? (E/H): ");
        char ogrC = sc.next().charAt(0);
        boolean ogrenci = (ogrC == 'E' || ogrC == 'e');

        System.out.print("Gun (1=Pzt ... 7=Paz): ");
        int gun = sc.nextInt();


        // ---    Ürün fiyatları ---
        double anaF = getMainDishPrice(ana);
        double basF = getAppetizerPrice(bas);
        double icecekF = getDrinkPrice(icecek);
        double tatliF = getDessertPrice(tatli);

        // --- Ara toplam ---
        double araToplam = anaF + basF + icecekF + tatliF;

        // --- kombo kontrolü -  --
        boolean combo = isComboOrder(anaF > 0, icecekF > 0, tatliF > 0);

        // --- Toplam indirim ---
        double indirim = calculateDiscount(araToplam, combo, ogrenci, saat, gun, icecekF);

        // --- Ödeme   tutarı ---
        double odenecek = araToplam - indirim;

        // --- Bahşiş ---
        double bahsis = calculateServiceTip(odenecek);

        // --- ÇIKTI ---
        System.out.println("\n=== SIPARIS OZETI ===");
        System.out.printf("Ara Toplam: %.2f TL\n", araToplam);
        System.out.printf("Toplam Indirim: %.2f TL\n", indirim);
        System.out.printf("Odenecek Tutar: %.2f TL\n", odenecek);
        System.out.printf("Bahsis Onerisi (%%10): %.2f TL\n", bahsis);
    }
}

