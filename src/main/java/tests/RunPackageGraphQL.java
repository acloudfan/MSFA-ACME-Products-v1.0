package tests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan (basePackages = {"com.acme.products.graphql","com.acme.tests"})
public class RunPackageGraphQL {


    public static void main(String[] args){
        SpringApplication.run(RunPackageGraphQL.class, args);
//        SpringApplication.run(PackageRestAPIController.class, args);
    }
}
