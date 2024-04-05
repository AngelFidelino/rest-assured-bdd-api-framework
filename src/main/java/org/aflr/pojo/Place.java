package org.aflr.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Place {

    private int accuracy;
    private String name;
    private String phone_number;
    private String address;
    private String website;
    private String language;
    private Location location;
    private List<String> types;

}
