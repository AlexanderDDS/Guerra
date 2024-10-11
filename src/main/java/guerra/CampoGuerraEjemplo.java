package guerra;

import java.io.File;

public class CampoGuerraEjemplo {

	public static void main(String[] args) {
		CampoGuerraEjemplo campoGuerra = new CampoGuerraEjemplo();
		File datosVehiculo = new File("DatosVehiculo.txt");
		Guerra.empezarGuerra(datosVehiculo);
	}
}
