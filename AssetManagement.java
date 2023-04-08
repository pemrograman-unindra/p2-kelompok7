import java.time.LocalDate;
import java.util.Scanner;

public class AssetManagement {

    // method utama yang akan dipanggil ketika aplikasi pertama dijalankan
    public static void main(String[] args) {
        cetakJudul();
        persiapan();
        menu();
    }

    // tampilkan judul aplikasi
    private static void cetakJudul() {
        System.out.println();
        System.out.println("+------------------------------------------+");
        System.out.println("|              Asset Management            |");
        System.out.println("+------------------------------------------+");
        System.out.println("|            Tugas Pemrograman 2           |");
        System.out.println("|                 Kelompok 7               |");
        System.out.println("+--------------+---------------------------+");
        System.out.println("| NPM          | Nama                      |");
        System.out.println("+--------------+---------------------------+");
        System.out.println("| 202243500501 | Abdur Rosyid Fachriansyah |");
        System.out.println("| 202243570024 | Jeffry Luqman             |");
        System.out.println("| 202243500497 | Alfarobby                 |");
        System.out.println("| 202243500500 | Ahmad Badawi              |");
        System.out.println("| 202243500502 | Sangga Buana              |");
        System.out.println("| 202243500524 | Riyan Rizaldy             |");
        System.out.println("+--------------+---------------------------+");
    }

    // tampilkan pilih menu
    public static void menu() {
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("🏘  Pilih Menu");
        System.out.println("--------------------------------------------");
        System.out.println();
        System.out.println("1. Tampilkan Daftar Asset 📋");
        System.out.println("2. Lihat Rincian Asset 🗂️");
        System.out.println("3. Tambah Data Asset ➕");
        System.out.println("4. Ubah Data Asset 📝");
        System.out.println("5. Hapus Data Asset ❌");
        System.out.println();
        System.out.println("0. Keluar ⬅️");
        System.out.println();
        System.out.print("Silakan pilih menu (0-5) : ");
        switch (helper.bacaInput()) {
            case "1": clearScreen();
                      daftarAsset(true); break;
            case "2": clearScreen();
                      rincianAsset(); break;
            case "3": clearScreen();
                      tambahAsset(); break;
            case "4": clearScreen();
                      ubahAsset(); break;
            case "5": clearScreen();
                      hapusAsset(); break;
            case "0": keluar(); break;
            
            default: 
                clearScreen();
                System.out.println();
                cetakJudul();
                System.out.println("\nNomor menu tidak valid! silakan pilih menu dengan angka 0 sampai 4"); 
                menu(); break;
        }
    }
    
        private static void keluar() {
        Scanner s = new Scanner(System.in);
        String pilih;
        boolean selesai = true;
        
        System.out.println("\n=============================\n");
        
        System.out.print("Anda yakin ingin keluar ? [y/n] "); pilih = s.next();
        if(selesai = pilih.equalsIgnoreCase("y")) {
            selesai = true;
            System.out.println();
            System.out.println("Terima kasih telah menggunakan aplikasi asset management kami...");
            System.out.println("\ncopyright © 2023 Kelompok 7 X2E");
            System.exit(0);
        } else {
            clearScreen();
            cetakJudul();
            menu();
        }
    }

    private static void clearScreen() {
        // untuk clear screen (membersihkan tampilan di terminal)
        try {
            if(System.getProperty("os.name").contains("Windows")){
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            } else {
                System.out.println("\033\143");
            }
        } catch (Exception ex) {
            System.err.println("Tidak bisa clear screen");
        }
    }
    
    // tahun ini tahun berapa, terkait perhitungan akumulasi penyusutan
    private static int tahun_ini = LocalDate.now().getYear();

    // konstanta jumlah data kategori asset = 5 (scope dari aplikasi ini tidak sampai merubah isi data kategori asset)
    private static final int jumlah_data_kategori_asset = 5;
    private static AssetCategory[] data_kategori_asset = new AssetCategory[5];

