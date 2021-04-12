package com.ndabhi.demo.Controller;

import com.ndabhi.demo.Helper.DBHelper;
import com.ndabhi.demo.Model.CollisionsDAO;
import com.ndabhi.demo.Model.RequestModel;
import com.ndabhi.demo.Model.ResponseModel;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {

    @GetMapping("/")
    public String Home() {
        return "Home";
    }


    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CollisionsDAO> getDBData() {
        return DBHelper.getInstance().readDB();
    }


    @PostMapping(value = "/submit")
    public ResponseModel getCollisionPoint(@RequestBody RequestModel requestModel) {

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
                return new ResponseModel(requestModel, x1);
            }
            return new ResponseModel(requestModel,-1);
        } else if (isV1Greater && isRemainderZero) {
            int jumps, pos;
            jumps = (x2 - x1) / (v1 - v2);
            pos = (jumps * v1 ) + x1;
            DBHelper.getInstance().writeDB(requestModel, pos);
            return new ResponseModel(requestModel,pos);
        } else {
            return new ResponseModel(requestModel,-1);
        }

    }
}
