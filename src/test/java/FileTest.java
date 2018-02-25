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
        String filepath="F:"+File.separator+"RedisInsatall"+File.separator+"6380"+File.separator+"redis.windows.conf";
        String filepath1="F:"+File.separator+"RedisInsatall"+File.separator+"6380"+File.separator+"redis.windows1.conf";
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader(filepath));
            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(filepath1));
            int len=0;
            String str="";int jia=6380;
            while ((str=bufferedReader.readLine())!=null) {
                String regexStr = "port\\s{1}[0-9]{4}";
                Pattern pattern = Pattern.compile(regexStr);
                Matcher matcher = pattern.matcher(str);

                Pattern pattern1 = Pattern.compile("#\\s{1}cluster-config-file.*[a-z]+");
                Matcher matcher1 = pattern1.matcher(str);
                bufferedWriter.flush();
                if (matcher.find()) {
                    // 输出捕获到的匹配内容
                    System.out.print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + str);
                    String portNew = "port "+6380;
                    bufferedWriter.write(portNew);
                    bufferedWriter.newLine();
                } else if(matcher1.find()){
                   String newConfig="cluster-config-file nodes-6380.conf";
                    bufferedWriter.flush();
                    bufferedWriter.write(newConfig);
                    bufferedWriter.newLine();
                }else{
                    bufferedWriter.flush();
                    bufferedWriter.write(str);
                    bufferedWriter.newLine();
                }
            }

            bufferedWriter.close();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


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
}
