package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.curso.java.guerra.Guerra;
import com.curso.java.guerra.excepciones.ExceedsStatsLimits;
import com.curso.java.guerra.excepciones.ExceedsVehicleCapacityException;
import com.curso.java.guerra.guerreros.Guerrero;
import com.curso.java.guerra.guerreros.GuerreroImperioGalactico;
import com.curso.java.guerra.guerreros.GuerreroRebelde;
import com.curso.java.guerra.vehiculos.NaveRebelde;
import com.curso.java.guerra.vehiculos.VehiculoGuerra;
import com.curso.java.guerra.vehiculos.VehiculoTerrestreImperial;

public class GuerraTest {
	VehiculoGuerra vehiculo1;
	VehiculoGuerra vehiculo2;
	Guerrero guerrero1;
	Guerrero guerrero2;
	@Before
	public void crearVehiculos() {
		try {
			vehiculo1 = new VehiculoTerrestreImperial("vehiculo1");
		} catch (ExceedsStatsLimits e) {
			e.printStackTrace();
		}
		try {
			vehiculo2 = new NaveRebelde("vehiculo2", 3, 4);
		} catch (ExceedsStatsLimits esl) {
			esl.getMessage();
		}
		try {
			guerrero1 = new GuerreroImperioGalactico("guerrero1", "tipo1", 10, 0);
			guerrero2 = new GuerreroRebelde("guerrero2", "tipo2", 5, 5);
		} catch (ExceedsStatsLimits esl) {
			esl.getMessage();
		}
		try {
			vehiculo1.embarcar(guerrero1);
			vehiculo2.embarcar(guerrero2);
		} catch (ExceedsVehicleCapacityException evce) {
			evce.getMessage();
		}
	}
	@Test
	public void testPuntosVidaIniciales() { 
		int resultado = vehiculo1.getPuntosVida();
		assertEquals(1000, resultado);
	}
	@Test
	public void testStatsIniciales() {
		int resultado = vehiculo1.getAtaque()+vehiculo1.getDefensa();
		assertTrue(resultado<=10);
	}
	@Test
	public void testCombate() {
		Guerra.combate(vehiculo1, vehiculo2);
		assertTrue(vehiculo1.getPuntosVida()<=0||vehiculo2.getPuntosVida()<=0);
	}
}

