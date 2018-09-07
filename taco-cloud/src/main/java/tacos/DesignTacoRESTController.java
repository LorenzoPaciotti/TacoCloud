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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tacos.data.OrderRepository;
import tacos.data.TacoRepository;

@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoRESTController {

	private TacoRepository tacoRepo;
	
	private OrderRepository orderRepo;

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
			return optTaco.get();
		}
		return null;
	}

	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Taco postTaco(@RequestBody Taco taco) {
		return tacoRepo.save(taco);
		/*
		 * The Taco parameter to the method is annotated with @RequestBody to indicate
		 * that the body of the request should be converted to a Taco object and bound
		 * to the parameter. This annotation is important; without it, Spring MVC would
		 * assume that you want request parameters (either query parameters or form
		 * parameters) to be bound to the Taco object, rather than the JSON in the
		 * request body.
		 */
	}

	@PatchMapping(path = "/{orderId}", consumes = "application/json")
	public Order patchOrder(@PathVariable("orderId") Long orderId, @RequestBody Order patch) {
		
		Order order = orderRepo.findById(orderId).get();
		
		if (patch.getName() != null) {
			order.setName(patch.getName());
		}
		if (patch.getStreet() != null) {
			order.setStreet(patch.getStreet());
		}
		if (patch.getCity() != null) {
			order.setCity(patch.getCity());
		}
		if (patch.getState() != null) {
			order.setState(patch.getState());
		}
		if (patch.getZip() != null) {
			order.setZip(patch.getZip());
		}
		if (patch.getCcNumber() != null) {
			order.setCcNumber(patch.getCcNumber());
		}
		if (patch.getCcExpiration() != null) {
			order.setCcExpiration(patch.getCcExpiration());
		}
		if (patch.getCcCVV() != null) {
			order.setCcCVV(patch.getCcCVV());
		}
		return orderRepo.save(order);
	}
}
