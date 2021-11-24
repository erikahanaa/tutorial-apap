# Tutorial APAP

## Authors

* **Erika Hana Prasanti** - *1906298872* - *APAP B*

## Tutorial 6✨

### What I have learned today✅😊

Pada tutorial kali ini, saya sudah belajar lebih lanjut mengenai Web Security dan mempraktikannya secara langsung. Implementasinya berupa penerapan fitur Login juga Logout pada Web, juga membatasi apa saja yang dapat dilihat oleh suatu user berdasarkan role-nya. Secara keseluruhan, menurut saya, materi kali ini sangatlah seru dan tidaklah sesulit tutorial-tutorial sebelumnya. Saya juga merasa implementasi Login Logout di Spring ini lebih mudah daripada pada Django!😆

### Pertanyaan

**1. Jelaskan secara singkat perbedaan Otentikasi dan Otorisasi! Di bagian mana (dalam kode yang telah anda buat) konsep tersebut diimplementasi??**

Autentikasi merupakan suatu proses identifikasi pengguna, sedangkan autorisasi merupakan proses menentukan apa saja yang dapat dilakukan atau diakses oleh pengguna. Autorisasi sendiri merupakan proses setelah Autentikasi berhasil dilakukan. Pada tutorial ini, saya sudah mengimplementasikan Autentikasi dan Autorisasi lewat kode yang sudah dibuat. Konsep Autentikasi sendiri diimplementasi saat saya membuat kode di WebSecurityConfig.java untuk mengidentifikasi pengguna dan memutuskan apakah pengguna bisa mengakses web atau tidak, sedangkan konsep Autorisasi diimplementasi juga di WebSecurityConfig.java saat memutuskan pengguna dengan role apa saja yang bisa mengakses suatu halaman, seperti Add Destinasi hanya bisa dilakukan oleh Agen atau Add User hanya bisa dilakukan oleh Admin.

Berikut detail implementasi kodenya pada WebSecurityConfig.java:
- Autentikasi
```ruby
@Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

```
- Autorisasi
```ruby
    .antMatchers("/user/add").hasAuthority("Admin")
            .antMatchers("/destinasi/add").hasAuthority("Agen")
            .antMatchers("/user/updatePassword").hasAnyAuthority("Admin", "User", "Agen")

```

**2. Apa itu BCryptPasswordEncoder? Jelaskan secara singkat cara kerja dan tujuannya**

BCryptPasswordEncoder adalah salah satu encoder password yang digunakan dalam modul SpringBoot untuk penyandian kata sandi dan decoding kata sandi atau validasi. BCryptPasswordEncoder sendiri menggunakan algoritma BCrypt. Kemudian, tujuan utama dari BCrypt adalah untuk menyimpan kata sandi di database dengan aman.

Mekanisme kerja BCrypt adalah menghasilkan byte acak dan menggabungkannya dengan kata sandi sebelum hashing, kemudian menciptakan hash unik di setiap kata sandi pengguna. Jika dua pengguna memiliki kata sandi yang sama, mereka tidak akan memiliki hash kata sandi yang sama. Ini untuk mencegah serangan yang dapat membalikkan kata sandi hash menggunakan fungsi hashing umum yang tidak menggunakan metocd ini. Kemudian, algoritma Hashing ini juga merupakan fungsi satu arah. Algoritma ini mengubah sejumlah data menjadi "sidik jari" dengan panjang tetap yang tidak dapat dibalik. Hal ini bagus untuk melindungi kata sandi, tetapi pada saat yang sama algoritma ini juga harus dapat memverifikasi bahwa kata sandi pengguna sudah benar.

Oleh karena itu, general workflow-nya adalah:
- User membuat akun (termasuk di dalamnya password akun tersebut)
- Kata sandi ini di-hash dan disimpan dalam database
- Ketika pengguna mencoba untuk login, hash dari kata sandi yang mereka masukkan diperiksa dengan hash dari kata sandi asli pengguna(diambil dari database)
- Jika hash cocok, pengguna diberikan akses. Jika tidak, pengguna akan diberitahu bahwa mereka memasukkan kredensial login yang tidak valid.
- Langkah 3 dan 4 diulangi setiap kali pengguna mencoba masuk ke akunnya.

**3. Apakah penyimpanan password sebaiknya menggunakan encryption atau hashing? Mengapa demikian?**

