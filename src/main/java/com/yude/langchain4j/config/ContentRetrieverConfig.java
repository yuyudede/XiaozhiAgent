package com.yude.langchain4j.config;


import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.IngestionResult;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContentRetrieverConfig {

    @Autowired
    private EmbeddingModel embeddingModel;


    @Autowired
    private EmbeddingStore embeddingStore;

    @Bean
    public ContentRetriever contentRetriever() {
        Document document = FileSystemDocumentLoader
                .loadDocument("/Users/yude/Documents/workshop/java-ai-langchain4j/src/main/resources/testRag.txt",new TextDocumentParser());
        InMemoryEmbeddingStore<TextSegment> inMemoryEmbeddingStore = new InMemoryEmbeddingStore<>();
        IngestionResult ingest = EmbeddingStoreIngestor.ingest(document, inMemoryEmbeddingStore);
        return EmbeddingStoreContentRetriever.from(inMemoryEmbeddingStore);
    }


    @Bean
    public ContentRetriever contentRetrieverPinecone(){
        return EmbeddingStoreContentRetriever.builder()
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .maxResults(1)
                .minScore(0.8)
                .build();
    }
}
