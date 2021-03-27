package com.acme.products.graphql;

import com.acme.products.model.PackageRepo;
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


                ArrayList packagesList = packageRepo.findPackage(publicId,destination,numberNightsMin,numberNightsMax);
//                Package[] packages = new Package[1];
//                packages[0] = new Package(100,"BAH", "this is a desc","Bahamas",3);
                return packagesList ;
            }
        };
    }

    public DataFetcher getProviderDataFetcher(){

        // Implements the DataFetcher using Lambda notation
        return DataFetcher -> {
            Provider[] providers = new Provider[1];
            providers[0]=new HotelProvider("Hilton","this is descr",5);
            return providers;
        };
    }
}
