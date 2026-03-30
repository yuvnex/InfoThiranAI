package com.research.assistant;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/research")
@RestController
@CrossOrigin(origins = "*")

public class ResearchController {
    @Autowired
    private final ResearchService researchService;

    public ResearchController(ResearchService researchService) {
        this.researchService = researchService;
    }
    @PatchMapping("/process")
    public ResponseEntity<String> processContent(@RequestBody ResearchRequest request){
        String result=researchService.processContent(request);
        return ResponseEntity.ok(result);

    }
}
