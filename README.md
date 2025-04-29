# Tugas-PBO-1

Kode ini adalah sistem investasi berbasis Java yang memungkinkan admin mengelola saham dan SBN (Surat Berharga Negara), sementara customer dapat berinvestasi, melihat portofolio, dan simulasi keuntungan. Admin bisa menambah/mengubah produk investasi, sedangkan customer dapat membeli/menjual saham dan SBN. Data disimpan dalam koleksi seperti ArrayList dan HashMap, dengan fitur simulasi bunga SBN dan tracking portofolio. Aplikasi ini berbasis console dengan menu interaktif.

Tampilan awal saat kode di run akan menampilkan pilihan login atau keluar dari aplikasi investasi ini. 

![UML Diagram](https://raw.githubusercontent.com/titi291123/Tugas-PBO-1/main/assets/images/UML_PBO3.drawio.png)

Saat memilih login akan langsung masuk ke login page dan pengguna harus memasukkan username (admin atau customer) dan password.

Ada 2 tipe user yang dapat menggunakan aplikasi ini, yaitu admin dan customer. Ada 4 fitur yang tersedia untuk digunakan admin dan 6 fitur untuk customer. Berikut penjelasannya.
    1. Fitur Admin terdiri dari:
            + Kelola Saham, berguna untuk menambahkan saham baru ke dalam daftar saham yang sudah ada dengan memasukkan kode baru, nama perusahaan, dan harga saham.
            + Kelola SBN, di dalam menu kelola SBN terdapat 3 fitur yaitu, tambah SBN, hapus SBN, dan lihat daftar SBN.
            + Lihat Portofolio Customer, berguna untuk melihat portofolio jumlah saham dan SBN semua customer yang ada di dalam sistem aplikasi.
            + Logout digunakan oleh user saat ingin keluar dari aplikasi.

    2. Fitur Customer terdiri dari :
            + Beli Saham, berguna saat customer ingin membeli saham. Pada fitur ini di dalamnya akan dijabarkan daftar saham secara rinci setiap perusahaan yang tersedia untuk di beli customer. Setelah itu customer akan memilih nomor saham yang akan dibeli, jumlah lembar saham, kemudian akan muncul total dan permintaan konfirmasi pembelian kepada customer.
            + Jual Saham, fitur ini berguna saat customer ingin menjual saham yang dimiliki dengan cara memasukkan nomor saham yang akan dijual, jumlah lembar, dan permintaan konfirmasi penjualan kepada customer.
            + Beli SBN, di dalam fitur ini akan dirincikan daftar SBN yang dapat dibeli oleh customer, kemudian customer memilih nomor SBN yang akan dibeli, kemudian sistem akan menampilkan jumlah SBN yang tersedia, setelah itu customer memasukkan nominal pembelian, dan sistem akan menampilkan estimasi bunga perbulan.
            +Simulasi SBN, fitur ini seperti  gambaran kasar bagaimana tata cara pembelian SBN sebelum customer benar-benar membeli SBN. Di dalamnya akan dirincikan daftar SBN yang tersedia untuk dibeli, kemudian memilih nomor SBN yang akan dibeli, lalu customer akan diminta untuk memasukkan nominal investasi, setelah itu sistem akan merincikan proyeksi bunga secara lengkap.
            + Portofolio Saya, fitur ini memungkinkan customer untuk melihat daftar saham dan SBN apa saja yang mereka miliki.
            + Logout digunakan saat user ingin keluar dari aplikasi.
