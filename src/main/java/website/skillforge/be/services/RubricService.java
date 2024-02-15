package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.repository.AssignmentRepository;
import website.skillforge.be.repository.RubricRepository;

@Service
public class RubricService {
    @Autowired
    private RubricRepository rubricRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;
}
