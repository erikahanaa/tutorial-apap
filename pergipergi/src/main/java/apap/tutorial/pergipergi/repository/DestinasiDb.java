package apap.tutorial.pergipergi.repository;

import apap.tutorial.pergipergi.model.DestinasiModel;
// import apap.tutorial.pergipergi.model.TravelAgensiModel;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import java.util.Optional;

public interface DestinasiDb extends JpaRepository<DestinasiModel, Long>{
    
}
