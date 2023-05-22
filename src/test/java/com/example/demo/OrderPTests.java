package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.handlers.OrderPHandler;
import com.example.demo.models.DataResponse;
import com.example.demo.models.DataResponseStatus;
import com.example.demo.models.OrderP;
import com.example.demo.models.OrderPStatus;
import com.example.demo.repositories.OrderPRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class OrderPTests {
	// Repository
	@Mock
	OrderPRepository orderPRepository;

	// SetUp
	@BeforeEach
	void setUp(){
		MockitoAnnotations.openMocks(this);
	}

	// Mock Data Model Generators

	OrderP mockOrderP(){
		OrderP orderP = new OrderP();
		orderP.setId(1);
		orderP.setDeskId(1);
		orderP.setUserId(1);
		orderP.setStatus(OrderPStatus.NEW);
		orderP.setTotal(300.0);
		return orderP;
	}

	String mockOrderPJSON(){
		try{
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", 1);
			map.put("desk_id", 1);
			map.put("user_id", 1);
			map.put("status", OrderPStatus.NEW);
			map.put("total", 300.0);
			return mapper.writeValueAsString(map);
		} catch (Exception e){
			return "";
		}
	}

	Exception mockException(){
		return new Exception("mockException");
	}

	// OrderP Tests

	@Test
	void orderPSaveSuccess() throws JsonMappingException, JsonProcessingException{
		OrderP orderP = mockOrderP();
		when(orderPRepository.save(orderP)).thenReturn(orderP);
		OrderPHandler orderPHandler = OrderPHandler.instance(orderPRepository);
		DataResponse response = orderPHandler.save(mockOrderPJSON());
		OrderP testOrderP = (OrderP)response.getData();
		assertEquals(DataResponseStatus.SUCCESS, response.getStatus());
		assertEquals(orderP, testOrderP);
	}

	@Test
	void orderPSaveError() throws JsonMappingException, JsonProcessingException{
		OrderP orderP = mockOrderP();
		when(orderPRepository.save(orderP)).thenThrow(new IllegalArgumentException("save fail"));
		OrderPHandler orderPHandler = OrderPHandler.instance(orderPRepository);
		DataResponse response = orderPHandler.save(mockOrderPJSON());
		assertEquals(DataResponseStatus.ERROR, response.getStatus());
		assertEquals("save fail", response.getMessage());
	}

	@Test
	void orderPFindAllSuccess(){
		List<OrderP> orderPList = new ArrayList<OrderP>();
		OrderP mockOrderP = mockOrderP();
		orderPList.add(mockOrderP);
		when(orderPRepository.findAll()).thenReturn(orderPList);
		OrderPHandler orderPHandler = OrderPHandler.instance(orderPRepository);
		DataResponse response = orderPHandler.findAll();
		ArrayList<OrderP> testOrderPList = (ArrayList)response.getData();
		OrderP testOrderP = testOrderPList.get(0).copy();
		assertEquals(mockOrderP, testOrderP);
	}

	@Test
	void orderPUpdateSucces() throws JsonMappingException, JsonProcessingException{
		List<OrderP> orderPList = new ArrayList<OrderP>();
		OrderP mockOrderP = mockOrderP();
		OrderP mockOrderP2 = mockOrderP();
		mockOrderP2.setStatus(OrderPStatus.PAID);
		orderPList.add(mockOrderP2);
		when(orderPRepository.findAll()).thenReturn(orderPList);
		when(orderPRepository.save(mockOrderP)).thenReturn(mockOrderP);
		OrderPHandler orderPHandler = OrderPHandler.instance(orderPRepository);
		DataResponse response = orderPHandler.update(mockOrderPJSON());
		OrderP testOrderP = (OrderP)response.getData();
		verify(orderPRepository, times(1)).delete(mockOrderP2);
		assertEquals(DataResponseStatus.SUCCESS, response.getStatus());
		assertEquals(mockOrderP, testOrderP);
	}

	@Test
	void orderPUpdateError() throws JsonMappingException, JsonProcessingException{
		List<OrderP> orderPList = new ArrayList<OrderP>();
		OrderP mockOrderP = mockOrderP();
		OrderP mockOrderP2 = mockOrderP();
		mockOrderP2.setId(2);
		orderPList.add(mockOrderP2);
		when(orderPRepository.findAll()).thenReturn(orderPList);
		when(orderPRepository.save(mockOrderP)).thenReturn(mockOrderP);
		OrderPHandler orderPHandler = OrderPHandler.instance(orderPRepository);
		DataResponse response = orderPHandler.update(mockOrderPJSON());
		verify(orderPRepository, times(0)).delete(mockOrderP2);
		assertEquals(DataResponseStatus.ERROR, response.getStatus());
	}

	@Test
	void orderPDeleteSuccess() {
		List<OrderP> orderPList = new ArrayList<OrderP>();
		OrderP mockOrderP = mockOrderP();
		OrderP mockOrderP2 = mockOrderP();
		mockOrderP2.setId(2);
		orderPList.add(mockOrderP);
		orderPList.add(mockOrderP2);
		when(orderPRepository.findAll()).thenReturn(orderPList);
		OrderPHandler orderPHandler = OrderPHandler.instance(orderPRepository);
		DataResponse response = orderPHandler.delete(2);
		verify(orderPRepository, times(1)).delete(mockOrderP2);
		assertEquals(DataResponseStatus.SUCCESS, response.getStatus());
	}

	@Test
	void orderPDeleteError() {
		List<OrderP> orderPList = new ArrayList<OrderP>();
		OrderP mockOrderP = mockOrderP();
		OrderP mockOrderP2 = mockOrderP();
		mockOrderP2.setId(2);
		orderPList.add(mockOrderP);
		orderPList.add(mockOrderP2);
		when(orderPRepository.findAll()).thenReturn(orderPList);
		OrderPHandler orderPHandler = OrderPHandler.instance(orderPRepository);
		DataResponse response = orderPHandler.delete(3);
		verify(orderPRepository, times(0)).delete(mockOrderP2);
		assertEquals(DataResponseStatus.ERROR, response.getStatus());
	}
}
