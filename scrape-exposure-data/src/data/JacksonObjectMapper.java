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
		// check for exposure type arg - for now only except pm25 and o3
		if ((args.length < 1) || (!args[0].equals("pm25") && !args[0].equals("o3"))){
			System.out.println("Usage: java -jar scrape-exposure-data.jar <'pm25' or 'o3'>");
			System.exit(1);
		}
		String exposureType = args[0];
		
		// create csv file and write headers
		String csvFile = exposureType + ".csv";
        FileWriter writer = new FileWriter(csvFile);
        CSVUtils.writeLine(writer,
        	Arrays.asList("utc_date", "local_date", "country", "city", "longitude", "latitude", "location", "parameter", "value", "unit"));
        
        System.out.println("Collecting exposure data for type: " + exposureType + " ...");
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
			
			String encodedCity = city.replaceAll(" ", "%20");
//			System.out.println("making http call for city: " + city);
	        String stateJson = call("https://api.openaq.org/v1/locations?country=US&city=" + encodedCity);
	        
	        ObjectMapper stateObjectMapper = new ObjectMapper();
	        
	        City cr = stateObjectMapper.readValue(stateJson, City.class);
	        
	        Iterator<Results> rs = cr.getResults().iterator();
	        
		    while (rs.hasNext()) 
		    {
		    	Results r = rs.next();
		    	String loc = r.getLocation().replaceAll(" ", "%20"); // need to encode spaces
		    	
		    	String state = call("http://data.fcc.gov/api/block/find?format=json&latitude="
		    	+r.getCoordinates().getLatitude()+"&longitude="+r.getCoordinates().getLongitude()+"&showall=true");
		    	
		    	ObjectMapper om = new ObjectMapper();
		    	
		    	State st= om.readValue(state.toLowerCase(), State.class);
		    	
//		    	cnt++;
//		    	System.out.println(cnt + " " + st.getState().get("code"));
		    	
		    	// now get air quality info for this city, since it is in NC
		    	if(st.getState().get("code").equals("nc"))
		    	{
		    		// also need to query for location, since there can be multiple locations in one city
		    		//System.out.println("making http call for city: " + city + "  loc: " + loc);
		    		String exp_measurments = call("https://api.openaq.org/v1/measurements?limit=100000&country=US&parameter=" + exposureType + "&city=" + encodedCity + "&location=" + loc);

		    		//System.out.println(exp_measurments);
		    		JSONObject jsonObj = new JSONObject(exp_measurments.toString());
		    		
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
//		    			System.out.println(exp_measurments);
		    		}
		    	}
		    }
		}
		writer.flush();
        writer.close();
        System.out.println("Done!");
	}
}
