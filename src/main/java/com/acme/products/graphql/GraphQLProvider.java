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

    // Used for interacting with the server layer
    private GraphQL graphQL;

    @Bean
    public GraphQL graphQL(){
        return graphQL;
    }

    /**
     *
     * @throws IOException
     */
    @PostConstruct
    public void init() throws IOException{

        // 1. Read the SDL file
        URL url = Resources.getResource("root.graphqls");

        // 2. Setup the schema in runtime
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);

        // 3. Setup the GraphQL instance
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
     * Registers the Data Fetchers for the Products and the Providers
     * For each Query defined in the SDL there is a data fetcher object
     */
    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("products", graphQLDataFetchers.getPackageByPublicIdDataFetcher()))
                .type(newTypeWiring("Query")
                        .dataFetcher("providers", graphQLDataFetchers.getProviderDataFetcher()))
                .build();
    }
}
