package strategytest;

import static org.junit.Assert.assertEquals;
import jugador.Jugador;
import jugador.TipoColor;
import model.Juego;

import org.junit.Before;
import org.junit.Test;

import razas.Protoss;
import razas.Terran;
import strategy.ContextoStrategy;
import strategy.Radiacion;
import common.Posicion;
import exceptions.CostoInvalidoException;
import exceptions.DanioInvalidoException;
import exceptions.ElementoInvalidoException;
import exceptions.ElementoNoEncontradoException;
import exceptions.EnergiaInsuficienteException;
import exceptions.FactoryInvalidaException;
import exceptions.FinDePartidaException;
import exceptions.FueraDeRangoDeVisionException;
import exceptions.FueraDeRangoException;
import exceptions.PartidaGanadaException;
import exceptions.PartidaPerdidaException;
import exceptions.PoblacionFaltanteException;
import exceptions.PosicionInvalidaException;
import exceptions.RecursosFaltantesException;
import exceptions.RecursosInsuficientesException;
import exceptions.UnidadInvalidaException;
import exceptions.UnidadLlenaException;
import factory.UnidadFactory;
import factory.unidades.TipoUnidad;
import factory.unidades.Unidad;

public class RadiacionTest {

	ContextoStrategy contexto;
	UnidadFactory factory;
	Unidad unidadAtacante;
	Unidad unidadAtacadaEnRango;
	Unidad unidadAtacadaFueraDeRango;
	Jugador jugadorActual;
	Jugador jugadorEnemigo;
	Posicion posicionUnidadAtacante;
	Posicion posicionUnidadAtacadaEnRango;
	Posicion posicionUnidadAtacadaFueraDeRango;

	@Before
	public void setUp() throws Exception {
		
		Juego.DestruirInstancia();
		
		factory = new UnidadFactory();
		contexto=new ContextoStrategy(new Radiacion());
		
		jugadorActual = new Jugador("jugador1",TipoColor.COLOR_ROJO,new Terran());
		posicionUnidadAtacante = new Posicion(2,2);
		unidadAtacante=factory.getUnidad(TipoUnidad.TERRAN_NAVE_CIENCIA, posicionUnidadAtacante);
		unidadAtacante.setEnergia(100);
		jugadorActual.agregarElemento(unidadAtacante);
		Juego.getInstancia().setJugadorActual(jugadorActual);

		jugadorEnemigo = new Jugador("jugador2",TipoColor.COLOR_AZUL,new Protoss());
		posicionUnidadAtacadaEnRango = new Posicion(2,4);//El rango de vision de nave ciencia es 8
		unidadAtacadaEnRango=factory.getUnidad(TipoUnidad.TERRAN_MARINE,posicionUnidadAtacadaEnRango);
		jugadorEnemigo.agregarElemento(unidadAtacadaEnRango);
		
//		posicionUnidadAtacadaFueraDeRango = new Posicion(15,20);//El rango de vision de nave ciencia es 8
//		unidadAtacadaFueraDeRango=factory.getUnidad(TipoUnidad.TERRAN_MARINE,posicionUnidadAtacadaFueraDeRango);
//		jugadorEnemigo.agregarElemento(unidadAtacadaFueraDeRango);
		
		Juego.getInstancia().setJugadorEnemigo(jugadorEnemigo);
	}

	/**
	 * La vida del enemigo deberia queda en 0
	 * @throws CostoInvalidoException 
	 * @throws RecursosInsuficientesException 
	 * @throws CloneNotSupportedException 
	 * @throws PartidaPerdidaException 
	 * @throws PartidaGanadaException 
	 * @throws FinDePartidaException 
	 */
	@Test
	public void testAtacaElementoSinUnidadesAlLado() 
	throws FactoryInvalidaException, UnidadInvalidaException, FueraDeRangoException, ElementoInvalidoException, PosicionInvalidaException, ElementoNoEncontradoException, FueraDeRangoDeVisionException, EnergiaInsuficienteException, CostoInvalidoException, RecursosInsuficientesException, CloneNotSupportedException, FinDePartidaException, PartidaGanadaException, PartidaPerdidaException {
		try {
			unidadAtacante.realizarAccion(contexto, posicionUnidadAtacadaEnRango);
		} catch (Exception e) {
			assertEquals(0,Juego.getInstancia().getJugadorEnemigo().obtenerArmada().getArmada().get(0).getVida());
		}
	}
	
