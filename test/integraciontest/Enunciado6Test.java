package integraciontest;

import static org.junit.Assert.assertEquals;
import jugador.Jugador;
import jugador.TipoColor;
import model.ElementoArtificial;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import razas.Protoss;
import razas.Terran;
import strategy.ContextoStrategy;
import strategy.CrearAltoTemplario;
import strategy.CrearDragon;
import strategy.CrearEspectro;
import strategy.CrearGolliat;
import strategy.CrearMarine;
import strategy.CrearNaveCiencia;
import strategy.CrearNaveTransporteProtoss;
import strategy.CrearNaveTransporteTerran;
import strategy.CrearScout;
import strategy.CrearZealot;

import common.Posicion;

import controller.JuegoController;
import exceptions.CostoInvalidoException;
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
import factory.AbstractFactory;
import factory.GeneradorDeFactory;
import factory.TipoFactory;
import factory.construcciones.TipoEdificio;
import factory.unidades.Unidad;

@RunWith(JUnit4.class)
public class Enunciado6Test {
	
	private Jugador jugadorTerran;
	private Jugador jugadorProtoss;
	AbstractFactory factoryUnidad;
	AbstractFactory factoryConstruccion;

	
	@Before
	public void setUp() throws Exception {
		factoryUnidad = GeneradorDeFactory.getFactory(TipoFactory.UNIDAD_FACTORY);
		factoryConstruccion = GeneradorDeFactory.getFactory(TipoFactory.CONSTRUCCION_FACTORY);
		
		jugadorTerran = new Jugador("Jugador1",TipoColor.COLOR_ROJO,new Terran());
		jugadorProtoss = new Jugador("Jugador2",TipoColor.COLOR_AZUL,new Protoss());
	
	}
	@Test
	public void testAumentoDePoblacionActualMarineOk() throws FueraDeRangoException, CostoInvalidoException, FactoryInvalidaException, UnidadInvalidaException, ElementoInvalidoException, PosicionInvalidaException, ElementoNoEncontradoException, FueraDeRangoDeVisionException, EnergiaInsuficienteException, RecursosInsuficientesException, CloneNotSupportedException, FinDePartidaException, PartidaGanadaException, PartidaPerdidaException, UnidadLlenaException, RecursosFaltantesException, PoblacionFaltanteException {
		JuegoController.getInstancia().setJugadorActual(jugadorTerran);
		jugadorTerran.setPoblacionDisponible(100);
		jugadorTerran.agregarCantidadDeCristal(100000);
		jugadorTerran.agregarCantidadDeGas(100000);
		
		//Creo un Marine
		ContextoStrategy contexto = new ContextoStrategy(new CrearMarine());
		Posicion posBarraca = new Posicion(1,1);
		ElementoArtificial barraca = factoryConstruccion.getEdificio(TipoEdificio.TERRAN_BARRACA, posBarraca);
		Posicion posMarine = new Posicion(1,2);
		barraca.realizarAccion(contexto, posMarine);
		int poblacionActual = jugadorTerran.getPoblacionActual();
		
		//Verifico que la poblacion actual aumento
		Unidad marine = (Unidad) jugadorTerran.obtenerArmada().obtenerElementoEnPosicion(posMarine);
		assertEquals(marine.getSuministro(), poblacionActual);
	}
	
