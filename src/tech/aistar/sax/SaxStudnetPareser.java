package tech.aistar.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import tech.aistar.pojo.Address;
import tech.aistar.pojo.Student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:SAX解析是基于事件驱动
 * @date 2019/4/22 0022
 */
public class SaxStudnetPareser extends DefaultHandler{

    //存放所有的学生信息
    private List<Student> students = new ArrayList<>();

    public List<Student> getStudents(){
        return students;
    }

    private Student student;

    private Address address;

    //定义一个变量,用来拼接标签中的内容
    private StringBuilder builder = new StringBuilder();


    /**
     * 加载xml文件的时候触发的事件
     * @throws SAXException
     */
    @Override
    public void startDocument() throws SAXException {
        System.out.println("文档开始...");
    }

    /**
     * 读取开始标签 - 决定是否创建对象 - 以及是否设置对象的属性值 - 注册对象之间的关系
     * @param uri
     * @param localName
     * @param qName 标签的名称...
     * @param attributes 属性
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if("student".equals(qName)){
            //创建一个student对象
            student = new Student();
            //设置对象的属性 - 根据属性的名称获取属性值
            student.setId(Integer.parseInt(attributes.getValue("id")));
            //将student对象放入到students集合中
            students.add(student);
        }else if("address".equals(qName)){
            //创建一个地址对象
            address = new Address();
            //将address绑定当前的对象
            student.setAddress(address);
        }
    }

    /**
     * 拼接标签中的内容
     * @param ch
     * @param start
     * @param length
     * @throws SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
       builder.append(ch,start,length);
    }

    /**
     * 结束标签 - 将当前标签中的内容设置到对象中
     * @param uri
     * @param localName
     * @param qName
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String v = builder.toString().trim();
        if("name".equals(qName)){
            //将name标签中的数据并且设置到student对象上.
            student.setName(v);
        }else if("no".equals(qName)){
            student.setNo(v);
        }else if("birthday".equals(qName)){
            try {
                student.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(v));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if("province".equals(qName)){
            address.setProvince(v);
        }else if("city".equals(qName)){
            address.setCity(v);
        }
        //清空StringBuilder
        builder.delete(0,builder.capacity());
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("文档结束...");
    }
}
