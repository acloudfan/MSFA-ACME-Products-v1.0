package com.acme.tests;

import com.acme.products.model.ProviderRepo;
import com.acme.products.model.provider.AirlineProvider;
import com.acme.products.model.provider.CruiseProvider;
import com.acme.products.model.provider.HotelProvider;
import com.acme.products.model.provider.Provider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProviderFakeRepo implements ProviderRepo {

    // Collection of providers
    private ArrayList<Provider> collection = new ArrayList<>();

    @Bean
    @Primary
    public ProviderRepo getProviderRepo(){
        System.out.println("Initializing Fake Provider Repo");

        ProviderRepo providerRepo = new ProviderFakeRepo();

        providerRepo.addProvider(new CruiseProvider(100,"Carnival","Queen of Ocean",4));

        providerRepo.addProvider(new CruiseProvider(200, "Norwegian","Atlantic princess",5));

        providerRepo.addProvider(new HotelProvider(300,"Hilton","Hilton next to Orlando Disney",5));
        providerRepo.addProvider(new AirlineProvider(400,"United","Round trip flight to Orlando, FL",4));

        providerRepo.addProvider(new HotelProvider(500,"Crown Plaza","In the heart of Nasau",4));
        providerRepo.addProvider(new AirlineProvider(600,"American","Round trip flight to Jamaica International from anywhere in US.",3));

        return providerRepo;
    }

    @Override
    public Provider findProvider(int id) {
        System.out.println(id+ "   "+ collection.size());
        for(Provider provider : collection){
            if(provider.getId() == id){
                return provider;
            }
        }
        return null;
    }

    @Override
    public boolean addProvider(Provider provider) {
//        System.out.println(provider.getName()+ "   "+ collection.size());

        collection.add(provider);

        return true;
    }
}
