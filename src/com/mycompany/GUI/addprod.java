/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.Entity.Products;
import com.mycompany.Services.prodService;
import java.io.IOException;


/**
 *
 * @author Don Solid
 */
public class addprod {
    
    Form f;
    TextField nomp;
    TextField imagep;
    TextField prix;
    TextField description;
    Label msg ; 
    Label msg2 ; 
    Label dateExp;
    public addprod(){
        f=new Form();
        f.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        nomp=new TextField("", "Produc name");
        prix=new TextField("", "Price");
        description=new TextField("", "description");
        imagep=new TextField("", "imageUrl");
        msg = new Label("Expiration Date");
        msg2 = new Label ("Price In Tunisian Dinar "); 
        dateExp=new Picker();
         Button ajouter = new Button("Add Product");
         Container c1 = new Container();
         Container c2 = new Container();
        f.add(nomp);
        f.add(description);
        f.add(msg2); 
        f.add(prix);       
        f.add(imagep);
        f.add(msg);
        f.add(dateExp);
        f.add(c1);
        f.add(c2);
        f.add(ajouter);
        f.getToolbar().addCommandToRightBar("back", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                    getallprod h=new getallprod();
                getallprod consulte = null;
                    consulte = new getallprod();
                consulte.getF().show();
            }
        });
        Container cn = new Container(new BorderLayout());

        Label lbText = new Label("Application name");
        cn.add(BorderLayout.SOUTH,lbText);
        f.getToolbar().addComponentToSideMenu(cn);
        f.getToolbar().addMaterialCommandToSideMenu("All Products",FontImage.MATERIAL_ACCOUNT_CIRCLE, ev->{getallprod h=new getallprod();
        h.getF().show();});
        f.getToolbar().addMaterialCommandToSideMenu("My Products",FontImage.MATERIAL_PERSONAL_VIDEO, ev->{myproducts h= new myproducts();
        h.getF().show();});
        Form settings = new Form("Settings");
        settings.getToolbar().addCommandToLeftBar("Back", null, e->{settings.showBack();});
        f.getToolbar().addMaterialCommandToSideMenu("About",FontImage.MATERIAL_ACCOUNT_CIRCLE, ev->{});
        Form about = new Form("About");
        about.getToolbar().addCommandToLeftBar("Back", null, e->{about.showBack();
        about.show();});
        ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {                                 
                if((nomp.getText().equals(""))||(description.getText().equals(""))||(prix.getText().equals("")))
                    Dialog.show("Erreur", "Vous devez remplir touts les champs ", "Ok", null);               
                else
                {
                    Products e =new Products(nomp.getText(), Integer.valueOf(prix.getText()),dateExp.getText(),description.getText(),imagep.getText());
                    prodService es=new prodService();
                    es.addprod(e);
                    Dialog.show("Succes","Product Added", "OK", null);
                 }
                } catch (NumberFormatException e) {
                     Dialog.show("Erreur", "", "Ok", null);
                }
            }
        });
                
    }
        public Form getF() {
            return f;
        }
        
        public void setF(Form f) {
            this.f = f;
        }
    
}
