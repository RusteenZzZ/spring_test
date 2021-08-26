package corporations.structure.ggwp.field;

import corporations.structure.ggwp.corporation.Corporation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FieldRepo extends JpaRepository<Field, Long> {

    @Query("SELECT f FROM Field f WHERE f.name = ?1")
    Optional<Field> findFieldByName(String name);
}
