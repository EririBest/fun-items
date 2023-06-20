package com.example.controller;

import com.example.domain.Goods;
import com.example.domain.HtmlCommodityDataObject;
import com.example.domain.User;
import com.example.service.CommodityService;
import org.apache.ibatis.annotations.Param;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 覃汉宇
 * @date 2023/04/18/13:54
 * @brief
 */
@SuppressWarnings("all")
@CrossOrigin
@RestController
@RequestMapping("ccs")
public class CommodityController {
    @Resource
    private CommodityService commodityService;
    @PostMapping("addUserCommodity")
    public void addUserCommodity(@Param("text") String text,@Param("title") String title,@Param("price")Integer price,String type, User user, MultipartFile[] files){
        String xmlFileName = title+new Date().getMinutes();
        Document document = DocumentHelper.createDocument();
        Element speak = document.addElement("speak");
        Element context = speak.addElement("context");
        context.addElement("text").addText(text);
        System.out.println(files.length);
        int i = 0;
        String firstImgPath = "";
        for (MultipartFile photo:files){
            if (photo!=null && photo.getSize()>0){
                i++;
                String imgPath = "";
                String originalFilename = photo.getOriginalFilename();
                String filename = i + "CommoditySculpture" + originalFilename.substring(originalFilename.lastIndexOf("."));
                try {
                    new File("E:\\dates\\userdates\\"+user.getName()+user.getId()+"\\"+title+new Date().getMinutes()).mkdirs();
                    photo.transferTo(new File("E:\\dates\\userdates\\"+user.getName()+user.getId()+"\\"+title+new Date().getMinutes()+"\\"+filename));
                    imgPath = "base/userdates/"+user.getName()+user.getId()+"/"+title+new Date().getMinutes()+"/"+filename;
                    if(i==1){
                        firstImgPath =imgPath;
                    }
                    context.addElement("img").addText(imgPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 4、格式化模板
        //OutputFormat format = OutputFormat.createCompactFormat();
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        // 5、生成xml文件
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            XMLWriter writer = new XMLWriter(out, format);
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 6、生成的XML文件
        // 7、利用文件输出流输出到文件， 文件输出到指定的用户数据目根目录下了
        try (FileOutputStream fos = new FileOutputStream("E:\\dates\\userdates\\"+user.getName()+user.getId()+"\\"+title+new Date().getMinutes()+"\\"+xmlFileName+ ".xml")) {
            fos.write(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        commodityService.addUserGoods(user.getId(),title,price,type,firstImgPath,"E:\\dates\\userdates\\"+user.getName()+user.getId()+"\\"+title+new Date().getMinutes()+"\\"+xmlFileName+ ".xml");
    }
    @PostMapping("getUserCommoditys")
    public List<Goods> getUserCommoditys(Integer userId){
        return commodityService.getUserCommoditys(userId);
    }
    @GetMapping("get")
    public List<Goods> getCommoditys(){
        return commodityService.getCommoditys();
    }
    @RequestMapping("getById")
    public HtmlCommodityDataObject getById(@RequestParam("id") Integer id){
        Goods goods = commodityService.getById(id);
        List<String> imgs = new ArrayList<String>();
        String texts = "";
        SAXReader reader = new SAXReader();
        if (goods==null) {
            return null;
        }
        File file = new File(goods.getXmlpath());
        if(!file.exists()) {
            return null;
        }
        try {
            //3.读取文件返回文档类
            Document read = reader.read(file);
            /*
             * 4.getRootElement();获得根标签
             */
            Element root = read.getRootElement();
            /*
             * 5.将标签中的内容作为字符串返回
             */
            String Text = root.asXML();
            System.out.println(Text);

            //6.获得根标签下的所有标签
            List<Element> list = root.elements();
            for(Element e:list) {
                List<Element> xmlpath = e.elements("img");
                if(xmlpath != null){
                    for (Element imgpath:xmlpath){
                        imgs.add(imgpath.getTextTrim());
                    }
                }
                Element text = e.element("text");
                texts = text.getTextTrim();
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        System.out.println(texts);
        return new HtmlCommodityDataObject(goods,texts,imgs);
    }
    @GetMapping("getKey")
    public List<Goods> getKey(){
        return commodityService.getKey();
    }
    @PostMapping("getByType")
    public List<Goods> getByType(@Param("keyword") String keyword){
        return commodityService.getByType(keyword);
    }
    @PostMapping("select")
    public List<Goods> select(@Param("keyword")String keyword){
        return commodityService.select(keyword);
    }
    @PostMapping("types")
    public String[] getTypes(@RequestParam(defaultValue = "@#$#%#@$#@$#@$#@$") String name){
        return commodityService.getTypes(name);
    }
    @PostMapping("del")
    public Boolean delete(Integer id){
        return commodityService.delete(id);
    }
}
