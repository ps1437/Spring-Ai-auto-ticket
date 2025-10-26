//package com.syshco.ai.external.embedding;
//
//import com.syshco.ai.external.embedding.model.EmbeddingResponse;
//import com.syshco.ai.external.embedding.model.TextRequest;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestClient;
//
//import java.util.List;
//
//@Service
//public class PythonEmbeddingService {
//
//    private final RestClient restClient;
//
//    @Value("${embedding.service.url:http://localhost:8000/embed}")
//    private String embeddingServiceUrl;
//
//    public PythonEmbeddingService() {
//        this.restClient = RestClient.create();
//    }
//
//    public float[] generateEmbedding(String text) {
//        TextRequest request = new TextRequest();
//        request.setText(text);
//
//        EmbeddingResponse response = restClient
//                .post()
//                .uri(embeddingServiceUrl)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(request)
//                .retrieve()
//                .body(EmbeddingResponse.class);
//
//        assert response != null;
//        List<Float> list = response.getEmbedding();
//        float[] array = new float[list.size()];
//        for (int i = 0; i < list.size(); i++) {
//            array[i] = list.get(i);
//        }
//
//        return array;
//    }
//}
