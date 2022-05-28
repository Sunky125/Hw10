package urfu.lesson9.Aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ApiInvokeCountCheckerAspect {

    @Value("${api.maxInvokesCount}")
    private int maxInvokesCount;
    private int currentInvokesCount = 0;

    @Around("@annotation(urfu.lesson9.Annotations.ApiInvokeCountChecker)")
    public Object Check(ProceedingJoinPoint pjp) throws Throwable {
        try {
            if (currentInvokesCount + 1 > maxInvokesCount)
                throw new Throwable(pjp.getSignature().getName() + " cannot be called. API request limit exceeded.");
            currentInvokesCount++;
            return pjp.proceed();
        } catch (Throwable e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
