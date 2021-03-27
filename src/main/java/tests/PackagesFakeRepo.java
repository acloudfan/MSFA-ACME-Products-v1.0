package tests;

import com.acme.products.model.Package;
import com.acme.products.model.PackageRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PackagesFakeRepo implements PackageRepo {
    private ArrayList<Package> collection = new ArrayList<>();

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
        packageRepo.addPackage(new Package(1001,"BAH03CRUISE","This is a 3 night package","Bahamas",3));
        packageRepo.addPackage(new Package(1002,"BAH07CRUISE","This is a 7 night package","Bahamas",7));
        packageRepo.addPackage(new Package(1003,"DISNEY05RESORT","This is a 5 night get away to Disney in FL","Florida",5));
        packageRepo.addPackage(new Package(1004,"JAMAICA07RESORT","This is a 7 night resort package","Jamaica",7));
        return packageRepo;
    }
}
