package com.acme.products.model;

import java.util.ArrayList;

/**
 * Pattern : Repository
 */
public interface ProductRepo {
    // finds the package with given criteria
    public ArrayList<Product> findPackage(String publicId, String destination, int minNumNights, int maxNumNights);
    public boolean addPackage(Product vProduct);
}
