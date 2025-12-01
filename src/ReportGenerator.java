import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportGenerator {
    public void generarReporteIncidencias(List<Incidencia> incidencias) {
        // Contar incidencias por estado
        Map<Enums.EstadoIncidencia, Long> conteoPorEstado = incidencias.stream()
            .collect(Collectors.groupingBy(Incidencia::getEstado, Collectors.counting()));

        System.out.println("    Incidencias por Estado:");
        conteoPorEstado.forEach((estado, count) -> 
            System.out.println(" - " + estado + ": " + count)
        );

        // Contar incidencias por tipo
        Map<Enums.TipoIncidencia, Long> conteoPorTipo = incidencias.stream()
            .collect(Collectors.groupingBy(Incidencia::getTipo, Collectors.counting()));
        
        System.out.println("\n  Incidencias por Tipo");
        conteoPorTipo.forEach((tipo, count) -> 
            System.out.println(" - " + tipo + ": " + count)
        );
    }

    public void generarReporteCargaTrabajoTecnico(List<Incidencia> incidencias) {
        System.out.println("\n  REPORTE DE CARGA DE TRABAJO POR TÉCNICO");
        
        // Filtra incidencias que ya han sido asignadas a técnicos
        Map<Tecnico, Long> conteoPorTecnico = incidencias.stream()
            .filter(i -> i.getTecnico() != null)
            .collect(Collectors.groupingBy(Incidencia::getTecnico, Collectors.counting()));

        if (conteoPorTecnico.isEmpty()) {
            System.out.println("No hay incidencias asignadas a técnicos en este momento.");
            return;
        }

        System.out.println("Técnico             | Incidencias Asignadas");
        System.out.println("--------------------|----------------------");
        conteoPorTecnico.forEach((tecnico, count) -> 
            System.out.println(String.format("%-20s", tecnico.getNombre()) + "| " + count)
        );
    }

    public void generarReportePrioridadPendiente(List<Incidencia> incidencias) {
        System.out.println("\n REPORTE DE INCIDENCIAS PENDIENTES POR PRIORIDAD ");

        // Define estados que se consideran "pendientes" (no resuelta ni cerrada)
        List<Enums.EstadoIncidencia> estadosPendientes = List.of(
            Enums.EstadoIncidencia.Nueva, 
            Enums.EstadoIncidencia.Abierta, 
            Enums.EstadoIncidencia.En_Proceso
        );

        Map<Enums.Prioridad, Long> conteoPrioridadPendiente = incidencias.stream()
            .filter(i -> estadosPendientes.contains(i.getEstado()))
            .collect(Collectors.groupingBy(Incidencia::getPrioridad, Collectors.counting()));

        if (conteoPrioridadPendiente.isEmpty()) {
            System.out.println("Sin incidencias pendientes");
            return;
        }

        // Orden de Prioridad 
        System.out.println("Prioridad | Cantidad Pendiente");
        System.out.println("----------|-------------------");
        
        // Este bucle asegura que se muestren de Mayor a Menor Prioridad
        List.of(Enums.Prioridad.Critica, Enums.Prioridad.Alta, Enums.Prioridad.Media, Enums.Prioridad.Baja)
            .forEach(prioridad -> {
                Long count = conteoPrioridadPendiente.getOrDefault(prioridad, 0L);
                System.out.println(String.format("%-9s", prioridad.name()) + " | " + count);
            });
    }
}

