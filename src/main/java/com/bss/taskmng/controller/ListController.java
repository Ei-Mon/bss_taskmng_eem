package com.bss.taskmng.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bss.taskmng.json.ListInBoard;
import com.bss.taskmng.model.ListModelBean;
import com.bss.taskmng.service.ListService;

@RestController
@RequestMapping("/list")
public class ListController {

	@Autowired
	private ListService listService;
	
	Logger logger = LogManager.getLogger(ListController.class);
	
	//Create New List
	@CrossOrigin(origins = "*")
	 @RequestMapping(method = RequestMethod.POST, value = "/createList", consumes = MediaType.APPLICATION_JSON_VALUE) 
	 public boolean createList(@RequestBody ListInBoard request) throws Exception{
		logger.info("ListController - createList");
		ListModelBean list = new ListModelBean();
		boolean createStatus = false;
		try {
			list.setListTitle(request.getListTitle());
			list.setBoardId(request.getBoardId());
			list.setStatusCd("");
			list.setRegId(request.getRegId());
			list.setRegDt(new Date());
			createStatus = listService.createList(list);		
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return createStatus;
	}
	
	//Get All Lists only status is '' ->'' status is actived boards
	@CrossOrigin(origins = "*")
	 @RequestMapping(method = RequestMethod.GET, value = "/getAllLists") 
	public List<ListModelBean> getAllLists() throws Exception{
		logger.info("ListController - getAllLists");
		List<ListModelBean> lists = new ArrayList<ListModelBean>();
		try {
			lists = listService.getAllLists();
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return lists;
	}
	
	//Get List By List Id
	@CrossOrigin(origins = "*")
	 @RequestMapping(method = RequestMethod.POST, value = "/getListById", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ListModelBean getListById(@RequestBody ListInBoard request) throws Exception{
		logger.info("ListController - getListById");
		ListModelBean list = new ListModelBean();
		try {
			list = listService.getListById(request.getListId());
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return list;
	}
	
	//Update List Information
	@CrossOrigin(origins = "*")
	 @RequestMapping(method = RequestMethod.POST, value = "/updateList", consumes = MediaType.APPLICATION_JSON_VALUE) 
	 public boolean updateList(@RequestBody ListInBoard request) throws Exception{
		logger.info("ListController - updateList");
		ListModelBean list = new ListModelBean();
		boolean updateStatus = false;
		try {
			list.setListId(request.getListId());
			list.setListTitle(request.getListTitle());
			list.setChgId(request.getChgId());
			list.setChgDt(new Date());
			updateStatus = listService.updateList(list);			
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return updateStatus;
	}
	
	//Delete List
	@CrossOrigin(origins = "*")
	 @RequestMapping(method = RequestMethod.POST, value = "/deleteList", consumes = MediaType.APPLICATION_JSON_VALUE) 
	 public String deleteList(@RequestBody ListInBoard request) throws Exception{
		logger.info("ListController - deleteList");
		ListModelBean list = new ListModelBean();
		String deleteStatus = "";
		boolean returnStatus = false;
		try {
			list.setListId(request.getListId());
			returnStatus = listService.deleteList(list);
			if(returnStatus)
				deleteStatus = "Delete record successful!";
			else
				deleteStatus = "Delete record fail!";
				
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return deleteStatus;
	}
}
