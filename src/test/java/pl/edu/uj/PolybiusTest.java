package pl.edu.uj;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PolybiusTest {

  @Test
  void test_crypt() {
    String input = "jedli";
    String output = "6615143124";

    Polybius polybius = new Polybius();

    String encrypted = polybius.crypt(input);
    assertEquals(output, encrypted);
  }

  @Test
  void test_decrypt() {
    String input = "6615143124";
    String output = "jedli";

    Polybius polybius = new Polybius();

    String decrypted = polybius.decrypt(input);
    assertEquals(output, decrypted);
  }
}
