package data.city;

import java.util.List;

//"location": "Yuba City",
//"city": "Yuba City",
//"country": "US",
//"count": 26006,
//"sourceNames": ["AirNow"],
//"lastUpdated": "2017-02-02T13:00:00.000Z",
//"firstUpdated": "2016-03-06T19:00:00.000Z",
//"parameters": ["no2", "o3", "pm10", "pm25"],
//"sourceName": "AirNow",
//"coordinates": {
//	"latitude": 39.1389,
//	"longitude": -121.6175
//}

public class Results 
{	
	private String city;
	private String country;
	private String location;
	private int count;
	private String[] sourceNames;
	private String sourceName;
	private String lastUpdated;
	private String firstUpdated;
	private String[] parameters;
	
	private Coordinates coordinates;
	
	public String getFirstUpdated() 
	{
		return firstUpdated;
	}
	
	public void setFirstUpdated(String firstUpdated) 
	{
		this.firstUpdated = firstUpdated;
	}
	
	public String getLastUpdated() 
	{
		return lastUpdated;
	}
	
	public void setLastUpdated(String lastUpdated) 
	{
		this.lastUpdated = lastUpdated;
	}
	
	public String[] getParameters() 
	{
		return parameters;
	}
	
	public void setParameters(String[] parameters) 
	{
		this.parameters = parameters;
	}
	
	public String getSourceName() 
	{
		return sourceName;
	}
	
	public void setSourceName(String sourceName) 
	{
		this.sourceName = sourceName;
	}
	
	public String[] getSourceNames() 
	{
		return sourceNames;
	}
	
	public void setSourceNames(String[] sourceNames) 
	{
		this.sourceNames = sourceNames;
	}
	
	public String getCity() 
	{
		return city;
	}
	
	public void setCity(String city) 
	{
		this.city = city;
	}
	public String getCountry() 
	{
		return country;
	}
	
	public void setCountry(String country) 
	{
		this.country = country;
	}
	
	public String getLocation() 
	{
		return location;
	}
	
	public void setLocation(String location) 
	{
		this.location = location;
	}
	
	public int getCount() 
	{
		return count;
	}
	
	public void setCount(int count) 
	{
		this.count = count;
	}

	public Coordinates getCoordinates() 
	{
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) 
	{
		this.coordinates = coordinates;
	}
}