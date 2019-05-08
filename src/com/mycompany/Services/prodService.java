/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entity.Products;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class prodService {
    
        public void editprod(Products ta) {
                    
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://127.0.0.1:8000/updateprod/"+ta.getIdpr()+"/?"+ "nomp="+ta.getNomp()+"&"+"description="+ta.getDescription()+"&"+"prix="+ta.getPrix();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
        
        public void addprod(  Products ta) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://127.0.0.1:8000/newprod?"+"nomp="+ta.getNomp()+"&"+"description="+ta.getDescription()+"&"+"prix="+ta.getPrix()+"&"+"imagep="+ta.getImagep()+"&"+"date_exp="+ta.getDate_exp();
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
       
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console
           

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
        
        

           public ArrayList<Products> parseListTaskJson(String json) { 
          
        ArrayList<Products> listTasks = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));      
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {
                Products e = new Products();
                
                float idpr = Float.parseFloat(obj.get("idpr").toString());
                float idpr1 = Float.parseFloat(obj.get("views").toString());
                float iduser = Float.parseFloat(obj.get("iduser").toString());
                e.setIdpr((int) idpr);
                e.setIduser((int) iduser);
                e.setNomp(obj.get("nomp").toString());
                e.setDescription(obj.get("description").toString());
                e.setPrix(Double.parseDouble(obj.get("prix").toString()));
                e.setNbreViews((int)idpr1);
                e.setImagep(obj.get("imagep").toString());
                System.out.println(e);
                
                listTasks.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listTasks);
        return listTasks;

    }
    ArrayList<Products> listTasks = new ArrayList<>();
    public ArrayList<Products> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/getallprod");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                prodService ser = new prodService();
                listTasks = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
    public ArrayList<Products> FindById(int idpr){      
        
        ConnectionRequest con = new ConnectionRequest();
            String url="http://127.0.0.1:8000/findprod/"+idpr+"?";
        System.out.println(url);
        con.setUrl(url);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                prodService ser = new prodService();
                listTasks = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
    public ArrayList<Products> MyProduct(int iduser)
     {    
         
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/getmyprod/"+iduser);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                prodService ser = new prodService();
                listTasks = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

    public void deleteprod(int idpr) {
     ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/deleteprod/"+idpr);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                prodService ser = new prodService();
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}