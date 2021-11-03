package com.cqcxi.flowEngine.util;

/**
 * <p>类描述：  </p>
 * <p>版权：ChongQingCXI Co.Itd All right reserved. 2021年01月18日 </p>
 * <p>创建人员： 杨顺 </p>
 * <p>创建日期：2021/7/13 </p>
 * <p>开发公司：重庆创信智能科技有限公司 </p>
 */

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TimeHelper {
    /**
     * 获取当前时间
     * @return String
     */
    public static String getCurrentTime(){
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取String类型的时间
        String createdate = sdf.format(date);
        return createdate;
    }
    public static String getCurrentDate(){
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取String类型的时间
        String createdate = sdf.format(date);
        return createdate;
    }
    public static String getNestDate(){
        Date date = new Date();
        Calendar   calendar   =   new   GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
        date=calendar.getTime();   //这个时间就是日期往后推一天的结果
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取String类型的时间
        String createdate = sdf.format(date);
        return createdate;
    }


    public static String getTime(){
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:00");
        //获取String类型的时间
        String createdate = sdf.format(date);
        return createdate;
    }


    public static String getDayTime(){
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        //获取String类型的时间
        String createdate = sdf.format(date);
        return createdate;
    }

    public static Long compareValue(String StringTime1,String StringTime2){
        String differenceFormat = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//yyyy-mm-dd, 会出现时间不对, 因为小写的mm是代表: 秒
        Date dateTime1 = null;
        Date dateTime2 = null;
        try {
            dateTime1 = sdf.parse(StringTime1);
            dateTime2 = sdf.parse(StringTime2);
        } catch (ParseException e) {
        }
        long difference = dateTime1.getTime() - dateTime2.getTime();
        long days = difference / (1000 * 60 * 60 * 24);
        long hours = (difference % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (difference % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (difference % (1000 * 60)) / 1000;
        differenceFormat = days + " days " + hours + " hours " + minutes + " minutes " + seconds + " seconds ";
        return difference;
    }

    /**
     * 获取date时间
     */
    public static Date getCurrentDateTime(){
        Date parse = null;
        try {
            String currentTime = getCurrentTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            parse = sdf.parse(currentTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }
    public static String dateToString(Date	 date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * string转date
     * @param string
     * @return
     */
    public static Date stringToDate(String string){
        Date parse = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            parse = sdf.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    //	public static Date stringToBirthDate(String string){
//		Date parse = null;
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			parse = sdf.parse(string);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return parse;
//	}
    public static Date cronStringToDate(String string){
        Date parse = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            parse = sdf.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    public static Date noTimeStringToDate(String string){
        Date parse = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            parse = sdf.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    /**
     * 判断是否小于当前时间
     */
    public static boolean compare(Date date){
        Date currentDateTime = getCurrentDateTime();
        boolean before = currentDateTime.before(date);
        return before;
    }

    /**
     * 判断是否能  是为false  否为true
     */
    public static boolean compareNowAddTenMinutes(Date date){
        boolean before = false;
        try {
            Calendar nowTime = Calendar.getInstance();
            nowTime.add(Calendar.MINUTE, 10);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = sdf.format(nowTime.getTime());
            Date  nowAddTen = sdf.parse(format);
            before = nowAddTen.before(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return before;
    }

    //判断两个date是否同一月
    public static boolean equals(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        int year1 = calendar1.get(Calendar.YEAR);
        int year2 = calendar2.get(Calendar.YEAR);
        int month1 = calendar1.get(Calendar.MONTH);
        int month2 = calendar2.get(Calendar.MONTH);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);
    }

    //判断是否合法
    public static boolean isValidDate(String str) {
        if (str == null){
            return false;
        }
        boolean convertSuccess=true;
// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess=false;
        }
        return convertSuccess;
    }


    //判断是否合法
    public static boolean isValidTime(String str) {
        if (str == null){
            return false;
        }
        boolean convertSuccess=true;
// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        try {
// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess=false;
        }
        return convertSuccess;
    }


    //判断是否合法
    public static boolean isValidBirth(String str) {
        if ( str == null){
            return false;
        }
        boolean convertSuccess=true;
// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess=false;
        }
        return convertSuccess;
    }

    //获取两个日期之间的所有日期集合
    public static List<Date> getBetweenDates(Date start, Date end) {
        List<Date> result = new ArrayList<Date>();
        result.add(start);
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);
        tempStart.add(Calendar.DAY_OF_YEAR, 1);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        while (tempStart.before(tempEnd)) {
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        result.add(end);
        return result;
    }

    //返回日期集合的天数集合和月数
    public static Map<Integer,Integer[]> getDayOfMonth(List<Date> dates){
        List<Integer> days = new ArrayList<Integer>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dates.get(0));
        Integer month = (Integer.valueOf(calendar.get(Calendar.MONTH)))+1;
        for (Date date : dates) {
            calendar.setTime(date);
            days.add(calendar.get(Calendar.DATE));
        }
        Integer ints[]=new Integer[days.size()];
        for(int i=0,j=days.size();i<j;i++){
            ints[i]=days.get(i);
        }
        Map<Integer,Integer[]> map = new HashMap<Integer,Integer[]>();
        map.put(month, ints);
        return map;
    }

    //获取date到当月初的月份和日期
    public static Map<Integer,Integer[]> getBeforeDayOfMonth(String date){
        Calendar cale = Calendar.getInstance();
        cale.setTime(TimeHelper.noTimeStringToDate(date));
        cale.set(Calendar.DAY_OF_MONTH,1);
        Date time = cale.getTime();
        List<Date> betweenDates = getBetweenDates(time, noTimeStringToDate(date));
        Map<Integer, Integer[]> dayOfMonth = getDayOfMonth(betweenDates);
        return dayOfMonth;
    }

    //获取date到当月月底的月份和日期
    public static Map<Integer,Integer[]> getAfterDayOfMonth(String date){
        Calendar cale = Calendar.getInstance();
        cale.setTime(TimeHelper.noTimeStringToDate(date));
        cale.set(Calendar.MONTH,cale.get(Calendar.MONTH)+1 );
        cale.set(Calendar.DAY_OF_MONTH,0);
        Date time = cale.getTime();
        List<Date> betweenDates = getBetweenDates( noTimeStringToDate(date),time);
        Map<Integer, Integer[]> dayOfMonth = getDayOfMonth(betweenDates);
        return dayOfMonth;
    }

    /**
     * date2比date1多的天数
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2-day1) ;
        }
        else    //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }


    public static int calcSeconds(Date sDate, Date eDate)

    {
        Calendar c = Calendar.getInstance() ;

        c.setTime(sDate);
        long ls = c.getTimeInMillis() ;
        c.setTime(eDate);
        long le = c.getTimeInMillis() ;

        return (int) ((le-ls)/1000);
    }

    /**
     * 时间戳转换成日期格式字符串
     * @return
     */
    public static String timeStamp2Date(String timestamp) {
        //时间格式,HH是24小时制，hh是AM PM12小时制
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
//比如timestamp=1449210225945；
        long date_temp = Long.valueOf(timestamp);
        String date_string = sdf.format(new Date(date_temp * 1000L));
        return date_string;
//至于取10位或取13位，date_temp*1000L就是这种截取作用。如果是和服务器传值的，就和后台商量好就可以了
    }

    //是否为今天
    public static boolean isToday(String str) throws Exception{

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = format.parse(str);


        Calendar c1 = Calendar.getInstance();

        c1.setTime(date);

        int year1 = c1.get(Calendar.YEAR);

        int month1 = c1.get(Calendar.MONTH)+1;

        int day1 = c1.get(Calendar.DAY_OF_MONTH);

        Calendar c2 = Calendar.getInstance();

        c2.setTime(new Date());

        int year2 = c2.get(Calendar.YEAR);

        int month2 = c2.get(Calendar.MONTH)+1;

        int day2 = c2.get(Calendar.DAY_OF_MONTH);

        if(year1 == year2 && month1 == month2 && day1 == day2){

            return true;

        }

        return false;

    }

    /**
     * 获取两个日期中的所有日期,并转换为表后缀
     *
     * @param begin 格式:yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
     * @param end   格式:yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
     */
    public static HashMap<Integer, List<Integer>>  getDetHashList(String begin, String end) {
        HashMap<Integer, List<Integer>> result = new HashMap<>();
        String btime = begin.substring(0, 10);//yyyy-MM-dd
        String etime = end.substring(0, 10);

        Date bDate = DateUtil.parse(btime, DatePattern.NORM_DATE_PATTERN);//yyyy-MM-dd
        Date eDate = DateUtil.parse(etime, DatePattern.NORM_DATE_PATTERN);
        List<DateTime> dateList = DateUtil.rangeToList(bDate, eDate, DateField.DAY_OF_YEAR);//创建日期范围生成器
        for (DateTime dt : dateList) {
            if (!result.containsKey(dt.month() + 1)){
                result.put(dt.month() + 1, new ArrayList<>());
            }
            result.get(dt.month() + 1).add(dt.dayOfMonth());
        }
        return result;
    }
}