	@Test
	public void testAumentoDePoblacionActualGolliatOk() throws FueraDeRangoException, CostoInvalidoException, FactoryInvalidaException, UnidadInvalidaException, ElementoInvalidoException, PosicionInvalidaException, ElementoNoEncontradoException, FueraDeRangoDeVisionException, EnergiaInsuficienteException, RecursosInsuficientesException, CloneNotSupportedException, FinDePartidaException, PartidaGanadaException, PartidaPerdidaException, UnidadLlenaException, RecursosFaltantesException, PoblacionFaltanteException {
		JuegoController.getInstancia().setJugadorActual(jugadorTerran);
		jugadorTerran.setPoblacionDisponible(100);
		jugadorTerran.agregarCantidadDeCristal(100000);
		jugadorTerran.agregarCantidadDeGas(100000);
		
		//Creo un Golliat
		ContextoStrategy contexto = new ContextoStrategy(new CrearGolliat());
		Posicion posFabrica = new Posicion(1,1);
		ElementoArtificial fabrica = factoryConstruccion.getEdificio(TipoEdificio.TERRAN_FABRICA, posFabrica);
		Posicion posGolliat = new Posicion(1,2);
		fabrica.realizarAccion(contexto, posGolliat);
		int poblacionActual = jugadorTerran.getPoblacionActual();
		
		//Verifico que la poblacion actual aumento
		Unidad golliat = (Unidad) jugadorTerran.obtenerArmada().obtenerElementoEnPosicion(posGolliat);
		assertEquals(golliat.getSuministro(), poblacionActual);
	}
	@Test
	public void testAumentoDePoblacionActualEspectroOk() throws FueraDeRangoException, CostoInvalidoException, FactoryInvalidaException, UnidadInvalidaException, ElementoInvalidoException, PosicionInvalidaException, ElementoNoEncontradoException, FueraDeRangoDeVisionException, EnergiaInsuficienteException, RecursosInsuficientesException, CloneNotSupportedException, FinDePartidaException, PartidaGanadaException, PartidaPerdidaException, UnidadLlenaException, RecursosFaltantesException, PoblacionFaltanteException {
		JuegoController.getInstancia().setJugadorActual(jugadorTerran);
		jugadorTerran.setPoblacionDisponible(100);
		jugadorTerran.agregarCantidadDeCristal(100000);
		jugadorTerran.agregarCantidadDeGas(100000);
		
		//Creo un Espectro
		ContextoStrategy contexto = new ContextoStrategy(new CrearEspectro());
		Posicion posPuertoEstelar = new Posicion(1,1);
		ElementoArtificial puertoEstelar = factoryConstruccion.getEdificio(TipoEdificio.TERRAN_PUERTO_ESTELAR, posPuertoEstelar);
		Posicion posEspectro = new Posicion(1,2);
		puertoEstelar.realizarAccion(contexto, posEspectro);
		int poblacionActual = jugadorTerran.getPoblacionActual();
		
		//Verifico que la poblacion actual aumento
		Unidad espectro = (Unidad) jugadorTerran.obtenerArmada().obtenerElementoEnPosicion(posEspectro);
		assertEquals(espectro.getSuministro(), poblacionActual);
	}
	
	@Test
	public void testAumentoDePoblacionActualNaveDeTransporteTerranOk() throws FueraDeRangoException, CostoInvalidoException, FactoryInvalidaException, UnidadInvalidaException, ElementoInvalidoException, PosicionInvalidaException, ElementoNoEncontradoException, FueraDeRangoDeVisionException, EnergiaInsuficienteException, RecursosInsuficientesException, CloneNotSupportedException, FinDePartidaException, PartidaGanadaException, PartidaPerdidaException, UnidadLlenaException, RecursosFaltantesException, PoblacionFaltanteException {
		JuegoController.getInstancia().setJugadorActual(jugadorTerran);
		jugadorTerran.setPoblacionDisponible(100);
		jugadorTerran.agregarCantidadDeCristal(100000);
		jugadorTerran.agregarCantidadDeGas(100000);
		
		//Creo una Nave de transporte
		ContextoStrategy contexto = new ContextoStrategy(new CrearNaveTransporteTerran());
		Posicion posPuertoEstelar = new Posicion(1,1);
		ElementoArtificial puertoEstelar = factoryConstruccion.getEdificio(TipoEdificio.TERRAN_PUERTO_ESTELAR, posPuertoEstelar);
		Posicion posNaveDeTransporte = new Posicion(1,2);
		puertoEstelar.realizarAccion(contexto, posNaveDeTransporte);
		int poblacionActual = jugadorTerran.getPoblacionActual();
		
		//Verifico que la poblacion actual aumento
		Unidad naveDeTransporte = (Unidad) jugadorTerran.obtenerArmada().obtenerElementoEnPosicion(posNaveDeTransporte);
		assertEquals(naveDeTransporte.getSuministro(), poblacionActual);
	}
	
