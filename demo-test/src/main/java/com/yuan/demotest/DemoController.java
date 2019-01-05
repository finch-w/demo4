package com.yuan.demotest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@Slf4j
public class DemoController extends BaseController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("index")
    public DeferredResult index() throws Exception {
//        log.info("index", new Exception());
        throw new Exception("测试");
    }


    @RequestMapping("index2")
    @ResponseBody
    public Flux index2() {
        log.info("index2");
        return Flux.just("aaaaa");
    }


    @RequestMapping("index3")
    @ResponseBody
    public Flux index3() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from user");
//        log.info(maps.toString());
        return Flux.fromIterable(maps);
    }

    @RequestMapping("index4")
    @ResponseBody
    public DeferredResult index4() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from user");
//        log.info(maps.toString());
        return deferredResult(maps);
    }

    @RequestMapping("add")
    @ResponseBody
    public Flux add() {
        int update = jdbcTemplate.update("insert into user(id, name, password, salt,  username) VALUE (?,?,?,?,?)", UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString());
        return Flux.just(update);
    }
}
