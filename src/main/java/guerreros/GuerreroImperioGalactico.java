package guerreros;

import excepciones.ExceedsStatsLimits;

public class GuerreroImperioGalactico extends Guerrero{
	public GuerreroImperioGalactico(String nombre, String tipo, int fuerza, int resistencia) throws ExceedsStatsLimits {
		super(nombre, tipo, fuerza, resistencia);
	}
	
}