    // variabel untuk kebutuhan cetak tabel agar rapih
    private static String[] judul_kolom_tabel_kategori_asset = new String[4];
    private static int[] lebar_kolom_tabel_kategori_asset = new int[4];
    private static String[][] isi_data_tabel_kategori_asset = new String[5][4];

    // inisiasi jumlah data asset = 0
    private static int jumlah_data_asset = 0;

    // inisiasi variabel data_asset dengan kapasitas 10 
    // kapasitas nya akan dilipatgandakan apabila kapasitasnya sudah penuh
    private static Asset[] data_asset = new Asset[10];

    // variabel untuk kebutuhan cetak tabel agar rapih
    private static String[] judul_kolom_tabel_asset = new String[7];
    private static int[] lebar_kolom_tabel_asset = new int[7];
    private static String[][] isi_data_tabel_asset = new String[10][7];

    // isi data kategori asset untuk digunakan setiap akan menambah atau merubah data asset
    private static void persiapan() {
        data_kategori_asset[0] = new AssetCategory("Tanah", "Tanpa Penyusutan", 0);
        data_kategori_asset[1] = new AssetCategory("Gedung", "Garis Lurus", 20);
        data_kategori_asset[2] = new AssetCategory("Kendaraan (Mobil)", "Garis Lurus", 8);
        data_kategori_asset[3] = new AssetCategory("Kendaraan (Sepeda Motor)", "Garis Lurus", 4);
        data_kategori_asset[4] = new AssetCategory("Peralatan", "Garis Lurus", 4);
        judul_kolom_tabel_kategori_asset = new String[]{"No.", "Nama Kategori", "Metode Penyusutan", "Umur Ekonomis"};
        lebar_kolom_tabel_kategori_asset = new int[]{3, 24, 17, 13};
        for (int i = 0; i < jumlah_data_kategori_asset; i++) {
            isi_data_tabel_kategori_asset[i][0] = Integer.toString(i + 1);
            isi_data_tabel_kategori_asset[i][1] = data_kategori_asset[i].nama;
            isi_data_tabel_kategori_asset[i][2] = data_kategori_asset[i].metode_penyusutan;
            if (data_kategori_asset[i].umur_ekonomis>0) {
                isi_data_tabel_kategori_asset[i][3] = Integer.toString(data_kategori_asset[i].umur_ekonomis) + " Tahun";
            } else {
                isi_data_tabel_kategori_asset[i][3] = "-";
            }
        }
        judul_kolom_tabel_asset = new String[]{"No.", "Nama Asset", "Tahun Perolehan", "Nilai Perolehan", "Penyusutan Per Tahun", "Akumulasi Penyusutan", "Nilai Buku"};
        lebar_kolom_tabel_asset = new int[]{3, 10, 15, 15, 20, 20, 15};
    }

    // tampilan input data asset
    private static Asset inputAsset() {
        Asset data = new Asset();
        data.kategori = inputKategori();
        data.nama = inputNama();
        data.tahun_perolehan = inputTahunPerolehan();
        data.nilai_perolehan = inputNilaiPerolehan();
        if (data.kategori.umur_ekonomis==0) {
            data.nilai_residu = 0;
            data.penyusutan_per_tahun = 0;
        } else {
            data.nilai_residu = inputNilaiResidu();
            data.penyusutan_per_tahun = (data.nilai_perolehan - data.nilai_residu) / data.kategori.umur_ekonomis;
        }
        if (data.nama.length() > lebar_kolom_tabel_asset[1]) {
            lebar_kolom_tabel_asset[1] = data.nama.length();
        }
        return data;
    }

