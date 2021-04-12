package com.ndabhi.demo.Model;

public class ResponseModel {
    private Integer collision;
    private Integer x1;
    private Integer v1;
    private Integer x2;
    private Integer v2;

    public ResponseModel(RequestModel requestModel, Integer position) {
        setCollision(position);
        setX1(requestModel.getX1());
        setV1(requestModel.getV1());
        setX2(requestModel.getX2());
        setV2(requestModel.getV2());
    }

    public Integer getX1() {
        return x1;
    }

    public void setX1(Integer x1) {
        if (null == x1 || x1 < 0) {
            this.x1 = 0;
        } else {
            this.x1 = x1;
        }
    }

    public Integer getX2() {
        return x2;
    }

    public void setX2(Integer x2) {
        if (null == x2 || x2 < 0) {
            this.x2 = 0;
        } else {
            this.x2 = x2;
        }
    }

    public Integer getV1() {
        return v1;
    }

    public void setV1(Integer v1) {
        if (null == v1 || v1 < 0) {
            this.v1 = 0;
        } else {
            this.v1 = v1;
        }
    }

    public Integer getV2() {
        return v2;
    }

    public void setV2(Integer v2) {
        if (null == v2 || v2 < 0) {
            this.v2 = 0;
        } else {
            this.v2 = v2;
        }
    }

    public Integer getCollision() {
        return collision;
    }

    public void setCollision(Integer collision) {
        if (null == collision){
            this.collision = -1;
        }else {
            this.collision = collision;
        }
    }
}
