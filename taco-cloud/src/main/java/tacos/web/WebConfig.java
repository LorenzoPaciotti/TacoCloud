package tacos.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
	}
}
/*
when a controller is
simple enough that it doesn’t populate a model or process input—as is the case with
our HomeController — there’s another way that you can define the controller. Have a
look at listing 2.15 to see how to declare a view controller—a controller that does
nothing but forward the request to a view.

la configurazione sostituisce classe HomeController che era un controller:
@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
	return "home";
	}
}
*/