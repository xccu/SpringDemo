package com.example.annotation;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * 环绕通知：在类方法调用前，先判断角色是否匹配。
 */
@Component("visitorRoleAdvice")
public class VisitorRoleAdvice implements MethodInterceptor {
    public Object invoke(MethodInvocation invocation) throws Throwable {
        //有指定注解
        if(invocation.getMethod().isAnnotationPresent(VisitorRole.class)){
            String role = null;
            //获取指定注解
            Annotation annotation = invocation.getMethod().getAnnotation(VisitorRole.class);
            if(annotation!=null){
                role = ((VisitorRole)annotation).value(); //从注解中获取角色
            }

            if("ADMIN".equals(role)){
                return invocation.proceed();  //角色匹配，继续执行方法
            }else{
                System.out.println("没有角色权限！");
                return null;
            }

        }else{ //类方法没有自定义注解，直接执行该方法
            return invocation.proceed();
        }
    }
}