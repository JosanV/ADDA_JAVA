package ejercicio1BT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import ejercicio1PD.DatosEstanteria;
import ejercicio1PD.SolucionEstanteria;
import us.lsi.bt.EstadoBT;

public class EstadoEstanteria implements EstadoBT<SolucionEstanteria, Integer, EstadoEstanteria> {

	
	public static EstadoEstanteria createInitial() {
		return new EstadoEstanteria(0, DatosEstanteria.getListaAnchuras(), 0, SolucionEstanteria.empty());
	}
	
	private Integer index;
	private List<Integer> anchurasRestantes;
	private Integer librosAcumulados;
	private SolucionEstanteria s;

	private EstadoEstanteria(Integer index, List<Integer> anchurasRestantes, Integer librosAcumulados,
			SolucionEstanteria s) {
		super();
		this.index = index;
		this.anchurasRestantes = anchurasRestantes;
		this.librosAcumulados = librosAcumulados;
		this.s = s;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public List<Integer> getAnchurasRestantes() {
		return anchurasRestantes;
	}

	public void setAnchurasRestantes(List<Integer> anchurasRestantes) {
		this.anchurasRestantes = anchurasRestantes;
	}

	public Integer getLibrosAcumulados() {
		return librosAcumulados;
	}

	public void setLibrosAcumulados(Integer librosAcumulados) {
		this.librosAcumulados = librosAcumulados;
	}

	public SolucionEstanteria getS() {
		return s;
	}

	@Override
	public Tipo getTipo() {
		return Tipo.Max;
	}

	@Override
	public EstadoEstanteria getEstadoInicial() {
		return EstadoEstanteria.createInitial();
	}
	
	public Double getObjetivo() {
		return (double) librosAcumulados;
	}
	
//	public Double getObjetivoEstimado(Integer a) {
//		Double cota = (double) librosAcumulados;
//		if(a > -1) {
//			cota = cota + 1;
//		}
//		for (int i = index+1; i < DatosEstanteria.getLibros().size(); i++) {
//			cota = cota +
//		}
//	}


	@Override
	public EstadoEstanteria avanza(Integer a) {
		List<Integer> aux = new ArrayList<Integer>(this.anchurasRestantes);
		int libros = this.librosAcumulados;
		if(a > -1) {
			int n = aux.get(a) - DatosEstanteria.getAnchuraLibro(this.index);	//Para anchurasRestantes
			aux.set(a, n);
			libros = libros+1;	//Para libros acums
		}
		this.anchurasRestantes = aux;
		this.librosAcumulados = libros;
		this.s.add(DatosEstanteria.getLibros().get(this.index), a);
		this.index = this.index+1;
		return this;
	}

	@Override
	public EstadoEstanteria retrocede(Integer a) {
		this.index = this.index-1;
		List<Integer> aux = new ArrayList<Integer>(this.anchurasRestantes);
		int libros = this.librosAcumulados;
		if(a > -1) {
			int n = aux.get(a) + DatosEstanteria.getAnchuraLibro(this.index);	//Para anchurasRestantes
			aux.set(a, n);
			libros = libros-1;	//Para libros acums
		}
		this.anchurasRestantes = aux;
		this.librosAcumulados = libros;
		this.s.remove(DatosEstanteria.getLibros().get(this.index), a);
		return this;
	}

	@Override
	public int size() {
		return DatosEstanteria.getLibros().size() - index;
	}

	@Override
	public boolean esCasoBase() {
		return (this.index == DatosEstanteria.getLibros().size());
	}

	@Override
	public List<Integer> getAlternativas() {
		List<Integer> res = DatosEstanteria.getEstantes()
				.stream()
				.filter(p1 -> p1.getAltura() >= DatosEstanteria.getAlturaLibro(index))
				.filter(p2 -> this.anchurasRestantes.get(p2.getId()) >= DatosEstanteria.getAnchuraLibro(index))
				.map(e -> e.getId())
				.collect(Collectors.toList());
		res.add(-1);
		Collections.reverse(res);	//Para que de exactamente la misma solucion que nos han dado
		return res;
	}

	@Override
	public SolucionEstanteria getSolucion() {
		return this.s.copy();
	}

	@Override
	public String toString() {
		return "EstadoEstanteria [index=" + index + ", anchurasRestantes=" + anchurasRestantes + ", librosAcumulados="
				+ librosAcumulados + ", s=" + s + "]";
	}
	
	

}