Menurut saya, penyimpanan password sebaiknya menggunakan hashing daripada enskripsi. Hal ini karena Hashing adalah fungsi satu arah artinya tidak mungkin untuk "mendekripsi" hash dan mendapatkan nilai plain text asli. Hashing sendiri sesuai untuk validasi kata sandi. Bahkan jika attacker mendapatkan kata sandi hash, mereka tidak dapat memasukkannya ke dalam bidang kata sandi aplikasi dan masuk sebagai pengguna. Di sisi lain, enkripsi adalah fungsi dua arah, artinya plain text asli dapat diambil kembali. Enkripsi sesuai untuk menyimpan data seperti nama atau alamat pengguna karena data ini ditampilkan dalam teks biasa pada profil pengguna. Jika data yang tidak terlalu penting seperti alamat atau nama dipakaikan hashing, maka data akan terlihat sangat berantakan. Oleh karena itu, untuk penyimpanan password sebaiknya kita menggunakan hashing saja.
 
**4. Jelaskan secara singkat apa itu UUID beserta penggunaannya!**

UUID (Universally Unique Identifiers) merupakan angka 128 bit yang terdiri dari 16 oktet dan direpresentasikan sebagai 32 karakter dasar-16, yang dapat digunakan untuk mengidentifikasi informasi di seluruh sistem komputer. Spesifikasi ini awalnya dibuat oleh Microsoft dan distandarisasi oleh IETF dan ITU.

UUID umumnya digunakan untuk mengidentifikasi informasi yang perlu unik dalam sistem atau jaringannya. Keunikan dan kemungkinannya yang rendah untuk diulang membuat UUID ini berguna untuk menjadi key dalam database dan identifier untuk perangkat keras fisik dalam suatu organisasi. Salah satu manfaat UUID adalah bahwa mereka tidak perlu dikeluarkan oleh central authority, tetapi dapat dibuat secara independen dan kemudian digunakan di seluruh sistem tertentu tanpa kecurigaan adanya duplikat atau bertabrakan.

Ada empat jenis utama UUID yang digunakan dalam skenario yang sedikit berbeda. Semua UUID memiliki panjang 128 bit, tetapi umumnya direpresentasikan sebagai 32 karakter heksadesimal yang dipisahkan oleh empat tanda hubung.

Versi 1 UUID, yang paling umum, menggabungkan alamat MAC dan stempel waktu untuk menghasilkan keunikan yang memadai. Kemudian, Versi 3 dan Versi 5 UUID, yang paling tidak umum, menggunakan fungsi hash MD5 dan SHA1, ditambah namespace, ditambah nilai data yang sudah unik untuk menghasilkan ID unik. Ini dapat digunakan untuk menghasilkan UUID dari URL misalnya. Terakhir, UUID versi 4, hanyalah 128 bit data acak, dengan sedikit memutar-mutar untuk mengidentifikasi versi dan varian UUID.

**5. Apa kegunaan class UserDetailsServiceImpl.java? Mengapa harus ada class tersebut padahal kita sudah memiliki class UserRoleServiceImpl.java?**

Interface UserDetailsService digunakan untuk mengambil data terkait pengguna. UserDetailsService ini memiliki satu metode bernama loadUserByUsername() yang dapat diganti untuk menyesuaikan proses menemukan pengguna. Selanjutnya, metode ini digunakan oleh DaoAuthenticationProvider untuk memuat detail tentang pengguna selama proses autentikasi. 

Lebih lanjut, UserDetailsService ini hanya menyimpan informasi pengguna yang kemudian dienkapsulasi menjadi objek autentikasi. Hal ini memungkinkan informasi pengguna yang tidak terkait dengan keamanan (seperti alamat email, nama, dan lainnya) disimpan di lokasi yang nyaman.

### What I did not understand😩
- [ ] Sejauh ini belum ada kesulitan yang berarti.

### Referensi

https://degananda.com/perbedaan-antara-autentikasi-dan-autorisasi-dalam-dunia-software-engineering/

https://www.yawintutor.com/encode-decode-using-bcryptpasswordencoder-in-spring-boot-security/

https://javascript.plainenglish.io/how-bcryptjs-works-90ef4cb85bf4

https://duo.com/labs/tech-notes/breaking-down-uuids

https://stackoverflow.com/questions/292965/what-is-a-uuid

https://www.baeldung.com/spring-security-authentication-with-a-database

https://howtodoinjava.com/spring-security/custom-userdetailsservice-example-for-spring-3-security/

https://cheatsheetseries.owasp.org/cheatsheets/Password_Storage_Cheat_Sheet.html

https://stackoverflow.com/questions/326699/difference-between-hashing-a-password-and-encrypting-it


## Tutorial 5✨

### What I have learned today✅😊

