package payment.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payment.business.EmployeeBusiness;
import payment.common.ExceptionUtil;
import payment.common.SunriseWincashException;
import payment.model.Employee;
import payment.util.UrlConstant;
import java.util.List;
import static payment.common.ExceptionUtil.logException;


@RestController
@Slf4j
public class EmployeeController {


    @Autowired
    private EmployeeBusiness employeeBusiness;


    @GetMapping(UrlConstant.GET_ALL)
    public List<Employee> findAllEmployee() throws SunriseWincashException {
        return employeeBusiness.getAllEmployee();
    }

    @PostMapping(UrlConstant.SAVE_EMPLOYEE)
    public int saveEmployee(@RequestBody Employee employee) throws SunriseWincashException {

        return employeeBusiness.saveEmployee(employee);
    }

    @DeleteMapping(UrlConstant.DELETE_BY_ID)
    public ResponseEntity<?> deleteById(@PathVariable Long id) throws SunriseWincashException {
        try {
            return new ResponseEntity<>(employeeBusiness.deleteById(id), HttpStatus.OK);
        } catch (SunriseWincashException sunriseWincashException) {
            logException(sunriseWincashException, "");
            return new ResponseEntity<>(ExceptionUtil.getClientException(sunriseWincashException), HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            //TODO: replace source with error will give method name Search in Google
            logException(exception, this.getClass().getSimpleName() + "::->" + new Object() {
            }.getClass().getEnclosingMethod().getName());
            return new ResponseEntity<>(ExceptionUtil.getClientException(exception), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(UrlConstant.UPDATE_EMPLOYEE)
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
        try {
            return new ResponseEntity<>(employeeBusiness.update(employee), HttpStatus.OK);
        } catch (SunriseWincashException sunriseWincashException) {
            logException(sunriseWincashException, "");
            return new ResponseEntity<>(ExceptionUtil.getClientException(sunriseWincashException), HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            logException(exception, this.getClass().getSimpleName() + "::->" + new Object() {
            }.getClass().getEnclosingMethod().getName());
            return new ResponseEntity<>(ExceptionUtil.getClientException(exception), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(UrlConstant.GET_BY_ID)
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(employeeBusiness.getById(id), HttpStatus.OK);
        } catch (SunriseWincashException sunriseWincashException) {
            logException(sunriseWincashException, "");
            return new ResponseEntity<>(ExceptionUtil.getClientException(sunriseWincashException), HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            logException(exception, this.getClass().getSimpleName() + "::->" + new Object() {
            }.getClass().getEnclosingMethod().getName());
            return new ResponseEntity<>(ExceptionUtil.getClientException(exception), HttpStatus.BAD_REQUEST);
        }
    }


}
