import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
	
public class Banner {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);

		String username = " ";
		String password = " ";
		
		String major = " ";
		
        String subject = " ";
        String course = " ";
        String title = " ";
        String grade = " ";
        String credits = " ";
        String qualityPoints = " ";
        
        int summerCounter = 0;
        int summerFirstSemester = 0;
        int semesterCounter = 0;
        
        GPA  gpa = new GPA();
        Curriculum curriculum = new Curriculum();

        List<StudentClasses> objectArray = new ArrayList<StudentClasses>();
        List<Double> creditHours = new ArrayList<Double>();
        
        //default 5 to avoid exiting while loop on accident
        int switchOption = 5;
        //default z to avoid exiting while loop on accident
        String continueOption = "z";
        
        
		WebDriver driver = new FirefoxDriver();
        String appUrl = "https://ssb.vsu.edu:9000/bnprod/twbkwbis.P_WWWLogin";
        
        driver.get(appUrl);
        
		driver.manage().window();
		              
		String expectedTitle = "User Login";

		String actualTitle = driver.getTitle();

		if (expectedTitle.equals(actualTitle))
		{
			System.out.println("Verification Successful - The correct title is displayed on the web page.\n");
		}
		else
		{
			System.out.println("Verification Failed - An incorrect title is displayed on the web page.\n");
			System.exit(0);
		}
		
		System.out.print("Please enter your banner ID: ");
		username = keyboard.nextLine();
		
		System.out.print("Please enter your banner password: ");
		
		password = keyboard.nextLine();
		System.out.println(" ");
		
		WebElement userID = driver.findElement(By.id("UserID"));
        userID.clear();
        userID.sendKeys(username);

        WebElement pin = driver.findElement(By.name("PIN"));
        pin.sendKeys(password);
        pin.sendKeys(Keys.RETURN);
        
        //Student, Housing, and Financial Aid executes before page is ready, sleep for .5 seconds
        try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        driver.findElement(By.xpath("//.[contains(text(),'Student, Housing, and Financial Aid')]")).click();
        
        driver.findElement(By.xpath("//.[contains(text(),'Student Records')]")).click();
        
        driver.findElement(By.xpath("//.[contains(text(),'Academic Transcript')]")).click();
		 
        Select transcriptLevel = new Select(driver.findElement(By.id("levl_id")));
        transcriptLevel.selectByVisibleText("Undergraduate");
        
        Select transcriptType = new Select(driver.findElement(By.id("type_id")));
        transcriptType.selectByVisibleText("Unofficial Transcript");
        driver.findElement(By.xpath("//input[@value='Submit']")).click();
        
        System.out.println("Accessing Banner Information, Please Wait...\n");
        
        major = (driver.findElement(By.xpath(".//table[@class='datadisplaytable']/tbody/tr[7]/td[1]")).getText());
        
        int rows = driver.findElements(By.xpath(".//table[@class='datadisplaytable']/tbody/tr")).size();
        
        for (int i = 1; i <= rows; i++) {
        	int columns = driver.findElements(By.xpath(".//table[@class='datadisplaytable']/tbody/tr[" + i + "]/td")).size();
        	
        	if (driver.findElement(By.xpath(".//table[@class='datadisplaytable']/tbody/tr[" + i + "]")).getText().contains("Summer")) {
        		if (creditHours.size() == 0) {
        			summerFirstSemester = 1;
        		}
        		else {
        			summerCounter = 1;
        		}
        	}
        	
        	if (driver.findElement(By.xpath(".//table[@class='datadisplaytable']/tbody/tr[" + i + "]")).getText().contains("Current Term")) {
        		if (!driver.findElement(By.xpath(".//table[@class='datadisplaytable']/tbody/tr[" + i + "]/td[4]")).getText().contains("0.00"))
        		{
        			if (summerFirstSemester == 1) {
        				if (semesterCounter == 0) {
        					creditHours.add(Double.parseDouble(
        							(driver.findElement(By.xpath(".//table[@class='datadisplaytable']/tbody/tr[" + i + "]/td[4]")).getText())));
        					
        					semesterCounter = 1;
        				}
        				else {
        					creditHours.set((creditHours.size() - 1), (creditHours.get(creditHours.size() - 1) + 
        							(Double.parseDouble(
        									(driver.findElement(By.xpath(".//table[@class='datadisplaytable']/tbody/tr[" + i + "]/td[4]")).getText())))));
        				}
        			}
        			else if (summerCounter == 1) {
        				creditHours.set((creditHours.size() - 1), (creditHours.get(creditHours.size() - 1) + 
    							(Double.parseDouble(
    									(driver.findElement(By.xpath(".//table[@class='datadisplaytable']/tbody/tr[" + i + "]/td[4]")).getText())))));
        				summerCounter = 0;
        			}
        			else {
        				creditHours.add(Double.parseDouble(
        								(driver.findElement(By.xpath(".//table[@class='datadisplaytable']/tbody/tr[" + i + "]/td[4]")).getText())));
        			}
        		}
        	}
        	
        	//Ignore everything in banner except for classes
        	if (columns >= 6 && 
        			StringUtils.isAllUpperCase(driver.findElement(
        					By.xpath(".//table[@class='datadisplaytable']/tbody/tr[" + i + "]/td[1]")).getText())) {
        		subject = driver.findElement(By.xpath(".//table[@class='datadisplaytable']/tbody/tr[" + i + "]/td[1]")).getText();
        		course = driver.findElement(By.xpath(".//table[@class='datadisplaytable']/tbody/tr[" + i + "]/td[2]")).getText();

        		//Transfer classes
        		if (driver.findElement(By.xpath(".//table[@class='datadisplaytable']/tbody/tr[" + i + "]/td[4]")).getText().contains("TR")) {
        			title = driver.findElement(By.xpath(".//table[@class='datadisplaytable']/tbody/tr[" + i + "]/td[3]")).getText();
        			grade = "TR";
        			credits = driver.findElement(By.xpath(".//table[@class='datadisplaytable']/tbody/tr[" + i + "]/td[5]")).getText();
        			qualityPoints = "0.000";
        		}
        		//Institution classes
        		else  {
        			//Completed classes
        			if (driver.findElement(By.xpath(".//table[@class='datadisplaytable']/tbody/tr[" + i + "]/td[5]")).getText().length() == 1) {
        				title = driver.findElement(By.xpath(".//table[@class='datadisplaytable']/tbody/tr[" + i + "]/td[4]")).getText();
        				grade = driver.findElement(By.xpath(".//table[@class='datadisplaytable']/tbody/tr[" + i + "]/td[5]")).getText();
        				credits = driver.findElement(By.xpath(".//table[@class='datadisplaytable']/tbody/tr[" + i + "]/td[6]")).getText();
        				qualityPoints = driver.findElement(By.xpath(".//table[@class='datadisplaytable']/tbody/tr[" + i + "]/td[7]")).getText();
        			}
        			//Currently enrolled classes
        			else {
        				title = driver.findElement(By.xpath(".//table[@class='datadisplaytable']/tbody/tr[" + i + "]/td[4]")).getText();
        				grade = "I";
        				credits = driver.findElement(By.xpath(".//table[@class='datadisplaytable']/tbody/tr[" + i + "]/td[5]")).getText();
        				qualityPoints = "0.000";
        			}
        		}
        		
        		StudentClasses sClasses = new StudentClasses();
        		sClasses.SetClasses(subject, course, title, grade, credits, qualityPoints);
        		objectArray.add(sClasses);
        	}
        }
        
        while (switchOption != 0) {
        	System.out.println("Option 1: What classes do I need to take to meet the requirement of a given cirriculum?");
            System.out.println("Option 2: What is my cumulative GPA for each subject area?");
            System.out.println("Option 3: What is my cumulative GPA in major/non-major courses");
            System.out.println("Option 4: What is my cululative GPA within last x amount of years?");
            System.out.println("Option 0: Exit program.\n");
        	System.out.print("Please enter a number (Options 0-4): ");
        	switchOption = Integer.parseInt(keyboard.nextLine());
        	
        	System.out.println(" ");
        	
        	switch (switchOption) {
        		case 0:
        			System.exit(0);
        		
        			break;
        		
        		case 1:
        			curriculum.Requirements(objectArray, major);
        			
        			break;
                	
        		case 2:
                	gpa.SubjectGPA(objectArray);
                	
                	break;
   
        		case 3:
        			gpa.MajorGPA(objectArray, major);
        			
        			break;
        		
        		case 4:
        			gpa.GPAYears(objectArray, creditHours);
        			
        			break;
        		
        		default:
        			System.out.println("Invalid option. Please enter a number 0-4\n");
        		
        			break;
        	}
        	
        	while (!continueOption.contentEquals("y")) {
        		System.out.print("Would you like to continue using the program? (y/n): ");
        		continueOption = keyboard.nextLine();
        		System.out.println(" ");
        	
        		if (continueOption.contentEquals("n")) {
        			System.exit(0);
        		}
        		else if (!continueOption.contentEquals("y") && !continueOption.contentEquals("n")) {
        			System.out.println("Please enter either 'y' or 'n'\n");
        		}
        	}
        	//avoid continuing on next iteration accidentally
        	continueOption = "z";
        }
        keyboard.close();
	}
}
