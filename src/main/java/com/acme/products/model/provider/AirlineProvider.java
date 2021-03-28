package com.acme.products.model.provider;

import org.json.JSONObject;

public class AirlineProvider implements Provider {

    private int id;
    private String name;
    private String description;
    private int rating;

    public AirlineProvider(int id, String name, String description, int rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    @Override
    public int getId() {
        return id;
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
        jsonObject.put("id", id);
        jsonObject.put("type", "Airline");
        jsonObject.put("name", name);
        jsonObject.put("description", description);
        jsonObject.put("rating", rating);

        return jsonObject.toString(4);
    }
}
