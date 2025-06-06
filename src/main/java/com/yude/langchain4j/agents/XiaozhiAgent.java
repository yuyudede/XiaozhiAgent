package com.yude.langchain4j.agents;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

@AiService(
        wiringMode = EXPLICIT,
        chatModel = "deepseekChatModel",
        chatMemoryProvider = "chatMemoryProvider",
        tools="appointmentTools",
        contentRetriever ="contentRetrieverPinecone"
)
public interface XiaozhiAgent {
    @SystemMessage(fromResource = "prompt.txt")
    String chat(@MemoryId int memoryId,@UserMessage String message);
}
