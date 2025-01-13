package com.app.CANCHASBA_API.models.dao;

import com.app.CANCHASBA_API.models.entity.Cancha;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CanchaDao extends CrudRepository<Cancha,Long> {
    //Retorna todas las canchas que cumplan el filtro
    @Query("SELECT c FROM Cancha c " +
            "WHERE (:city IS NULL OR c.city = :city) " +
            "AND (:type IS NULL OR c.type = :type) " +
            "AND (:size IS NULL OR CAST(c.size AS string) LIKE CONCAT('%', :size, '%'))")
    List<Cancha> findFilteredCanchas(
            @Param("city") String city,
            @Param("type") String type,
            @Param("size") String size
    );

    //retorna todas las canchas que contengan la palabra recibida en su nombre
    @Query("SELECT c FROM Cancha c WHERE UPPER(c.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    List<Cancha> findByName(@Param("name") String name);

    //retorna todas las ciudades en las que hay alguna cancha
    @Query("SELECT DISTINCT c.city FROM Cancha c ORDER BY c.city ASC")
    List<String> findDistinctCities();

}
