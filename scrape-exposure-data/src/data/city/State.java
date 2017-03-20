package data.city;

import java.util.Map;

public class State 
{	
	private Map<String, String> block;
	private Map<String, String> county;
	private Map<String, String> state;
	private String status;
	private int executiontime;
	
	public Map<String, String> getBlock() 
	{
		return block;
	}
	
	public void setBlock(Map<String, String> block) 
	{
		this.block = block;
	}
	
	public Map<String, String> getCounty() 
	{
		return county;
	}
	
	public void setCounty(Map<String, String> county) 
	{
		this.county = county;
	}
	
	public Map<String, String> getState() 
	{
		return state;
	}
	
	public void setState(Map<String, String> state) 
	{
		this.state = state;
	}
	
	public String getStatus() 
	{
		return status;
	}
	
	public void setStatus(String status) 
	{
		this.status = status;
	}
	
	public int getExecutiontime() 
	{
		return executiontime;
	}
	
	public void setExecutiontime(int executionTime) 
	{
		this.executiontime = executionTime;
	}
	
}