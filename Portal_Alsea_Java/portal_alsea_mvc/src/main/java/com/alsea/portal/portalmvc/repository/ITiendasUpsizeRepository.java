package com.alsea.portal.portalmvc.repository;

import com.alsea.portal.portalmvc.entity.CajerosEntity;

import com.alsea.portal.portalmvc.entity.TiendasEntity;
import com.alsea.portal.portalmvc.entity.TiendasUpsizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITiendasUpsizeRepository  extends JpaRepository<TiendasUpsizeEntity, String> {


    TiendasUpsizeEntity findTiendasUpsizeEntityById(int parseInt);

    TiendasUpsizeEntity findTiendasEntityByNombre (String nombre);


}
