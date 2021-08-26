package corporations.structure.ggwp.worker;

import corporations.structure.ggwp.corporation.Corporation;
import corporations.structure.ggwp.corporation.CorporationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    private final WorkerRepo workerRepo;
    private final CorporationRepo corporationRepo;

    @Autowired
    public WorkerService(WorkerRepo workerRepo, CorporationRepo corporationRepo){
        this.workerRepo = workerRepo;
        this.corporationRepo = corporationRepo;
    }

    public List<Worker> getAll() {
        return workerRepo.findAll();
    }

    public Optional<Worker> getOne(Long id) {
        return workerRepo.findById(id);
    }

    public void addWorker(Worker worker) throws Exception {
        Optional<Worker> workerByEmail = workerRepo.findWorkerByEmail(worker.getEmail());

        if(workerByEmail.isEmpty()){

            Optional<Corporation> corporationByEmail =
                    corporationRepo.findCorporationByEmail(worker.getCorporation().getEmail());

            if(corporationByEmail.isEmpty()){
                throw new Exception("This email is not registered for any corporation");
            }else{

                if(!worker.getCorporation().getName().equals(corporationByEmail.get().getName())){
                    throw new Exception("Wrong corporation info");
                }

                workerRepo.save(worker);
            }

            workerRepo.save(worker);

        }else{
            throw new Exception("This email is already used by another worker");
        }
    }

    public void deleteWorker(Worker worker) {
        workerRepo.delete(worker);
    }
}
