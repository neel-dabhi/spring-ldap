package com.ndabhi.demo.Model;

public class Kangaroo {

    private Integer velocity;
    private Integer startPoint;

    public Kangaroo(Integer startPoint, Integer velocity) {
        setVelocity(velocity);
        setStartPoint(startPoint);
    }

    public Integer getVelocity() {
        return velocity;
    }

    public void setVelocity(Integer velocity) {
        if (null == velocity || velocity < 0) {
            this.velocity = 0;
        } else {
            this.velocity = velocity;
        }
    }

    public Integer getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Integer startPoint) {
        if (null == startPoint || startPoint < 0) {
            this.startPoint = 0;
        }else {
            this.startPoint = startPoint;
        }
    }


}
