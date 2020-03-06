package services;

import dto.UserDTO;
import entities.User;
import validation.EmailExistsException;

public interface IRegistrationService {

	 User registerNewUserAccount(UserDTO accountDto)     
		      throws EmailExistsException;
}
