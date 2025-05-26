package com.yude.langchain4j.config;

import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LlmModelConfig {
    @Value("${ds_api_key}")
    String ds_api_key;

    @Value("${ds_base_url}")
    String ds_base_url;

    @Value("${ds_model_name}")
    String ds_model_name;

    @Bean(name = "deepseekChatModel")
    public OpenAiChatModel deepseekChatModel(){
        System.out.println("-------------------------");
        System.out.println(ds_api_key);
        System.out.println(ds_base_url);
        return OpenAiChatModel.builder()
                .apiKey(ds_api_key)
                .baseUrl(ds_base_url)
                .modelName(ds_model_name)
                .build();
    }
}
