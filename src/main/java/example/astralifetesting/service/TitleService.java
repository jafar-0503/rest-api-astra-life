package example.astralifetesting.service;

import example.astralifetesting.config.error.ResourceNotFoundException;
import example.astralifetesting.config.response.BaseResponse;
import example.astralifetesting.model.Salaries;
import example.astralifetesting.model.Title;
import example.astralifetesting.repository.SalaryRepository;
import example.astralifetesting.repository.TitleRepository;
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
public class TitleService {
    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private ModelMapper modelMapper;

    //Get-All-Title
    public ResponseEntity<BaseResponse<Title>> getAllTitle(){
        List<Title> titles = titleRepository.findAll();
        if (titles.isEmpty()){
            return ResponseEntity.ok(new BaseResponse<>(false, null, "No Data"));
        }
        Type targetType = new TypeToken<List<Title>>(){}.getType();
        List<Title> data= modelMapper.map(titles, targetType);
        BaseResponse response = new BaseResponse(true, data, "Title retrieved successfully");

        return ResponseEntity.ok(response);
    }

    //Get-Title-By-Id
    public ResponseEntity<BaseResponse<Title>> titleById(Long id) throws ResourceNotFoundException {
        Title title = titleRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Title ID " + id + " is not exist"));
        Title data = modelMapper.map(title, Title.class);
        EntityModel<Title> resource = EntityModel.of(title);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllTitle())
                .withRel("getAllTitle"));
        BaseResponse response = new BaseResponse(true, data, "Title retrieved successfully");

        return ResponseEntity.ok(response);
    }

    //Post-Title
    public ResponseEntity<BaseResponse<Title>> addTitle(Title emp){
        Title title = modelMapper.map(emp, Title.class);
        Title saved = titleRepository.save(title);
        Title data = modelMapper.map(saved, Title.class);
        BaseResponse response = new BaseResponse(true, data, "Title added successfully");

        return ResponseEntity.ok(response);
    }

    //Put-Title
    public ResponseEntity<BaseResponse<Title>> editTitle(Title Title, Long id){
        Title title = titleRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Title ID " + id + " is not exist"));
        title.setTitle(title.getTitle());
        titleRepository.save(title);

        Title data = modelMapper.map(Title, Title.class);
        BaseResponse response = new BaseResponse(true, data, "Title edited successfully");

        return ResponseEntity.ok(response);
    }

    //Remove-Title
    public ResponseEntity<BaseResponse<Title>> deleteTitle(Long id){
        Title title = titleRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Title ID " + id + " is not exist"));
        titleRepository.deleteById(id);
        Title data = modelMapper.map(title, Title.class);
        BaseResponse response = new BaseResponse(true, data, "Title deleted successfully");

        return ResponseEntity.ok(response);
    }
}
