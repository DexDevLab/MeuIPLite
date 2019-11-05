package com.dex;

import javax.swing.*;
import java.awt.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author Daniel Augusto Monteiro de Almeida
 * @since 11/05/2019
 * @version 1.0.0-20191105-15
 *
 * Main Class.
 */
public class Main
{

    /** Main method. */
    public static void main(String[] args)
    {
        String ip = null; /* IP Address */
        String host = null; /* Hostname */
        InetAddress inetAddress = null; /* InetAddress to get localhost */
        try(DatagramSocket socket = new DatagramSocket())
        {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            ip = socket.getLocalAddress().getHostAddress();
            inetAddress = InetAddress.getLocalHost();
        }
        catch (SocketException | UnknownHostException ex)
        {
           noInternetDialog();
        }
        if (inetAddress != null)
        {
            host = inetAddress.getHostName();
        }
        if ("0.0.0.0".equals(ip))
        {
            noInternetDialog();
        }
        else
        {
            JTextArea jtext = new JTextArea(5, 20);
            jtext.setText("Hostname:\n" + host + "\n\nEndereço IP:\n" + ip);
            Font f = new Font("Tahoma", Font.BOLD, 14);
            jtext.setFont(f);
            jtext.setEnabled(true);
            JOptionPane.showMessageDialog
            (null,
                jtext,
                "Meu IP",
                JOptionPane.INFORMATION_MESSAGE
            );
        }

    }

    private static void noInternetDialog ()
    {
        JOptionPane.showMessageDialog
        (null,
            "O computador está sem conexão à internet. \n"
            + "Verifique sua conexão com a rede e tente novamente."
        );
        System.exit(0);
    }
}
