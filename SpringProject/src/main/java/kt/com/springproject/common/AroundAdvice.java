package kt.com.springproject.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class AroundAdvice {

    public Object aroundLog(ProceedingJoinPoint jp) throws Throwable {
        Object obj = null;
        StopWatch watch = new StopWatch();
        watch.start();;

        System.out.println("before logic");

        obj = jp.proceed();

        watch.start();
        System.out.println("after log" + watch.getTotalTimeSeconds());
        return obj;
    }
}
