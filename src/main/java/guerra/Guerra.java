package guerra;

import excepciones.ExceedsStatsLimits;
import excepciones.ExceedsVehicleCapacityException;
import guerreros.Guerrero;
import guerreros.GuerreroImperioGalactico;
import guerreros.GuerreroRebelde;
import utils.ConsoleColors;
import vehiculos.NaveRebelde;
import vehiculos.VehiculoGuerra;
import vehiculos.VehiculoTerrestreImperial;

public class Guerra {
	/**
	Método que necesita un objeto de tipo VehiculoGuerra, al cual pone a luchar contra otro vehículo preestablecido
	@param VehiculoGuerra vehiculo
	*/
	public static void empezarGuerra(VehiculoGuerra vehiculo) {
		Guerra campoGuerra = new Guerra();
		System.out.println("");
		VehiculoGuerra vehiculoPreestablecido = campoGuerra.crearVehiculo();
		campoGuerra.mostrarLogo();
		combate(vehiculoPreestablecido, vehiculo);
	}
	/**
	Método que necesita dos objetos de tipo VehiculoGuerra, a los cuales pone a luchar entre sí
	@param int ataqueEnemigo
	*/
	public static void combate(VehiculoGuerra vehiculo1, VehiculoGuerra vehiculo2) {
		while (vehiculo1.getPuntosVida()>0||vehiculo2.getPuntosVida()>0) {
			System.out.print(ConsoleColors.PURPLE_BOLD_BRIGHT);
			vehiculo2.defender(vehiculo1.atacar());
			System.out.print(ConsoleColors.RESET);
			if (vehiculo2.getPuntosVida()<=0) {
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT+vehiculo2.getNombre()+" ha sido destruido!!"+ConsoleColors.RESET);
				break;
			}
			System.out.print(ConsoleColors.BLUE_BOLD_BRIGHT);
			vehiculo1.defender(vehiculo2.atacar());
			System.out.println(ConsoleColors.RESET);
			if (vehiculo1.getPuntosVida()<=0) {
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT+vehiculo1.getNombre()+" ha sido destruido!!"+ConsoleColors.RESET);
				break;
			}
		}
	}
	private void mostrarLogo() {
		String logo = "LA GUERRA HA EMPEZADO!!";
		try {
			Thread.sleep(500);
		} catch (InterruptedException ie) {
			ie.getMessage();
		}
		System.out.print("\n\t\t"+ConsoleColors.RED_BACKGROUND+ConsoleColors.YELLOW_BOLD_BRIGHT);
		char[] chars = logo.toCharArray();
		for (char ch : chars) {
			System.out.print(ch);
			try {
				Thread.sleep(50);
			} catch (InterruptedException ie) {
				ie.getMessage();
			}
	    }
		System.out.print(ConsoleColors.RESET+"\n");
		try {
			Thread.sleep(1200);
		} catch (InterruptedException ie) {
			ie.getMessage();
		}
	}
	private VehiculoGuerra crearVehiculo() {
		VehiculoGuerra vehiculo=null;
		try {
			vehiculo = new VehiculoTerrestreImperial("AT-AT", 10, 0);
		} catch (ExceedsStatsLimits esl) {
			System.out.println(esl.getMessage());
		}
		try {
			Guerrero guerrero1 = new GuerreroImperioGalactico("Darth Vader", "Sith", 10, 0);
			Guerrero guerrero2 = new GuerreroImperioGalactico("Tarkin", "Gran Moff", 10, 0);
			Guerrero guerrero3 = new GuerreroImperioGalactico("soldado", "Soldado de asalto", 10, 0);
			
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
