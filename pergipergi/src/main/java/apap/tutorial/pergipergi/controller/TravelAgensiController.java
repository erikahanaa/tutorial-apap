package apap.tutorial.pergipergi.controller;

import org.aspectj.weaver.patterns.Bindings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalTime;

import apap.tutorial.pergipergi.model.TravelAgensiModel;
import apap.tutorial.pergipergi.model.TourGuideModel;
import apap.tutorial.pergipergi.model.DestinasiModel;
import apap.tutorial.pergipergi.service.TravelAgensiService;
import apap.tutorial.pergipergi.service.DestinasiService;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;

@Controller
public class TravelAgensiController {

    @Qualifier("travelAgensiServiceImpl")
    @Autowired
    private TravelAgensiService travelAgensiService;

    @Qualifier("destinasiServiceImpl")
    @Autowired
    private DestinasiService destinasiService;

    // tambah atribut listDestinasi
    @GetMapping("agensi/add")
    public String addAgensiFormPage(Model model){
        List<DestinasiModel> checkListDestinasi = destinasiService.getListDestinasi();
        
        model.addAttribute("agensi", new TravelAgensiModel());
        model.addAttribute("checkListDestinasi", checkListDestinasi);

        return "form-add-agensi";
    }

    // modif post mapping agar bisa add destinasi
    @PostMapping(value = "agensi/add", params = "save")
    public String addAgensiSubmitPage(
        @ModelAttribute TravelAgensiModel agensi,
        Model model
    ){
        travelAgensiService.addAgensi(agensi);
        model.addAttribute("noAgensi", agensi.getNoAgensi());
        return "add-agensi";
    }

    // buat addrow
    @PostMapping(value = "/agensi/add", params = "addRow")
    public String addAgensiAddRow(
        @ModelAttribute TravelAgensiModel agensi,
        BindingResult bindingResult,
        Model model
    ){
        List<DestinasiModel> checkListDestinasi = destinasiService.getListDestinasi();
 
        if(agensi.getListDestinasi() ==  null){
            agensi.setListDestinasi(new ArrayList<DestinasiModel>());;
        }
 
        List<DestinasiModel> listDestinasi = agensi.getListDestinasi();
        DestinasiModel destinasi = new DestinasiModel();
        listDestinasi.add(destinasi);
 
        model.addAttribute("agensi", agensi);
        model.addAttribute("listDestinasi", checkListDestinasi);
        return "form-add-agensi";
    }

    // untuk delete row
    @PostMapping(value = "/agensi/add", params = "deleteRow")
    public String addAgensiDeleteRow(
        @ModelAttribute TravelAgensiModel agensi,
        final BindingResult bindingResult,
        final HttpServletRequest request,
        Model model
    ){
        List<DestinasiModel> checkListDestinasi = destinasiService.getListDestinasi();
        final Integer rowId = Integer.valueOf(request.getParameter("deleteRow"));
        agensi.getListDestinasi().remove(rowId.intValue());
 
        model.addAttribute("agensi", agensi);
        model.addAttribute("listDestinasi", checkListDestinasi);
        return "form-add-agensi";
    }

    @GetMapping("agensi/viewall")
    public String listAgensi(Model model){
        List<TravelAgensiModel> listAgensi = travelAgensiService.getListAgensi();
        model.addAttribute("listAgensi", listAgensi);
        return "viewall-agensi";
    }

    @GetMapping("agensi/viewall-asc")
    public String listAgensiAsc(Model model){
        List<TravelAgensiModel> listAgensi = travelAgensiService.findAllByOrderByNamaAgensiAsc();
        model.addAttribute("listAgensi", listAgensi);
        return "viewall-asc";
    }

    @GetMapping("agensi/view")
    public String viewDetailAgensiPage(
        @RequestParam(value = "noAgensi", required = false) Long noAgensi,
        Model model
    ){

        TravelAgensiModel agensi = travelAgensiService.getAgensiByNoAgensi(noAgensi);
        if (agensi == null || noAgensi == null){
            return "no-agensi";
        }
        List<TourGuideModel> listTourGuide = agensi.getListTourGuide();

        model.addAttribute("agensi", agensi);
        model.addAttribute("listTourGuide", listTourGuide);
        model.addAttribute("listDestinasi", agensi.getListDestinasi());

        return "view-agensi";

    }

    @GetMapping("agensi/update/{noAgensi}")
    public String updateAgensiFormPage(
        @PathVariable Long noAgensi,
        Model model
    ){
        TravelAgensiModel agensi = travelAgensiService.getAgensiByNoAgensi(noAgensi);
        if (agensi == null || noAgensi == null){
            return "no-agensi";
        }
        model.addAttribute("agensi", agensi);
        return "form-update-agensi";
    }

    @PostMapping("/agensi/update")
    public String updateAgensiSubmitPage(
        @ModelAttribute TravelAgensiModel agensi,
        Model model
    ){
        TravelAgensiModel updatedAgensi = travelAgensiService.updateAgensi(agensi);
        model.addAttribute("noAgensi", updatedAgensi.getNoAgensi());
        return "update-agensi";
    }

    @GetMapping(value="/agensi/remove/{noAgensi}")
    public String deleteAgensi(
        @PathVariable(value = "noAgensi") Long noAgensi, 
        Model model
    ){
        TravelAgensiModel agensi = travelAgensiService.getAgensiByNoAgensi(noAgensi);
        if (agensi == null || noAgensi == null){
            return "no-agensi";
        }
        List<TourGuideModel> listTourGuide = agensi.getListTourGuide();
        if ((agensi.getWaktuTutup().isBefore(LocalTime.now()) || agensi.getWaktuBuka().isAfter(LocalTime.now())) && listTourGuide.isEmpty()){
            travelAgensiService.deleteAgensi(agensi);
            return "remove-agensi";
        }
        return "delete-agensi-fail";
    }

}

