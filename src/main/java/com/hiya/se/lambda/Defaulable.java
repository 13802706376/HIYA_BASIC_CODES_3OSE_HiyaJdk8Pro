package com.hiya.se.lambda;

public interface Defaulable
{
    default String notRequired() { 
        return "Default implementation"; 
    }        
    
    
    
}
