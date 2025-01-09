package com.app.CANCHASBA_API.models.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class CanchaDto implements Serializable {
    private Integer id;
    private String name;
    private String address;
    private String city;
    private String zone;
    private String phone;
    private String quantity;
    private String type;
    private String size;
    private String rating;
    private boolean collaborator;
}