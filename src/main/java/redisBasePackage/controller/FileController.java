package redisBasePackage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

//https://www.cnblogs.com/hello-yao-ge/p/6606564.html从request中获取web路径各种信息
@Controller
@RequestMapping(value = "/upload",method = RequestMethod.POST)
public class FileController {
//    http://blog.csdn.net/xiejx618/article/details/43638537 比较好的@modelAndView的解释
    @RequestMapping("/file")
    @ResponseBody
    public String uploadFile(HttpServletRequest request,MultipartFile file) {
        if (!(file.isEmpty())) {
            String path = request.getServletContext().getRealPath("/images/");
            System.out.println(request.getContextPath() + "测试看看");
            String filename = file.getOriginalFilename();
//http://blog.csdn.net/houfeng30920/article/details/51496852   File操作的博客
            // 根据parent路径名字符串和child路径名字符串创建一个新File实例
//            http://blog.csdn.net/yumolan4325/article/details/78977462 zip的读写操作
            File filepath = new File(path, filename);
//            http://blog.csdn.net/li_sponge_crazy_/article/details/53143363 file.getParentFile()创建文件
            if (!filepath.getParentFile().exists()) {
//                file.mkdir（）只创建单个文件夹和filepath。mkdirs（）的区别
/*                解说：这是一个在SDCard上建立文件的方法，mkdirs()方法一般是用来建立“多级”文件夹目录的，
                （当你不知道此文件夹是否存在，也不知道父文件夹存在不存在），就可用此方法，它建立文件夹的原则是：如果父文件夹不存在并且最后一级子文件夹不存在，
                它就自动新建所有路经里写的文件夹；如果父文件夹存在，
                那么它就会直接在已经存在的父文件夹下新建子文件夹。*/
                filepath.getParentFile().mkdirs();
            }
            try {
                file.transferTo(new File(path + File.separator + filepath));
                return "\"status\"：\"SUCCESS \"";
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return "\"status\"：\"FAIL \"";
    }
}
