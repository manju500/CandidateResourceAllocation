package in.company.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.company.entity.Candidate;
import in.company.repo.CandidateRepository;

@Service
public class CandidateService {
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	public Candidate addCandidate(Candidate candidate ) 
	{
		candidateRepository.save(candidate);
		return candidate;
	}
	
	public List<Candidate> getAllCandidates(){
		return (List<Candidate>) candidateRepository.findAll();
	}
	
	public void deleteById(Integer id) {
		candidateRepository.deleteById(id);
	}
	
public List<String> findMatchingCandidate()
	{
		List<String> result=new ArrayList<String>();
		
		List<Candidate> clist=getAllCandidates();
		
		//retrieve Candidate names eligible for microservice based project
		Optional<String> microserviceCandidates=
				clist
				.stream()
				.map(candidate->candidate.getSkills())
				.filter(skills->skills.toLowerCase().contains("java")
						&&skills.toLowerCase().contains("redis")
						&&skills.toLowerCase().contains("javascript"))
				.reduce((ans,name)->ans+", "+name);
		
		
		//retrieve Candidate names eligible for cloud based project
		Optional<String> cloudCandidates=
				clist
				.stream()
				.filter(candidate->candidate.getExperience()<10) //consider less than 10 year
				.map(candidate->candidate.getSkills())
				.filter(skills->skills.toLowerCase().contains("mysql")
						&&skills.toLowerCase().contains("docker")
						&&skills.toLowerCase().contains("spring")
						&&skills.toLowerCase().contains("react"))
				.reduce((ans,name)->ans+", "+name);
		
		result.add(microserviceCandidates.orElse("")); //convert Optional type to String type
		result.add(cloudCandidates.orElse(""));
		return result;
	}
}
