import java.time.LocalDate;
import java.util.Scanner;

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

// class utama dari aplikasi Asset Management
public class AssetManagement {

    // method utama yang akan dipanggil ketika aplikasi pertama dijalankan
    public static void main(String[] args) {
        cetakJudul();
        persiapan();
        tahan();
        menu();
    }

    // tampilkan judul aplikasi
    private static void cetakJudul() {
        clearScreen();
        System.out.println(fmt("                                            ", BOLD, BG_MAGENTA));
        System.out.println(fmt("               Asset Management             ", BOLD, BG_MAGENTA));
        System.out.println(fmt("                                            ", BOLD, BG_MAGENTA));
        System.out.println();
        System.out.println(fmt("             Tugas Pemrograman 2            ", BOLD, HI_CYAN));
        System.out.println(fmt("                  Kelompok 7                ", ITALIC, HI_CYAN));
        System.out.println(fmt("+--------------+---------------------------+", HI_GREEN));
        System.out.println(fmt("| NPM          | Nama                      |", HI_GREEN));
        System.out.println(fmt("+--------------+---------------------------+", HI_GREEN));
        System.out.println(fmt("| 202243500497 | Alfarobby                 |", HI_GREEN));
        System.out.println(fmt("| 202243500500 | Ahmad Badawi              |", HI_GREEN));
        System.out.println(fmt("| 202243500501 | Abdur Rosyid Fachriansyah |", HI_GREEN));
        System.out.println(fmt("| 202243500502 | Sangga Buana              |", HI_GREEN));
        System.out.println(fmt("| 202243500524 | Riyan Rizaldy             |", HI_GREEN));
        System.out.println(fmt("| 202243570024 | Jeffry Luqman             |", HI_GREEN));
        System.out.println(fmt("+--------------+---------------------------+", HI_GREEN));
    }

    // tampilkan judul menu
    private static void cetakJudul(String judul) {
        clearScreen();
        System.out.println(fmt("--------------------------------------------", BOLD, HI_GREEN));
        System.out.println(fmt(judul, BOLD, ITALIC, HI_GREEN));
        System.out.println(fmt("--------------------------------------------", BOLD, HI_GREEN));
        System.out.println();
    }

    // tampilkan pilih menu
    private static void menu() {
        cetakJudul("Pilih Menu 💻");
        System.out.println("1. Tampilkan Daftar Asset 🗂️");
        System.out.println("2. Lihat Rincian Asset 📋");
        System.out.println("3. Tambah Data Asset ➕");
        System.out.println("4. Ubah Data Asset 📝");
        System.out.println("5. Hapus Data Asset ❌");
        System.out.println();
        System.out.println("0. Keluar ⬅️");
        System.out.println();
        System.out.print(fmt("Silakan pilih menu (0-5) : ", HI_MAGENTA));
        switch (bacaInput()) {
            case "1": daftarAsset(true); break;
            case "2": rincianAsset(); break;
            case "3": tambahAsset(); break;
            case "4": ubahAsset(); break;
            case "5": hapusAsset(); break;
            case "0": keluar(); break;
            default: 
                pesanKesalahan("Nomor menu tidak valid! silakan pilih menu dengan angka 0 sampai 5.");
                menu(); break;
        }
    }

