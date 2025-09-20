package com.microservice.recordatorio.controller;

import com.microservice.recordatorio.dto.RecordatorioDTO;
import com.microservice.recordatorio.service.RecordatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recordatorios")
public class RecordatorioController {

    @Autowired
    private RecordatorioService service;

    @PostMapping
    public RecordatorioDTO crear(@RequestBody RecordatorioDTO dto) {
        return service.crearRecordatorio(dto);
    }

    @GetMapping("/{id}")
    public RecordatorioDTO obtener(@PathVariable Long id) {
        return service.obtenerRecordatorio(id);
    }

    @GetMapping
    public List<RecordatorioDTO> listar() {
        return service.listarRecordatorios();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<RecordatorioDTO> listarPorUsuario(@PathVariable Long usuarioId) {
        return service.listarPorUsuario(usuarioId);
    }

    @PutMapping("/{id}")
    public RecordatorioDTO actualizar(@PathVariable Long id, @RequestBody RecordatorioDTO dto) {
        return service.actualizarRecordatorio(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminarRecordatorio(id);
    }
}
