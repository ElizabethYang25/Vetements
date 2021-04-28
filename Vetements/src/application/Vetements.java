package application;

public class Vetements 
{
	private String nom;
	private String type;
	private double prix;
	private double quantity;
	
	//constructer vide
	public Vetements()
	{
		this(null);
	}
	
	//contructeur avec 2 paramètre
	public Vetements(String nom)
	{
		this.nom=nom;
		this.type="";
		this.prix=0.0;
		this.quantity=0.0;
			
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
		
}



