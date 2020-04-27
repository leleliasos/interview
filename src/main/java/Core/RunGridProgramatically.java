package Core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class RunGridProgramatically {

    public static void startHub() throws IOException, InterruptedException {
        String relativePath = "./selenium/selenium-server-standalone-3.9.1.jar";

        ProcessBuilder pb = new ProcessBuilder("java", "-jar", relativePath, "-role", "hub", "-host", "localhost");
        pb.redirectErrorStream(true); // redirect error stream to output stream
        Process proc = pb.start();

        String command = pb.command().toString();
        System.out.println("command:" + command);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()))) {
            for (String line; (line = br.readLine()) != null; ) {
                System.out.println(line);
            }
            System.out.println("Job running");
            proc.waitFor();
            System.out.println("Job finished");
        }
    }

    public static void main (String args[]) throws IOException, InterruptedException {
        startHub();
    }
}