    // tampilkan inputan untuk memilih kategori asset
    private static AssetCategory inputKategori() {

        // tampilkan pilihan kategori dengan tabel yang rapih
        helper.cetakTabel(judul_kolom_tabel_kategori_asset, lebar_kolom_tabel_kategori_asset, isi_data_tabel_kategori_asset, jumlah_data_kategori_asset);
        System.out.print("Pilih kategori (1-5) : "); String inputan = helper.bacaInput();

        int nilai = helper.toInteger(inputan);
        
        // keterangan kategori yang telah dipilih
        if (nilai == 1) System.out.println("\n1. Kategori yang anda pilih adalah Tanah dengan tanpa metode penyusutan =\n");
        else if (nilai == 2) System.out.println("\n2. Kategori yang anda pilih adalah Gedung dengan metode penyusutan garis lurus =\n");
        else if (nilai == 3) System.out.println("\n3. Kategori yang anda pilih adalah Kendaraan(mobil) dengan metode penyusutan garis lurus =\n");
        else if (nilai == 4) System.out.println("\n4. Kategori yang anda pilih adalah Kendaraan(motor) dengan metode penyusutan garis lurus =\n");
        else if (nilai == 5) System.out.println("\n5. Kategori yang anda pilih adalah Peralatan dengan metode penyusutan garis lurus =\n");
        // validasi input, apabila tidak valid maka beri pesan tidak valid dan kembali ke tampilan inputan
        if (!helper.isValidNumber(inputan) || nilai<0 || nilai > jumlah_data_kategori_asset) {
            System.out.println();
            System.out.println("Kategori tidak valid! silakan isi sesuai nomor kategori yang valid atau isi 0 untuk membatalkan."); 
            System.out.println();
            return inputKategori();

        // apabila diisi 0 maka kembali ke menu utama
        } else if (nilai==0) {
            cetakJudul();
            menu();
        }

        // jika valid maka tampilkan sesuai index
        return data_kategori_asset[nilai-1];
    }

    // tampilkan inputan untuk mengisi nama asset.
    private static String inputNama() {
        System.out.print("Masukan nama asset : "); 
        return helper.bacaInput();
    }

    // tampilkan inputan untuk mengisi tahun perolehan asset.
    private static int inputTahunPerolehan() {
        System.out.print("Masukan tahun perolehan asset : "); String inputan = helper.bacaInput();
        int nilai = helper.toInteger(inputan);

        // validasi input, apabila tidak valid maka beri pesan tidak valid dan kembali ke tampilan inputan
        if (!helper.isValidNumber(inputan) || nilai<1900 || nilai > tahun_ini) {
            System.out.println();
            System.out.println("Tahun perolehan tidak valid! silakan isi dengan tahun yang valid atau isi 0 untuk membatalkan."); 
            System.out.println();
            return inputTahunPerolehan();

        // apabila diisi 0 maka kembali ke menu utama
        } else if (nilai==0) {
            cetakJudul();
            menu();
        }

        return nilai;
    }

    // tampilkan inputan untuk mengisi nilai perolehan asset.
    private static int inputNilaiPerolehan() {
        System.out.print("Masukan nilai perolehan asset : "); String inputan = helper.bacaInput();
        int nilai = helper.toInteger(inputan);

        // validasi input, apabila tidak valid maka beri pesan tidak valid dan kembali ke tampilan inputan
        if (!helper.isValidNumber(inputan) || nilai<0) {
            System.out.println();
            System.out.println("Nilai perolehan tidak valid! silakan isi dengan angka yang valid atau isi 0 untuk membatalkan."); 
            System.out.println();
            return inputNilaiPerolehan();

        // apabila diisi 0 maka kembali ke menu utama
        } else if (nilai==0) {
            cetakJudul();
            menu();
        }

        return nilai;
    }

    // tampilkan inputan untuk mengisi nilai residu asset.
    private static int inputNilaiResidu() {
        System.out.print("Masukan nilai residu asset : "); String inputan = helper.bacaInput();
        int nilai = helper.toInteger(inputan);

        // validasi input, apabila tidak valid maka beri pesan tidak valid dan kembali ke tampilan inputan
        if (!helper.isValidNumber(inputan) || nilai<0) {
            System.out.println();
            System.out.println("Nilai residu tidak valid! silakan isi dengan angka yang valid atau isi 0 untuk membatalkan."); 
            System.out.println();
            return inputNilaiResidu();

        // apabila diisi 0 maka kembali ke menu utama
        } else if (nilai==0) {
            cetakJudul();
            menu();
        }

        return nilai;
    }

