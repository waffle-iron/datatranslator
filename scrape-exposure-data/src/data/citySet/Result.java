package data.citySet;

//"city": "ABBEVILLE",
//"country": "US",
//"locations": 1,
//"count": 4619 

public class Result 
{	
	private String city;
	private String country;
	private int locations;
	private int count;
	
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
	
	public int getLocations() 
	{
		return locations;
	}
	
	public void setLocations(int locations) 
	{
		this.locations = locations;
	}
	
	public int getCount() 
	{
		return count;
	}
	
	public void setCount(int count) 
	{
		this.count = count;
	}
	
//	@Override
//	public String toString(){
//		return getStreet() + ", "+getCity()+", "+getZipcode();
//	}
}