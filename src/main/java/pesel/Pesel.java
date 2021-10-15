package pesel;

public class Pesel {
    private final String pesel;

    public Pesel(String pesel) {
        this.pesel = pesel;
    }

    public void compare(Pesel numer, Pesel numer2) {

        if(numer.pesel.equals(numer2.pesel)){
            System.out.println("Podane numery PESEL są takie same");
        }
        else
            System.out.println("PESELE są różne");

    }

    public static void check(Pesel numer) {
        int[] wage = new int[] {1,3,7,9,1,3,7,9,1,3};
        int[] position = new int[11];
        int sum = 0;

        for(int i=0;i<wage.length;i++) {
            char number = numer.pesel.charAt(i);
            position[i] = Integer.parseInt(String.valueOf(number));
            sum += (wage[i]*position[i]);
        }
        System.out.println("Suma: " + sum);
        int modulo = sum % 10;
        int last = Integer.parseInt(String.valueOf(numer.pesel.charAt(10)));
        System.out.println("Liczba kontrola podanego numeru PESEL wynosi:" + modulo);
        if((last == modulo) && ((sum+modulo) % 10 == 0)) {
            System.out.println("PESEL poprawny");
        }
        else
        {
            System.out.println("Numer PESEL niepoprawny");
        }

    }
}
