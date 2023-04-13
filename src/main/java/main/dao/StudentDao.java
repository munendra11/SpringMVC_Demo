package main.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


import main.bean.Student;

public class StudentDao {
	
	JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {  
	    this.template = template; 
	}
	
	//insert data method 
	public int save(Student s) {
		String sql = "insert into stu99(name,address,phone) values('"+s.getName()+"','"+s.getAddress()+"','"+s.getPhone()+"')";
		return template.update(sql);
	}
	    
	  
	
	// all data fatch in database
	public List<Student> getStudent(){   
	    return template.query("select * from stu99",new RowMapper<Student>(){  
	        public Student mapRow(ResultSet rs, int row) throws SQLException {  
	        	Student s = new Student(); 
	        	s.setId(rs.getInt(1));
	        	s.setName(rs.getString(2));
	        	s.setAddress(rs.getString(3));
	        	s.setPhone(rs.getString(4));
	            return s;  
	        }  
	    });  }

	
	public int delete(int id){  
	    String sql="delete from stu99 where id="+id+"";  
	    return template.update(sql);  
	}
	
	public Student getStudentById(int id){  
	    String sql="select * from stu99 where id=?";  
	    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Student>(Student.class));  
	}
	public int update(Student s){  
	    String sql="update stu99 set name ='"+s.getName()+"', address ='"+s.getAddress()+"',phone ='"+s.getPhone()+"' where id="+s.getId()+"";  
	    return template.update(sql);  
	}  
	


}
