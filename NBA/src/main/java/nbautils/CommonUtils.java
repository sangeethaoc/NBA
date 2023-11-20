package nbautils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;

public class CommonUtils {
public static List<String> getDuplicateStringsFromList(List<WebElement> hyperLinksList)
{
	Set<String> hyperLinksSet = new HashSet<String>();
	List<String> duplicateLinks = new ArrayList<String>();
	for(int i=0; i<hyperLinksList.size();i++)
	{
		if(!hyperLinksSet.add(hyperLinksList.get(i).getText()))
		{
			duplicateLinks.add(hyperLinksList.get(i).getText());
		}
	}
	return duplicateLinks;
}

public static String[] convertListToStringArray(List<String> hyperLinksList) {
	// TODO Auto-generated method stub
	 String duplicateString[] = hyperLinksList.toArray(new String[hyperLinksList.size()]);
	return duplicateString;
}

}
