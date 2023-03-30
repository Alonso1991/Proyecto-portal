package com.alsea.portal.portalmvc.repository;

import com.alsea.portal.portalmvc.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, String> {

    UsuarioEntity findUsuarioEntityByNombre(String nombre);
    boolean existsByNombre(String nombre);
    UsuarioEntity findUsuarioEntityById (int id);
    UsuarioEntity findUsuarioEntityByEmail (String email);

    Optional<UsuarioEntity> findById(int id);

    boolean existsById(int id);
}
