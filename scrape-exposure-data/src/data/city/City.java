package data.city;

import java.util.List;

import data.citySet.Meta;

public class City 
{	
	private List<Results> results;
	
	private Meta meta;
	
	public Meta getMeta() 
	{
		return meta;
	}

	public void setMeta(Meta meta) 
	{
		this.meta = meta;
	}

	public List<Results> getResults() 
	{
		return results;
	}
	
	public void setResults(List<Results> results) 
	{
		this.results = results;
	}
}