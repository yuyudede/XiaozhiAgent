package com.yude.langchain4j.tools;


import com.yude.langchain4j.entity.Appointment;
import com.yude.langchain4j.service.AppointmentService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentTools {

    @Autowired
    private AppointmentService appointmentService;


    @Tool(name = "bookAppointment", value = "根据参数，先执行工具方法queryDepartment查询是否可以预约，并直接给用户回答是否可以预约，并让用户确认所有预约信息，用户确认后再进行预约。")
    public String bookAppointment(Appointment appointment) {
        Appointment one = appointmentService.getOne(appointment);
        if (null == one) {
            appointment.setId(null);
            if (appointmentService.save(appointment)) {
                return "预约成功，预约详情";
            } else {
                return "预约失败";
            }
        }
        return "你在相同科室已经预约过了";
    }



    @Tool(name = "queryDepartment", value= "根据科室名称，日期，时间和医生查询是否有号源，并返回给用户")
    public boolean queryDepartment(
            @P(value = "科室名称") String name,
            @P(value = "日期") String date,
            @P(value = "时间，可选值：上午、下午") String time,
            @P(value = "医生名称", required = false) String doctorName
    ) {
        System.out.println("查询是否有号源");
        System.out.println("科室名称：" + name);
        System.out.println("日期：" + date);
        System.out.println("时间：" + time);
        System.out.println("医生名称：" + doctorName);
        return true;
    }





}
