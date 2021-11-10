package apap.tutorial.pergipergi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name="travel_agensi")

@JsonIgnoreProperties(value={"listTourGuide"},allowSetters = true)
public class TravelAgensiModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noAgensi;

    @NotNull
    @Size(max=30)
    @Column(name = "nama_agensi", nullable = false)
    private String namaAgensi;

    @NotNull
    @Size(max=30)
    @Column(name = "alamat_agensi", nullable = false)
    private String alamatAgensi;

    @NotNull
    @Size(max=30)
    @Column(name = "no_telepon_agensi", nullable = false)
    private String noTeleponAgensi;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime waktuBuka;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime waktuTutup;

    // Relasi dengan TourGuideModel
    @OneToMany(mappedBy = "agensi", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TourGuideModel> listTourGuide;

    // Relasi dengan DestinasiModel
    @ManyToMany
    @JoinTable(
        name = "agensi_destinasi",
        joinColumns = @JoinColumn(name = "no_agensi"),
        inverseJoinColumns = @JoinColumn(name = "no_destinasi")
    )
    List<DestinasiModel> listDestinasi;
}