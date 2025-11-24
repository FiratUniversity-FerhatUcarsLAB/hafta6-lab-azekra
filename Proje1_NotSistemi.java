/*
 * Ad Soyad: [ENES ZUKRA]
 * Ogrenci No: [250541616]
 * Tarih: [24.11.2025] 
 * Aciklama: Gorev 1 - Not sistemi
 */



import java.util.Scanner;

public class NotDegerlendirme {


    public static double calculateAverage(double vize, double fin, double odev) {
        return vize * 0.30 + fin * 0.40 + odev * 0.30;
    }

    public static boolean isPassingGrade(double avg) {
        return avg >= 50;
    }

    public static String getLetterGrade(double avg) {
        if (avg >= 90) return "AA";
        else if (avg >= 80) return "BA";
        else if (avg >= 70) return "BB";
        else if (avg >= 60) return "CB";
        else return "FF";
    }

    public static boolean isHonorList(double avg, double v, double f, double o) {
        return avg >= 85 && v >= 70 && f >= 70 && o >= 70;
    }


    public static boolean hasRetakeRight(double avg) {
        return avg >= 40 && avg < 50;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Vize: ");
        double vize = input.nextDouble();

        System.out.print("Final: ");
        double fin = input.nextDouble();

        System.out.print("Odev: ");
        double odev = input.nextDouble();

        double ort = calculateAverage(vize, fin, odev);
        boolean passed = isPassingGrade(ort);
        String letter = getLetterGrade(ort);
        boolean honor = isHonorList(ort, vize, fin, odev);
        boolean retake = hasRetakeRight(ort);

        System.out.println("\n=== OGRENCI NOT RAPORU ===");
        System.out.printf("Vize Notu : %.1f\n", vize);
        System.out.printf("Final Notu : %.1f\n", fin);
        System.out.printf("Odev Notu : %.1f\n", odev);
        System.out.println("------------------------------");
        System.out.printf("Ortalama : %.1f\n", ort);
        System.out.println("Harf Notu : " + letter);
        System.out.println("Durum : " + (passed ? "GECTI" : "KALDI"));
        System.out.println("Onur Listesi : " + (honor ? "EVET" : "HAYIR"));
        System.out.println("Butunleme : " + (retake ? "VAR" : "YOK"));
    }
}
