package com.yude.langchain4j.config;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.pinecone.PineconeEmbeddingStore;
import dev.langchain4j.store.embedding.pinecone.PineconeServerlessIndexConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmbddingStoreConfig {

    @Autowired
    EmbeddingModel embeddingModel;

    @Bean
    public EmbeddingStore<TextSegment> embeddingStore() {
        PineconeEmbeddingStore build = PineconeEmbeddingStore.builder()
                .apiKey("pcsk_5tjSGE_FvwNigHg2zYpN9vyGNsrf6xCk3vLg89Q94kgsJkyXBFeEvBk4hugFBRbUGhT75H")
                .index("xiaozhi-index")
                .nameSpace("xioazhi-namespace")
                .createIndex(PineconeServerlessIndexConfig.builder()
                        .cloud("AWS")
                        .region("us-east-1")
                        .dimension(embeddingModel.dimension()).build())
                .build();
        return build;
    }
}
