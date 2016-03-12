import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GPA {
	public void SubjectGPA(List<StudentClasses> objectArray) {
		
		String[] classArray = new String[6];
		
		List<String> subjects = new ArrayList<String>();
		List<Double> subjectCredits = new ArrayList<Double>();
		List<Double> subjectPoints = new ArrayList<Double>();
		
		DecimalFormat df = new DecimalFormat("#.###");
		
		//tracks if subject has already been added to subjects ArrayList
		int counter = 0;
		
		for (int i = 0; i < objectArray.size(); i++) {
        	classArray = objectArray.get(i).GetClasses();
        	
        	//use contains("W") because simple comparison would not catch "W" for some reason
        	if (!classArray[3].contains("W") && !classArray[3].contains("I") && !classArray[3].contains("TR")) {
        		if (i == 0) {
        			subjects.add(classArray[0]);
        			subjectCredits.add(Double.parseDouble(classArray[4]));
        			subjectPoints.add(Double.parseDouble(classArray[5]));
        		}
        		else {
        			for (int j = 0; j < subjects.size(); j++) {
        				if (subjects.get(j).contains(classArray[0])) {
        					subjectCredits.set(j, subjectCredits.get(j) + Double.parseDouble(classArray[4]));
        					subjectPoints.set(j, subjectPoints.get(j) + Double.parseDouble(classArray[5]));
        					counter++;
        				}
        			}
        			if (counter == 0) {
        				subjects.add(classArray[0]);
            			subjectCredits.add(Double.parseDouble(classArray[4]));
            			subjectPoints.add(Double.parseDouble(classArray[5]));
        			}
        			counter = 0;
        		}
        	}
        }
		System.out.println("Subject GPAs: ");
		
		for (int i = 0; i < subjects.size(); i++) {
			System.out.println(subjects.get(i) + ":     " + df.format((subjectPoints.get(i) / subjectCredits.get(i))));
		}
		System.out.println(" ");
		
		return;
	}
	
	public void MajorGPA (List<StudentClasses> objectArray, String major) {
		String[] classArray = new String[6];
		
		List<Double> subjectCredits = new ArrayList<Double>();
		List<Double> subjectPoints = new ArrayList<Double>();
		
		//initializing starting variables for major (index 0) / non-major (index 1) courses
		subjectCredits.add(0.00);
		subjectCredits.add(0.00);
		subjectPoints.add(0.00);
		subjectPoints.add(0.00);
		
		DecimalFormat df = new DecimalFormat("#.###");
		
		if (major.contains("Computer Science")) {
			for (int i = 0; i < objectArray.size(); i++) {
        		classArray = objectArray.get(i).GetClasses();
        	
        		//use contains("W") because simple comparison would not catch "W" for some reason
        		if (!classArray[3].contains("W") && !classArray[3].contains("I") && !classArray[3].contains("TR")) {
        			if (classArray[0].contains("CSCI")) {
        				subjectCredits.set(0, subjectCredits.get(0) + Double.parseDouble(classArray[4]));
    					subjectPoints.set(0, subjectPoints.get(0) + Double.parseDouble(classArray[5]));
        			}
        			else {
        				subjectCredits.set(1, subjectCredits.get(1) + Double.parseDouble(classArray[4]));
    					subjectPoints.set(1, subjectPoints.get(1) + Double.parseDouble(classArray[5]));
        			}
        		}
			}
		}
		System.out.println("Subject GPAs: ");
		System.out.println("Major Courses:     " + df.format((subjectPoints.get(0) / subjectCredits.get(0))));
		System.out.println("Non-Major Courses:     " + df.format((subjectPoints.get(1) / subjectCredits.get(1))));
		System.out.println(" ");
		
		
		return;
	}
	
	public void GPAYears(List<StudentClasses> objectArray, List<Double> creditHours) {
		String[] classArray = new String[6];
		
		double[] credits = new double[creditHours.size()];
		double[] points = new double[creditHours.size()];
		
		double yearCredits = 0;
		double yearPoints = 0;
		double gpa = 0;
		
		int semesterCount = 0;
		int semesters = creditHours.size();
		int years = 0;
		
		DecimalFormat df = new DecimalFormat("#.###");
		
		for ( int i = 0; i < creditHours.size(); i++) {
			credits[i] = creditHours.get(i);
		}
		
		for (int i = objectArray.size() - 1; i >= 0; i--) {
        	classArray = objectArray.get(i).GetClasses();
        	
        	if (!classArray[3].contains("W") && !classArray[3].contains("I") && !classArray[3].contains("TR")) {
        		//System.out.println(classArray[0] + " " + classArray[1] + " " + classArray[2] + " " + classArray[3] +  " " +classArray[4] + " " + classArray[5]);
        		
        		if (creditHours.get(semesters - 1) == 0) {
        			semesters--;
        		}
        		
        		creditHours.set(semesters - 1, creditHours.get(semesters - 1) - Double.parseDouble(classArray[4]));
        		points[semesters - 1] += Double.parseDouble(classArray[5]);
        	}
		}
		System.out.println(" ");
		
		for (int i = credits.length - 1; i >= 0; i--) {
			if (semesterCount < 2) {
				
				yearCredits += credits[i];
				yearPoints += points[i];
				semesterCount++;
			}
			
			if (semesterCount == 2) {
				years++;
				
				gpa = (yearPoints/yearCredits);
				
				System.out.println("GPA for the past " + years + " years: " + df.format(gpa));
				
				semesterCount = 0;
			}
		}
		
		if (semesterCount == 1) {
			System.out.println("GPA for the past " + years + " 1/2 years (total): " + df.format(gpa));
		}
		System.out.println(" ");
		
		return;
	}
}
