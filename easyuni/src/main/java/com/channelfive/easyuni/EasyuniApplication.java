package com.channelfive.easyuni;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.channelfive.easyuni.entities.School;
import com.channelfive.easyuni.entities.University;
import com.channelfive.easyuni.services.repositories.CourseRepository;
import com.channelfive.easyuni.services.repositories.SchoolRepository;
import com.channelfive.easyuni.services.repositories.UniversityRepository;

@SpringBootApplication
public class EasyuniApplication {

	@Autowired
	private UniversityRepository universityRepository;

	@Autowired
	private SchoolRepository schoolRepository;

	@Autowired
	private CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(EasyuniApplication.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {
		return () -> {

			if (universityRepository.count() > 0 && 
			schoolRepository.count() > 0 /*&&
			courseRepository.count() > 0*/) 
				return;

			// Start of Universities
			List<University> universities = new ArrayList<University>();
			University nus = new University();
			nus.setName("National University of Singapore");
			nus.setDescription("The National University of Singapore aspires to be a vital community of academics," +
			" researchers, staff, students and alumni working together in a spirit of innovation and enterprise for a" +
			" better world. Our singular focus on talent will be the cornerstone of a truly great university that is" + 
			" dedicated to quality education, influential research and visionary enterprise, in service of country and society.");
			nus.setZipCode("119077");
			universities.add(nus);

			University ntu = new University();
			ntu.setName("Nanyang Technological University");
			ntu.setDescription("A great global university founded on science and technology, nurturing leaders and creating societal impact through interdisciplinary education and research."); 
			ntu.setZipCode("639798");
			universities.add(ntu);

			University smu = new University();
			smu.setName("Singapore Management University");
			smu.setDescription("A premier university in Asia, the Singapore Management University (SMU) is internationally recognised for its world-class research and distinguished teaching. Established in 2000, SMU's mission is to generate leading-edge research with global impact and to produce broad-based, creative, and entrepreneurial leaders for the knowledge-based economy. SMU's education is known for its highly interactive, collaborative, and project-based approach to learning."); 
			smu.setZipCode("188065");
			universities.add(smu);
			universityRepository.saveAll(universities);
			// End of Universities

			// Start of NUS Schools
			List<School> nus_schools = new ArrayList<School>();
			School nus_sode = new School();
			nus_sode.setName("School of Design & Environment");
			nus_sode.setUniversity(nus);
			nus_schools.add(nus_sode);

			School nus_bs = new School();
			nus_bs.setName("NUS Business School");
			nus_bs.setUniversity(nus);
			nus_schools.add(nus_bs);

			School nus_c = new School();
			nus_c.setName("NUS Computing");
			nus_c.setUniversity(nus);
			nus_schools.add(nus_c);

			School nus_scale = new School();
			nus_scale.setName("NUS School of Continuing and Lifelong Education");
			nus_scale.setUniversity(nus);
			nus_schools.add(nus_scale);

			School nus_foc = new School();
			nus_foc.setName("Faculty of Dentistry");
			nus_foc.setUniversity(nus);
			nus_schools.add(nus_foc);
			// End of NUS Schools

			// Start of NTU Schools
			List<School> ntu_schools = new ArrayList<School>();
			School ntu_nbs = new School();
			ntu_nbs.setName("Nanyang Business School");
			ntu_nbs.setUniversity(ntu);
			ntu_schools.add(ntu_nbs);

			School ntu_coe = new School();
			ntu_coe.setName("College of Engineering");
			ntu_coe.setUniversity(ntu);
			ntu_schools.add(ntu_coe);

			School ntu_coh = new School();
			ntu_coh.setName("College of Humanities, Arts, and Social Sciences");
			ntu_coh.setUniversity(ntu);
			ntu_schools.add(ntu_coh);

			School ntu_cos = new School();
			ntu_cos.setName("College of Science");
			ntu_cos.setUniversity(ntu);
			ntu_schools.add(ntu_cos);

			School ntu_som = new School();
			ntu_som.setName("Lee Kong Chian School of Medicine"); 
			ntu_som.setUniversity(ntu);
			ntu_schools.add(ntu_som);
			// End of NTU Schools

			// Start of SMU Schools
			List<School> smu_schools = new ArrayList<School>();
			School smu_cis = new School();
			smu_cis.setName("College of Integrative Studies");
			smu_cis.setUniversity(smu);
			smu_schools.add(smu_cis);

			School smu_soa = new School();
			smu_soa.setName("School of Accountancy");
			smu_soa.setUniversity(smu);
			smu_schools.add(smu_soa);

			School smu_sob = new School();
			smu_sob.setName("Lee Kong Chian School of Business");
			smu_sob.setUniversity(smu);
			smu_schools.add(smu_sob);

			School smu_soe = new School();
			smu_soe.setName("School of Economics");
			smu_soe.setUniversity(smu);
			smu_schools.add(smu_soe);
			// End of SMU Schools

			// Start of Saving Schools & Update Universities
			nus.setSchools(nus_schools);
			schoolRepository.saveAll(nus_schools);
			universityRepository.save(nus); 

			ntu.setSchools(ntu_schools);
			schoolRepository.saveAll(ntu_schools);
			universityRepository.save(ntu); 

			smu.setSchools(smu_schools);
			schoolRepository.saveAll(smu_schools);
			universityRepository.save(smu); 
			// End of Saving Schools & Update Universities

			// Start of NUS Courses
			//List<School>  = new ArrayList<School>();


		};
	}
}

