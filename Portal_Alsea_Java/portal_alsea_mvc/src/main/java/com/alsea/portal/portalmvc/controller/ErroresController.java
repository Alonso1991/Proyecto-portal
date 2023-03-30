package com.alsea.portal.portalmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/error")
public class ErroresController {

    @GetMapping(value = "/401")
    public String error401(){

        return "/errores/error401";
    }
    @GetMapping(value = "/403")
    public String error403(){

        return "/errores/error403";
    }
    @GetMapping(value = "/404")
    public String error404(){

        return "/errores/error404";
    }

    @GetMapping(value = "/prueba")
    public String prueba(){

        return "/prueba";
    }
}