    // menampilkan daftar asset
    private static void daftarAsset(Boolean navigasi) {
        if (navigasi) {        
            cetakJudul("Daftar Asset 🗂️");
        }

        for (int i = 0; i < jumlah_data_asset; i++) {
            int umur_ekonomis = data_asset[i].kategori.umur_ekonomis;
            int tahun_perolehan = data_asset[i].tahun_perolehan;
            double nilai_perolehan = data_asset[i].nilai_perolehan;
            double nilai_residu = data_asset[i].nilai_residu;
            double penyusutan_per_tahun = data_asset[i].penyusutan_per_tahun;
            double akumulasi_penyusutan = 0;
            double nilai_buku = nilai_perolehan;
            
            // jika umur ekonomis nya lebih dari 0, maka hitung akumulasi penyusutan nya
            if (umur_ekonomis>0) {
                akumulasi_penyusutan = penyusutan_per_tahun * (tahun_ini - tahun_perolehan);
                nilai_buku = nilai_perolehan - akumulasi_penyusutan;

                // nilai buku maksimal senilai dengan nilai residu
                if (nilai_buku < nilai_residu) {
                    akumulasi_penyusutan = (nilai_perolehan-nilai_residu);
                    nilai_buku = nilai_residu;
                }
            }
            isi_data_tabel_asset[i][0] = Integer.toString(i + 1);
            isi_data_tabel_asset[i][1] = data_asset[i].nama;
            isi_data_tabel_asset[i][2] = Integer.toString(tahun_perolehan);
            isi_data_tabel_asset[i][3] = formatAngka(nilai_perolehan);
            isi_data_tabel_asset[i][4] = formatAngka(penyusutan_per_tahun);
            isi_data_tabel_asset[i][5] = formatAngka(akumulasi_penyusutan);
            isi_data_tabel_asset[i][6] = formatAngka(nilai_buku);
        }
        cetakTabel(judul_kolom_tabel_asset, lebar_kolom_tabel_asset, isi_data_tabel_asset, jumlah_data_asset);

        if (navigasi) {
            tahan();
            menu();
        }
    }

    // menampilkan rincian asset
    private static void rincianAsset(Asset... a) {
        Asset data = new Asset();
        if (a.length>0) {
            data = a[0];
        } else {            
            cetakJudul("Rincian Data Asset 📋");

            // pilih asset yang mau diubah
            int id = pilihNomorAsset("rincian");
            System.out.println();

            // persiapkan data yang ingin ditampilkan
            data = data_asset[id-1];
        }
        String umur_ekonomis_string = "-";
        int umur_ekonomis = data.kategori.umur_ekonomis;
        int tahun_perolehan = data.tahun_perolehan;
        double nilai_perolehan = data.nilai_perolehan;
        double nilai_residu = data.nilai_residu;
        double penyusutan_per_tahun = data.penyusutan_per_tahun;
        double akumulasi_penyusutan = 0;
        double nilai_buku = nilai_perolehan;
        
        // jika umur ekonomis nya lebih dari 0, maka hitung akumulasi penyusutan nya
        if (umur_ekonomis>0) {
            umur_ekonomis_string = umur_ekonomis + " Tahun";
            akumulasi_penyusutan = penyusutan_per_tahun * (tahun_ini - tahun_perolehan);
            nilai_buku = data.nilai_perolehan - akumulasi_penyusutan;

            // nilai buku maksimal senilai dengan nilai residu
            if (nilai_buku < nilai_residu) {
                akumulasi_penyusutan = (nilai_perolehan-nilai_residu);
                nilai_buku = nilai_residu;
            }
        }

        // tampilkan rincian dari asset yang dipilih
        System.out.println("Nama Asset           : " + data.nama);
        System.out.println("Nama Kategori Asset  : " + data.kategori.nama);
        System.out.println("Metode Penyusutan    : " + data.kategori.metode_penyusutan);
        System.out.println("Umur Ekonomis        : " + umur_ekonomis_string);
        System.out.println("Tahun Perolehan      : " + tahun_perolehan);
        System.out.println("Nilai Perolehan      : " + formatAngka(nilai_perolehan));
        System.out.println("Nilai Residu         : " + formatAngka(nilai_residu));
        System.out.println("Penyusutan Per Tahun : " + formatAngka(penyusutan_per_tahun));
        System.out.println("Akumulasi Penyusutan : " + formatAngka(akumulasi_penyusutan));
        System.out.println("Nilai Buku           : " + formatAngka(nilai_buku));
        
        if (a.length==0) {
            pesanSukses("melihat rincian asset", "");
            rincianAsset();
        }
    }

    // tampilan untuk menambahkan data asset
    private static void tambahAsset() {
        cetakJudul("Tambah Data Asset ➕");

        // pastikan kapasitas array nya masih cukup, apabila kurang maka lipatgandakan kapasitas nya terlebih dahulu
        if (jumlah_data_asset == data_asset.length) {
            isi_data_tabel_asset = new String[data_asset.length * 2][7];
            Asset[] data_asset_baru = new Asset[data_asset.length * 2];
            System.arraycopy(data_asset, 0, data_asset_baru, 0, data_asset.length);
            data_asset = data_asset_baru;
        }

        // input data asset dan tambahkan kedalam array
        Asset data = inputAsset();
        data_asset[jumlah_data_asset] = data;
        jumlah_data_asset++;

        // tampilkan pesan sukses dan konfirmasi apakah ingin menambah data asset kembali
        pesanSukses("menambah data asset", "Data asset berhasil ditambahkan!", data);

        // jika pilih y, maka lanjut menambah data asset kembali
        tambahAsset();
    }

