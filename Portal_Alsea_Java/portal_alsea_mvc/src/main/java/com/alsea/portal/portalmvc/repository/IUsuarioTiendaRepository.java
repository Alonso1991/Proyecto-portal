package com.alsea.portal.portalmvc.repository;

import com.alsea.portal.portalmvc.entity.TiendasEntity;
import com.alsea.portal.portalmvc.entity.TiendasUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUsuarioTiendaRepository extends JpaRepository<TiendasUsuarioEntity, String>{

    List<TiendasUsuarioEntity> findTiendasUsuarioEntityByIdUsuario (int idUsuario);
}
