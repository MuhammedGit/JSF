import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class SeriesController {

	private List<Series> series;
	private SeriesDbUtil seriesDbUtil;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	public void setSeries(List<Series> series) {
		this.series = series;
	}
	public void setSeriesDbUtil(SeriesDbUtil seriesDbUtil) {
		this.seriesDbUtil = seriesDbUtil;
	}
	public List<Series> getSeries(){
		return series;
	}
	public SeriesController() throws Exception{
		
		this.series = new ArrayList<>();
		this.seriesDbUtil = SeriesDbUtil.getInstance();
	}
	public void loadSeries(){
		series.clear();
		try {
			series=seriesDbUtil.loadSeries();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Series sendGet(Series serie) {
		logger.info("getting a serie from omdb: " + serie);
		try {
			serie= serie.sendGet(serie);
			
			
		} catch (Exception e){
			e.printStackTrace();
		}finally{
			
		}
		logger.info("getted a serie from omdb: " + serie);
		return serie;
	}
	public String addSerie(Series serie){
		logger.info("Adding serie: " + serie);
		try {
			serie.toString();
			seriesDbUtil.addSerie(serie);
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("added serie: " + serie);
		return "myseries?faces-redirect=true";
		return "";
	}
	public void getSerie(String ser){
		try {
			serie=seriesDbUtil.getSerie(ser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
