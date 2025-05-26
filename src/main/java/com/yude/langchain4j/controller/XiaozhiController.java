package com.yude.langchain4j;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Tag(name="硅谷小智")
@Controller
public class XiaozhiController {
    @Autowired
    XiaozhiAgent xiaozhiAgent;

    @ResponseBody
    @PostMapping("/chat")
    public String chat(@RequestBody ChatDto chatDto){
       return xiaozhiAgent.chat(chatDto.memoryId,chatDto.message);
    }

}
