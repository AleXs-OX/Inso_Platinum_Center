package controller;

import model.dao.MaterialDao;
import model.vo.MaterialVo;

import java.sql.Timestamp;

public class ListenerRemoveMaterialUI {

    MaterialVo material;


    public ListenerRemoveMaterialUI(){}

    public void removeMaterial(MaterialVo material) throws Exception {
        this.material = material;
        MaterialDao materialDao = new MaterialDao();

        /*Fecha actual*/
        long millis=System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        /*Da de baja el Material*/
        material.setFechaBaja(date);
        materialDao.actualizarBaja(material);
    }


}
