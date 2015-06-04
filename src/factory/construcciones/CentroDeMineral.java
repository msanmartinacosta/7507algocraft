package factory.construcciones;

import model.Cristal;
import controller.Posicion;
import exceptions.FueraDeRangoException;

public class CentroDeMineral extends ConstruccionSobreCristal {

	public CentroDeMineral(Cristal cristal) {
		super(cristal);
	}
	@Override
	public void posicionar(Posicion nuevaPosicion) throws FueraDeRangoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void mover(Posicion nuevaPosicion) throws FueraDeRangoException {
		// TODO Auto-generated method stub

	}

}
