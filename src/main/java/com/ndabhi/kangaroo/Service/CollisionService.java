package com.ndabhi.kangaroo.Service;

import com.ndabhi.kangaroo.Model.RequestModel;
import com.ndabhi.kangaroo.Model.ResponseModel;

import javax.persistence.criteria.CriteriaBuilder;


public class CollisionService {

    public ResponseModel getCollisionPoint(RequestModel requestModel){
        Integer x1 = requestModel.getX1();
        Integer v1 = requestModel.getV1();
        Integer x2 = requestModel.getX2();
        Integer v2 = requestModel.getV2();

        boolean isEqualVelocity = v1.equals(v2);
        boolean isRemainderZero = (x2 - x1) % (v1 - v2) == 0;
        boolean isV1Greater = v1 > v2;

        if (isEqualVelocity) {
            boolean isSameStartPoint = x1.equals(x2);

            if (isSameStartPoint) {
                if (writeDB(requestModel, x1)){
                    return new ResponseModel(requestModel,x1, "Kangaroo Collides");
                }else {
                    return new ResponseModel(new RequestModel(0,0,0,0),x1, "Problem Writing Obj to DB");
                }
            }
            return new ResponseModel(requestModel,-1,"Kangaroo Does Not Collide");

        } else if (isV1Greater && isRemainderZero) {

            int pos = getPosition(requestModel);

            if (writeDB(requestModel,pos)){
                return new ResponseModel(requestModel,pos, "Kangaroo Collides");
            }else {
                return new ResponseModel(new RequestModel(0,0,0,0),pos, "Problem Writing Obj to DB");
            }
        } else {
            return new ResponseModel(requestModel,-1, "Kangaroo Does Not Collide");
        }
    }

    private boolean writeDB(RequestModel requestModel, Integer pos){
        return  DBService.getInstance().writeDB(requestModel, pos);
    }

    private int getPosition(RequestModel requestModel){
        Integer x1 = requestModel.getX1();
        Integer v1 = requestModel.getV1();
        Integer x2 = requestModel.getX2();
        Integer v2 = requestModel.getV2();
        return ((x2 - x1) / (v1 - v2) * v1 ) + x1;
    }
}
