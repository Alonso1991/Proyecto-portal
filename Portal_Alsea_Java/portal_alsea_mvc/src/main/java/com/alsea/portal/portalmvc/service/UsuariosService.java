package com.alsea.portal.portalmvc.service;

import com.alsea.portal.portalmvc.entity.TiendasEntity;
import com.alsea.portal.portalmvc.entity.TiendasUsuarioEntity;
import com.alsea.portal.portalmvc.entity.UsuarioEntity;
import com.alsea.portal.portalmvc.model.Tiendas;
import com.alsea.portal.portalmvc.model.UsuarioSesion;
import com.alsea.portal.portalmvc.repository.ITiendasRepository;
import com.alsea.portal.portalmvc.repository.IUsuarioRepository;
import com.alsea.portal.portalmvc.repository.IUsuarioTiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuariosService implements IUsuarios {

    @Autowired
    IUsuarioRepository usuarioRepository;
    @Autowired
    IUsuarioTiendaRepository usuarioTiendaRepository;

    @Autowired
    ITiendasRepository tiendaRepository;

   public int getIdForEmail(String email){
        UsuarioEntity usuario = usuarioRepository.findUsuarioEntityByEmail(email);
        int idUser = usuario.getId();
        return idUser;
    }

    public UsuarioSesion getUsuarioForId(int idUser){
        UsuarioEntity usuario = usuarioRepository.findUsuarioEntityById(idUser);
        UsuarioSesion usuarioSesion = new UsuarioSesion();
        usuarioSesion.setEmail(usuario.getEmail());
        usuarioSesion.setNombre(usuario.getNombre());
        usuarioSesion.setId(usuario.getId());

        return usuarioSesion;

    }



    @Override
    public List<Tiendas> getBranchForIdUser(int idUser) {
        List <TiendasUsuarioEntity> tiendasUser = usuarioTiendaRepository.findTiendasUsuarioEntityByIdUsuario(idUser);

        Tiendas tiendaObj;
        TiendasEntity tiendaEntity;
        List <Tiendas> tiendas =new ArrayList<>();
        for (int i=0;tiendasUser.size()>i; i++){
            tiendaEntity = tiendaRepository.findTiendasEntityById(tiendasUser.get(i).getIdTienda());
            tiendaObj = new Tiendas(tiendaEntity.getId(),tiendaEntity.getTiendas(),tiendaEntity.getIp());
            tiendas.add(tiendaObj);
        }
        return tiendas;
    }

    public List<UsuarioEntity> lista(){
       return usuarioRepository.findAll();
    }

    public Optional<UsuarioEntity> getById(int id){

       return usuarioRepository.findById(id);
    }
 public void save (UsuarioEntity usuario){
       usuarioRepository.save(usuario);
 }
 public boolean existsById(int id){
       return usuarioRepository.existsById(id);
 }

 public boolean existsByNombre(String nombre){
       return usuarioRepository.existsByNombre(nombre);
 }

}
