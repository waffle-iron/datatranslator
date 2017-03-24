package data.citySet;

import java.util.List;

public class CitySet 
{	
	private List<Result> results;
	
	private Meta meta;
	
	public Meta getMeta() 
	{
		return meta;
	}

	public void setMeta(Meta meta) 
	{
		this.meta = meta;
	}

	public List<Result> getResults() 
	{
		return results;
	}
	
	public void setResults(List<Result> results) 
	{
		this.results = results;
	}
}