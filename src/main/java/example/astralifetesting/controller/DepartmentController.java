package example.astralifetesting.controller;

import example.astralifetesting.config.response.BaseResponse;
import example.astralifetesting.model.Department;
import example.astralifetesting.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("departments")
    public ResponseEntity<BaseResponse<Department>> department(){
        return departmentService.getAllDept();
    }
    @GetMapping("departments/{id}")
    public ResponseEntity<BaseResponse<Department>> getDepartmentById(@PathVariable Long id){
        return departmentService.departmentById(id);
    }

    @PostMapping("departments")
    public ResponseEntity<BaseResponse<Department>> addDepartment(@RequestBody Department department){
        return departmentService.addDepartment(department);
    }

    @PutMapping("departments/{id}")
    public ResponseEntity<BaseResponse<Department>> editDepartment(@RequestBody Department editDepartment, @PathVariable Long id){
        return departmentService.editDepartment(editDepartment, id);
    }

    @DeleteMapping("departments/{id}")
    public ResponseEntity<BaseResponse<Department>> deleteDepartment(@PathVariable Long id){
        return departmentService.deleteDepartment(id);
    }
}
