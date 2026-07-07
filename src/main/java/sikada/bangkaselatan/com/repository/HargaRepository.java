package sikada.bangkaselatan.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sikada.bangkaselatan.com.entity.Harga;

public interface HargaRepository extends JpaRepository<Harga, Long> {

    List<Harga> findByPasarId(Long pasarId);

    List<Harga> findByKomoditasId(Long komoditasId);

    List<Harga> findByPasarIdAndKomoditasId(Long pasarId, Long komoditasId);
}