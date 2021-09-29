package apap.tutorial.pergipergi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalTime;

import apap.tutorial.pergipergi.model.TravelAgensiModel;
import apap.tutorial.pergipergi.model.TourGuideModel;
import apap.tutorial.pergipergi.service.TravelAgensiService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TravelAgensiController {

    @Qualifier("travelAgensiServiceImpl")
    @Autowired
    private TravelAgensiService travelAgensiService;

    @GetMapping("agensi/add")
    public String addAgensiFormPage(Model model){
        model.addAttribute("agensi", new TravelAgensiModel());
        return "form-add-agensi";
    }

    @PostMapping("agensi/add")
    public String addAgensiSubmitPage(
        @ModelAttribute TravelAgensiModel agensi,
        Model model
    ){
        travelAgensiService.addAgensi(agensi);
        model.addAttribute("noAgensi", agensi.getNoAgensi());
        return "add-agensi";
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

