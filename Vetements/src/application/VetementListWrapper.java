package application;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="vetements")
public class VetementListWrapper 
{
	private List<Vetements> vetements;
	@XmlElement(name="vetements")
	public List<Vetements> getVetements()
	{
		return vetements;
	}
	public void setVetements(List<Vetements> vetements)
	{
		this.vetements=vetements;
	}
	
}