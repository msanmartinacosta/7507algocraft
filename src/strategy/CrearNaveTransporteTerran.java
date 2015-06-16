package strategy;

import model.ElementoArtificial;
import common.Posicion;
import controller.JuegoController;
import exceptions.CostoInvalidoException;
import exceptions.ElementoInvalidoException;
import exceptions.FueraDeRangoException;
import exceptions.PoblacionFaltanteException;
import exceptions.PosicionInvalidaException;
import exceptions.RecursosFaltantesException;
import exceptions.RecursosInsuficientesException;
import exceptions.UnidadInvalidaException;
import factory.UnidadFactory;
import factory.unidades.TipoUnidad;

public class CrearNaveTransporteTerran implements Strategy {
	
	@Override
	public void realizarAccion(ElementoArtificial elementoActuante, Posicion posicionDestino) 
	throws UnidadInvalidaException, FueraDeRangoException, ElementoInvalidoException, PosicionInvalidaException, CostoInvalidoException, RecursosInsuficientesException, RecursosFaltantesException, PoblacionFaltanteException {
		
		UnidadFactory factory = new UnidadFactory();
		
		ElementoArtificial naveTransporte = factory.getUnidad(TipoUnidad.TERRAN_NAVE_TRANSPORTE, posicionDestino);
		JuegoController.getInstancia().getJugadorActual().validarCreacion(naveTransporte);
		JuegoController.getInstancia().getJugadorActual().aumentarPoblacionActual(naveTransporte.sumarPoblacion());
		JuegoController.getInstancia().getJugadorActual().disminuirRecursos(naveTransporte.disminuirMineral(),naveTransporte.disminuirGas());
		JuegoController.getInstancia().agregarUnidadAJugadorActual(naveTransporte);
		
	}

}
