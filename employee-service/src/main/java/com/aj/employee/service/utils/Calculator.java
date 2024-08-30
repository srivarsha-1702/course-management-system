package com.aj.employee.service.utils;

public class Calculator {
    public Integer multiply(int n1, int n2){
        return n1 * n2;
    }
    public Integer divide(int n1, int n2){
        if(n2 == 0){
            return 0;
        }
        return n1/n2;
    }
}
