package com.alsea.portal.portalmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collection;
import java.util.Objects;


@Controller
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping(value="/index")
    public String getSession( Model model) {
       System.out.println("HOla");

        return "redirect:/propinas/index?id=1";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value="error", required=false) String error,
                        @RequestParam(value="logout", required = false) String logout,
                        Model model, Principal principal, RedirectAttributes flash) {

        if(principal != null) {
            flash.addFlashAttribute("info", "Ud ya inicio sesión");
            return "redirect:/home";
        }

        if(error != null) {
            model.addAttribute("error", "Error de autentificacion: Nombre de usuario o contraseña incorrecta, por favor intente nuevamente!");
        }

        if(logout != null) {
            model.addAttribute("success", "Ha cerrado sesión con éxito!");
        }

        return "login";
    }

    @RequestMapping(value = {"/home", "/"}, method = RequestMethod.GET)
    public String index(Model model,
                         Authentication authentication,
                         HttpServletRequest request) {

        if(authentication != null) {
            logger.info("Hola usuario autenticado, tu username es: ".concat(authentication.getName()));
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth != null) {
            logger.info("Utilizando forma estática SecurityContextHolder.getContext().getAuthentication(): Usuario autenticado: ".concat(auth.getName()));
        }

        if(hasRole("ROLE_ADMIN")) {
            logger.info("Hola ".concat(auth.getName()).concat(" tienes acceso!"));
        } else {
            logger.info("Hola ".concat(auth.getName()).concat(" NO tienes acceso!"));
        }

        SecurityContextHolderAwareRequestWrapper securityContext = new SecurityContextHolderAwareRequestWrapper(request, "");

        if(securityContext.isUserInRole("ROLE_ADMIN")) {
            logger.info("Forma usando SecurityContextHolderAwareRequestWrapper: Hola ".concat(auth.getName()).concat(" tienes acceso!"));
        } else {
            logger.info("Forma usando SecurityContextHolderAwareRequestWrapper: Hola ".concat(auth.getName()).concat(" NO tienes acceso!"));
        }

        if(request.isUserInRole("ROLE_ADMIN")) {
            logger.info("Forma usando HttpServletRequest: Hola ".concat(auth.getName()).concat(" tienes acceso!"));
        } else {
            logger.info("Forma usando HttpServletRequest: Hola ".concat(auth.getName()).concat(" NO tienes acceso!"));
        }


        return "index";
    }

    private boolean hasRole(String role) {

        SecurityContext context = SecurityContextHolder.getContext();

        if(context == null) {
            return false;
        }

        Authentication auth = context.getAuthentication();

        if(auth == null) {
            return false;
        }

        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

        return authorities.contains(new SimpleGrantedAuthority(role));

		/*
		 * for(GrantedAuthority authority: authorities) {
			if(role.equals(authority.getAuthority())) {
				logger.info("Hola usuario ".concat(auth.getName()).concat(" tu role es: ".concat(authority.getAuthority())));
				return true;
			}
		}

		return false;

*/
    }

}
