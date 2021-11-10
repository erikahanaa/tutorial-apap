package apap.tutorial.pergipergi.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name="tour_guide")
public class TourGuideModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noTourGuide;

    @NotNull
    @Size(max=30)
    @Column(name="nama_tour_guide", nullable = false)
    private String namaTourGuide;

    @NotNull
    @Column(name = "jenis_kelamin", nullable = false)
    private Integer jenisKelamin;

    @Column(name = "umur", nullable = false, columnDefinition = "integer default 0")
    private Integer umur;

    // Relasi dengan AgensiModel
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "no_agensi", referencedColumnName = "noAgensi", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TravelAgensiModel agensi;

}

