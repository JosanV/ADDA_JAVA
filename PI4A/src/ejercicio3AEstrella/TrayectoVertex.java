package ejercicio3AEstrella;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ejercicio3PD.DatosTrayecto;
import us.lsi.graphs.virtual.ActionVirtualVertex;

public class TrayectoVertex extends ActionVirtualVertex<TrayectoVertex, TrayectoEdge, Integer>{

	private Integer estacionActual;
	private Integer destino;
	private Integer index;
	private Integer costeAcumulado;
	
	public static TrayectoVertex of() {
		return new TrayectoVertex(DatosTrayecto.getOrigen(), DatosTrayecto.getDestino(), 0, 0);
	}
	
	public static TrayectoVertex lastVertex() {
		return new TrayectoVertex(DatosTrayecto.getOrigen(), DatosTrayecto.getDestino(), DatosTrayecto.getTrayectos().size(), Integer.MAX_VALUE);
	}
	
	private TrayectoVertex(Integer estacionActual, Integer destino, Integer index, Integer costeAcumulado) {
		super();
		this.estacionActual = estacionActual;
		this.destino = destino;
		this.index = index;
		this.costeAcumulado = costeAcumulado;
	}
	
	
	public Integer getEstacionActual() {
		return estacionActual;
	}
	public Integer getDestino() {
		return destino;
	}
	public Integer getIndex() {
		return index;
	}
	public Integer getCosteAcumulado() {
		return costeAcumulado;
	}

	public void setCosteAcumulado(Integer costeAcumulado) {
		this.costeAcumulado = costeAcumulado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estacionActual == null) ? 0 : estacionActual.hashCode());
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrayectoVertex other = (TrayectoVertex) obj;
		if (estacionActual == null) {
			if (other.estacionActual != null)
				return false;
		} else if (!estacionActual.equals(other.estacionActual))
			return false;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		return true;
	}

	@Override
	public Boolean isValid() {
		return true;
	}

	@Override
	public List<Integer> actions() {
		List<Integer> res = IntStream.range(0, DatosTrayecto.getTrayectos().size())
				.filter(i -> DatosTrayecto.getTrayecto(i).getOrigen() == estacionActual)
				.filter(i -> DatosTrayecto.getTrayecto(i).getDestino() <= destino)
				.boxed()
				.collect(Collectors.toList());
		return res;
	}

	@Override
	public TrayectoVertex neighbor(Integer a) {
		return new TrayectoVertex(DatosTrayecto.getTrayecto(a).getDestino(), destino, a, costeAcumulado+DatosTrayecto.getTrayecto(a).getCoste());
	}
	
	public TrayectoEdge getEdgeFromAction(Integer a) {
		TrayectoVertex v = this.neighbor(a);
		return TrayectoEdge.of(this, v, a);
	}
	
	public static Double heuristic(TrayectoVertex v1, TrayectoVertex v2) {
		return (double) v1.getCosteAcumulado();
	}
	
	
	
	
}
