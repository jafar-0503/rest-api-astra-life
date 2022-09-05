package example.astralifetesting.controller;

import example.astralifetesting.config.response.BaseResponse;
import example.astralifetesting.model.Title;
import example.astralifetesting.service.SalaryService;
import example.astralifetesting.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/titles")
public class TitleController {

    @Autowired
    private TitleService titleService;

    @GetMapping()
    public ResponseEntity<BaseResponse<Title>> Title(){
        return titleService.getAllTitle();
    }
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<Title>> getTitleById(@PathVariable Long id){
        return titleService.titleById(id);
    }

    @PostMapping()
    public ResponseEntity<BaseResponse<Title>> addTitle(@RequestBody Title Title){
        return titleService.addTitle(Title);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Title>> editTitle(@RequestBody Title editTitle, @PathVariable Long id){
        return titleService.editTitle(editTitle, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Title>> deleteTitle(@PathVariable Long id){
        return titleService.deleteTitle(id);
    }
}
