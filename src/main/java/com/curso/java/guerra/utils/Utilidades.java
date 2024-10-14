package com.curso.java.guerra.utils;

public class Utilidades {
	public static void limpiarPantallaTerminal() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (Exception e) {
			/* No hacer nada */}
	}
}
