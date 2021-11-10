package apap.tutorial.pergipergi.restcontroller;

import apap.tutorial.pergipergi.model.TourGuideModel;
import apap.tutorial.pergipergi.service.TourGuideRestService;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1")
public class TourGuideRestController {
    
    @Autowired
    private TourGuideRestService tourGuideRestService;

    @GetMapping(value = "tour/{noTourGuide}")
    private TourGuideModel retrieveTourGuide(@PathVariable("noTourGuide") Long noTourGuide){
        try{
            return tourGuideRestService.getTourGuideByNoTourGuide(noTourGuide);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No Tour Guide " + String.valueOf(noTourGuide) + " Not Found."
            );
        }
    }

    @GetMapping(value = "/tour/umur/{noTourGuide}")
    private String getTourGuide(@PathVariable("noTourGuide") Long noTourGuide){
        return tourGuideRestService.getTourGuideAge(noTourGuide);
    }
}