package example.astralifetesting.service;

import example.astralifetesting.config.error.ResourceNotFoundException;
import example.astralifetesting.config.response.BaseResponse;
import example.astralifetesting.model.Department;
import example.astralifetesting.model.Salaries;
import example.astralifetesting.repository.SalaryRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class SalaryService {
    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private ModelMapper modelMapper;

    //Get-All-Salaries
    public ResponseEntity<BaseResponse<Salaries>> getAllSalaries(){
        List<Salaries> salariess = salaryRepository.findAll();
        if (salariess.isEmpty()){
            return ResponseEntity.ok(new BaseResponse<>(false, null, "No Data"));
        }
        Type targetType = new TypeToken<List<Salaries>>(){}.getType();
        List<Salaries> data= modelMapper.map(salariess, targetType);
        BaseResponse response = new BaseResponse(true, data, "Salaries retrieved successfully");

        return ResponseEntity.ok(response);
    }

    //Get-Salaries-By-Id
    public ResponseEntity<BaseResponse<Salaries>> salariesById(Long id) throws ResourceNotFoundException {
        Salaries salaries = salaryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Salaries ID " + id + " is not exist"));
        Salaries data = modelMapper.map(salaries, Salaries.class);
        EntityModel<Salaries> resource = EntityModel.of(salaries);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllSalaries())
                .withRel("getAllSalaries"));
        BaseResponse response = new BaseResponse(true, data, "Salaries retrieved successfully");

        return ResponseEntity.ok(response);
    }

    //Post-Salaries
    public ResponseEntity<BaseResponse<Salaries>> addSalaries(Salaries emp){
        Salaries salaries = modelMapper.map(emp, Salaries.class);
        Salaries saved = salaryRepository.save(salaries);
        Salaries data = modelMapper.map(saved, Salaries.class);
        BaseResponse response = new BaseResponse(true, data, "Salaries added successfully");

        return ResponseEntity.ok(response);
    }

    //Put-Salaries
    public ResponseEntity<BaseResponse<Salaries>> editSalaries(Salaries salaries, Long id){
        Salaries salary = salaryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Salaries ID " + id + " is not exist"));
        salary.setSalary(salaries.getSalary());
        salaryRepository.save(salary);

        Salaries data = modelMapper.map(salaries, Salaries.class);
        BaseResponse response = new BaseResponse(true, data, "Salaries edited successfully");

        return ResponseEntity.ok(response);
    }

    //Remove-Salaries
    public ResponseEntity<BaseResponse<Salaries>> deleteSalaries(Long id){
        Salaries salaries = salaryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Salaries ID " + id + " is not exist"));
        salaryRepository.deleteById(id);
        Salaries data = modelMapper.map(salaries, Salaries.class);
        BaseResponse response = new BaseResponse(true, data, "Salaries deleted successfully");

        return ResponseEntity.ok(response);
    }
}
