package com.security.jwtlearning.security;

import com.security.jwtlearning.domen.TestUchun;
import com.security.jwtlearning.repository.TestUchunRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TestUchunService {
    private final TestUchunRepository testUchunRepository;

    public TestUchunService(TestUchunRepository testUchunRepository) {
        this.testUchunRepository = testUchunRepository;
    }
    public List<TestUchun> getAll(){
        return testUchunRepository.findAll();
    }
}
