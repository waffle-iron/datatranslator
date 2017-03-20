package data;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import org.codehaus.jackson.map.ObjectMapper;

import data.city.City;
import data.city.Results;
import data.city.State;
import data.citySet.CitySet;
import data.citySet.Result;

//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import com.journaldev.jackson.model.Address;
//import com.journaldev.jackson.model.Employee;


public class JacksonObjectMapper
{
	public static String call(String uRL)
	{
		String json = "";
		
		try 
		{
			URL url = new URL(uRL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) 
			{
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
			
			String output = "";
			
			while ((output = br.readLine()) != null) {
				json = json.concat(output);
			}
			
			conn.disconnect();
		} 
		  
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	
		return json;
	}
	
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		// create csv file and write headers
		String csvFile = "pm25.csv";
        FileWriter writer = new FileWriter(csvFile);
        CSVUtils.writeLine(writer,
        	Arrays.asList("utc_date", "local_date", "country", "city", "longitude", "latitude", "location", "parameter", "value", "unit"));
        
        //get list of all city names in us
		String json = call("https://api.openaq.org/v1/cities?country=US&limit=10000");

		ObjectMapper objectMapper = new ObjectMapper();
		CitySet cs = objectMapper.readValue(json, CitySet.class);
		
		Iterator<Result> results = cs.getResults().iterator();
		
		List<String> cities = new ArrayList<String>();

		
	    while (results.hasNext()) 
	    {
	        Result result = results.next();
	        
	        cities.add(result.getCity());
	    }
	    
//	    String cityString = "";
	    // go through list of cities and find out if they are in NC
//	    int cnt = 0;
		for (String city : cities) 
		{		
			if (city.equals("BIG HORN") ||
				city.equals("Hartford-West Hartford-East Hartford") ||
				city.equals("Holland-Grand Haven") ||
				city.equals("Killeen-Temple-Fort Hood") ||
				city.equals("Kokomo") ||
				city.equals("Lake Havasu City-Kingman") ||
				city.equals("New Haven-Milford") ||
				city.equals("Niles-Benton Harbor") ||
				city.equals("Panama City-Lynn Haven") ||
				city.equals("Terre Haute")
				) continue; // for some reason these cities give a 400 error code back
			
//			System.out.println("making http call for city: " + city);
	        String stateJson = call("https://api.openaq.org/v1/locations?country=US&city=" + city);
	        
	        ObjectMapper stateObjectMapper = new ObjectMapper();
	        
	        City cr = stateObjectMapper.readValue(stateJson, City.class);
	        
	        Iterator<Results> rs = cr.getResults().iterator();
	        
		    while (rs.hasNext()) 
		    {
		    	Results r = rs.next();
		    	String loc = r.getLocation();
		    	
		    	String state = call("http://data.fcc.gov/api/block/find?format=json&latitude="
		    	+r.getCoordinates().getLatitude()+"&longitude="+r.getCoordinates().getLongitude()+"&showall=true");
		    	
		    	ObjectMapper om = new ObjectMapper();
		    	
		    	State st= om.readValue(state.toLowerCase(), State.class);
		    	
//		    	cnt++;
//		    	System.out.println(cnt + " " + st.getState().get("code"));
		    	
		    	// now get air quality info for this city, since it is in NC
		    	if(st.getState().get("code").equals("nc"))
		    	{
		    		//System.out.println("https://api.openaq.org/v1/measurements?format=csv&country=US&city=" + city);
		    		// also need to query for location, since there can be multiple locations in one city
		    		String pm25_measurments = call("https://api.openaq.org/v1/measurements?limit=100000&parameter=pm25&country=US&city=" + city + "&location=" + loc);
//		    		String O3_measurments = call("https://api.openaq.org/v1/measurements?limit=100000&parameter=O3&country=US&city=" + city);
		    		//System.out.println(pm25_measurments);
		    		JSONObject jsonObj = new JSONObject(pm25_measurments.toString());
		    		
		    		// if there are any air quality results associated with this city
		    		// save them to the CSV file
		    		JSONObject meta = (JSONObject) jsonObj.get("meta");
		    		String fnd = meta.get("found").toString();
		    		int found = Integer.parseInt(fnd);
		    		if (found > 0) {
		    			JSONArray resultSet = (JSONArray) jsonObj.get("results");
		    			for (int i=0; i<resultSet.length(); i++) {
//		    				System.out.println(((JSONObject) resultSet.get(i)).toString());
		    				CSVUtils.writeLine(writer,
				    			Arrays.asList(((JSONObject)((JSONObject)resultSet.get(i)).get("date")).get("utc").toString(),
				    						  ((JSONObject)((JSONObject)resultSet.get(i)).get("date")).get("local").toString(),
				    						  ((JSONObject)resultSet.get(i)).get("country").toString(),
				    						  ((JSONObject)resultSet.get(i)).get("city").toString(),
				    						  ((JSONObject)((JSONObject)resultSet.get(i)).get("coordinates")).get("longitude").toString(),
				    						  ((JSONObject)((JSONObject)resultSet.get(i)).get("coordinates")).get("latitude").toString(),
				    						  ((JSONObject)resultSet.get(i)).get("location").toString(),
				    						  ((JSONObject)resultSet.get(i)).get("parameter").toString(),
				    						  ((JSONObject)resultSet.get(i)).get("value").toString(),
				    						  ((JSONObject)resultSet.get(i)).get("unit").toString()
				    					));
		    			}
//		    			System.out.println(pm25_measurments);
		    		}
		    	}
		    }
		}
		writer.flush();
        writer.close();
	}
}
