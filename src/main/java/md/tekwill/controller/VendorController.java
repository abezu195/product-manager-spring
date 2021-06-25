package md.tekwill.controller;

import md.tekwill.entity.product.Vendor;
import md.tekwill.service.VendorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping("/vendor")
    public List<Vendor> getAllVendors() {
        return vendorService.getAll();
    }
}
