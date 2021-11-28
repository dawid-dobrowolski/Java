package pl.edu.uj;

public class ROT11 implements Algorithm {

  private static final String alphabet =
          "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
  private static final char[] tabOfAlphabet = alphabet.toCharArray();

  @Override
  public String crypt(String inputWord) {
    StringBuilder result = new StringBuilder();
    // TODO implement
    OUTER_lOOP: for(int i = 0; i < inputWord.length(); i++) {
      char c = inputWord.charAt(i);
      for (int j = 0; j < tabOfAlphabet.length; j++) {
        if (c == tabOfAlphabet[j]) {
          result.append(tabOfAlphabet[(j + 11) % tabOfAlphabet.length]);
          continue OUTER_lOOP;
        }
      }
      if(c == '.') result.append(".");
      else result.append(" ");
    }
    return result.toString();
  }


  // TODO implement
  public String decrypt(String inputWord){
    StringBuilder result = new StringBuilder();

    OUTER_lOOP: for(int i = 0; i < inputWord.length(); i++){
    char c = inputWord.charAt(i);
      for(int j = 0; j < tabOfAlphabet.length; j++){
        if(c == tabOfAlphabet[j]){
          if(j > 11) {
            result.append(tabOfAlphabet[j - 11]);
          }

          if(j < 11) {
            result.append(tabOfAlphabet[tabOfAlphabet.length - (11 - j)]);
          }
          if(j == 11) {
            result.append((tabOfAlphabet[26]));
          }
            continue OUTER_lOOP;
        }
      }

      result.append(c);

    }
    return result.toString();
  }


}
