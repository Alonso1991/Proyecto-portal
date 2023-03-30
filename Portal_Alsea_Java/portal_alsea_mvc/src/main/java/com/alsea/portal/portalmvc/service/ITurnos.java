package com.alsea.portal.portalmvc.service;

import com.alsea.portal.portalmvc.entity.AreasEntity;
import com.alsea.portal.portalmvc.entity.EmployeesAreasEntity;
import com.alsea.portal.portalmvc.entity.EmployeesEntity;
import com.alsea.portal.portalmvc.entity.TurnosEntity;

import java.util.List;

public interface ITurnos {


 List<EmployeesEntity> getEmployees(int idArea);
    List<AreasEntity> getAreas();
  //  List<EmployeesEntity> getEmployees(int idArea);

    void guardarTurno (TurnosEntity turnosEntity);

    void guardarEmployee (EmployeesEntity employee);

    void guardarEmployeeArea(EmployeesAreasEntity employeesAreasEntity);
}



