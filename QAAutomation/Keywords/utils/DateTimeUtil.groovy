package utils
import java.text.SimpleDateFormat
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import core.HMException
public class DateTimeUtil {
	/**
	 * This public method is used to get current Date time with a given format
	 * @param format: this is a given format, default value will be "MM/dd/yyyy"
	 * @return String: this returns a current date time string
	 */
	static String getDateTime(String format="MM/dd/yyyy", String timeZoneID=""){
		try{
			Date date = new Date()
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format)
			if(timeZoneID.equals("")){
				simpleDateFormat.setTimeZone(TimeZone.getDefault())
			}else{
				simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneID))//"America/New_York" = "GMT-4"
			}
			return simpleDateFormat.format(date.getTime())
		}catch(Throwable e){
			throw new HMException("Cannot get current date time",e);
		}
	}
	/**
	 * This public method is used to get Date time after plus with n days
	 * @param days: the days will be plus
	 * @param format: this is a given format, default value will be "MM/dd/yyyy"
	 * @param timeZoneID: this is a TimeZone ID, default value will be local timezone
	 * @return String: this returns a current date time string
	 */
	public static String nextDateTime(int days=5, String format="MM/dd/yyyy", String timeZoneID=getTimeZoneIDOfLocal()){
		try{
			Date date = new Date()
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format)
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneID))
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE, days);
			date = c.getTime();
			return simpleDateFormat.format(date.getTime())
		}catch(Throwable e){
			throw new HMException("Cannot get next date time",e);
		}
	}
	/**
	 * This public method is used to get Date time after plus with n days
	 * @param days: the days will be plus
	 * @param format: this is a given format, default value will be "MM/dd/yyyy"
	 * @param timeZoneID: this is a TimeZone ID, default value will be local timezone
	 * @return String: this returns a current date time string
	 */
	public static String nextDateTimeFromAGivenDate(int days=5, String givenDate, String format="MM/dd/yyyy", String timeZoneID=getTimeZoneIDOfLocal()){
		try{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format)
			Date date = simpleDateFormat.parse(givenDate);
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneID))
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE, days);
			date = c.getTime();
			return simpleDateFormat.format(date.getTime())
		}catch(Throwable e){
			throw new HMException("Cannot get next date time",e);
		}
	}
	/**
	 * This public method is used to get current local time-zone ID
	 * @param N/A
	 * @return String: this returns a current local time-zone ID
	 */
	public static String getTimeZoneIDOfLocal(){
		try{
			String timezoneID = Calendar.getInstance().getTimeZone().getID()
			return timezoneID
		}catch(Throwable e){
			throw new HMException("Cannot get Timezone",e);
		}
	}
	/**
	 * This public method is used to convert a Date String to a Date String with new format
	 * @param dateString: this is a given dateString
	 * @param formatOfDateString: this is a given old format
	 * @param newFormat: this is a given new format, default value will be "MM/dd/yyyy"
	 * @return Date: this returns a date string
	 */
	public static String convertDateStringFormat(String dateString, String formatOfDateString, String newFormat="MM/dd/yyyy"){
		try{
			SimpleDateFormat result = new SimpleDateFormat(formatOfDateString);
			Date date = result.parse(dateString);
			result.applyPattern(newFormat);
			return result.format(date);
		}catch(Throwable e){
			throw new HMException("Cannot convert String to DateTime",e);
		}
	}
	//Compare 2 date time together
	public static boolean isDatetimeBeforeDatetime(String date1, String date2, String format="MM/dd/yyyy") {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			return simpleDateFormat.parse(date1).before(simpleDateFormat.parse(date2));
		}catch(Throwable e){
			throw new HMException("Cannot compare 2 Datetimes",e);
		}
	}
}