Pada tutorial kali ini, saya sudah belajar lebih lanjut mengenai Web Service dan mempraktikannya secara langsung. Implementasi Web Service ini ditandai dengan adanya RestService dan RestController. Selain itu, saya juga sudah belajar tentang bagaimana cara menggunakan API dari suatu website. Secara keseluruhan, menurut saya lab kali ini tidak terlalu sulit, tapi karena banyak syntax asing jadi butuh waktu pengerjaan yang lama. Semoga ke depannya saya bisa memahami materi ini secara menyeluruh!😁

### Pertanyaan

**1. Apa itu Postman? Apa kegunaannya?**

Postman adalah suatu aplikasi yang digunakan untuk melakukan pengujian pada API. Jadi, Postman ini merupakan klien HTTP yang menguji permintaan HTTP, dengan menggunakan antarmuka pengguna grafis, dengan tindak lanjut berupa berbagai jenis respons yang perlu divalidasi selanjutnya. Postman memiliki berbagai metode interaksi yang banyak digunakan, seperti GET (untuk mendapatkan informasi), POST (untuk menambah informasi), PUT (untuk mengganti informasi), PATCH (untuk mengganti berbagai informasi spesifik), juga DELETE (untuk menghapus informasi). Bentuk response code dari Postman pun beragam, seperti 100 series untuk temporal responses, 200 series untuk accept request, 300 series untuk response yang terkait URL redirection, 400 series sebagai bad request, dan 500 series sebagai server error. Beberapa keunggulan Postman antara lain memiliki fitur lingkungan pengujian yang berbeda dan membantu developer saat menjalankan tes dengan tampilan yang user friendly.

**2. Jelaskan fungsi dari anotasi @JsonIgnoreProperties dan @JsonProperty**

- @JsonIgnoreProperties digunakan di tingkat kelas untuk menandai properti atau list dari properti yang akan diabaikan. Dalam kata lain, anotasi ini mengabaikan properti JSON saat dibaca. Tujuan dari penggunaaan anotasi ini adalah untuk memudahkan developer untuk melakukan panggilan REST.
- @JsonProperty digunakan untuk memetakan nama properti dengan kunci JSON selama serialisasi dan deserialisasi. Secara default, jika kita mencoba membuat serialisasi POJO, JSON yang dihasilkan akan memiliki kunci yang dipetakan ke bidang POJO. Nah, @JsonProperty ini digunakan untuk mengganti perilaku tersebut. Anotasi ini juga dapat digunakan selama deserialisasi saat nama properti JSON dan nama bidang objek Java tidak cocok.

**3. Apa kegunaan atribut WebClient?**

WebClient, jika dibandingkan dengan RestTemplate, merupakan sebuah tools yang bersifat non-blocking, reaktif, dan mendukung konkurensi yang lebih tinggi dengan sumber daya perangkat keras yang lebih sedikit. WebClient juga menyediakan API fungsional yang memanfaatkan lambda Java 8 dan mendukung skenario sinkron dan asinkron.

Secara singkat, kegunaan dari atribut WebClient adalah untuk menerima sekaligus mengirim data dari resource URI.

RestTemplate tidak cocok untuk digunakan dalam aplikasi yang tidak memblokir, sehingga aplikasi Spring WebFlux harus selalu menggunakan WebClient. WebClient juga umumnya sering diterapkan di Spring MVC, juga umum digunakan untuk menyusun urutan panggilan jarak jauh yang saling bergantung.
 
**4. Apa itu ResponseEntity dan BindingResult? Apa kegunaannya?**

- ResponseEntity merupakan representasi dari seluruh respons HTTP (kode status, header, dan isi). Karena hal tersebut, kita dapat menggunakan ResponseEntity untuk menkonfigurasi respons HTTP sepenuhnya. Ketika kita ingin menggunakannya, kita juga perlu mengembalikan ResponseEntity ini dari titik akhir dan sisanya akan ditangani oleh Spring.
- BindingResult merupakan suatu objek Spring yang menyimpan hasil validasi dan binding juga berisi kesalahan yang mungkin terjadi. BindingResult ini harus muncul tepat setelah objek model yang divalidasi. Jika tidak, maka Spring akan gagal memvalidasi objek dan akan throw exception. Dengan kata lain, BindingResult ini merupakan wadah yang berisi informasi terkait kesalahan yang terjadi.

### What I did not understand😩
- [v] Masih kesulitan memahami syntax baru yang belum pernah digunakan sebelumnya.

### Referensi

https://www.encora.com/insights/what-is-postman-api-test

https://www.tutorialspoint.com/jackson_annotations/jackson_annotations_jsonignoreproperties.htm#:~:text=%40JsonIgnoreProperties%20is%20used%20at%20class,of%20properties%20to%20be%20ignored.

https://fasterxml.github.io/jackson-annotations/javadoc/2.7/com/fasterxml/jackson/annotation/JsonIgnoreProperties.html

