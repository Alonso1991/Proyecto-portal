package com.alsea.portal.portalmvc.controller;

import com.alsea.portal.portalmvc.dto.PropinasResponseDTO;
import com.alsea.portal.portalmvc.model.Tiendas;
import com.alsea.portal.portalmvc.model.UsuarioSesion;
import com.alsea.portal.portalmvc.service.PropinasService;
import com.alsea.portal.portalmvc.service.UsuariosService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import java.util.List;

@Controller
@RequestMapping("/propinas")
public class PropinasController {

    final PropinasService propinas;
    final UsuariosService usuarios;

    public PropinasController(PropinasService propinas, UsuariosService usuarios) {
        this.propinas = propinas;
        this.usuarios = usuarios;
    }

    UsuarioSesion user = null;
    List<PropinasResponseDTO> prop = null;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String Propinas(@RequestParam(value = "id") int id, Model model) {
        user = new UsuarioSesion();
        user = usuarios.getUsuarioForId(id);
        user.setActivo(true);
        List<Tiendas> tiendasForUser = usuarios.getBranchForIdUser(id);
        model.addAttribute("listTiendas", tiendasForUser);
        model.addAttribute("user", user);
        model.addAttribute("prop", prop);
        prop = null;
        return "propinas";
    }

    @GetMapping(value = "/buscar")
    public String getPropinas(@RequestParam(value = "fecIni") @DateTimeFormat(pattern = "yyyy-MM-dd") String fecIni,
                              @RequestParam(value = "fecFin") @DateTimeFormat(pattern = "yyyy-MM-dd") String fecFin,
                              @RequestParam(value = "tienda") String tienda,
                              Model model) {
        prop = new ArrayList<>();
        List<Tiendas> tiendasForUser = usuarios.getBranchForIdUser(user.getId());
        model.addAttribute("listTiendas", tiendasForUser);

        //Busca propinas solo si estan todos los parametros
        if (tienda != null && fecFin != null && fecIni != null) {
            prop = propinas.getPropinas(fecIni, fecFin, tienda);
        }
        model.addAttribute("prop", prop);

        return "redirect:/propinas/index?id=" + user.getId();
    }
}
