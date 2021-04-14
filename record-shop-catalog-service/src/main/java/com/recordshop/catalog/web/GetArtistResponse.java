package com.recordshop.catalog.web;

import lombok.Data;

@Data
public class GetArtistResponse {
    private final Long id;
    private final String firstName;
    private final String lastName;
}
