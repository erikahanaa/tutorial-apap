package apap.tutorial.pergipergi.service;

import org.springframework.stereotype.Service;

import apap.tutorial.pergipergi.model.TravelAgensiModel;

import java.util.*;

@Service
public class TravelAgensiServiceImpl implements TravelAgensiService{
    
    private List<TravelAgensiModel> listAgensi;

    public TravelAgensiServiceImpl(){
        listAgensi = new ArrayList<>(); 
        // List<TipeData> listAgensi
    }

    @Override
    public void addAgensi(TravelAgensiModel travelAgensiModel){
        listAgensi.add(travelAgensiModel);
    }

    @Override
    public List<TravelAgensiModel> getListAgensi(){
        return listAgensi;
    }

    @Override
    public TravelAgensiModel getAgensiByIdAgensi(String idAgensi){
        for (TravelAgensiModel agensi: listAgensi){
            if (agensi.getIdAgensi().equals(idAgensi)){
                return agensi;
            }

        }
        return null;
    }

    @Override
    public void removeAgensi(TravelAgensiModel travelAgensiModel){
        listAgensi.remove(travelAgensiModel);
    }
}