    // tampilan untuk merubah data asset
    private static void ubahAsset() {
        cetakJudul("Ubah Data Asset 📝");

        // pilih asset yang mau diubah
        int id = pilihNomorAsset("ubah");

        // perbarui data asset sesuai inputan baru user
        Asset data = inputAsset();
        data_asset[id-1] = data;

        // tampilkan pesan sukses dan konfirmasi apakah ingin merubah data asset kembali
        pesanSukses("merubah data asset", "Data asset berhasil diubah!", data);

        // jika pilih y, maka lanjut merubah data asset kembali
        tambahAsset();
    }

    // tampilan untuk menghapus data asset
    private static void hapusAsset() {
        cetakJudul("Hapus Data Asset ❌");

        // pilih asset yang mau diubah
        int id = pilihNomorAsset("hapus");

        // konfirmasi apakah yakin ingin menghapus data asset
        System.out.print(fmt("Apakah anda yakin ingin menghapus "+data_asset[id-1].nama+" ? [y/n] : ", HI_YELLOW));
        if (bacaInput().equalsIgnoreCase("y")) {
            // hapus data dengan cara membuat array baru dan mengisi nya dengan data yang lama selain data yang dihapus
            Asset[] data_asset_baru = new Asset[--jumlah_data_asset];
            System.arraycopy(data_asset, 0, data_asset_baru, 0, id - 1);
            System.arraycopy(data_asset, id, data_asset_baru, id - 1, jumlah_data_asset - id + 1);
            data_asset = data_asset_baru;

            // tampilkan pesan sukses dan konfirmasi apakah ingin menghapus data asset kembali
            pesanSukses("menghapus data asset", "Data asset berhasil dihapus!");
        }
    }

    // keluar dari aplikasi
    private static void keluar() {
        System.out.println();
        System.out.print(fmt("Apakah anda yakin ingin keluar ? [y/n] : ", HI_YELLOW));
        if (bacaInput().equalsIgnoreCase("y")) {
            cetakJudul();
            System.out.println("Terima kasih...");
            System.out.println();
            System.out.println(fmt("Copyright © 2023 Kelompok 7 X2E", BLINK_SLOW, HI_CYAN));
            System.out.println();
            System.exit(0);
        } else {
            menu();
        }
    }

