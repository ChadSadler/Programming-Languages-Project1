
public class StudentClasses {
	private String newSubject = " ";
	private String newCourse = " ";
	private String newTitle = " ";
	private String newGrade = " ";
	private String newCredits = " ";
	private String newQualityPoints = " ";
	
	public void SetClasses (String subject, String course, String title, String grade, String credits, String qualityPoints){
		newSubject = subject;
		newCourse = course;
		newTitle = title;
		newGrade = grade;
		newCredits = credits;
		newQualityPoints = qualityPoints;
	}
	
	public String[] GetClasses () {
		String[] classArray = {newSubject, newCourse, newTitle, newGrade, newCredits, newQualityPoints};
		return classArray;
	}
}
