package md.tekwill.service;

import md.tekwill.dao.VendorRepository;
import md.tekwill.entity.product.Vendor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<Vendor> getAll() {
        return vendorRepository.findAll();
    }

    @Override
    public Vendor getById(long vendorId) {
        Optional<Vendor> optionalVendor = vendorRepository.findById(vendorId);
        if (optionalVendor.isPresent()) {
            return optionalVendor.get();
        }
        throw new IllegalArgumentException("Unkown vendor id " + vendorId);
    }
}
