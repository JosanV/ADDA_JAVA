package main;

public class Relacion {
	private Usuario source;
	private Usuario target;
	
	public static Relacion of() {
		return new Relacion();
	}
	
	public static Relacion ofFormat(Usuario u1, Usuario u2, String[] fmt) {
		return new Relacion(u1, u2);
	}
	
	
	private Relacion(Usuario source, Usuario target) {
		super();
		this.source = source;
		this.target = target;
	}
	
	private Relacion() {
		super();
		this.source = null;
		this.target = null;
	}

	public Usuario getSource() {
		return source;
	}

	public Usuario getTarget() {
		return target;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
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
		Relacion other = (Relacion) obj;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (target == null) {
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Relacion [source=" + source + ", target=" + target + "]";
	}
	
	
	
}
