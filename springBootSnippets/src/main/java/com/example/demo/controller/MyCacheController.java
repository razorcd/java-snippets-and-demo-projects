package com.example.demo.controller;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import javax.cache.annotation.CacheResult;
import java.util.Random;

@RestController
public class MyCacheController {

// TODO: to enable javax cache disable Ehcache dependency and useage
//    @CacheResult // add @EnableCaching to Main app method. requires javax.cache-api dependency
//        @RequestMapping("/javaxCachedData")
//    public String javaxCacheData() {
//        return getRandom();
//    }

    @Cacheable(cacheNames = "mycachedata") // add @EnableCaching to Main app method. requires ehcache dependency
    @RequestMapping("/ehCachedData")
    public String ehCacheData() {
        return getRandom();
    }

    private String getRandom() {
//        return new Date().toString();
        return String.valueOf(new Random().nextInt());
    }



}
