package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.handlers.DeskHandler;
import com.example.demo.models.DataResponse;
import com.example.demo.models.Desk;
import com.example.demo.models.DeskRequest;
import com.example.demo.models.DeskStatus;
import com.example.demo.models.OrderP;
import com.example.demo.models.Room;
import com.example.demo.models.UserP;
import com.example.demo.repositories.DeskRepository;
import com.example.demo.repositories.DeskRequestRepository;
import com.example.demo.repositories.OrderPRepository;
import com.example.demo.repositories.RoomRepository;
import com.example.demo.repositories.UserPRepository;

@SpringBootTest
class DemoApplicationTests {
	// Repositories
	@Mock
	DeskRepository deskRepository;
	@Mock 
	DeskRequestRepository deskRequestRepository;
	@Mock
	OrderPRepository orderPRepository;
	@Mock 
	RoomRepository roomRepository;
	@Mock
	UserPRepository userPRepository;

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
		desk.setTariffType("HOUR");
		return desk;
	}

	DeskRequest mockDeskRequest(){
		SimpleDateFormat dateFormater = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
		DeskRequest deskRequest = new DeskRequest(); 
		deskRequest.setId(1);
		deskRequest.setDeskId(1);
		deskRequest.setStatus(DeskStatus.AVAILABLE);
		deskRequest.setUserId(1);
		try {
			deskRequest.setStartDate(dateFormater.parse("09-05-2023-00:22:23"));
			deskRequest.setEndDate(dateFormater.parse("09-05-2023-07:22:23"));
		} catch (Exception e) {}
		return deskRequest;
	}

	OrderP mockOrderP(){
		OrderP orderP = new OrderP();
		orderP.setId(1);
		orderP.setDeskId(1);
		orderP.setUserId(1);
		orderP.setStatus("PERFECT");
		orderP.setTotal(300.0);
		return orderP;
	}

	Room mockRoom(){
		Room room = new Room();
		room.setDetails("details");
		room.setId(1);
		room.setLength(200);
		room.setWidth(20);
		return room;
	}

	UserP mockUser(){
		UserP userP = new UserP();
		userP.setId(1);
		userP.setType("USER");
		userP.setPassword("1234");
		userP.setUsername("TestUsername");
		return userP;
	}

	//TODO: align data in order to make all the logic work

	// Desk Tests
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
	void deskFindAllError(){
		List<Desk> deskList = new ArrayList<Desk>();
		Desk mockDesk = mockDesk();
		deskList.add(mockDesk);
		when(deskRepository.findAll()).thenReturn(deskList);
		DeskHandler deskHandler = DeskHandler.instance(deskRepository);
		DataResponse response = deskHandler.findAll();
		ArrayList<Desk> testDeskList = (ArrayList)response.getData();
		Desk testDesk = testDeskList.get(0);
		assertEquals(mockDesk, testDesk);
	}

	@Test
	void deskSaveSuccess(){
		Desk mockDesk = mockDesk();
		when(deskRepository.save(mockDesk)).thenReturn(mockDesk);
		DeskHandler deskHandler = DeskHandler.instance(deskRepository);
		DataResponse response = deskHandler.findAll();
		ArrayList<Desk> testDeskList = (ArrayList)response.getData();
		Desk testDesk = testDeskList.get(0);
		assertEquals(mockDesk, testDesk);
	}
}
