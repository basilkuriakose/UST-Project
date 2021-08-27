package com.user.ControllerTest;
import com.user.Controller.UserController;
import com.user.Entity.User;
import com.user.Service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ControllerTest {
	
	
	@Mock
	UserService service;
	
	@InjectMocks
	UserController controller;
	

	User u=new User(1L,"Basil","Basil@gmail.com","Basil123","9954324566");
	
	@Test
	void givenUserDetailstoRegisterThenShouldReturnSavedUserDetails()
	{
		when(service.save(any())).thenReturn(u);
		
		assertEquals(u, service.save(u));
		
		verify(service, times(1)).save(any());
	}
}
