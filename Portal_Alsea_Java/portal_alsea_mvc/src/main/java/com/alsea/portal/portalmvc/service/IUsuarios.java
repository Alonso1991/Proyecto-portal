package com.alsea.portal.portalmvc.service;

import com.alsea.portal.portalmvc.model.Tiendas;
import com.alsea.portal.portalmvc.model.UsuarioSesion;

import java.util.List;

public interface IUsuarios {

    int getIdForEmail(String usuario);

    UsuarioSesion getUsuarioForId(int id);
    List<Tiendas> getBranchForIdUser(int idUser);

}
