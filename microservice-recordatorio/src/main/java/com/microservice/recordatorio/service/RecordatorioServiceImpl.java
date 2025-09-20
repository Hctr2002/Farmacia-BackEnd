package com.microservice.recordatorio.service;

import com.microservice.recordatorio.client.UsuarioClient;
import com.microservice.recordatorio.dto.RecordatorioDTO;
import com.microservice.recordatorio.model.Recordatorio;
import com.microservice.recordatorio.repository.RecordatorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordatorioServiceImpl implements RecordatorioService {

    @Autowired
    private RecordatorioRepository repository;

    @Autowired
    private UsuarioClient usuarioClient;

    private RecordatorioDTO mapToDTO(Recordatorio r) {
        return RecordatorioDTO.builder()
                .id(r.getId())
                .usuarioId(r.getUsuarioId())
                .medicamento(r.getMedicamento())
                .descripcion(r.getDescripcion())
                .fechaHora(r.getFechaHora())
                .activo(r.getActivo())
                .build();
    }

    private Recordatorio mapToEntity(RecordatorioDTO dto) {
        return Recordatorio.builder()
                .id(dto.getId())
                .usuarioId(dto.getUsuarioId())
                .medicamento(dto.getMedicamento())
                .descripcion(dto.getDescripcion())
                .fechaHora(dto.getFechaHora())
                .activo(dto.getActivo() != null ? dto.getActivo() : true)
                .build();
    }

    @Override
    public RecordatorioDTO crearRecordatorio(RecordatorioDTO recordatorioDTO) {
        try {
            usuarioClient.getUsuarioById(recordatorioDTO.getUsuarioId());
        } catch (feign.FeignException.NotFound e) {
            throw new RuntimeException("El usuario con id " + recordatorioDTO.getUsuarioId() + " no existe");
        }
        Recordatorio recordatorio = repository.save(mapToEntity(recordatorioDTO));
        return mapToDTO(recordatorio);
    }

    @Override
    public RecordatorioDTO obtenerRecordatorio(Long id) {
        return repository.findById(id).map(this::mapToDTO).orElse(null);
    }

    @Override
    public List<RecordatorioDTO> listarRecordatorios() {
        return repository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<RecordatorioDTO> listarPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public RecordatorioDTO actualizarRecordatorio(Long id, RecordatorioDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setMedicamento(dto.getMedicamento());
            existing.setDescripcion(dto.getDescripcion());
            existing.setFechaHora(dto.getFechaHora());
            existing.setActivo(dto.getActivo());
            return mapToDTO(repository.save(existing));
        }).orElse(null);
    }

    @Override
    public void eliminarRecordatorio(Long id) {
        repository.deleteById(id);
    }
}
