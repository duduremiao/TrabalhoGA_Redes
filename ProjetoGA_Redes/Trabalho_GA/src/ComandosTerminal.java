import java.io.*;

public class ComandosTerminal {

    public static void rodarComando(File caminho, String comando) throws Exception {

        System.out.println("Comando: " + comando);

        ProcessBuilder builder = new ProcessBuilder();
        builder.directory(caminho);

        builder.command("powershell.exe", comando);


        Process process = builder.start();


        InputStream resp = process.getInputStream();
        InputStream erro = process.getErrorStream();

        printStream(resp);
        printStream(erro);

    }

    private static void printStream(InputStream inputStream) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String msg;
            while((msg = br.readLine()) != null) {
                System.out.println(msg);
            }

        }
    }
}