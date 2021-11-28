package pl.edu.uj;

import java.util.Locale;

public class Polybius implements Algorithm {

    public String crypt(String inputWord) {
        StringBuilder finalresult = new StringBuilder();
        int value;
        String finalResult = "";
        inputWord = inputWord.toUpperCase(Locale.ROOT);
        for (int i = 0; i < inputWord.length(); i++) {
            if (inputWord.charAt(i) >= 'A' && inputWord.charAt(i) <= 'Z') {
                value = toPolybius(inputWord.charAt(i));
                if (i == 0) {
                    finalResult = Integer.toString(value);
                } else finalResult = finalResult + Integer.toString(value);
            } else finalResult = finalResult + " ";
        }
        return finalResult.toString();
    }

    public static int toPolybius(char c) {
        int cryptValue = 0;
        switch (c) {
            case 'A':
                cryptValue = 11;
                break;
            case 'B':
                cryptValue = 12;
                break;
            case 'C':
                cryptValue = 13;
                break;
            case 'D':
                cryptValue = 14;
                break;
            case 'E':
                cryptValue = 15;
                break;
            case 'F':
                cryptValue = 21;
                break;
            case 'G':
                cryptValue = 22;
                break;
            case 'H':
                cryptValue = 23;
                break;
            case 'I':
                cryptValue = 24;
                break;
            case 'K':
                cryptValue = 25;
                break;
            case 'L':
                cryptValue = 31;
                break;
            case 'M':
                cryptValue = 32;
                break;
            case 'N':
                cryptValue = 33;
                break;
            case 'O':
                cryptValue = 34;
                break;
            case 'P':
                cryptValue = 35;
                break;
            case 'Q':
                cryptValue = 41;
                break;
            case 'R':
                cryptValue = 42;
                break;
            case 'S':
                cryptValue = 43;
                break;
            case 'T':
                cryptValue = 44;
                break;
            case 'U':
                cryptValue = 45;
                break;
            case 'V':
                cryptValue = 51;
                break;
            case 'W':
                cryptValue = 52;
                break;
            case 'X':
                cryptValue = 53;
                break;
            case 'Y':
                cryptValue = 54;
                break;
            case 'Z':
                cryptValue = 55;
                break;
            case 'J':
                cryptValue = 66;
                break;
        }
        return cryptValue;
    }

   /* public String makeCleanString(String inputWord) {
        String cleanString = "";
        for(int i=0; i < inputWord.length(); i++) {
            if(isValidChar(inputWord.charAt(i)))
                cleanString += inputWord.charAt(i);
        }
        return cleanString;
    }*/

   /* public Boolean isValidChar(char c) {
        return ((c>='A' && c<='Z')|| c == ' ');
    }*/

    public Boolean checkIfHaveWhiteSpace(String inputWord) {
        for (int i = 0; i < inputWord.length(); i++) {
            if (inputWord.charAt(i) == ' ') {
                return true;
            }
        }
        return false;
    }


    public String decrypt(String inputWord) {
        StringBuilder deResult = new StringBuilder();
        String temp;
        String part = null;
        char decryptChar;
        int spaceIndex = 0;

        int amount = 0;
        int i;
        inputWord = inputWord.trim();
        boolean flag = checkIfHaveWhiteSpace(inputWord);
        if (flag == true) {
            String[] tab = inputWord.split("[ ]+");
            System.out.println(tab[0]);
            inputWord = tab[0] + " " + tab[1];
            spaceIndex = tab[0].length() + 1;
        }
        inputWord = inputWord.replaceAll(" ", "");
        int count = inputWord.length();

        for (i = 0; i < count; i = i + 2) {
            if (i + 2 <= inputWord.length())
                part = inputWord.substring(i, i + 2);
            amount = Integer.parseInt(part);
            decryptChar = toNormal(amount);
            decryptChar = Character.toLowerCase(decryptChar);
            deResult.append(decryptChar);
            if (flag == true) {
                if (i + 3 == spaceIndex) {
                    deResult.append(" ");
                }
            }
            if (i == count - 1) {
                deResult.append(" ");
            }
        }
        return deResult.toString();
    }

    public char toNormal(int amount) {
        char uncryptChar = 0;
        switch (amount) {
            case 11:
                uncryptChar = 'A';
                break;
            case 12:
                uncryptChar = 'B';
                break;
            case 13:
                uncryptChar = 'C';
                break;
            case 14:
                uncryptChar = 'D';
                break;
            case 15:
                uncryptChar = 'E';
                break;
            case 21:
                uncryptChar = 'F';
                break;
            case 22:
                uncryptChar = 'G';
                break;
            case 23:
                uncryptChar = 'H';
                break;
            case 24:
                uncryptChar = 'I';
                break;
            case 25:
                uncryptChar = 'K';
                break;
            case 31:
                uncryptChar = 'L';
                break;
            case 32:
                uncryptChar = 'M';
                break;
            case 33:
                uncryptChar = 'N';
                break;
            case 34:
                uncryptChar = 'O';
                break;
            case 35:
                uncryptChar = 'P';
                break;
            case 41:
                uncryptChar = 'Q';
                break;
            case 42:
                uncryptChar = 'R';
                break;
            case 43:
                uncryptChar = 'S';
                break;
            case 44:
                uncryptChar = 'T';
                break;
            case 45:
                uncryptChar = 'U';
                break;
            case 51:
                uncryptChar = 'V';
                break;
            case 52:
                uncryptChar = 'W';
                break;
            case 53:
                uncryptChar = 'X';
                break;
            case 54:
                uncryptChar = 'Y';
                break;
            case 55:
                uncryptChar = 'Z';
                break;
            case 66:
                uncryptChar = 'J';
                break;
        }
        return uncryptChar;
    }
}
