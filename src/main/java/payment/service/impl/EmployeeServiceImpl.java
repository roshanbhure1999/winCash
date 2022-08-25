package payment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import payment.common.ExceptionUtil;
import payment.common.SunriseWincashException;
import payment.model.Employee;
import payment.service.EmployeeService;
import payment.util.ApplicationConstant;
import payment.util.ExceptionConstant;
import payment.util.QueryConstant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author PB00875339
 * @version 1.0
 */
@Repository
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Employee> getAllEmployee() {
        return jdbcTemplate.query(QueryConstant.GET_ALL_EMPLOYEE,
                (rs, rowNum) -> Employee.builder().
                        id(rs.getLong(ApplicationConstant.ID))
                        .name(rs.getString(ApplicationConstant.NAME))
                        .department(rs.getString(ApplicationConstant.DEPARTMENT))
                        .email(rs.getString(ApplicationConstant.EMAIL))
                        .address(rs.getString(ApplicationConstant.ADDRESS))
                        .contact(rs.getString(ApplicationConstant.CONTACT))
                        .salary(rs.getLong(ApplicationConstant.SALARY))
                        .build());

    }

    @Override
    public int save(Employee employee) {
        return jdbcTemplate.update(QueryConstant.SAVE_EMPLOYEE_DETAILS,
                employee.getId(),
                employee.getName(),
                employee.getDepartment(),
                employee.getEmail(),
                employee.getAddress(),
                employee.getContact(),
                employee.getSalary());

    }

    /**
     * @param employee
     * @return Employee
     * @throws SunriseWincashException
     */
    @Override
    public int update(Employee employee) throws SunriseWincashException {
        try {
            return jdbcTemplate.update(QueryConstant.UPDATE,
                    employee.getId(),
                    employee.getName(),
                    employee.getDepartment(),
                    employee.getEmail(),
                    employee.getAddress(),
                    employee.getContact(),
                    employee.getSalary());
        } catch (DataAccessException dataAccessException) {
            throw ExceptionUtil.createSunriseWincashException(HttpStatus.BAD_REQUEST, "2291_SW", "Please enter valid id to Update Employee",
                    ApplicationConstant.SOURCE, dataAccessException,
                    this.getClass().getSimpleName()+" :: "+new Object(){}.getClass().getEnclosingMethod().getName(), ExceptionConstant.ERROR_LEVEL_IS_CRITICAL);
        } catch (Exception exception) {
            throw ExceptionUtil.createSunriseWincashException(HttpStatus.INTERNAL_SERVER_ERROR, "500_SW",
                    "Internal Server Error", ApplicationConstant.SOURCE, exception,
                    "EmployeeServiceImpl.java :: update()", ExceptionConstant.ERROR_LEVEL_IS_CRITICAL);

        }
    }

    /**
     * @param id
     * @return Employee
     * @throws SunriseWincashException
     */
    @Override
    public Employee deleteById(Long id) throws SunriseWincashException {
        try {
            Employee employee = jdbcTemplate.queryForObject(QueryConstant.DELETE_BY_ID, new Object[]{id}, new RowMapper<Employee>() {
                @Override
                public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return Employee.builder().id(rs.getLong(ApplicationConstant.ID))
                            .name(rs.getString(ApplicationConstant.NAME))
                            .department(rs.getString(ApplicationConstant.DEPARTMENT))
                            .email(rs.getString(ApplicationConstant.EMAIL))
                            .address(rs.getString(ApplicationConstant.ADDRESS))
                            .contact(rs.getString(ApplicationConstant.CONTACT))
                            .salary(rs.getLong(ApplicationConstant.SALARY))
                            .build();
                }
            });
            return employee;

        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw ExceptionUtil.createSunriseWincashException(HttpStatus.BAD_REQUEST, "2291_SW", "Please enter valid id for deletion",
                    ApplicationConstant.SOURCE, dataIntegrityViolationException,
                    "EmployeeServiceImpl.java :: deleteById()", ExceptionConstant.ERROR_LEVEL_IS_MINOR);
        } catch (Exception exception) {
            throw ExceptionUtil.createSunriseWincashException(HttpStatus.INTERNAL_SERVER_ERROR, "500_SW",
                    "Internal Server Error", ApplicationConstant.SOURCE,
                    exception, "EmployeeServiceImpl.java :: deleteById()", ExceptionConstant.ERROR_LEVEL_IS_MINOR);
        }
    }

    /**
     * @param id
     * @return Employee
     * @throws SunriseWincashException
     */
    @Override
    public Employee getById(Long id) throws SunriseWincashException {
        try {
            Employee employee = jdbcTemplate.queryForObject(QueryConstant.GET_BY_ID, new Object[]{id}, new RowMapper<Employee>() {
                @Override
                public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return Employee.builder().id(rs.getLong(ApplicationConstant.ID))
                            .name(rs.getString(ApplicationConstant.NAME))
                            .department(rs.getString(ApplicationConstant.DEPARTMENT))
                            .email(rs.getString(ApplicationConstant.EMAIL))
                            .address(rs.getString(ApplicationConstant.ADDRESS))
                            .contact(rs.getString(ApplicationConstant.CONTACT))
                            .salary(rs.getLong(ApplicationConstant.SALARY))
                            .build();
                }
            });
            return employee;

        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            throw ExceptionUtil.createSunriseWincashException(HttpStatus.BAD_REQUEST, "2221_SW",
                    "No such employee is found", ApplicationConstant.SOURCE,
                    emptyResultDataAccessException,this.getClass().getSimpleName() +"::->"+new Object(){}.getClass().getEnclosingMethod().getName(), ExceptionConstant.ERROR_LEVEL_IS_MINOR);

        } catch (Exception exception) {
            throw ExceptionUtil.createSunriseWincashException(HttpStatus.INTERNAL_SERVER_ERROR, "500_SW",
                    "Internal Server Error", ApplicationConstant.SOURCE,
                    exception, "EmployeeServiceImpl.java :: getById()", ExceptionConstant.ERROR_LEVEL_IS_MAJOR);
        }

    }


}
