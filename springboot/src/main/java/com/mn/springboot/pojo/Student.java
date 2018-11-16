package com.mn.springboot.pojo;/*

import com.frameworkset.orm.annotation.ESId;
import org.frameworkset.elasticsearch.entity.ESBaseData;

/**
 * 测试实体，可以从ESBaseData对象继承meta属性，检索时会将文档的一下meta属性设置到对象实例中
 */

import com.frameworkset.orm.annotation.ESId;
import org.frameworkset.elasticsearch.entity.ESBaseData;

public class Student extends ESBaseData {
	//设定文档标识字段
	@ESId
	private String studentId;
	private String name;
	private Integer age;
	private String sex;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	/**  当在mapping定义中指定了日期格式时，则需要指定以下两个注解,例如
	 *
	 "agentStarttime": {
	 "type": "date",###指定多个日期格式
	 "format":"yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd'T'HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss||epoch_millis"
	 }
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	 @Column(dataformat = "yyyy-MM-dd HH:mm:ss.SSS")
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
