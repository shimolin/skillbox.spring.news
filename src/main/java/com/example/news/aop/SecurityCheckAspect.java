package com.example.news.aop;

import com.example.news.exception.NotPermitException;
import com.example.news.model.Role;
import com.example.news.model.RoleType;
import com.example.news.service.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Aspect
@Component
@RequiredArgsConstructor
public class SecurityCheckAspect {

    private final UserService userService;

    @Around("@annotation(SecurityCheck) && args(userDetails, requestedUserId, ..)")
    public Object securityCheck(ProceedingJoinPoint pjp, UserDetails userDetails, Long requestedUserId){


        System.out.println("---Annotation.SecurityCheck---");
        System.out.println(pjp.getSignature().getDeclaringTypeName());
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.getAuthorities());
        System.out.println(requestedUserId);

        Long currentUserId = userService.findByUsername(userDetails.getUsername()).getId();
        List<Role> currentUserRoles = userService.findByUsername(userDetails.getUsername()).getRoles();
        Set<RoleType> currentUserRoleTypes = new HashSet<>();
        for(Role r : currentUserRoles){
            currentUserRoleTypes.add(r.getAuthority());
        };

        Boolean securityCheck = false;

        if(currentUserRoleTypes.contains(RoleType.ROLE_ADMIN) || currentUserRoleTypes.contains(RoleType.ROLE_MODERATOR)){
            securityCheck = true;
        } else {
            if (currentUserId == requestedUserId) securityCheck = true;

        }

        if(!securityCheck){
            throw new NotPermitException("Not permitted!!!");
        }





        try {
            return pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

    }
}