    // tampilkan inputan untuk memilih asset.
    private static int pilihNomorAsset() {
        daftarAsset(false);
        System.out.print("Masukan nomor asset : "); String inputan = helper.bacaInput();
        int nilai = helper.toInteger(inputan);

        // validasi input, apabila tidak valid maka beri pesan tidak valid dan kembali ke tampilan inputan
        if (!helper.isValidNumber(inputan) || nilai<0 || nilai>jumlah_data_asset) {
            System.out.println();
            System.out.println("Nomor asset tidak valid! silakan isi dengan angka yang valid atau isi 0 untuk membatalkan."); 
            System.out.println();
            return pilihNomorAsset();

        // apabila diisi 0 maka kembali ke menu utama
        } else if (nilai==0) {
            cetakJudul();
            menu();
        }

        return nilai;
    }

    // menampilkan daftar asset
    private static void daftarAsset(Boolean navigasi) {
        if (navigasi) {        
            System.out.println();
            System.out.println("📋 Daftar Asset");
        }

        for (int i = 0; i < jumlah_data_asset; i++) {
            int umur_ekonomis = data_asset[i].kategori.umur_ekonomis;
            int tahun_perolehan = data_asset[i].tahun_perolehan;
            double nilai_perolehan = Double.valueOf(data_asset[i].nilai_perolehan);
            double penyusutan_per_tahun = Double.valueOf(data_asset[i].penyusutan_per_tahun);
            double akumulasi_penyusutan = 0;
            double nilai_buku = nilai_perolehan;
            if (umur_ekonomis>0) {
                akumulasi_penyusutan = penyusutan_per_tahun * (tahun_ini - tahun_perolehan);
                nilai_buku = nilai_perolehan - akumulasi_penyusutan;
            }

            isi_data_tabel_asset[i][0] = Integer.toString(i + 1);
            isi_data_tabel_asset[i][1] = data_asset[i].nama;
            isi_data_tabel_asset[i][2] = Integer.toString(tahun_perolehan);
            isi_data_tabel_asset[i][3] = helper.formatAngka(nilai_perolehan);
            isi_data_tabel_asset[i][4] = helper.formatAngka(penyusutan_per_tahun);
            isi_data_tabel_asset[i][5] = helper.formatAngka(akumulasi_penyusutan);
            isi_data_tabel_asset[i][6] = helper.formatAngka(nilai_buku);
        }
        helper.cetakTabel(judul_kolom_tabel_asset, lebar_kolom_tabel_asset, isi_data_tabel_asset, jumlah_data_asset);

        if (navigasi) {
            menu();
        }
    }

    // menampilkan rincian asset
    private static void rincianAsset() {
        System.out.println();
        System.out.println("🗂️  Rincian Data Asset");

        // pilih asset yang mau diubah
        int id = pilihNomorAsset();
        System.out.println();

        // persiapkan data yang ingin ditampilkan
        Asset data = data_asset[id-1];
        String umur_ekonomis_string = "-";
        int umur_ekonomis = data.kategori.umur_ekonomis;
        int tahun_perolehan = data.tahun_perolehan;
        double penyusutan_per_tahun = data.penyusutan_per_tahun;
        double akumulasi_penyusutan = 0;
        double nilai_buku = 0;
        if (umur_ekonomis>0) {
            umur_ekonomis_string = umur_ekonomis + " Tahun";
            akumulasi_penyusutan = penyusutan_per_tahun * (tahun_ini - tahun_perolehan);
            nilai_buku = data.nilai_perolehan - akumulasi_penyusutan;
        }

        // tampilkan rincian dari asset yang dipilih
        System.out.println("Nama Asset           : " + data.nama);
        System.out.println("Nama Kategori Asset  : " + data.kategori.nama);
        System.out.println("Metode Penyusutan    : " + data.kategori.metode_penyusutan);
        System.out.println("Umur Ekonomis        : " + umur_ekonomis_string);
        System.out.println("Tahun Perolehan      : " + tahun_perolehan);
        System.out.println("Nilai Perolehan      : " + helper.formatAngka(Double.valueOf(data.nilai_perolehan)));
        System.out.println("Nilai Residu         : " + helper.formatAngka(Double.valueOf(data.nilai_residu)));
        System.out.println("Penyusutan Per Tahun : " + helper.formatAngka(penyusutan_per_tahun));
        System.out.println("Akumulasi Penyusutan : " + helper.formatAngka(akumulasi_penyusutan));
        System.out.println("Nilai Buku           : " + helper.formatAngka(nilai_buku));
        
        //pilih lanjut atau tidak
        Scanner s = new Scanner(System.in);
        String milih;
        boolean lagi = true;
        
        System.out.println("\n===============================\n");
        System.out.print("Apakah anda ingin melihat rincian asset lagi? [y/n] "); milih = s.next();
        
        if(lagi = milih.equalsIgnoreCase("y")) {
            lagi = true; clearScreen(); rincianAsset();
        }else{
            clearScreen(); cetakJudul(); menu();
        }
    }

