package com.acme.products.model;

import org.json.JSONObject;

import java.util.Date;

/**
 * Pattern : Entity
 */
public class Package {
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

    public Package(int id, String publicId, String description, String destination, int numberNights) {
        this.id = id;
        this.publicId = publicId;
        this.description = description;
        this.destination = destination;
        this.numberNights = numberNights;
    }

    public String toJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("publicId", publicId);
        jsonObject.put("description", description);
        jsonObject.put("numberNights", numberNights);
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
}
