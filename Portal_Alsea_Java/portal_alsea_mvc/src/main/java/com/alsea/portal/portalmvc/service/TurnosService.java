package com.alsea.portal.portalmvc.service;

import com.alsea.portal.portalmvc.entity.AreasEntity;
import com.alsea.portal.portalmvc.entity.EmployeesAreasEntity;
import com.alsea.portal.portalmvc.entity.EmployeesEntity;
import com.alsea.portal.portalmvc.entity.TurnosEntity;
import com.alsea.portal.portalmvc.repository.IAreasRepository;
import com.alsea.portal.portalmvc.repository.IEmployeeAreaRepository;
import com.alsea.portal.portalmvc.repository.IEmployeesRepository;
import com.alsea.portal.portalmvc.repository.ITurnosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnosService implements ITurnos{

    final
    IEmployeesRepository iEmployeesRepository;
    final
    IAreasRepository iAreasRepository;
    public List<AreasEntity> getAllAreas;
    final
    ITurnosRepository iTurnosRepository;

    final
    IEmployeeAreaRepository iEmployeeAreaRepository;


      public TurnosService(IAreasRepository iAreasRepository, IEmployeesRepository iEmployeesRepository, ITurnosRepository iTurnosRepository, IEmployeeAreaRepository iEmployeeAreaRepository) {

        this.iAreasRepository = iAreasRepository;
        this.iEmployeesRepository = iEmployeesRepository;
        this.iTurnosRepository = iTurnosRepository;
          this.iEmployeeAreaRepository = iEmployeeAreaRepository;
      }



    @Override
    public List<EmployeesEntity> getEmployees(int idArea) {


        List<EmployeesEntity> employeesEntityList = iEmployeesRepository.getEmployees(idArea);


        return employeesEntityList;
    }

    @Override
    public List<AreasEntity> getAreas() {

        List<AreasEntity> areasEntityList = iAreasRepository.getAllByActivo(1);

        return areasEntityList;
    }
    @Override
    public void guardarTurno(TurnosEntity turnosEntity) {

          iTurnosRepository.save(turnosEntity);

    }
    @Override
    public void guardarEmployee(EmployeesEntity employeesEntity) {

        iEmployeesRepository.save(employeesEntity);

    }

    @Override
    public void guardarEmployeeArea(EmployeesAreasEntity employeesAreasEntity) {
          iEmployeeAreaRepository.save(employeesAreasEntity);

    }


}