    // tampilan untuk menambahkan data asset
    private static void tambahAsset() {
        System.out.println();
        System.out.println("➕ Tambah Data Asset");

        // pastikan kapasitas array nya masih cukup, apabila kurang maka lipatgandakan kapasitas nya terlebih dahulu
        if (jumlah_data_asset == data_asset.length) {
            isi_data_tabel_asset = new String[data_asset.length * 2][7];
            Asset[] data_asset_baru = new Asset[data_asset.length * 2];
            System.arraycopy(data_asset, 0, data_asset_baru, 0, data_asset.length);
            data_asset = data_asset_baru;
        }

        // input data asset dan tambahkan kedalam array
        data_asset[jumlah_data_asset] = inputAsset();
        jumlah_data_asset++;

        System.out.println();
        System.out.println("Data asset berhasil ditambahkan!");

        //lihat daftar asset yang telah ditambahkan
        daftarAsset(false);
        
        //pilih lanjut atau tidak
        Scanner s = new Scanner(System.in);
        String milih;
        boolean lagi = true;
        
        System.out.println("\n===============================\n");
        System.out.print("Apakah anda ingin tambah asset lagi? [y/n] "); milih = s.next();
        
        if(lagi = milih.equalsIgnoreCase("y")) {
            lagi = true; clearScreen(); tambahAsset();
        }else{
            clearScreen(); cetakJudul(); menu();
        }
    }

    // tampilan untuk merubah data asset
    private static void ubahAsset() {
        System.out.println();
        System.out.println("📝 Ubah Data Asset");

        // pilih asset yang mau diubah
        int id = pilihNomorAsset();

        // perbarui data asset sesuai inputan baru user
        data_asset[id-1] = inputAsset();

        System.out.println();
        System.out.println("Data asset berhasil diubah!");
        
        //lihat daftar asset yang telah diubah
        daftarAsset(false);
        
        //pilih lanjut atau tidak
        Scanner s = new Scanner(System.in);
        String milih;
        boolean lagi = true;
        
        System.out.println("\n===============================\n");
        System.out.print("Apakah anda ingin ubah asset lagi? [y/n] "); milih = s.next();
        
        if(lagi = milih.equalsIgnoreCase("y")) {
            lagi = true; clearScreen(); ubahAsset();
        }else{
            clearScreen(); cetakJudul(); menu();
        }
    }

    // tampilan untuk menghapus data asset
    private static void hapusAsset() {
        System.out.println();
        System.out.println("❌ Hapus Data Asset");

        // pilih asset yang mau diubah
        int id = pilihNomorAsset();

        // hapus data dengan cara membuat array baru dan mengisi nya dengan data yang lama selain data yang dihapus
        Asset[] data_asset_baru = new Asset[--jumlah_data_asset];
        System.arraycopy(data_asset, 0, data_asset_baru, 0, id - 1);
        System.arraycopy(data_asset, id, data_asset_baru, id - 1, jumlah_data_asset - id + 1);
        data_asset = data_asset_baru;

        System.out.println();
        System.out.println("Data asset berhasil dihapus!");
        
         //pilih lanjut atau tidak
        Scanner s = new Scanner(System.in);
        String milih;
        boolean lagi = true;
        
        System.out.println("\n===============================\n");
        System.out.print("Apakah anda ingin hapus asset lagi? [y/n] "); milih = s.next();
        
        if(lagi = milih.equalsIgnoreCase("y")) {
            lagi = true; clearScreen(); hapusAsset();
        }else{
            clearScreen(); cetakJudul(); menu();
        }
    }
}

