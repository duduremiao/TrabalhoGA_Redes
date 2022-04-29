
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server
{
    public static void main(String[] args)
    {
        DatagramSocket socket;
        File caminho;

        try
        {
            //criação de um socket de servidor, o parâmetro é o número da porta local
            socket = new DatagramSocket(5000);


            byte[] buffer = new byte[65536];
            DatagramPacket entrada = new DatagramPacket(buffer, buffer.length);


            echo("Server socket criado. Aguardando comandos...");


            while(true)
            {
                String texto;
                socket.receive(entrada);
                byte[] dados = entrada.getData();
                String comando = new String(dados, 0, entrada.getLength());
                texto = comando;
                //Imprime a porta de origem da mensagem do cliente
                echo("Porta Origem : " + entrada.getPort());

                //Definição caminho comandos
                caminho = new File("C:/Users/Public/Documents");
                ComandosTerminal.rodarComando(caminho, comando);

                texto = "COMANDO ENVIADO : " + texto;
                DatagramPacket pacote = new DatagramPacket(texto.getBytes() , texto.getBytes().length , entrada.getAddress() , entrada.getPort());
                socket.send(pacote);
            }
        }

        catch(IOException e)
        {
            System.err.println("IOException " + e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    //Imprime mensagem no terminal
    public static void echo(String mensagem)
    {
        System.out.println(mensagem);
    }
}