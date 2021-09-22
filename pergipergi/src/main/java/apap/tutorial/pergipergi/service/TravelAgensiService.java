package apap.tutorial.pergipergi.service;

import apap.tutorial.pergipergi.model.TravelAgensiModel;

import java.util.List;

public interface TravelAgensiService {
    // method untuk menambahkan agensi
    void addAgensi(TravelAgensiModel travelAgensi);

    // method untuk mendapatkan daftar agensi yang telah tersimpan
    List<TravelAgensiModel> getListAgensi();

    // method untuk mendapatkan data agensi berdasarkan id agensi
    TravelAgensiModel getAgensiByIdAgensi(String idAgensi);

    // method untuk menghapus agensi berdasarkan id agensi
    void removeAgensi(TravelAgensiModel travelAgensi);
}
