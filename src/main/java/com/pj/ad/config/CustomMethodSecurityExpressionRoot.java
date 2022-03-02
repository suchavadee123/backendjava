//package com.pj.ad.config;
//
//import java.util.Arrays;
//import org.springframework.security.access.expression.SecurityExpressionRoot;
//import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.jwt.Jwt;
//
//public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot
//        implements MethodSecurityExpressionOperations {
//
//    public CustomMethodSecurityExpressionRoot(Authentication authentication) {
//        super(authentication);
//    }
//
////    public boolean hasPermission(String pages) {
////        Jwt principal = (Jwt) this.authentication.getPrincipal();
////        return Arrays.asList(pages.split(" ")).stream().anyMatch(principal.getClaimAsStringList("role")::contains);
////    }
//
//    @Override
//    public void setFilterObject(Object filterObject) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public Object getFilterObject() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public void setReturnObject(Object returnObject) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public Object getReturnObject() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public Object getThis() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//}