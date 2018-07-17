package com.nanda.caterfood.konfigurasi;

import android.content.Context;
import android.widget.Toast;

public class Config {

    public static final String IP = "192.168.1.5";
    public static final String LOGIN_URL = "HTTP://"+IP+"/CATERFOOD/LOGIN.PHP";
    public static final String DAFTAR_MAKANAN_URL = "HTTP://"+IP+"/CATERFOOD/MAKANAN.PHP";
    public static final String DAFTAR_MINUMAN_URL = "HTTP://"+IP+"/CATERFOOD/MINUMAN.PHP";
    public static final String ORDER_URL = "HTTP://"+IP+"/CATERFOOD/ORDER.PHP";
    public static final String ID_URL = "HTTP://"+IP+"/CATERFOOD/LASTID.PHP";

    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    public static final String LOGIN_SUCCESS = "success";

    public static final String SHARED_PREF_NAME = "CATERFOODAPP";

    public static final String EMAIL_SHARED_PREF = "email";

    public static final String LOGGEDIN_SHARED_PREF = "loggedin";

    public static final String SCANNED_SHARED_PREF = "scanned";

    public static final String SCANNED_DATA_PREF = "scandata";

    public static void pesan (Context c, String msg){
        Toast.makeText(c,msg,Toast.LENGTH_LONG).show();
    }

}
