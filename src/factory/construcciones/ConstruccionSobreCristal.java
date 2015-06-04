package factory.construcciones;

import model.Cristal;
import model.Recurso;
import controller.Posicion;
import exceptions.FueraDeRangoException;

public abstract class ConstruccionSobreCristal extends Construccion {

	public ConstruccionSobreCristal(Cristal cristal) {
		// TODO Auto-generated constructor stub
		this.setPosicion(cristal.getPosicion());
		
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
