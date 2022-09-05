package example.astralifetesting.controller;

import example.astralifetesting.config.response.BaseResponse;
import example.astralifetesting.model.Salaries;
import example.astralifetesting.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/salaries")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @GetMapping()
    public ResponseEntity<BaseResponse<Salaries>> Salaries(){
        return salaryService.getAllSalaries();
    }
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<Salaries>> getSalariesById(@PathVariable Long id){
        return salaryService.salariesById(id);
    }

    @PostMapping()
    public ResponseEntity<BaseResponse<Salaries>> addSalaries(@RequestBody Salaries Salaries){
        return salaryService.addSalaries(Salaries);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Salaries>> editSalaries(@RequestBody Salaries editSalaries, @PathVariable Long id){
        return salaryService.editSalaries(editSalaries, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Salaries>> deleteSalaries(@PathVariable Long id){
        return salaryService.deleteSalaries(id);
    }
}
