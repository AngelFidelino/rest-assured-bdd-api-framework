package org.aflr.enums;

public enum ApiResources {
    ADD_PLACE_API("/maps/api/place/add/json"),
    GET_PLACE_API("/maps/api/place/get/json"),
    DELETE_PLACE_API("/maps/api/place/delete/json");
    private String uri;

    ApiResources(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }
}
