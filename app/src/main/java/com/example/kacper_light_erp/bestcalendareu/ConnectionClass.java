package com.example.kacper_light_erp.bestcalendareu;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Kacper-Light-ERP on 2016-06-10.
 */
public class ConnectionClass {
        String ip = "bestcalendateu.database.windows.net";
        String classs = "net.sourceforge.jtds.jdbc.Driver";
        String db = "BestCallendarEU";
        String un = "administrator_kalendarza";
        String password = "get_rekt!!!420";

        public Connection CONN() {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Connection conn = null;
            String ConnURL = null;
            try {

                Class.forName(classs);
                ConnURL = "jdbc:sqlserver://" + ip + ";"
                        + "DatabaseName=" + db + ";user=" + un + ";password="
                        + password + ";";
                conn = DriverManager.getConnection(ConnURL);
            } catch (SQLException se) {
                Log.e("ERRO", se.getMessage());
            } catch (ClassNotFoundException e) {
                Log.e("ERRO", e.getMessage());
            } catch (Exception e) {
                Log.e("ERRO", e.getMessage());
            }
            return conn;
        }
}
