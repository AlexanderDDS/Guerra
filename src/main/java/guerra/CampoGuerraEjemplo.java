package guerra;

import excepciones.ExceedsStatsLimits;
import excepciones.ExceedsVehicleCapacityException;
import guerreros.Guerrero;
import guerreros.GuerreroRebelde;
import vehiculos.NaveRebelde;
import vehiculos.VehiculoGuerra;

public class CampoGuerraEjemplo {

	public static void main(String[] args) {
		CampoGuerraEjemplo campoGuerra = new CampoGuerraEjemplo();
		VehiculoGuerra vehiculo = campoGuerra.crearVehiculo();
		Guerra.empezarGuerra(vehiculo);
	}
	public VehiculoGuerra crearVehiculo() {
		VehiculoGuerra vehiculo = null;
		try {
			vehiculo = new NaveRebelde("T-47", 5, 5);
		} catch (ExceedsStatsLimits esl) {
			System.out.println(esl.getMessage());
		}
		try {
			Guerrero guerrero1 = new GuerreroRebelde("Luke Skywalker", "Jedi", 5, 5);
			Guerrero guerrero2 = new GuerreroRebelde("Han Solo", "Contrabandista", 5, 5);
			Guerrero guerrero3 = new GuerreroRebelde("soldado", "Soldado", 5, 5);
			try {
				vehiculo.embarcar(guerrero1, guerrero2, guerrero3, guerrero3, guerrero3, guerrero3, guerrero3, guerrero3, guerrero3, guerrero3);
			} catch (ExceedsVehicleCapacityException evce) {
				System.out.println(evce.getMessage());
			}
		} catch (ExceedsStatsLimits esl) {
			System.out.println(esl.getMessage());
		}
		return vehiculo;
	}
}
