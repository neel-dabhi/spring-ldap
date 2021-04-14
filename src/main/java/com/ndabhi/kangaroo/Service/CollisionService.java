package com.ndabhi.kangaroo.Service;

import com.ndabhi.kangaroo.Model.ReqResModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class CollisionService {

    public ReqResModel getCollisionPoint(ReqResModel reqResModel) {
        checkNullObj(reqResModel);

        Integer x1 = reqResModel.getX1();
        Integer v1 = reqResModel.getV1();
        Integer x2 = reqResModel.getX2();
        Integer v2 = reqResModel.getV2();

        boolean isEqualVelocity = v1.equals(v2);
        boolean isRemainderZero = getRemainder(reqResModel);
        boolean isV1Greater = v1 > v2;


        if (isEqualVelocity) {
            boolean isSameStartPoint = x1.equals(x2);

            if (isSameStartPoint) {
                reqResModel.setCollision(x1);

                if (writeDB(reqResModel)) {
                    reqResModel.setMessage("Kangaroo Collides");
                } else {
                    throw getResponseStatusException("Problem Writing Obj to DB");
                }
                return reqResModel;
            }
            reqResModel.setCollision(-1);
            reqResModel.setMessage("Kangaroo Does Not Collide");
            return reqResModel;

        } else if (isV1Greater && isRemainderZero) {

            reqResModel.setCollision(getPosition(reqResModel));

            if (writeDB(reqResModel)) {
                reqResModel.setMessage("Kangaroo Collides");
            } else {
                throw getResponseStatusException("Problem Writing Obj to DB");
            }
            return reqResModel;
        } else {
            reqResModel.setCollision(-1);
            reqResModel.setMessage("Kangaroo Does Not Collide");
            return reqResModel;
        }
    }

    private boolean writeDB(ReqResModel reqResModel) {
        return DBService.getInstance().writeDB(reqResModel);
    }

    private int getPosition(ReqResModel reqResModel) {
        Integer x1 = reqResModel.getX1();
        Integer v1 = reqResModel.getV1();
        Integer x2 = reqResModel.getX2();
        Integer v2 = reqResModel.getV2();
        return ((x2 - x1) / (v1 - v2) * v1) + x1;
    }

    private ResponseStatusException getResponseStatusException(String message) {
        return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    private boolean getRemainder(ReqResModel reqResModel){
        Integer x1 = reqResModel.getX1();
        Integer v1 = reqResModel.getV1();
        Integer x2 = reqResModel.getX2();
        Integer v2 = reqResModel.getV2();
        boolean result;
        try {
            result  = (x2 - x1) % (v1 - v2) == 0;

        }catch (ArithmeticException e){
            throw getResponseStatusException(e.getMessage());
        }
        return result;

    }

    private void checkNullObj(ReqResModel reqResModel){
        Integer x1 = reqResModel.getX1();
        Integer v1 = reqResModel.getV1();
        Integer x2 = reqResModel.getX2();
        Integer v2 = reqResModel.getV2();

        if (isNull(x1)){
            throw getResponseStatusException("x1 can't be null");
        }else if (isNull(v1)){
            throw getResponseStatusException("v1 can't be null");
        }else if (isNull(x2)){
            throw getResponseStatusException("x2 can't be null");
        }else if (isNull(v2)){
            throw getResponseStatusException("v2 can't be null");
        }
    }

    private boolean isNull(Object o){
        if (null == o)
        {
            return true;
        }else {
            return false;
        }
    }
}
