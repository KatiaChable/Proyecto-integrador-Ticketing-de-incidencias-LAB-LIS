import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TicketManager {
    private List<Incidencia> listaIncidencias;
    private List<EquipoComputo> listaEquipos;
    private List<Tecnico> listaTecnicos;
    private int contadorIncidencias = 1; // Para generar IDs únicos

    public TicketManager() {
        this.listaIncidencias = new ArrayList<>();
        this.listaEquipos = new ArrayList<>();
        this.listaTecnicos = new ArrayList<>();
        // Lógica para precargar datos iniciales del LAB-LIS aquí
    }

    // Permite registrar una nueva incidencia reportada por un usuario.

    public Incidencia registrarIncidencia(Usuario usuario, EquipoComputo equipo, String descripcion, Enums.TipoIncidencia tipo, Enums.Prioridad prioridad) {
        String idIncidencia = String.format("INC-%04d", contadorIncidencias++);
        Incidencia nuevaIncidencia = new Incidencia(idIncidencia, usuario, equipo, descripcion, tipo, prioridad);
        listaIncidencias.add(nuevaIncidencia);
        System.out.println("Incidencia " + idIncidencia + " registrada con éxito.");
        return nuevaIncidencia;
    }

    // Asigna técnico a una incidencia.
    public boolean asignarTecnico(String idIncidencia, Tecnico tecnico) {
        Incidencia inc = buscarIncidenciaPorId(idIncidencia);
        if (inc != null) {
            inc.setTecnico(tecnico); // Asignar el técnico
            inc.setEstado(Enums.EstadoIncidencia.Abierta); // Cambiar el estado a Abierta/Asignada
            System.out.println("Incidencia " + idIncidencia + " asignada a " + tecnico.getNombre());
            return true;
        }
        return false;
    }

    // Permite al técnico poder cambiar el estado de una incidencia

    public boolean actualizarEstado(String idIncidencia, Enums.EstadoIncidencia nuevoEstado) {
        Incidencia inc = buscarIncidenciaPorId(idIncidencia);
        if (inc != null) {
            inc.setEstado(nuevoEstado);
            return true;
        }
        return false;
    }

    // Consulta y filtra incidencias por criterio

    public List<Incidencia> filtrarIncidencias(Enums.EstadoIncidencia estado) {
        return listaIncidencias.stream()
                .filter(i -> i.getEstado() == estado)
                .collect(Collectors.toList());
    }
    
    // Método auxiliar
    public Incidencia buscarIncidenciaPorId(String id) {
        return listaIncidencias.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void agregarEquipo(EquipoComputo equipo) {
        listaEquipos.add(equipo);
    }
    public void agregarTecnico(Tecnico tecnico) {
        listaTecnicos.add(tecnico);
    }
    public List<EquipoComputo> getEquipos() {
        return listaEquipos;
    }
    public List<Tecnico> getTecnicos() {
        return listaTecnicos;
    }
    public List<Incidencia> getListaIncidencias() {
    return listaIncidencias;
    }
}