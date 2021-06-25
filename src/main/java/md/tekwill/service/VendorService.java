package md.tekwill.service;

import md.tekwill.entity.product.Vendor;

import java.util.List;

public interface VendorService {
    List<Vendor> getAll();

    Vendor getById(long vendorId);
}
