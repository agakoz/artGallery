package com.example.artGallery.exc;

public class LocalizedException extends IllegalArgumentException{
    public LocalizedException(String msg){
        super(String.format( msg));
    }
}