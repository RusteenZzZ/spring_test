package corporations.structure.ggwp.worker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkerRepo extends JpaRepository<Worker, Long> {

    @Query("SELECT w FROM Worker w WHERE w.email = ?1")
    Optional<Worker> findWorkerByEmail(String email);
}
