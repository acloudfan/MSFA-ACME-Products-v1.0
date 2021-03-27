package com.acme.products.model;

import java.util.ArrayList;

/**
 * Pattern : Repository
 */
public interface PackageRepo {
    // finds the package with given criteria
    public ArrayList<Package> findPackage(String publicId, String destination, int minNumNights, int maxNumNights);
    public boolean addPackage(Package vPackage);
}