    // untuk clear screen (membersihkan tampilan di terminal)
    private static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            } else if (isSupportANSICode()) {
                System.out.print("\033\143");
            }
        } catch (Exception ex) {
            System.err.println("Tidak bisa clear screen");
        }
    }

    // tahan tampilan agar tidak langsung di clear
    private static void tahan() {
        System.out.println();
        System.out.print("Silakan tekan "+fmt("ENTER ⏎ ", BLINK_SLOW, HI_CYAN)+" untuk melanjutkan..."); bacaInput();
    }

    // tampilkan pesan sukses
    private static void pesanSukses(String aksi, String text, Asset... a) {
        if (text!="") {        
            System.out.println("--------------------------------------------");
            System.out.println();
            System.out.println(fmt(text, HI_GREEN));
        }
        if (a.length>0) {
            System.out.println();
            rincianAsset(a[0]);
        }
        System.out.println();
        System.out.print(fmt("Apakah anda ingin "+aksi+ " kembali ? [y/n] : ", HI_MAGENTA));
        if (!bacaInput().equalsIgnoreCase("y")) {
            menu();
        }
    }

    // tampilkan pesan kesalahan
    private static void pesanKesalahan(String text) {
        System.out.println("--------------------------------------------");
        System.out.println();
        System.out.println(fmt(text, HI_RED));
        tahan();
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

    // tampilkan inputan untuk memilih asset.
    private static int pilihNomorAsset(String aksi) {
        daftarAsset(false);

        // jika data asset nya masih kosong, tampilkan pesan kesalahan.
        if (jumlah_data_asset==0) {
            System.out.println();
            pesanKesalahan("Data asset masih kosong, silakan tambah data asset terlebih dahulu!");
            menu();
        }

        System.out.print(fmt("Masukan nomor asset (1-"+jumlah_data_asset+")  : ", HI_MAGENTA)); String inputan = bacaInput();
        int nilai = toInteger(inputan);

        // validasi input, apabila tidak valid maka beri pesan tidak valid dan kembali ke tampilan inputan
        if (!isValidNumber(inputan) || nilai<0 || nilai>jumlah_data_asset) {
            pesanKesalahan("Nomor asset tidak valid! silakan isi dengan angka yang valid atau isi 0 untuk membatalkan.");
            switch (aksi) {
                case "rincian": rincianAsset(); break;
                case "ubah": ubahAsset(); break;
                case "hapus": hapusAsset(); break;
            }

        // apabila diisi 0 maka kembali ke menu utama
        } else if (nilai==0) {
            menu();
        }

        return nilai;
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
        cetakTabel(judul_kolom_tabel_kategori_asset, lebar_kolom_tabel_kategori_asset, isi_data_tabel_kategori_asset, jumlah_data_kategori_asset);
        System.out.print(fmt("Pilih kategori asset (1-5)    : ", HI_MAGENTA)); String inputan = bacaInput();

        int nilai = toInteger(inputan);
        
        // validasi input, apabila tidak valid maka beri pesan tidak valid dan kembali ke tampilan inputan
        if (!isValidNumber(inputan) || nilai<0 || nilai > jumlah_data_kategori_asset) {
            pesanKesalahan("Kategori tidak valid! silakan isi sesuai nomor kategori yang valid atau isi 0 untuk membatalkan."); 
            return inputKategori();

        // apabila diisi 0 maka kembali ke menu utama
        } else if (nilai==0) {
            menu();
        }

        // jika valid maka tampilkan sesuai index
        AssetCategory kategoriTerpilih = data_kategori_asset[nilai-1];

        // tampilkan keterangan kategori yang sudah dipilih
        // System.out.println(kategoriTerpilih.nama);

        return kategoriTerpilih;
    }

    // tampilkan inputan untuk mengisi nama asset.
    private static String inputNama() {
        System.out.print(fmt("Masukan nama asset            : ", HI_MAGENTA)); 
        return bacaInput();
    }

    // tampilkan inputan untuk mengisi tahun perolehan asset.
    private static int inputTahunPerolehan() {
        System.out.print(fmt("Masukan tahun perolehan asset : ", HI_MAGENTA)); String inputan = bacaInput();
        int nilai = toInteger(inputan);

        // validasi input, apabila tidak valid maka beri pesan tidak valid dan kembali ke tampilan inputan
        if (!isValidNumber(inputan) || nilai<1900 || nilai > tahun_ini) {
            pesanKesalahan("Tahun perolehan tidak valid! silakan isi dengan tahun yang valid atau isi 0 untuk membatalkan."); 
            return inputTahunPerolehan();

        // apabila diisi 0 maka kembali ke menu utama
        } else if (nilai==0) {
            menu();
        }

        return nilai;
    }

    // tampilkan inputan untuk mengisi nilai perolehan asset.
    private static int inputNilaiPerolehan() {
        System.out.print(fmt("Masukan nilai perolehan asset : ", HI_MAGENTA)); String inputan = bacaInput();
        int nilai = toInteger(inputan);

        // validasi input, apabila tidak valid maka beri pesan tidak valid dan kembali ke tampilan inputan
        if (!isValidNumber(inputan) || nilai<0) {
            pesanKesalahan("Nilai perolehan tidak valid! silakan isi dengan angka yang valid atau isi 0 untuk membatalkan."); 
            return inputNilaiPerolehan();

        // apabila diisi 0 maka kembali ke menu utama
        } else if (nilai==0) {
            menu();
        }

        return nilai;
    }

    // tampilkan inputan untuk mengisi nilai residu asset.
    private static int inputNilaiResidu() {
        System.out.print(fmt("Masukan nilai residu asset    : ", HI_MAGENTA)); String inputan = bacaInput();
        int nilai = toInteger(inputan);

        // validasi input, apabila tidak valid maka beri pesan tidak valid dan kembali ke tampilan inputan
        if (!isValidNumber(inputan) || nilai<0) {
            pesanKesalahan("Nilai residu tidak valid! silakan isi dengan angka yang valid atau isi 0 untuk membatalkan."); 
            return inputNilaiResidu();

        // apabila diisi 0 maka kembali ke menu utama
        } else if (nilai==0) {
            menu();
        }

        return nilai;
    }

    // tampung scanner untuk pembacaan input user
    private static Scanner scanner;

    // pembacaan input oleh user
    private static String bacaInput() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner.nextLine();
    }

    // validasi apakah yang diinput oleh user merupakan angka atau bukan.
    private static Boolean isValidNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // konversi dari string ke int dengan mengabaikan error.
    private static int toInteger(String str) {
        int res = 0;
        try {
            res = Integer.parseInt(str);
        } catch (Exception e) {
            
        }
        return res;
    }

    // format angka pecahan dengan pemisah ribuan menggunakan titik tanpa desimal.
    private static String formatAngka(double angka) {
        return String.format("%,.0f", angka).replace(',', '.');
    }

    // cetak tabel biar rapih
    private static void cetakTabel(String[] judul_kolom, int[] lebar_kolom, String[][] isi_data, int jumlah_data) {
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
    private static void cetakGaris(int[] lebar_kolom) {
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
    private static void cetakBaris(String[] kolom, int[] lebar_kolom) {
        for (int i = 0; i < kolom.length; i++) {
            System.out.print("|");
            cetakCell(kolom[i], lebar_kolom[i]);
        }
        System.out.println("|");
    }

    // cetak cell dari suatu tabel
    private static void cetakCell(String teks, int length) {
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

    // cek apakah console yang digunakan support kode ANSI atau tidak
    // untuk windows umum nya tidak support https://superuser.com/questions/413073/windows-console-with-ansi-colors-handling
    private static boolean isSupportANSICode() {
        return System.console() != null && System.getenv().get("TERM") != null;
    }

    // format teks dengan atribut tertentu
    private static String fmt(String text, int... attribute) {

        // apabila tidak support kode ANSI maka atribut diabaikan
        if (!isSupportANSICode()) {
            return text;
        }

        String format = "0";
        int i = 0;
        for (int att : attribute) {
            if (++i > 0) {
                format += ";";
            } else {
                format = "";
            }
            format += Integer.toString(att);
        }
        return "\033["+format+"m"+text+"\033[0m";
    }

    private static final int RESET = 0;
    private static final int BOLD = 1;
    private static final int FAINT = 2;
    private static final int ITALIC = 3;
    private static final int UNDERLINE = 4; 
    private static final int BLINK_SLOW = 5;
    private static final int BLINK_RAPID = 6;
    private static final int REVERSE_VIDEO = 7;
    private static final int CONCEALED = 8;
    private static final int CROSSED_OUT = 9;
    private static final int BLACK = 30;
    private static final int RED = 31;
    private static final int GREEN = 32;
    private static final int YELLOW = 33;
    private static final int BLUE = 34;
    private static final int MAGENTA = 35;
    private static final int CYAN = 36;
    private static final int WHITE = 37;
    private static final int HI_BLACK = 90;
    private static final int HI_RED = 91;
    private static final int HI_GREEN = 92;
    private static final int HI_YELLOW = 93;
    private static final int HI_BLUE = 94;
    private static final int HI_MAGENTA = 95;
    private static final int HI_CYAN = 96;
    private static final int HI_WHITE = 97;
    private static final int BG_BLACK = 40;
    private static final int BG_RED = 42;
    private static final int BG_GREEN = 43;
    private static final int BG_YELLOW = 44;
    private static final int BG_BLUE = 45;
    private static final int BG_MAGENTA = 46;
    private static final int BG_CYAN = 47;
    private static final int BG_WHITE = 48;
    private static final int BG_HI_BLACK = 100;
    private static final int BG_HI_RED = 101;
    private static final int BG_HI_GREEN = 102;
    private static final int BG_HI_YELLOW = 103;
    private static final int BG_HI_BLUE = 104;
    private static final int BG_HI_MAGENTA = 105;
    private static final int BG_HI_CYAN = 106;
    private static final int BG_HI_WHITE = 107;
}
