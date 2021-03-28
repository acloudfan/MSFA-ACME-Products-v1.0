package com.acme.products.api;

import com.acme.products.model.Product;
import com.acme.products.model.ProductRepo;
import com.acme.products.model.ProviderRepo;
import com.acme.products.model.provider.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * This is the REST API controller for products API
 */

@SpringBootApplication(scanBasePackages = {"com.acme.products.model", "com.acme.products.model.provider"})
@RestController
@ComponentScan("com.acme.tests")
public class ProductsRestAPIController {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProviderRepo providerRepo;

//    @Bean
//    public PackageRepo getPackageRepo(){
//        return new PackageFakeRepo();
//    }



    /**
     * Receives a JSON with info on vacation packages
     */
    @GetMapping(path="/products", produces= MediaType.APPLICATION_JSON_VALUE)
    public String getPackages(@RequestParam(name="publicId", required=false, defaultValue = "") String publicId,
                              @RequestParam(name="dest", required=false, defaultValue = "") String dest,
                              @RequestParam(name="minNight", required=false, defaultValue = "0") int minNight,
                              @RequestParam(name="maxNight", required=false, defaultValue = "1000") int maxNight){

        ArrayList<Product> products = productRepo.findPackage(publicId,dest,minNight,maxNight);
        String json ="[";
        boolean firstElement = true;
        for(Product p : products){
            if(!firstElement) {
                json += ",";
            }
            firstElement = false;
            json += p.toJson() ;
        }
        json += "]";
        return  json;
    }

    @GetMapping(path="/providers", produces= MediaType.APPLICATION_JSON_VALUE)
    public String getProvider(@RequestParam(name="id", required=true, defaultValue = "-1") int id){
        Provider provider = providerRepo.findProvider(id);
        if (provider == null){
            return "{'error': 'No provider found'}";
        } else {
            return provider.toJSON();
        }
    }

}
