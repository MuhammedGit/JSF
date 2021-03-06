import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.faces.bean.ManagedBean;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@ManagedBean
public class Series {
	private String imdbID;
	private String serie_title;
	private String serie_genre;
	private String serie_plot;
	private String serie_poster;
	private String serie_rating;
	private String purl="";
	public String rurl;


	public Series() {
		System.out.println("bos series");
	}

	public Series(String imdbID, String imdb_title, String imdb_genre, String imdb_plot, String imdb_poster,
			String imdb_rating) {

		this.imdbID = imdbID;
		this.serie_title = imdb_title;
		this.serie_genre = imdb_genre;
		this.serie_plot = imdb_plot;
		this.serie_poster = imdb_poster;
		this.serie_rating = imdb_rating;
		System.out.println("dolu series");
	}
	public Series(String series_title){
		this.serie_title=series_title;
	}
	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}


	public String getSerie_title() {
		System.out.println("get title: " + serie_title);
		return serie_title;
	}

	public void setSerie_title(String serie_title) {
		this.serie_title = serie_title;
	}

	public String getSerie_genre() {
		return serie_genre;
	}

	public void setSerie_genre(String serie_genre) {
		this.serie_genre = serie_genre;
	}

	public String getSerie_plot() {
		return serie_plot;
	}

	public void setSerie_plot(String serie_plot) {
		this.serie_plot = serie_plot;
	}

	public String getSerie_poster() {
		return serie_poster;
	}

	public void setSerie_poster(String serie_poster) {
		this.serie_poster = serie_poster;
	}

	public String getSerie_rating() {
		return serie_rating;
	}

	public void setSerie_rating(String serie_rating) {
		this.serie_rating = serie_rating;
	}

	public String getPurl() {
		System.out.println("get purl: " + purl);
		return purl;
	}

	public void setPurl(String purl) {
		System.out.println("set purl" + purl);
		this.purl = purl;
	}
	public Series sendGet(Series serie) {
		//Series jsonSerie = null;
		purl=trims(purl);
	if(purl!=""){
		System.out.println(purl+"this is purl");
		rurl="http://www.omdbapi.com/?t="+purl+"&y=&plot=full&r=json";
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
	    serie_plot = rootobj.get("Plot").getAsString();
	    serie_title = rootobj.get("Title").getAsString();
	    serie_genre = rootobj.get("Genre").getAsString();
	    serie_rating = rootobj.get("imdbRating").getAsString();
	    imdbID = rootobj.get("imdbID").getAsString();
	    serie_poster=purl;
	    serie= new Series(imdbID,  serie_title,  serie_genre,  serie_plot,  serie_poster, serie_rating);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{}
	    
		}
	else{
		System.out.println("Purl is empty");
	}
	//return jsonSerie;
	return serie;
	}
	public String trims(String purl){
		String purltrimmed= purl.replaceAll("\\s+","+");
		return purltrimmed;
	}
	@Override
	public String toString() {
		return "Series [imdbID=" + imdbID + ", serie_title=" + serie_title + ", serie_genre=" + serie_genre + ", serie_plot="
				+ serie_plot + ", serie_poster=" + purl + ", serie_rating=" + serie_rating + "]";
	}

}
