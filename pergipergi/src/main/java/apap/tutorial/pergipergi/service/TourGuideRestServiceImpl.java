package apap.tutorial.pergipergi.service;

import apap.tutorial.pergipergi.model.TourGuideModel;
import apap.tutorial.pergipergi.repository.TourGuideDb;
import apap.tutorial.pergipergi.rest.TourGuideDetail;
import apap.tutorial.pergipergi.rest.Setting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class TourGuideRestServiceImpl implements TourGuideRestService{
    
    @Autowired
    private TourGuideDb tourGuideDb;
    private final WebClient webClient;

    @Override
    public TourGuideModel getTourGuideByNoTourGuide(Long noTourGuide){
        Optional<TourGuideModel> tourGuide = tourGuideDb.findByNoTourGuide(noTourGuide);
        
        if (tourGuide.isPresent()){
            return tourGuide.get();
        }
        else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public TourGuideModel getTourGuideAge(Long noTourGuide){
        Optional<TourGuideModel> tourGuide = tourGuideDb.findByNoTourGuide(noTourGuide);
        // System.out.println(tourGuide.get().getNamaTourGuide());
        // // return this.webClient.get().uri("?name=" + tourGuide.get().getNamaTourGuide()).retrieve().bodyToMono(String.class);
        // String response = this.webClient.get().uri("?name=" + tourGuide.get().getNamaTourGuide()).retrieve().bodyToMono(String.class).block();
        // System.out.println(response);
        // return response;
        TourGuideDetail response = this.webClient.get().uri("/?name=" + tourGuide.get().getNamaTourGuide()).retrieve().bodyToMono(TourGuideDetail.class).block();
        tourGuide.get().setUmur(response.getTourGuideAge());
        tourGuideDb.save(tourGuide.get());
        return tourGuide.get();
    }

    public TourGuideRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.guideUrl).build();
    }
    
}
