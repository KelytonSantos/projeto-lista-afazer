package com.list.to_do.exceptions;

public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(Object obj) {
        super(obj.toString());
    }
}
