package clases;

public class EquipoInvalidoException extends Exception {
    public EquipoInvalidoException(String mensaje) {
        super(mensaje); // Llama al constructor de la clase padre con el mensaje
    }
}