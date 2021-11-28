package pl.edu.uj;

import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

public class Cryptographer {

    private Cryptographer() {
    }

    public static void cryptfile(String pathToFileIn, String pathToFileOut, Algorithm algorithm)
            throws IOException {
        File fileIn = new File(pathToFileIn);
        File fileOut = new File(pathToFileOut);

        FileReader in = null;
        FileWriter out = null;

        in = new FileReader(fileIn);
        out = new FileWriter(fileOut);

        BufferedReader bufferread = null;
        bufferread = new BufferedReader(in);

        StringBuffer buffer = null;
        buffer = new StringBuffer();

        String l;
        while ((l = bufferread.readLine()) != null) {
            buffer.append(algorithm.crypt(l));
            buffer.append("\n");
        }

        BufferedWriter bufferwrite = null;
        bufferwrite = new BufferedWriter(out);

        bufferwrite.write(buffer.toString());
        bufferwrite.flush();

        in.close();
        out.close();

    }

    public static void decryptfile(String pathToFileIn, String pathToFileOut, Algorithm algorithm)
            throws IOException {
        File fileIn = new File(pathToFileIn);
        File fileOut = new File(pathToFileOut);

        FileReader in = null;
        FileWriter out = null;

        in = new FileReader(fileIn);
        out = new FileWriter(fileOut);

        BufferedReader bufferread = null;
        bufferread = new BufferedReader(in);

        StringBuffer buffer = null;
        buffer = new StringBuffer();

        String l;
        while ((l = bufferread.readLine()) != null) {
            buffer.append(algorithm.decrypt(l));
            buffer.append("\n");
        }

        BufferedWriter bufferwrite = null;
        bufferwrite = new BufferedWriter(out);

        bufferwrite.write(buffer.toString());
        bufferwrite.flush();

        in.close();
        out.close();
    }
}


