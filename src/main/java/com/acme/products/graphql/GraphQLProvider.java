package com.acme.products.graphql;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

/**
 * Created using the guidance in this article:
 * https://www.graphql-java.com/tutorials/getting-started-with-spring-boot/#graphql-in-3-minutes
 *
 */

@Component
public class GraphQLProvider {

    @Autowired
    com.acme.products.graphql.GraphQLDataFetchers graphQLDataFetchers;

    private GraphQL graphQL;

    @Bean
    public GraphQL graphQL(){
        return graphQL;
    }

    @PostConstruct
    public void init() throws IOException{
        URL url = Resources.getResource("root.graphqls");
//        System.out.println(url.toString());
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        // This sets up the server schema
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    /**
     * Create the GraphQL Schema object
     */
    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    /**
     * Registers the Data Fetchers for the Packages and the Providers
     */
    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("packages", graphQLDataFetchers.getPackageByPublicIdDataFetcher()))
                .type(newTypeWiring("Query")
                        .dataFetcher("providers", graphQLDataFetchers.getProviderDataFetcher()))
                .build();
    }
}
