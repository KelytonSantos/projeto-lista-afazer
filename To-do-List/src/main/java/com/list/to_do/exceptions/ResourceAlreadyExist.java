package com.list.to_do.exceptions;

public class ResourceAlreadyExist extends RuntimeException {

    public ResourceAlreadyExist(Object obj) {
        super(obj.toString());
    }
}
