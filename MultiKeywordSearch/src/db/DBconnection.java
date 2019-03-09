/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 *
 * @author SAJAN
 */



import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBconnection {

	public static Connection getConnection() {
		
		Connection con = null;
		try {
                    Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/enablingkeyword_search","root","root");
            
            if(con==null)
            {
                System.out.println("connection can not be established");
            }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
