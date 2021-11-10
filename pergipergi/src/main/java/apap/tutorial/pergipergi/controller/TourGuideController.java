package apap.tutorial.pergipergi.controller;

import apap.tutorial.pergipergi.model.TourGuideModel;
import apap.tutorial.pergipergi.model.TravelAgensiModel;
import apap.tutorial.pergipergi.service.TourGuideService;
import apap.tutorial.pergipergi.service.TravelAgensiService;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TourGuideController {
    
    @Qualifier("tourGuideServiceImpl")
    @Autowired
    private TourGuideService tourGuideService;

    @Qualifier("travelAgensiServiceImpl")
    @Autowired
    private TravelAgensiService travelAgensiService;

    @GetMapping("tour-guide/add/{noAgensi}")
    public String addTourGuideFormPage(@PathVariable Long noAgensi, Model model){
        TourGuideModel guide = new TourGuideModel();
        TravelAgensiModel agensi = travelAgensiService.getAgensiByNoAgensi(noAgensi);
        guide.setAgensi(agensi);
        model.addAttribute("guide", guide);
        return "form-add-tour-guide";
    }

    @PostMapping("/tour-guide/add")
    public String addTourGuideSubmitPage(
        @ModelAttribute TourGuideModel tourGuide,
        Model model
    ){
        tourGuideService.addTourGuide(tourGuide);
        model.addAttribute("noTourGuide", tourGuide.getNoTourGuide());
        return "add-tour-guide";
    }

    @GetMapping("tour-guide/update/{noAgensi}/tour-guide/{noTourGuide}")
    public String updateTourGuide(
        @PathVariable(value = "noAgensi") Long noAgensi, 
        @PathVariable(value = "noTourGuide") Long noTourGuide,
        Model model
    ){
        TravelAgensiModel agensi = travelAgensiService.getAgensiByNoAgensi(noAgensi);
        if (agensi == null || noAgensi == null){
            return "no-agensi";
        }
        TourGuideModel tourGuide = tourGuideService.getTourGuideByNoTourGuide(noTourGuide);
        if (tourGuide == null || noTourGuide == null){
            return "no-tour-guide";
        }
        
        if (agensi.getWaktuTutup().isBefore(LocalTime.now()) || agensi.getWaktuBuka().isAfter(LocalTime.now()) ){
            model.addAttribute("agensi", agensi);
            model.addAttribute("tourGuide", tourGuide);
            return "form-update-guide";
        }
        return "update-fail";

    }

    @PostMapping("/tour-guide/update")
    public String updateTourGuideSubmitPage(
        @ModelAttribute TourGuideModel tourGuide,
        Model model
    ){
        tourGuideService.updateTourGuide(tourGuide);
        
        return "update-tour-guide";
    }

    @GetMapping(value="/tour-guide/delete/{noAgensi}/tour-guide/{noTourGuide}")
    public String deleteTourGuideByNoTourGuide(
        @PathVariable(value = "noAgensi") Long noAgensi, 
        @PathVariable(value = "noTourGuide") Long noTourGuide, 
        Model model
    ){
        TravelAgensiModel agensi = travelAgensiService.getAgensiByNoAgensi(noAgensi);
        if (agensi == null || noAgensi == null){
            return "no-agensi";
        }
        TourGuideModel tourGuide = tourGuideService.getTourGuideByNoTourGuide(noTourGuide);
        if (tourGuide == null || noTourGuide == null){
            return "no-tour-guide";
        }

        if (agensi.getWaktuTutup().isBefore(LocalTime.now()) || agensi.getWaktuBuka().isAfter(LocalTime.now()) ){
            tourGuideService.deleteTourGuide(tourGuide);
            return "delete-tour-guide";
        }
        return "delete-guide-fail";
    }

    @PostMapping("/tour-guide/delete")
    public String deleteTourGuideSubmit(
        @ModelAttribute TravelAgensiModel agensi,
        Model model
    ){
        if (travelAgensiService.isClosed(agensi.getWaktuBuka(), agensi.getWaktuTutup())){
            for (TourGuideModel tourGuide :
            agensi.getListTourGuide()) 
        {
            tourGuideService.deleteTourGuide(tourGuide);
        }
        }
        model.addAttribute("noAgensi", agensi.getNoAgensi());
        return "delete-tour-guide";
    }

}
