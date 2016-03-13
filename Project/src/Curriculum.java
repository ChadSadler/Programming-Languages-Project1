import java.util.ArrayList;
import java.util.List;

public class Curriculum {
	public void Requirements (List<StudentClasses> objectArray, String major) {
		String[] classArray = new String[6];
		List<String> courses = new ArrayList<String>();
		List<String> incomplete = new ArrayList<String>();
		
		if (major.contains("Computer Science")) {
			CSCICurriculum CSCI = new CSCICurriculum();
			courses = CSCI.GetCSCICurriculum();
			
			for (int i = 0; i < objectArray.size(); i++) {
        		classArray = objectArray.get(i).GetClasses();
        		
        		if (!classArray[3].contains("W") && !classArray[3].contains("F")) {
        			//Do not include courses currently in progress, save in list for later
        			if (classArray[3].contains("I")) {
        				incomplete.add(classArray[0] + " " + classArray[1] + ": " + classArray[2]);
        			}
        			//major classes
        			else if (!classArray[3].contains("D") && classArray[0].contains("CSCI")) {
        				for (int j = 0; j < courses.size(); j++) {
        					//non substituted required classes (major)
        					if (courses.get(j).contains(classArray[1])) {
        						courses.remove(j);
        						break;
        					}
        					//substituted
        					else if (courses.get(j).contains("CSCI 287")
        							&& classArray[1].contains("387")) {
        						courses.remove(j);
        						break;
        					}
        					//substituted
        					else if (courses.get(j).contains("CSCI 296")
        							&& classArray[1].contains("496")) {
        						courses.remove(j);
        						break;
        					}
        					//elective
        					else if (courses.get(j).contains("CSCI ELECTIVE")) {
        						courses.remove(j);
        						break;
        					}
        					//elective
        					else if (courses.get(j).contains("CSCI/CPEG/CISY")) {
        						courses.remove(j);
        						break;
        					}
        					//elective
        					else if (courses.get(j).contains("CSCI/MATH/STAT ELECTIVE")) {
        						courses.remove(j);
        						break;
        					}
        					//elective (if all others fail, must be free elective)
        					else if (courses.get(j).contains("FREE ELECTIVE")) {
        						courses.remove(j);
        						break;
        					}
        				}
        			}
        			//non-major classes
        			else {
        				for (int j = 0; j < courses.size(); j++) {
        					//non-substituted required classes (non-major)
        					if (courses.get(j).contains((classArray[0] + " " + classArray[1]))) {
        						courses.remove(j);
        						break;
        					}
        					//non-substituted required class w/ no defined course number
        					else if (courses.get(j).contains("BIOL/CHEM/PHYS LABORATORY SCIENCE")
        							&& (classArray[0].contains("BIOL")
        							|| classArray[0].contains("CHEM")
        							|| classArray[0].contains("PHYS"))) {
        						courses.remove(j);
        						break;
        					}
        					//substituted
        					else if (courses.get(j).contains("MATH 280")
        							&& (classArray[0] + " " + classArray[1]).contains("MATH 284")) {
        						courses.remove(j);
        						break;
        					}
        					//substituted
        					else if (courses.get(j).contains("PHIL 450")
        							&& (classArray[0] + " " + classArray[1]).contains("PHIL 275")) {
        						courses.remove(j);
        						break;
        					}
        					//substituted
        					else if (courses.get(j).contains("HEALTH AND WELLNESS")
        							&& classArray[2].contains("Health And Wellness")) {
        						courses.remove(j);
        						break;
        					}
        					//substituted
        					else if (courses.get(j).contains("STAT 340")
        							&& classArray[2].contains("Intro Statistics")) {
        						courses.remove(j);
        						break;
        					}
        					//elective
        					else if (courses.get(j).contains("HISTORY ELECTIVE")
        							&& classArray[0].contains("HIST")) {
        						courses.remove(j);
        						break;
        					}
        					//elective
        					else if (courses.get(j).contains("LITERATURE ELECTIVE")
        							&& classArray[0].contains("ENGL")) {
        						courses.remove(j);
        						break;
        					}
        					//elective
        					else if (courses.get(j).contains("MATH ELECTIVE")
        							&& classArray[0].contains("MATH")) {
        						courses.remove(j);
        						break;
        					}
        					//elective
        					else if (courses.get(j).contains("CSCI/MATH/STAT ELECTIVE")
        							&& (classArray[0].contains("MATH")
        							|| classArray[0].contains("STAT"))) {
        						courses.remove(j);
        						break;
        					}
        					//elective
        					else if (courses.get(j).contains("CSCI/CPEG/CISY")
        							&& (classArray[0].contains("CPEG")
        							|| classArray[0].contains("CISY"))) {
        						courses.remove(j);
        						break;
        					}
        					//elective
        					else if (courses.get(j).contains("SOCIAL SCIENCE ELECTIVE")
        							&& (classArray[0].contains("POLI")
        							|| classArray[0].contains("SOCI")
        							|| classArray[0].contains("ECON")
        							|| classArray[0].contains("CJUS")
        							|| classArray[0].contains("PSYC")
        							|| classArray[0].contains("FACS"))) {
        						courses.remove(j);
        						break;
        					}
        					else if (courses.get(j).contains("GLOBAL STUDIES ELECTIVE")
        							&&	((classArray[0] + " " + classArray[1]).contains("AGRI 295")
        							|| (classArray[0] + " " + classArray[1]).contains("ARTS 301")
        							|| (classArray[0] + " " + classArray[1]).contains("ARTS 302")
        							|| (classArray[0] + " " + classArray[1]).contains("ARTS 405")
        							|| (classArray[0] + " " + classArray[1]).contains("ECON 451")
        							|| (classArray[0] + " " + classArray[1]).contains("ENGL 314")
        							|| (classArray[0] + " " + classArray[1]).contains("ENGL 315")
        							|| (classArray[0] + " " + classArray[1]).contains("ENGL 322")
        							|| (classArray[0] + " " + classArray[1]).contains("ENGL 411")
        							|| (classArray[0] + " " + classArray[1]).contains("ENGL 412")
        							|| (classArray[0] + " " + classArray[1]).contains("GEOG 210")
        							|| (classArray[0] + " " + classArray[1]).contains("IDUP 270")
        							|| (classArray[0] + " " + classArray[1]).contains("MUSI 286")
        							|| (classArray[0] + " " + classArray[1]).contains("POLI 207")
        							|| (classArray[0] + " " + classArray[1]).contains("POLI 210")
        							|| classArray[2].contains("Mythology")
        							|| classArray[0].contains("FREN")
        							|| classArray[0].contains("SPAN")
        							|| classArray[0].contains("GERM"))) {
        						courses.remove(j);
        						break;
        					}
        					//elective (if all others fail, must be free elective)
        					else if (courses.get(j).contains("FREE ELECTIVE")) {
        						courses.remove(j);
        						break;
        					}
        				}
        			}
        		}
			}
		}
		System.out.println("Remaining Courses: ");
		for (int i = 0; i < courses.size(); i++) {
			System.out.println(courses.get(i));
		}
		
		System.out.println("\nCourses in Progress: ");
		for (int i = 0; i < incomplete.size(); i++) {
			System.out.println(incomplete.get(i));
		}
		System.out.println(" ");
		
		return;
	}
	
}
