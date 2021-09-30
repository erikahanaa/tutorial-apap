package apap.tutorial.pergipergi.service;

import apap.tutorial.pergipergi.model.TravelAgensiModel;

import java.util.List;
import java.time.LocalTime;

public interface TravelAgensiService {
    void addAgensi(TravelAgensiModel travelAgensi);
    List<TravelAgensiModel> getListAgensi();
    TravelAgensiModel getAgensiByNoAgensi(Long noAgensi);
    TravelAgensiModel updateAgensi(TravelAgensiModel travelAgensi);
    List<TravelAgensiModel> findAllByOrderByNamaAgensiAsc();
    void deleteAgensi(TravelAgensiModel travelAgensi);
    boolean isClosed(LocalTime waktuBuka, LocalTime waktuTutup);
}