	@Test
	public void testAumentoDePoblacionActualNaveDeCienciaOk() throws FueraDeRangoException, CostoInvalidoException, FactoryInvalidaException, UnidadInvalidaException, ElementoInvalidoException, PosicionInvalidaException, ElementoNoEncontradoException, FueraDeRangoDeVisionException, EnergiaInsuficienteException, RecursosInsuficientesException, CloneNotSupportedException, FinDePartidaException, PartidaGanadaException, PartidaPerdidaException, UnidadLlenaException, RecursosFaltantesException, PoblacionFaltanteException {
		JuegoController.getInstancia().setJugadorActual(jugadorTerran);
		jugadorTerran.setPoblacionDisponible(100);
		jugadorTerran.agregarCantidadDeCristal(100000);
		jugadorTerran.agregarCantidadDeGas(100000);
		
		//Creo una Nave de ciencia
		ContextoStrategy contexto = new ContextoStrategy(new CrearNaveCiencia());
		Posicion posPuertoEstelar = new Posicion(1,1);
		ElementoArtificial puertoEstelar = factoryConstruccion.getEdificio(TipoEdificio.TERRAN_PUERTO_ESTELAR, posPuertoEstelar);
		Posicion posNaveDeCiencia = new Posicion(1,2);
		puertoEstelar.realizarAccion(contexto, posNaveDeCiencia);
		int poblacionActual = jugadorTerran.getPoblacionActual();
		
		//Verifico que la poblacion actual aumento
		Unidad naveDeCiencia = (Unidad) jugadorTerran.obtenerArmada().obtenerElementoEnPosicion(posNaveDeCiencia);
		assertEquals(naveDeCiencia.getSuministro(), poblacionActual);
	}
	
	@Test
	public void testAumentoDePoblacionActualTerranOk() throws FueraDeRangoException, CostoInvalidoException, FactoryInvalidaException, UnidadInvalidaException, ElementoInvalidoException, PosicionInvalidaException, ElementoNoEncontradoException, FueraDeRangoDeVisionException, EnergiaInsuficienteException, RecursosInsuficientesException, CloneNotSupportedException, FinDePartidaException, PartidaGanadaException, PartidaPerdidaException, UnidadLlenaException, RecursosFaltantesException, PoblacionFaltanteException {
		JuegoController.getInstancia().setJugadorActual(jugadorTerran);
		jugadorTerran.setPoblacionDisponible(100);
		jugadorTerran.agregarCantidadDeCristal(100000);
		jugadorTerran.agregarCantidadDeGas(100000);
		
		//Creo un Marine
		ContextoStrategy contexto = new ContextoStrategy(new CrearMarine());
		Posicion posBarraca = new Posicion(1,1);
		ElementoArtificial barraca = factoryConstruccion.getEdificio(TipoEdificio.TERRAN_BARRACA, posBarraca);
		Posicion posMarine = new Posicion(1,2);
		barraca.realizarAccion(contexto, posMarine);
		int poblacionActual = jugadorTerran.getPoblacionActual();
		
		//Verifico que la poblacion actual aumento
		Unidad marine = (Unidad) jugadorTerran.obtenerArmada().obtenerElementoEnPosicion(posMarine);
		assertEquals(marine.getSuministro(), poblacionActual);
		
		//Creo un Golliat
		contexto = new ContextoStrategy(new CrearGolliat());
		Posicion posFabrica = new Posicion(2,2);
		ElementoArtificial fabrica = factoryConstruccion.getEdificio(TipoEdificio.TERRAN_FABRICA, posFabrica);
		Posicion posGolliat = new Posicion(2,3);
		fabrica.realizarAccion(contexto, posGolliat);
		poblacionActual = jugadorTerran.getPoblacionActual();
		
		//Verifico que la poblacion actual aumento
		Unidad golliat = (Unidad) jugadorTerran.obtenerArmada().obtenerElementoEnPosicion(posGolliat);
		assertEquals(golliat.getSuministro() + marine.getSuministro(), poblacionActual);	
		
	}
	
	@Test
	public void testAumentoDePoblacionActualProtossOk() throws FueraDeRangoException, CostoInvalidoException, FactoryInvalidaException, UnidadInvalidaException, ElementoInvalidoException, PosicionInvalidaException, ElementoNoEncontradoException, FueraDeRangoDeVisionException, EnergiaInsuficienteException, RecursosInsuficientesException, CloneNotSupportedException, FinDePartidaException, PartidaGanadaException, PartidaPerdidaException, UnidadLlenaException, RecursosFaltantesException, PoblacionFaltanteException {
		JuegoController.getInstancia().setJugadorActual(jugadorProtoss);
		jugadorProtoss.setPoblacionDisponible(100);
		jugadorProtoss.agregarCantidadDeCristal(100000);
		jugadorProtoss.agregarCantidadDeGas(100000);
		
		//Creo un Zealot
		ContextoStrategy contexto = new ContextoStrategy(new CrearZealot());
		Posicion posAcceso = new Posicion(3,3);
		ElementoArtificial acceso = factoryConstruccion.getEdificio(TipoEdificio.PROTOSS_ACCESO, posAcceso);
		Posicion posZealot = new Posicion(3,2);
		acceso.realizarAccion(contexto, posZealot);
		int poblacionActual = jugadorProtoss.getPoblacionActual();
		
		//Verifico que la poblacion actual aumento
		Unidad zealot = (Unidad) jugadorProtoss.obtenerArmada().obtenerElementoEnPosicion(posZealot);
		assertEquals(zealot.getSuministro(), poblacionActual);
		
		//Creo un Dragon
		contexto = new ContextoStrategy(new CrearDragon());
		Posicion posDragon = new Posicion(3,4);
		acceso.realizarAccion(contexto, posDragon);
		poblacionActual = jugadorProtoss.getPoblacionActual();
		
		//Verifico que la poblacion actual aumento
		Unidad dragon = (Unidad) jugadorProtoss.obtenerArmada().obtenerElementoEnPosicion(posDragon);
		assertEquals(dragon.getSuministro() + zealot.getSuministro(), poblacionActual);
			
	}
	
