package com.acme.products.graphql;

import com.acme.products.model.Package;
import com.acme.products.model.PackageRepo;
import com.acme.products.model.ProviderRepo;
import com.acme.products.model.provider.HotelProvider;
import com.acme.products.model.provider.Provider;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Provides the logic for providing the data for query response
 */
@Component
public class GraphQLDataFetchers implements DataFetcher {

    @Autowired
    private PackageRepo packageRepo;

    @Autowired
    private ProviderRepo providerRepo;

    @Override
    public Object get(DataFetchingEnvironment environment) throws Exception {
        return null;
    }

    public DataFetcher getPackageByPublicIdDataFetcher(){

        // Not using the Java Lambda for clarity
        return new DataFetcher() {
            @Override
            public Object get(DataFetchingEnvironment environment) throws Exception {
                // Get the query arguments
                String publicId = environment.containsArgument("publicId") ? environment.getArgument("publicId") :"" ;
                String destination = environment.containsArgument("destination") ? environment.getArgument("destination") :"" ;
                int numberNightsMin = environment.containsArgument("numberNightsMin") ? environment.getArgument("numberNightsMin") : -1 ;
                int numberNightsMax = environment.containsArgument("numberNightsMax") ? environment.getArgument("numberNightsMax") : -1 ;

                System.out.println("Query criteria="+publicId+" "+destination+" "+numberNightsMin+" "+numberNightsMax);

                // 1. Find the packages
                ArrayList<Package> packagesList = packageRepo.findPackage(publicId,destination,numberNightsMin,numberNightsMax);

                // 2. For each package
                ArrayList<PackagesQueryModel>  packagesQueryModels = new ArrayList<>();
                for(Package vPackage : packagesList){
                    ArrayList<Integer> providerIds = vPackage.getProviders();

                    // 3. For each provider ID, get the provider object
                    ArrayList<Provider> providers = new ArrayList<>();
                    for(Integer providerId : providerIds){
                        Provider provider = providerRepo.findProvider(providerId);
                        providers.add(provider);
                    }
                    packagesQueryModels.add(new PackagesQueryModel(vPackage, providers));
                }

                return packagesQueryModels ;
            }
        };
    }

    public DataFetcher getProviderDataFetcher(){

        // Implements the DataFetcher using Lambda notation
        return dataFetchingEnvironment -> {
            int id = dataFetchingEnvironment.containsArgument("id") ? dataFetchingEnvironment.getArgument("id") : -1 ;
            System.out.println("Query criteria="+id);
            return providerRepo.findProvider(id);
        };
    }
}
