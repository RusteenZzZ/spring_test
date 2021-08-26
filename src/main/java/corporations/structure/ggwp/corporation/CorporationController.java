package corporations.structure.ggwp.corporation;

import corporations.structure.ggwp.field.Field;
import corporations.structure.ggwp.field.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/corporation")
public class CorporationController {

    private final CorporationService corporationService;
    private final FieldService fieldService;

    @Autowired
    public CorporationController(
            CorporationService corporationService,
            FieldService fieldService
    ){
        this.corporationService = corporationService;
        this.fieldService = fieldService;
    }

    @GetMapping
    public List<Corporation> getCorporations(){
        return corporationService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Corporation> getCorporation(@PathVariable Long id){
        return corporationService.getOne(id);
    }

    @PostMapping
    public void createCorporation(@RequestBody Corporation corporation) throws Exception {
        corporationService.addCorporation(corporation);
    }

    @PutMapping("/{corporationId}/field/{fieldId}")
    public void connectFieldToCorporation(
        @PathVariable Long corporationId,
        @PathVariable Long fieldId
    ) throws Exception {
        Optional<Corporation> corporation = corporationService.getOne(corporationId);
        Optional<Field> field = fieldService.getOne(fieldId);
        if(corporation.isEmpty() || field.isEmpty()){
            throw new Exception("Corporation or Field with such Id is not found");
        }else{
            corporationService.connectFieldToCorporation(corporation.get(), field.get());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCorporation(@PathVariable Long id) throws Exception {
        Optional<Corporation> corporation = corporationService.getOne(id);
        if(corporation.isEmpty()){
            throw new Exception("No corporation with such id is found");
        }else{
            corporationService.deleteCorporation(corporation.get());
        }
    }
}
