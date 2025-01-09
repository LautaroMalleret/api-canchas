package com.app.CANCHASBA_API.service;

import com.app.CANCHASBA_API.models.dto.CanchaDto;
import com.app.CANCHASBA_API.models.entity.Cancha;

import java.util.List;

public interface InterfaceCanchaService {

        Cancha save (CanchaDto canchaDto);

        Cancha findById(Integer id);

        void delete(Cancha cancha);

        boolean existById(Integer id);

        List<Cancha> listAll();

}
