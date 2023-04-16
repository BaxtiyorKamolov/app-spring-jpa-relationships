package uz.pdp.appspringjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appspringjparelationships.entity.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty,Integer> {
    boolean existsByNameAndUniversityId(String name, Integer universityId);

    List<Faculty> findAllByUniversityId(Integer universityId);
}
