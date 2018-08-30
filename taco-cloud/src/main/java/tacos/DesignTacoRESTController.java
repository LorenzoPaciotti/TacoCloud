package tacos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tacos.data.TacoRepository;

@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoRESTController {
	private TacoRepository tacoRepo;

	@Autowired
	EntityLinks entityLinks;

	public DesignTacoRESTController(TacoRepository tacoRepo) {
		this.tacoRepo = tacoRepo;
	}

	@GetMapping("/recent")
	public Iterable<Taco> recentTacos() {

		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());

//		return tacoRepo.findAll(page);//.getContent();
		return tacoRepo.findAll();
	}

	@GetMapping("/{id}")
	public Taco tacoById(@PathVariable("id") Long id) {

		Optional<Taco> optTaco = tacoRepo.findById(id);
		if (optTaco.isPresent()) {
//			return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
			return optTaco.get();
		}
		return null;
	}
}