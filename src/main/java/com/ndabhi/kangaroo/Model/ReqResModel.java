package com.ndabhi.kangaroo.Model;

public class ReqResModel {
    public ReqResModel(){}

    public ReqResModel(Integer x1, Integer v1, Integer x2, Integer v2) {
        this.setX1(x1);
        this.setV1(v1);
        this.setX2(x2);
        this.setV2(v2);
    }

    private Integer x1;
    private Integer v1;
    private Integer x2;
    private Integer v2;
    private Integer collision;
    private String message;


    public Integer getX1() {
        return x1;
    }

    public void setX1(Integer x1) {
        this.x1 = validate(x1);
    }

    public Integer getX2() {
        return x2;
    }

    public void setX2(Integer x2) {
        this.x2 = validate(x2);
    }

    public Integer getV1() {
        return v1;
    }

    public void setV1(Integer v1) {
        this.v1 = validate(v1);
    }

    public Integer getV2() {
        return v2;
    }

    public void setV2(Integer v2) {
        this.v2 = validate(v2);
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if (null == message || message.isBlank()){
            this.message = "Problem Setting Message";
        }else {
            this.message = message;
        }
    }

    private int validate(Integer x){
        if (null == x || x< 0)
        {
            return 0;
        }else {
            return x;
        }
    }

}
