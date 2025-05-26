package com.yude.langchain4j.controller;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Tag(name = "知识库维护")
@Controller
@Slf4j
public class RagController {

    @Autowired
    public EmbeddingModel embeddingModel;
    @Autowired
    public EmbeddingStore embeddingStore;
    @Value("${file_path}")
    private String path;

    @ResponseBody
    @GetMapping("/refreshRag")
    public void refreshRag(){
        log.info("开始进行文件遍历");
        List<Document> documents = FileSystemDocumentLoader.loadDocumentsRecursively(path,new TextDocumentParser());
        log.info("文件数量为：{}", documents.size());
        EmbeddingStoreIngestor.builder()
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .build()
                .ingest(documents);
        log.info("文件解析完成");
    }
}
