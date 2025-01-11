package com.facul.cefet_store.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CupomDto {

    private Long id;

    private String name;

    private String code;

    private Long discount;

    private Date expirationDate;
}
