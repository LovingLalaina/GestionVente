package modele;

import java.util.Calendar;

public class Calendrier
{
	private int jourDuMois;
	private int mois;
	private int annee;
	
	public Calendrier()
	{
		Calendar aujourdhui = Calendar.getInstance();
		this.jourDuMois = aujourdhui.get( Calendar.DAY_OF_MONTH );
		this.mois = aujourdhui.get( Calendar.MONTH ) + 1;
		this.annee = aujourdhui.get( Calendar.YEAR );
	}
	
	public String afficherSurInput()
	{
		String Annee = Integer.toString( this.annee );
		String Mois = ( this.mois < 10 ) ? "0" + Integer.toString( this.mois ) : Integer.toString( this.mois );
		String JourDuMois = ( this.jourDuMois < 10 ) ? "0" + Integer.toString( this.jourDuMois ) : Integer.toString( this.jourDuMois );
		return Annee + "-" + Mois + "-" + JourDuMois;
	}
	
	public int getAnnee()
	{
		return this.annee;
	}
	
	public int getMois()
	{
		return this.mois;
	}
}
