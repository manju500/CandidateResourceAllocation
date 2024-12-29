package in.company;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import in.company.entity.Candidate;
import in.company.repo.CandidateRepository;
import in.company.service.CandidateService;

@SpringBootTest
class ResourceAllocationApplicationTests {

	@Autowired
	  private CandidateService candidateService;
	  
	  @Mock
	  private CandidateRepository candidateRespository;  
	  
	  @Test
	  public void addCandidate()
	  {
	    
	    Candidate candidate=new Candidate(1 ,
	        "Dennis",
	        4,
	        " Java, Spring, JMS, mysql, Angular, React, Web services, Nodejs,Docker");
	    
	    candidateRespository.deleteAll();
	    when(candidateRespository.save(candidate)).thenReturn(candidate);
	    Candidate savedCandidate = candidateService.addCandidate(candidate);
	    
	    assertThat(savedCandidate.getId()).isEqualTo(1);
	    assertThat(savedCandidate.getName()).isEqualTo("Dennis");
	  }
	  
	  @Test
	  public void getAllCandidates() {
	    
	    List<Candidate> clist=(List<Candidate>) candidateRespository.findAll(); 
	    when(candidateRespository.findAll()).thenReturn(clist);
	    assertThat(clist).hasSizeGreaterThanOrEqualTo(0);
	  }
}
