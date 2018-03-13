import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

/*
*
* source net: http://blog.csdn.net/carl_jiang/article/details/70599628
* */
public class ZipUtil {

    @Test
    public void testAbsoupath() {
        String filepath = "D:\\a\\a.txt";
        File file = new File(filepath);
        String ip = "126.168.1.1";
        System.out.println(ip.indexOf("168"));
        System.out.println(file.getAbsolutePath());


    }
    /**
     * 递归压缩文件夹
     *
     * @param srcRootDir 压缩文件夹根目录的子路径
     * @param file       当前递归压缩的文件或目录对象
     * @param zipOutputStream        压缩文件存储对象
     * @throws Exception
     */

    public static void zip(String srcRootDir, File file, ZipOutputStream zipOutputStream) {
        if (file == null) {
            return;
        }
        //如果是文件直接压缩
        if (file.isFile()) {
            int count, bufferLen = 1024;
            //获取文件相对于压缩文件夹根目录的子路径
            String subpath = file.getAbsolutePath();
            byte data[] = new byte[bufferLen];
            int index = subpath.indexOf(srcRootDir);
            if (index != -1) {
                subpath = subpath.substring(srcRootDir.length() + File.separator.length());
            }
            ZipEntry zipEntry = new ZipEntry(subpath);
            try {
                zipOutputStream.putNextEntry(zipEntry);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                while ((count = bufferedInputStream.read(data, 0, bufferLen)) != -1) {
                    zipOutputStream.write(data, 0, count);
                }
                bufferedInputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            //压缩目录中的文件或子目录
            File[] childFileList = file.listFiles();
            for (int n = 0; n < childFileList.length; n++) {
                childFileList[n].getAbsolutePath().indexOf(file.getAbsolutePath());
                zip(srcRootDir, childFileList[n], zipOutputStream);
            }
        }
    }


    @Test
    public void testIndex() {
        String filepath = "D:\\a\\b";
        File file = new File(filepath);
        for(File file1 : file.listFiles()){
            System.out.println(file1.getAbsolutePath().indexOf(file.getAbsolutePath()));
        }
    }


    /**
     * 对文件或文件目录进行压缩
     * @param srcPath 要压缩的源文件路径。如果压缩一个文件，则为该文件的全路径；如果压缩一个目录，则为该目录的顶层目录路径
     * @param zipPath 压缩文件保存的路径。注意：zipPath不能是srcPath路径下的子文件夹
     * @param zipFilename 压缩文件名
     * @throws Exception
     */
    public static void zip(String srcPath,String zipPath,String zipFilename){
        if(StringUtils.isEmpty(srcPath) ||StringUtils.isEmpty(zipPath)||StringUtils.isEmpty(zipFilename)){
            System.out.println("压缩源文件路径为空或者压缩文件保存的路径为空，压缩文件名为空");
            return;
        }
        CheckedOutputStream cos=null;
        ZipOutputStream zos=null;
        File srcFile =new File(srcPath);
        //判断压缩文件保存的路径是否为源文件路径的子文件夹，如果是，则抛出异常（防止无限递归压缩的发生）
            if(srcFile.isDirectory()&&zipPath.indexOf(srcPath)!=-1){
                System.out.println("和源文件一模一样会造成无限递归的死循环");
                return;
            }
        File zipDir=new File(zipPath);
        if(!zipDir.exists()||!zipDir.isDirectory()){
            zipDir.mkdirs();
        }
        //创建压缩文件保存的文件对象
        String zipFilePath=zipPath+File.separator+zipFilename;
        File zipFile=new File(zipFilePath);
        if(zipFile.exists()){
            SecurityManager securityManager=new SecurityManager();
            securityManager.checkDelete(zipFilePath);
            zipFile.delete();
        }
        try {
            cos=new CheckedOutputStream(new FileOutputStream(zipFile),new CRC32());
            zos=new ZipOutputStream(cos);
            //如果只是压缩一个文件，则需要截取该文件的父目录
            String srcRootDir=srcPath;
            if(srcFile.isFile()){
                int index=srcPath.lastIndexOf(File.separator);
                if(index!=-1){
                    srcRootDir=srcPath.substring(0,index);
                }
            }
            //调用递归压缩方法进行目录或文件压缩
            zip(srcRootDir, srcFile, zos);
            zos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally
        {
            try
            {
                if (zos != null)
                {
                    zos.close();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        String zipPath="D:\\zipTest";
        String srcPath="D:\\a";
        String zipFileName = "test.zip";
        zip(srcPath,zipPath,zipFileName);

        String zipFilePath="D:\\zipTest\\test.zip";
        String unzipFilePath="D:\\zipTest";

        upzip(zipFilePath,unzipFilePath,true);

    }

    //_________________________________________________________________________________________________

    /**
     * 解压缩zip包
     * @param zipFilePath zip文件的全路径
     * @param unzipFilePath 解压后的文件保存的路径
     * @param includeZipFileName 解压后的文件保存的路径是否包含压缩文件的文件名。true-包含；false-不包含
     */
    public static void upzip(String zipFilePath,String unzipFilePath,boolean includeZipFileName){
        if(StringUtils.isEmpty(zipFilePath)||StringUtils.isEmpty(unzipFilePath)){
            System.out.println("请输入zip文件路径和要解压的文件路径");
            return;
        }
        File zipFile=new File(zipFilePath);
        //如果解压后的文件保存路径包含压缩文件的文件名，则追加该文件名到解压路径
        if (includeZipFileName) {
            String fileName=zipFile.getName();
            if(StringUtils.isNotEmpty(fileName)){
                fileName=fileName.substring(0,fileName.lastIndexOf("."));
            }
            unzipFilePath = unzipFilePath + File.separator + fileName;
        }
        //创建解压路径
        File upzipFileDir=new File(unzipFilePath);
        if(!upzipFileDir.exists()||!upzipFileDir.isDirectory()){
            upzipFileDir.mkdirs();
        }
        //开始解压
        ZipEntry entry=null;
        String entryFilePath=null,entryDirPath=null;
        File entryFile=null,entryDir=null;
        int index=0,count=0,bufferSize=1024;
        byte []buffer=new byte[bufferSize];
        BufferedInputStream bufferedInputStream=null;
        BufferedOutputStream bufferedOutputStream=null;
        ZipFile zip = null;
        try {
            zip = new ZipFile(zipFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            Enumeration<ZipEntry> enties = (Enumeration<ZipEntry>) zip.entries();
            ///循环对压缩包里的每一个文件进行解压
            while (enties.hasMoreElements()) {
                //构建压缩包中一个文件解压后保存的文件全路径
                entry = enties.nextElement();
                entryFilePath = unzipFilePath + File.separator + entry.getName();
                index = entryFilePath.lastIndexOf(File.separator);
                if (index != -1) {
                    entryDirPath = entryFilePath.substring(0, index);
                } else {
                    entryDirPath = "";
                }
                entryDir = new File(entryDirPath);
                //如果文件夹路径不存在，则创建文件夹
                if (!entryDir.exists()||!entryDir.isDirectory()) {
                    entryDir.mkdirs();
                }
                entryFile=new File(entryFilePath);
                if(!(entryFile.getParentFile().exists())){
                    entryFile.getParentFile().mkdirs();
                }
                entryFile.createNewFile();
//                写入文件
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(entryFile));
                bufferedInputStream = new BufferedInputStream(zip.getInputStream(entry));
                while ((count = bufferedInputStream.read(buffer, 0, bufferSize)) != -1) {
                    bufferedOutputStream.write(buffer, 0, count);
                }
                bufferedOutputStream.flush();
            }

            bufferedOutputStream.close();
           } catch (ZipException e1) {
                e1.printStackTrace();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();

    }


    }


}