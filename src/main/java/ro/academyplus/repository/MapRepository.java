package ro.academyplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.academyplus.model.data.map.storeable.MapStructure;

public interface MapRepository extends JpaRepository<MapStructure, Long> {
    MapStructure findOneById(int id);
}