https://dzone.com/articles/jackson-annotations-for-json-part-4-general#:~:text=The%20%40JsonProperty%20annotation%20is%20used,keys%20during%20serialization%20and%20deserialization.&text=You%20can%20also%20use%20this,Java%20object%20do%20not%20match.

https://docs.spring.io/spring-framework/docs/5.0.x/spring-framework-reference/web-reactive.html

https://www.baeldung.com/spring-response-entity

https://stackoverflow.com/questions/10413886/what-is-the-use-of-bindingresult-interface-in-spring-mvc/36715053#:~:text=%5B%20BindingResult%20%5D%20is%20Spring's%20object%20that.object%20and%20throw%20an%20exception. 


## Tutorial 4✨

### What I have learned today✅😊

Hari ini saya sudah belajar mengenai web presentation terutama penggunaan thymeleaf untuk merapihkan tampilan website. Selain itu, saya juga kembali mengulang pelajaran yang sudah didapat di PPW dulu, yaitu mengenai MVC atau Model, View, juga Controller. Lalu, jika dulu di Django untuk Navbar saya biasa pakai base.html, di sini saya belajar penggunaan yang hampir mirip yaitu untuk menyingkat navbar di tiap halaman dengan memanfaatkan fragments. Menurut saya, lab kali ini cukup menyenangkan karena kita mengubah-ubah tampilan website, tetapi saya juga mengalami kesulitan untuk mengerjakan latihan nomor tiga yang cukup challenging!😵😁

### Pertanyaan

**1. Jelaskan perbedaan th:include dan th:replace!**

Jika kita menggunakan th:include itu artinya fragment akan ditempatkan di dalam tag div. Namun, jika kita menggunakan th: replace, maka tag div nya itu akan digantikan oleh konten. Berikut merupakan contoh code-nya.

```ruby
<div th:include="..."> content here </div>

```
Pada kode di atas, konten akan masuk ke dalam div.
```ruby
<div th:replace="..."> content here </div>

```
Namun, pada kode yang kedua ini, konten akan menggantikan div.

**2. Jelaskan apa fungsi dari th:object!**

Pada thymeleaf, th:object digunakan untuk menspesifikan objek yang akan di-submit pada form yang akan dikirim. Selain itu, th:object juga disebut sebagai penghubung antara html dan controller karena ia perlu passing nilai dari controller untuk ditaruh di html dan menentukan objek apa saja yang perlu diisi nilainya.

**3. Jelaskan perbedaan dari * dan $ pada saat penggunaan th:object! Kapan harus dipakai?**

Penggunaan $ pada th:object biasanya lebih umum digunakan dan ini artinya kita akan passing variabel, sedangkan penggunaan * pada th:object artinya kita passing 'selection variable'. Jadi, kalau $ sifatnya mencakup seluruh variabel pada objek, sedangkan * sifatnya hanya evaluasi variabel pada th:object yang sebelumnya sudah di-declare.

### What I did not understand😩
- [v] Masih belum memahami secara menyeluruh terkait konsep binding list pada Thymeleaf.

### Referensi
https://stackoverflow.com/questions/37103958/difference-between-thymeleaf-include-and-replace

https://www.baeldung.com/thymeleaf-in-spring-mvc

https://stackabuse.com/getting-started-with-thymeleaf-in-java-and-spring/



## Tutorial 3✨

### What I have learned today✅😊

Hari ini saya udah belajar mengenai access pattern, atau bagaimana menghubungkan antara database dengan business logic. Hal ini berarti melibatkan model, service, dan controller. Lalu, saya juga sudah belajar menggunakan JPA Repository yang membuat saya bisa mengakses data dari database untuk diolah pada sistem. Jadi, pada lab ini, untuk memasukkan data ke database kita tidak perlu manual create database-nya, melainkan bisa lewat tampilan yang lebih user friendly (isi form di website). Selain itu, saya juga telah belajar mengenai create, read, update, dan delete object dengan menggunakan Request Mapping. Secara keseluruhan, lab kali ini sungguh challenging karena saya menemukan banyak sekali error, bahkan pada proses mengikuti tutorialnya saja (belum masuk ke latihan). Saya harap saya dapat bisa lebih memahami materi ini ke depannya agar bisa mengoding lebih mudah & efektif🥰😄

### Pertanyaan

**1. Tolong jelaskan secara singkat apa kegunaan dari anotasi-anotasi yang ada pada model (@AllArgsConstructor, @NoArgsConstructor, @Setter, @Getter, @Entity, @Table)**

