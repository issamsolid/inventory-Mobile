/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI;
import com.codename1.ui.Command;
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
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.Entity.Products;
import com.mycompany.Services.prodService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Don Solid
 */
public class myproducts {
    Form f;
    private Resources theme;
    public myproducts() {
    theme = UIManager.initFirstTheme("/theme");
        f=new Form();
        f.setLayout(new  BoxLayout(BoxLayout.Y_AXIS));
         int mm = Display.getInstance().convertToPixels(3);
         EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 5, mm * 5, 0), false);
        Container cn = new Container(new BorderLayout());
        Label lbText = new Label("EcoSys");
        Label lbImage = new Label(theme.getImage("logo.jpg"));
        cn.add(BorderLayout.WEST,lbImage);
        cn.add(BorderLayout.EAST,lbText);
        f.getToolbar().addComponentToSideMenu(cn);
        Command addMaterialCommandToSideMenu = f.getToolbar().addMaterialCommandToSideMenu("Home",FontImage.MATERIAL_ACCOUNT_CIRCLE, (ActionListener) (ActionEvent ev) -> {
            home h=new home();
            h.getF().show();
        });
        f.getToolbar().addMaterialCommandToSideMenu("All Products",FontImage.MATERIAL_ACCOUNT_CIRCLE, ev->{getallprod h=new getallprod();
        h.getF().show();});
        f.getToolbar().addMaterialCommandToSideMenu("My Products",FontImage.MATERIAL_PERSONAL_VIDEO, ev->{myproducts h= new myproducts();
        h.getF().show();});
        Form settings = new Form("Settings");
        settings.getToolbar().addCommandToLeftBar("Back", null, e->{settings.showBack();});   
         Image icon1=theme.getImage("delete.png");
         Image icon2=theme.getImage("pencil-edit-button.png");
         prodService es=new prodService();
         ArrayList<Map<String, Object>> data = new ArrayList<>();
         for(Products e:es.MyProduct(home.getIduser()))
         {
             String url=e.getImagep();
             Image icon=URLImage.createToStorage(placeholder, url, url, URLImage.RESIZE_SCALE);
             data.add(createListEntry(e.getNomp(), e.getDescription(), icon));
         }
         
         DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
        MultiList ml = new MultiList(model);
        f.add( ml);
  
  
    ml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                es.deleteprod(es.MyProduct(home.getIduser()).get(ml.getSelectedIndex()).getIdpr());
                                 Dialog.show("Manage My Products", "choose action", "Delete","cancel");

                myproducts h = new myproducts();
                h.getF().show();
                 
            }
        });
  

    f.getToolbar().addCommandToRightBar("back", null, (ev)->{getallprod consulte=new getallprod();
            consulte.getF().show();
            });

    f.getToolbar().addCommandToLeftBar("Add Product", null, (ev)->{addprod ajout=new addprod();
            ajout.getF().show();
            });     
    }   
    
    private Map<String, Object> createListEntry(String nomp, String description, Image icon) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", nomp);
        entry.put("Line2", description);
        entry.put("icon", icon);
        return entry;
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
}
