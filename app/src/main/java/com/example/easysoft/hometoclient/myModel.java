package com.example.easysoft.hometoclient;

import java.io.Serializable;

/**
 * Created by easysoft on 2/27/18.
 */

public class myModel implements Serializable{
    String nama;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
