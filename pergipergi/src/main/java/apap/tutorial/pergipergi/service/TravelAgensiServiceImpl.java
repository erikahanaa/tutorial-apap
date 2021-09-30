package apap.tutorial.pergipergi.service;

import org.springframework.stereotype.Service;

import apap.tutorial.pergipergi.model.TravelAgensiModel;
import apap.tutorial.pergipergi.repository.TravelAgensiDb;
import apap.tutorial.pergipergi.repository.DestinasiDb;

import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TravelAgensiServiceImpl implements TravelAgensiService{
    
    @Autowired
    TravelAgensiDb travelAgensiDb;

    @Override
    public void addAgensi(TravelAgensiModel travelAgensi){
        travelAgensiDb.save(travelAgensi);
    }

    @Override
    public List<TravelAgensiModel> getListAgensi(){
        return travelAgensiDb.findAll();
    }

    @Override
    public List<TravelAgensiModel> findAllByOrderByNamaAgensiAsc(){
        return travelAgensiDb.findAllByOrderByNamaAgensiAsc();
    }

    @Override
    public TravelAgensiModel getAgensiByNoAgensi(Long noAgensi){
        Optional <TravelAgensiModel> agensi = travelAgensiDb.findByNoAgensi(noAgensi);
        if (agensi.isPresent()) return agensi.get();
        else return null;
    }

    @Override
    public TravelAgensiModel updateAgensi(TravelAgensiModel travelAgensi){
        travelAgensiDb.save(travelAgensi);
        return travelAgensi;
    }

    @Override
    public void deleteAgensi(TravelAgensiModel travelAgensi){
        travelAgensiDb.delete(travelAgensi);
    }

    @Override
    public boolean isClosed(LocalTime waktuBuka, LocalTime waktuuTutup){
        LocalTime now = LocalTime.now();
        if (now.isBefore(waktuBuka) || now.isAfter(waktuuTutup)){
            return true;
        }
        return false;
    }


}
