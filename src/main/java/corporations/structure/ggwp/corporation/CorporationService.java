package corporations.structure.ggwp.corporation;

import corporations.structure.ggwp.field.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CorporationService {

    private final CorporationRepo corporationRepo;

    @Autowired
    public CorporationService(CorporationRepo corporationRepo){
        this.corporationRepo = corporationRepo;
    }

    public List<Corporation> getAll() {
        return corporationRepo.findAll();
    }

    public Optional<Corporation> getOne(Long id) {
        return corporationRepo.findById(id);
    }

    public void addCorporation(Corporation corporation) throws Exception {
        Optional<Corporation> corporationByEmail = corporationRepo.findCorporationByEmail(corporation.getEmail());
        if(corporationByEmail.isEmpty()){
            corporationRepo.save(corporation);
        }else{
            throw new Exception("This email is already used by another corporation");
        }
    }

    public void connectFieldToCorporation(Corporation corporation, Field field) {
        corporation.assignField(field);
        corporationRepo.save(corporation);
    }

    public void deleteCorporation(Corporation corporation) {
        corporationRepo.delete(corporation);
    }
}
