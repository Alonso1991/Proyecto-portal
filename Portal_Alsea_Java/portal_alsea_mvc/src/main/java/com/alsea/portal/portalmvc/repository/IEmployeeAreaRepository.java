package com.alsea.portal.portalmvc.repository;

import com.alsea.portal.portalmvc.entity.AreasEntity;
import com.alsea.portal.portalmvc.entity.EmployeesAreasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeAreaRepository extends JpaRepository<EmployeesAreasEntity, String> {
}
