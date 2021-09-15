package apap.tutorial.pergipergi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tutorial.pergipergi.model.TravelAgensiModel;
import apap.tutorial.pergipergi.service.TravelAgensiService;

import java.util.*;

@Controller
public class TravelAgensiController {
    @Autowired
    private TravelAgensiService travelAgensiService;

    // routing URL yang diinginkan
    @RequestMapping("agensi/add")
    public String addAgensi(
        @RequestParam(value = "idAgensi", required = true) String idAgensi,
        @RequestParam(value = "namaAgensi", required = true) String namaAgensi,
        @RequestParam(value = "alamat", required = true) String alamat,
        @RequestParam(value = "noTelepon", required = true) String noTelepon,
        Model model
    ){
      
        // membuat objek TravelAgensiModel
        TravelAgensiModel agensi = new TravelAgensiModel(idAgensi, namaAgensi, alamat, noTelepon);
        
        // memanggil service addAgensi
        travelAgensiService.addAgensi(agensi);
        
        // add variabel id agensi ke 'idAgensi' untuk dirender di thymeleaf
        model.addAttribute("idAgensi", idAgensi);

        // return view template yang digunakan
        return "add-agensi";
    }
    // membuat request untuk halaman view all
    @RequestMapping("agensi/viewAll")
    public String listAgensi(Model model){

        // mendapat semua TravelAgensiModel
        List<TravelAgensiModel> listAgensi = travelAgensiService.getListAgensi();

        // add variabel seluruh TravelAgensiModel ke 'List Agensi' untuk dirender pada thymeleaf
        model.addAttribute("listAgensi", listAgensi);

        // return view template yang diinginkan
        return "viewall-agensi";
    }

    // membuat request untuk halaman detail agensi
    @RequestMapping("agensi/view")
    public String detailAgensi(
        @RequestParam(value = "idAgensi", required = false) String idAgensi,
        Model model
    ){
        // mendapat TravelAgensiModel sesuai idAgensi
        TravelAgensiModel agensi = travelAgensiService.getAgensiByIdAgensi(idAgensi);

        // mengecek dulu apakah Agensi ada
        if(agensi == null){
            return "no-agensi";
        }

        // add variabel TravelAgensiModel ke 'agensi' untuk dirender pada thymeleaf
        model.addAttribute("agensi", agensi);

        return "view-agensi";
    }

    // membuat request untuk detail agensi dengan menggunakan path variable
    @RequestMapping({"agensi/view/id-agensi/{idAgensi}","agensi/view/id-agensi"})
    public String viewIdAgensiWithPathVariable(
            @PathVariable(value = "idAgensi", required = false) String idAgensi,
            Model model
    ){
        // mendapatkan TravelAgensiModel sesuai idAgensi
        TravelAgensiModel agensi = travelAgensiService.getAgensiByIdAgensi(idAgensi);

        // mengecek dulu apakah Agensi ada
        if(agensi == null){
            return "no-agensi";
        }

        // add variabel TravelAgensiModel ke 'agensi' untuk dirender pada thymeleaf
        model.addAttribute("agensi", agensi);
        return "view-agensi";
    }

    @RequestMapping({"agensi/update/id-agensi/{idAgensi}/no-telepon/{noTelepon}", "agensi/update/id-agensi/no-telepon/{noTelepon}"})
    public String updateNomorTeleponAgensiWithPathVariable(
            @PathVariable(value = "idAgensi", required = false) String idAgensi,
            @PathVariable(value = "noTelepon") String noTelepon,
            Model model
    ){
        // mendapatkan TravelAgensiModel sesuai dengan idAgensi
        TravelAgensiModel agensi = travelAgensiService.getAgensiByIdAgensi(idAgensi);

        // mengecek dulu apakah Agensi ada
        if(agensi == null){
            return "no-agensi";
        }

        // melakukan set untuk nomor telepon pada URL
        agensi.setNoTelepon(noTelepon);

        // add variabel TravelAgensiModel ke 'agensi' untuk dirender pada thymeleaf
        model.addAttribute("agensi", agensi);

        return "update-agensi";
    }

    @RequestMapping({"agensi/remove/id-agensi/{idAgensi}","agensi/remove/id-agensi"})
    public String deleteAgensiWithPathVariable(
            @PathVariable(value = "idAgensi", required = false) String idAgensi,
            Model model
    ){
        // mendapatkan TravelAgensiModel sesuai dengan idAgensi
        TravelAgensiModel agensi = travelAgensiService.getAgensiByIdAgensi(idAgensi);

        // mengecek dulu apakah Agensi ada
        if(agensi == null){
            return "no-agensi";
        }

        // hapus Agensi nya yeay
        travelAgensiService.removeAgensi(agensi);

        // add variabel TravelAgensiModel ke 'agensi' untuk dirender pada thymeleaf
        model.addAttribute("agensi", agensi);

        return "remove-agensi";
    }

}

