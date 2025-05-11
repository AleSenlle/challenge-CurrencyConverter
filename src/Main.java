import api.ExchangeRateAPI;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExchangeRateAPI api = new ExchangeRateAPI();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        System.out.println("¡Bienvenido al conversor de moneda!");

        do {
            mostrarMenu();
            System.out.print("Por favor, ingrese el número de la operación que desea realizar: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        convertirMoneda(api, "USD", "ARS", "Dólar (USD)", "Peso argentino (ARS)", scanner);
                        break;
                    case 2:
                        convertirMoneda(api, "ARS", "USD", "Peso argentino (ARS)", "Dólar (USD)", scanner);
                        break;
                    case 3:
                        convertirMoneda(api, "USD", "BRL", "Dólar (USD)", "Real brasileño (BRL)", scanner);
                        break;
                    case 4:
                        convertirMoneda(api, "BRL", "USD", "Real brasileño (BRL)", "Dólar (USD)", scanner);
                        break;
                    case 5:
                        convertirMoneda(api, "USD", "COP", "Dólar (USD)", "Peso colombiano (COP)", scanner);
                        break;
                    case 6:
                        convertirMoneda(api, "COP", "USD", "Peso colombiano (COP)", "Dólar (USD)", scanner);
                        break;
                    case 7:
                        convertirMoneda(api, "USD", "CLP", "Dólar (USD)", "Peso Chileno (CLP)", scanner);
                        break;
                    case 8:
                        convertirMoneda(api, "CLP", "USD", "Peso Chileno (CLP)", "Dólar (USD)", scanner);
                        break;
                    case 9:
                        System.out.println("¡Gracias por usar el conversor de monedas!");
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, ingrese un número del 1 al 9.");
                }
            } catch (Exception e) {
                System.out.println("Error: Ingrese solo números por favor.");
                scanner.nextLine();
                opcion = 0;  // Para que el bucle continue
            }

            if (opcion != 9) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }

        } while (opcion != 9);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n----------------------------------------");
        System.out.println("1) Convertir Dólar (USD) a Peso argentino (ARS)");
        System.out.println("2) Convertir Peso argentino (ARS) a Dólar (USD)");
        System.out.println("3) Convertir Dólar (USD) a Real brasileño (BRL)");
        System.out.println("4) Convertir Real brasileño (BRL) a Dólar (USD)");
        System.out.println("5) Convertir Dólar (USD) a Peso colombiano (COP)");
        System.out.println("6) Convertir Peso colombiano (COP) a Dólar (USD)");
        System.out.println("7) Convertir Dólar (USD) a Peso Chileno (CLP)");
        System.out.println("8) Convertir Peso Chileno (CLP) a Dólar (USD)");
        System.out.println("9) Salir");
        System.out.println("----------------------------------------");
    }

    private static void convertirMoneda(ExchangeRateAPI api, String fromCode, String toCode,
                                        String fromName, String toName, Scanner scanner) {
        try {
            System.out.printf("\nIngrese la cantidad en %s: ", fromName);
            double cantidad = scanner.nextDouble();

            double tasa = api.getExchangeRate(fromCode, toCode);
            double resultado = cantidad * tasa;

            System.out.printf("\n%.2f %s = %.2f %s\n", cantidad, fromName, resultado, toName);
            System.out.printf("Tasa de cambio: 1 %s = %.4f %s\n", fromCode, tasa, toCode);

        } catch (Exception e) {
            System.out.println("Error al realizar la conversión: " + e.getMessage());
        }
    }
}
