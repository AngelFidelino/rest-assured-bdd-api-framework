package org.aflr.utils;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static io.restassured.RestAssured.given;

public class RestAssuredExtension {
    private static RequestSpecification request;
    private WireMockServer wm;

    public RestAssuredExtension() {
        wm = new WireMockServer(wireMockConfig().dynamicPort().withRootDirectory("src/test/resources/wiremock"));
        wm.start();

        var requestSpec =
                new RequestSpecBuilder().setPort(wm.port()).setBaseUri("http://localhost")
                        .setContentType(ContentType.JSON).build();
        request = given().spec(requestSpec);
    }

    public void stopWireMockServer() {
        wm.stop();
    }

    public static ResponseOptions<Response> getOps(String url) {
        return request.get(url);
    }

    public static ResponseOptions<Response> getOpsWithPathParam(String url, Map<String, String> pathParams) {
        request.pathParams(pathParams);
        return request.get(url);
    }

    public static ResponseOptions<Response> getOpsWithQueryParam(String url, Map<String, String> pathParams) {
        request.queryParams(pathParams);
        return request.get(url);
    }

    public static ResponseOptions<Response> postOpsWithBodyAndPathParams(String url, Map<String, String> body,
            Map<String, String> pathParams) {
        request.pathParams(pathParams);
        request.body(body);
        return request.post(url);
    }

    public static ResponseOptions<Response> postOpsWithBody(String url, Map<String, String> body) {
        request.body(body);
        return request.post(url);
    }

    public static ResponseOptions<Response> deleteOpsWithPathParam(String url, Map<String, String> pathParams) {
        request.pathParams(pathParams);
        return request.delete(url);
    }
}
