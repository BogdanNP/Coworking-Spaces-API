package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.lab7.CalculDobanda;
import com.example.demo.lab7.OpBD;
import com.example.demo.lab7.OpBDMock;
import com.example.demo.lab7.RiskProfile;
import com.example.demo.lab7.TipDobanda;
import com.example.demo.lab7.User;

// @SpringBootTest
class DemoApplicationTests {

	@Mock
	OpBD opBD;

	@Test 
	void testF(){
		// init mocks
		MockitoAnnotations.openMocks(this);
		User user = new User();
		user.setName("TEST USER");
		user.setRisk(RiskProfile.LOW);
		when(opBD.getUser("TEST USER")).thenReturn(user);
		CalculDobanda calculDobanda = new CalculDobanda();

		assertEquals(0.7, calculDobanda.dobanda(opBD.getUser("TEST USER")));
	}

	@Test 
	void testF2(){
		// init mocks
		MockitoAnnotations.openMocks(this);
		User user = new User();
		user.setName("TEST USER");
		user.setRisk(RiskProfile.MEDIUM);
		when(opBD.getUser("TEST USER")).thenReturn(user);
		CalculDobanda calculDobanda2 = new CalculDobanda(opBD);

		assertEquals(0.3, calculDobanda2.calculDobandaDB());
	}

	@Test 
	void testF3(){
		// init mocks
		MockitoAnnotations.openMocks(this);
		User user = new User();
		user.setName("TEST USER 3");
		user.setRisk(RiskProfile.HIGH);
		when(opBD.getUser()).thenReturn(user);
		CalculDobanda calculDobanda2 = new CalculDobanda(opBD);

		assertEquals(0.1, calculDobanda2.calculDobandaDB2());
		verify(opBD, times(1));
	}

	@Test
	void contextLoads() {
	}

	@Test 
	void dobandaMica(){
		System.out.println("TEST RUNNING...");
		CalculDobanda calculDobanda = new CalculDobanda();
		double result = calculDobanda.dobanda(TipDobanda.MICA);
		assertEquals(0.1, result);
		assertNotEquals(0.3, result);
		assertNotEquals(0.7, result);
		assertNotEquals(0.5, result);
		System.out.println("SUCCESS!!!");
	}

	@Test 
	void dobanda(){
		System.out.println("TEST RUNNING...");
		CalculDobanda calculDobanda = new CalculDobanda();
		OpBDMock opBDMock = new OpBDMock();
		User user = opBDMock.getUser("TEST USER");
		double result = calculDobanda.dobanda(user);
		assertEquals(0.1, result);
		assertNotEquals(0.3, result);
		assertNotEquals(0.7, result);
		assertNotEquals(0.5, result);
		System.out.println("SUCCESS!!!");
	}

}
