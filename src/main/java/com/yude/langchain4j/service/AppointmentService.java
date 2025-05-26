package com.yude.langchain4j.service;

import com.yude.langchain4j.entity.Appointment;

public interface AppointmentService {
    Appointment getOne(Appointment appointment);

    long count();

    boolean save(Appointment appointment);

    void removeById(long l);
}
