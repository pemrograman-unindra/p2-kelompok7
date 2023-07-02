// import scanner
import java.util.Scanner;
public class KuisAkhirUPM {

        // tampilkan judul menu
        private static void cetakJudul(String judul) {
            System.out.println("--------------------------------------------------------------");
            System.out.println(judul);
            System.out.println("--------------------------------------------------------------");
            System.out.println();
        }

        private static Scanner s = new Scanner(System.in);

        public static void main(String[]args) {
            System.out.println("Silahkan tentukan pilihan menu yang mau anda kerjakan");
            System.out.print("Mulai tentukan pilihan anda [y/t] ? "); String mulai = s.nextLine();
            while( mulai.equalsIgnoreCase("y") ) {
                    clearScreen();
                    cetakJudul("Pilih Menu");
                    System.out.println("1 : Pilih > Tarif Sewa Kendaraan [if bersarang]");            
                    System.out.println("2 : Pilih > Perubahan Kelipatan angka dengan '#' [while]");
                    System.out.println("3 : Pilih > Membuat Kelipatan Angka Akhir [do while]");
                    System.out.println("4 : Pilih > Perulangan 'X' dan 'O' [for]");            
                    System.out.println("5 : Pilih > Data Penjualan Gamis [array 10]");
                    System.out.println("6 : Pilih > Pendaftaran Mahasiswa Baru [array 20]");
                    System.out.println();
                    System.out.print("Masukkan pilihan anda : "); String pilih = s.next();
                    switch(pilih) {
                        case "1": boolean lanjut = true;
                                    while(lanjut) {
                                        clearScreen();
                                        cetakJudul(" * MENGHITUNG TARIF SEWA KENDARAAN * ");
                                        System.out.println("* Promo Idul Adha *");                                    
                                        System.out.println("# Kendaraan >= 3 #");
                                        System.out.println("- Lama Sewa >= 3 hari Potongan 200.000 ");                        
                                        System.out.println("- Lama Sewa <= 2 hari Potongan 100.000 ");                                    
                                        System.out.println("# Kendaraan <= 2 #");
                                        System.out.println("- Lama Sewa >= 3 hari Potongan 50.000");                                    
                                        System.out.println("- Lama Sewa <= 2 hari tidak dapat Potongan ");
                                        System.out.println();
                                        
                                        System.out.print("Masukkan Penyewa Kendaraan     : "); String namaSewa = s.next();                                   
                                        System.out.print("Masukkan Jumlah Kendaraan Sewa : "); int jumlahKendaraan = s.nextInt();
                                        System.out.print("Masukkan Lama Sewa (Hari)      : "); int lamaSewa = s.nextInt();
                                        System.out.println();

                                        int harga = 200000*jumlahKendaraan;
                                        System.out.println("=== RINCIAN PEMBAYARAN ===");
                                        System.out.println();
                                        System.out.println("Nama Penyewa          : " + namaSewa);
                                        System.out.println("Harga Kendaraan       : " + harga);

                                        int potongan;
                                        if( jumlahKendaraan >= 3 ) {
                                            if( lamaSewa >= 3 ) {
                                                potongan = 200000;
                                                System.out.println("Anda dapat potongan   : " + potongan);
                                                System.out.println("------------------------------  +");
                                            } else {
                                                potongan = 100000;
                                                System.out.println("Anda dapat potongan   : " + potongan);
                                                System.out.println("------------------------------  +");
                                            }
                                        } else {
                                            if( lamaSewa >= 3 ) {
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
                                        
                                        System.out.print("Input pilihan lagi [y/t] ? "); String lagi = s.next();
                                        if( lagi.equalsIgnoreCase("y")) {
                                            lanjut = true;
                                        } else {
                                            lanjut = false;
                                        }
                                  }
                                    break;

                        case "2": lanjut = true;
                                    while(lanjut) {
                                        clearScreen();
                                        cetakJudul(" * MEMBUAT PERUBAHAN KELIPATAN ANGKA dengan '#' SESUAI INPUTAN * ");
                                        
                                        //ketik disini






                                        
                                        System.out.println();
                                        
                                        System.out.print("Input pilihan lagi [y/t] ? "); String lagi = s.next();
                                        if( lagi.equalsIgnoreCase("y")) {
                                            lanjut = true;
                                        } else {
                                            lanjut = false;
                                        }
                                    }
                                    break;

                        case "3": lanjut = true;
                                    while(lanjut) {
                                        clearScreen();
                                        cetakJudul(" * MEMBUAT KELIPATAN ANGKA AKHIR * ");
                                        
                                        //ketik disini


                                        


                                        

                                        System.out.println();
                                        
                                        System.out.print("Input pilihan lagi [y/t] ? "); String lagi = s.next();
                                        if( lagi.equalsIgnoreCase("y")) {
                                            lanjut = true;
                                        } else {
                                            lanjut = false;
                                        }
                                    }
                                    break;

                        case "4": lanjut = true;
                                    while(lanjut) {
                                        clearScreen();
                                        cetakJudul(" * MEMBUAT PERULANGAN 'X' DAN 'O' * ");
                                        
                                        //ketik disini






                                        System.out.println();
                                        
                                        System.out.print("Input pilihan lagi [y/t] ? "); String lagi = s.next();
                                        if( lagi.equalsIgnoreCase("y")) {
                                            lanjut = true;
                                        } else {
                                            lanjut = false;
                                        }
                                    }
                                    break;

                        case "5": lanjut = true;
                                    while(lanjut) {
                                        clearScreen();
                                        cetakJudul(" * DATA PEMESANAN GAMIS / SARIMBIT * ");
                                        
                                        //ketik disini







                                        System.out.println();
                                        
                                        System.out.print("Input pilihan lagi [y/t] ? "); String lagi = s.next();
                                        if( lagi.equalsIgnoreCase("y")) {
                                            lanjut = true;
                                        } else {
                                            lanjut = false;
                                        }
                                    }
                                    break;

                        case "6": lanjut = true;
                                    while(lanjut) {
                                        clearScreen();
                                        cetakJudul(" * DATA PENDAFTARAN MAHASISWA BARU * ");

                                        //ketik disini





                        
                                        System.out.println();
                                        
                                        System.out.print("Input pilihan lagi [y/t] ? "); String lagi = s.next();
                                        if( lagi.equalsIgnoreCase("y")) {
                                            lanjut = true;
                                        } else {
                                            lanjut = false;
                                        }
                                    }
                                    break;

                        default: System.out.println("Nomor menu tidak valid silahkan pilih menu dengan angka 1 sampai 6.");
                    }

                    System.out.print("Kembali ke menu [y/t] ? ");
                    mulai = s.next();
            }
            System.out.println("Terima kasih...");
            System.exit(0);
        }

            // untuk clear screen (membersihkan tampilan di terminal)
            private static void clearScreen() {
                try {
                    // apabila menggunakan os windows maka lakukan perintah cls di cmd
                    if (System.getProperty("os.name").contains("Windows")) {
                        new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();

                    // apabila tidak menggunakan os windows dan support kode ANSI maka clear menggunakan kode ANSI
                    } else if (isSupportANSICode()) {
                        System.out.print("\033\143");
                    }
                } catch (Exception ex) {
                    System.err.println("Tidak bisa clear screen");
                }
            }

            // cek apakah console yang digunakan support kode ANSI atau tidak
            // untuk windows umum nya tidak support https://superuser.com/questions/413073/windows-console-with-ansi-colors-handling
            private static boolean isSupportANSICode() {
                return System.console() != null && System.getenv().get("TERM") != null;
            }
}