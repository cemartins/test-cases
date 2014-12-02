package com.audaxys.test.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StopHsqldb {

	public static void main(String[] args) {

		try {
		Class.forName("org.hsqldb.jdbcDriver");
        String url = "jdbc:hsqldb:hsql://localhost/sandboxDb";
        Connection con = DriverManager.getConnection(url, "sa", "");
        String sql = "SHUTDOWN";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
		}
		catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
