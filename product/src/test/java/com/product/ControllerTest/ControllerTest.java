package com.product.ControllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.product.Entity.Product;
import com.product.Service.ProductService;



@ExtendWith(MockitoExtension.class)
public class ControllerTest {
	
	
	@Mock
	ProductService service;
	

	Product p=new Product(1L,"Mobile",25000,"Awsome Product","Average",5);
	
	@Test
	void givenUserDetailstoRegisterThenShouldReturnSavedUserDetails()
	{
		when(service.save(any())).thenReturn(p);
		
		assertEquals(p, service.save(p));
		
		verify(service, times(1)).save(any());
	}
}
