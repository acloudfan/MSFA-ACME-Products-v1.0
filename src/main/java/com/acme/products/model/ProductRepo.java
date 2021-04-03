package com.acme.products.model;

import java.util.ArrayList;

/**
 * Pattern : Repository
 */
public interface ProductRepo {
    // finds the package with given criteria
    public ArrayList<Product> findProducts(String publicId, String destination, int minNumNights, int maxNumNights);
    public boolean addProduct(Product vProduct);
}
