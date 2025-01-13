package com.app.CANCHASBA_API.models.payload;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class MessageResponse {
    //MANEJO DE RESPUESTA
    private String message;
    private Object object;
}
