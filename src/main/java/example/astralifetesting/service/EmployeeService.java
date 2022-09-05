package example.astralifetesting.service;

import example.astralifetesting.config.error.ResourceNotFoundException;
import example.astralifetesting.config.response.BaseResponse;
import example.astralifetesting.model.Employee;
import example.astralifetesting.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    //Get-All-Employee
    public ResponseEntity<BaseResponse<Employee>> getAllEmployee(){
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()){
            return ResponseEntity.ok(new BaseResponse<>(false, null, "No Data"));
        }
        Type targetType = new TypeToken<List<Employee>>(){}.getType();
        List<Employee> data= modelMapper.map(employees, targetType);
        BaseResponse response = new BaseResponse(true, data, "Employee retrieved successfully");

        return ResponseEntity.ok(response);
    }

    //Get-Employee-By-Id
    public ResponseEntity<BaseResponse<Employee>> employeeById(Long id) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee ID " + id + " is not exist"));
        Employee data = modelMapper.map(employee, Employee.class);
        BaseResponse response = new BaseResponse(true, data, "Employee retrieved successfully");

        return ResponseEntity.ok(response);
    }

    //Post-Employee
    public ResponseEntity<BaseResponse<Employee>> addEmployee(Employee emp){
        Employee employee = modelMapper.map(emp, Employee.class);
        Employee saved = employeeRepository.save(employee);
        Employee data = modelMapper.map(saved, Employee.class);
        BaseResponse response = new BaseResponse(true, data, "Employee added successfully");

        return ResponseEntity.ok(response);
    }

    //Put-Employee
    public ResponseEntity<BaseResponse<Employee>> editEmployee(Employee emp, Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee ID " + id + " is not exist"));
        employee.setBirth_date(emp.getBirth_date());
        employee.setFirst_name(emp.getFirst_name());
        employee.setLast_name(emp.getLast_name());
        employee.setGender(emp.getGender());
        employee.setHire_date(emp.getHire_date());
        employeeRepository.save(employee);

        Employee data = modelMapper.map(employee, Employee.class);
        BaseResponse response = new BaseResponse(true, data, "Employee edited successfully");

        return ResponseEntity.ok(response);
    }

    //Remove-Employee
    public ResponseEntity<BaseResponse<Employee>> deleteEmployee(Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee ID " + id + " is not exist"));
        employeeRepository.deleteById(id);
        Employee data = modelMapper.map(employee, Employee.class);
        BaseResponse response = new BaseResponse(true, data, "Employee deleted successfully");

        return ResponseEntity.ok(response);
    }
}
