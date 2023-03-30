package com.bikkadIt;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import com.bikkadIt.dao.ContactDaoI;
import com.bikkadIt.service.ContactServiceImpl;

public class ContactServiceITest {
	
	
	
	@Test
	public void testfindByNameById()
	{
		
		//Dependent dao mock
		ContactDaoI  daoproxy = EasyMock.createMock(ContactDaoI.class);
		
		
		//option 
		EasyMock.expect(daoproxy.findNameById(101)).andReturn("rahul");
		EasyMock.expect(daoproxy.findNameById(102)).andReturn("santosh");
		EasyMock.expect(daoproxy.findNameById(103)).andReturn("rohit");
		EasyMock.expect(daoproxy.findNameById(104)).andReturn("rathod");
		EasyMock.expect(daoproxy.findNameById(105)).andReturn("sagar");
		EasyMock.expect(daoproxy.findNameById(106)).andReturn("ganesh");
		
		
		EasyMock.replay(daoproxy);
		
		//Create Object 
		ContactServiceImpl contactServiceImpl = new ContactServiceImpl();
		contactServiceImpl.setContactDaoI(daoproxy);
				
		//method actual method call 
		
		String name = contactServiceImpl.getNameById(102);

		assertNotNull(name);
		
		
		
		
		
		
		
		
		

		
		
		
		
		
		
		
		
		




		
		
	}

}

