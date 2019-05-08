/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entity.Products;
import com.mycompany.Services.prodService;
import com.mycompany.Services.prodService;


/**
 *
 * @author DELL
 */
public class detailprod {
    
    Form f;
    Label nomp;
    Label description;
    Label nbrviews; 
    Label prix; 
    public static String url; 

    

    public detailprod() {
         prodService es=new prodService();
        Products ar=es.FindById(getallprod.getIdpr()).get(0);
        
        f=new Form();
//        f.getToolbar().addCommandToRightBar("back", null, (ev)->{getallprod h=new getallprod();
//          h.getF().show();
//          });
        f.setLayout(new BoxLayout(BoxLayout.Y_AXIS));  
             
//        System.out.println(es.FindById(getallprod.getIdpr()).size());
        
        Container cn = new Container(new BorderLayout());
        Label lbText = new Label("Application name");
        cn.add(BorderLayout.SOUTH,lbText);
        f.getToolbar().addComponentToSideMenu(cn);
        if(ar.getIduser()==home.getIduser()){
        f.getToolbar().addCommandToRightBar("Edit", null, (ev)->{editprod modifier=new editprod();
        modifier.getF().show();
        });
        };
         f.getToolbar().addMaterialCommandToSideMenu("Home",FontImage.MATERIAL_ACCOUNT_CIRCLE, ev->{home h=new home();
        h.getF().show();});
        f.getToolbar().addMaterialCommandToSideMenu("All Products",FontImage.MATERIAL_ACCOUNT_CIRCLE, ev->{getallprod h=new getallprod();
        h.getF().show();});
        f.getToolbar().addMaterialCommandToSideMenu("My Products",FontImage.MATERIAL_PERSONAL_VIDEO, ev->{myproducts h= new myproducts();
        h.getF().show();});
        
      
        int mm = Display.getInstance().convertToPixels(3);
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 10, mm * 10, 0), false);
        
          
         //placeholder=EncodedImage.create("/workwork.jpg");
          
       

        nomp=new Label("Product :"+ar.getNomp());
        description=new Label("Description: "+ar.getDescription());
        prix = new Label("Price in DT:"+ar.getPrix());
        nbrviews = new Label("Nomber de views: "+ar.getNbreViews());
        url=ar.getImagep();

   
        
        Image icon=URLImage.createToStorage(placeholder, url, url, URLImage.RESIZE_SCALE_TO_FILL);   
        f.add(icon); 
        f.add(nomp);
        f.add(description);
        f.add(prix);
        f.add(nbrviews);
   
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
    
    
    
     
    
    
    
}