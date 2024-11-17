package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import clases.SortearEquipos;
import clases.EquipoInvalidoException;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int etapa = 0;

        do {
        	//Menú de opciones para el usuario
        	System.out.println("--------------------------------------");
            System.out.println("M E N U  D E  O P C I O N E S");
            System.out.println("--------------------------------------");
            System.out.println("1. Octavos de final");
            System.out.println("2. Cuartos de final");
            System.out.println("3. Semifinal");
            System.out.println("4. Final");
            System.out.println("5. Salir");
            System.out.println("--------------------------------------");
            System.out.print("Ingrese una opción: ");
            System.out.println("\n--------------------------------------");

            //Valida que la entrada sea un número entero
            while (!scanner.hasNextInt()) {
                System.out.println("Error: Debe ingresar un número válido entre 1 y 5.");
                scanner.next(); // Limpiar el buffer
                System.out.print("Ingrese una opción: ");
            }
            etapa = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
            
            SortearEquipos sorteo = new SortearEquipos();  //Crea instancia para manejar el sorteo
            int cantidadEquipos = 0;

            //Nos indica la cantidad de equipos según la etapa seleccionada
            switch (etapa) {
                case 1: // Octavos de final
                    cantidadEquipos = 16;
                    break;
                case 2: // Cuartos de final
                    cantidadEquipos = 8;
                    break;
                case 3: // Semifinal
                    cantidadEquipos = 4;
                    break;
                case 4: // Final
                    cantidadEquipos = 2;
                    break;
                case 5: // Salir
                    System.out.println("Gracias por usar el programa");
                    scanner.close();
                    return; // Salir del programa
                default:
                    System.out.println("Error. Ingrese una opción entre 1 y 5");
                    continue;  //volver al inicio del bucle
            }

            List<String> nombresEquiposIngresados = new ArrayList<>(); //Lista para almacenar los nombres de los equipos ingresados

            //Bucle para el ingreso de nombres de los equipos
            for (int i = 0; i < cantidadEquipos; i++) {
                String nombreEquipo;

                while (true) { // Bucle para asegurarnos que no se repita el nombre de los equipos
                    System.out.print("Equipo " + (i + 1) + ": ");
                    nombreEquipo = scanner.nextLine();

                    if (nombresEquiposIngresados.contains(nombreEquipo)) {
                        System.out.println("El equipo " + nombreEquipo + " ya ha sido ingresado. Por favor ingrese un nombre diferente.");
                    } else {
                        try {
                            sorteo.agregarEquipo(nombreEquipo); //Agregar equipo para el sorteo
                            nombresEquiposIngresados.add(nombreEquipo); // Agregar a la lista solo si no hay excepción
                            break; // Salir del bucle si se agrega correctamente
                        } catch (EquipoInvalidoException e) {
                            System.out.println(e.getMessage()); // Muestra el mensaje de error si el nombre no es válido
                        }
                    }
                }
            }

            // Realizar el sorteo según la etapa seleccionada
            try {
                sorteo.sortearPartidos(etapa);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); // Muestra mensaje de error si la etapa no es válida o falta equipos
            }

        } while (etapa != 5); 
        
        scanner.close();
    }
}