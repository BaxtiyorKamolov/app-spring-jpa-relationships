package uz.pdp.appspringjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appspringjparelationships.entity.University;

@Repository
public interface UniversityRepository extends JpaRepository<University,Integer> {

}
