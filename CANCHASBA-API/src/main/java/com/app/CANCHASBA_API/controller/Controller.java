package com.app.CANCHASBA_API.controller;

import com.app.CANCHASBA_API.models.dto.CanchaDto;
import com.app.CANCHASBA_API.models.entity.Cancha;
import com.app.CANCHASBA_API.models.payload.MessageResponse;
import com.app.CANCHASBA_API.service.InterfaceCanchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class Controller {

    @Autowired
    InterfaceCanchaService canchaService;

    @GetMapping("canchas")
    public ResponseEntity<?> showAll(){
        List<Cancha> listCanchas = canchaService.listAll();
        if(listCanchas == null) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message("there are not records")
                            .object(null)
                            .build()
                    , HttpStatus.OK);

        }else{
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message("")
                            .object(listCanchas)
                            .build()
                    , HttpStatus.OK);
        }
    }



    @PostMapping("/cancha")
    public ResponseEntity<?> create(@RequestBody CanchaDto canchaDto){
        Cancha canchaSave = null;
        try{
            canchaSave = canchaService.save(canchaDto);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("SAVE")
                    .object(CanchaDto.builder().
                            id(canchaSave.getId()).
                            name(canchaSave.getName()).
                            address(canchaSave.getAddress()).
                            city(canchaSave.getCity()).
                            zone(canchaSave.getZone()).
                            phone(canchaSave.getPhone()).
                            quantity(canchaSave.getQuantity()).
                            type(canchaSave.getType()).
                            size(canchaSave.getSize())
                            .build()).
                    build(),
                    HttpStatus.CREATED);
        }
        catch (DataAccessException exc){
            return new ResponseEntity<>(
                    MessageResponse
                            .builder()
                            .message(exc.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("/cancha/{id}")
    public ResponseEntity<?> update(@RequestBody CanchaDto canchaDto, @PathVariable Long id){
        Cancha canchaUpdate=null;
        try{
            if(canchaService.existById(id)){
                canchaDto.setId(id);
                canchaUpdate = canchaService.save(canchaDto);
                return new ResponseEntity<>(MessageResponse.builder()
                        .message("UPDATED")
                        .object(CanchaDto.builder().
                                id(canchaUpdate.getId()).
                                name(canchaUpdate.getName()).
                                address(canchaUpdate.getAddress()).
                                city(canchaUpdate.getCity()).
                                zone(canchaUpdate.getZone()).
                                phone(canchaUpdate.getPhone()).
                                quantity(canchaUpdate.getQuantity()).
                                type(canchaUpdate.getType()).
                                size(canchaUpdate.getSize())
                                .build()).
                        build(),
                        HttpStatus.CREATED);
            }else {
                return new ResponseEntity<>(
                        MessageResponse
                                .builder()
                                .message("Record not found in DB")
                                .object(null)
                                .build()
                        , HttpStatus.NOT_FOUND);
            }

        }catch (DataAccessException exc){
            return new ResponseEntity<>(
                    MessageResponse
                            .builder()
                            .message(exc.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }


    }

    @DeleteMapping("canchadelete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            Cancha canchaDelete = canchaService.findById(id);
            canchaService.delete(canchaDelete);
            return new ResponseEntity<>(canchaDelete, HttpStatus.NO_CONTENT);
        }catch (DataAccessException exc){
            return new ResponseEntity<>(
                    MessageResponse
                            .builder()
                            .message(exc.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("cancha/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id){
        Cancha cancha = canchaService.findById(id);
        if(cancha == null){
            return new ResponseEntity<>(
                    MessageResponse
                            .builder()
                            .message("Record no exist")
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<>(MessageResponse.builder()
                .message("")
                .object(CanchaDto.builder().
                        id(cancha.getId()).
                        name(cancha.getName()).
                        address(cancha.getAddress()).
                        city(cancha.getCity()).
                        zone(cancha.getZone()).
                        phone(cancha.getPhone()).
                        quantity(cancha.getQuantity()).
                        type(cancha.getType()).
                        size(cancha.getSize())
                        .build()).
                build(),
                HttpStatus.OK);
        }


    @GetMapping(value = "/")
    public String hola(){
        return "hola soy lautaro y esta es mi api! esta es una api para consultar canchas de futbol de la provincia de buenos aires. Para saber como funciona agrega esta linea en la url de arriba : /swagger-ui/index.html#/";
    }


    @GetMapping("/filter")
    public ResponseEntity<?> filterCanchas(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String size) {
        List<Cancha> filteredCanchas = canchaService.getFilteredCanchas(city.toUpperCase(), type.toUpperCase(), size);
        if (filteredCanchas.isEmpty()) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message("No records found for the provided filters")
                            .object(null)
                            .build(),
                    HttpStatus.NOT_FOUND
            );
        }
        // Convertimos la lista de Cancha a una lista de DTOs si es necesario
        List<CanchaDto> canchaDtos = filteredCanchas.stream().map(cancha ->
                CanchaDto.builder()
                        .id(cancha.getId())
                        .name(cancha.getName())
                        .address(cancha.getAddress())
                        .city(cancha.getCity())
                        .zone(cancha.getZone())
                        .phone(cancha.getPhone())
                        .quantity(cancha.getQuantity())
                        .type(cancha.getType())
                        .size(cancha.getSize())
                        .build()
        ).toList();

        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("")
                        .object(canchaDtos)
                        .build(),
                HttpStatus.OK
        );
    }


    @GetMapping("/filterByName")
    public ResponseEntity<?> filterByName(@RequestParam(required = false) String name) {
        List<Cancha> filteredCanchas = canchaService.findByName(name);

        if (filteredCanchas.isEmpty()) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message("No records found for the provided name")
                            .object(null)
                            .build(),
                    HttpStatus.NOT_FOUND
            );
        }

        List<CanchaDto> canchaDtos = filteredCanchas.stream().map(cancha ->
                CanchaDto.builder()
                        .id(cancha.getId())
                        .name(cancha.getName())
                        .address(cancha.getAddress())
                        .city(cancha.getCity())
                        .zone(cancha.getZone())
                        .phone(cancha.getPhone())
                        .quantity(cancha.getQuantity())
                        .type(cancha.getType())
                        .size(cancha.getSize())
                        .build()
        ).toList();

        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("")
                        .object(canchaDtos)
                        .build(),
                HttpStatus.OK
        );
    }


    @GetMapping("/cities")
    public ResponseEntity<?> getDistinctCities() {
        List<String> cities = canchaService.findDistinctCities();

        if (cities.isEmpty()) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message("No cities found")
                            .object(null)
                            .build(),
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("")
                        .object(cities)
                        .build(),
                HttpStatus.OK
        );
    }

}
