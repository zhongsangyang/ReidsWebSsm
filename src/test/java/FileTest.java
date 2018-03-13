import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* http://blog.csdn.net/houfeng30920/article/details/51496852
* Filke操作的相关知识
* */
public class FileTest {
    @Test
    public void testFileMkdirAndMadirs(){
        String lujing=File.separator;
        String filename="D:"+lujing+"a"+lujing+"b"+lujing+"laaa.txt";
        File file=new File(filename);
        if(!(file.getParentFile().exists())){
           file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        file.mkdirs();
    }

    @Test
    public void testFileList(){
        File file=new File("D:"+File.separator);
        String fileist[]=file.list();
        for(String s: fileist){
            System.out.println(s);
        }
    }
    @Test
    public void qufen(){
        File file=new File("F:"+File.separator);
        File filenames[]=file.listFiles();
        for (int i=0;i<filenames.length;i++){
            File files=filenames[i];
            if(files.isFile()){
                System.out.println("文件夹 = " + files.getName());
            }else if(files.isDirectory()){
                System.out.println("目录="+files.getName());
            }
        }
    }
    @Test
    public  void testEndJava(){
        File file=new File("F:"+File.separator+"RedisInsatall"+File.separator);
        File []files=file.listFiles();
        for (int i=0;i<files.length;i++){
            file=files[i];
            if (file.getName().endsWith("java")){
                System.out.println("这是一个dll文件"+"="+file.getName());
            }else if(file.getName().endsWith("dll")||!(file.isDirectory())){
                file.delete();
            }
        }
    }

    @Test
    //https://www.cnblogs.com/biehongli/p/6074713.html
    public void testReadFile(){
       for(int filenameIndex=6379;filenameIndex<=6384;filenameIndex++) {
            String filenameIndeStr = String.valueOf(filenameIndex);
            String filepath = "F:" + File.separator + "RedisInsatall" + File.separator + filenameIndeStr + File.separator + "redis.windows.conf";
            String filepath1 = "F:" + File.separator + "RedisInsatall" + File.separator + filenameIndeStr + File.separator + "redis.windows1.conf";
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath1));
                int len = 0;
                String str = "";
                while ((str = bufferedReader.readLine()) != null) {
                    String regexStr = "port\\s{1}[0-9]{4}";
                    Pattern pattern = Pattern.compile(regexStr);
                    Matcher matcher = pattern.matcher(str);

                    Pattern pattern1 = Pattern.compile("#\\s{1}cluster-config-file.*[a-z]+");
                    Matcher matcher1 = pattern1.matcher(str);

                    Pattern pattern2 = Pattern.compile("appendonly no");
                    Matcher matcher2 = pattern2.matcher(str);

                    Pattern pattern3 = Pattern.compile("#\\s{1}[a-z]+\\-{1}[a-z]+\\-{1}[a-z]+\\s{1}(?=15000)");
                    Matcher matcher3 = pattern3.matcher(str);

                    Pattern pattern4 = Pattern.compile("#\\s{1}\\w{7}\\-\\w{7}\\s{1}yes");
                    Matcher matcher4 = pattern4.matcher(str);
                    bufferedWriter.flush();
                    if (matcher.find()) {
                        // 输出捕获到的匹配内容
                        System.out.print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + str);
                        String portNew = "port " + filenameIndex;

                        bufferedWriter.write(portNew);
                        bufferedWriter.newLine();
                    } else if (matcher1.find()) {
                        String newConfig = "cluster-config-file nodes-" + filenameIndex + ".conf";
                        bufferedWriter.write(newConfig);
                        bufferedWriter.newLine();
                    } else if (matcher2.find()) {
                        String newConfig = "appendonly yes";
                        bufferedWriter.write(newConfig);
                        bufferedWriter.newLine();
                    } else if (matcher3.find()) {
                        String newConfig = "cluster-node-timeout 15000";
                        bufferedWriter.write(newConfig);
                        bufferedWriter.newLine();
                    } else if (matcher4.find()) {
                        String newConfig = "cluster-enabled yes";
                        bufferedWriter.write(newConfig);
                        bufferedWriter.newLine();
                    } else {
                        bufferedWriter.write(str);
                        bufferedWriter.newLine();
                    }
                }
                bufferedWriter.close();
                bufferedReader.close();
//                File oragin=new File(filepath);
//                File newFile=new File(filepath1);
//                String spilt1=oragin.getAbsolutePath().substring(oragin.getParent().length()+File.separator.length());
//                System.out.println(spilt1);
//                newFile.renameTo(new File(oragin.getParent()+File.separator+spilt1));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        FileRenameTest();
    }
    @Test
    public void java8WriteFile(){
        String content="Hello word";
        try {
            Files.write(Paths.get("D:\\"+"a.txt"), content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void puduanIfNumerHasZifuchuan(){
        String str = "123abc";
        if (!"".equals(str)) {
            char num[] = str.toCharArray();//把字符串转换为字符数组
            StringBuffer title = new StringBuffer();//使用StringBuffer类，把非数字放到title中
            StringBuffer hire = new StringBuffer();//把数字放到hire中

            for (int i = 0; i < num.length; i++) {

                // 判断输入的数字是否为数字还是字符
                if (Character.isDigit(num[i])) {//把字符串转换为字符，再调用Character.isDigit(char)方法判断是否是数字，是返回True，否则False
                    hire.append(num[i]);// 如果输入的是数字，把它赋给hire
                } else {
                    title.append(num[i]);// 如果输入的是字符，把它赋给title
                }
            }
        }
    }

    @Test
    public void readFileReg(){
        String filepath="F:"+File.separator+"RedisInsatall"+File.separator+"6380"+File.separator+"redis.windows.conf";
        BufferedReader bufferedReader= null;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(filepath)));
            String str=null;
            StringBuffer s1=new StringBuffer();
            while ((str=bufferedReader.readLine())!=null){
                s1.append(str);
                System.out.println(str);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testIsEqual(){
        String str1="";
        Pattern pattern=Pattern.compile(str1);
        String str2="";
        Matcher matcher=pattern.matcher(str2);
        System.out.println(matcher.matches());

    }
    @Test
    public void FileRenameTest(){
        for(int i=6379;i<=6384;i++){
            String istr=String.valueOf(i);
            File oldFile = new File("F:\\RedisInsatall\\"+istr+"\\redis.windows1.conf");
    //        if(!oldFile.exists())
    //        {
    //            try {
    //                oldFile.createNewFile();
    //            } catch (IOException e) {
    //                e.printStackTrace();
    //            }
    //        }
            System.out.println("修改前文件名称是："+oldFile.getName());
            String rootPath = oldFile.getParent();
            System.out.println("根路径是："+rootPath);
            File newFile = new File(rootPath + File.separator + "redis.windows.conf");
            System.out.println("修改后文件名称是："+newFile.getName());
            if (oldFile.renameTo(newFile))
            {
                System.out.println("修改成功!");
            }
            else
            {
                System.out.println("修改失败");
            }
        }
    }
}
