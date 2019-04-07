package com.dahanlior.msappexam.utils;

import java.util.concurrent.Executor;

public interface AppExecutors {

    Executor diskIO();

    Executor networkIO();

    Executor mainThread();

    Executor background();
}
