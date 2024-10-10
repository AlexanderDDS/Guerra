package guerra;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import excepciones.ExceedsStatsLimits;
import excepciones.ExceedsVehicleCapacityException;
import guerreros.Guerrero;
import guerreros.GuerreroImperioGalactico;
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
	Método que necesita un fichero mediante un objeto tipo File, a partir del cual crea un vehiculo que lucha contra otro vehículo preestablecido
	@param File fichero
	*/
	public static void empezarGuerra(File fichero) {
		Guerra guerra = new Guerra();
		System.out.println("");
		VehiculoGuerra vehiculoPreestablecido = guerra.crearVehiculo();
		if (!fichero.isDirectory()) {
			if (fichero.getName().endsWith(".txt")) {
				VehiculoGuerra vehiculoContrincante;
				try {
					vehiculoContrincante = guerra.crearVehiculo(fichero);
					guerra.mostrarLogo();
					combate(vehiculoPreestablecido, vehiculoContrincante);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				System.out.println("No se puede llevar a cabo la guerra, el objeto File no es un fichero .txt");
			}
		}else {
			System.out.println("No se puede llevar a cabo la guerra, el objeto File es un directorio y no un fichero");
		}
	}
	/**
	Método que necesita dos objetos de tipo VehiculoGuerra, a los cuales pone a luchar entre sí
	@param int ataqueEnemigo
	*/
	public static void combate(VehiculoGuerra vehiculo1, VehiculoGuerra vehiculo2) {
		Guerra guerra = new Guerra();
		while (vehiculo1.getPuntosVida()>0||vehiculo2.getPuntosVida()>0) {
			System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT);
			vehiculo2.defender(vehiculo1.atacar());
			if (vehiculo2.getPuntosVida()<=0) {
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT+vehiculo2.getNombre()+" ha sido destruido!!"+ConsoleColors.RESET);
				break;
			}
			System.out.print(ConsoleColors.BLUE_BOLD_BRIGHT);
			vehiculo1.defender(vehiculo2.atacar());
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
			Guerrero guerrero3 = new GuerreroImperioGalactico("Soldado", "Soldado de asalto", 10, 0);
			
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
	private VehiculoGuerra crearVehiculo(File file) throws IOException {
		VehiculoGuerra vehiculo=null;
		
		Path path = Paths.get(file.getName());
		List<String> lines = null;
		lines = Files.readAllLines(path);
		int numeroLinea = 0;
		for (String linea : lines) {
			StringBuilder sb = new StringBuilder();
			numeroLinea++;
			sb.append(linea);
			if (numeroLinea==1) {
				String[] datosVehiculo = linea.split(";");
				if (datosVehiculo.length==4) {
					if (datosVehiculo[0].equals("NaveRebelde")) {
						String nombre = datosVehiculo[1];
						int ataque = Integer.parseInt(datosVehiculo[2]);
						int defensa = Integer.parseInt(datosVehiculo[3]);
						try {
							vehiculo = new NaveRebelde(nombre, ataque, defensa);
						} catch (ExceedsStatsLimits esl) {
							esl.getMessage();
						}
					}
					
				}else {
					System.out.println(ConsoleColors.RED_BOLD_BRIGHT+"Ha habido un error de lectura del fichero "+file.getName()+" en la línea "+numeroLinea+":");
					System.out.println("\t"+linea+ConsoleColors.RESET);
				}
			}
			
		}
		
		
		
		
		try {
			vehiculo = new VehiculoTerrestreImperial("AT-AT", 10, 0);
		} catch (ExceedsStatsLimits esl) {
			System.out.println(esl.getMessage());
		}
		try {
			Guerrero guerrero1 = new GuerreroImperioGalactico("Darth Vader", "Sith", 10, 0);
			Guerrero guerrero2 = new GuerreroImperioGalactico("Tarkin", "Gran Moff", 10, 0);
			Guerrero guerrero3 = new GuerreroImperioGalactico("Soldado", "Soldado de asalto", 10, 0);
			
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









