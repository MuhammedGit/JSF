import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class SeriesDbUtil {

	private static SeriesDbUtil instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/series";
	
	public static SeriesDbUtil getInstance() throws Exception {
		if (instance== null){
			instance= new SeriesDbUtil();
		}
		return instance;
	}

	public SeriesDbUtil() throws Exception{
		dataSource = getDataSource();
	}
	
	public DataSource getDataSource() throws Exception {
		Context context= new InitialContext();
		DataSource ds= (DataSource)context.lookup(jndiName);
		return ds;
	}

	private Connection getConnection() throws Exception{
		Connection connection = dataSource.getConnection();
		return connection;
	}

	public List<Series> loadSeries() throws Exception {
		List <Series> series = new ArrayList<>();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try{
			myConn= getConnection();
			String sql="Select * from series";
			myStmt = myConn.createStatement();
			myRs=myStmt.executeQuery(sql);
			while(myRs.next()){
				String serie_genre= myRs.getString("serie_genre");
				String serie_plot= myRs.getString("serie_plot");
				String serie_poster= myRs.getString("serie_poster");
				String serie_title = myRs.getString("serie_title").toLowerCase();
				String serie_rating = myRs.getString("serie_rating");
				String imdbID = myRs.getString("imdbID");
				Series ser= new Series(imdbID,serie_title,serie_genre,serie_plot,serie_poster, serie_rating);
				series.add(ser);
				
			}
			
			
		}finally{
			myConn.close();
			myStmt.close();
			myRs.close();
		}
		return series; 
			
	}
	public void addSerie(Series serie) throws SQLException{
		Connection myConn=null;
		PreparedStatement myStmt=null;
		try {
			myConn= getConnection();
			String sql="insert into series (imdbID, serie_title, serie_genre, serie_plot, serie_rating) VALUES (?, ?, ?, ?, ?)";
			myStmt=myConn.prepareStatement(sql);
			myStmt.setString(1,serie.getImdbID());
			myStmt.setString(2,serie.getSerie_title());
			myStmt.setString(3,serie.getSerie_genre());
			myStmt.setString(4,serie.getSerie_plot());
			myStmt.setString(5,serie.getSerie_rating());
			myStmt.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			myConn.close();
			myStmt.close();
			//myRs.close();
		}
	}
	public Series getSerie(String se) throws Exception {
		System.out.println(se);
		Series ser = null;
		String imdb_serie=se;
		//List <Series> series = new ArrayList<>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try{
			myConn= getConnection();
			String sql="Select * from imdb where imdbID=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, imdb_serie);
			myRs=myStmt.executeQuery(sql);
			while(myRs.next()){
				String imdb_genre = myRs.getString("imdb_genre").toLowerCase();
				String imdb_plot = myRs.getString("imdb_plot");
				System.out.println("genre" + imdb_genre);
				System.out.println(imdb_plot);
				ser= new Series(imdb_genre,imdb_plot);
				//series.add(ser);
			}
			
		}finally{
			myConn.close();
			myStmt.close();
			myRs.close();
		}
		return ser; 
			
	}
}
