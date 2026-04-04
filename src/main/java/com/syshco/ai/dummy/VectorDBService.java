//package com.syshco.ai.ai;
//
//import com.syshco.ai.external.embedding.PythonEmbeddingService;
//import com.syshco.ai.external.snow.ServiceNowClient;
//import com.syshco.ai.external.snow.SoftwareCatalogItem;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.concurrent.CopyOnWriteArrayList;
//
//@Service
//@RequiredArgsConstructor
//public class VectorDBService {
//
//    private final ServiceNowClient serviceNowClient;
//    private final PythonEmbeddingService pythonEmbeddingService;
//    private final List<SoftwareCatalogItem> softwareCatalogItems = new CopyOnWriteArrayList<>();
//
//   // @PostConstruct
//    public List<SoftwareCatalogItem> insert() {
//        List<SoftwareCatalogItem> items = serviceNowClient.fetchSoftwareCatalog();
//        for (SoftwareCatalogItem item : items) {
//            try {
//                float[] embedding = getEmbedding(item.getName() + " " + item.getDescription());
//                item.setEmbedding(embedding);
//                softwareCatalogItems.add(item);
//            } catch (IOException e) {
//                System.err.println("Failed to fetch embedding for: " + item.getName() + " -> " + e.getMessage());
//            }
//        }
//        return softwareCatalogItems;
//    }
//
//
//    public SoftwareCatalogItem findClosest(String query) {
//        float[] queryVector;
//        try {
//            queryVector = getEmbedding(query);
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to fetch embedding for query", e);
//        }
//
//        SoftwareCatalogItem best = null;
//        double bestScore = -1;
//
//        for (SoftwareCatalogItem item : softwareCatalogItems) {
//            double score = cosineSimilarity(queryVector, item.getEmbedding());
//            if (score > bestScore) {
//                bestScore = score;
//                best = item;
//            }
//        }
//        if (bestScore < 0.6) {
//            System.out.println("No match found — score too low.");
//            return null;
//        }
//
//        return best;
//    }
//
//    public float[] getEmbedding(String text) throws IOException {
//        return pythonEmbeddingService.generateEmbedding(text);
//    }
//
//    private double cosineSimilarity(float[] a, float[] b) {
//        double dot = 0.0, normA = 0.0, normB = 0.0;
//        for (int i = 0; i < a.length; i++) {
//            dot += a[i] * b[i];
//            normA += a[i] * a[i];
//            normB += b[i] * b[i];
//        }
//        return dot / (Math.sqrt(normA) * Math.sqrt(normB) + 1e-10);
//    }
//
//    public void addToCatalog(SoftwareCatalogItem softwareCatalogItem) {
//        softwareCatalogItems.add(softwareCatalogItem);
//    }
//
//    public List<SoftwareCatalogItem> getSoftwareCatalogItems() {
//        return softwareCatalogItems;
//    }
//}
