import java.util.*;

// La clase Card representa una carta individual de poker.
class Card {
    private final String suit; // Palo de la carta.
    private final String color; // Color de la carta, puede ser rojo o negro.
    private final String value; // Valor de la carta, puede ser del 2 al 10, A, J, Q o K.

    // Constructor de la clase Card.
    public Card(String suit, String color, String value) {
        this.suit = suit;
        this.color = color;
        this.value = value;
    }

    // Método toString para imprimir la información de la carta en el formato deseado.
    @Override
    public String toString() {
        return suit + "," + color + "," + value;
    }
}

// La clase Deck representa un conjunto de cartas de poker, es decir, la baraja.
class Deck {
    private final List<Card> cards; // Lista de cartas en el deck.

    // Constructor de la clase Deck.
    public Deck() {
        cards = new ArrayList<>();
        initializeDeck(); // Inicializa el deck con las 52 cartas.
    }

    // Método para inicializar el deck con las 52 cartas de poker.
    private void initializeDeck() {
        String[] suits = {"tréboles", "corazones", "picas", "diamantes"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "J", "Q", "K"};
        for (String suit : suits) {
            for (String value : values) {
                // Determina el color de la carta basado en el palo.
                String color = (suit.equals("corazones") || suit.equals("diamantes")) ? "rojo" : "negro";
                // Añade la nueva carta al deck.
                cards.add(new Card(suit, color, value));
            }
        }
    }

    // Método para mezclar el deck.
    public void shuffle() {
        Collections.shuffle(cards);
        System.out.println("Se mezcló el Deck.");
    }

    // Método para mostrar la primera carta del deck y removerla del mismo.
    public void head() throws Exception {
        if (!cards.isEmpty()) {
            Card card = cards.removeFirst();
            System.out.println(card);
            System.out.println("Quedan " + cards.size());
        } else {
            throw new Exception("Se han agotado las cartas");
        }
    }

    // Método para seleccionar una carta al azar del deck y removerla.
    public void pick() throws Exception {
        if (!cards.isEmpty()) {
            int randomIndex = (int) (Math.random() * cards.size());
            Card card = cards.remove(randomIndex);
            System.out.println(card);
            System.out.println("Quedan " + cards.size());
        } else {
            throw new Exception("Se han agotado las cartas");
        }
    }

    // Método para regresar un arreglo de cinco cartas del deck y removerlas.
    public void hand() throws Exception {
        if (cards.size() >= 5) {
            for (int i = 0; i < 5; i++) {
                this.pick(); // Utiliza el método pick para seleccionar cada carta.
            }
        } else {
            throw new Exception("Se han agotado las cartas");
        }
    }
}

// Clase principal que contiene el método main para ejecutar el programa.
public class CardGame {
    public static void main(String[] args) {
        Deck deck = new Deck(); // Crea un nuevo deck de cartas.
        Scanner scanner = new Scanner(System.in);

        int option = -1; // Inicializa la variable option con un valor por defecto.
        do {
            try {
                System.out.println("Bienvenido a Poker!");
                System.out.println("Selecciona una opción:");
                System.out.println("1 Mezclar deck");
                System.out.println("2 Sacar una carta");
                System.out.println("3 Carta al azar");
                System.out.println("4 Generar una mano de 5 cartas");
                System.out.println("0 Salir");
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        deck.shuffle();
                        break;
                    case 2:
                        deck.head();
                        break;
                    case 3:
                        deck.pick();
                        break;
                    case 4:
                        deck.hand();
                        break;
                    case 0:
                        System.out.println("¡Hasta luego!");
                        break;
                    default:
                        throw new Exception("Opción no válida");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Entrada no válida. Por favor, introduce un número.");
                scanner.next(); // Limpiar el buffer del scanner.
            }catch (Exception e) {
                System.out.println(e.getMessage());
                if (e.getMessage().equals("Se han agotado las cartas")) {
                    break; // Finalizar el programa si se agotan las cartas.
                }
            }
        } while (option != 0);

        scanner.close();
    }
}
