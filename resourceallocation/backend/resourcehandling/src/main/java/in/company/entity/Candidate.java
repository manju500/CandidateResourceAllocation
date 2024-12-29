package in.company.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="candidatetab")
public class Candidate {
	
	@Id
	@Column(name="cid")
	Integer id;
	
	@Column(name="cname")
	String name;
	
	@Column(name="cexp")
	Integer experience;
	
	@Column(name="cskills")
	String skills;
}
