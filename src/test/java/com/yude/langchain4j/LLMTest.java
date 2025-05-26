package com.yude.langchain4j;

import com.yude.langchain4j.agents.XiaozhiAgent;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LLMTest {

    @Autowired
    XiaozhiAgent xiaozhiAgent;
    @Autowired
    EmbeddingModel embeddingModel;

    @Test
    public void test() {
        String res = xiaozhiAgent.chat(1,"你好,我是俞德");
        System.out.println(res);
    }


    @Test
    public void test2() {
        Document document = FileSystemDocumentLoader
                .loadDocument("/Users/yude/Documents/workshop/java-ai-langchain4j/src/main/resources/testRag.txt",new TextDocumentParser());
        InMemoryEmbeddingStore<TextSegment> inMemoryEmbeddingStore = new InMemoryEmbeddingStore<>();
        EmbeddingStoreIngestor.ingest(document, inMemoryEmbeddingStore);
        System.out.println(inMemoryEmbeddingStore);
    }

    @Test
    public void test3() {
        System.out.println(embeddingModel);
    }
}
