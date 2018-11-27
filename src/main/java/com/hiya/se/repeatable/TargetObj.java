package com.hiya.se.repeatable;

@Schedule(hour = 0)
@Schedule(hour = 8)
@Schedule(hour = 12)
public class TargetObj
{
    public static void main(String[] args) {
        // 推荐的方式
        Schedule[] annotations = TargetObj.class.getAnnotationsByType(Schedule.class);
        for (Schedule each : annotations) {
            System.out.println(each.hour());
        }
 

 
    }
}
