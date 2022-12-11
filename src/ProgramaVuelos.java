import java.util.Scanner;

import control.ControlVuelos;

public class ProgramaVuelos {
    public static void main(String[] args) throws Exception {
        ControlVuelos control = new ControlVuelos();

        Scanner scanner = new Scanner(System.in);

        
        System.out.print("\nElige el origen del vuelo: ");
        String origen = scanner.next();

        System.out.println();

        System.out.print("Elige el destino del vuelo: ");
        String destino = scanner.next();

        System.out.println(control.mostrarVuelosDisponibles(origen, destino));

        scanner.close();
    }
}
