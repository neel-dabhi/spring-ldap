package com.ndabhi.demo.Controller;

import com.ndabhi.demo.Service.CollisionService;
import com.ndabhi.demo.Service.DBService;
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
        return DBService.getInstance().readDB();
    }


    @PostMapping(value = "/submit")
    public ResponseModel getCollisionPoint(@RequestBody RequestModel requestModel) {
        CollisionService collisionService = new CollisionService();
        return collisionService.getCollisionPoint(requestModel);
    }
}
