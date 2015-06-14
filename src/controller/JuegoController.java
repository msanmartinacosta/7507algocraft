package controller;

import java.util.Iterator;
import java.util.List;

import jugador.Jugador;
import jugador.TipoColor;
import model.Armada;
import model.CampoBatalla;
import model.ElementoArtificial;
import exceptions.ElementoInvalidoException;
import exceptions.FinDePartidaException;
import exceptions.NombreJugadorRepetidoException;
import exceptions.PosicionInvalidaException;
import exceptions.RecursosInsuficientesException;

public class JuegoController {
	
	private static Jugador jugadorActual;
	private static Jugador jugadorEnemigo;
//	private static CampoBatalla campoDeBatalla;
	private static JuegoController INSTANCIA = null;
	
	private JuegoController(){
		jugadorActual = new Jugador();
		jugadorEnemigo = new Jugador();
		//campoDeBatalla=CampoBatalla.getInstancia();
	}
	
	
	public static JuegoController getInstancia() {
			
			if (INSTANCIA == null) {
				crearInstancia();
			}
			return INSTANCIA;
		}
		
		//para probar
		public static void DestruirInstancia(){
			INSTANCIA = null;
		}
		
		private synchronized static void crearInstancia() {
			
			if (INSTANCIA == null) { 
		       INSTANCIA = new JuegoController();
		    }
		}
	
	
	private JuegoController(Jugador jugadorActual, Jugador jugadorEnemigo,CampoBatalla campoDeBatalla) throws NombreJugadorRepetidoException{
		JuegoController.getInstancia().setJugadorActual(jugadorActual);
		JuegoController.getInstancia().setJugadorEnemigo(jugadorEnemigo);
//		JuegoController.getInstancia().setCampoDeBatalla(campoDeBatalla);
	}
	
	
	
	
	public Armada obtenerArmadaJugadorEnemigo() {
		return JuegoController.getInstancia().getJugadorEnemigo().obtenerArmada();
	}
	
	public static Armada obtenerArmadaJugadorActual() {
		return getInstancia().getJugadorActual().obtenerArmada();
	}

	public Jugador getJugadorEnemigo() {
		return jugadorEnemigo;
	}

	public void setJugadorEnemigo(Jugador jugadorEnemigo) throws NombreJugadorRepetidoException {
		if(jugadorEnemigo.getNombre().equals(JuegoController.getInstancia().getJugadorActual().getNombre()))
			throw new NombreJugadorRepetidoException();
		JuegoController.jugadorEnemigo = jugadorEnemigo;
	}

	public Jugador getJugadorActual() {
		return jugadorActual;
	}

	public void setJugadorActual(Jugador jugadorActual) {
		JuegoController.jugadorActual = jugadorActual;
	}
	
	public String obtenerNombreJugadorActual() {
		return getInstancia().getJugadorActual().getNombre();
	}
	
	public String obtenerNombreJugadorEnemigo() {
		return getInstancia().getJugadorEnemigo().getNombre();
	}

	public TipoColor obtenerColorJugadorActual() {
		return getInstancia().getJugadorActual().getColor();
	}

	public TipoColor obtenerColorJugadorEnemigo() {
		return JuegoController.getInstancia().getJugadorEnemigo().getColor();
	}
	
	public void intercambiarJugadores() throws NombreJugadorRepetidoException {
		Jugador jugadorTemp = getInstancia().getJugadorActual();
		getInstancia().setJugadorActual(jugadorEnemigo);
		getInstancia().setJugadorEnemigo(jugadorTemp);
		
		getInstancia().actualizar();
	}

	private void actualizar() {
		
		getInstancia().getJugadorActual().actualizarUnidades();
		getInstancia().getJugadorEnemigo().actualizarUnidades();
		
	}


	public void agregarUnidadAJugadorEnemigo(ElementoArtificial elem) 
	throws ElementoInvalidoException, PosicionInvalidaException, RecursosInsuficientesException {
		getInstancia().getJugadorEnemigo().agregarElemento(elem);
	
	}
	
	public void agregarUnidadAJugadorActual(ElementoArtificial elem) 
	throws ElementoInvalidoException, PosicionInvalidaException, RecursosInsuficientesException {
		
		int cantidadDeCristal=getInstancia().getJugadorActual().getCantidadDeCristal();
		int cantidadDeGas= getInstancia().getJugadorActual().getCantidadDeGas();
		int costoMineral = elem.getCosto().getCostoMineral();
		int costoGas = elem.getCosto().getCostoGas();
		
		if(cantidadDeCristal<costoMineral&&cantidadDeGas<costoGas)
				throw new RecursosInsuficientesException();
		else
			getInstancia().getJugadorActual().agregarElemento(elem);
	}
	
	/**
	 * Verifica que exista al menos una unidad con vida en la lista de enemigos
	 * @throws FinDePartidaException
	 */
	public void verificarFinDePartida() throws FinDePartidaException {
		
		Armada armadaEnemiga = JuegoController.getInstancia().getJugadorEnemigo().obtenerArmada();
		
		List<ElementoArtificial> unidadesEnemigas = armadaEnemiga.getArmada();
		Iterator<ElementoArtificial> it = unidadesEnemigas.iterator();
		
		while(it.hasNext()) {
			if(!it.next().estaMuerta())
				return;		
		}
		throw new FinDePartidaException();
	}

}