Berikut merupakan penjelasan kegunaan dari masing-masing anotasi pada model:
- @AllArgsConstructor: digunakan untuk menghasilkan konstruktor dengan satu parameter untuk setiap field pada suatu class. Secara umum, constructor yang dihasilkan akan bersifat public.
- @NoArgsConstructor: digunakan untuk menghasilkan konstruktor tanpa parameter.
- @Setter: digunakan untuk menghasilkan default dari setter secara otomatis oleh Lombok. Jadi, kita tidak perlu repot-repot menulis public tipedata setSomething lagi.
- @Getter: digunakan untuk menghasilkan default dari getter secara otomatis oleh Lombok.
- @Entity: digunakan untuk menspesifikasi bahwa class adalah suatu entitas dan akan dipetakan ke tabel database.
- @Table: digunakan untuk menspesifikasi nama tabel database yang digunakan dalam pemetaan.

**2. Pada class TravelAgensiDb, terdapat method findByNoAgensi, apakah kegunaan dari method tersebut?**

Method findByNoAgensi digunakan untuk mengambil atau melihat agensi berdasarkan nomor atau id-nya. Sebelumnya, method findBy ini memang disediakan oleh JPA Repository untuk membantu mencari di database lewat suatu parameter tertentu tanpa coding query ambil database-nya, sehingga sangat mempermudah programmer.

**3. Jelaskan perbedaan kegunaan dari anotasi @JoinTable dan @JoinColumn**

Perbedaan @JoinTable dan @JoinColumn ialah pada @JoinTable, ia menyimpan id dari kedua tabel ke dalam tabel lain yang terpisah, sedagkan pada @JoinColumn menyimpan id dari tabel lain pada kolom baru. @JoinTable merupakan default dan pada umumnya digunakan untuk me-normalize database (contohnya mengurangi redundansi), sedangkan @JoinColumn digunakan untuk performance yang lebih baik karena kita tidak membutuhkan tabel extra. Lalu, @JoinTable juga digunakan untuk me-manage hubungan antara entitas di tabel lain, sedangkan @JoinColumn digunakan ketika entitas memiliki direct relationship.

**4. Pada class TourGuideModel, digunakan anotasi @JoinColumn pada atribut agensi, apa kegunaan dari name, referencedColumnName, dan nullable dalam anotasi tersebut? dan apa perbedaan nullable dan penggunaan anotasi @NotNull**

Pada @JoinColumn di atribut agensi, berikut merupakan masing-masing kegunaan dari:
- name: sebagai foreign key dari tabel TourGuideModel dan foreign key ini digunakan untuk menghubungkan tabel TourGuideModel dan TravelGuideModel.
- referencedColumnName: digunakan sebagai nama atribut untuk kolom yang direferensi pada tabel destinasi.
- nullable: digunakan untuk mengecek apakah foreign key pada tabel tidak bernilai null.

Lalu, perbedaan antara nullable dan penggunaan anotasi @NotNull adalah anotasi @NotNull memberi tahu implementasi Validation untuk memeriksa apakah atributnya bukan nol. Jika validasi gagal, Hibernate tidak akan mengeksekusi pernyataan SQL apa pun. Di sisi lain, nullable digunakan hanya untuk menambahkan batasan not null ke definisi tabel. Hibernate tidak akan melakukan validasi apa pun pada atribut entitas. Namun, Hibernate tetap mengikuti pernyataan SQL UPDATE, dan database akan memvalidasi constraints. Jika atribut entitas adalah nol, pernyataan SQL akan gagal.

**5. Jelaskan kegunaan FetchType.LAZY, CascadeType.ALL, dan FetchType.EAGER**

- FetchType.LAZY merupakan tipe Hibernate yang digunakan untuk menunda inisialisasi objek selama mungkin. Dalam kata lain, tipe ini tidak melakukan semua load untuk collection object child ketika parent-nya akan di-fetch, makanya disebut fetchtype-lazy. Ini merupakan default dari fetch. Keuntungannya menggunakan tipe LAZY ini initial load time jauh lebih kecil daripada pendekatan lain dan konsumsi memori juga lebih sedikit daripada di pendekatan lain. Kerugiannya, tipe ini inisialisasi yang tertunda dapat memengaruhi kinerja selama momen yang tidak diinginkan dan dalam beberapa kasus, kita perlu menangani objek yang diinisialisasi dengan erhatian khusus atau harus pakai exception.
- CascadeType.ALL merupakan tipe Hibernate yang akan menyebarkan semua (cascade all) operasi entity manager yang meliputi persist, remove, refresh, merge, dan detach ke entitas terkait.
- FetchType.EAGER merupakan tipe Hibernate di mana inisialisasi data terjadi di tempat. Dalam kata lain, tipe ini fetch child ketika parent-nya di fetch. Keuntungannya, tidak ada dampak kinerja terkait inisialisasi yang tertunda dan kerugiannya initial loading time-nya lama dan ada kemungkinan dia nge-load data yang tidak penting, sehingga bisa memengaruhi performance.


