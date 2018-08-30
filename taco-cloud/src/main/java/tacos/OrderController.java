package tacos;

import java.awt.print.Pageable;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import tacos.data.OrderRepository;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

	private OrderRepository orderRepo;
	
	private OrderProps props;

	public OrderController(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}

	@GetMapping("/current")
	public String orderForm(Model model, @ModelAttribute("order") Order order, @AuthenticationPrincipal User user) {
		model.addAttribute("order", order);
		order.setUser(user);
		return "orderForm";
	}

	@GetMapping("/list")
	public String ordersForUser(@AuthenticationPrincipal User user, Model model) {

//		Pageable pageable = (Pageable) PageRequest.of(0, props.getPageSize());
//		model.addAttribute("orders", orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));

		return "orderList";
	}

	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {
		if (errors.hasErrors()) {
			return "orderForm";
		}

		log.info("processOrder " + order);

		orderRepo.save(order);
		sessionStatus.setComplete();

		return "redirect:/";
	}

}

/*
 * Authentication authentication =
 * SecurityContextHolder.getContext().getAuthentication(); User user = (User)
 * authentication.getPrincipal();
 */