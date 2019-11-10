package com.gold.InterviewTest.model;

public interface IDataCallback<T> {
    void success(T data);

    void error(Exception error);
}
