package com.ndabhi.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndabhi.demo.Helper.DBHelper;
import com.ndabhi.demo.Model.Kangaroo;
import com.ndabhi.demo.Model.RequestModel;
import com.ndabhi.demo.Model.ResponseModel;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomeResource {

    @GetMapping("/")
    public String Home() {

        return "Home";
    }


    @GetMapping("/get")
    public String getDBData() {

        return "Hello User";
    }


    @PostMapping(value = "/submit")
    public ResponseModel getCollisionPoint(@RequestBody RequestModel requestModel) {
        Boolean isEqualVelocity = requestModel.getV1().equals(requestModel.getV2());
        Boolean isRemainder = (requestModel.getX2() - requestModel.getX1()) % (requestModel.getV1() - requestModel.getV2()) == 0;
        Boolean isV1Greater = requestModel.getV1() > requestModel.getV2();

        if (isEqualVelocity) {
            Boolean isSameStartPoint = requestModel.getX1().equals(requestModel.getX2());
            if (isSameStartPoint) {
                return new ResponseModel(requestModel, requestModel.getX1());
            }
            return new ResponseModel(requestModel,-1);
        } else if (isV1Greater && isRemainder) {
            int jumps, pos;
            jumps = (requestModel.getX2() - requestModel.getX1()) / (requestModel.getV1() - requestModel.getV2());
            pos = (jumps *requestModel.getV1() ) + requestModel.getX1();
            DBHelper.getInstance().writeDB(requestModel.getX1(),requestModel.getV1(),requestModel.getX2(),requestModel.getV2(), pos);
            return new ResponseModel(requestModel,pos);
        } else {
            return new ResponseModel(requestModel,-1);
        }

    }
}
