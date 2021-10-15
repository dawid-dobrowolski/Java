package pesel;

/*Napisz program sprawdzający poprawność numeru PESEL.
Program powinien składać się z dwóch klas: jednej z metodą main i osobnej klasy PESEL implementującej numer PESEL
PESEL powinien być reprezentowany w klasie jako String
PESEL ma konstruktor który przyjmuje String pesel jako parametr
PESEL ma funkcję compare, która przyjmuje jako parametr inny obiekt typu PESEL i porównuje czy PESELE są identyczne
PESEL powinien mieć statyczną metodę check sprawdzającą czy podany pesel jest OK.
 */
public class Main {
    public static void main(String[] args) {
        Pesel pesel = new Pesel("01235122310");
        Pesel pesel2 = new Pesel("01285127315");
        pesel.compare(pesel,pesel2);
        pesel.check(pesel);
    }
}
