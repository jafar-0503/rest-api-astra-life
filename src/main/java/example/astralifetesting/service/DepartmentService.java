package example.astralifetesting.service;

import example.astralifetesting.config.error.ResourceNotFoundException;
import example.astralifetesting.config.response.BaseResponse;
import example.astralifetesting.model.Department;
import example.astralifetesting.model.Employee;
import example.astralifetesting.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    //Get-All-Dept
    public ResponseEntity<BaseResponse<Department>> getAllDept(){
        List<Department> departments = departmentRepository.findAll();
        if (departments.isEmpty()){
            return ResponseEntity.ok(new BaseResponse<>(false, null, "No Data"));
        }
        Type targetType = new TypeToken<List<Department>>(){}.getType();
        List<Department> data= modelMapper.map(departments, targetType);
        BaseResponse response = new BaseResponse(true, data, "Department retrieved successfully");

        return ResponseEntity.ok(response);
    }

    //Get-Dept-By-Id
    public ResponseEntity<BaseResponse<Department>> departmentById(Long id){
        Department department = departmentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Department ID " + id + " is not exist"));
        Department data = modelMapper.map(department, Department.class);
        BaseResponse response = new BaseResponse(true, data, "Department retrieved successfully");

        return ResponseEntity.ok(response);
    }

    //Post-Dept
    public ResponseEntity<BaseResponse<Department>> addDepartment(Department emp){
        Department department = modelMapper.map(emp, Department.class);
        Department saved = departmentRepository.save(department);
        Department data = modelMapper.map(saved, Department.class);
        BaseResponse response = new BaseResponse(true, data, "Department added successfully");

        return ResponseEntity.ok(response);
    }

    //Put-Dept
    public ResponseEntity<BaseResponse<Department>> editDepartment(Department emp, Long id){
        Department department = departmentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Department ID " + id + " is not exist"));
        department.setDept_name(emp.getDept_name());
        departmentRepository.save(department);

        Department data = modelMapper.map(department, Department.class);
        BaseResponse response = new BaseResponse(true, data, "Department edited successfully");

        return ResponseEntity.ok(response);
    }

    //Remove-Dept
    public ResponseEntity<BaseResponse<Department>> deleteDepartment(Long id){
        Department department = departmentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Department ID " + id + " is not exist"));
        departmentRepository.deleteById(id);
        Department data = modelMapper.map(department, Department.class);
        BaseResponse response = new BaseResponse(true, data, "Department deleted successfully");

        return ResponseEntity.ok(response);
    }
}
