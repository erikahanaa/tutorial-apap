# Tutorial APAP

## Authors

* **Erika Hana Prasanti** - *1906298872* - *APAP B*

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

jawaban no 4

**5. Jelaskan kegunaan FetchType.LAZY, CascadeType.ALL, dan FetchType.EAGER**

- FetchType.LAZY merupakan tipe Hibernate yang digunakan untuk menunda inisialisasi objek selama mungkin. Dalam kata lain, tipe ini tidak melakukan semua load untuk collection object child ketika parent-nya akan di-fetch, makanya disebut fetchtype-lazy. Ini merupakan default dari fetch. Keuntungannya menggunakan tipe LAZY ini initial load time jauh lebih kecil daripada pendekatan lain dan konsumsi memori juga lebih sedikit daripada di pendekatan lain. Kerugiannya, tipe ini inisialisasi yang tertunda dapat memengaruhi kinerja selama momen yang tidak diinginkan dan dalam beberapa kasus, kita perlu menangani objek yang diinisialisasi dengan erhatian khusus atau harus pakai exception.
- CascadeType.ALL merupakan tipe Hibernate yang akan menyebarkan semua (cascade all) operasi entity manager yang meliputi persist, remove, refresh, merge, dan detach ke entitas terkait.
- FetchType.EAGER merupakan tipe Hibernate di mana inisialisasi data terjadi di tempat. Dalam kata lain, tipe ini fetch child ketika parent-nya di fetch. Keuntungannya, tidak ada dampak kinerja terkait inisialisasi yang tertunda dan kerugiannya initial loading time-nya lama dan ada kemungkinan dia nge-load data yang tidak penting, sehingga bisa memengaruhi performance.


### What I did not understand😩
- [ ] Masih belum lancar untuk berpindah-pindah view, model, dan controller untuk mencapai hasil yang diinginkan

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
