package com.list.to_do.exceptions;

public class ResourceNotAllowed extends RuntimeException {

    public ResourceNotAllowed(Object obj) {
        super(obj.toString());
    }
}
