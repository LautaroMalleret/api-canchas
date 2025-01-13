package com.app.CANCHASBA_API.service.impl;

import com.app.CANCHASBA_API.models.dto.CanchaDto;
import com.app.CANCHASBA_API.models.entity.Cancha;
import com.app.CANCHASBA_API.models.dao.CanchaDao;
import com.app.CANCHASBA_API.service.InterfaceCanchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class implCancha implements InterfaceCanchaService {

    @Autowired
    private CanchaDao canchaDao;

    @Transactional
    @Override
    public Cancha save(CanchaDto canchaDto) {
        Cancha cancha = Cancha.builder().
                id(canchaDto.getId()).
                name(canchaDto.getName()).
                address(canchaDto.getAddress()).
                city(canchaDto.getCity()).
                zone(canchaDto.getZone()).
                phone(canchaDto.getPhone()).
                quantity(canchaDto.getQuantity()).
                type(canchaDto.getType()).
                size(canchaDto.getSize())
                .build();
        return canchaDao.save(cancha);
    }

    @Transactional(readOnly = true)
    @Override
    public Cancha findById(Long id) {
        return canchaDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Cancha cancha) {
        canchaDao.delete(cancha);
    }

    @Override
    public boolean existById(Long id) {
        return canchaDao.existsById(id);
    }

    @Override
    public List<Cancha> listAll() {
        return (List) canchaDao.findAll();
    }

    @Override
    public List<Cancha> getFilteredCanchas(String city, String type, String size) {
        return canchaDao.findFilteredCanchas(
                city != null && !city.isEmpty() ? city : null,
                type != null && !type.isEmpty() ? type : null,
                size != null && !size.isEmpty() ? size : null
        );
    }

    @Override
    public List<Cancha> findByName(String name) {
        if (name == null || name.isEmpty()) {
            return listAll(); // Retorna todas las canchas si no se especifica un nombre
        }
        return canchaDao.findByName(name);
    }

    @Override
    public List<String> findDistinctCities() { //RETORNA TODAS LAS CIUDADES DE LA BASE DE DATOS
        return canchaDao.findDistinctCities();
    }


}
