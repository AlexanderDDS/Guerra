package com.curso.java.guerra.vehiculos;

import com.curso.java.guerra.excepciones.ExceedsStatsLimits;

public class NaveRebelde extends VehiculoGuerra{

	public NaveRebelde(String nombre) throws ExceedsStatsLimits {
		super(nombre);
	}

	public NaveRebelde(String nombre, int ataque, int defensa) throws ExceedsStatsLimits {
		super(nombre, ataque, defensa);
	}

	@Override
	public String toString() {
		return "NaveRebelde [HP=" + getPuntosVida() + ", ATK=" + getAtaque() + ", DEF="
				+ getDefensa() + ", NAME=" + getNombre() + ", CREW=" + getGuerreros() + "]";
	}
	
}