### What I did not understand😩
- [v] Masih belum lancar untuk berpindah-pindah view, model, dan controller untuk mencapai hasil yang diinginkan

### Referensi
https://javabydeveloper.com/lombok-allargsconstructor-examples/

https://projectlombok.org/features/constructor

https://projectlombok.org/features/GetterSetter

https://zetcode.com/springboot/annotations/

https://stackoverflow.com/questions/30288464when-should-i-use-joincolumn-or-jointable-with-jpa#:~:text=%40JoinColumn%20will%20define%20the%20target,Entity%20table%20(e.g.%20B%20).&text=%40JoinTable%20will%20use%20a%20separate,relationship%20between%20A%20and%20B%20.
https://javakeypoint.wordpress.com/2020/04/21/difference-between-joincolumn-and-jointable-in-hibernate/

https://www.baeldung.com/hibernate-lazy-eager-loading

https://www.java2novice.com/hibernate/eager-vs-lazy-fetch-type/#:~:text=FetchType.,is%20actually%20fetched%20by%20hibernate.

https://stackoverflow.com/questions/13027214/what-is-the-meaning-of-the-cascadetype-all-for-a-manytoone-jpa-association

https://thorben-janssen.com/hibernate-tips-whats-the-difference-between-column-nullable-false-and-notnull/



## Tutorial 2✨

### What I have learned today✅😊

Hari ini udah belajar gimana cara untuk organize domain logic and service layer through tutorial 2😄 Jadi, sekarang udah lumayan ngerti gimana caranya membuat kedua hal itu saling berhubungan dan juga membuat service untuk create, read, update, dan delete pakai konsep MVC!

### Pertanyaan

**1. Cobalah untuk menambahkan sebuah Agensi dengan mengakses link berikut: http://localhost:8080/agensi/add?idAgensi=1&namaAgensi=Papa%20APAP&alamat=Maung%20Fasilkom&noTelepon=081xxx Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi?**

Ketika klik link tersebut, maka yang terjadi adalah error. Hal ini karena ketika kita membuka link tersebut, maka kita akan diarahkan ke halaman add-idAgensi, sedangkan kita belum membuat halaman untuk menampilkan add-idAgensi. Jika nanti halaman add-idAgensi nya sudah terbuat, maka halaman tersebut nantinya akan bisa diakses.

**2. Menurut kamu anotasi @Autowired pada class Controller tersebut merupakan implementasi dari konsep apa? Dan jelaskan secara singkat cara kerja @Autowired tersebut dalam konteks service dan controller yang telah kamu buat**

Anotasi @Autowired pada class Controller merupakan implementasi dari konsep dependency injection. Kemudian, cara kerja @Autowired dalam konteks service dan controller pada lab kali ini adalah @Autowired melakukan dependency injection terhadap service yang mengimplementasikan autowiring secara otomatis, sehingga kita tidak perlu lagi menambahkan argumen di constructor, maupun menyediakan setter getter lagi.

**3. Cobalah untuk menambahkan sebuah Agensi dengan mengakses link berikut: http://localhost:8080/agensi/add?idAgensi=1&namaAgensi=Papa%20APAP&alamat=Maung%20Fasilkom Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi.**

Ketika mengakses link tersebut, maka akan muncul error. Hal ini dikarenakan pada link tersebut tidak ada parameter untuk menambahkan nomor telepon, sehingga link tersebut terhitung tidak lengkap dan error. Untuk bisa sampai di halaman add-agensi, keseluruhan parameter, termasuk idAgensi, namaAgensi, alamat, dan nomorTelepon harus ada semua karena di controller kita setting bernilai true. Nah, jika ingin mengakses halamannya dan memastikan tidak error, maka kita perlu untuk melengkapi semua parameter pada url, kemudian nanti akan muncul halaman yang berisikan tulisan "Travel Agensi dengan id Agensi 1 berhasil ditambahkan." dengan tombol "Kembali" di bawahnya. Hal ini berarti halaman yang menampilkan add-agensi sudah bisa diakses karena kita sudah berhasil buat html-nya. 

**4. Jika Papa APAP ingin melihat Travel Agensi dengan nama Papa APAP, link apa yang harus diakses?**

