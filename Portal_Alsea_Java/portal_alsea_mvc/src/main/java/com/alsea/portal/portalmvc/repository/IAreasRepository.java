package com.alsea.portal.portalmvc.repository;

import com.alsea.portal.portalmvc.entity.AreasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAreasRepository extends JpaRepository<AreasEntity, String> {


   List<AreasEntity> getAllByActivo(int activo);
}
