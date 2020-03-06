package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	// If user will be successfully authenticated he/she will be taken to the login secure page.
		@RequestMapping(value="/admin", method = RequestMethod.GET)
		public ModelAndView adminPage() {

			ModelAndView m = new ModelAndView();
			m.addObject("title", "Spring Security Custom Login Form Example");
			m.addObject("message", "This is protected page!");
			m.setViewName("admin");

			return m;
		}
		// Spring security will see this message.
		@RequestMapping(value = "/login", method = RequestMethod.GET)
		public ModelAndView login(@RequestParam(value = "error", required = false) String error, 
				@RequestParam(value = "logout", required = false) String logout) {

			ModelAndView m = new ModelAndView();
			if (error != null) {
				m.addObject("error", "Hello error");		// Invalid username and password error.
			}

			if (logout != null) {
				m.addObject("msg", "Bye");		// You have left successfully
			}

			m.setViewName("login");
			return m;
		}
}
