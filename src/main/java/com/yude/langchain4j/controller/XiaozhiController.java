package com.yude.langchain4j.controller;


import com.yude.langchain4j.entity.Appointment;
import com.yude.langchain4j.entity.ChatDto;
import com.yude.langchain4j.agents.XiaozhiAgent;
import com.yude.langchain4j.service.AppointmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Tag(name="硅谷小智")
@Controller
public class XiaozhiController {
    @Autowired
    XiaozhiAgent xiaozhiAgent;

    @Autowired
    AppointmentService appointmentService;

    @ResponseBody
    @PostMapping("/chat")
    public String chat(@RequestBody ChatDto chatDto){
       return xiaozhiAgent.chat(chatDto.memoryId,chatDto.message);
    }

    @ResponseBody
    @PostMapping("/getAppointment")
    public Appointment getAppointment(@RequestBody Appointment appointment){
        return appointmentService.getOne(appointment);
    }


    @ResponseBody
    @PostMapping("/count")
    public long getAppointment(){
        return appointmentService.count();
    }




}
