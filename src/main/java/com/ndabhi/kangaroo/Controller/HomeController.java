package com.ndabhi.kangaroo.Controller;

import com.ndabhi.kangaroo.Service.CollisionService;
import com.ndabhi.kangaroo.Service.DBService;
import com.ndabhi.kangaroo.Model.ReqResModel;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {

    @GetMapping("/")
    public String Home() {
        return "Home";
    }


    @GetMapping(value = "/collisions", produces = MediaType.APPLICATION_JSON_VALUE)
    public List getDBData() {
        return DBService.getInstance().readDB();
    }


    @PostMapping(value = "/submit")
    public ReqResModel getCollisionPoint(@RequestBody ReqResModel reqResModel) {
        CollisionService collisionService = new CollisionService();
        return collisionService.getCollisionPoint(reqResModel);
    }
}
