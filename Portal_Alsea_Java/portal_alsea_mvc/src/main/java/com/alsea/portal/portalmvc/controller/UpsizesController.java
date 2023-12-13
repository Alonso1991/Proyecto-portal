package com.alsea.portal.portalmvc.controller;

/*
import com.alsea.portal.portalmvc.dto.UpsizeResponseDTO;
import com.alsea.portal.portalmvc.model.Tiendas;
import com.alsea.portal.portalmvc.model.UsuarioSesion;
import com.alsea.portal.portalmvc.service.IUsuarios;
import com.alsea.portal.portalmvc.service.UpsizeService;
import com.alsea.portal.portalmvc.service.UsuariosService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import static com.sun.tools.doclint.Entity.prop;

@Controller
@RequestMapping("/upsizes")
public class UpsizesController {




    final UpsizeService upsizes;
    final UsuariosService usuarios;

    public UpsizesController(UpsizeService upsizes, UsuariosService usuarios) {
        this.upsizes = upsizes;
        this.usuarios = usuarios;
    }
    UsuarioSesion user = null;
    List<UpsizeResponseDTO> size = null;
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String Upsizes(@RequestParam(value = "id") int id, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("prop", prop);
        size = null;
        return "upzises";
    }
    @GetMapping(value = "/buscar")
    public String getUpsize(@RequestParam(value = "fecIni") @DateTimeFormat(pattern = "yyyy-MM-dd") String fecIni,
                              @RequestParam(value = "fecFin") @DateTimeFormat(pattern = "yyyy-MM-dd") String fecFin,
                              @RequestParam(value = "tienda") String tienda,
                              Model model) {
        size = new ArrayList<>();
        List<Tiendas> upsizesForTiendas = usuarios.getBranchForIdUser(user.getId());
        model.addAttribute("listTiendas", upsizesForTiendas);

        //Busca upsizes solo si estan todos los parametros
        if (tienda != null && fecIni != null && fecFin !=null) {
            size = upsizes.getUpsize(fecIni, fecFin, tienda);
        }
        model.addAttribute("size", size);

        return "redirect:/upsizes/index";
    }

}
 */