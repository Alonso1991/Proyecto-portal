package com.alsea.portal.portalmvc.controller;

import com.alsea.portal.portalmvc.entity.AreasEntity;
import com.alsea.portal.portalmvc.entity.EmployeesAreasEntity;
import com.alsea.portal.portalmvc.entity.EmployeesEntity;
import com.alsea.portal.portalmvc.entity.TurnosEntity;
import com.alsea.portal.portalmvc.service.TurnosService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/turnos")
public class TurnosController {

    final
    TurnosService turnosService;

    public TurnosController(TurnosService turnosService) {
        this.turnosService = turnosService;
    }

    int idArea = 0;
    List<AreasEntity> areasEntityList = null;
    List<EmployeesEntity> employeesEntityList = null;

    Boolean transaccion =null;


    @GetMapping(value = "/index")
    public String getIndex(Model model) {
        areasEntityList = new ArrayList<>();

        AreasEntity areasEntity = new AreasEntity(0, " Seleccion filtro ", 1);
        areasEntityList.add(areasEntity);
        areasEntityList.addAll(turnosService.getAreas());

        model.addAttribute("listAreas", areasEntityList);
        model.addAttribute("idArea", idArea);
        model.addAttribute("listaEmp", employeesEntityList);
        employeesEntityList = null;
        idArea = 0;

        return "turnos";
    }


    @GetMapping(value = "/get/employees")
    public String getEmployees(@RequestParam(value = "areasSubGerencias") int idArea,
                               Model model) {

        employeesEntityList = turnosService.getEmployees(idArea);
        this.idArea = idArea;


        return "redirect:/turnos/index";
    }

    @GetMapping(value = "/crear/employee")
    public String crearEmployee(Model model) {

        areasEntityList = new ArrayList<>();
        AreasEntity areasEntity = new AreasEntity(0, " Seleccione área ", 1);
        areasEntityList.add(areasEntity);
        areasEntityList.addAll(turnosService.getAreas());
        model.addAttribute("listAreas", areasEntityList);
        model.addAttribute("idArea", idArea);
        model.addAttribute("transaccion", transaccion);
        transaccion = null;
        idArea = 0;

        return "crear";
    }

    @PostMapping(value = "/trabajador/save")
    public String guardarTrabajador(@RequestParam(value = "nombre") String nombre,
                                    @RequestParam(value = "apellido") String apellido,
                                    @RequestParam(value = "email") String email,
                                    @RequestParam(value = "telefono") String telefono,
                                    @RequestParam(value = "cargo") String cargo,
                                    @RequestParam(value = "escalamiento") int escalamiento,
                                    @RequestParam(value = "area") int idArea, Model model) {

        try {
            EmployeesEntity employeesEntity = new EmployeesEntity(nombre, apellido, email, cargo, escalamiento, telefono, 0,1);
            turnosService.guardarEmployee(employeesEntity);
            EmployeesAreasEntity employeesAreasEntity = new EmployeesAreasEntity( idArea, employeesEntity.getId());
            turnosService.guardarEmployeeArea(employeesAreasEntity);
            transaccion = true;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error en el registro del trabajador: "+ e.getMessage());
            transaccion= false;

        }
        return "redirect:/turnos/crear/employee";
    }
    @GetMapping(value = "/grabar/turnos")
    public String guardarTurno(){

        return "asignacionTurnos";
    }

    @GetMapping(value = "/historicos")
    public String historicos(){

        return "historicoTurnos";
    }
}
