/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Services;

import com.mycompany.Entity.CatProd;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**

/**
 *
 * @author Don Solid
 */
public class catService {
    
//      public void ajoutws(CatProd w){
//        ConnectionRequest con= new ConnectionRequest();
//        String url= "http://localhost/pidevtow/web/app_dev.php/mobile/new?";
//        con.setUrl(url);
//        con.addArgument("nomws", w.getNomcp());
//       
//        //con.addArgument("iduser", 2+"");
//        //con.addArgument("idcw", 1+"");
//        
//        con.addResponseListener((e) -> {
//            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
//            System.out.println(str);//Affichage de la réponse serveur sur la console
//
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);
//        
//    }
    
    public ArrayList<CatProd> parseListTaskJson(String json) {

        ArrayList<CatProd> listTasks = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));     
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            //Parcourir la liste des tâches Json
            for(Map <String,Object> obj:list){
                //Création des tâches et récupération de leurs données
                CatProd e = new CatProd();
                float id = Float.parseFloat(obj.get("idcp").toString());
                e.setIdcp((int) id);
                e.setNomcp(obj.get("nomcp").toString());
                System.out.println(e);
                listTasks.add(e);
            }

        } catch (IOException ex) {
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        System.out.println(listTasks);
        return listTasks;

    }
    
    ArrayList<CatProd> listTasks = new ArrayList<>();
    
    public ArrayList<CatProd> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/getallcat");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                catService ser = new catService();
                listTasks = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
}
