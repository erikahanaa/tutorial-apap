# Tutorial APAP

## Authors

* **Erika Hana Prasanti** - *1906298872* - *APAP B*

## Tutorial 2✨

### What I have learned today✅😊

### Pertanyaan

**1. Cobalah untuk menambahkan sebuah Agensi dengan mengakses link berikut: http://localhost:8080/agensi/add?idAgensi=1&namaAgensi=Papa%20APAP&alamat=Maung%20Fasilkom&noTelepon=081xxx Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi?**

Ketika klik link tersebut, maka yang terjadi adalah error. Hal ini karena ketika kita membuka link tersebut, maka kita akan diarahkan ke halaman add-idAgensi, sedangkan kita belum membuat halaman untuk menampilkan add-idAgensi. Jika nanti halaman add-idAgensi nya sudah terbuat, maka halaman tersebut nantinya akan bisa diakses.

**2. Menurut kamu anotasi @Autowired pada class Controller tersebut merupakan implementasi dari konsep apa? Dan jelaskan secara singkat cara kerja @Autowired tersebut dalam konteks service dan controller yang telah kamu buat**

Anotasi @Autowired pada class Controller merupakan implementasi dari konsep dependency injection. Kemudian, cara kerja @Autowired dalam konteks service dan controller pada lab kali ini adalah @Autowired melakukan dependency injection terhadap service yang mengimplementasikan autowiring secara otomatis, sehingga kita tidak perlu lagi menambahkan argumen di constructor, maupun menyediakan setter getter lagi.

**3. Cobalah untuk menambahkan sebuah Agensi dengan mengakses link berikut: http://localhost:8080/agensi/add?idAgensi=1&namaAgensi=Papa%20APAP&alamat=Maung%20Fasilkom Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi.**

Ketika mengakses link tersebut, maka akan muncul error. Hal ini dikarenakan pada link tersebut tidak ada parameter untuk menambahkan nomor telepon, sehingga link tersebut terhitung tidak lengkap dan error. Untuk bisa sampai di halaman add-agensi, keseluruhan parameter, termasuk idAgensi, namaAgensi, alamat, dan nomorTelepon harus ada semua karena di controller kita setting bernilai true. Nah, jika ingin mengakses halamannya dan memastikan tidak error, maka kita perlu untuk melengkapi semua parameter pada url, kemudian nanti akan muncul halaman yang berisikan tulisan "Travel Agensi dengan id Agensi 1 berhasil ditambahkan." dengan tombol "Kembali" di bawahnya. Hal ini berarti halaman yang menampilkan add-agensi sudah bisa diakses karena kita sudah berhasil buat html-nya. 

**4. Jika Papa APAP ingin melihat Travel Agensi dengan nama Papa APAP, link apa yang harus diakses?**

Link yang harus diakses adalah link untuk menuju halaman view Agensi dengan parameter tertentu. Maka dari itu, Papa APAP harus menuju url berikut: http://localhost:8080/agensi/view?idAgensi=Papa%20APAP . URL ini menunjukan bahwa kita mencari suatu agensi dengan id Agensi berupa Papa APAP sehingga pada URL kita bisa lihat idAgensi nya sesuai yang diminta. Ketika kita ingin mencari agensi dengan id lain kita tinggal ganti id agensinya saja di URL. Lalu, agar URL tersebut tidak error, saya akan menambahkan agensi dengan id Agensi Papa APAP dengan link berikut: http://localhost:8080/agensi/add?idAgensi=Papa%20APAP&namaAgensi=Papa%20APAP&alamat=Fasilkom%20Jaya&noTelepon=088888888123 , sehingga ketika di-search akan muncul seperti pada screenshot ini.

Link Screenshot buat agensi baru: https://ibb.co/LQygZJ4

Link Screenshot agensi baru muncul ketika di search: https://ibb.co/QMsQnRw

**5. Tambahkan 1 contoh Travel Agensi lainnya sesukamu. Lalu cobalah untuk mengakses http://localhost:8080/agensi/viewAll , apa yang akan ditampilkan? Sertakan juga bukti screenshotmu.**

Untuk soal ini, saya menambahka suatu travel agensi baru dengan id agensi 2, nama agensi ErikaKeren, alamat FasilkomOke, dan nomor telepon 021727666666 dengan cara memasukkan link berikut http://localhost:8080/agensi/add?idAgensi=2&namaAgensi=ErikaKeren&alamat=FasilkomOke&noTelepon=021727666666 . Berikut saya lampirkan screenshot kalau id Agensi baru sudah berhasil terbuat. 
Selanjutnya, saya akan menuju halaman view All untuk melihat agensi yang sudah terdaftar. Berikut tampilannya. Dari screenshot, dapat dilihat bahwa id yang baru saya masukkan dengan nama agensi ErikaKeren sudah muncul😆

Link Screenshot buat agensi baru: https://ibb.co/6PCCtGd

Link Screenshot seluruh agensi: https://ibb.co/smjNsjJ

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