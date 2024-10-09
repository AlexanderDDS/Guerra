package vehiculos;
import java.util.ArrayList;
import java.util.List;

import excepciones.ExceedsStatsLimits;
import excepciones.ExceedsVehicleCapacityException;
import guerreros.Guerrero;
import utils.ConsoleColors;

public abstract class VehiculoGuerra implements Tripulable{
	private int puntosVida;
	private int ataque;
	private int defensa;
	private String nombre;
	private List<Guerrero> guerreros;
	public VehiculoGuerra(String nombre){
		super();
		this.puntosVida = 1000;
		this.ataque = 5;
		this.defensa = 5;
		this.nombre = nombre;
		this.guerreros = new ArrayList();
	}
	public VehiculoGuerra(String nombre, int ataque, int defensa) throws ExceedsStatsLimits{
		super();
		this.puntosVida = 1000;
		this.ataque = ataque;
		this.defensa = defensa;
		if (this.ataque+this.defensa>10) {
			ExceedsStatsLimits esl = new ExceedsStatsLimits("La suma del ataque y defensa de un vehículo no puede sobrepasar 10");
			throw esl;
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
		System.out.println(this.nombre+" le quedan "+this.puntosVida+" puntos de vida");
	}
	/**
	Método que recibe objetos de tipo Guerrero y los introduce en este objeto tipo de tipo VehiculoGuerra
	@param Guerrero... guerreros
	*/
	public final void embarcar(Guerrero... guerreros) throws ExceedsVehicleCapacityException{
		for (Guerrero guerrero : guerreros) {
			System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT+guerrero.getNombre()+" está embarcando el vehículo "+this.nombre+ConsoleColors.RESET);
			this.guerreros.add(guerrero);
			if (this.guerreros.size()>10) {
				ExceedsVehicleCapacityException evce = new ExceedsVehicleCapacityException("No pueden haber más de 10 guerreros en un vehículo");
				throw evce;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException ie) {
				ie.getMessage();
			}
		}
	}
}
