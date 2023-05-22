package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.handlers.UserPHandler;
import com.example.demo.models.DataResponse;
import com.example.demo.models.DataResponseStatus;
import com.example.demo.models.UserP;
import com.example.demo.models.UserPTypes;
import com.example.demo.repositories.UserPRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class UserPTests {
	// Repository
	@Mock
	UserPRepository userPRepository;

	// SetUp
	@BeforeEach
	void setUp(){
		MockitoAnnotations.openMocks(this);
	}

	// Mock Data Model Generators

	UserP mockUserP(){
		UserP userP = new UserP();
		userP.setId(1);
		userP.setType(UserPTypes.LOGGED_IN);
		userP.setPassword("1234");
		userP.setUsername("TestUsername");
		return userP;
	}

	String mockUserPJSON(){
		try{
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", 1);
			map.put("type", UserPTypes.LOGGED_IN);
			map.put("password", "1234");
			map.put("username", "TestUsername");
			return mapper.writeValueAsString(map);
		} catch (Exception e){
			return "";
		}
	}

	Exception mockException(){
		return new Exception("mockException");
	}

	// UserP Tests
	
	@Test
	void userPSaveSuccess() throws JsonMappingException, JsonProcessingException{
		UserP mockUserP = mockUserP();
		when(userPRepository.save(mockUserP)).thenReturn(mockUserP);
		UserPHandler userPHandler = UserPHandler.instance(userPRepository);
		DataResponse response = userPHandler.save(mockUserPJSON());
		UserP testUserP = (UserP)response.getData();
		assertEquals(DataResponseStatus.SUCCESS, response.getStatus());
		assertEquals(mockUserP, testUserP);
	}

	@Test
	void userPSaveError() throws JsonMappingException, JsonProcessingException{
		UserP mockUserP = mockUserP();
		when(userPRepository.save(mockUserP)).thenThrow(new IllegalArgumentException("save fail"));
		UserPHandler userPHandler = UserPHandler.instance(userPRepository);
		DataResponse response = userPHandler.save(mockUserPJSON());
		assertEquals(DataResponseStatus.ERROR, response.getStatus());
		assertEquals("save fail", response.getMessage());
	}

	@Test
	void userPFindAllSuccess(){
		List<UserP> deskList = new ArrayList<UserP>();
		UserP mockUserP = mockUserP();
		deskList.add(mockUserP);
		when(userPRepository.findAll()).thenReturn(deskList);
		UserPHandler userPHandler = UserPHandler.instance(userPRepository);
		DataResponse response = userPHandler.findAll();
		ArrayList<UserP> testUserPList = (ArrayList)response.getData();
		UserP testUserP = testUserPList.get(0).copy();
		assertEquals(mockUserP, testUserP);
	}

	@Test
	void userPUpdateSuccess() throws JsonMappingException, JsonProcessingException{
		List<UserP> userPList = new ArrayList<UserP>();
		UserP mockUserP = mockUserP();
		UserP mockUserP2 = mockUserP();
		mockUserP2.setType(UserPTypes.ADMIN);
		userPList.add(mockUserP2);
		when(userPRepository.findAll()).thenReturn(userPList);
		when(userPRepository.save(mockUserP)).thenReturn(mockUserP);
		UserPHandler userPHandler = UserPHandler.instance(userPRepository);
		DataResponse response = userPHandler.update(mockUserPJSON());
		UserP testUserP = (UserP)response.getData();
		verify(userPRepository, times(1)).delete(mockUserP2);
		assertEquals(DataResponseStatus.SUCCESS, response.getStatus());
		assertEquals(mockUserP, testUserP);
	}

	@Test
	void userPUpdateError() throws JsonMappingException, JsonProcessingException{
		List<UserP> userPList = new ArrayList<UserP>();
		UserP mockUserP = mockUserP();
		UserP mockUserP2 = mockUserP();
		mockUserP2.setId(2);
		userPList.add(mockUserP2);
		when(userPRepository.findAll()).thenReturn(userPList);
		when(userPRepository.save(mockUserP)).thenReturn(mockUserP);
		UserPHandler userPHandler = UserPHandler.instance(userPRepository);
		DataResponse response = userPHandler.update(mockUserPJSON());
		verify(userPRepository, times(0)).delete(mockUserP2);
		assertEquals(DataResponseStatus.ERROR, response.getStatus());
	}

	@Test
	void userPDeleteSuccess(){
		List<UserP> userPList = new ArrayList<UserP>();
		UserP mockUserP = mockUserP();
		UserP mockUserP2 = mockUserP();
		mockUserP2.setId(2);
		userPList.add(mockUserP);
		userPList.add(mockUserP2);
		when(userPRepository.findAll()).thenReturn(userPList);
		UserPHandler userPHandler = UserPHandler.instance(userPRepository);
		DataResponse response = userPHandler.delete(2);
		verify(userPRepository, times(1)).delete(mockUserP2);
		assertEquals(DataResponseStatus.SUCCESS, response.getStatus());
	}

	@Test
	void userPDeleteError(){
		List<UserP> userPList = new ArrayList<UserP>();
		UserP mockUserP = mockUserP();
		UserP mockUserP2 = mockUserP();
		mockUserP2.setId(2);
		userPList.add(mockUserP);
		userPList.add(mockUserP2);
		when(userPRepository.findAll()).thenReturn(userPList);
		UserPHandler userPHandler = UserPHandler.instance(userPRepository);
		DataResponse response = userPHandler.delete(3);
		verify(userPRepository, times(0)).delete(mockUserP2);
		assertEquals(DataResponseStatus.ERROR, response.getStatus());
	}
}
