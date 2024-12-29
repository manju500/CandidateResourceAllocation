package in.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.company.entity.Candidate;
import in.company.service.CandidateService;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/api/candidates")
public class CandidateController {
	@Autowired
	private CandidateService candidateService;
	
	@GetMapping
	public List<Candidate> getAllCandidates(){
		return candidateService.getAllCandidates();
	}
	
	@PostMapping
	public void addCandidate(@RequestBody Candidate candidate)
	{
		candidateService.addCandidate(candidate);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCandidate(@PathVariable Integer id)
	{
		candidateService.deleteById(id);
	}
	
}
