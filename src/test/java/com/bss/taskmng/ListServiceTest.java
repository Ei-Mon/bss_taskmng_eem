package com.bss.taskmng;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bss.taskmng.model.ListModelBean;
import com.bss.taskmng.service.ListService;

/* Integration Testing for List Service */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ListServiceTest {

	@Autowired
	private ListService listService;
	
	@Test
	public void createList() {
		ListModelBean list = new ListModelBean();
		list.setListTitle("Open Issue List");
		list.setBoardId(3);
		list.setStatusCd("");
		list.setRegId(1);
		list.setRegDt(new Date());
		
		boolean createList = listService.createList(list);
		if(createList)
			assertThat(createList);
	}
	
	@Test
	public void getAllLists() {
		List<ListModelBean> list = listService.getAllLists();
		assertThat(list).isNotNull().isNotEmpty();
	}
	
	@Test
	public void getListById() {
		int listId = 11;
		ListModelBean list = listService.getListById(listId);
		assertThat(list.getListId()).isEqualTo(listId);;
	}
	
	@Test
	public void updateList() {
		ListModelBean list = new ListModelBean();
		list.setListId(11);
		list.setListTitle("Opening Issue List");
		list.setChgId(1);
		list.setChgDt(new Date());
		
		boolean updateList = listService.updateList(list);
		if(updateList)
			assertThat(updateList);
	}
	
	@Test
	public void deleteList() {
		ListModelBean list = new ListModelBean();
		list.setListId(11);
		
		boolean deleteList = listService.deleteList(list);
		if(deleteList)
			assertThat(deleteList);
	}
}
