package com.liuyan.controllerTest;

import com.liuyan.service.IFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class FunctionRest {

    @Autowired
    IFunction function;

    @GetMapping(value = "get")
    @ResponseBody
    String get(@RequestParam String type){
        return function.handle(type);
    }
}
