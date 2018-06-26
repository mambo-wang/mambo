package com.wb.wbao;

import com.wb.wbao.config.shiro.ShiroConfig;
import com.wb.wbao.server.user.User;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

public class Test {

    public static void main(String[] args) {

//        testBean();

//        testArray();


        String str = "wangbao";

        System.out.println(new StringBuilder(str).reverse().toString());

        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        System.out.println(String.valueOf(arr));


    }

    private static void testBean(){
        ApplicationContext context = new AnnotationConfigApplicationContext(ShiroConfig.class);
        HashedCredentialsMatcher hashedCredentialsMatcher = (HashedCredentialsMatcher) context.getBean("restTemplate");
        System.out.println(hashedCredentialsMatcher.getHashAlgorithmName());
    }

    private static void testArray() {
        //        byte[] bytes = new byte[1024];
//
//        System.out.println(bytes.length);
    }
}
