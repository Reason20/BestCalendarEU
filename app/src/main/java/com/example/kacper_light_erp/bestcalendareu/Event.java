package com.example.kacper_light_erp.bestcalendareu;

/**
 * Created by Kacper-Light-ERP on 2016-06-10.
 */
public class Event {
    private String tytul;
    private String data;
    private String godzina;
    private String opis;

    public String getTytul(){return this.tytul;}
    public String getData(){return this.data;}
    public String getGodzina(){return this.godzina;}
    public String getOpis(){return this.opis;}

    public void setTytul(String Tytul){this.tytul=Tytul;}
    public void setData(String Data){this.data=Data;}
    public void setGodzina(String Godzina){this.godzina=Godzina;}
    public void setOpis(String Opis){this.opis=Opis;}
    public Event(){}
}
