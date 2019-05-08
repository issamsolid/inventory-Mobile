/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI;

import com.codename1.ui.Container;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.mycompany.Entity.Products;
import com.mycompany.Services.prodService;
import com.mycompany.myapp.MyApplication;
import java.io.IOException;

/**
 *
 * @author Don Solid
 */
public class getallprod {
    Form f;
    
    private static int idpr;
    private Resources theme;
    public static int getIdpr() {
        return idpr;
    }
    public static void setIdpr(int idpr) {
        getallprod.idpr = idpr;
    }
    private Object es;
    
    
 public getallprod() {   
        theme = UIManager.initFirstTheme("/theme");        
        f= new Form("Products",BoxLayout.y());
        f.setLayout(new  BoxLayout(BoxLayout.Y_AXIS));
        Container cn = new Container(new BorderLayout());
        Label lbText = new Label("EcoSys");
        cn.add(BorderLayout.SOUTH,lbText);
        f.getToolbar().addComponentToSideMenu(cn);
          f.getToolbar().addMaterialCommandToSideMenu("Home",FontImage.MATERIAL_ACCOUNT_CIRCLE, ev->{home h=new home();
        h.getF().show();});
        f.getToolbar().addMaterialCommandToSideMenu("All Products",FontImage.MATERIAL_ACCOUNT_BALANCE_WALLET, ev->{getallprod h=new getallprod();
        h.getF().show();});
        f.getToolbar().addMaterialCommandToSideMenu("My Products",FontImage.MATERIAL_PERSONAL_VIDEO, ev->{myproducts h= new myproducts();
        h.getF().show();});
        Form settings = new Form("Settings");
        settings.getToolbar().addCommandToLeftBar("Back", null, e->{settings.showBack();});   

        int mm = Display.getInstance().convertToPixels(3);
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 5, mm * 5, 0), false);
        
        
        prodService ws=new prodService(); 
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        for(Products w:ws.getList2())
         {   
             String url=w.getImagep();
             Image icon=URLImage.createToStorage(placeholder, url, url, URLImage.RESIZE_SCALE);
             data.add(createListEntry(w.getNomp(),w.getDescription(), icon,w.getPrix()));
         }
        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
        MultiList ml = new MultiList(model);
        f.add(ml);
         ml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println(ml.getSelectedIndex());
                idpr=ws.getList2().get(ml.getSelectedIndex()).getIdpr();
                detailprod detail = new detailprod();
                detail.getF().show();

            }
        });



}
private Map<String, Object> createListEntry(String nomp,String description, Image icon,Double prix ) {
  Map<String, Object> entry = new HashMap<>();
  entry.put("Line1", nomp);
  entry.put("Line2", description);
  entry.put("Line3", prix);
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
