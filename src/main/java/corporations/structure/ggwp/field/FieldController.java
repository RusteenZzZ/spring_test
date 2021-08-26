package corporations.structure.ggwp.field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/field")
public class FieldController {

    private final FieldService fieldService;

    @Autowired
    public FieldController(FieldService fieldService){
        this.fieldService = fieldService;
    }

    @GetMapping
    public List<Field> getFields(){
        return fieldService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Field> getField(@PathVariable Long id){
        return fieldService.getOne(id);
    }

    @PostMapping
    public void createField(@RequestBody Field field) throws Exception {
        fieldService.addField(field);
    }

    @DeleteMapping("/{id}")
    public void deleteField(@PathVariable Long id) throws Exception {
        Optional<Field> field = fieldService.getOne(id);
        if(field.isEmpty()){
            throw new Exception("No field with such id is found");
        }else{
            fieldService.deleteField(field.get());
        }
    }
}
