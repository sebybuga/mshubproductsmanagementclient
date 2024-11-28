package com.hubproductsmanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;

import java.time.LocalDateTime;
@Data
sealed class BaseSealedEntityDTO permits ProductRequestDTO,
                                         StoreRequestDTO,
                                         ProductStoreRequestDTO {

    private Long id;

    private String createdBy;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "ro-RO", timezone = "Europe/Bucharest")
    private LocalDateTime createdAt;

    private String updatedBy;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "ro-RO", timezone = "Europe/Bucharest")
    private LocalDateTime updatedAt;
}
