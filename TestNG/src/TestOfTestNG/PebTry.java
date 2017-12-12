package TestOfTestNG;

import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PebTry {

	
	WebDriver driver=new FirefoxDriver();

	@BeforeMethod
	
	public void LogInPebx() throws InterruptedException{
		
		driver.get("https://pebx.pl/login/");
		Thread.sleep(7000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//-----------------------------Log in
		String encodedPassword = "bWlsb3N6bWFzdGVyNTIx";
				String decodedPassword = new String(Base64.getDecoder().decode(encodedPassword.getBytes()));
		
		driver.findElement(By.xpath("//*[@class='ctrlUnit']//input[@tabindex='1']")).sendKeys("piotrm88");
		driver.findElement(By.xpath("//*[@class='ctrlUnit']//input[@tabindex='2']")).sendKeys(decodedPassword);
		driver.findElement(By.xpath(".//*[@class='ctrlUnit submitUnit']//input[@tabindex='3']")).click();
		driver.findElement(By.xpath(".//*[@class='ctrlUnit submitUnit']//input[@tabindex='4']")).click();
	}
	
	@AfterMethod
	
	public void LogOutPebx() {
		driver.findElement(By.xpath(".//*[@id='userBar']/div/div/div/div/ul/li[1]/a")).click();
		driver.findElement(By.xpath(".//*[@id='AccountMenu']/div[3]/ul[2]/li/a")).click();
		
	}
	@Test(priority= 1)
	
		//-----------------------------Find subject in Discussion that has the biggest number of comments 
	public void NavigationDyskusja() {	
		WebElement dyskusja = driver.findElement(By.id("dyskusja.4"));
		
		List<WebElement> listOftopics = dyskusja.findElements(By.className("nodeText"));
		System.out.println(listOftopics.size());
		List<WebElement> numberOfComments = dyskusja.findElements(By.xpath("//*[@id='dyskusja.4']/ol/li/div/div[1]/div/dl[2]/dd"));
		System.out.println(numberOfComments.size());
		
		long liczbaStartInt = '0';
		WebElement FinalLink = null;
		
		for(int i=0; i<numberOfComments.size();i++) {
			WebElement link = listOftopics.get(i).findElement(By.tagName("a"));
			System.out.println(link.getText());
			String liczbaStr = numberOfComments.get(i).getText();
			System.out.println(liczbaStr);
			
			String liczbaStrReady = liczbaStr.replaceAll("\\s+","");
			System.out.println(liczbaStrReady);
			
		    long liczbaInt = Long.parseLong(liczbaStrReady);
		    
		    if(liczbaStartInt<liczbaInt) {
		    	liczbaStartInt = liczbaInt;
		    	FinalLink=link;
		    }
		   
		}
		 System.out.println(FinalLink.getText());
		 FinalLink.click();
		 
		 
		 //------------------------------ Find Subject that has the biggest number of discussions
		 
		 WebElement ListOfSubjects = driver.findElement(By.id("forums"));
		 
		 List<WebElement> listOfTopics2 = ListOfSubjects.findElements(By.className("nodeText"));
		 System.out.println(listOfTopics2.size());
		 List<WebElement> numberOfDiscussions = ListOfSubjects.findElements(By.xpath("//*[@id='forums']/li/div/div[1]/div/dl[1]/dd"));
		 System.out.println(numberOfDiscussions.size());
		 
		 liczbaStartInt = '0';
		 
		 for(int i=0; i<numberOfDiscussions.size();i++) {
				WebElement link = listOfTopics2.get(i).findElement(By.tagName("a"));
				System.out.println(link.getText());
				String liczbaStr = numberOfDiscussions.get(i).getText();
				System.out.println(liczbaStr);
				
				String liczbaStrReady = liczbaStr.replaceAll("\\s+","");
				System.out.println(liczbaStrReady);
				
			    long liczbaInt = Long.parseLong(liczbaStrReady);
			    
			    if(liczbaStartInt<liczbaInt) {
			    	liczbaStartInt = liczbaInt;
			    	FinalLink=link;
			    }
			   
			}
			 System.out.println(FinalLink.getText());
			 FinalLink.click();
			 
			//-----------------------------------Open Subject that has the biggest number of replies
			WebElement PanelOfPosts = driver.findElement(By.className("discussionListItems"));
			
			List<WebElement> listOfPosts = PanelOfPosts.findElements(By.className("title"));
			System.out.println(listOfPosts.size());
			List<WebElement> numberOfReplies = PanelOfPosts.findElements(By.className("major"));
			System.out.println(numberOfReplies.size());
			
			liczbaStartInt = '0';
					
			for(int i=0; i<numberOfReplies.size();i++) {
				WebElement link = listOfPosts.get(i).findElement(By.tagName("a"));
				System.out.println(link.getText());
				WebElement liczbaMiejsce = numberOfReplies.get(i).findElement(By.tagName("dd"));
				String liczbaStr = liczbaMiejsce.getText();
				System.out.println(liczbaStr);
				
				String liczbaStrReady = liczbaStr.replaceAll("\\s+","");
				System.out.println(liczbaStrReady);
				
			    long liczbaInt = Long.parseLong(liczbaStrReady);
			    
			    if(liczbaStartInt<liczbaInt) {
			    	liczbaStartInt = liczbaInt;
			    	FinalLink=link;
			    }
			   
			}
			 String TitleFromLink = FinalLink.getText();
			 System.out.println(FinalLink.getText());
			 FinalLink.click();
			 
			
			 WebElement Title = driver.findElement(By.className("titleBar"));
			 String TitleFromPage = Title.findElement(By.tagName("span")).getText();
			 System.out.println("-----------------------------------------------------------");
			 System.out.println(TitleFromLink);
			 System.out.println(TitleFromPage);
			 
			 Assert.assertEquals(TitleFromLink, TitleFromPage);
			
	}
	@Test(priority= 2)
	public void NavigationKrytyka(){
	
		//-----------------------------Find subject in Discussion that has the biggest number of comments 
			WebElement dyskusja = driver.findElement(By.id("krytyka-i-recenzje.110"));
			
			List<WebElement> listOftopics = dyskusja.findElements(By.className("nodeText"));
			System.out.println(listOftopics.size());
			List<WebElement> numberOfComments = dyskusja.findElements(By.xpath("//*[@id='krytyka-i-recenzje.110']/ol/li/div/div[1]/div/dl[2]/dd"));
			System.out.println(numberOfComments.size());
			
			long liczbaStartInt = '0';
			WebElement FinalLink = null;
			
			for(int i=0; i<numberOfComments.size();i++) {
				WebElement link = listOftopics.get(i).findElement(By.tagName("a"));
				System.out.println(link.getText());
				String liczbaStr = numberOfComments.get(i).getText();
				System.out.println(liczbaStr);
				
				String liczbaStrReady = liczbaStr.replaceAll("\\s+","");
				System.out.println(liczbaStrReady);
				
			    long liczbaInt = Long.parseLong(liczbaStrReady);
			    
			    if(liczbaStartInt<liczbaInt) {
			    	liczbaStartInt = liczbaInt;
			    	FinalLink=link;
			    }
			   
			}
			 System.out.println(FinalLink.getText());
			 FinalLink.click();
			 
			 
			 //------------------------------ Find Subject that has the biggest number of discussions
			 
			 WebElement ListOfSubjects = driver.findElement(By.id("forums"));
			 
			 List<WebElement> listOfTopics2 = ListOfSubjects.findElements(By.className("nodeText"));
			 System.out.println(listOfTopics2.size());
			 List<WebElement> numberOfDiscussions = ListOfSubjects.findElements(By.xpath("//*[@id='forums']/li/div/div[1]/div/dl[1]/dd"));
			 System.out.println(numberOfDiscussions.size());
			 
			 liczbaStartInt = '0';
			 
			 for(int i=0; i<numberOfDiscussions.size();i++) {
					WebElement link = listOfTopics2.get(i).findElement(By.tagName("a"));
					System.out.println(link.getText());
					String liczbaStr = numberOfDiscussions.get(i).getText();
					System.out.println(liczbaStr);
					
					String liczbaStrReady = liczbaStr.replaceAll("\\s+","");
					System.out.println(liczbaStrReady);
					
				    long liczbaInt = Long.parseLong(liczbaStrReady);
				    
				    if(liczbaStartInt<liczbaInt) {
				    	liczbaStartInt = liczbaInt;
				    	FinalLink=link;
				    }
				   
				}
				 System.out.println(FinalLink.getText());
				 FinalLink.click();
				 
				//-----------------------------------Open Subject that has the biggest number of replies
				WebElement PanelOfPosts = driver.findElement(By.className("discussionListItems"));
				
				List<WebElement> listOfPosts = PanelOfPosts.findElements(By.className("title"));
				System.out.println(listOfPosts.size());
				List<WebElement> numberOfReplies = PanelOfPosts.findElements(By.className("major"));
				System.out.println(numberOfReplies.size());
				
				liczbaStartInt = '0';
						
				for(int i=0; i<numberOfReplies.size();i++) {
					WebElement link = listOfPosts.get(i).findElement(By.tagName("a"));
					System.out.println(link.getText());
					WebElement liczbaMiejsce = numberOfReplies.get(i).findElement(By.tagName("dd"));
					String liczbaStr = liczbaMiejsce.getText();
					System.out.println(liczbaStr);
					
					String liczbaStrReady = liczbaStr.replaceAll("\\s+","");
					System.out.println(liczbaStrReady);
					
				    long liczbaInt = Long.parseLong(liczbaStrReady);
				    
				    if(liczbaStartInt<liczbaInt) {
				    	liczbaStartInt = liczbaInt;
				    	FinalLink=link;
				    }
				   
				}
				 String TitleFromLink = FinalLink.getText();
				 System.out.println(FinalLink.getText());
				 FinalLink.click();
				 
				
				 WebElement Title = driver.findElement(By.className("titleBar"));
				 String TitleFromPage = Title.findElement(By.tagName("span")).getText();
				 System.out.println("-----------------------------------------------------------");
				 System.out.println(TitleFromLink);
				 System.out.println(TitleFromPage);
				 
				 Assert.assertEquals(TitleFromLink, TitleFromPage);
	}
	
	@Test(priority= 3)
		public void NavigationKomunikacja() {
		
		WebElement dyskusja = driver.findElement(By.id("komunikacja.6"));
		
		List<WebElement> listOftopics = dyskusja.findElements(By.className("nodeText"));
		System.out.println(listOftopics.size());
		
		List<WebElement> numberOfComments = dyskusja.findElements(By.xpath("//*[@id='komunikacja.6']/ol/li/div/div[1]/div/dl[2]/dd"));
		System.out.println(numberOfComments.size());
		
		long liczbaStartInt = '0';
		WebElement FinalLink = null;
		
		for(int i=0; i<numberOfComments.size();i++) {
			WebElement link = listOftopics.get(i).findElement(By.tagName("a"));
			System.out.println(link.getText());
			String liczbaStr = numberOfComments.get(i).getText();
			System.out.println(liczbaStr);
			
			String liczbaStrReady = liczbaStr.replaceAll("\\s+","");
			System.out.println(liczbaStrReady);
			
		    long liczbaInt = Long.parseLong(liczbaStrReady);
		    
		    if(liczbaStartInt<liczbaInt) {
		    	liczbaStartInt = liczbaInt;
		    	FinalLink=link;
		    }
		   
		}
		 System.out.println(FinalLink.getText());
		 FinalLink.click();
		 
		 
		 //------------------------------ Find Subject that has the biggest number of discussions
		 
		 WebElement ListOfSubjects = driver.findElement(By.id("forums"));
		 
		 List<WebElement> listOfTopics2 = ListOfSubjects.findElements(By.className("nodeText"));
		 System.out.println(listOfTopics2.size());
		 List<WebElement> numberOfDiscussions = ListOfSubjects.findElements(By.xpath("//*[@id='forums']/li/div/div[1]/div/dl[1]/dd"));
		 System.out.println(numberOfDiscussions.size());
		 
		 liczbaStartInt = '0';
		 
		 for(int i=0; i<numberOfDiscussions.size();i++) {
				WebElement link = listOfTopics2.get(i).findElement(By.tagName("a"));
				System.out.println(link.getText());
				String liczbaStr = numberOfDiscussions.get(i).getText();
				System.out.println(liczbaStr);
				
				String liczbaStrReady = liczbaStr.replaceAll("\\s+","");
				System.out.println(liczbaStrReady);
				
			    long liczbaInt = Long.parseLong(liczbaStrReady);
			    
			    if(liczbaStartInt<liczbaInt) {
			    	liczbaStartInt = liczbaInt;
			    	FinalLink=link;
			    }
			   
			}
			 System.out.println(FinalLink.getText());
			 FinalLink.click();
			 
			//-----------------------------------Open Subject that has the biggest number of replies
			WebElement PanelOfPosts = driver.findElement(By.className("discussionListItems"));
			
			List<WebElement> listOfPosts = PanelOfPosts.findElements(By.className("title"));
			System.out.println(listOfPosts.size());
			List<WebElement> numberOfReplies = PanelOfPosts.findElements(By.className("major"));
			System.out.println(numberOfReplies.size());
			
			liczbaStartInt = '0';
			String FinalString = "null";
		
					
			for(int i=0; i<numberOfReplies.size();i++) {
				WebElement link = listOfPosts.get(i).findElement(By.tagName("a"));
				System.out.println(link.getText());
				String link2 = link.getAttribute("href");
				WebElement liczbaMiejsce = numberOfReplies.get(i).findElement(By.tagName("dd"));
				String liczbaStr = liczbaMiejsce.getText();
				System.out.println(liczbaStr);
				
				String liczbaStrReady = liczbaStr.replaceAll("\\s+","");
				System.out.println(liczbaStrReady);
				
			    long liczbaInt = Long.parseLong(liczbaStrReady);
			    FinalLink=link;
			    FinalString=link2;
			    if(liczbaStartInt<liczbaInt) {
			    	liczbaStartInt = liczbaInt;
			    	FinalLink=link;
			    	FinalString=link2;
			    	String TitleFromLink = FinalLink.getText();
					System.out.println(FinalLink.getText());
			    }
			   
			}
			
			 //String TitleFromLink = FinalLink.getText();
			 //System.out.println(FinalLink.getText());
			 System.out.println(FinalString);
			 driver.get(FinalString);
			 
			 
			
			 /*WebElement Title = driver.findElement(By.className("titleBar"));
			 String TitleFromPage = Title.findElement(By.tagName("span")).getText();
			 System.out.println("-----------------------------------------------------------");
			 System.out.println(TitleFromLink);
			 System.out.println(TitleFromPage);
			 
			 Assert.assertEquals(TitleFromLink, TitleFromPage);*/
		}
	
			
	}

