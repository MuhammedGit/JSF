
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.faces.bean.ManagedBean;
import com.google.gson.*;
import com.google.gson.JsonParser;
@ManagedBean
public class JsonGet {
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String purl="";
	public String rurl;
	private String SendGet;
	String Title;
	private String imdbID;
	private String imdb_title;
	private String imdb_genre;
	private String imdb_plot;
	private String imdb_poster;
	private String imdb_rating;
	   
	

	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}

	public String getImdb_title() {
		return imdb_title;
	}

	public void setImdb_title(String imdb_title) {
		this.imdb_title = imdb_title;
	}

	public String getImdb_genre() {
		return imdb_genre;
	}

	public void setImdb_genre(String imdb_genre) {
		this.imdb_genre = imdb_genre;
	}

	public String getImdb_plot() {

		System.out.println("getteed plot" + imdb_plot);
		return imdb_plot;
	}

	public void setImdb_plot(String imdb_plot) {
		this.imdb_plot = imdb_plot;
		System.out.println("setted plot" + imdb_plot);
	}

	public String getImdb_poster() {
		return imdb_poster;
	}

	public void setImdb_poster(String imdb_poster) {
		this.imdb_poster = imdb_poster;
	}

	public String getImdb_rating() {
		return imdb_rating;
	}

	public void setImdb_rating(String imdb_rating) {
		this.imdb_rating = imdb_rating;
	}

	public String getPurl() {
		return purl;
	}

	public void setPurl(String purl) {
		this.purl = purl;
	}



	public void sendGet() {
		if(purl==""){}else{
		rurl="http://www.omdbapi.com/?t="+purl+"&y=&plot=short&r=json";
		try{
		URL url = new URL(rurl);
		  System.out.println("url"+rurl);
	    HttpURLConnection request = (HttpURLConnection) url.openConnection();
	    request.connect();

	    // Convert to a JSON object to print data
	    JsonParser jp = new JsonParser(); //from gson
	    JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
	    JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object. 
	    String str=rootobj.toString();
	    imdb_plot = rootobj.get("Plot").getAsString();
	    imdb_title = rootobj.get("Title").getAsString();
	    imdb_genre = rootobj.get("Genre").getAsString();
	    imdb_rating = rootobj.get("imdbRating").getAsString();
	    imdbID = rootobj.get("imdbID").getAsString();
	    System.out.println(imdbID+" "+imdb_genre+" "+imdb_title+ str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
	    //return Title;
		}
	}

	@Override
	public String toString() {
		return "JsonGet [purl=" + purl + ", rurl=" + rurl + ", SendGet=" + SendGet + ", Title=" + Title + ", imdbID="
				+ imdbID + ", imdb_title=" + imdb_title + ", imdb_genre=" + imdb_genre + ", imdb_plot=" + imdb_plot
				+ ", imdb_poster=" + imdb_poster + ", imdb_rating=" + imdb_rating + "]";
	}
}
