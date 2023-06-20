package com.example.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/**
 * @author 覃汉宇
 * @date 2023/04/14/14:54
 * @brief
 */
public class TestXmlRead {
    public static void main(String[] args) {
        SAXReader reader = new SAXReader();
        File file = new File("E:\\dates\\3.xml");
        if(!file.exists()) {
            return;
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
//            String String = root.asXML();
////            System.out.println(String);
//            System.out.println(String);
            //6.获得根标签下的所有标签
            List<Element> list = root.elements();
            for(Element e:list) {
//                System.out.println(e.element(e.getName()).getTextTrim());
//                //7.获得根标签下的name标签
                //8.获得标签名字
                //9.获得name标签下的值
//                String nameValue = nameEle.getTextTrim();
//                System.out.println("用户："+nameValue);
//                //10.获得age标签下的值

                List<Element> ageEle = e.elements("img");
                for (Element img: ageEle
                     ) {
                    System.out.println(img.getTextTrim());
                }


//                String ageValue = ageEle.getTextTrim();
//                System.out.println("@的人："+ageValue);
//                //11.获得sex标签下的值
//                Element sexEle = e.element("img");
//
//                String sexValue = sexEle.getTextTrim();
//                System.out.println("图片："+sexValue);
//                //12.输出一个人的所有信息
//                System.out.println(nameValue+","+ageValue+","+sexValue);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}

