package com.microservice.recordatorio.service;

import com.microservice.recordatorio.dto.RecordatorioDTO;
import java.util.List;

public interface RecordatorioService {
    RecordatorioDTO crearRecordatorio(RecordatorioDTO recordatorioDTO);
    RecordatorioDTO obtenerRecordatorio(Long id);
    List<RecordatorioDTO> listarRecordatorios();
    List<RecordatorioDTO> listarPorUsuario(Long usuarioId);
    RecordatorioDTO actualizarRecordatorio(Long id, RecordatorioDTO recordatorioDTO);
    void eliminarRecordatorio(Long id);
}
