package com.acme.products.graphql;

import com.acme.products.model.Package;
import com.acme.products.model.provider.Provider;

import java.util.ArrayList;

public class PackagesQueryModel  {

    // Holds the package object
    private  Package vPackage;

    // Holds the providers
    ArrayList<Provider>  providers;

    public PackagesQueryModel(Package vPackage, ArrayList<Provider>  providers){
        this.vPackage = vPackage;
        this.providers = providers;
    }

    // Exposes the same methods as the Package object
    public String getDescription() {
        return vPackage.getDescription();
    }

    public String getPublicId() {
        return vPackage.getPublicId();
    }

    public String getDestination() {
        return vPackage.getDestination();
    }

    public int getNumberNights() {
        return vPackage.getNumberNights();
    }

    // This one is different from the Package class
    public ArrayList<Provider> getProviders(){
        return providers;
    }
}
