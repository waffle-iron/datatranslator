package data.citySet;

//{
//	"meta": {
//		"name": "openaq-api",
//		"license": "CC BY 4.0",
//		"website": "https://docs.openaq.org/",
//		"page": 1,
//		"limit": 10000,
//		"found": 706
//	},
//	"results": [{
//		"city": "ABBEVILLE",
//		"country": "US",
//		"locations": 1,
//		"count": 4619
//	}, 

public class Meta 
{	
	private String name;
	private String license;
	private String website;
	private int page;
	private int limit;
	private int found;
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getLicense() 
	{
		return license;
	}
	
	public void setLicense(String license) 
	{
		this.license = license;
	}
	
	public String getWebsite() 
	{
		return website;
	}
	
	public void setWebsite(String website) 
	{
		this.website = website;
	}
	
	public int getPage() 
	{
		return page;
	}
	
	public void setPage(int page) 
	{
		this.page = page;
	}
	
	public int getLimit() 
	{
		return limit;
	}
	
	public void setLimit(int limit) 
	{
		this.limit = limit;
	}
	
	public int getFound() 
	{
		return found;
	}
	
	public void setFound(int found) 
	{
		this.found = found;
	}
	
//	@Override
//	public String toString(){
//		return getStreet() + ", "+getCity()+", "+getZipcode();
//	}
}