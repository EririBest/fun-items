package com.example.xml;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author 覃汉宇
 * @date 2023/04/14/22:59
 * @brief
 */
public class TestXmlWrite {
    public static void main(String[] args) {
        String fileName = "xml_test";
        // 2.创建dom对象
        Document document = DocumentHelper.createDocument();

        // 3.添加节点，根据需求添加，这里我只是设置了一个head节点，下面有name和age两个子节点
        Element head = document.addElement("context");
        Element name = head.addElement("name");
        name.setText("LQ");
        for (int i = 0; i < 10; i++) {
            Element age = head.addElement("age");
            age.addText(i+"");
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
        // 7、利用文件输出流输出到文件， 文件输出到了您的项目根目录下了
        try (FileOutputStream fos = new FileOutputStream("E:/"+fileName + ".xml")) {
            fos.write(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            //1.写出格式
//            OutputFormat format = OutputFormat.createPrettyPrint();
//            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(new File("E:\\dates\\userdates\\test.xml")),format);
//            //2.创建一个空白文档
//            Document document = DocumentHelper.createDocument();
//            //3.创建根节点
//            Element rootElement = document.addElement("list");
//            //4.设置子节点
//            Element emp = rootElement.addElement("emp");
//            //5.设置子节点里面的标签。addtext是添加文本
//            emp.addElement("name").addText("马云飞");
//            emp.addElement("age").addText("20");
//            emp.addElement("sex").addText("男");
//            xmlWriter.write(document);
//            System.out.println("操作成功");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
