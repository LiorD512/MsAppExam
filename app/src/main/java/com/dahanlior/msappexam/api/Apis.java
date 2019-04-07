package com.dahanlior.msappexam.api;

public enum Apis {

    Movie(MoviesApi.class);

    private Class<?> serviceClass;

    Apis(Class<?> serviceClass) {
        this.serviceClass = serviceClass;
    }

    public Class<?> getApiClass() {
        return serviceClass;
    }

    @Override
    public String toString() {
        return serviceClass.getSimpleName();
    }
}
