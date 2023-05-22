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

import com.example.demo.handlers.DeskHandler;
import com.example.demo.models.DataResponse;
import com.example.demo.models.DataResponseStatus;
import com.example.demo.models.Desk;
import com.example.demo.models.TariffTypes;
import com.example.demo.repositories.DeskRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class DeskTests {
	// Repository
	@Mock
	DeskRepository deskRepository;

	// SetUp
	@BeforeEach
	void setUp(){
		MockitoAnnotations.openMocks(this);
	}

	// Mock Data Model Generators
	Desk mockDesk(){
		Desk desk = new Desk(); 
		desk.setId(1);
		desk.setHeight(100);
		desk.setWidth(50);
		desk.setLength(120);
		desk.setTariff(5.0);
		desk.setTariffType(TariffTypes.HOUR);
		return desk;
	}

	String mockDeskJSON(){
		try{
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", 1);
			map.put("height", 100);
			map.put("width", 50);
			map.put("length", 120);
			map.put("tariff", 5.0);
			map.put("tariff_type", TariffTypes.HOUR);
			return mapper.writeValueAsString(map);
		} catch (Exception e){
			return "";
		}
	}

	// Desk Tests

	@Test
	void deskSaveSuccess() throws JsonMappingException, JsonProcessingException{
		Desk mockDesk = mockDesk();
		when(deskRepository.save(mockDesk)).thenReturn(mockDesk);
		DeskHandler deskHandler = DeskHandler.instance(deskRepository);
		DataResponse response = deskHandler.save(mockDeskJSON());
		Desk testDesk = (Desk)response.getData();
		assertEquals(DataResponseStatus.SUCCESS, response.getStatus());
		assertEquals(mockDesk, testDesk);
	}

	@Test
	void deskSaveError() throws JsonMappingException, JsonProcessingException{
		Desk mockDesk = mockDesk();
		when(deskRepository.save(mockDesk)).thenThrow(new IllegalArgumentException("save fail"));
		DeskHandler deskHandler = DeskHandler.instance(deskRepository);
		DataResponse response = deskHandler.save(mockDeskJSON());
		assertEquals(DataResponseStatus.ERROR, response.getStatus());
		assertEquals("save fail", response.getMessage());
	}

	@Test
	void deskFindAllSuccess(){
		List<Desk> deskList = new ArrayList<Desk>();
		Desk mockDesk = mockDesk();
		deskList.add(mockDesk);
		when(deskRepository.findAll()).thenReturn(deskList);
		DeskHandler deskHandler = DeskHandler.instance(deskRepository);
		DataResponse response = deskHandler.findAll();
		ArrayList<Desk> testDeskList = (ArrayList)response.getData();
		Desk testDesk = testDeskList.get(0).copy();
		assertEquals(mockDesk, testDesk);
	}

	@Test
	void deskUpdateSuccess() throws JsonMappingException, JsonProcessingException{
		List<Desk> deskList = new ArrayList<Desk>();
		Desk mockDesk = mockDesk();
		Desk mockDesk2 = mockDesk();
		mockDesk2.setTariffType("DAY");
		deskList.add(mockDesk2);
		when(deskRepository.findAll()).thenReturn(deskList);
		when(deskRepository.save(mockDesk)).thenReturn(mockDesk);
		DeskHandler deskHandler = DeskHandler.instance(deskRepository);
		DataResponse response = deskHandler.update(mockDeskJSON());
		Desk testDesk = (Desk)response.getData();
		verify(deskRepository, times(1)).delete(mockDesk2);
		assertEquals(DataResponseStatus.SUCCESS, response.getStatus());
		assertEquals(mockDesk, testDesk);
	}

	@Test
	void deskUpdateError() throws JsonMappingException, JsonProcessingException{
		List<Desk> deskList = new ArrayList<Desk>();
		Desk mockDesk = mockDesk();
		Desk mockDesk2 = mockDesk();
		mockDesk2.setId(2);
		deskList.add(mockDesk2);
		when(deskRepository.findAll()).thenReturn(deskList);
		when(deskRepository.save(mockDesk)).thenReturn(mockDesk);
		DeskHandler deskHandler = DeskHandler.instance(deskRepository);
		DataResponse response = deskHandler.update(mockDeskJSON());
		verify(deskRepository, times(0)).delete(mockDesk2);
		assertEquals(DataResponseStatus.ERROR, response.getStatus());
	}

	@Test
	void deskDeleteSuccess(){
		List<Desk> deskList = new ArrayList<Desk>();
		Desk mockDesk = mockDesk();
		Desk mockDesk2 = mockDesk();
		mockDesk2.setId(2);
		deskList.add(mockDesk);
		deskList.add(mockDesk2);
		when(deskRepository.findAll()).thenReturn(deskList);
		DeskHandler deskHandler = DeskHandler.instance(deskRepository);
		DataResponse response = deskHandler.delete(2);
		verify(deskRepository, times(1)).delete(mockDesk2);
		assertEquals(DataResponseStatus.SUCCESS, response.getStatus());
	}

	@Test
	void deskDeleteError(){
		List<Desk> deskList = new ArrayList<Desk>();
		Desk mockDesk = mockDesk();
		Desk mockDesk2 = mockDesk();
		mockDesk2.setId(2);
		deskList.add(mockDesk);
		deskList.add(mockDesk2);
		when(deskRepository.findAll()).thenReturn(deskList);
		DeskHandler deskHandler = DeskHandler.instance(deskRepository);
		DataResponse response = deskHandler.delete(3);
		verify(deskRepository, times(0)).delete(mockDesk2);
		assertEquals(DataResponseStatus.ERROR, response.getStatus());
    }
}
