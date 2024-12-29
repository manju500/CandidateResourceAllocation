package in.company.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.company.entity.Candidate;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate, Integer>{
	
}
