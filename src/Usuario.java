public class Usuario {
    private String nombre;
    private String matricula;
    
    // Constructor
    public Usuario(String nombre, String matricula) {
        this.nombre = nombre;
        this.matricula = matricula;
    }

    // Getters and Setters
    public String getNombre() {
        return nombre;
    }
    public String getMatricula() {
        return matricula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
