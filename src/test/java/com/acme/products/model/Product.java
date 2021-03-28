package com.acme.products.model;

import com.acme.products.model.provider.Provider;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Pattern : Entity
 */
public class Product {
    public final int id;
    public final String publicId;
    public final String description;

    // Destination
    public final String destination;

    // Number of nights
    public final int    numberNights;

    // Discounts - between 0 & 100
    private int discountPercent = 0;
    private Date discountExpiry;

    // Date on which package becomes unavailable
    private Date packageExpiry;

    // Collection of providers
    private ArrayList<Provider> providers = new ArrayList<>();

    public Product(int id, String publicId, String description, String destination, int numberNights, ArrayList<Provider> providers ) {
        this.id = id;
        this.publicId = publicId;
        this.description = description;
        this.destination = destination;
        this.numberNights = numberNights;
        this.providers = providers;
    }


    public String toJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("publicId", publicId);
        jsonObject.put("description", description);
        jsonObject.put("numberNights", numberNights);

        JSONArray jsonArray = new JSONArray();
        for(Provider provider : providers) {
            jsonArray.put(provider.toJSON());
        }

        jsonObject.put("providers", jsonArray);

        return jsonObject.toString(4);
    }

    public String getDescription() {
        return description;
    }

    public String getPublicId() {
        return publicId;
    }

    public String getDestination() {
        return destination;
    }

    public int getNumberNights() {
        return numberNights;
    }

    public ArrayList<Provider> getProviders(){
        return providers;
    }
}
