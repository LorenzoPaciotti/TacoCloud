package tacos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.validation.Errors;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import tacos.Taco;
import tacos.Ingredient;
import tacos.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

//	private final tacos.data.IngredientRepository ingredientRepoJDBC;
//	private tacos.data.TacoRepository designRepoJDBC;
	
	private final tacos.data.IngredientRepository ingredientRepoJPA;
	private tacos.data.TacoRepository designRepoJPA;

	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}

	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	
//	//COSTRUTTORE AUTOWIRED A REPOSITORY JDBC
//	@Autowired
//	public DesignTacoController(tacos.data.IngredientRepository ingredientRepo, tacos.data.TacoRepository designRepo) {
//		this.ingredientRepoJDBC = ingredientRepo;
//		this.designRepoJDBC = designRepo;
//	}

	//COSTRUTTORE AUTOWIRED A REPOSITORY JPA
	@Autowired
	public DesignTacoController(tacos.data.IngredientRepository ingredientRepo, tacos.data.TacoRepository designRepo) {
		this.ingredientRepoJPA = ingredientRepo;
		this.designRepoJPA = designRepo;
	}

	@GetMapping
	public String showDesignForm(Model model) {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepoJPA.findAll().forEach(i -> ingredients.add(i));
//		ingredientRepoJDBC.findAll().forEach(i -> ingredients.add(i));

		Type[] types = Ingredient.Type.values();

		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}

		model.addAttribute("design", new Taco());
		
		return "design";
	}

	@PostMapping
	public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order) {
		// VALIDAZIONE
//		if (errors.hasErrors()) {
//			log.warn("************validazione non passata");
//			// return "design";
//		}

		// SALVATAGGIO CON REPOSITORY
		log.info("INVIO A REPOSITORY TACO: " + design);
		if (design != null) {
			Taco saved = designRepoJPA.save(design);
			order.addDesign(saved);
		}else {log.error("*************design ====> NULL!");}
		return "redirect:/orders/current";
	}

	private Object filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream().filter(p -> p.getType().equals(type)).collect(Collectors.toList());
	}

}

/*
 * The Order parameter is annotated with @ModelAttribute here to indicate that
 * its value should come from the model and that Spring MVC should not attempt
 * to bind request parameters to it. After checking for validation errors,
 * processDesign() uses the injected TacoRepository to save the taco. It then
 * adds the Taco object to the Order that’s kept in the session. In fact, the
 * Order object will remain in the session and not saved to the database until
 * the user completes and submits the order form. At that point, OrderController
 * will need to call out to an implementation of OrderRepository to save the
 * order.
 */

//@GetMapping
//public String showDesignForm(Model model) {
//	List<Ingredient> ingredients = Arrays.asList(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
//			new Ingredient("COTO", "Corn Tortilla", Type.WRAP), new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
//			new Ingredient("CARN", "Carnitas", Type.PROTEIN),
//			new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES), new Ingredient("LETC", "Lettuce", Type.VEGGIES),
//			new Ingredient("CHED", "Cheddar", Type.CHEESE), new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
//			new Ingredient("SLSA", "Salsa", Type.SAUCE), new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
//
//	Type[] types = Ingredient.Type.values();
//
//	for (Type type : types) {
//		model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
//	}
//
//	model.addAttribute("design", new Taco());
//	return "design";
//}
