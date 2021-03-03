package com.tianjie.study.y2021.fastjson;

import com.tianjie.study.model.LittleBird;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/fastjsontest")
    public LittleBird test(){
        LittleBird littleBird = new LittleBird();
        littleBird.setName("小小");
        littleBird.setKind(BirdKindEnum.PHOENIX.code());
        return littleBird;

    }
}
