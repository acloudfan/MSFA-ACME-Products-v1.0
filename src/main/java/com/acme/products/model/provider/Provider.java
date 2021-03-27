package com.acme.products.model.provider;

public interface Provider {
    // Enumeration for types of providers
    public enum ProviderType {
        HOTEL,
        CRUISE,
        EXCURSION
    }

    // Get the type
    public ProviderType getType();

    // Name of the provider
    public String getName();

    // Get the description of the provider
    public String getDescription();

    // Customer rating between 1 to 5
    public int getRating();
}
