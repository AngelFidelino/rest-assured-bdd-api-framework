package org.aflr.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class RestAssuredSpecsUtil {
    private static RequestSpecification requestSpec;
    public static RequestSpecification getRequestSpec() throws FileNotFoundException {
        if(requestSpec!=null)
            return requestSpec;
        PrintStream loggingFile = new PrintStream("logging.txt");
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(PropertyUtils.props.getProperty("URI"))
                .addFilter(RequestLoggingFilter.logRequestTo(loggingFile))
                .addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON)
                .build();
        return requestSpec;
    }
    public static ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();
    }
}
