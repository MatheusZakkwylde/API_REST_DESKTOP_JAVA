
package com.project.api.client;

import com.project.api.client.dto.UsuarioDTO;
import com.project.api.client.resource.ClientResource;
import java.util.List;
public class Client {

  
   public static void main(String[] args)  {
   
    UsuarioDTO usuario = new UsuarioDTO();
    usuario.setNome("chico ");
    usuario.setEmail("matheuszakkwylde360@gmail.com");
    usuario.setPassword("123456");
    usuario.setUrl_foto_75x100(null);
    usuario.setUsername("Matheus");
    
    ClientResource cliente = new ClientResource();
    
    if(cliente.post(usuario)){
       System.out.println("Usuario cadastrado com sucesso!");
    }
    
    System.out.println("Usuários cadastrados no sistema");
    List<UsuarioDTO> listUsuario  = cliente.read();
    
    for(UsuarioDTO user : listUsuario ){
        System.out.println(user.getNome()+" "+user.getUsername()+" "+user.getEmail()+" "+user.getPassword());
    }   
 }
}
    
