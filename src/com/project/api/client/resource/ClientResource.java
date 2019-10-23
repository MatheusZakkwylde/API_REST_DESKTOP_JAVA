package com.project.api.client.resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.api.client.dto.UsuarioDTO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.ProcessBuilder.Redirect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientResource {

    private static final String URL = "http://localhost:8080/mca/api/usuario/";

    public boolean post(UsuarioDTO usuario) {

        try {
            URL url = new URL(URL + "adicionar");
            HttpURLConnection connection;

            try {
                connection = (HttpURLConnection) url.openConnection(); //abre conexão
                try {
                    connection.setRequestMethod("POST"); //tipo do método
                    connection.setRequestProperty("Content-type", "application/json"); //cabeçalho
                    connection.setDoOutput(true); //enviar requisição

                    PrintStream printStream = new PrintStream(connection.getOutputStream());
                    Gson gson = new Gson();
                    printStream.println(gson.toJson(usuario)); //transforma o objeto em json e cria um pacote para enviar

                    connection.connect(); //envia requisição para o servidor
                    Scanner scanner = new Scanner(connection.getInputStream());//apenas pega o resultado da requisição ao servido
                    connection.disconnect();

                    return true;
                } catch (ProtocolException ex) {
                    Logger.getLogger(ClientResource.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClientResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<UsuarioDTO> read() {
       
        try {
            URL url = new URL(URL + "lista");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "+ conn.getResponseCode());
            }
            
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
         
             Gson gson = new Gson();
             TypeToken tt = new TypeToken<List<UsuarioDTO>>() {};
             List<UsuarioDTO> listUsuario = gson.fromJson(response.toString(), tt.getType());
             conn.disconnect();
             return listUsuario;

        } catch (IOException | RuntimeException e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
           return null;
    }
}
