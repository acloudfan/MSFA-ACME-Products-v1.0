package com.acme.tests;

import com.acme.products.api.ProductsRestAPIController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan (basePackages = {"com.acme.products.graphql","com.acme.tests"})
public class RunPackageRESTApi {

    public static void main(String[] args){
        SpringApplication.run(ProductsRestAPIController.class, args);
    }
}
