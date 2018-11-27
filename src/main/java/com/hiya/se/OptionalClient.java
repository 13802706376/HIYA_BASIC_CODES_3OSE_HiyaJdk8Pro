package com.hiya.se;

import java.util.Optional;

import com.hiya.se.lambda.User;

public class OptionalClient
{

    public static void main(String[] args)
    {
        User u1 = null;
        User u2 = new User("333", "444");
        User UNKNOWN_USER = new User("100", "101");
        
        //宽容的方式来构造一个 Optional 实例. 来者不拒, 传 null 进到就得到 Optional.empty(),
        Optional<User> op1 = Optional.ofNullable(u1);
        
        //它要求传入的 obj 不能是 null 值的, 否则还没开始进入角色就倒在了 NullPointerException 异常上了.
        Optional<User> op2 = Optional.of(u2);
        
        //存在即返回, 无则提供默认值
        User user = op1.orElse(UNKNOWN_USER);
        
        //存在做点什么,而不是 if(ffff){fffff}
        op1.ifPresent(param1 -> 
        {
             System.out.println(param1);
        });
        
        //存在即返回, 无则由函数来产生
        User user5 = op1.orElseGet(() -> getDefaultUser());
        User user6 = op1.orElseGet(() -> {   return null; });
        
        /*
         * 之前的代码 
         * User user = .....
            if(user != null) {
              String name = user.getUsername();
              if(name != null) {
                return name.toUpperCase();
              } else {
                return null;
              }
            } else {
              return null;
            }
         */
        //map函数 ,map  是可能无限级联的, 再深一层, 获得用户名的大写形式
        String id = Optional.ofNullable(u1).map(u -> u.getId()).orElse("66");
        String name = Optional.ofNullable(u1).map(u -> u.getName()).map(n -> n.toUpperCase()).orElse("NULL");
        

    }


    private static  User getDefaultUser()
    {
        return new User("2", "64564");
    }
    
}
