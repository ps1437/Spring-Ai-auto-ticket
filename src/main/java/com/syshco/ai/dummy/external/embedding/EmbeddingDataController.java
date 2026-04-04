//package com.syshco.ai.external.embedding;
//
//import com.syshco.ai.ai.VectorDBService;
//import com.syshco.ai.external.snow.SoftwareCatalogItem;
//import lombok.RequiredArgsConstructor;
//import org.apache.commons.lang3.tuple.Pair;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.util.List;
//
//@RestController
//@RequestMapping("/embedding")
//@RequiredArgsConstructor
//public class EmbeddingDataController {
//
//    private final VectorDBService vectorDBService;
//
//    @GetMapping("/softwares")
//    public ResponseEntity<List<Pair<String, String>>> getSoftwareSummaries() {
//        List<Pair<String, String>> summaries = vectorDBService.getSoftwareCatalogItems().stream()
//                .map(item -> Pair.of(item.getId(), item.getName()))
//                .toList();
//
//        return ResponseEntity.ok(summaries);
//    }
//
//
//    @PostMapping("/addSoftware")
//    public ResponseEntity<SoftwareCatalogItem> addSoftware(@RequestBody SoftwareCatalogItem newItem) {
//        try {
//            float[] embedding = vectorDBService.getEmbedding(newItem.getName() + " " + newItem.getDescription());
//            newItem.setEmbedding(embedding);
//            vectorDBService.addToCatalog(newItem);
//            return ResponseEntity.ok(newItem);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(null);
//        }
//    }
//}