Link yang harus diakses adalah link untuk menuju halaman view Agensi dengan parameter tertentu. Maka dari itu, Papa APAP harus menuju url berikut: http://localhost:8080/agensi/view?idAgensi=1 . Kita tidak bisa mengakses langsung dengan parameter nama Papa APAP karena kita tidak memiliki method untuk retrieve detail agensi dengan menggunakan nama, melainkan kita bisa mencarinya lewat id. Maka dari itu, karena Travel Agensi dengan nama Papa APAP ini memiliki id Agensi 1, maka link yang bisa diakses adalah link http://localhost:8080/agensi/view?idAgensi=1 ini dengan parameter id.

**5. Tambahkan 1 contoh Travel Agensi lainnya sesukamu. Lalu cobalah untuk mengakses http://localhost:8080/agensi/viewAll , apa yang akan ditampilkan? Sertakan juga bukti screenshotmu.**

Untuk soal ini, saya menambahka suatu travel agensi baru dengan id agensi 2, nama agensi ErikaKeren, alamat FasilkomOke, dan nomor telepon 021727666666 dengan cara memasukkan link berikut http://localhost:8080/agensi/add?idAgensi=2&namaAgensi=ErikaKeren&alamat=FasilkomOke&noTelepon=021727666666 . Berikut saya lampirkan screenshot kalau id Agensi baru sudah berhasil terbuat. 
Selanjutnya, saya akan menuju halaman view All untuk melihat agensi yang sudah terdaftar. Berikut tampilannya. Dari screenshot, dapat dilihat bahwa id yang baru saya masukkan dengan nama agensi ErikaKeren sudah muncul😆

