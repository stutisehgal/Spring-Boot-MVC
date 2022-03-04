package com.trg.boot.controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.trg.model.Employee;

@Controller //creating a controller class with this annotation
@RequestMapping("/employees") // controller class request mapping for servlet URLs
public class EmployeeController {

	Map<Integer, Employee> data;
	public EmployeeController() {
		
		// data flows into the MVC Spring application from the Controller class and gets displayed by JSP
		data = new HashMap<Integer,Employee>();
		
		data.put(100, new Employee(100,"Abhishek",23000));
		data.put(200, new Employee(200,"Birbal",34000));
		data.put(300, new Employee(300,"Charlie",13000));
		data.put(400, new Employee(400,"Deepak",14000));
		
	}
	
	@RequestMapping("{eid}") // so that URL Empid = 101 will automatically enter here
	public ModelAndView getEmployee(@PathVariable("eid") int empid) {
		
		ModelAndView mv = new ModelAndView("empdetails");
		
		Employee e = data.get(empid);
		
		if(e!=null) {
			return new ModelAndView("empdetails","emp",e);
			// RequestDispatcher handled by servlet
		}
		else {
			return new ModelAndView("response","msg","Employee with id"+empid+"not found");
		}
	}
	
	@RequestMapping
	public ModelAndView getAllEmployees() {
		Collection<Employee> col = data.values();
		return new ModelAndView("emplist","emps",col);
	}
	
	@RequestMapping("save")
	public ModelAndView saveEmployee(@RequestParam("empid") int empid, ("name") String name, ("salary") float salary) {
		//RequestParam as now we have to not access the empid from the url as path variable to fetch data but actually input the params in the getForm link
		
		if(data.containsKey(empid)) {
			return new ModelAndView ("response","msg","Employee with empid"+empid+"already exists!");
		}
		else {
			data.put(empid, new Employee(empid,name,salary));
			return new ModelAndView("response","msg","Employee with empid"+empid+"saved successfully");
		}
	}
	
	@RequestMapping("delete/{eid}") // URL mapping -> /employees/delete/100
	public ModelAndView deleteEmployee(@PathVariable("eid") int empid) {
		
		ModelAndView mv = new ModelAndView("response");
				
		if(data.containsKey(empid)) {
			data.remove(empid);
			mv.addObject("msg","Employee with id "+empid+"successfully deleted!");
		}
		else {
			mv.addObject("msg","Delete failed! Employee with id"+empid+"not found");
		}
		return mv;
	}
	

}
