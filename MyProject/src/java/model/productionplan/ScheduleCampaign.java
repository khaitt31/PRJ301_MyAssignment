/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.productionplan;

import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class ScheduleCampaign {

    private int id;
    private PlanCampaign cam;
    private Date date;
    private String k;
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlanCampaign getCam() {
        return cam;
    }

    public void setCam(PlanCampaign cam) {
        this.cam = cam;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getK() {
        return k;
    }

    public void setK(String K) {
        this.k = K;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
