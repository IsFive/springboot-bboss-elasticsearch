package com.mn.springboot.controller;

import com.mn.springboot.pojo.Teacher;
import com.mn.springboot.utils.es.ESClient;
import com.mn.springboot.utils.es.ESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TeacherController {
    @Autowired
    private ESUtil esUtil;

    @GetMapping("add")
    public String add(){
        Teacher teacher = new Teacher();
        teacher.setTeacherId("2");
        teacher.setName("吴老师");
        teacher.setSex("男");
        teacher.setAge(50);
        Teacher teacher1 = new Teacher();
        teacher1.setTeacherId("1");
        teacher1.setName("王老师");
        teacher1.setSex("女");
        teacher1.setAge(20);
        List<Teacher> list = new ArrayList<>();
        list.add(teacher);
        list.add(teacher1);
        esUtil.addOrUpdateDocuments("teacher",list);
        return "ok";
    }

    @GetMapping("delete")
    public String delete(){
        esUtil.deleteDocumentById("teacher","1");
        return "ok";
    }

    @GetMapping("get")
    public List<Teacher> get(){
        Teacher teacher = new Teacher();
        teacher.setTeacherId("2");
        List<Teacher> t = esUtil.getDocumentList("teacher",teacher,"searchTeacher",ESClient.restClient("teacher"));
        return t;
    }
}
