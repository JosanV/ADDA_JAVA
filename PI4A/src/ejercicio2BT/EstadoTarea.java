package ejercicio2BT;

import java.util.ArrayList;
import java.util.List;

import ejercicio2PD.DatosTarea;
import ejercicio2PD.SolucionTarea;
import ejercicio2PD.Tarea;
import us.lsi.bt.EstadoBT;

public class EstadoTarea implements EstadoBT<SolucionTarea, Integer, EstadoTarea>{

	private Integer index;
	private List<Tarea> tareasCogidas;
	private Integer gananciaAcumulada;
	private SolucionTarea s;
	
	public static EstadoTarea createInitial() {
		return new EstadoTarea(0, new ArrayList<Tarea>(), 0, SolucionTarea.empty());
	}
	
	private EstadoTarea(Integer index, List<Tarea> tareasCogidas, Integer gananciaAcumulada, SolucionTarea s) {
		super();
		this.index = index;
		this.tareasCogidas = tareasCogidas;
		this.gananciaAcumulada = gananciaAcumulada;
		this.s = s;
	}
	
	public Integer getIndex() {
		return index;
	}

	public List<Tarea> getTareasCogidas() {
		return tareasCogidas;
	}

	public Integer getGananciaAcumulada() {
		return gananciaAcumulada;
	}

	public SolucionTarea getS() {
		return s;
	}

	@Override
	public Tipo getTipo() {
		return Tipo.Max;
	}

	@Override
	public EstadoTarea getEstadoInicial() {
		return EstadoTarea.createInitial();
	}

	@Override
	public EstadoTarea avanza(Integer a) {

		if(a == 1) {
			List<Tarea> l = new ArrayList<Tarea>(this.tareasCogidas);
			l.add(DatosTarea.getTarea(this.index));
			this.tareasCogidas = l;
			this.gananciaAcumulada = this.gananciaAcumulada + DatosTarea.getGanancia(index);
			this.s.add(this.index+1, DatosTarea.getTarea(index));
		}
		this.index = this.index+1;
		return this;
	}

	@Override
	public EstadoTarea retrocede(Integer a) {
		this.index = this.index-1;
		if(a == 1) {
			List<Tarea> l = new ArrayList<Tarea>(this.tareasCogidas);
			l.remove(l.size()-1);
			this.tareasCogidas = l;
			this.gananciaAcumulada = this.gananciaAcumulada - DatosTarea.getGanancia(index);
			this.s.remove(this.index+1, DatosTarea.getTarea(index));
		}
		return this;
	}

	@Override
	public int size() {
		return DatosTarea.getTareas().size() - index;
	}

	@Override
	public boolean esCasoBase() {
		return index == DatosTarea.getTareas().size();
	}
	
	public Double getObjetivo() {
		return (double) gananciaAcumulada;
	}

	@Override
	public List<Integer> getAlternativas() {
		List<Integer> res = new ArrayList<Integer>();
		res.add(0);
		if(tareasCogidas.stream().allMatch(t -> !t.seSolapaBoolean(DatosTarea.getTarea(index)))) {
			res.add(1);
		}
		return res;
	}

	@Override
	public SolucionTarea getSolucion() {
		return this.s.copy();
	}

}
