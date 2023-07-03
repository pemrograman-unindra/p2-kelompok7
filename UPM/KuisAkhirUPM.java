import java.util.Scanner;

public class KuisAkhirUPM {

    public static void main(String[]args) {

        System.out.println("+--------------+---------------------------+-----------------+");
        System.out.println("| NPM          | Nama                      | Pembagian Tugas |");
        System.out.println("+--------------+---------------------------+-----------------+");
        System.out.println("| 202243500497 | Alfarobby                 | Menu No. 1      |");
        System.out.println("| 202243500500 | Ahmad Badawi              | Menu No. 3      |");
        System.out.println("| 202243500501 | Abdur Rosyid Fachriansyah | Menu No. ?      |");
        System.out.println("| 202243500502 | Sangga Buana              | Menu No. ?      |");
        System.out.println("| 202243500524 | Riyan Rizaldy             | Menu No. 4      |");
        System.out.println("| 202243570024 | Jeffry Luqman             | Menu No. 6      |");
        System.out.println("+--------------+---------------------------+-----------------+");

        Scanner s = new Scanner(System.in);

        System.out.println("Silahkan tentukan pilihan menu yang mau anda kerjakan");
        System.out.print("Mulai tentukan pilihan anda [y/t] ? "); String tampilkanMenu = s.nextLine();

        while (tampilkanMenu.equalsIgnoreCase("y")) {

            System.out.println();
            System.out.println("1 : Pilih > Tarif Sewa Kendaraan [if bersarang]");            
            System.out.println("2 : Pilih > Perubahan Kelipatan angka dengan '#' [while]");
            System.out.println("3 : Pilih > Membuat Kelipatan Angka Akhir [do while]");
            System.out.println("4 : Pilih > Perulangan 'X' dan 'O' [for]");            
            System.out.println("5 : Pilih > Data Penjualan Gamis [array 1D]");
            System.out.println("6 : Pilih > Pendaftaran Mahasiswa Baru [array 2D]");
            System.out.print("Masukkan pilihan anda : "); int menuDipilih = s.nextInt();
            System.out.println();

            switch (menuDipilih) {
                case 1 :
                    System.out.println("-------------------------------------");
                    System.out.println(" * MENGHITUNG TARIF SEWA KENDARAAN * ");
                    System.out.println("-------------------------------------");
                    System.out.println("* Promo IDUL ADHA*");
                    System.out.println("# Kendaraan >= 3 #");
                    System.out.println("- Lama Sewa >= 3 hari Potongan 200.000");
                    System.out.println("- Lama Sewa <= 2 hari Potongan 100.000");
                    System.out.println("# Kendaraan <= 2 #");
                    System.out.println("- Lama Sewa >= 3 hari Potongan 50.000");
                    System.out.println("- Lama Sewa <= 2 hari tidak dapat Potongan");
                    System.out.println();

                    System.out.print("Masukkan Penyewa Kendaraan     : "); String namaPenyewa = s.next();                                   
                    System.out.print("Masukkan Jumlah Kendaraan Sewa : "); int jumlahKendaraan = s.nextInt();
                    System.out.print("Masukkan Lama Sewa (Hari)      : "); int lamaSewa = s.nextInt();
                    System.out.println();

                    int harga = 200000*jumlahKendaraan;
                    System.out.println("=== RINCIAN PEMBAYARAN ===");
                    System.out.println();
                    System.out.println("Nama Penyewa          : " + namaPenyewa);
                    System.out.println("Harga Kendaraan       : " + harga);

                    int potongan;
                    if (jumlahKendaraan >= 3) {
                        if (lamaSewa >= 3) {
                            potongan = 200000;
                            System.out.println("Anda dapat potongan   : " + potongan);
                            System.out.println("------------------------------  +");
                        } else {
                            potongan = 100000;
                            System.out.println("Anda dapat potongan   : " + potongan);
                            System.out.println("------------------------------  +");
                        }
                    } else {
                        if (lamaSewa >= 3) {
                            potongan = 50000;
                            System.out.println("Anda dapat potongan   : " + potongan);
                            System.out.println("------------------------------  +");
                        } else {
                            potongan = 0;
                            System.out.println("----------------------------");
                        }
                    }

                    int tarif = harga-potongan;
                    System.out.println("Tarif yang anda bayar : " + tarif);
                    System.out.println();
                    break;

                case 2 :
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println(" * MEMBUAT PERUBAHAN KELIPATAN ANGKA dengan '#' SESUAI INPUTAN * ");
                    System.out.println("-----------------------------------------------------------------");
                                
                    // TULIS KODINGAN NYA DISINI

                    break;

                case 3 :
                    System.out.println("-----------------------------------");
                    System.out.println(" * MEMBUAT KELIPATAN ANGKA AKHIR * ");
                    System.out.println("-----------------------------------");
                    System.out.println();            
                    System.out.print("input angka akhir = "); int akhir = s.nextInt();
                    System.out.print("input angka batas = "); int batas = s.nextInt();
                    System.out.println();
                    int idx = akhir;
                    do {
                        if (idx>=akhir) {
                            System.out.print( idx + " ");
                            idx+=akhir;
                        }
                    } while (idx<=batas);
                    System.out.println();
                    System.out.println("-----------------------------------");
                    System.out.println();
                    break;

                case 4 :
                    System.out.println("------------------------------------");    
                    System.out.println(" * MEMBUAT PERULANGAN 'X' DAN 'O' * ");
                    System.out.println("------------------------------------");
                                
                    System.out.println();
                    System.out.print("Masukkan jumlah baris: ");
                    int baris = s.nextInt();

                    System.out.print("Masukkan jumlah kolom: ");
                    int kolom = s.nextInt();

                    for (int i = 0; i < baris; i++) {
                        for (int j = 0; j < kolom; j++) {
                            if (i == j || i + j == baris - 1) {
                                System.out.print("X ");
                            } else {
                                System.out.print("O ");
                            }
                        }
                        System.out.println();
                    }
                    System.out.println();
                    System.out.println("-----------------------------------");
                    System.out.println();
                    break;

                case 5 :
                    System.out.println("-------------------------------------");
                    System.out.println(" * DATA PEMESANAN GAMIS / SARIMBIT * ");
                    System.out.println("------------------------------------");
                                
                    // TULIS KODINGAN NYA DISINI

                    break;

                case 6 :
                    System.out.println("-------------------------------------");
                    System.out.println(" * DATA PENDAFTARAN MAHASISWA BARU * ");
                    System.out.println("-------------------------------------");

                    System.out.print("Masukkan Jumlah Mahasiswa : "); int jumlahMahasiswa = s.nextInt();
                    System.out.println();
                    String[][] dataMahasiswa = new String[jumlahMahasiswa][3];
                    for (int i = 0; i < jumlahMahasiswa; i++) {
                        System.out.println("Masukan Data Mahasiswa Ke "+(i+1));
                        System.out.print("NIM     : "); dataMahasiswa[i][0] = s.next();
                        System.out.print("NAMA    : "); dataMahasiswa[i][1] = s.next();
                        System.out.print("JURUSAN : "); dataMahasiswa[i][2] = s.next();
                        System.out.println();
                    }
                    System.out.println("Data Mahasiswa Baru Yang Mendaftar");
                    System.out.println("------------------------------------------------");
                    System.out.println("NIM\t\tNAMA\t\tJURUSAN");
                    System.out.println("------------------------------------------------");
                    for (String[] dm : dataMahasiswa) {
                        System.out.println(dm[0]+"\t\t"+dm[1]+"\t\t"+dm[2]);
                    }
                    System.out.println("------------------------------------------------");
                    break;

                default :
                    System.out.println("Nomor menu tidak valid silahkan pilih menu dengan angka 1 sampai 6.");
                    break;
            }

            System.out.print("Kembali ke menu [y/t] ? ");
            tampilkanMenu = s.next();
        }
        System.out.println();
        System.out.println("Terima kasih...");
    }
}