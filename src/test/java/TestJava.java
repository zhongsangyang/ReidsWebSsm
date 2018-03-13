//import java.io.Serializable;
//
//public class TestJava implements Serializable {
//    public static void fileDownLoad(HttpServletResponse response, String filePath) throws ServletException {
//        BufferedInputStream bis = null;
//        BufferedOutputStream bos = null;
//        OutputStream fos = null;
//        InputStream fis = null;
//        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
//        try {
//            response.reset();
//            response.setContentType(getContentType(fileName));
//            response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
//            fis = new FileInputStream(filePath);
//            bis = new BufferedInputStream(fis);
//            fos = response.getOutputStream();
//            bos = new BufferedOutputStream(fos);
//            int bytesRead = 0;
//            byte[] buffer = new byte[5 * 1024];
//            while ((bytesRead = bis.read(buffer)) != -1) {
//                bos.write(buffer, 0, bytesRead);// 将文件发送到客户端
//            }
//        }catch (IOException e) {
//            response.reset(); e.printStackTrace();
//        } finally
//        {
//            try {
//                if (fos != null)
//                { fos.close();
//                }
//                if (bos != null) {
//                    bos.close();
//                } if(fis != null) {
//                    fis.close();
//                 } if (bis != null)  {
//                    bis.close();
//                }
//            }catch (IOException e) {
//                System.err.print(e);
//            }
//        }
//    }
//}
