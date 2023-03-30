package com.alsea.portal.portalmvc.repository;

import com.alsea.portal.portalmvc.entity.TiendasEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ITiendasRepository extends JpaRepository<TiendasEntity, String> {

    TiendasEntity findTiendasEntityById (int idTienda);

    TiendasEntity findTiendasEntityByNombre (String nombre);


}