	@Test
	public void testAumentoDePoblacionActualZealotOk() throws FueraDeRangoException, CostoInvalidoException, FactoryInvalidaException, UnidadInvalidaException, ElementoInvalidoException, PosicionInvalidaException, ElementoNoEncontradoException, FueraDeRangoDeVisionException, EnergiaInsuficienteException, RecursosInsuficientesException, CloneNotSupportedException, FinDePartidaException, PartidaGanadaException, PartidaPerdidaException, UnidadLlenaException, RecursosFaltantesException, PoblacionFaltanteException {
		JuegoController.getInstancia().setJugadorActual(jugadorProtoss);
		jugadorProtoss.setPoblacionDisponible(100);
		jugadorProtoss.agregarCantidadDeCristal(100000);
		jugadorProtoss.agregarCantidadDeGas(100000);
		
		//Creo un Zealot
		ContextoStrategy contexto = new ContextoStrategy(new CrearZealot());
		Posicion posAcceso = new Posicion(3,3);
		ElementoArtificial acceso = factoryConstruccion.getEdificio(TipoEdificio.PROTOSS_ACCESO, posAcceso);
		Posicion posZealot = new Posicion(3,2);
		acceso.realizarAccion(contexto, posZealot);
		int poblacionActual = jugadorProtoss.getPoblacionActual();
		
		//Verifico que la poblacion actual aumento
		Unidad zealot = (Unidad) jugadorProtoss.obtenerArmada().obtenerElementoEnPosicion(posZealot);
		assertEquals(zealot.getSuministro(), poblacionActual);
	}
	
	@Test
	public void testAumentoDePoblacionActualDragonOk() throws FueraDeRangoException, CostoInvalidoException, FactoryInvalidaException, UnidadInvalidaException, ElementoInvalidoException, PosicionInvalidaException, ElementoNoEncontradoException, FueraDeRangoDeVisionException, EnergiaInsuficienteException, RecursosInsuficientesException, CloneNotSupportedException, FinDePartidaException, PartidaGanadaException, PartidaPerdidaException, UnidadLlenaException, RecursosFaltantesException, PoblacionFaltanteException {
		JuegoController.getInstancia().setJugadorActual(jugadorProtoss);
		jugadorProtoss.setPoblacionDisponible(100);
		jugadorProtoss.agregarCantidadDeCristal(100000);
		jugadorProtoss.agregarCantidadDeGas(100000);
		
		//Creo un Dragon
		ContextoStrategy contexto = new ContextoStrategy(new CrearDragon());
		Posicion posAcceso = new Posicion(3,3);
		ElementoArtificial acceso = factoryConstruccion.getEdificio(TipoEdificio.PROTOSS_ACCESO, posAcceso);
		Posicion posDragon = new Posicion(3,2);
		acceso.realizarAccion(contexto, posDragon);
		int poblacionActual = jugadorProtoss.getPoblacionActual();
		
		//Verifico que la poblacion actual aumento
		Unidad dragon = (Unidad) jugadorProtoss.obtenerArmada().obtenerElementoEnPosicion(posDragon);
		assertEquals(dragon.getSuministro(), poblacionActual);
	}
	@Test
	public void testAumentoDePoblacionActualScoutOk() throws FueraDeRangoException, CostoInvalidoException, FactoryInvalidaException, UnidadInvalidaException, ElementoInvalidoException, PosicionInvalidaException, ElementoNoEncontradoException, FueraDeRangoDeVisionException, EnergiaInsuficienteException, RecursosInsuficientesException, CloneNotSupportedException, FinDePartidaException, PartidaGanadaException, PartidaPerdidaException, UnidadLlenaException, RecursosFaltantesException, PoblacionFaltanteException {
		JuegoController.getInstancia().setJugadorActual(jugadorProtoss);
		jugadorProtoss.setPoblacionDisponible(100);
		jugadorProtoss.agregarCantidadDeCristal(100000);
		jugadorProtoss.agregarCantidadDeGas(100000);
		
		//Creo un Scout
		ContextoStrategy contexto = new ContextoStrategy(new CrearScout());
		Posicion posPuertoEstelar = new Posicion(3,3);
		ElementoArtificial puertoEstelar = factoryConstruccion.getEdificio(TipoEdificio.PROTOSS_PUERTO_ESTELAR, posPuertoEstelar);
		Posicion posScout = new Posicion(3,2);
		puertoEstelar.realizarAccion(contexto, posScout);
		int poblacionActual = jugadorProtoss.getPoblacionActual();
		
		//Verifico que la poblacion actual aumento
		Unidad scout = (Unidad) jugadorProtoss.obtenerArmada().obtenerElementoEnPosicion(posScout);
		assertEquals(scout.getSuministro(), poblacionActual);
	}
	
