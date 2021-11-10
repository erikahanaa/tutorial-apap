package apap.tutorial.pergipergi.restcontroller;

import apap.tutorial.pergipergi.model.DestinasiModel;
import apap.tutorial.pergipergi.service.DestinasiRestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1")
public class DestinasiRestController {
    
    @Autowired
    private DestinasiRestService destinasiRestService;

    @PostMapping(value="/destinasi")
    private String createDestinasi(@Valid @RequestBody DestinasiModel destinasi, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        }
        else{
            destinasiRestService.createDestinasi(destinasi);
            return "Create Destinasi success!";
        }
    }

    @GetMapping(value = "destinasi/{noDestinasi}")
    private DestinasiModel retrieveDestinasi(@PathVariable("noDestinasi") Long noDestinasi){
        try{
            return destinasiRestService.getDestinasiByNoDestinasi(noDestinasi);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No Destinasi " + String.valueOf(noDestinasi) + " Not Found."
            );
        }
    }

    @DeleteMapping(value = "/destinasi/{noDestinasi}")
    private ResponseEntity<String> deleteDestinasi(@PathVariable("noDestinasi") Long noDestinasi){
        try{
            destinasiRestService.deleteDestinasi(noDestinasi);
            return ResponseEntity.ok("Destinasi has been deleted!");
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Destinasi with No Destinasi "+ String.valueOf(noDestinasi)+ " Not Found!"
            );
        }
    }

    @PutMapping(value = "/destinasi/{noDestinasi}")
    private String updateDestinasi(@PathVariable("noDestinasi") Long noDestinasi, @RequestBody DestinasiModel destinasi){
        try{
            destinasiRestService.updateDestinasi(noDestinasi, destinasi);
            return "Update destinasi success";
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Destinasi with No Destinasi " + String.valueOf(noDestinasi) + " Not Found."
            );
        }
    }

    @GetMapping(value = "/list-destinasi")
    private List<DestinasiModel> retrieveListDestinasi(){
        return destinasiRestService.retrieveListDestinasi();
    }
}
