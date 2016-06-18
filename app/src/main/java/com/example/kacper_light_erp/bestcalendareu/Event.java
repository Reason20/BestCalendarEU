package com.example.kacper_light_erp.bestcalendareu;

import java.io.Serializable;
/**
 * Created by Kacper-Light-ERP on 2016-06-10.
 */
public class Event implements Serializable{
    private String Tytul;
    private String Data;
    private String Godzina;
    private String Opis;
    private int Id;

    public Event(String a, String b, String c, String d){
        setTytul(a);
        setData(b);
        setGodzina(c);
        setOpis(d);
    }

    public Event(String a, String b, String c, String d, int w){
        setTytul(a);
        setData(b);
        setGodzina(c);
        setOpis(d);
        setId(w);
    }

    public String getTytul(){return this.Tytul;}
    public String getData(){return this.Data;}
    public String getGodzina(){return this.Godzina;}
    public String getOpis(){return this.Opis;}
    public int getId(){return this.Id;}

    public void setTytul(String Tytul){this.Tytul=Tytul;}
    public void setData(String Data){this.Data=Data;}
    public void setGodzina(String Godzina){this.Godzina=Godzina;}
    public void setOpis(String Opis){this.Opis=Opis;}
    public void setId(int Id){this.Id=Id;}
    public Event(){}
}
