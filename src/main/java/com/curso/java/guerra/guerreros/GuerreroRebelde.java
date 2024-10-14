package com.curso.java.guerra.guerreros;

import com.curso.java.guerra.excepciones.ExceedsStatsLimits;

public class GuerreroRebelde extends Guerrero{
	public GuerreroRebelde(String nombre, String tipo, int fuerza, int resistencia) throws ExceedsStatsLimits {
		super(nombre, tipo, fuerza, resistencia);
	}
}
