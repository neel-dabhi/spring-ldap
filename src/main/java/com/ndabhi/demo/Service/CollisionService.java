package com.ndabhi.demo.Service;

import com.ndabhi.demo.Model.RequestModel;
import com.ndabhi.demo.Model.ResponseModel;


public class CollisionService {

    public ResponseModel getCollisionPoint(RequestModel requestModel){
        Integer x1 = requestModel.getX1();
        Integer v1 = requestModel.getV1();
        Integer x2 = requestModel.getX2();
        Integer v2 = requestModel.getV2();

        Boolean isEqualVelocity = requestModel.getV1().equals(requestModel.getV2());
        Boolean isRemainderZero = (requestModel.getX2() - requestModel.getX1()) % (requestModel.getV1() - requestModel.getV2()) == 0;
        Boolean isV1Greater = requestModel.getV1() > requestModel.getV2();

        if (isEqualVelocity) {
            Boolean isSameStartPoint = x1.equals(x2);
            if (isSameStartPoint) {
                Boolean isWriteSuccessful = DBService.getInstance().writeDB(requestModel, x1);
                if (isWriteSuccessful){
                    return new ResponseModel(requestModel,x1, "Kangaroo Collides");
                }else {
                    return new ResponseModel(new RequestModel(0,0,0,0),x1, "Problem Writing Obj to DB");
                }
            }
            return new ResponseModel(requestModel,-1,"Kangaroo Does Not Collide");
        } else if (isV1Greater && isRemainderZero) {

            int jumps, pos;
            jumps = (x2 - x1) / (v1 - v2);
            pos = (jumps * v1 ) + x1;

            Boolean isWriteSuccessful = DBService.getInstance().writeDB(requestModel, pos);

            if (isWriteSuccessful){
                return new ResponseModel(requestModel,pos, "Kangaroo Collides");
            }else {
                return new ResponseModel(new RequestModel(0,0,0,0),pos, "Problem Writing Obj to DB");
            }

        } else {
            return new ResponseModel(requestModel,-1, "Kangaroo Does Not Collide");
        }
    }
}
