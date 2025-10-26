//package com.syshco.ai.gemini;
//
//import io.weaviate.client.Config;
//import io.weaviate.client.WeaviateClient;
//import io.weaviate.client.base.Result;
//import io.weaviate.client.v1.misc.model.Meta;
//import io.weaviate.client.v1.graphql.model.GraphQLResponse;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//import java.util.List;
//
//@Service
//public class WeaviateService {
//    private final WeaviateClient client;
//
//    public WeaviateService(
//            @Value("${weaviate.scheme}") String scheme,
//            @Value("${weaviate.host}") String host
//    ) {
//        Config config = new Config(scheme, host);
//        this.client = new WeaviateClient(config);
//    }
//
//    public void checkConnection() {
//        Result<Meta> meta = client.misc().metaGetter().run();
//        if (meta.getError() != null) {
//            throw new RuntimeException("Weaviate error: " + meta.getError().getMessages());
//        }
//        System.out.println("Weaviate version: " + meta.getResult().getVersion());
//    }
//
//    public Map<String, Object> fetchRequestTemplate(String intent) {
//        String graphql = String.format(
//                "{ Get { RequestTemplate(where: { path: [\"requestType\"], operator: Equal, valueString: \"%s\" }) { requestType mandatoryFields optionalFields fieldDescriptions examples } } }",
//                intent
//        );
//        Result<GraphQLResponse> resp = client.graphQL().raw().withQuery(graphql).run();
//        if (resp.getError() != null) {
//            throw new RuntimeException("GraphQL error: " + resp.getError().getMessages());
//        }
//        List<Map<String, Object>> list = (List<Map<String, Object>>) resp.getResult().getData();
//        if (list.isEmpty()) {
//            return null;
//        }
//        return list.get(0);
//    }
//}
