package com.ndabhi.kangaroo.Service;

import com.ndabhi.kangaroo.Model.ReqResModel;


public class CollisionService {

    public ReqResModel getCollisionPoint(ReqResModel reqResModel){
        Integer x1 = reqResModel.getX1();
        Integer v1 = reqResModel.getV1();
        Integer x2 = reqResModel.getX2();
        Integer v2 = reqResModel.getV2();

        boolean isEqualVelocity = v1.equals(v2);
        boolean isRemainderZero = (x2 - x1) % (v1 - v2) == 0;
        boolean isV1Greater = v1 > v2;

        if (isEqualVelocity) {
            boolean isSameStartPoint = x1.equals(x2);

            if (isSameStartPoint) {
                reqResModel.setCollision(x1);

                if (writeDB(reqResModel)){
                    reqResModel.setMessage("Kangaroo Collides");
                    return reqResModel;
                }else {
                    reqResModel.setMessage("Problem Writing Obj to DB");
                    return reqResModel;
                }
            }
            reqResModel.setCollision(-1);
            reqResModel.setMessage("Kangaroo Does Not Collide");
            return reqResModel;

        } else if (isV1Greater && isRemainderZero) {

            reqResModel.setCollision(getPosition(reqResModel));

            if (writeDB(reqResModel)){
                reqResModel.setMessage("Kangaroo Collides");
                return reqResModel;
            }else {
                reqResModel.setMessage("Problem Writing Obj to DB");
                return reqResModel;
            }
        } else {
            reqResModel.setCollision(-1);
            reqResModel.setMessage("Kangaroo Does Not Collide");
            return reqResModel;

        }
    }

    private boolean writeDB(ReqResModel reqResModel){
        return  DBService.getInstance().writeDB(reqResModel);
    }

    private int getPosition(ReqResModel reqResModel){
        Integer x1 = reqResModel.getX1();
        Integer v1 = reqResModel.getV1();
        Integer x2 = reqResModel.getX2();
        Integer v2 = reqResModel.getV2();
        return ((x2 - x1) / (v1 - v2) * v1 ) + x1;
    }
}
