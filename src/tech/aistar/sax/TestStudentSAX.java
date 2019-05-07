package tech.aistar.sax;

import org.xml.sax.SAXException;
import tech.aistar.pojo.Student;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/4/22 0022
 */
public class TestStudentSAX {
    public static void main(String[] args) {
        try {
            for(Student s:getStudents()){
                System.out.println(s);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Student> getStudents() throws ParserConfigurationException, SAXException, IOException {
        //1. 获取SAX解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();

        //2.获取SAX解析器
        SAXParser parser = factory.newSAXParser();

        //3. 准备参数
        SaxStudnetPareser handler = new SaxStudnetPareser();

        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("tech/aistar/dtd/students.xml");

        parser.parse(in,handler);

        return handler.getStudents();
    }
}
