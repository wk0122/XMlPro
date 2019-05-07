package tech.aistar.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import tech.aistar.pojo.Address;
import tech.aistar.pojo.Student;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/4/22 0022
 */
public class StudentsDom4jWriter {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();

        Student s1 = new Student(1,"tom","1001",new Date());
        Address arr = new Address("江苏","苏州");
        s1.setAddress(arr);

        Student s2 = new Student(2,"tom","1002",new Date());


        Student s3 = new Student(3,"jack","1003",new Date());

        students.add(s1);
        students.add(s2);
        students.add(s3);

        try {
            writerList(students);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void writerList(List<Student> students) throws IOException{

        //1. 创建一个倒置的文档树
        Document document = DocumentHelper.createDocument();

        //2. 添加根节点
        Element root = document.addElement("students");

        for(Student s:students){
            //构建一个student元素
            Element stu = root.addElement("student");
            //设置属性值
            stu.addAttribute("id",String.valueOf(s.getId()));
            //<student id="1"></student>

            //继续给stu元素添加name,no,birthday
            Element nameElement = stu.addElement("name");
            //设置标签中的内容
            nameElement.setText(s.getName());

            Element noElement = stu.addElement("no");
            noElement.setText(s.getNo());

            Element birthElement = stu.addElement("birthday");
            birthElement.setText(String.format("%tF",s.getBirthday()));

            //判断学生中是否存在address
            Address addr = s.getAddress();
            if(null!=addr){
                Element addrEleent = stu.addElement("address");

                Element pro = addrEleent.addElement("province");
                pro.setText(addr.getProvince());

                Element city = addrEleent.addElement("city");
                city.setText(addr.getCity());
            }
        }

        //构建输出格式
        OutputFormat format = new OutputFormat("\t",true);

        //将文档树写出去...
        XMLWriter out = new XMLWriter(new FileOutputStream("src/tech/aistar/dom4j/ss.xml"),format);

        out.write(document);

        out.flush();

        out.close();

        System.out.println("写入成功!");

    }
}
