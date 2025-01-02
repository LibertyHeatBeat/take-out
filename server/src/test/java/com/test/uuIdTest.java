package com.test;

import org.junit.jupiter.api.Test;

import java.util.UUID;


public class uuIdTest {
    @Test
    public void t1(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        int desiredLength = 10; // 你想要的长度
        String truncatedUuid = uuid.substring(0, Math.min(desiredLength, uuid.length()));
        System.out.println(truncatedUuid);
    }
}
