package apap.tutorial.pergipergi.service;

import apap.tutorial.pergipergi.model.DestinasiModel;
import apap.tutorial.pergipergi.repository.DestinasiDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class DestinasiRestServiceImpl implements DestinasiRestService{
    
    @Autowired
    private DestinasiDb destinasiDb;

    @Override
    public DestinasiModel createDestinasi(DestinasiModel destinasi){
        return destinasiDb.save(destinasi);
    }

    @Override
    public List<DestinasiModel> retrieveListDestinasi() {
        return destinasiDb.findAll();
    }

    @Override
    public DestinasiModel getDestinasiByNoDestinasi(Long noDestinasi){
        Optional<DestinasiModel> destinasi = destinasiDb.findByNoDestinasi(noDestinasi);
        
        if (destinasi.isPresent()){
            return destinasi.get();
        }
        else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public DestinasiModel updateDestinasi(Long noDestinasi, DestinasiModel destinasiUpdate){
        DestinasiModel destinasi = getDestinasiByNoDestinasi(noDestinasi);
        destinasi.setNegaraDestinasi(destinasiUpdate.getNegaraDestinasi());
        destinasi.setIsBebasVisa(destinasiUpdate.getIsBebasVisa());

        return destinasiDb.save(destinasi);
    }

    @Override
    public void deleteDestinasi(long noDestinasi){
        DestinasiModel destinasi = getDestinasiByNoDestinasi(noDestinasi);
        destinasiDb.delete(destinasi);
    }
}