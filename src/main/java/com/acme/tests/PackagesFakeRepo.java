package com.acme.tests;

import com.acme.products.model.Package;
import com.acme.products.model.PackageRepo;
import com.acme.products.model.provider.AirlineProvider;
import com.acme.products.model.provider.CruiseProvider;
import com.acme.products.model.provider.HotelProvider;
import com.acme.products.model.provider.Provider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PackagesFakeRepo implements PackageRepo {

    // Collection of packages
    private ArrayList<Package> collection = new ArrayList<>();

    // Collection of providers
    private ArrayList<Provider> providers = new ArrayList<>();


    @Override
    public ArrayList<Package> findPackage(String publicId, String destination, int minNumNights, int maxNumNights) {
        ArrayList<Package> result = new ArrayList<>();

        for(Package p : collection){

            // If matches the publicId then add it and break out of loop as there can be only one
            if(p.publicId.equalsIgnoreCase(publicId)){
                result.add(p);
                break;
            }

            // Continue if destination criteria doesn't match
            if(!destination.equals("") && !p.destination.equalsIgnoreCase(destination)) continue;

            // Continue if num nights criteria doesn't match
            if((minNumNights > 0 && p.numberNights < minNumNights ) ||  (maxNumNights > 0 && p.numberNights > maxNumNights  )){
                continue;
            }

            result.add(p);
        }

        return result;
    }

    @Override
    public boolean addPackage(Package vPackage) {

        collection.add(vPackage);

        return true;
    }

    @Bean
    @Primary
    public PackageRepo getPackageRepo(){
        PackageRepo packageRepo = new PackagesFakeRepo();
        System.out.println("Initializing Fake Repo");
        ArrayList<Provider> providers;


        providers = new ArrayList<>();
        providers.add(new CruiseProvider("Carnival","Queen of Ocean",4));
        packageRepo.addPackage(new Package(1001,"BAH03CRUISE","This is a 3 night package","Bahamas",3, providers));

        providers = new ArrayList<>();
        providers.add(new CruiseProvider("Norwegian","Atlantic princess",5));
        packageRepo.addPackage(new Package(1002,"BAH07CRUISE","This is a 7 night package","Bahamas",7, providers));

        providers = new ArrayList<>();
        providers.add(new HotelProvider("Hilton","Hilton next to Orlando Disney",5));
        providers.add(new AirlineProvider("United","Round trip flight to Orlando, FL",5));
        packageRepo.addPackage(new Package(1003,"DISNEY05RESORT","This is a 5 night get away to Disney in FL","Florida",5, providers));

        providers = new ArrayList<>();
        providers.add(new HotelProvider("Crown Plaza","In the heart of Nasau",5));
        providers.add(new AirlineProvider("American","Round trip flight to Jamaica International from anywhere in US.",5));
        packageRepo.addPackage(new Package(1004,"JAMAICA07RESORT","This is a 7 night resort package","Jamaica",7, providers));

        return packageRepo;
    }
}
