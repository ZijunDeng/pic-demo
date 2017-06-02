package com.example;

import com.sun.xml.internal.bind.v2.TODO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by csdaniel on 2016/7/27.
 */
@Path("/pic")
public class picController {

    String picUrl="http://discuz.comli.com/weixin/weather/icon/cartoon.jpg";
    String picPath="pic/123.jpg";
    String picId="abc";

    //调用该接口可以下载并保存图片
    @GET
    @Path("/saving")
    public String downAndSave() throws IOException{

        //下载图片
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(picUrl).build();
        Response response = client.newCall(request).execute();

        //保存图片
        byte picData[] = response.body().bytes();
        FileOutputStream out = new FileOutputStream(picPath);//注意要事先创建好需要保存到的目录。默认从根目录开始搜索
        out.write(picData);
        out.close();

        return "success";
    }

    //用参数abc调用接口，可获取图片
    @GET
    @Path("/getting/{toGetPicId}")
    @Produces("image/*")
    public javax.ws.rs.core.Response returnPic(@PathParam("toGetPicId")String toGetPicId){
        if(picId.equals(toGetPicId)){
            File f = new File(picPath);
            if (!f.exists()) {
                // TODO: 抛出图片不存在异常
            }
            String mt = new MimetypesFileTypeMap().getContentType(f);
            return javax.ws.rs.core.Response.ok(f, mt).build();
        }
        return null;
    }

}
