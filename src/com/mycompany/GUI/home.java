/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
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
import com.mycompany.myapp.MyApplication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Don Solid
 */
public class home {
    private static int idpr;
    Form f;
    private final Resources theme;
    private static int iduser=2;
    public static int getIduser() {
        return iduser;
    }
    public static void setIduser(int iduser) {
        home.iduser = iduser;
    }

    public home() {
    theme = UIManager.initFirstTheme("/theme");  
    Form f = new Form("Search", BoxLayout.y());
        Container cn = new Container(new BorderLayout());
        Label lbText = new Label("EcoSys");
        cn.add(BorderLayout.SOUTH,lbText);
        f.getToolbar().addComponentToSideMenu(cn);
        f.getToolbar().addMaterialCommandToSideMenu("Home",FontImage.MATERIAL_HOME, ev->{home h=new home();
        h.getF().show();});
        f.getToolbar().addMaterialCommandToSideMenu("All Products",FontImage.MATERIAL_ACCOUNT_BALANCE_WALLET, ev->{getallprod h=new getallprod();
        h.getF().show();}); 
        f.getToolbar().addMaterialCommandToSideMenu("My Products",FontImage.MATERIAL_PERSONAL_VIDEO, ev->{myproducts h= new myproducts();
        h.getF().show();});
        Form settings = new Form("Settings");
        settings.getToolbar().addCommandToLeftBar("Back", null, e->{settings.showBack();});     
        int fiveMM = Display.getInstance().convertToPixels(5);
        Toolbar.setGlobalToolbar(true);
        f.add(new InfiniteProgress());
        Display.getInstance().scheduleBackgroundTask(()-> {
        prodService ws=new prodService(); 
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        Display.getInstance().callSerially(() -> {
        f.removeAll();
        int mm = Display.getInstance().convertToPixels(3);
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 5, mm * 5, 0), false);
        
        for(Products w:ws.getList2()) {
            String url=w.getImagep();
            Image icon=URLImage.createToStorage(placeholder, url, url, URLImage.RESIZE_SCALE); 
            MultiButton m = new MultiButton();
            m.setTextLine1(w.getNomp());
            m.setTextLine2(w.getDescription());
            m.setIcon(icon);
            f.add(m);           
        }
        f.revalidate();
       
                 
    });
    });

    f.getToolbar().addSearchCommand(e -> {
    String text = (String)e.getSource();
    if(text == null || text.length() == 0) {
        // clear search
        for(Component cmp : f.getContentPane()) {
            cmp.setHidden(false);
            cmp.setVisible(true);
        }
        f.getContentPane().animateLayout(150);
    } else {
        text = text.toLowerCase();
        for(Component cmp : f.getContentPane()) {
            MultiButton mb = (MultiButton)cmp;
            String line1 = mb.getTextLine1();
            String line2 = mb.getTextLine2();
            boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
                    line2 != null && line2.toLowerCase().indexOf(text) > -1;
            mb.setHidden(!show);
            mb.setVisible(show);
        }
        f.getContentPane().animateLayout(150);
    }
}, 4);

f.show();
       // f.show();
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
}
