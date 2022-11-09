package utils
import org.apache.commons.lang.RandomStringUtils
import org.openqa.selenium.By
import com.google.common.base.Stopwatch
import com.google.common.base.Strings
import core.Logger
public class Utilities {
	public static boolean isStringMatchedWithWildcard(String wildCardString, String fullString) {
		// If we reach at the end of both strings,
		// we are done
		if (wildCardString.length() == 0 && fullString.length() == 0)
			return true;
		// Make sure that the characters after '*'
		// are present in second string.
		// This function assumes that the wildCardString
		// string will not contain two consecutive '*'
		if (wildCardString.length() > 1 && wildCardString.charAt(0) == '*' &&
		fullString.length() == 0)
			return false;
		// If the wildCardString string contains '?',
		// or current characters of both strings match
		if ((wildCardString.length() > 1 && wildCardString.charAt(0) == '?') ||
		(wildCardString.length() != 0 && fullString.length() != 0 &&
		wildCardString.charAt(0) == fullString.charAt(0)))
			return isStringMatchedWithWildcard(wildCardString.substring(1),
					fullString.substring(1));
		// If there is *, then there are two possibilities
		// a) We consider current character of second string
		// b) We ignore current character of second string.
		if (wildCardString.length() > 0 && wildCardString.charAt(0) == '*')
			return isStringMatchedWithWildcard(wildCardString.substring(1), fullString) ||
					isStringMatchedWithWildcard(wildCardString, fullString.substring(1));
		return false;
	}
	public static By mapArgumentToSelector(String selector){
		if(!Strings.isNullOrEmpty(selector) && selector.startsWith("//")) {
			return By.xpath(selector);
		}else if(!Strings.isNullOrEmpty(selector)){
			return By.cssSelector(selector);
		}else{
			return null;
		}
	}
	public static String generateRandomNumber(int quantityNumbers=5){
		if(quantityNumbers<=10){
			String numbers = RandomStringUtils.randomNumeric(quantityNumbers);
			return numbers;
		}else{
			Logger.logERROR("The random number should less than or equal 10");
			return null;
		}
	}
	public static boolean tryToParseStringToInt(String num) {
		try {
			Integer.parseInt(num)
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	public static void waitForAWhile(int seconds, boolean condition=true){
		long timeInMiliSeconds = seconds * 1000;
		Stopwatch sw = new Stopwatch();
		sw.start();
		//		boolean condition = true;
		while(condition){
			if(timeInMiliSeconds< sw.elapsed().toMillis()){
				sw.stop();
				break;
			}
		}
	}
}