	/**
	 * La vida del enemigo deberia ser cero y la del que esta al lado tambien
	 * @throws CostoInvalidoException 
	 * @throws RecursosInsuficientesException 
	 * @throws CloneNotSupportedException 
	 * @throws PartidaPerdidaException 
	 * @throws PartidaGanadaException 
	 * @throws FinDePartidaException 
	 * @throws UnidadLlenaException 
	 * @throws PoblacionFaltanteException 
	 * @throws RecursosFaltantesException 
	 * @throws DanioInvalidoException 
	 */
	@Test
	public void testAtacaElementoConUnidadADerecha() 
	throws UnidadInvalidaException, FueraDeRangoException, ElementoInvalidoException, PosicionInvalidaException, FactoryInvalidaException, ElementoNoEncontradoException, FueraDeRangoDeVisionException, EnergiaInsuficienteException, CostoInvalidoException, RecursosInsuficientesException, CloneNotSupportedException, FinDePartidaException, PartidaGanadaException, PartidaPerdidaException, UnidadLlenaException, RecursosFaltantesException, PoblacionFaltanteException, DanioInvalidoException {
		try {
			Unidad unidadADerecha= factory.getUnidad(TipoUnidad.TERRAN_MARINE, new Posicion(3,4));
			Juego.getInstancia().agregarUnidadAJugadorEnemigo(unidadADerecha);
			unidadAtacante.realizarAccion(contexto, posicionUnidadAtacadaEnRango);	
			
		} catch (PartidaGanadaException e) {
			assertEquals(0,Juego.getInstancia().getJugadorEnemigo().obtenerArmada().getArmada().get(0).getVida());
			assertEquals(0,Juego.getInstancia().getJugadorEnemigo().obtenerArmada().getArmada().get(1).getVida());
			Juego.getInstancia().getJugadorEnemigo().eliminarElementoMuertoEnPosicion(new Posicion(3,4));
		}
	}

	@Test
	public void testAtacaElementoConUnidadAIzquierda() 
	throws UnidadInvalidaException, FueraDeRangoException, ElementoInvalidoException, PosicionInvalidaException, FactoryInvalidaException, ElementoNoEncontradoException, FueraDeRangoDeVisionException, EnergiaInsuficienteException, CostoInvalidoException, RecursosInsuficientesException, CloneNotSupportedException, FinDePartidaException, PartidaGanadaException, PartidaPerdidaException, UnidadLlenaException, RecursosFaltantesException, PoblacionFaltanteException, DanioInvalidoException {
		try {
			Unidad unidadAIzquierda= factory.getUnidad(TipoUnidad.TERRAN_MARINE, new Posicion(1,4));
			Juego.getInstancia().agregarUnidadAJugadorEnemigo(unidadAIzquierda);
			unidadAtacante.realizarAccion(contexto, posicionUnidadAtacadaEnRango);
			//JuegoController.getInstancia().getJugadorEnemigo().eliminarElementoMuertoEnPosicion(new Posicion(1,4));
		} catch (PartidaGanadaException e) {
			
			assertEquals(0,Juego.getInstancia().getJugadorEnemigo().obtenerArmada().getArmada().get(0).getVida());
			assertEquals(0,Juego.getInstancia().getJugadorEnemigo().obtenerArmada().getArmada().get(1).getVida());
		}
	}
	
