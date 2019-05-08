/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entity.Products;
import com.mycompany.Services.prodService;

/**
 *
 * @author Don Solid
 */
public class editprod {
      Form f;
    TextField titre;
    TextField text;
    TextField prix ; 
  

    Button modifier;
    Container c1;
    Container c2;
    String oldNomar="";
    
    Products e=new Products();
    
    prodService es=new prodService();

    public editprod() {
        f=new Form("Edit Product");
        f.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        titre=new TextField("", "Product Name");
        text=new TextField("", "Description");
        prix = new TextField("","Price");
        modifier=new Button("Edit");
   
        c1=new Container();
        c2=new Container();
       
        f.add(titre);
        f.add(text);
        f.add(prix);
        f.add(c1);
        f.add(c2);
        f.add(modifier);
        
        String newNomart="";
        
        f.getToolbar().addCommandToRightBar("Retour", null, (ev)->{getallprod h=new getallprod();
          h.getF().show();
          });
        
                for(Products ar:  es.FindById(getallprod.getIdpr()))
        {
            e=ar;
            oldNomar=ar.getNomp();
            titre.setText(ar.getNomp());
            text.setText(ar.getDescription());
            prix.setText(String.valueOf(ar.getPrix()));

     
        }
        
      modifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                      
                if((titre.getText().equals(""))||(text.getText().equals("")))
                    Dialog.show("Erreur", "All fields is required  ", "Ok", null);
               
               
                else
                {
               
                e.setNomp(titre.getText());
                e.setDescription(text.getText());
                es.editprod(e);
                myproducts h=new myproducts();
                h.getF().show();
                }
                } catch (NumberFormatException e) {
                     Dialog.show("Erreur", "La capacitee doit etre un chiffre ", "Ok", null);
                }
            }
        });
        
        
        
          
  Toolbar tb = f.getToolbar();
  f.getToolbar().addMaterialCommandToSideMenu("All Products",FontImage.MATERIAL_ACCOUNT_CIRCLE, ev->{getallprod h=new getallprod();
        h.getF().show();});
        f.getToolbar().addMaterialCommandToSideMenu("My Products",FontImage.MATERIAL_PERSONAL_VIDEO, ev->{myproducts h= new myproducts();
        h.getF().show();});
        Form settings = new Form("Settings");
        settings.getToolbar().addCommandToLeftBar("Back", null, e->{settings.showBack();});


                
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
