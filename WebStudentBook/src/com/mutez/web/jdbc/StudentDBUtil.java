package com.mutez.web.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDBUtil {
	private DataSource dataSource;
	
	public StudentDBUtil(DataSource theDataSource) { 
		dataSource = theDataSource;
	}
	
	//on créer une fonction qui permet de retourner la liste de student a aprtir de la database
	public List<Student> getStudents() throws Exception {
		//on initialise la liste
		List<Student> students= new ArrayList<Student>();
		//on initialise une connexion avec Sql
		Connection myConn=null; 
		Statement myStmt = null;
		ResultSet myRs= null; 
		try {
			myConn = dataSource.getConnection();
			myStmt= myConn.createStatement();
			String sql= "select * from student order by last_name"; 
			myRs = myStmt.executeQuery(sql);
			while(myRs.next()){
				//on recupere les datas
				int id = myRs.getInt("id");
				String firstName=myRs.getString("first_name"); 
				String lastName=myRs.getString("last_name"); 
				String email = myRs.getString("email");
				//on stock le student
				Student tempStudent= new Student(id,firstName,lastName,email); 
				//on l'ajoute dans le tableau
				students.add(tempStudent);
			}
			//on retourne le tableau
			return students; 
		} finally{
			close(myConn,myStmt,myRs);
	             }
	}
	
	// permet de fermer la connexion
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try{
			if(myStmt!=null)
				myStmt.close();
			if(myRs!=null)
				myRs.close();
			if(myConn!=null)
				myConn.close(); 
			}catch(Exception e){
				System.out.println(e.getMessage()); }
	}
	
	//fonction pour ajouter un student
	public void AddDatabase(Student s){
        Connection myConn=null; 
        PreparedStatement myStmt = null;
        ResultSet myRs= null; 
        try 
        {
            myConn = dataSource.getConnection();
            String query = "insert into student (first_name,last_name,email) values (?,?,?);";
            myStmt = myConn.prepareStatement(query);
            //on dit a quoi correspond tous les ?
            myStmt.setString(1, s.getFirst_Name());
            myStmt.setString(2, s.getLast_Name());
            myStmt.setString(3, s.getEmail());
            //on execute la commande
            myStmt.execute();                
        }
        catch (SQLException e) 
        { 
            e.printStackTrace();
        }
        close(myConn,myStmt,myRs);
    }
	
	//permet de recupérer les attributs d'un student a partir de son id, dans la database
	//retourne le student en question
	public Student fetchStudent(int id) { 
		Connection myConn=null;
		Statement myStmt = null; 
		ResultSet myRs= null; 
		Student student=null;
		
		try {
			myConn = dataSource.getConnection();
			myStmt= myConn.createStatement();
			String sql= "select * from student where id="+id; 
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next()){
				String firstName=myRs.getString("first_name"); 
				String lastName=myRs.getString("last_name"); 
				String email = myRs.getString("email");
				
				student = new Student(id,firstName,lastName,email); 
			}
			
			return student;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null; 
		} finally{
			close(myConn,myStmt,myRs);
		}
	}
	
	//permet d'update un student
	public void updateStudent(Student student) { 
		Connection myConn=null;
		PreparedStatement myStmt = null; 
		try {
			myConn = dataSource.getConnection();
			String sql = "update student set first_name=?, last_name=?,email=? where id=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, student.getFirst_Name()); 
			myStmt.setString(2, student.getLast_Name()); 
			myStmt.setString(3, student.getEmail()); 
			myStmt.setInt(4,student.getId()); 
			myStmt.execute();
		}
		catch(Exception e){
			System.out.println(e.getMessage()); 
		}
		finally{ 
			close(myConn,myStmt,null);
		} 
	}
	
	public void deleteStudent(Student student) {
		Connection myConn=null; 
        PreparedStatement myStmt = null;
        ResultSet myRs= null; 
        try 
        {
            myConn = dataSource.getConnection();
            String query = "delete from student where id = ?;";
            myStmt = myConn.prepareStatement(query);
            
            myStmt.setInt(1, student.getId());
            myStmt.execute();                
        }
        catch (SQLException e) 
        { 
            e.printStackTrace();
        }
        close(myConn,myStmt,myRs);
	}

}
