package com.acme.products.api;

import com.acme.products.model.Package;
import com.acme.products.model.PackageRepo;
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

@SpringBootApplication(scanBasePackages = {"com.acme.products.model"})
@RestController
@ComponentScan("com.acme.tests")
public class PackageRestAPIController {

    @Autowired
    private PackageRepo packageRepo;

//    @Bean
//    public PackageRepo getPackageRepo(){
//        return new PackageFakeRepo();
//    }



    /**
     * Receives a JSON with
     */
    @GetMapping(path="/api/packages", produces= MediaType.APPLICATION_JSON_VALUE)
    public String getPackages(@RequestParam(name="publicId", required=false, defaultValue = "") String publicId,
                              @RequestParam(name="name", required=false, defaultValue = "") String dest,
                              @RequestParam(name="minNight", required=false, defaultValue = "0") int minNight,
                              @RequestParam(name="maxNight", required=false, defaultValue = "1000") int maxNight){

        ArrayList<Package> packages = packageRepo.findPackage(publicId,dest,minNight,maxNight);
        String json ="[";
        boolean firstElement = true;
        for(Package p : packages){
            if(!firstElement) {
                json += ",";
            }
            firstElement = false;
            json += p.toJson() ;
        }
        json += "]";
        return  json;
    }

}
