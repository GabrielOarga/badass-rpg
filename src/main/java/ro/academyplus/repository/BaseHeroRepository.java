package ro.academyplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.academyplus.model.data.characters.BaseHero;

public interface BaseHeroRepository extends JpaRepository<BaseHero, Long> {
    BaseHero findOneByName(String name);
}
