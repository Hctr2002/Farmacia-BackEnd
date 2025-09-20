package com.microservice.recordatorio.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordatorioDTO {
    private Long id;
    private Long usuarioId;
    private String medicamento;
    private String descripcion;
    private LocalDateTime fechaHora;
    private Boolean activo;
}
