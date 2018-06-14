package com.wb.wbao;

import com.wb.wbao.config.shiro.ShiroConfig;
import com.wb.wbao.server.user.User;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.HashMap;

public class Test {

    public static void main(String[] args) {

//        testBean();

//        testArray();

        User user = new User();
        user.setLoginName("wbao");
        user.setPassword("xxx");

        System.out.println(user.hashCode());

        HashMap<User, Integer> map = new HashMap<>();
        map.put(user, 1);

        User user2 = new User();
        user2.setLoginName("wbao");
        user2.setPassword("xxx");

        System.out.println(map.get(user2));



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
