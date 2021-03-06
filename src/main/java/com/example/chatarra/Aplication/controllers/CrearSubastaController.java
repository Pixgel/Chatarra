package com.example.chatarra.Aplication.controllers;

import com.example.chatarra.Aplication.conveters.SubastaConverter;
import com.example.chatarra.Aplication.dto.CrearSubastaDto;
import com.example.chatarra.Domain.Services.AceptarPropuestaService;
import com.example.chatarra.Domain.Services.CrearSubastaService;
import com.example.chatarra.Domain.Entitys.Subasta;
import com.example.chatarra.Aplication.utils.WrapperResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/subasta")
public class CrearSubastaController {

    private final CrearSubastaService crearSubastaService;
    private final SubastaConverter subastaConverter;

    private final AceptarPropuestaService aceptarPropuestaService;

    public CrearSubastaController(CrearSubastaService crearSubastaService, SubastaConverter subastaConverter, AceptarPropuestaService aceptarPropuestaService) {
        this.crearSubastaService = crearSubastaService;
        this.subastaConverter = subastaConverter;
        this.aceptarPropuestaService = aceptarPropuestaService;
    }

    @PostMapping("/crear")
    public ResponseEntity<WrapperResponse<CrearSubastaDto>> CrearSubasta(@RequestBody CrearSubastaDto crearSubastaDto) {
        Subasta subasta = subastaConverter.fromDTO(crearSubastaDto);
        Subasta SubastaNew = crearSubastaService.crearSubasta(subasta);
        CrearSubastaDto response = subastaConverter.fromEntity(SubastaNew);
        return new WrapperResponse<CrearSubastaDto>(true, "success", response).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/eliminar/{id}")
    public ResponseEntity<WrapperResponse<Subasta>> Eliminar(@PathVariable("id") Integer id) {
        Subasta SubastaDeleted = crearSubastaService.eliminarSubasta(id);
        return new WrapperResponse<Subasta>(true, "success", SubastaDeleted).createResponse(HttpStatus.OK);
    }

    @GetMapping("/mis/{ids}")
    public ResponseEntity<WrapperResponse<List<CrearSubastaDto>>> Listar(@PathVariable("ids") Integer id) {
        List<Subasta> subastasList = crearSubastaService.misSubastas(id);
        List<CrearSubastaDto> subastaDtoList = subastaConverter.fromEntity(subastasList);
        return new WrapperResponse<List<CrearSubastaDto>>(true, "success", subastaDtoList)
                .createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WrapperResponse<CrearSubastaDto>> ObtenerPorId(@PathVariable("id") Integer id) {
        Subasta subasta = crearSubastaService.buscarPorId(id);
        CrearSubastaDto subastaDto = subastaConverter.fromEntity(subasta);
        return new WrapperResponse<>(true, "success", subastaDto)
                .createResponse(HttpStatus.OK);
    }
    @GetMapping("estados/{estado}")
    public ResponseEntity<WrapperResponse<List<CrearSubastaDto>>> FiltrarSubastasPorEstado(@PathVariable("estado") String estado) {
        List<Subasta> subastasList = crearSubastaService.FiltrarSubastasPorEstado(estado);
        List<CrearSubastaDto> SubastaListDto = subastaConverter.fromEntity(subastasList);
        return new WrapperResponse<List<CrearSubastaDto>>(true, "success", SubastaListDto)
                .createResponse(HttpStatus.OK);
    }

    @GetMapping("estados/{ides}/{estado}")
    public ResponseEntity<WrapperResponse<List<CrearSubastaDto>>> ListarEstados(@PathVariable("ides") Integer id, @PathVariable("estado") String estado) {
        List<Subasta> subastasList = crearSubastaService.SubsatasEstados(id, estado);
        List<CrearSubastaDto> SubastaListDto = subastaConverter.fromEntity(subastasList);
        return new WrapperResponse<List<CrearSubastaDto>>(true, "success", SubastaListDto)
                .createResponse(HttpStatus.OK);
    }

    @PutMapping("eleccion/{ids}/{idp}")
    public ResponseEntity<WrapperResponse<CrearSubastaDto>> AsignarPropuesta(@PathVariable("ids") Integer idSubasta, @PathVariable("idp") Integer idPropuesta) {
        Subasta propuestaAsignada = aceptarPropuestaService.aceptarPropuesta(idSubasta, idPropuesta);
        CrearSubastaDto response = subastaConverter.fromEntity(propuestaAsignada);
        return new WrapperResponse<CrearSubastaDto>(true, "success", response)
                .createResponse(HttpStatus.OK);
    }

    @PutMapping("eleccionAnulada/{id}")
    public ResponseEntity<WrapperResponse<CrearSubastaDto>> AsignacionAnulada(@PathVariable("id") Integer idSubasta) {
        Subasta propuestaAnulada= aceptarPropuestaService.cancelarAceptacion(idSubasta);
        CrearSubastaDto response = subastaConverter.fromEntity(propuestaAnulada);
        return new WrapperResponse<CrearSubastaDto>(true, "success", response)
                .createResponse(HttpStatus.OK);
    }


    @PutMapping("/editar")
    public ResponseEntity<WrapperResponse<CrearSubastaDto>> EditarSubasta(@RequestBody CrearSubastaDto crearSubastaDto) {
        Subasta subasta = subastaConverter.fromDTO(crearSubastaDto);
        Subasta SubastaNew = crearSubastaService.editarSubasta(subasta);
        CrearSubastaDto response = subastaConverter.fromEntity(SubastaNew);
        return new WrapperResponse<CrearSubastaDto>(true, "success", response).createResponse(HttpStatus.CREATED);

    }


}
