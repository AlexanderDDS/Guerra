package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import excepciones.ExceedsStatsLimits;
import excepciones.ExceedsVehicleCapacityException;
import guerra.Guerra;
import guerreros.Guerrero;
import guerreros.GuerreroImperioGalactico;
import guerreros.GuerreroRebelde;
import vehiculos.NaveRebelde;
import vehiculos.VehiculoGuerra;
import vehiculos.VehiculoTerrestreImperial;

public class GuerraTest {
	VehiculoGuerra vehiculo1;
	VehiculoGuerra vehiculo2;
	Guerrero guerrero1;
	Guerrero guerrero2;
	@Before
	public void crearVehiculos() {
		vehiculo1 = new VehiculoTerrestreImperial("vehiculo1");
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

