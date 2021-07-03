package ejercicio3BT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ejercicio3PD.DatosTrayecto;
import ejercicio3PD.SolucionTrayecto;
import us.lsi.bt.EstadoBT;

public class EstadoTrayecto implements EstadoBT<SolucionTrayecto, Integer, EstadoTrayecto>{

	private Integer index;
	private List<Integer> indexVisitados;
	private Integer destino;
	private Integer estacionActual;
	private Integer costeAcumulado;
	private SolucionTrayecto s;
	
//	public static EstadoTrayecto createInitial(Integer origen, Integer destino) {
//		return new EstadoTrayecto(0, destino, origen, , s)
//	}

	public static EstadoTrayecto createInitial() {
		return new EstadoTrayecto(0, new ArrayList<Integer>(), DatosTrayecto.getDestino(), 
				DatosTrayecto.getOrigen(),0, SolucionTrayecto.empty());
	}
	
	
	private EstadoTrayecto(Integer index, List<Integer> indexVisitados, Integer destino, Integer estacionActual, Integer costeAcumulado,
			SolucionTrayecto s) {
		super();
		this.index = index;
		this.indexVisitados = indexVisitados;
		this.destino = destino;
		this.estacionActual = estacionActual;
		this.costeAcumulado = costeAcumulado;
		this.s = s;
	}
	
	
	

	public Integer getIndex() {
		return index;
	}


	public List<Integer> getIndexVisitados() {
		return indexVisitados;
	}


	public Integer getDestino() {
		return destino;
	}


	public Integer getEstacionActual() {
		return estacionActual;
	}


	public Integer getCosteAcumulado() {
		return costeAcumulado;
	}


	public SolucionTrayecto getS() {
		return s;
	}


	@Override
	public Tipo getTipo() {
		return Tipo.Min;
	}

	@Override
	public EstadoTrayecto getEstadoInicial() {
		return EstadoTrayecto.createInitial();
	}

	@Override
	public EstadoTrayecto avanza(Integer a) {
		this.indexVisitados.add(index);
		this.estacionActual = DatosTrayecto.getTrayecto(a).getDestino();
		this.costeAcumulado = this.costeAcumulado + DatosTrayecto.getTrayecto(a).getCoste();
		this.s.add(a, DatosTrayecto.getTrayecto(a));
		this.index = a;
		return this;
	}

	@Override
	public EstadoTrayecto retrocede(Integer a) {
		List<Integer> aux = new ArrayList<Integer>(this.indexVisitados);
		this.index = aux.get(aux.size()-1);
		this.indexVisitados.remove(indexVisitados.size()-1);
		this.estacionActual = DatosTrayecto.getTrayecto(index).getDestino();
		this.costeAcumulado = this.costeAcumulado - DatosTrayecto.getTrayecto(a).getCoste();
		this.s.remove(a, DatosTrayecto.getTrayecto(a));
		return this;
	}

	@Override
	public int size() {
		return DatosTrayecto.getTrayectos().size() - index;
	}

	@Override
	public boolean esCasoBase() {
		return (estacionActual == destino);	
	}

	@Override
	public List<Integer> getAlternativas() {
		List<Integer> res = IntStream.range(0, DatosTrayecto.getTrayectos().size())
				.filter(i -> DatosTrayecto.getTrayecto(i).getOrigen() == estacionActual)
				.filter(i -> DatosTrayecto.getTrayecto(i).getDestino() <= destino)
				.boxed()
				.sorted(Collections.reverseOrder())	//Para que la solucion coincida con la del PDF
				.collect(Collectors.toList());
		return res;
	}
	
	public Double getObjetivo() {
		return (double) costeAcumulado;
	}

	@Override
	public SolucionTrayecto getSolucion() {
		return s.copy();
	}
	
	
}
