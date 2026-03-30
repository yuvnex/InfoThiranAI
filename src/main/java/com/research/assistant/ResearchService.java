package com.research.assistant;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class ResearchService {
    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    private final WebClient webClient;

    public ResearchService(WebClient.Builder webClient) {
        this.webClient = WebClient.builder().build();
    }


    public String processContent(ResearchRequest request) {
        //build prompt

        String prompt=buildPrompt(request);
        Map<String,Object> requestBody=Map.of(
                "contents", new Object[]{
                        Map.of("parts",new Object[]{
                                Map.of("text", prompt)
                        })
                }
        );
        String response=webClient.post()
                .uri(geminiApiUrl + geminiApiUrl)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return extractTextFromResponse(response);

    }

    private String extractTextFromResponse(String response) {
        try{

        }
        catch (Exception e){

        }
    }

    private String buildPrompt(ResearchRequest request) {
        StringBuilder prompt = new StringBuilder();

        switch (request.getOperation()) {
            case "summarize":
                prompt.append("Provide a clear and concise summary of the following text in a few sentences:\n\n");
                break;

            case "suggest":
                prompt.append("Based on the following content, suggest related topics and further reading. ")
                        .append("Format the response with clear headings and bullet points:\n\n");
                break;

            default:
                throw new IllegalArgumentException("Unknown operation: " + request.getOperation());
        }

        prompt.append(request.getContent());
        return prompt.toString();
    }
}
