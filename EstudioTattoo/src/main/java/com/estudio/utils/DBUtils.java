package com.estudio.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBUtils {
	private static Logger loger = LoggerFactory.getLogger(DBUtils.class);
	private static SessionFactory sesionFactory;
	
	public static SessionFactory creadorSessionFactory() {
		
		if (sesionFactory == null) {
			sesionFactory = new Configuration().configure().buildSessionFactory();
		}
		return sesionFactory;
	}
	public static Connection conectarBBDD() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String urlDB = "jdbc:mysql://localhost:3306/tiendaTattoos?serverTimezone=UTC";
		String user = "root";
		String pass = "PracticaRoot";
		Connection connection = DriverManager.getConnection(urlDB, user, pass);
		loger.info("Establecida la conexión para la BBDD");
		return connection;
	}

}
