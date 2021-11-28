package pl.edu.uj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.MessageFormat;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /*
     * Usage:
     * 1. tocrypt.txt encrypt-rot.txt crypt rot
     * 2. decrypt.txt decrypt-rot.txt decrypt rot
     * 3. poli-input.txt encrypt-polib.txt crypt polib
     * 4. poli-output.txt decrypt-polib.txt decrypt polib
     * */
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Error: niepoprawna ilosc argumnetow");
            System.exit(-1);
        }
        String inputFile = args[0];
        String outputFile = args[1];
        boolean crypt = args[2].equals("crypt");
        Algorithm algorithm;
        if (args[3].equals("rot")) {
            algorithm = new ROT11();
        } else algorithm = new Polybius();
        logger.info(MessageFormat.format("Started {0}: {1} -> {2}", args[2], inputFile, outputFile));

        try {
            if (crypt) {
                Cryptographer.cryptfile(inputFile, outputFile, algorithm);
            } else {
                Cryptographer.decryptfile(inputFile, outputFile, algorithm);
            }
        } catch (IOException e) {
            logger.error(
                    MessageFormat.format(
                            "! Could not complete {0}: {1} -> {2}", args[2], inputFile, outputFile),
                    e);
        }
    }
}
