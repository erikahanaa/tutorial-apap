package apap.tutorial.pergipergi.service;

import apap.tutorial.pergipergi.model.TravelAgensiModel;
import apap.tutorial.pergipergi.rest.AgensiDetail;

import java.util.List;

import reactor.core.publisher.Mono;

public interface TravelAgensiRestService {
    
    TravelAgensiModel createAgensi(TravelAgensiModel agensi);
    List<TravelAgensiModel> retrieveListAgensi();
    TravelAgensiModel getAgensiByNoAgensi(Long noAgensi);
    TravelAgensiModel updateAgensi(Long noAgensi, TravelAgensiModel agensiUpdate);
    void deleteAgensi(long noAgensi);
    Mono<String> getStatus(Long noAgensi);
    Mono<AgensiDetail> postStatus();
}


