package ejercicio3AEstrella;

import ejercicio3PD.DatosTrayecto;
import us.lsi.graphs.virtual.ActionSimpleEdge;

public class TrayectoEdge extends ActionSimpleEdge<TrayectoVertex, Integer>{
	
	public Integer a;
	
	public static TrayectoEdge of(TrayectoVertex v1, TrayectoVertex v2, Integer a) {
		return new TrayectoEdge(v1, v2, a);
	}
	
	private TrayectoEdge(TrayectoVertex v1, TrayectoVertex v2, Integer a) {
		super(v1, v2);
		this.a = a;
		super.weight = (double) DatosTrayecto.getTrayecto(a).getCoste();	
	}

	@Override
	public String toString() {
		return "TrayectoEdge [a=" + a + "]";
	}
	
	

}
