package guerreros;

import excepciones.ExceedsStatsLimits;

public class GuerreroRebelde extends Guerrero{
	public GuerreroRebelde(String nombre, String tipo, int fuerza, int resistencia) throws ExceedsStatsLimits {
		super(nombre, tipo, fuerza, resistencia);
	}
}
