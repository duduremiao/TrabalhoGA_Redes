import java.io.*;
import java.net.*;

public class Client
{
    public static void main(String[] args)
    {
        DatagramSocket socket;
        int portaDestino = 5000;
        String texto;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try
        {
            socket = new DatagramSocket();

            InetAddress host = InetAddress.getByName("localhost");


            while(true)
            {
                //recebe a entrada e envia o pacote
                echo("Insira o comando : ");
                texto = br.readLine();
                byte[] b = texto.getBytes();

                DatagramPacket  pacote = new DatagramPacket(b , b.length , host , portaDestino);
                socket.send(pacote);

                //recebe a resposta
                //buffer pra receber a entrada de dados
                byte[] buffer = new byte[65536];
                DatagramPacket resposta = new DatagramPacket(buffer, buffer.length);
                socket.receive(resposta);

                byte[] data = resposta.getData();
                texto = new String(data, 0, resposta.getLength());

                //Imprime a porta destino
                echo("\n"+ texto);
                echo("Porta destino: " + resposta.getPort());
            }
        }

        catch(IOException e)
        {
            System.err.println("IOException " + e);
        }
    }


    //Imprime mensagem no terminal
    public static void echo(String mensagem)
    {
        System.out.println(mensagem);
    }
}