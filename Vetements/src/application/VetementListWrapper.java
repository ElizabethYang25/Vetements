package application;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="vetements")
public class VetementListWrapper 
{
	private List<Vetements> vetements;
	@XmlElement(name="vetements")
	public List<Vetements> getEtudiants()
	{
		return vetements;
	}
	public void setEtudiants(List<Vetements> etudiants)
	{
		this.vetements=etudiants;
	}
	
}
