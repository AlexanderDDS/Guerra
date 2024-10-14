package com.curso.java.guerra;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.curso.java.guerra.excepciones.ExceedsStatsLimits;
import com.curso.java.guerra.excepciones.ExceedsVehicleCapacityException;
import com.curso.java.guerra.guerreros.Guerrero;
import com.curso.java.guerra.guerreros.GuerreroImperioGalactico;
import com.curso.java.guerra.guerreros.GuerreroRebelde;
import com.curso.java.guerra.utils.ConsoleColors;
import com.curso.java.guerra.vehiculos.NaveRebelde;
import com.curso.java.guerra.vehiculos.VehiculoGuerra;
import com.curso.java.guerra.vehiculos.VehiculoTerrestreImperial;

public class Guerra {
	/**
	Método que necesita un objeto de tipo VehiculoGuerra, al cual pone a luchar contra otro vehículo preestablecido
	@param VehiculoGuerra vehiculo
	*/
	public static void empezarGuerra(VehiculoGuerra vehiculo) {
		Guerra guerra = new Guerra();
		System.out.println("");
		VehiculoGuerra vehiculoPreestablecido = guerra.crearVehiculoPreestablecido();
		System.out.println("Vehículo Preestablecido = "+ConsoleColors.YELLOW_BACKGROUND_BRIGHT+ConsoleColors.BLACK_BOLD_BRIGHT+vehiculoPreestablecido+ConsoleColors.RESET);
		guerra.mostrarLogo();
		Guerra.combate(vehiculoPreestablecido, vehiculo);
	}
	/**
	Método que necesita un objeto tipo File (fichero), a partir del cual crea un vehiculo que lucha contra otro vehículo preestablecido.
	El fichero debe contener al menos dos líneas:
	En la primera línea, deben pasarse 4 parámetros para el vehículo (Clase, nombre, ataque, defensa) seaparados por ';'. Solo se pueden escoger entre las clases: NaveRebelde, o VehiculoTerrestreImperial.
	A partir de la segunda línea, deben pasarse 6 parámetros para cada tipo de guerrero (Clase, nombre, tipo, fuerza, resistencia, número de copias) seaparados por ';'.  Solo se pueden escoger entre las clases: GuerreroRebelde, o GuerreroImperioGalactico.
	@param File fichero
	*/
	public static void empezarGuerra(File fichero) {
		Guerra guerra = new Guerra();
		VehiculoGuerra vehiculoPreestablecido = guerra.crearVehiculoPreestablecido();
		System.out.println("Vehículo Preestablecido = "+ConsoleColors.YELLOW_BACKGROUND_BRIGHT+ConsoleColors.BLACK_BOLD_BRIGHT+vehiculoPreestablecido+ConsoleColors.RESET+"\n");
		if (fichero.exists()) {
			if (!fichero.isDirectory()) {
				if (fichero.getName().endsWith(".txt")) {
					VehiculoGuerra vehiculoContrincante;
					try {
						vehiculoContrincante = guerra.crearVehiculoConGuerreros(fichero);
						System.out.println("Vehículo Contrincante = "+ConsoleColors.YELLOW_BACKGROUND_BRIGHT+ConsoleColors.BLACK_BOLD_BRIGHT+vehiculoContrincante+ConsoleColors.RESET);
						guerra.mostrarLogo();
						Guerra.combate(vehiculoPreestablecido, vehiculoContrincante);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else {
					System.err.println("No se puede llevar a cabo la guerra, "+fichero.getName()+" no es un fichero .txt");
				}
			}else {
				System.err.println("No se puede llevar a cabo la guerra, "+fichero.getName()+" es un directorio y no un fichero");
			}
		}else {
			System.err.println("No se puede llevar a cabo la guerra, "+fichero.getName()+" no existe");
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
	private VehiculoGuerra crearVehiculoPreestablecido() {
		VehiculoGuerra vehiculo=null;
		try {
			vehiculo = new VehiculoTerrestreImperial("AT-AT", 10, 0);
		} catch (ExceedsStatsLimits esl) {
			System.err.println(esl.getMessage());
		}
		try {
			Guerrero guerrero1 = new GuerreroImperioGalactico("Darth Vader", "Sith", 10, 0);
			Guerrero guerrero2 = new GuerreroImperioGalactico("Tarkin", "Gran Moff", 10, 0);
			Guerrero guerrero3 = new GuerreroImperioGalactico("Soldado", "Soldado de asalto", 10, 0);
			
			try {
				vehiculo.embarcar(guerrero1, guerrero2, guerrero3, guerrero3, guerrero3, guerrero3, guerrero3, guerrero3, guerrero3, guerrero3);
			} catch (ExceedsVehicleCapacityException evce) {
				System.err.println(evce.getMessage());
			}
		} catch (ExceedsStatsLimits esl) {
			System.err.println(esl.getMessage());
		}
		return vehiculo;
	}
	private VehiculoGuerra crearVehiculoConGuerreros(File file) throws IOException {
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
				vehiculo = crearVehiculo(linea);
				if (vehiculo==null) {
					System.err.println("Ha habido un error de lectura del fichero "+file.getName()+" en la línea "+numeroLinea+":\t"+linea);
				}
			}else {
				if (vehiculo!=null) {
					if (!crearGuerrerosEmbarcarGuerreros(linea, vehiculo)) {
						System.err.println("Ha habido un error de lectura del fichero "+file.getName()+" en la línea "+numeroLinea+":\t"+linea);
					}
				}
			}
			
		}
		return vehiculo;
	}
	private VehiculoGuerra crearVehiculo(String linea) {
		VehiculoGuerra vehiculo=null;
		String[] datosVehiculo = linea.split(";");
		if (datosVehiculo.length==4) {
			String nombre = datosVehiculo[1];
			int ataque = Integer.parseInt(datosVehiculo[2]);
			int defensa = Integer.parseInt(datosVehiculo[3]);
			if (datosVehiculo[0].equals("NaveRebelde")) {
				try {
					vehiculo = new NaveRebelde(nombre, ataque, defensa);
				} catch (ExceedsStatsLimits esl) {
					System.err.println(esl.getMessage());
				}
			}
			else if (datosVehiculo[0].equals("VehiculoTerrestreImperial")) {
				try {
					vehiculo = new VehiculoTerrestreImperial(nombre, ataque, defensa);
				} catch (ExceedsStatsLimits esl) {
					System.err.println(esl.getMessage());
				}
			}else {
				System.err.println("No existe el tipo de vehículo: "+datosVehiculo[0]);
			}
		}
		return vehiculo;
	}
	private boolean crearGuerrerosEmbarcarGuerreros(String linea, VehiculoGuerra vehiculo) {
		boolean GuerrerosHanEmbarcado = true;
		String[] datosGuerrero = linea.split(";");
		if (datosGuerrero.length==6) {
			String nombre = datosGuerrero[1];
			String tipo = datosGuerrero[2];
			int fuerza = Integer.parseInt(datosGuerrero[3]);
			int resistencia = Integer.parseInt(datosGuerrero[4]);
			int numeroCopias = Integer.parseInt(datosGuerrero[5]);
			if (datosGuerrero[0].equals("GuerreroRebelde")) {
				try {
					for (int i=0; i<numeroCopias; i++) {
						vehiculo.embarcar(new GuerreroRebelde(nombre, tipo, fuerza, resistencia));
					}
				} catch (ExceedsStatsLimits esl) {
					System.err.println(esl.getMessage());
				} catch (ExceedsVehicleCapacityException evce) {
					System.err.println(evce.getMessage());
				}
			}
			else if (datosGuerrero[0].equals("GuerreroImperioGalactico")) {
				try {
					for (int i=0; i<numeroCopias; i++) {
						vehiculo.embarcar(new GuerreroImperioGalactico(nombre, tipo, fuerza, resistencia));
					}
				} catch (ExceedsStatsLimits esl) {
					System.err.println(esl.getMessage());
				} catch (ExceedsVehicleCapacityException evce) {
					System.err.println(evce.getMessage());
				}
			}else {
				System.err.println("No existe el tipo de guerrero: "+datosGuerrero[0]);
			}
		}else {
			GuerrerosHanEmbarcado = false;
		}
		return GuerrerosHanEmbarcado;
	}
}