package com.ndabhi.kangaroo.Model;

public class RequestModel {
    Integer x1;
    Integer v1;
    Integer x2;
    Integer v2;

    public RequestModel(Integer x1, Integer x2, Integer v1, Integer v2) {
        setX1(x1);
        setV1(v1);
        setX2(x2);
        setV2(v2);
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

}
