package main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import main.bean.Student;
import main.dao.StudentDao;

@Controller
public class StudentController {
	
	//StudentDao class object
	@Autowired
	StudentDao studentDao;
	
	//Student form
	@RequestMapping("/studentform")  
    public String showform(Model m){  
    	m.addAttribute("command", new Student());
    	return "studentform";
	}
	
	// Data save method 
    @RequestMapping(value="/save1",method = RequestMethod.POST)  
    public String save(@ModelAttribute("studentDao") Student student){  
    	studentDao.save(student);  
        return "redirect:/viewstudent";
    }
    
    //Data show method
    @RequestMapping("/viewstudent")  
    public String viewemp(Model m){  
        List<Student> list = studentDao.getStudent();  
        m.addAttribute("list",list);
        return "viewstudent";  
    }  

    
    @RequestMapping(value="/editstudent/{id}")  
    public String edit(@PathVariable int id, Model m){  
    	Student student=studentDao.getStudentById(id);  
        m.addAttribute("command",student);
        return "studenteditform";  
    }  
    
    //update data method
    @RequestMapping(value="/editsave",method = RequestMethod.POST)  
    public String editsave(@ModelAttribute("student") Student student){  
    	studentDao.update(student);  
        return "redirect:/viewstudent";  
    }  
   
    // fatch data { id }
    @RequestMapping(value="/deletestudent/{id}",method = RequestMethod.GET)  
    public String delete(@PathVariable int id){  
    	studentDao.delete(id);  
        return "redirect:/viewstudent";  
    }   
    
    
}
