import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
//http://www.jb51.net/article/31251.htm 正则表达式小李子
public class RegexTest {
    @Test
    public void testRegSplit(){
        String str = "cyy,single.abc/https";
        String[] results = str.split("[,./]");
        for(String s : results){
            System.out.print(s+"  ");
        }
    }
    @Test
    public void regNew(){
//        String str1="http://blog.csdn.net/a19881029/article/details/37905995";
//        String str="http://blog.csdn.net/19881029/";
        String str2="0123456789";
        String regStr1="(//d)+";
        Pattern pattern=Pattern.compile(regStr1);
        Matcher matcher=pattern.matcher(str2);
        System.out.println(matcher.group());


    }
    @Test public void expmale(){
        String  str = "1234567abc";
        String regex = "\\w{10,}";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(str);
        System.out.println(matcher.matches());
//        System.out.println(str.matches(regex));
    }
    @Test
    public  void expamale1(){
        String str = "12Y34h56dAd7";
        String regex="([a-zA-Z])+";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(str);
        System.out.println(matcher.group(1));
    }
    @Test
    public  void example2(){
        String string = "Jack is a boy";
// 将字符串编译为正则表达式的对象表示形式
        Pattern pattern = Pattern.compile("a.*");
// 创建对字符串 string 根据正则表达式 pattern 进行匹配操作的匹配器对象
        Matcher matcher = pattern.matcher(string);
// 查找下一个匹配的字符串内容，如果找到返回 true，找不到返回 false
        while(matcher.find()) {
            // 输出捕获到的匹配内容
            System.out.print(matcher.group() + "\t");
        }
    }
    @Test
    public void findPort(){
//        String regexStr="port\\s{1}[0-9]{4}";
//        Pattern pattern=Pattern.compile(regexStr);
//        Matcher matcher=pattern.matcher("port 6379");
        String regexStr="port\\s{1}[0-9]{4}";
        Pattern pattern=Pattern.compile(regexStr);
        Matcher matcher=pattern.matcher("port 6379");
        if(matcher.find()) {
            // 输出捕获到的匹配内容
            System.out.print(matcher.group() + "\t");
        }
    }
    @Test
    public void testMatch(){
        String regexStr="port\\s{1}[0-9]{4}";
        Pattern pattern=Pattern.compile(regexStr);
        Matcher matcher=pattern.matcher("port1 6379");
        if(matcher.find()) {
            // 输出捕获到的匹配内容
            System.out.print(matcher.group() + "\t");
        }
    }
    @Test
    public void matcher1Test(){

        Pattern pattern1 = Pattern.compile("#\\s{1}cluster-slave-validity-factor");
        Matcher matcher1 = pattern1.matcher("# cluster-slave-validity-factor 10");
        if(matcher1.find()) {
            // 输出捕获到的匹配内容
            System.out.print(matcher1.group() + "\t");
        }
    }
    @Test
    public void matcher2Test(){
        Pattern pattern2 = Pattern.compile("#\\s{1}cluster-config-file.*[a-z]+");
        Matcher matcher2 = pattern2.matcher("# cluster-config-file nodes-6379.conf");
        if(matcher2.find()) {
            // 输出捕获到的匹配内容
            System.out.print(matcher2.group() + "\t");
        }
    }
    @Test
    public void matcher3Test(){
        Pattern pattern2 = Pattern.compile("#\\s{1}[a-z]+\\-{1}[a-z]+\\-{1}[a-z]+\\s{1}(?=15000)");
        Matcher matcher2 = pattern2.matcher("# cluster-node-timeout 15000");
        if(matcher2.find()){
            System.out.println(matcher2.group());
        }

    }
    @Test
    public void matcher4Test(){
        Pattern pattern2 = Pattern.compile("appendonly no");
        Matcher matcher2 = pattern2.matcher("appendonly no");
        System.out.println("appendonly".length());
        if(matcher2.find()){
            System.out.println(matcher2.group());
        }

    }

}
