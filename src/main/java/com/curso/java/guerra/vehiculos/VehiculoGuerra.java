package com.curso.java.guerra.vehiculos;
import java.util.ArrayList;
import java.util.List;

import com.curso.java.guerra.excepciones.ExceedsStatsLimits;
import com.curso.java.guerra.excepciones.ExceedsVehicleCapacityException;
import com.curso.java.guerra.guerreros.Guerrero;
import com.curso.java.guerra.interfaces.Tripulable;
import com.curso.java.guerra.utils.ConsoleColors;

public abstract class VehiculoGuerra implements Tripulable{
	private int puntosVida = 1000;
	private int ataque;
	private int defensa;
	private String nombre;
	private List<Guerrero> guerreros;
	/**
	Constructor que necesita un String nombre para crear un objeto de tipo VehiculoGuerra con 1000 puntos de vida, 5 ataque y 5 defensa
	@param String nombre
	@return VehiculoGuerra
	@throws ExceedsStatsLimits
	*/
	public VehiculoGuerra(String nombre) throws ExceedsStatsLimits{
		this(nombre, 5, 5);
		this.guerreros = new ArrayList();
	}
	/**
	Constructor que necesita un String nombre, int ataque e int defensa para crear un objeto de tipo VehiculoGuerra con 1000 puntos de vida
	@param String nombre, int ataque, int defensa
	@return VehiculoGuerra
	@throws ExceedsStatsLimits
	*/
	public VehiculoGuerra(String nombre, int ataque, int defensa) throws ExceedsStatsLimits{
		super();
		this.ataque = ataque;
		this.defensa = defensa;
		if (this.ataque+this.defensa>10) {
			throw new ExceedsStatsLimits("La suma del ataque y defensa de un vehículo no puede sobrepasar 10");
		}
		this.nombre = nombre;
		this.guerreros = new ArrayList();
	}
	public int getPuntosVida() {
		return puntosVida;
	}
	public int getAtaque() {
		return ataque;
	}
	public int getDefensa() {
		return defensa;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Guerrero> getGuerreros() {
		return guerreros;
	}
	@Override
	public String toString() {
		return "VehiculoGuerra [HP="+puntosVida+", ATK="+ataque+", DEF="+defensa+", NAME="+nombre+", CREW="+guerreros+"]";
	}
	/**
	Método que devuelve el ataque total de este objeto de tipo VehiculoGuerra
	@return int
	*/
	@Override
	public final int atacar() {
		int sumaFuerzaGuerreros = 0;
		for (Guerrero guerrero : this.guerreros) {
			sumaFuerzaGuerreros+=guerrero.getFuerza();
		}
		int ataqueVehiculo = (int)(this.ataque*Math.random()) + (int)(sumaFuerzaGuerreros*Math.random()/2);
		System.out.print(this.nombre+" ataca con "+ataqueVehiculo+" puntos de daño. ");
		return ataqueVehiculo;
	}
	/**
	Método que requiere un dato de tipo int cuyo valor sea los puntos de ataque de un enemigo, calcula los puntos de defensa de este objeto de tipo VehiculoGuerra, compara ambos valores y le quita la cantidad de puntos de vida correspondientes a ete objeto
	@param int ataqueEnemigo
	*/
	@Override
	public final void defender(int ataqueEnemigo) {
		int sumaResistenciaGuerreros = 0, puntosVidaPerdidos = 0;
		for (Guerrero guerrero : this.guerreros) {
			sumaResistenciaGuerreros+=guerrero.getResistencia();
		}
		int defensaVehiculo = (int)(this.defensa*Math.random()) + (int)(sumaResistenciaGuerreros*Math.random()/2);
		if (ataqueEnemigo>defensaVehiculo) {
			puntosVidaPerdidos = ataqueEnemigo-defensaVehiculo;
		}
		this.puntosVida -= puntosVidaPerdidos;
		System.out.println(this.nombre+" tiene una defensa de "+defensaVehiculo+" puntos, y pierde "+puntosVidaPerdidos+" puntos de vida");
		mostrarHp();
	}
	private void mostrarHp() {
		System.out.print(this.nombre+"= "+ConsoleColors.RED_BACKGROUND+ConsoleColors.RED_BOLD_BRIGHT+"|");
		for (int i=0 ; i<=this.puntosVida; i+=50) {
			System.out.print("_|");
		}
		System.out.print(ConsoleColors.RESET);
		System.out.println(this.puntosVida+"HP");
	}
	/**
	Método que recibe objetos de tipo Guerrero y los introduce en este objeto tipo de tipo VehiculoGuerra
	@param Guerrero... guerreros
	@throws ExceedsVehicleCapacityException 
	*/
	public final void embarcar(Guerrero... guerreros) throws ExceedsVehicleCapacityException{
		for (Guerrero guerrero : guerreros) {
			if (this.guerreros.size()>=10) {
				ExceedsVehicleCapacityException evce = new ExceedsVehicleCapacityException("No pueden haber más de 10 guerreros en un vehículo");
				throw evce;
			}else {
				System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT+guerrero.getNombre()+" está embarcando el vehículo "+this.nombre+ConsoleColors.RESET);
				this.guerreros.add(guerrero);
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException ie) {
				ie.getMessage();
			}
		}
	}
	/**
	Método que recibe un objeto de tipo Guerrero y lo introduce en este objeto tipo de tipo VehiculoGuerra
	@param Guerrero guerrero
	@throws ExceedsVehicleCapacityException 
	*/
	public final void embarcar(Guerrero guerrero) throws ExceedsVehicleCapacityException{
		if (this.guerreros.size()>=10) {
			ExceedsVehicleCapacityException evce = new ExceedsVehicleCapacityException("No pueden haber más de 10 guerreros en un vehículo");
			throw evce;
		}else {
			System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT+guerrero.getNombre()+" está embarcando el vehículo "+this.nombre+ConsoleColors.RESET);
			this.guerreros.add(guerrero);
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException ie) {
			ie.getMessage();
		}
	}
}