	@Test
	public void testAumentoDePoblacionActualNaveDeTransporteProtossOk() throws FueraDeRangoException, CostoInvalidoException, FactoryInvalidaException, UnidadInvalidaException, ElementoInvalidoException, PosicionInvalidaException, ElementoNoEncontradoException, FueraDeRangoDeVisionException, EnergiaInsuficienteException, RecursosInsuficientesException, CloneNotSupportedException, FinDePartidaException, PartidaGanadaException, PartidaPerdidaException, UnidadLlenaException, RecursosFaltantesException, PoblacionFaltanteException {
		JuegoController.getInstancia().setJugadorActual(jugadorProtoss);
		jugadorProtoss.setPoblacionDisponible(100);
		jugadorProtoss.agregarCantidadDeCristal(100000);
		jugadorProtoss.agregarCantidadDeGas(100000);
		
		//Creo una nave de transporte
		ContextoStrategy contexto = new ContextoStrategy(new CrearNaveTransporteProtoss());
		Posicion posPuertoEstelar = new Posicion(3,3);
		ElementoArtificial puertoEstelar = factoryConstruccion.getEdificio(TipoEdificio.PROTOSS_PUERTO_ESTELAR, posPuertoEstelar);
		Posicion posNaveDeTransporte = new Posicion(3,2);
		puertoEstelar.realizarAccion(contexto, posNaveDeTransporte);
		int poblacionActual = jugadorProtoss.getPoblacionActual();
		
		//Verifico que la poblacion actual aumento
		Unidad naveDeTransporte = (Unidad) jugadorProtoss.obtenerArmada().obtenerElementoEnPosicion(posNaveDeTransporte);
		assertEquals(naveDeTransporte.getSuministro(), poblacionActual);
	}
	
	@Test
	public void testAumentoDePoblacionActualAltoTemplariosOk() throws FueraDeRangoException, CostoInvalidoException, FactoryInvalidaException, UnidadInvalidaException, ElementoInvalidoException, PosicionInvalidaException, ElementoNoEncontradoException, FueraDeRangoDeVisionException, EnergiaInsuficienteException, RecursosInsuficientesException, CloneNotSupportedException, FinDePartidaException, PartidaGanadaException, PartidaPerdidaException, UnidadLlenaException, RecursosFaltantesException, PoblacionFaltanteException {
		JuegoController.getInstancia().setJugadorActual(jugadorProtoss);
		jugadorProtoss.setPoblacionDisponible(100);
		jugadorProtoss.agregarCantidadDeCristal(100000);
		jugadorProtoss.agregarCantidadDeGas(100000);
		
		//Creo una nave de transporte
		ContextoStrategy contexto = new ContextoStrategy(new CrearAltoTemplario());
		Posicion posArchivosTemplarios = new Posicion(3,3);
		ElementoArtificial archivosTemplarios = factoryConstruccion.getEdificio(TipoEdificio.PROTOSS_ARCHIVO_TEMPLARIO, posArchivosTemplarios);
		Posicion posAltoTemplario = new Posicion(3,2);
		archivosTemplarios.realizarAccion(contexto, posAltoTemplario);
		int poblacionActual = jugadorProtoss.getPoblacionActual();
		
		//Verifico que la poblacion actual aumento
		Unidad altoTemplario = (Unidad) jugadorProtoss.obtenerArmada().obtenerElementoEnPosicion(posAltoTemplario);
		assertEquals(altoTemplario.getSuministro(), poblacionActual);
	}
		
	
}