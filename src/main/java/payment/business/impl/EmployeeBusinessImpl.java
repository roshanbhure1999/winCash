package payment.business.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import payment.business.EmployeeBusiness;
import payment.common.ExceptionUtil;
import payment.common.SunriseWincashException;
import payment.model.Employee;
import payment.service.EmployeeService;
import payment.util.ApplicationConstant;
import payment.util.ExceptionConstant;

import java.util.List;

@Service
@Slf4j
public class EmployeeBusinessImpl implements EmployeeBusiness {
    @Autowired
    private EmployeeService employeeService;

    /*@Value("${employee.name}")
    private String name;*/

    @Override
    public List<Employee> getAllEmployee() throws SunriseWincashException {
        List<Employee> allEmployee = null;

        try {

            allEmployee = employeeService.getAllEmployee();
            log.info("The Employee List size :: {}", allEmployee.size());
            // log.info("Properties from server configuration :: {}", name);
        } catch (Exception exception) {
            throw ExceptionUtil.createSunriseWincashException(HttpStatus.INTERNAL_SERVER_ERROR, "500_SW",
                    "Internal Server Error", ApplicationConstant.SOURCE, exception,
                    "EmployeeBusinessImpl.java :: getAllEmployee()", ExceptionConstant.ERROR_LEVEL_IS_CRITICAL);

        }

        return allEmployee;
    }

    @Override
    public int saveEmployee(Employee employee) throws SunriseWincashException {

        try {
            if (StringUtils.isEmpty(employee.getName())) {
                log.error("please enter valid name");
                return 0;
            }
            return employeeService.save(employee);
          } catch (Exception exception) {
            throw ExceptionUtil.createSunriseWincashException(HttpStatus.INTERNAL_SERVER_ERROR, "500_SW",
                    "Internal Server Error", ApplicationConstant.SOURCE, exception,
                    "EmployeeBusinessImpl.java :: saveEmployee()",ExceptionConstant.ERROR_LEVEL_IS_CRITICAL);
        }
    }

    @Override
    public Employee deleteById(Long id) throws SunriseWincashException {
        try {
            return employeeService.deleteById(id);
        } catch (SunriseWincashException sunriseWincashException) {
            throw sunriseWincashException;
        } catch (Exception exception) {
            throw ExceptionUtil.createSunriseWincashException(HttpStatus.INTERNAL_SERVER_ERROR, "500_SW",
                    "Internal Server Error", ApplicationConstant.SOURCE, exception, "EmployeeBusinessImpl.java :: deleteById()",
                    ExceptionConstant.ERROR_LEVEL_IS_MAJOR);
        }

    }

    @Override
    public int update(Employee employee) throws SunriseWincashException {
      try {
          return employeeService.update(employee);
      } catch (SunriseWincashException sunriseWincashException) {
          throw sunriseWincashException;
      } catch (Exception exception) {
          throw ExceptionUtil.createSunriseWincashException(HttpStatus.INTERNAL_SERVER_ERROR, "9999_NA",
                  "Internal Server Error", ApplicationConstant.SOURCE, exception, "EmployeeBusinessImpl.java :: update()",
                  ExceptionConstant.ERROR_LEVEL_IS_MINOR);
      }
    }

    @Override
    public Employee getById(Long id) throws SunriseWincashException {
        try {
            return employeeService.getById(id);
        } catch (SunriseWincashException sunriseWincashException) {
            throw sunriseWincashException;
        } catch (Exception exception) {
            throw ExceptionUtil.createSunriseWincashException(HttpStatus.INTERNAL_SERVER_ERROR, "500_SW",
                    "Internal Server Error", ApplicationConstant.SOURCE, exception, this.getClass().getSimpleName() +"::->"+new Object(){}.getClass().getEnclosingMethod().getName(),ExceptionConstant.ERROR_LEVEL_IS_NORMAL);
        }
    }
}
