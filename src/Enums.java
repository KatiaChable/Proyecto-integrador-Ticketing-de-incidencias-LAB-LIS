public class Enums {
    // Tipos de incidencia
    public enum TipoIncidencia{
        Red, Software, Hardware
    }

    // Prioridad de una incidencia
    public enum Prioridad{
        Baja, Media, Alta, Critica
    }

    // Estados de una incidencia
    public enum EstadoIncidencia{
        Nueva, Abierta, En_Proceso, Resuelta, Cerrada
    }
}
