package com.acme.products.model.provider;

import org.json.JSONObject;

public class AirlineProvider implements Provider {

    private String name;
    private String description;
    private int rating;

    public AirlineProvider(String name, String description, int rating) {
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    @Override
    public ProviderType getType() {
        return ProviderType.AIRLINE;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public String toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "Airline");
        jsonObject.put("name", name);
        jsonObject.put("description", description);
        jsonObject.put("rating", rating);

        return jsonObject.toString(4);
    }
}
