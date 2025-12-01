import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    private static TicketManager ticketManager = new TicketManager();
    private static ReportGenerator reportGenerator = new ReportGenerator();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // --- Inicialización de Datos (LAB-LIS) ---
        inicializarDatos();

        int opcion = -1;
        do {
            mostrarMenu();
            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                switch (opcion) {
                    case 1:
                        registrarIncidencia();
                        break;
                    case 2:
                        asignarTecnico();
                        break;
                    case 3:
                        actualizarEstado();
                        break;
                    case 4:
                        filtrarIncidencias();
                        break;
                    case 5:
                        generarReportes();
                        break;
                    case 0:
                        System.out.println("Saliendo del sistema. ¡Adiós!");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                scanner.nextLine(); // Consumir la entrada incorrecta
                opcion = -1;
            }
            System.out.println("\n---");
        } while (opcion != 0);

        scanner.close();
    }

    /**
     * Inicializa equipos y técnicos de prueba con la configuración solicitada.
     */
    private static void inicializarDatos() {
        System.out.println("Inicializando datos del sistema...");
        
        // 1. **Carga de Técnicos Solicitados**
        // Se eliminan los técnicos anteriores y se añaden los solicitados.
        ticketManager.agregarTecnico(new Tecnico(1, "Irwing Ibañez", "Redes"));
        ticketManager.agregarTecnico(new Tecnico(2, "Pino Herrera", "Hardware"));
        ticketManager.agregarTecnico(new Tecnico(3, "José Montes", "Software"));
        
        // 2. **Carga de Equipos de Cómputo (41 Equipos en total)**
        generarEquiposDeComputo();

        ticketManager.getTecnicos().forEach(t -> System.out.println("Técnico cargado: " + t.getNombre() + " (ID: " + t.getId() + ")"));
        System.out.println("Total de equipos cargados: " + ticketManager.getEquipos().size());
        System.out.println("Datos iniciales cargados.\n");
    }

    /**
     * Genera automáticamente 41 equipos: 1 para el docente y 40 para las 10 mesas.
     */
    private static void generarEquiposDeComputo() {
        
        // 1. **Equipo del Docente (ID 1)**
        ticketManager.agregarEquipo(new EquipoComputo(
            1, 
            "Escritorio del Docente", 
            "Windows 11"
        ));
        System.out.println("Equipo cargado: ID 1 (Docente)");

        // 2. **Equipos de Alumnos (IDs 2 a 41)**
        int totalEquipos = 40;
        int equiposPorMesa = 4;
        
        for (int i = 2; i <= (totalEquipos + 1); i++) {
            
            // i=2 a i=41. Restamos 2 para empezar la cuenta de mesas en 0 para i=2.
            // (2 - 2) / 4 = 0 -> Mesa 1
            // (5 - 2) / 4 = 0.75 -> Mesa 1
            // (6 - 2) / 4 = 1 -> Mesa 2
            // El índice de mesa es el número de la mesa (1 a 10)
            int numMesa = ((i - 2) / equiposPorMesa) + 1;
            String ubicacion = "Mesa " + numMesa;
            
            String so = "Windows 11";
            // La computadora 28 es la excepción.
            if (i == 28) {
                so = "Bazzite";
            }
            
            ticketManager.agregarEquipo(new EquipoComputo(i, ubicacion, so));
            // Opcional: imprimir los primeros para verificar la distribución
            // if (i <= 10 || i == 28 || i == 41) {
            //     System.out.println("Equipo cargado: ID " + i + " | Ubicación: " + ubicacion + " | SO: " + so);
            // }
        }
    }


    private static void mostrarMenu() {
        System.out.println("\nGESTIÓN DE INCIDENCIAS");
        System.out.println("-------------------------");
        System.out.println("1. Registrar Nueva Incidencia");
        System.out.println("2. Asignar Técnico a Incidencia");
        System.out.println("3. Actualizar Estado de Incidencia");
        System.out.println("4. Filtrar Incidencias por Estado");
        System.out.println("5. Generar Reportes");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }
    
    // --- 1. Registrar Nueva Incidencia ---
    private static void registrarIncidencia() {
        System.out.println("\nREGISTRO DE NUEVA INCIDENCIA");
        
        // 1. Datos del Usuario
        System.out.print("Nombre del usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Matrícula/ID del usuario: ");
        String matriculaUsuario = scanner.nextLine();
        Usuario usuario = new Usuario(nombreUsuario, matriculaUsuario);

        // 2. Selección de Equipo (simplificado)
        List<EquipoComputo> equipos = ticketManager.getEquipos();
        if (equipos.isEmpty()) {
            System.out.println("No hay equipos registrados para seleccionar.");
            return;
        }
        System.out.println("\nSeleccione Equipo:");
        for (int i = 0; i < equipos.size(); i++) {
            // Se muestra el ID real del equipo para facilitar la selección.
            System.out.println("  " + (i + 1) + ". ID: " + equipos.get(i).getIdEquipo() + " | Ubicación: " + equipos.get(i).getUbicacion() + " | SO: " + equipos.get(i).getSo());
        }
        System.out.print("Ingrese el número de la lista del equipo: ");
        int indiceEquipo = -1;
        try {
            indiceEquipo = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea
        } catch (InputMismatchException e) {
            System.out.println("Entrada no válida. Debe ingresar un número.");
            scanner.nextLine();
            return;
        }

        EquipoComputo equipoSeleccionado = null;
        try {
            if (indiceEquipo > 0 && indiceEquipo <= equipos.size()) {
                equipoSeleccionado = equipos.get(indiceEquipo - 1);
            } else {
                System.out.println("Selección de equipo no válida.");
                return;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Selección de equipo no válida.");
            return;
        }

        // 3. Descripción
        System.out.print("\nDescripción detallada de la incidencia: ");
        String descripcion = scanner.nextLine();

        // 4. Tipo de Incidencia
        Enums.TipoIncidencia tipoInc = seleccionarTipoIncidencia();
        if (tipoInc == null) return;

        // 5. Prioridad
        Enums.Prioridad prioridad = seleccionarPrioridad();
        if (prioridad == null) return;

        // Registro
        ticketManager.registrarIncidencia(usuario, equipoSeleccionado, descripcion, tipoInc, prioridad);
    }
    
    private static Enums.TipoIncidencia seleccionarTipoIncidencia() {
        System.out.println("\nSeleccione Tipo de Incidencia:");
        Enums.TipoIncidencia[] tipos = Enums.TipoIncidencia.values();
        for (int i = 0; i < tipos.length; i++) {
            System.out.println("  " + (i + 1) + ". " + tipos[i]);
        }
        System.out.print("Ingrese el número del tipo: ");
        try {
            int indiceTipo = scanner.nextInt();
            scanner.nextLine();
            if (indiceTipo > 0 && indiceTipo <= tipos.length) {
                return tipos[indiceTipo - 1];
            }
        } catch (InputMismatchException e) {
            scanner.nextLine();
        }
        System.out.println("Selección de tipo no válida.");
        return null;
    }

    private static Enums.Prioridad seleccionarPrioridad() {
        System.out.println("\nSeleccione Prioridad:");
        Enums.Prioridad[] prioridades = Enums.Prioridad.values();
        for (int i = 0; i < prioridades.length; i++) {
            System.out.println("  " + (i + 1) + ". " + prioridades[i]);
        }
        System.out.print("Ingrese el número de prioridad: ");
        try {
            int indicePrioridad = scanner.nextInt();
            scanner.nextLine();
            if (indicePrioridad > 0 && indicePrioridad <= prioridades.length) {
                return prioridades[indicePrioridad - 1];
            }
        } catch (InputMismatchException e) {
            scanner.nextLine();
        }
        System.out.println("Selección de prioridad no válida.");
        return null;
    }

    // --- 2. Asignar Técnico a Incidencia ---
    private static void asignarTecnico() {
        System.out.println("\nASIGNAR TÉCNICO");
        System.out.print("Ingrese ID de la incidencia a asignar (ej: INC-0001): ");
        String idIncidencia = scanner.nextLine().toUpperCase();
        
        Incidencia inc = ticketManager.buscarIncidenciaPorId(idIncidencia);
        if (inc == null) {
            System.out.println("Error: Incidencia con ID " + idIncidencia + " no encontrada.");
            return;
        }
        
        List<Tecnico> tecnicos = ticketManager.getTecnicos();
        if (tecnicos.isEmpty()) {
            System.out.println("No hay técnicos registrados para asignar.");
            return;
        }

        System.out.println("\nSeleccione Técnico:");
        for (int i = 0; i < tecnicos.size(); i++) {
            Tecnico t = tecnicos.get(i);
            System.out.println("  " + (i + 1) + ". " + t.getNombre() + " (Especialidad: " + t.getEspecialidad() + ", ID: " + t.getId() + ")");
        }
        System.out.print("Ingrese el número del técnico a asignar: ");
        
        try {
            int indiceTecnico = scanner.nextInt();
            scanner.nextLine(); 
            
            if (indiceTecnico > 0 && indiceTecnico <= tecnicos.size()) {
                Tecnico tecnicoSeleccionado = tecnicos.get(indiceTecnico - 1);
                ticketManager.asignarTecnico(idIncidencia, tecnicoSeleccionado);
            } else {
                System.out.println("Selección de técnico no válida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada no válida. Debe ser un número.");
            scanner.nextLine();
        }
    }

    // --- 3. Actualizar Estado de Incidencia ---
    private static void actualizarEstado() {
        System.out.println("\nACTUALIZAR ESTADO");
        System.out.print("Ingrese ID de la incidencia (ej: INC-0001): ");
        String idIncidencia = scanner.nextLine().toUpperCase();
        
        Incidencia inc = ticketManager.buscarIncidenciaPorId(idIncidencia);
        if (inc == null) {
            System.out.println("Error: Incidencia con ID " + idIncidencia + " no encontrada.");
            return;
        }
        
        System.out.println("Incidencia: " + inc.getId() + ", Estado actual: " + inc.getEstado());

        System.out.println("\nSeleccione Nuevo Estado:");
        Enums.EstadoIncidencia[] estados = Enums.EstadoIncidencia.values();
        for (int i = 0; i < estados.length; i++) {
            System.out.println("  " + (i + 1) + ". " + estados[i]);
        }
        System.out.print("Ingrese el número del nuevo estado: ");

        try {
            int indiceEstado = scanner.nextInt();
            scanner.nextLine();
            
            if (indiceEstado > 0 && indiceEstado <= estados.length) {
                Enums.EstadoIncidencia nuevoEstado = estados[indiceEstado - 1];
                if (ticketManager.actualizarEstado(idIncidencia, nuevoEstado)) {
                    System.out.println("Estado de Incidencia " + idIncidencia + " actualizado a " + nuevoEstado);
                } else {
                    System.out.println("Error al actualizar el estado.");
                }
            } else {
                System.out.println("Selección de estado no válida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada no válida. Debe ser un número.");
            scanner.nextLine();
        }
    }
    
    // --- 4. Filtrar Incidencias por Estado ---
    private static void filtrarIncidencias() {
        System.out.println("\nFILTRAR INCIDENCIAS POR ESTADO");
        
        System.out.println("Seleccione Estado para filtrar:");
        Enums.EstadoIncidencia[] estados = Enums.EstadoIncidencia.values();
        for (int i = 0; i < estados.length; i++) {
            System.out.println("  " + (i + 1) + ". " + estados[i]);
        }
        System.out.print("Ingrese el número del estado: ");
        
        try {
            int indiceEstado = scanner.nextInt();
            scanner.nextLine();
            
            if (indiceEstado > 0 && indiceEstado <= estados.length) {
                Enums.EstadoIncidencia estadoFiltro = estados[indiceEstado - 1];
                List<Incidencia> filtradas = ticketManager.filtrarIncidencias(estadoFiltro);
                
                System.out.println("\n--- INCIDENCIAS EN ESTADO: " + estadoFiltro + " (" + filtradas.size() + " total) ---");
                if (filtradas.isEmpty()) {
                    System.out.println("No se encontraron incidencias en ese estado.");
                } else {
                    for (Incidencia i : filtradas) {
                        String tecnicoInfo = i.getTecnico() != null ? i.getTecnico().getNombre() : "SIN ASIGNAR";
                        // Muestra el ID de la computadora también
                        System.out.println("ID: " + i.getId() + " | Equipo: " + i.getEquipo().getIdEquipo() + " (" + i.getEquipo().getUbicacion() + ") | Prioridad: " + i.getPrioridad() + " | Técnico: " + tecnicoInfo + " | Descripción: " + i.getDescripcion().substring(0, Math.min(i.getDescripcion().length(), 40)) + "...");
                    }
                }
            } else {
                System.out.println("Selección de estado no válida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada no válida. Debe ser un número.");
            scanner.nextLine();
        }
    }

    // --- 5. Generar Reportes ---
    private static void generarReportes() {
        System.out.println("\nGENERAR REPORTES");
        List<Incidencia> todasIncidencias = ticketManager.getListaIncidencias();
        
        if (todasIncidencias.isEmpty()) {
            System.out.println("No hay incidencias registradas para generar reportes.");
            return;
        }

        // Reporte 1: Conteo por Estado y Tipo
        System.out.println("--- REPORTE DE RESUMEN DE INCIDENCIAS ---");
        reportGenerator.generarReporteIncidencias(todasIncidencias);
        
        // Reporte 2: Carga de Trabajo por Técnico
        reportGenerator.generarReporteCargaTrabajoTecnico(todasIncidencias);

        // Reporte 3: Incidencias Pendientes por Prioridad
        reportGenerator.generarReportePrioridadPendiente(todasIncidencias);
    }
}