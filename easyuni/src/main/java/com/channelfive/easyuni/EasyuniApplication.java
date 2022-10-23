package com.channelfive.easyuni;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.channelfive.easyuni.entities.Course;

import com.channelfive.easyuni.entities.CourseIGP;

import com.channelfive.easyuni.entities.School;
import com.channelfive.easyuni.entities.University;
import com.channelfive.easyuni.services.repositories.CourseRepository;
import com.channelfive.easyuni.services.repositories.SchoolRepository;
import com.channelfive.easyuni.services.repositories.UniversityRepository;
import com.channelfive.easyuni.services.repositories.CourseIGPRepository;

@SpringBootApplication
public class EasyuniApplication {

	@Autowired
	private UniversityRepository universityRepository;

	@Autowired
	private SchoolRepository schoolRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private CourseIGPRepository courseIGPRepository;
	public static void main(String[] args) {
		SpringApplication.run(EasyuniApplication.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {
		return () -> {

			if (universityRepository.count() > 0 && 
			schoolRepository.count() > 0 &&
			courseRepository.count() > 0 && courseIGPRepository.count() > 0)
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
			nus_sode.setUniversityId(nus.getId());
			nus_schools.add(nus_sode);

			School nus_bs = new School();
			nus_bs.setName("NUS Business School");
			nus_bs.setUniversityId(nus.getId());
			nus_schools.add(nus_bs);

			School nus_c = new School();
			nus_c.setName("NUS Computing");
			nus_c.setUniversityId(nus.getId());
			nus_schools.add(nus_c);

			School nus_foc = new School();
			nus_foc.setName("Faculty of Dentistry");
			nus_foc.setUniversityId(nus.getId());
			nus_schools.add(nus_foc);
			// End of NUS Schools

			// Start of NTU Schools
			List<School> ntu_schools = new ArrayList<School>();
			School ntu_nbs = new School();
			ntu_nbs.setName("Nanyang Business School");
			ntu_nbs.setUniversityId(ntu.getId());
			ntu_schools.add(ntu_nbs);

			School ntu_coe = new School();
			ntu_coe.setName("College of Engineering");
			ntu_coe.setUniversityId(ntu.getId());
			ntu_schools.add(ntu_coe);

			School ntu_coh = new School();
			ntu_coh.setName("College of Humanities, Arts, and Social Sciences");
			ntu_coh.setUniversityId(ntu.getId());
			ntu_schools.add(ntu_coh);

			School ntu_cos = new School();
			ntu_cos.setName("College of Science");  
			ntu_cos.setUniversityId(ntu.getId());
			ntu_schools.add(ntu_cos);

			School ntu_som = new School();
			ntu_som.setName("Lee Kong Chian School of Medicine"); 
			ntu_som.setUniversityId(ntu.getId());
			ntu_schools.add(ntu_som);
			// End of NTU Schools

			// Start of SMU Schools
			List<School> smu_schools = new ArrayList<School>();
			School smu_cis = new School();
			smu_cis.setName("College of Integrative Studies");
			smu_cis.setUniversityId(smu.getId());
			smu_schools.add(smu_cis);

			School smu_soa = new School();
			smu_soa.setName("School of Accountancy");
			smu_soa.setUniversityId(smu.getId());
			smu_schools.add(smu_soa);

			School smu_sob = new School();
			smu_sob.setName("Lee Kong Chian School of Business");
			smu_sob.setUniversityId(smu.getId());
			smu_schools.add(smu_sob);

			School smu_soe = new School();
			smu_soe.setName("School of Economics");
			smu_soe.setUniversityId(smu.getId());
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
			List<Course> nus_courses_de = new ArrayList<Course>();
			Course nus_archi = new Course();
			nus_archi.setCourseName("NUS Architecture");
			nus_archi.setSchoolId(nus_sode.getId());
			nus_courses_de.add(nus_archi);

			Course nus_realEstate = new Course();
			nus_realEstate.setCourseName("NUS Real Estate");
			nus_realEstate.setSchoolId(nus_sode.getId());
			nus_courses_de.add(nus_realEstate);

			List<Course> nus_courses_biz = new ArrayList<Course>();
			Course nus_bizAd = new Course();
			nus_bizAd.setCourseName("NUS Business Administration");
			nus_bizAd.setSchoolId(nus_bs.getId());
			nus_courses_biz.add(nus_bizAd);

			List<Course> nus_courses_cs = new ArrayList<Course>();
			Course nus_cs = new Course();
			nus_cs.setCourseName("NUS Computer Science");
			nus_cs.setSchoolId(nus_c.getId());
			nus_courses_cs.add(nus_cs);

			Course nus_is = new Course();
			nus_is.setCourseName("NUS Information Systems");
			nus_is.setSchoolId(nus_c.getId());
			nus_courses_cs.add(nus_is);

			List<Course> nus_courses_dent = new ArrayList<Course>();
			Course nus_dent = new Course();
			nus_dent.setCourseName("NUS Dentistry");
			nus_dent.setSchoolId(nus_foc.getId());
			nus_courses_dent.add(nus_dent);
			//End of NUS courses

			//Start of NTU courses
			List<Course> ntu_courses_nbs = new ArrayList<Course>();
			Course ntu_biz = new Course();
			ntu_biz.setCourseName("NBS Business");
			ntu_biz.setSchoolId(ntu_nbs.getId());
			ntu_courses_nbs.add(ntu_biz);

			List<Course> ntu_courses_eng = new ArrayList<Course>();
			Course ntu_cs = new Course();
			ntu_cs.setCourseName("NTU Computer Science");
			ntu_cs.setSchoolId(ntu_coe.getId());
			ntu_courses_eng.add(ntu_cs);

			Course ntu_bioeng = new Course();
			ntu_bioeng.setCourseName("NTU Bioengineering");
			ntu_bioeng.setSchoolId(ntu_coe.getId());
			ntu_courses_eng.add(ntu_bioeng);

			List<Course> ntu_courses_humans = new ArrayList<Course>();
			Course ntu_econs = new Course();
			ntu_econs.setCourseName("NTU Economics");
			ntu_econs.setSchoolId(ntu_coh.getId());
			ntu_courses_humans.add(ntu_econs);

			Course ntu_soci = new Course();
			ntu_soci.setCourseName("NTU Sociology");
			ntu_soci.setSchoolId(ntu_coh.getId());
			ntu_courses_humans.add(ntu_soci);

			List<Course> ntu_courses_sci = new ArrayList<Course>();
			Course ntu_bioS = new Course();
			ntu_bioS.setCourseName("NTU Biological Science");
			ntu_bioS.setSchoolId(ntu_cos.getId());
			ntu_courses_sci.add(ntu_bioS);

			List<Course> ntu_courses_lkc = new ArrayList<Course>();
			Course ntu_med = new Course();
			ntu_med.setCourseName("NTU Lee Kong Chain Medicine");
			ntu_med.setSchoolId(ntu_som.getId());
			ntu_courses_lkc.add(ntu_med);
			//End of NTU courses

			//Start of SMU courses
			List<Course> smu_courses_acc = new ArrayList<Course>();
			//placeholder portion for SMU college of integrative studies, not sure if got any courses

			Course smu_account = new Course();
			smu_account.setCourseName("SMU Bachelor of Accounting");
			smu_account.setSchoolId(smu_soa.getId());
			smu_courses_acc.add(smu_account);

			List<Course> smu_courses_biz = new ArrayList<Course>();
			Course smu_bizMan = new Course();
			smu_bizMan.setCourseName("SMU Bachelor of Business Management");
			smu_bizMan.setSchoolId(smu_sob.getId());
			smu_courses_biz.add(smu_bizMan);

			List<Course> smu_courses_econ = new ArrayList<Course>();
			Course smu_econ = new Course();
			smu_econ.setCourseName("SMU Bachelor of Science (Economics)");
			smu_econ.setSchoolId(smu_soe.getId());
			smu_courses_econ.add(smu_econ);
			//End of SMU courses

			//Start of Saving Courses & Update Schools
			//Start of NUS part
			nus_sode.setCourses(nus_courses_de);
			nus_bs.setCourses(nus_courses_biz);
			nus_c.setCourses(nus_courses_cs);
			nus_foc.setCourses(nus_courses_dent);
			courseRepository.saveAll(nus_courses_de);
			courseRepository.saveAll(nus_courses_biz);
			courseRepository.saveAll(nus_courses_cs);
			courseRepository.saveAll(nus_courses_dent);
			schoolRepository.save(nus_sode);
			schoolRepository.save(nus_bs);
			schoolRepository.save(nus_c);
			schoolRepository.save(nus_foc);
			//End of NUS part

			//Start of NTU part
			ntu_nbs.setCourses(ntu_courses_nbs);
			ntu_coe.setCourses(ntu_courses_eng);
			ntu_coh.setCourses(ntu_courses_humans);
			ntu_cos.setCourses(ntu_courses_sci);
			ntu_som.setCourses(ntu_courses_lkc);
			courseRepository.saveAll(ntu_courses_nbs);
			courseRepository.saveAll(ntu_courses_eng);
			courseRepository.saveAll(ntu_courses_humans);
			courseRepository.saveAll(ntu_courses_sci);
			courseRepository.saveAll(ntu_courses_lkc);
			schoolRepository.save(ntu_nbs);
			schoolRepository.save(ntu_coe);
			schoolRepository.save(ntu_coh);
			schoolRepository.save(ntu_cos);
			schoolRepository.save(ntu_som);
			//End of NTU part

			//Start of SMU part
			smu_soa.setCourses(smu_courses_acc);
			smu_sob.setCourses(smu_courses_biz);
			smu_soe.setCourses(smu_courses_econ);
			courseRepository.saveAll(smu_courses_acc);
			courseRepository.saveAll(smu_courses_biz);
			courseRepository.saveAll(smu_courses_econ);
			schoolRepository.save(smu_soa);
			schoolRepository.save(smu_sob);
			schoolRepository.save(smu_soe);
			//End of SMU part
			//End of Saving Courses and Updating Schools

			//Start of NUS Courses IGP
			List<CourseIGP> nus_cs_igp = new ArrayList<CourseIGP>();
			CourseIGP nus_cs_2019 = new CourseIGP();
			nus_cs_2019.setIGP10th("AAA/A");
			nus_cs_2019.setIGP90th("AAA/A");
			nus_cs_2019.setyear(2019);
			nus_cs_2019.setCourseId(nus_cs.getId());
			nus_cs_igp.add(nus_cs_2019);

			CourseIGP nus_cs_2020 = new CourseIGP();
			nus_cs_2020.setIGP10th("AAA/A");
			nus_cs_2020.setIGP90th("AAA/A");
			nus_cs_2019.setyear(2020);
			nus_cs_2020.setCourseId(nus_cs.getId());
			nus_cs_igp.add(nus_cs_2020);
			
			CourseIGP nus_cs_2021 = new CourseIGP();
			nus_cs_2021.setIGP10th("AAA/A");
			nus_cs_2021.setIGP90th("AAA/A");
			nus_cs_2019.setyear(2021);
			nus_cs_2021.setCourseId(nus_cs.getId());
			nus_cs_igp.add(nus_cs_2021);

			List<CourseIGP> nus_biz_igp = new ArrayList<CourseIGP>();
			CourseIGP nus_bizAd_2019 = new CourseIGP();
			nus_bizAd_2019.setIGP90th("AAA/A");
			nus_bizAd_2019.setIGP10th("AAB/C");
			nus_bizAd_2019.setyear(2019);
			nus_bizAd_2019.setCourseId(nus_bizAd.getId());
			nus_biz_igp.add(nus_bizAd_2019);

			CourseIGP nus_bizAd_2020 = new CourseIGP();
			nus_bizAd_2020.setIGP90th("AAA/A");
			nus_bizAd_2020.setIGP10th("AAB/C");
			nus_bizAd_2020.setyear(2020);
			nus_bizAd_2020.setCourseId(nus_bizAd.getId());
			nus_biz_igp.add(nus_bizAd_2020);

			CourseIGP nus_bizAd_2021 = new CourseIGP();
			nus_bizAd_2021.setIGP90th("AAA/A");
			nus_bizAd_2021.setIGP10th("ABB/C");
			nus_bizAd_2021.setyear(2021);
			nus_bizAd_2021.setCourseId(nus_bizAd.getId());
			nus_biz_igp.add(nus_bizAd_2021);
			//End of NUS courses IGP

			//Start of NTU courses IGP
			List<CourseIGP> ntu_cs_igp = new ArrayList<CourseIGP>();
			CourseIGP ntu_cs_2019 = new CourseIGP();
			ntu_cs_2019.setIGP90th("AAA/A");
			ntu_cs_2019.setIGP10th("AAC/C");
			ntu_cs_2019.setyear(2019);
			ntu_cs_2019.setCourseId(ntu_cs.getId());
			ntu_cs_igp.add(ntu_cs_2019);

			CourseIGP ntu_cs_2020 = new CourseIGP();
			ntu_cs_2020.setIGP90th("AAA/A");
			ntu_cs_2020.setIGP10th("AAA/B");
			ntu_cs_2020.setyear(2020);
			ntu_cs_2020.setCourseId(ntu_cs.getId());
			ntu_cs_igp.add(ntu_cs_2020);

			CourseIGP ntu_cs_2021 = new CourseIGP();
			ntu_cs_2021.setIGP90th("AAA/A");
			ntu_cs_2021.setIGP10th("AAA/B");
			ntu_cs_2021.setyear(2021);
			ntu_cs_2021.setCourseId(ntu_cs.getId());
			ntu_cs_igp.add(ntu_cs_2021);

			List<CourseIGP> ntu_coh_igp = new ArrayList<CourseIGP>();
			CourseIGP ntu_soci_2019 = new CourseIGP();
			ntu_soci_2019.setIGP90th("AAA/A");
			ntu_soci_2019.setIGP10th("BBC/C");
			ntu_soci_2019.setyear(2019);
			ntu_soci_2019.setCourseId(ntu_soci.getId());
			ntu_coh_igp.add(ntu_soci_2019);

			CourseIGP ntu_soci_2020 = new CourseIGP();
			ntu_soci_2020.setIGP90th("AAA/A");
			ntu_soci_2020.setIGP10th("BBC/C");
			ntu_soci_2020.setyear(2020);
			ntu_soci_2020.setCourseId(ntu_soci.getId());
			ntu_coh_igp.add(ntu_soci_2020);

			CourseIGP ntu_soci_2021 = new CourseIGP();
			ntu_soci_2021.setIGP90th("AAA/A");
			ntu_soci_2021.setIGP10th("ABC/C");
			ntu_soci_2021.setyear(2021);
			ntu_soci_2021.setCourseId(ntu_soci.getId());
			ntu_coh_igp.add(ntu_soci_2021);
			//End of NTU Course IGP 

			//Start of SMU Course IGP 
			List<CourseIGP> smu_soa_igp = new ArrayList<CourseIGP>();
			CourseIGP smu_soa_2019 = new CourseIGP();
			smu_soa_2019.setIGP90th("AAA/A");
			smu_soa_2019.setIGP10th("ABB/C");
			smu_soa_2019.setyear(2019);
			smu_soa_2019.setCourseId(smu_account.getId());
			smu_soa_igp.add(smu_soa_2019);

			CourseIGP smu_soa_2020 = new CourseIGP();
			smu_soa_2020.setIGP90th("AAA/A");
			smu_soa_2020.setIGP10th("BBC/C");
			smu_soa_2020.setyear(2020);
			smu_soa_2020.setCourseId(smu_account.getId());
			smu_soa_igp.add(smu_soa_2020);

			CourseIGP smu_soa_2021 = new CourseIGP();
			smu_soa_2021.setIGP90th("AAA/A");
			smu_soa_2021.setIGP10th("BBC/C");
			smu_soa_2021.setyear(2021);
			smu_soa_2021.setCourseId(smu_account.getId());
			smu_soa_igp.add(smu_soa_2021);

			List<CourseIGP> smu_sob_igp = new ArrayList<CourseIGP>();
			CourseIGP smu_bizm_2019 = new CourseIGP();
			smu_bizm_2019.setIGP90th("AAA/A");
			smu_bizm_2019.setIGP10th("ABB/C");
			smu_bizm_2019.setyear(2019);
			smu_bizm_2019.setCourseId(smu_bizMan.getId());
			smu_sob_igp.add(smu_bizm_2019);

			CourseIGP smu_bizm_2020 = new CourseIGP();
			smu_bizm_2020.setIGP90th("AAA/A");
			smu_bizm_2020.setIGP10th("BBB/C");
			smu_bizm_2020.setyear(2020);
			smu_bizm_2020.setCourseId(smu_bizMan.getId());
			smu_sob_igp.add(smu_bizm_2020);

			CourseIGP smu_bizm_2021 = new CourseIGP();
			smu_bizm_2021.setIGP90th("AAA/A");
			smu_bizm_2021.setIGP10th("BBB/B");
			smu_bizm_2021.setyear(2021);
			smu_bizm_2021.setCourseId(smu_bizMan.getId());
			smu_sob_igp.add(smu_bizm_2021);
			//End of SMU courses IGP

			//Start of Saving course IGP and updating Courses
			//Start of NUS Saving 
			nus_cs.setCourseIGP(nus_cs_igp);
			nus_bizAd.setCourseIGP(nus_biz_igp);
			courseIGPRepository.saveAll(nus_cs_igp);
			courseIGPRepository.saveAll(nus_biz_igp);
			courseRepository.save(nus_cs);
			courseRepository.save(nus_bizAd);

			//End of NUS Saving

			//Start of NTU Saving
			ntu_cs.setCourseIGP(ntu_cs_igp);
			ntu_soci.setCourseIGP(ntu_coh_igp);
			courseIGPRepository.saveAll(ntu_cs_igp);
			courseIGPRepository.saveAll(ntu_coh_igp);
			courseRepository.save(ntu_cs);
			courseRepository.save(ntu_soci);
			//End of NTU Saving

			//Start of SMU Saving
			smu_account.setCourseIGP(smu_soa_igp);
			smu_bizMan.setCourseIGP(smu_sob_igp);
			courseIGPRepository.saveAll(smu_soa_igp);
			courseIGPRepository.saveAll(smu_sob_igp);
			courseRepository.save(smu_account);
			courseRepository.save(smu_bizMan);
			//End of SMU Saving


		};
	}
}

