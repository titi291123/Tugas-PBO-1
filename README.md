# Tugas-PBO-1

Kode ini adalah sistem investasi berbasis Java yang memungkinkan admin mengelola saham dan SBN (Surat Berharga Negara), sementara customer dapat berinvestasi, melihat portofolio, dan simulasi keuntungan. Admin bisa menambah/mengubah produk investasi, sedangkan customer dapat membeli/menjual saham dan SBN. Data disimpan dalam koleksi seperti ArrayList dan HashMap, dengan fitur simulasi bunga SBN dan tracking portofolio. Aplikasi ini berbasis console dengan menu interaktif.

Tampilan awal saat kode di run akan menampilkan pilihan login atau keluar dari aplikasi investasi ini. 

![Tampilan Awal](https://raw.githubusercontent.com/titi291123/Tugas-PBO-1/main/assets/images/page_awal.png)

Saat memilih login user akan langsung masuk ke login page dan pengguna harus memasukkan username (admin atau customer) dan password.

![Login Page](https://raw.githubusercontent.com/titi291123/Tugas-PBO-1/main/assets/images/login_page.png)


Ada 2 tipe user yang dapat menggunakan aplikasi ini, yaitu admin dan customer. Ada 4 fitur yang tersedia untuk digunakan admin dan 6 fitur untuk customer. Berikut penjelasannya.
1. Fitur Admin terdiri dari:

![Admin Menu](https://raw.githubusercontent.com/titi291123/Tugas-PBO-1/main/assets/images/Admin_Menu.png)
    
- Kelola Saham, berguna untuk menambahkan saham baru ke dalam daftar saham yang sudah ada dengan memasukkan kode baru, nama perusahaan, dan harga saham.

![Kelola Saham](https://raw.githubusercontent.com/titi291123/Tugas-PBO-1/main/assets/images/Kelola_Saham.png)
            
- Kelola SBN, di dalam menu kelola SBN terdapat 3 fitur yaitu, tambah SBN, hapus SBN, dan lihat daftar SBN.

![Kelola SBN](https://raw.githubusercontent.com/titi291123/Tugas-PBO-1/main/assets/images/Kelola_SBN.png)
            
- Lihat Portofolio Customer, berguna untuk melihat portofolio jumlah saham dan SBN semua customer yang ada di dalam sistem aplikasi.

![Lihat Portofolio Customer](https://raw.githubusercontent.com/titi291123/Tugas-PBO-1/main/assets/images/Lihat_Portofolio_Customer.png)
            
- Logout digunakan oleh user saat ingin keluar dari aplikasi.

![Logout Admin](https://raw.githubusercontent.com/titi291123/Tugas-PBO-1/main/assets/images/Logout_Admin.png)
            

2. Fitur Customer terdiri dari :

![Customer Menu](https://raw.githubusercontent.com/titi291123/Tugas-PBO-1/main/assets/images/Customer_Menu.png)
    
- Beli Saham, berguna saat customer ingin membeli saham. Pada fitur ini di dalamnya akan dijabarkan daftar saham secara rinci setiap perusahaan yang tersedia untuk di beli customer. Setelah itu customer akan memilih nomor saham yang akan dibeli, jumlah lembar saham, kemudian akan muncul total dan permintaan konfirmasi pembelian kepada customer.

![Beli Saham](https://raw.githubusercontent.com/titi291123/Tugas-PBO-1/main/assets/images/Beli_Saham.png)
            
- Jual Saham, fitur ini berguna saat customer ingin menjual saham yang dimiliki dengan cara memasukkan nomor saham yang akan dijual, jumlah lembar, dan permintaan konfirmasi penjualan kepada customer.

![Jual Saham](https://raw.githubusercontent.com/titi291123/Tugas-PBO-1/main/assets/images/Jual_Saham.png)
            
- Beli SBN, di dalam fitur ini akan dirincikan daftar SBN yang dapat dibeli oleh customer, kemudian customer memilih nomor SBN yang akan dibeli, kemudian sistem akan menampilkan jumlah SBN yang tersedia, setelah itu customer memasukkan nominal pembelian, dan sistem akan menampilkan estimasi bunga perbulan.

![Beli SBN](https://raw.githubusercontent.com/titi291123/Tugas-PBO-1/main/assets/images/Beli_SBN.png)
            
- Simulasi SBN, fitur ini seperti  gambaran kasar bagaimana tata cara pembelian SBN sebelum customer benar-benar membeli SBN. Di dalamnya akan dirincikan daftar SBN yang tersedia untuk dibeli, kemudian memilih nomor SBN yang akan dibeli, lalu customer akan diminta untuk memasukkan nominal investasi, setelah itu sistem akan merincikan proyeksi bunga secara lengkap.

![Simulasi SBN](https://raw.githubusercontent.com/titi291123/Tugas-PBO-1/main/assets/images/Simulasi_SBN_1.png)

![Simulasi SBN 2](https://raw.githubusercontent.com/titi291123/Tugas-PBO-1/main/assets/images/Simulasi_SBN_2.png)

            
- Portofolio Saya, fitur ini memungkinkan customer untuk melihat daftar saham dan SBN apa saja yang mereka miliki.

![Portofolio Saya](https://raw.githubusercontent.com/titi291123/Tugas-PBO-1/main/assets/images/Portofolio_Saya.png)

- Logout digunakan saat user ingin keluar dari aplikasi.

![Logout Customer](https://raw.githubusercontent.com/titi291123/Tugas-PBO-1/main/assets/images/Logout_Customer.png)


Berikut adalah diagram UML yang menghubungkan antar kelas:
![UML Diagram](https://raw.githubusercontent.com/titi291123/Tugas-PBO-1/main/assets/images/UML_PBO3.drawio.png)

Analisis Struktur dan Relasi dalam Diagram UML Sistem
1. Hubungan Inti Sistem
- MainApp menggunakan (dependency) AuthService untuk menangani proses login.
- AuthService bergantung pada DataService untuk melakukan verifikasi data pengguna.
- Setelah proses login berhasil, sistem akan mengarahkan pengguna ke AdminService atau  CustomerService sesuai dengan peran (role) yang dimiliki.
2. Struktur Kepemilikan Data
- DataService memiliki relasi composition dengan beberapa entitas data inti berikut:
   - User – menyimpan informasi pengguna (admin & customer).
   - Saham – daftar saham yang tersedia dalam sistem.
   - SuratBerhargaNegara – daftar surat berharga negara (SBN).
   - InvestasiCustomer – portofolio investasi milik masing-masing customer.
- Komposisi menandakan bahwa objek-objek tersebut dimiliki penuh oleh DataService dan tidak dapat berdiri sendiri.
3. Struktur Menu
- AdminMenu memiliki relasi composition dengan:
   - SahamMenu – untuk mengelola data saham.
   - SBNMenu – untuk mengelola data surat berharga negara.
- CustomerMenu memiliki relasi composition dengan InvestasiCustomer – untuk menampilkan dan mengelola portofolio milik customer.
4. Pola Injeksi Service
 - SahamMenu dan SBNMenu memiliki relasi aggregation dengan AdminService. Artinya, mereka menerima referensi AdminService yang telah dibuat di luar dan tidak memilikinya secara eksklusif.
 - CustomerMenu juga memiliki relasi aggregation dengan CustomerService.
5. Akses Data
 - Baik AdminService maupun CustomerService berelasi dengan DataService melalui association untuk mengakses dan memanipulasi data.
 - CustomerService memiliki association one-to-many dengan Saham dan SuratBerhargaNegara, menandakan interaksi dengan koleksi data tersebut.
6. Komponen Utilitas
 - Beberapa kelas seperti SBNMenu dan CustomerMenu menggunakan InputUtils melalui dependency untuk memudahkan pengambilan input dari pengguna.
 - AdminMenu menggunakan objek Scanner secara langsung, juga melalui dependency.
7. Implementasi Interface
 - Seluruh kelas menu (AdminMenu, CustomerMenu, dan lainnya) mengimplementasikan interface Menu, mengikuti pola realization. Mereka semua menyediakan implementasi konkret dari metode yang didefinisikan dalam interface Menu.
