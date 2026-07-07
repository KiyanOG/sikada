package sikada.bangkaselatan.com.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "harga")
public class Harga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double harga;

    private LocalDate tanggal;

    @ManyToOne
    @JoinColumn(name = "pasar_id")
    private Pasar pasar;

    @ManyToOne
    @JoinColumn(name = "komoditas_id")
    private Komoditas komoditas;

    @PrePersist
    @PreUpdate
    public void updateTanggal() {
        this.tanggal = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public Pasar getPasar() {
        return pasar;
    }

    public void setPasar(Pasar pasar) {
        this.pasar = pasar;
    }

    public Komoditas getKomoditas() {
        return komoditas;
    }

    public void setKomoditas(Komoditas komoditas) {
        this.komoditas = komoditas;
    }
}