package apap.tutorial.pergipergi.service;

import apap.tutorial.pergipergi.model.TravelAgensiModel;
import apap.tutorial.pergipergi.repository.TravelAgensiDb;
import apap.tutorial.pergipergi.rest.AgensiDetail;
import apap.tutorial.pergipergi.rest.Setting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class TravelAgensiRestServiceImpl implements TravelAgensiRestService {
    
    @Autowired
    private TravelAgensiDb travelAgensiDb;
    private final WebClient webClient;

    @Override
    public TravelAgensiModel createAgensi(TravelAgensiModel agensi){
        return travelAgensiDb.save(agensi);
    }

    @Override
    public List<TravelAgensiModel> retrieveListAgensi() {
        return travelAgensiDb.findAll();
    }

    @Override
    public TravelAgensiModel getAgensiByNoAgensi(Long noAgensi){
        Optional<TravelAgensiModel> agensi = travelAgensiDb.findByNoAgensi(noAgensi);
        
        if (agensi.isPresent()){
            return agensi.get();
        }
        else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public TravelAgensiModel updateAgensi(Long noAgensi, TravelAgensiModel agensiUpdate){
        TravelAgensiModel agensi = getAgensiByNoAgensi(noAgensi);
        agensi.setNamaAgensi(agensiUpdate.getNamaAgensi());
        agensi.setAlamatAgensi(agensiUpdate.getAlamatAgensi());
        agensi.setNoTeleponAgensi(agensiUpdate.getNoTeleponAgensi());
        agensi.setWaktuBuka(agensiUpdate.getWaktuBuka());
        agensi.setWaktuTutup(agensiUpdate.getWaktuTutup());

        return travelAgensiDb.save(agensi);
    }

    @Override
    public void deleteAgensi(long noAgensi){
        LocalTime now = LocalTime.now();
        TravelAgensiModel agensi = getAgensiByNoAgensi(noAgensi);

        if((now.isBefore(agensi.getWaktuBuka()) || now.isAfter(agensi.getWaktuTutup())) && agensi.getListTourGuide().isEmpty()){
            travelAgensiDb.delete(agensi);
        }
        else{
            throw new UnsupportedOperationException("Agensi still open!");
        }
    }

    @Override
    public Mono<String> getStatus(Long noAgensi){
        return this.webClient.get().uri("/rest/agensi/" + noAgensi + "/status").retrieve().bodyToMono(String.class);
    }

    @Override
    public Mono<AgensiDetail> postStatus(){
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("namaAgensi", "Agensi Mock Server");
        data.add("alamatAgensi", "Depok");
        return this.webClient.post().uri("rest/agensi/full").syncBody(data).retrieve().bodyToMono(AgensiDetail.class);
    }

    public TravelAgensiRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.agensiUrl).build();
    }
    
}
