package tech.aistar.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import tech.aistar.pojo.Address;
import tech.aistar.pojo.Student;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/4/22 0022
 */
public class StudentsDom4jParser {
    public static void main(String[] args) {
        try {
            for(Student s:getStudents()){
                System.out.println(s);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static List<Student> getStudents() throws DocumentException, ParseException {
        List<Student> students = new ArrayList<>();

        //1.获取xml文档输入流
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("tech/aistar/dtd/students.xml");

        //2.创建SAXReader对象
        SAXReader reader = new SAXReader();

        //3.创建Document对象 - xml中内存中形成的倒置的"文档树"
        Document document = reader.read(in);

        //4. 获取根元素 - 只有一个
        Element root = document.getRootElement();

        //5.获取root元素下方所有的student标签
        List<Element> elements = root.elements("student");

        //6. 遍历
        for(Element e:elements){//就是指student元素
            //创建一个学生对象
            Student s = new Student();
            //设置属性值
            s.setId(Integer.parseInt(e.attributeValue("id")));

            //设置其他属性的值
            s.setName(e.elementTextTrim("name"));

            s.setNo(e.elementTextTrim("no"));

            s.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(e.elementTextTrim("birthday")));

            //获取Address元素
            Element addrElement = e.element("address");
            //判断是否是存在的
            if(null!=addrElement){
                //创建一个Address对象
                Address addr = new Address();

                //获取address元素下面的province元素
                //只能父去子
                addr.setProvince(addrElement.elementTextTrim("province"));
                addr.setCity(addrElement.elementTextTrim("city"));

                //将地址绑定到学生
                s.setAddress(addr);
            }
            //将对象放入到学生集合中
            students.add(s);
        }
        return students;
    }
}
