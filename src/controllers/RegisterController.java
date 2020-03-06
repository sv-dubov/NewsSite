package controllers;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import dto.UserDTO;
import entities.User;
import services.RegistrationService;
import validation.EmailExistsException;

@Controller
public class RegisterController {
	//@Resource(name = "registrationService")
		@Autowired
		private RegistrationService registrationService;
		
		@RequestMapping(value = "/user/registration", method = RequestMethod.GET)
		public String showRegistrationForm(WebRequest request, Model model) {
		    UserDTO userDto = new UserDTO();
		    model.addAttribute("user", userDto);
		    return "registration";
		}
		@RequestMapping(value = "/user/registration", method = RequestMethod.POST)
		public ModelAndView registerUserAccount
		      (@ModelAttribute("user")  UserDTO accountDto, 
		      BindingResult result, WebRequest request, Errors errors) {    
		    User registered = new User();
		    System.out.println(accountDto.toString());
//		    if (!result.hasErrors()) {
		        registered = createUserAccount(accountDto, result);
//		    }
//		    if (registered == null) {
//		        result.rejectValue("email", "message.regError");
//		    }
//		    if (result.hasErrors()) {
//		        return new ModelAndView("registration", "user", accountDto);
//		    } 
//		    else {
		        return new ModelAndView("successRegister", "user", accountDto);
		   // }
		    // rest of the implementation
		}
		private User createUserAccount(UserDTO accountDto, BindingResult result) {
		    User registered = null;
		    try {
		        registered = registrationService.registerNewUserAccount(accountDto);
		    } catch (EmailExistsException e) {
		        return null;
		    }    
		    return registered;
		}
}
