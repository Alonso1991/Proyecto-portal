package com.alsea.portal.portalmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

    @Controller
    @RequestMapping(value= "/roles")
    public class RolesController {

        @GetMapping(value = "/usuarios")
        public String roles() {

            return "roles";
        }
    }

