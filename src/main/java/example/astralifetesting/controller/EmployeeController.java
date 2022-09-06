package example.astralifetesting.controller;

import example.astralifetesting.config.response.BaseResponse;
import example.astralifetesting.model.Employee;
import example.astralifetesting.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("employees")
    public ResponseEntity<BaseResponse<Employee>> employee(){
        return employeeService.getAllEmployee();
    }
    @GetMapping("employees/{id}")
    public ResponseEntity<BaseResponse<Employee>> getEmployeeById(@PathVariable Long id){
        return employeeService.employeeById(id);
    }

    @PostMapping("employees")
    public ResponseEntity<BaseResponse<Employee>> addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    @PutMapping("employees/{id}")
    public ResponseEntity<BaseResponse<Employee>> editEmployee(@RequestBody Employee editEmployee, @PathVariable Long id){
        return employeeService.editEmployee(editEmployee, id);
    }

    @DeleteMapping("employees/{id}")
    public ResponseEntity<BaseResponse<Employee>> deleteEmployee(@PathVariable Long id){
        return employeeService.deleteEmployee(id);
    }
}
