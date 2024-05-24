package com.ezen.springmvc.domain.field.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class FieldDto {
    private int fieldNum;
    private String fieldName;
    private String fieldOldAddress;
    private String fieldNewAddress;
    private String fieldPhone;
    private Date fieldRegdate;
    private String fieldSize;
    private String fieldPhoto;
    private int fieldDayprice;
    private int fieldEndprice;
    private Date fieldPaydate;
    private String fieldOpentime;
    private String fieldClosetime;
    private String fieldthumbnails;
    private String fieldDetailaddress;
    private String fieldX;
    private String fieldY;
}