Link Screenshot buat agensi baru: https://ibb.co/6PCCtGd
![image](https://i.ibb.co/mqWWydx/NOMOR-5-BUAT-BARU.jpg)

Link Screenshot seluruh agensi: https://ibb.co/smjNsjJ
![image](https://i.ibb.co/Tvc7Pcq/NOMOR-5-UDAH-TERBUAT.jpeg)

### What I did not understand😩
- [v] Masih belum terbayang jelas sebenarnya tentang konsep Autowiring dan kapan saja kita harus pakai @Autowired itu😵

## Tutorial 1✨

### What I have learned today✅😊

### Github

**1. Apa itu Issue Tracker? Apa saja masalah yang dapat diselesaikan dengan Issue Tracker?**

Issue Tracker ialah sebuah alat untuk tracking yang terintegrasi dengan repositori Github. Dengan Issue Tracker, kita dapat melacak pekerjaan kita pada Github dan tetap up to date dengan setiap perubahan. Selain itu, Issue Tracker juga sangat berguna ketika kita bekerja dalam satu repositori bersama tim, sehingga komunikasi dapat menjadi lebih efektif. Beberapa permasalahan yang dapat diselesaikan dengan Issue Tracker antara lain;
- Membagi tugas ke masing-masing anggota dalam tim
- Track pekerjaan tiap-tiap anggota
- Berkomunikasi antar satu sama lain dengan fitur mention
- Memastikan apakah codingan anggota tim sudah ok atau belum melalui fitur review. Jadi baru pull ketika di-accept oleh reviewer untuk meminimalisir kesalahan
- Bug tracker untuk proyek yang open source

**2. Apa perbedaan dari git merge dan git merge --squash?**

Baik git merge maupun git merge --squash sama-sama memiliki fungsi yang sama, yaitu untuk menggabungkan branch menjadi satu. Perbedaannya ialah dengan command git merge --squash, penggabungan di git akan menghasilkan satu parent saja. File hasil git merge --squash akan sama saja dengan file yang pakai command git merge biasa, namun ada perbedaan pada metadata commit yang hanya menampilkan satu parent commit saja. Hasilnya akan berupa satu commit di target branch dengan semua perubahan dari merge biasa. Singkatnya, git merge --squash ialah opsi untuk menyingkat riwayat git dari branch setelah selesai pull request. Git merge --squash ini biasanya digunakan agar history kita terlihat lebih rapih dan easier to  read.

**3. Apa keunggulan menggunakan Version Control System seperti Git dalam pengembangan suatu aplikasi?**

Version Control System atau VCS ialah sebuah sistem yang membantu pengguna untuk keep track seluruh koleksi file pengguna. VCS juga memungkinkan pengguna untuk mengakses file dari berbagai device. Git merupakan salah satu contohnya. Dalam hal ini, beberapa benefits penggunaan VCS dalam pengembangan aplikasi antara lain;
- Menyediakan slot repositori yang banyak dan dapat dimanfaatkan untuk proyek yang berbeda lengkap dengan fitur history
- Memiliki fitur branch yang dapat dimanfaatkan anggota tim untuk mengerjakan bagiannya masing-masing (bisa digunakan untuk mengerjakan masing-masing fitur dari sebuah app)
- Juga terdapat fitur merge, sehingga ketika tiap branch dirasa sudah cukup baik, bisa digabungkan jadi satu aplikasi
- Memudahkan pengguna untuk mengakses file dari device manapun
- Khususnya Git, memiliki security dan jaminan data assurance yang baik, sehingga agak tidak mungkin untuk memodifikasi versi sebelumnya ketika selesai push
- Bisa menjadi tempat back-up

### Spring
**4. Apa itu library & dependency?**

Library ialah berbagai data dan kode pemrograman yang terdokumentasi dan digunakan untuk mengembangkan program. Library ini sangat memudahkan pengembang dalam mengerjakan sautu proyek. Kemudian, dependency merupakan kondisi di mana suatu objek bergantung pada objek lainnya.

**5. Apa itu Maven? Mengapa kita menggunakan Maven? Apakah ada alternatif dari Maven?**

Maven ialah sebuah project management tool yang berbasis project object model (POM) dan dirancang untuk mengambil banyak pekerjaan dari build process. Maven biasanya digunakan untuk proyek berbasis Java. Maven menggunakan pendekatan deklaratif, sehingga membantu menegakkan development standard di seluruh perusahaan dan mengurangi waktu yang dibutuhkan untuk menulis dan memelihara build script. Beberapa benefits menggunakan Maven antara lain;
- Memudahkan kita untuk build suatu proyek
- Dapat menambahkan Jars maupun dependensi lain dengan memanfaatkan Maven
- Menyediakan informasi proyek yang meliputi log dokumen, daftar dependensi, dan lain-lain
- Dengan menggunakan Maven, kita dapat membangun sejumlah proyek menjadi tipe output seperti JAR, WAR dan lainnya tanpa melakukan skrip apapun
- Kita dapat dengan mudah mengintegrasikan proyek kita dengan sistem kontrol sumber seperti Git
Alternatif dari Maven antara lain adalah ANT, Red Hat Ansible Automation Platform, Jenkins, Travis CI, dan masih banyak lagi.

**6. Selain untuk pengembangan web, apa saja yang bisa dikembangkan dengan Spring framework?**

Selain pengembangan web, Spring Framework juga dapat dimanfaatkan untuk membangun aplikasi enterprise dan membangun microservice.

**7. Apa perbedaan dari @RequestParam dan @PathVariable? Kapan sebaiknya menggunakan @RequestParam atau @PathVariable?**

Baik @RequestParam maupun @PathVariable digunakan untuk fetch nilai dari parameter yang di-request. Perbedaan signifikan antara @RequestParam dan @PathVariable sendiri terletak pada tempat mengakses nilainya. @RequestParam digunakan untuk mengakses nilai parameter query, sedangkan @PathVariable digunakan untuk mengakses nilai dari template URL. 
Berikut merupakan contoh ketika kita ingin mengakses melalui @PathVariable.

`http://localhost:8080/is-palindrome/isi`

Dan ini merupakan contoh ketika kita akses melalui @RequestParam

`http://localhost:8080/is-palindrome?kalimat=isi`

Jadi, kita bisa menggunakan @PathVariable ketika datanya ingin diteruskan di URL, sedangkan ketika datanya ada di dalam parameter query, baru kita menggunakan @RequestParam.


### What I did not understand😩
- [v] Cara pakai issue di Github yang benar
- [v] Buat codingan pakai Spring ini jadi harus buat programnya di mana T-T
- [v] File apa aja yang harus dibuat ketika mau menjalankan App (di contoh Tutorial ini kan ada banyak file Java dan file HTML terus yang aku bingung defaultnya tuh apaa)

### Referensi
https://docs.github.com/en/issues/tracking-your-work-with-issues/about-issues

https://devblogs.microsoft.com/devops/squash-a-whole-new-way-to-merge-pull-requests/

https://docs.microsoft.com/en-us/azure/devops/repos/git/merging-with-squash?view=azure-devops

https://www.seguetech.com/a-review-of-software-version-control-systems-benefits-and-why-it-matters/

https://www.upgrad.com/blog/what-is-a-version-control-system-git-basics-benefits/

https://www.infoworld.com/article/2072203/an-introduction-to-maven-2.html

https://www.geeksforgeeks.org/introduction-apache-maven-build-automation-tool-java-projects/

https://www.g2.com/products/apache-maven/competitors/alternatives

https://www.dineshonjava.com/requestparam-vs-pathvariable-annotations-in-spring-mvc/

https://www.codebyamir.com/blog/spring-mvc-essentials-requestmapping-pathvariable-annotations

https://www.techopedia.com/definition/3828/software-library

https://askubuntu.com/questions/361741/what-are-dependencies#:~:text=3641%204-,0,one%20of%20the%20other%20modules.
