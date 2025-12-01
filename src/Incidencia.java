import java.util.Date;

public class Incidencia {
    private String id;
    private Usuario usuario;
    private EquipoComputo equipo;
    private Tecnico tecnico;
    private String descripcion;
    private Date fechaReporte;
    private Enums.TipoIncidencia tipo;
    private Enums.Prioridad prioridad;
    private Enums.EstadoIncidencia estado;
    private Date fechaResolucion;

    // Constructor
    public Incidencia(String id, Usuario usuario, EquipoComputo equipo, String descripcion, Enums.TipoIncidencia tipo, Enums.Prioridad prioridad) {
        this.id = id;
        this.usuario = usuario;
        this.equipo = equipo;
        this.descripcion = descripcion;
        this.fechaReporte = new Date(); // Fecha actual
        this.tipo = tipo;
        this.prioridad = prioridad;
        this.estado = Enums.EstadoIncidencia.Nueva; // Estado inicial por defecto
        this.tecnico = null; // Se asigna después
        this.fechaResolucion = null;
    }

    public void setEstado(Enums.EstadoIncidencia nuevoEstado) {
        this.estado = nuevoEstado;
        if (nuevoEstado == Enums.EstadoIncidencia.Resuelta || nuevoEstado == Enums.EstadoIncidencia.Cerrada) {
            this.fechaResolucion = new Date();
        }
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public String getId() {
        return id;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public EquipoComputo getEquipo() {
        return equipo;
    }
    public Tecnico getTecnico() {
        return tecnico;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public Date getFechaReporte() {
        return fechaReporte;
    }
    public Enums.TipoIncidencia getTipo() {
        return tipo;
    }
    public Enums.Prioridad getPrioridad() {
        return prioridad;
    }
    public Enums.EstadoIncidencia getEstado() {
        return estado;
    }
    public Date getFechaResolucion() {
        return fechaResolucion;
    }


}
