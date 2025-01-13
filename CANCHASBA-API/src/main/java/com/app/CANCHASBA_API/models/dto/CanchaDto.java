package com.app.CANCHASBA_API.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;

@Data
@ToString
@Builder
public class CanchaDto implements Serializable {
    @Schema(readOnly = true)
    private Long id;
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