// class untuk mendefinisikan objek asset
class Asset {

    public AssetCategory kategori; // kategori asset

    public String nama; // nama asset

    public int tahun_perolehan; // tahun perolehan

    public int nilai_perolehan; // nilai perolehan

    public int nilai_residu; // nilai residu

    public double penyusutan_per_tahun; // penyusutan per tahun
}

// class untuk mendefinisikan objek kategori asset
class AssetCategory {

    public String nama; // nama kategori asset

    public String metode_penyusutan; // metode penyusutan

    public int umur_ekonomis; // umur ekonomis

    public AssetCategory(String nama, String metode_penyusutan, int umur_ekonomis) {
        this.nama = nama;
        this.metode_penyusutan = metode_penyusutan;
        this.umur_ekonomis = umur_ekonomis;
    }
}

// class bantuan untuk penerapan DRY (Dont Repeat Your Self) principle 
// sehingga kodingan nya menjadi lebih rapih, tidak banyak pengulangan hal yang sama.
class helper {

    private static Scanner scanner;

    // pembacaan input oleh user
    public static String bacaInput() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner.nextLine();
    }

    // validasi apakah yang diinput oleh user merupakan angka atau bukan.
    public static Boolean isValidNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // konversi dari string ke int dengan mengabaikan error.
    public static int toInteger(String str) {
        int res = 0;
        try {
            res = Integer.parseInt(str);
        } catch (Exception e) {
            
        }
        return res;
    }

    // format angka pecahan dengan pemisah ribuan menggunakan titik tanpa desimal.
    public static String formatAngka(double angka) {
        return String.format("%,.0f", angka).replace(',', '.');
    }

    // cetak tabel biar rapih
    public static void cetakTabel(String[] judul_kolom, int[] lebar_kolom, String[][] isi_data, int jumlah_data) {
        cetakGaris(lebar_kolom);
        cetakBaris(judul_kolom, lebar_kolom);
        cetakGaris(lebar_kolom);
        if (jumlah_data>0) {        
            for (int i = 0; i < jumlah_data; i++) {
                cetakBaris(isi_data[i], lebar_kolom);
            }
        }
        cetakGaris(lebar_kolom);
    }

    // cetak garis dari suatu tabel
    public static void cetakGaris(int[] lebar_kolom) {
        for (int l : lebar_kolom) {
            System.out.print("+-");
            for (int i = 0; i < l; i++) {
                System.out.print("-");
            }
            System.out.print("-");
        }
        System.out.println("+");
    }

    // cetak baris dari suatu tabel
    public static void cetakBaris(String[] kolom, int[] lebar_kolom) {
        for (int i = 0; i < kolom.length; i++) {
            System.out.print("|");
            helper.cetakCell(kolom[i], lebar_kolom[i]);
        }
        System.out.println("|");
    }

    // cetak cell dari suatu tabel
    public static void cetakCell(String teks, int length) {
        int maxLength = (length - teks.length() + 1);

        // untuk angka maka rata kanan
        if ((teks.charAt(0) >= '0' && teks.charAt(0) <= '9') || teks.charAt(0) == '-') {
            for (int i = 0; i < maxLength; i++) {
                System.out.print(" ");
            }
            System.out.print(teks);
            System.out.print(" ");

        // untuk selain angka maka rata kiri
        } else {
            System.out.print(" ");
            System.out.print(teks);
            for (int i = 0; i < maxLength; i++) {
                System.out.print(" ");
            }
        }
    }
    

}