package com.acme.products.model.provider;

public class HotelProvider implements Provider {

    private String name;
    private String description;
    private int rating;

    public HotelProvider(String name, String description, int rating) {
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    @Override
    public ProviderType getType() {
        return ProviderType.HOTEL;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public int getRating() {
        return rating;
    }
}
