package com.example.kacper_light_erp.bestcalendareu;

import java.io.Serializable;

/**
 * Created by Kacper-Light-ERP on 2016-06-18.
 */
public class Data implements Serializable {
    private String Selected_Date;

    public void setDat(String a) {
        this.Selected_Date=a;
    }
    public String getDat(){return this.Selected_Date;}
}
