package payment.service;

import payment.common.SunriseWincashException;
import payment.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployee();


    int save(Employee employee);

    int update(Employee employee) throws SunriseWincashException;

    Employee deleteById(Long id) throws SunriseWincashException;

    Employee getById(Long id) throws SunriseWincashException;

}
