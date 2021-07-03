package ejercicio1AEstrella;

import ejercicio2PD.DatosTarea;
import us.lsi.graphs.virtual.ActionSimpleEdge;

public class EstanteriaEdge extends ActionSimpleEdge<EstanteriaVertex, Integer>{

	public static EstanteriaEdge of(EstanteriaVertex v1, EstanteriaVertex v2, Integer a) {
		return new EstanteriaEdge(v1, v2, a);
	}
	
	public Integer a;
	
	private EstanteriaEdge(EstanteriaVertex v1, EstanteriaVertex v2, Integer a) {
		super(v1, v2);
		this.a = a;
		super.weight = (double) -DatosTarea.getGanancia(v1.getIndex())*a;	//Para maximizar en negativo
	}

	@Override
	public String toString() {
		return "EstanteriaEdge [a=" + a + "]";
	}
	
}
