package com.curso.java.guerra;

import java.io.File;

public class CampoGuerraEjemplo {

	public static void main(String[] args) {
		CampoGuerraEjemplo campoGuerra = new CampoGuerraEjemplo();
		File datosContrincante = new File("DatosContrincante.txt");
		Guerra.empezarGuerra(datosContrincante);
	}
}
