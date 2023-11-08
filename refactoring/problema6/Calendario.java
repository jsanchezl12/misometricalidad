package refactoring.problema6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import refactoring.problema6.model.Dia;
import refactoring.problema6.model.Evento;

public class Calendario {
    private List<Dia> dias;

    public Calendario() {
        dias = new ArrayList<>();
    }

    public void setDias(List<Dia> dias) {
        this.dias = dias;
    }

    public List<Dia> getDias() {
        return dias;
    }

    private static void verEventosEnDia(Calendario calendario, int dia) {
        Dia diaSeleccionado = calendario.getDias().get(dia - 1);

        System.out.println("Eventos del día " + dia + ":");
        if (diaSeleccionado.getEventos().isEmpty()) {
            System.out.println("No hay eventos para este día.");
        } else {
            for (Evento evento : diaSeleccionado.getEventos()) {
                System.out.println(evento.getNombre());
            }
        }
    }

    private static void agregarEventoEnDia(Calendario calendario, int dia, String nombre) {
        Dia diaSeleccionado = calendario.getDias().get(dia - 1);
        Evento evento = new Evento(nombre);
        diaSeleccionado.getEventos().add(evento);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calendario calendario = new Calendario();
        for (int i = 0; i < 30; i++) {
            calendario.getDias().add(new Dia(i + 1));
        }
        System.out.println("Bienvenido al calendario.");
        while (true) {
            System.out.println("-------------------------");
            System.out.println("1. Ver eventos");
            System.out.println("2. Agregar evento");
            System.out.println("3. Salir");
            System.out.println("Seleccione una opción:");

            int opcion = scanner.nextInt();
            System.out.println("-------------------------");
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el día del que quiere ver los eventos:");
                    int dia = scanner.nextInt();
                    verEventosEnDia(calendario, dia);
                    break;
                case 2:
                    System.out.println("Ingrese el día en el que quiere agregar un evento:");
                    dia = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Ingrese el nombre del evento:");
                    String nombre = scanner.nextLine();
                    agregarEventoEnDia(calendario, dia, nombre);
                    break;
                case 3:
                    System.out.println("Gracias por usar el calendario.");
                    return;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }
}
