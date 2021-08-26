package corporations.structure.ggwp;

import corporations.structure.ggwp.corporation.Corporation;
import corporations.structure.ggwp.corporation.CorporationService;
import corporations.structure.ggwp.field.Field;
import corporations.structure.ggwp.field.FieldService;
import corporations.structure.ggwp.worker.Worker;
import corporations.structure.ggwp.worker.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class GgwpApplication implements CommandLineRunner {

	@Autowired
	private final CorporationService corporationService;
	private final FieldService fieldService;
	private final WorkerService workerService;

	public GgwpApplication(
			CorporationService corporationService,
			FieldService fieldService,
			WorkerService workerService
	) {
		this.corporationService = corporationService;
		this.fieldService = fieldService;
		this.workerService = workerService;
	}

	public static void main(String[] args) {
		SpringApplication.run(GgwpApplication.class, args);
	}

	// Filling the database with initial records
	@Override
	public void run(String... args) throws Exception {
		Field field1 = new Field(
				"IT"
		);

		Corporation corporation1 = new Corporation(
				"Apple",
				"apple.info@gmail.com"
		);

		Worker worker1 = new Worker(
				"Eldar",
				"Babayev",
				"elba@gmail.com",
				LocalDate.of(1992, 4, 27),
				900,
				"Security specialist",
				corporation1
		);

		Worker worker2 = new Worker(
				"John",
				"Smith",
				"johnyyy@mail.ru",
				LocalDate.of(1986, 7, 2),
				900,
				"Security specialist",
				corporation1
		);

		fieldService.addField(
				field1
		);

		corporationService.addCorporation(
				corporation1
		);

		corporationService.connectFieldToCorporation(corporation1, field1);

		workerService.addWorker(worker1);
		workerService.addWorker(worker2);
	}
}
