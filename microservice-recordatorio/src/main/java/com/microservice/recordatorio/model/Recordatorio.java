package com.microservice.recordatorio.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recordatorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;

    private String medicamento;

    private String descripcion; // Algún detalle extra

    private LocalDateTime fechaHora; // Cuándo debe tomar el medicamento

    private Boolean activo; // Si el recordatorio está habilitado
}
