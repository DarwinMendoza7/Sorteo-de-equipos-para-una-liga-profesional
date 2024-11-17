package clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortearEquipos {
    private List<Equipo> equipos;  //Lista para almacenar los equipos participantes
    private ArchivoSorteo archivoSorteo;  //Instancia para manejar la escritura en archivo
    
    public SortearEquipos() {
        this.equipos = new ArrayList<>();
        this.archivoSorteo = new ArchivoSorteo("sorteo.txt"); // Nombre del archivo donde se guardará los resultados
    }

    public void agregarEquipo(String nombre) throws EquipoInvalidoException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new EquipoInvalidoException("El nombre del equipo no puede ser nulo o vacío.");
        }
        equipos.add(new Equipo(nombre));
    }

    public void sortearPartidos(int etapa) throws IllegalArgumentException {
        int equiposRequeridos;

        switch (etapa) {
            case 1: // Octavos de final 
                equiposRequeridos = 16;
                String tituloOctavos = "---------------Octavos de Final---------------";
                
                System.out.println(tituloOctavos);
                archivoSorteo.guardarSorteo(tituloOctavos);
                break;
            case 2: // Cuartos de final
                equiposRequeridos = 8;
                String tituloCuartos = "---------------Cuartos de Final---------------";
                
                System.out.println(tituloCuartos);
                archivoSorteo.guardarSorteo(tituloCuartos);
                break;
            case 3: // Semifinal
                equiposRequeridos = 4;
                String tituloSemifinal="---------------Semifinal---------------";
                
                System.out.println(tituloSemifinal);
                archivoSorteo.guardarSorteo(tituloSemifinal);
                break;
            case 4: // Final
                equiposRequeridos = 2;
                String tituloFinal="---------------Final---------------";
                
                System.out.println(tituloFinal);
                archivoSorteo.guardarSorteo(tituloFinal);
                break;
            default:
                throw new IllegalArgumentException("Etapa no válida."); //Lanza excepción si la etapa no es válida
        }

        // Verifica si hay la cantidad necesaria de equipos
        if (equipos.size() != equiposRequeridos) {
            throw new IllegalArgumentException("Se requieren exactamente " + equiposRequeridos + " equipos para la etapa seleccionada.");
        }

        Collections.shuffle(equipos); // Mezcla aleatoriamente los equipos para el sorteo
        List<String> partidos = crearPartidosRecursivo(etapa, equipos);
        System.out.println("Partidos: ");
        for (String partido : partidos) {
            System.out.println(partido);
            archivoSorteo.guardarSorteo(partido);  //Guarda cada partido en el archivo
        }
    }
    //Método recursivo para crear partidos
    private List<String> crearPartidosRecursivo(int etapa, List<Equipo> equipos) {
        List<String> partidos = new ArrayList<>();
        int numeroPartidos;

        switch (etapa) {
            case 1: // Octavos de final
                numeroPartidos = 8; // 16 equipos
                break;
            case 2: // Cuartos de final
                numeroPartidos = 4; // 8 equipos
                break;
            case 3: // Semifinal
                numeroPartidos = 2; // 4 equipos
                break;
            case 4: // Final
                numeroPartidos = 1; // 2 equipos
                break;
            default:
                throw new IllegalArgumentException("Etapa no válida.");
        }

        for (int i = 0; i < numeroPartidos; i++) {
            if (equipos.size() < 2) break; // Nos aseguramos que haya suficientes equipos

            Equipo equipo1 = equipos.remove(0);
            Equipo equipo2 = equipos.remove(0);

            partidos.add(equipo1.getNombre() + " vs " + equipo2.getNombre());
        }
        return partidos;
    }
}

