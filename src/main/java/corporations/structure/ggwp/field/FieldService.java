package corporations.structure.ggwp.field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FieldService {

    private final FieldRepo fieldRepo;

    @Autowired
    public FieldService(FieldRepo fieldRepo){
        this.fieldRepo = fieldRepo;
    }

    public List<Field> getAll() {
        return fieldRepo.findAll();
    }

    public Optional<Field> getOne(Long id) {
        return fieldRepo.findById(id);
    }

    public void addField(Field field) throws Exception {
        Optional<Field> fieldByName = fieldRepo.findFieldByName(field.getName());
        if(fieldByName.isEmpty()){
            fieldRepo.save(field);
        }else{
            throw new Exception("This name is already used by another field");
        }
    }

    public void deleteField(Field field) {
        fieldRepo.delete(field);
    }
}
