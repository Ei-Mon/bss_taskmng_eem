package com.bss.taskmng.service;

import java.util.List;

import com.bss.taskmng.model.ListModelBean;

public interface ListService {

	boolean createList(ListModelBean list);	
	List<ListModelBean> getAllLists();
	ListModelBean getListById(int listId);
	boolean updateList(ListModelBean list);
	boolean deleteList(ListModelBean list);
}
