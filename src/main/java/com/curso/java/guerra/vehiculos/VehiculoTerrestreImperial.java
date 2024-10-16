package com.curso.java.guerra.vehiculos;

import com.curso.java.guerra.excepciones.ExceedsStatsLimits;

public class VehiculoTerrestreImperial extends VehiculoGuerra{
	
	public VehiculoTerrestreImperial(String nombre) throws ExceedsStatsLimits {
		super(nombre);
	}

	public VehiculoTerrestreImperial(String nombre, int ataque, int defensa) throws ExceedsStatsLimits {
		super(nombre, ataque, defensa);
	}
	
	@Override
	public String toString() {
		return "VehiculoTerrestreImperial [HP=" + getPuntosVida() + ", ATK=" + getAtaque()
				+ ", DEF=" + getDefensa() + ", NAME=" + getNombre() + ", CREW="
				+ getGuerreros() + "]";
	}
	
}
