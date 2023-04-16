package uz.pdp.appspringjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appspringjparelationships.entity.Address;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
