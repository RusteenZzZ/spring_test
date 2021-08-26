package corporations.structure.ggwp.corporation;

import corporations.structure.ggwp.worker.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CorporationRepo extends JpaRepository<Corporation, Long> {

    @Query("SELECT c FROM Corporation c WHERE c.email = ?1")
    Optional<Corporation> findCorporationByEmail(String email);
}
