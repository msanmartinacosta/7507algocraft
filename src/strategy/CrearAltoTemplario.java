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

public class CrearAltoTemplario implements Strategy {
	
	@Override
	public void realizarAccion(ElementoArtificial elementoActuante, Posicion posicionDestino) 
	throws UnidadInvalidaException, FueraDeRangoException, ElementoInvalidoException, PosicionInvalidaException, CostoInvalidoException, RecursosInsuficientesException, RecursosFaltantesException, PoblacionFaltanteException {
		
		UnidadFactory factory = new UnidadFactory();
		
		ElementoArtificial altoTemplario = factory.getUnidad(TipoUnidad.PROTOSS_ALTO_TEMPLARIO, posicionDestino);
		JuegoController.getInstancia().getJugadorActual().validarCreacion(altoTemplario);
		JuegoController.getInstancia().getJugadorActual().aumentarPoblacionActual(altoTemplario.sumarPoblacion());
		JuegoController.getInstancia().getJugadorActual().disminuirRecursos(altoTemplario.disminuirMineral(),altoTemplario.disminuirGas());
		JuegoController.getInstancia().agregarUnidadAJugadorActual(altoTemplario);
		
	}

}
