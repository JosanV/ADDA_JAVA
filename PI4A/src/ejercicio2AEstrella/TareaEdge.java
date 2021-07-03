package ejercicio2AEstrella;

import us.lsi.graphs.virtual.ActionSimpleEdge;

public class TareaEdge extends ActionSimpleEdge<TareaVertex, Integer>{

	public static TareaEdge of(TareaVertex v1, TareaVertex v2, Integer a) {
		return new TareaEdge(v1, v2, a);
	}
	
	public Integer a;
	
	private TareaEdge(TareaVertex v1, TareaVertex v2, Integer a) {
		super(v1, v2);
		this.a = a;
		if(a > -1) {
			super.weight = -1.;	//Para maximizar en negativo
		}
	}

	@Override
	public String toString() {
		return "EstanteriaEdge [a=" + a + "]";
	}
	
}
