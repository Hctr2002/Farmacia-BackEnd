package com.microservice.recordatorio.repository;

import com.microservice.recordatorio.model.Recordatorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordatorioRepository extends JpaRepository<Recordatorio, Long> {
    List<Recordatorio> findByUsuarioId(Long usuarioId);
}
