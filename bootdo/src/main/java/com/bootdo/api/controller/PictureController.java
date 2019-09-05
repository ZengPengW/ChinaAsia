package com.bootdo.api.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


import com.bootdo.api.utils.APIService;
import com.bootdo.api.utils.HttpResult;
import com.bootdo.api.utils.JsonUtils;
import com.bootdo.api.utils.MyFileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;



/**
 * 图片上传Controller
 * @author zp
 *
 */
@Controller
public class PictureController {



    @Value("${UPLOAD_URL}")
	private  String UPLOAD_URL;
	/**
	 *  文件上传
	 * @param uploadFile
	 * @return
	 */
	@RequestMapping(value="/pic/upload",produces="application/json;charset=utf-8")
	@ResponseBody
	public String picUpload(MultipartFile uploadFile)  {
		Map result=new HashMap();
		try {
			//接收文件
			//取扩展名
			String originalFilename = uploadFile.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.indexOf(".")+1).toLowerCase();
			//拦截脏文件
			if(extName.indexOf("jpg")==-1&&extName.indexOf("gif")==-1&&extName.indexOf("png")==-1&&extName.indexOf("bmp")==-1) {
				throw new RuntimeException("非法格式");
			}
			//上传到图片服务器
//			FastDFSClient fastDFSClient=new FastDFSClient("classpath:resource/client.conf");
//			String url = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
            String path=PictureController.class.getClass().getResource("/").getPath();

            path=path.substring(1);
            int index=path.indexOf("/");
            path=path.substring(0,index+1)+"myimage";
            System.out.println("当前路径："+path);
            File file= MyFileUtils.byte2File(uploadFile.getBytes(),path, UUID.randomUUID()+"."+extName);
            APIService apiService=new APIService();

            HttpResult httpResult = apiService.upload(UPLOAD_URL, file);
            //加载图片服务器地址的属性文件
			String json=httpResult.getBody();
            System.out.println(json);
			Map map= JsonUtils.jsonToPojo(json,Map.class);
			//响应数据
			result.put("error", 0);
			result.put("url", map.get("msg"));
			
			
		} catch (Exception e) {
			result.put("error", 1);
			result.put("message", "图片上传失败");
			e.printStackTrace();
		}
		String sj=JsonUtils.objectToJson(result);
        System.out.println(sj);
		return sj;
		
		
	}

	@RequestMapping("/picturebed")
	public String pictureBed(){
	    return "picturebed/pictureBed";
    }
}
