package guerreros;

import excepciones.ExceedsStatsLimits;

public abstract class Guerrero {
	private String nombre;
	private String tipo;
	private int fuerza;
	private int resistencia;
	public Guerrero(String nombre, String tipo, int fuerza, int resistencia) throws ExceedsStatsLimits{
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.fuerza = fuerza;
		this.resistencia = resistencia;
		if (this.fuerza+this.resistencia>10) {
			ExceedsStatsLimits esl = new ExceedsStatsLimits("La suma del ataque y defensa de un guerrero no puede sobrepasar 10");
			throw esl;
		}
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getFuerza() {
		return fuerza;
	}
	public int getResistencia() {
		return resistencia;
	}
	@Override
	public String toString() {
		return "Guerrero [NOMBRE="+nombre+", TIPO="+tipo+", FRZ="+fuerza+", RES="+resistencia+"]";
	}
	
}
