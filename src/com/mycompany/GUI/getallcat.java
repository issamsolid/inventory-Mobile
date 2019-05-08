/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI;

import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
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
import com.mycompany.Entity.CatProd;
import com.mycompany.Services.catService;
import java.io.IOException;

/**
 *
 * @author Don Solid
 */
public class getallcat {
    Form f;
    
    private static int idcp;
    
    private Resources theme;

    public static int getIdcp() {
        return idcp;
    }

    public static void setIdws(int idws) {
        getallcat.idcp = idws;
    }
    private Object es;
    
    
 public getallcat() throws IOException{   
            theme = UIManager.initFirstTheme("/theme");
            String url="http://localhost/images/looo.png";
                
         f= new Form("Liste des Workshops",BoxLayout.y());

         f.setLayout(new  BoxLayout(BoxLayout.Y_AXIS));
         int mm = Display.getInstance().convertToPixels(3);
         EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 5, mm * 5, 0), false);
         //placeholder=EncodedImage.create("/workwork.jpg");
          
         Image icon=URLImage.createToStorage(placeholder, url, url, URLImage.RESIZE_SCALE);
         catService ws=new catService();
          
         ArrayList<Map<String, Object>> data = new ArrayList<>();
         for(CatProd w:ws.getList2())
         {
             data.add(createListEntry(w.getNomcp(), icon));
         }
         
         DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
  MultiList ml = new MultiList(model);
  f.add( ml);
  
  
  ml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println(ml.getSelectedIndex());
                idcp=ws.getList2().get(ml.getSelectedIndex()).getIdcp();
                
                
            }
        });
    


}
private Map<String, Object> createListEntry(String name, Image icon) {
  Map<String, Object> entry = new HashMap<>();
  entry.put("Line1", name);
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
