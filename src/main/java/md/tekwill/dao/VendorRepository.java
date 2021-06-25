package md.tekwill.dao;

import md.tekwill.entity.product.Vendor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VendorRepository extends CrudRepository<Vendor, Long> {

    List<Vendor> findAll();
}
