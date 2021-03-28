package com.acme.products.model;

import com.acme.products.model.provider.Provider;

/**
 * Repository for managing the providers
 */
public interface ProviderRepo {
    // Returns the provider with specified ID
    public Provider findProvider(int id);

    // Adds the provider
    public boolean addProvider(Provider provider);
}