	@Test
	public void testAtacaElementoConUnidadDelante() 
	throws UnidadInvalidaException, FueraDeRangoException, ElementoInvalidoException, PosicionInvalidaException, FactoryInvalidaException, ElementoNoEncontradoException, FueraDeRangoDeVisionException, EnergiaInsuficienteException, CostoInvalidoException, RecursosInsuficientesException, CloneNotSupportedException, FinDePartidaException, PartidaGanadaException, PartidaPerdidaException, UnidadLlenaException, RecursosFaltantesException, PoblacionFaltanteException, DanioInvalidoException {
		try {
			Unidad unidadDelante= factory.getUnidad(TipoUnidad.TERRAN_MARINE, new Posicion(2,3));
			Juego.getInstancia().agregarUnidadAJugadorEnemigo(unidadDelante);
			unidadAtacante.realizarAccion(contexto, posicionUnidadAtacadaEnRango);
			assertEquals(0,Juego.getInstancia().getJugadorEnemigo().obtenerArmada().getArmada().get(0).getVida());
			assertEquals(0,Juego.getInstancia().getJugadorEnemigo().obtenerArmada().getArmada().get(1).getVida());
			//JuegoController.getInstancia().getJugadorEnemigo().eliminarElementoMuertoEnPosicion(new Posicion(3,3));
		} catch (PartidaGanadaException e) {
		}
	}
	
	@Test
	public void testAtacaElementoConUnidadDetras() 
	throws UnidadInvalidaException, FueraDeRangoException, ElementoInvalidoException, PosicionInvalidaException, FactoryInvalidaException, ElementoNoEncontradoException, FueraDeRangoDeVisionException, EnergiaInsuficienteException, CostoInvalidoException, RecursosInsuficientesException, CloneNotSupportedException, FinDePartidaException, PartidaGanadaException, PartidaPerdidaException, UnidadLlenaException, RecursosFaltantesException, PoblacionFaltanteException, DanioInvalidoException {
		try {
			Unidad unidadDetras= factory.getUnidad(TipoUnidad.TERRAN_MARINE, new Posicion(2,5));	
			Juego.getInstancia().agregarUnidadAJugadorEnemigo(unidadDetras);
			unidadAtacante.realizarAccion(contexto, posicionUnidadAtacadaEnRango);
			assertEquals(0,Juego.getInstancia().getJugadorEnemigo().obtenerArmada().getArmada().get(0).getVida());
			assertEquals(0,Juego.getInstancia().getJugadorEnemigo().obtenerArmada().getArmada().get(1).getVida());
			//JuegoController.getInstancia().getJugadorEnemigo().eliminarElementoMuertoEnPosicion(new Posicion(3,5));
		} catch (PartidaGanadaException e) {
		}
		
	}
	
	//@Test
	public void testAtacaElementoConUnidadDetrasEIzquierda() 
	throws UnidadInvalidaException, FueraDeRangoException, FactoryInvalidaException, ElementoInvalidoException, PosicionInvalidaException, ElementoNoEncontradoException, FueraDeRangoDeVisionException, EnergiaInsuficienteException, CostoInvalidoException, RecursosInsuficientesException, CloneNotSupportedException, FinDePartidaException, PartidaGanadaException, PartidaPerdidaException, UnidadLlenaException, RecursosFaltantesException, PoblacionFaltanteException, DanioInvalidoException {
		Unidad unidadDetras= factory.getUnidad(TipoUnidad.TERRAN_MARINE, new Posicion(3,5));
		Unidad unidadAIzquierda= factory.getUnidad(TipoUnidad.TERRAN_MARINE, new Posicion(1,4));
		
		Juego.getInstancia().agregarUnidadAJugadorEnemigo(unidadDetras);
		Juego.getInstancia().agregarUnidadAJugadorEnemigo(unidadAIzquierda);
		
		unidadAtacante.realizarAccion(contexto, posicionUnidadAtacadaEnRango);
		
		assertEquals(0,Juego.getInstancia().getJugadorEnemigo().obtenerArmada().getArmada().get(0).getVida());
		assertEquals(0,Juego.getInstancia().getJugadorEnemigo().obtenerArmada().getArmada().get(1).getVida());
		assertEquals(0,Juego.getInstancia().getJugadorEnemigo().obtenerArmada().getArmada().get(2).getVida());

	}
}

