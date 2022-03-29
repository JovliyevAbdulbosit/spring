package com.security.jwtlearning.Urls;

import com.security.jwtlearning.security.TestUchunService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestUchunUrl {
    private final TestUchunService testUchunService;

    public TestUchunUrl(TestUchunService testUchunService) {
        this.testUchunService = testUchunService;
    }
    @GetMapping("/uchun")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(testUchunService.getAll());
    }
}
