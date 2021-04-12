package com.ndabhi.demo.Model;

public class ResponseModel {
    Integer collision;
    Integer x1;
    Integer x2;
    Integer v1;
    Integer v2;

    public ResponseModel( RequestModel requestModel, Integer position) {
        this.collision = position;
        this.x1 = requestModel.getX1();
        this.x2 = requestModel.getX2();
        this.v1 = requestModel.getV1();
        this.v2 = requestModel.getV2();
    }

    public Integer getX1() {
        return x1;
    }

    public void setX1(Integer x1) {
        this.x1 = x1;
    }

    public Integer getX2() {
        return x2;
    }

    public void setX2(Integer x2) {
        this.x2 = x2;
    }

    public Integer getV1() {
        return v1;
    }

    public void setV1(Integer v1) {
        this.v1 = v1;
    }

    public Integer getV2() {
        return v2;
    }

    public void setV2(Integer v2) {
        this.v2 = v2;
    }




    public Integer getCollision() {
        return collision;
    }

    public void setCollision(Integer collision) {
        this.collision = collision;
    }
}
