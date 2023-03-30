package com.alsea.portal.portalmvc.repository;

import com.alsea.portal.portalmvc.entity.EmployeesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IEmployeesRepository extends JpaRepository<EmployeesEntity, String> {


  //  List<EmployeesEntity> findEmployeesByIdAreaAndActivoOrderByEscalamiento(int idArea, int idActivo);

    @Modifying
    @Transactional
    @Query("select e from employees e, employee_area ea where e.id = ea.idEmployee and e.activo=1 and ea.idArea=?1 order by e.escalamiento")
    List<EmployeesEntity> getEmployees(int idArea);


}
