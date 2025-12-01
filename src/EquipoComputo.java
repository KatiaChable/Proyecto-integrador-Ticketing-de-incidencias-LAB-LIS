public class EquipoComputo {
    private int idEquipo;
    private String ubicacion;
    private String so;

    // Constructor
    public EquipoComputo(int idEquipo, String ubicacion, String so) {
        this.idEquipo = idEquipo;
        this.ubicacion = ubicacion;
        this.so = so;
    }
    // Getters and Setters
    public int getIdEquipo() {
        return idEquipo;
    }
    public String getSo() {
        return so;
    }
    public String getUbicacion() {
        return ubicacion;
   }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }
    public void setSo(String so) {
        this.so = so;
    }
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}