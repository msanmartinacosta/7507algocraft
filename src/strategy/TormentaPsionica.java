package strategy;

import java.util.List;
import java.util.ListIterator;

import model.ElementoArtificial;
import controller.Posicion;
import exceptions.FactoryInvalidaException;

public class TormentaPsionica implements Strategy {
	
	private static final int RANGO_ATAQUE_TORMENTA_PSIONICA=10;
	private static final int DAŅO_TORMENTA_PSIONICA=100;

	@Override
	public void realizarAccion(Posicion posicionActual, String rangoAtaque, String daņo, List<ElementoArtificial> unidadesEnemigas) throws FactoryInvalidaException {
		
		ListIterator<ElementoArtificial> it = unidadesEnemigas.listIterator();
		
		while(it.hasNext())
		{
			ElementoArtificial elementoTemporal = it.next();
			
			Posicion posicionTemporal = elementoTemporal.getPosicion();
			
			String distancia = posicionTemporal.getDistancia(posicionActual);
			
			Long distanciaNum = Long.parseLong(distancia);
			
			String daņoTormentaPsionica=Long.toString(DAŅO_TORMENTA_PSIONICA);
			
			if(distanciaNum<RANGO_ATAQUE_TORMENTA_PSIONICA){
				elementoTemporal.restarVida(daņoTormentaPsionica);
				it.set(elementoTemporal);
			}
		}
	}

}
