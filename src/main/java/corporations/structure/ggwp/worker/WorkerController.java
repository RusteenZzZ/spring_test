package corporations.structure.ggwp.worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/worker")
public class WorkerController {

    private final WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService){
        this.workerService = workerService;
    }

    @GetMapping
    public List<Worker> getWorkers(){
        return workerService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Worker> getWorker(@PathVariable Long id){
        return workerService.getOne(id);
    }

    @PostMapping
    public void createWorker(@RequestBody Worker worker) throws Exception {
        workerService.addWorker(worker);
    }

    @DeleteMapping("/{id}")
    public void deleteWorker(@PathVariable Long id) throws Exception {
        Optional<Worker> worker = workerService.getOne(id);
        if(worker.isEmpty()){
            throw new Exception("No worker with such id is found");
        }else{
            workerService.deleteWorker(worker.get());
        }
    }
}
