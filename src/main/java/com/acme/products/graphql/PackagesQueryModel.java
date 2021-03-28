package com.acme.products.graphql;

import com.acme.products.model.Product;
import com.acme.products.model.provider.Provider;

import java.util.ArrayList;

public class PackagesQueryModel  {

    // Holds the package object
    private Product vProduct;

    // Holds the providers
    ArrayList<Provider>  providers;

    public PackagesQueryModel(Product vProduct, ArrayList<Provider>  providers){
        this.vProduct = vProduct;
        this.providers = providers;
    }

    // Exposes the same methods as the Package object
    public String getDescription() {
        return vProduct.getDescription();
    }

    public String getPublicId() {
        return vProduct.getPublicId();
    }

    public String getDestination() {
        return vProduct.getDestination();
    }

    public int getNumberNights() {
        return vProduct.getNumberNights();
    }

    // This one is different from the Package class
    public ArrayList<Provider> getProviders(){
        return providers;
    }
}
