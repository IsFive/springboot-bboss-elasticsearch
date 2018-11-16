# springboot-bboss-elasticsearch
##### https://blog.csdn.net/qq_31748587/article/details/84134864
##### 整合springboot+bboss+elasticsearch，实现java对es的操作
##### 需要修改的配置
##### application.yml文件：
##### basePath：对应mapper.xml文件存放的位置
##### indexs：es的全部索引，以逗号分隔
```
es:
  basePath: esmapper
  indexs: teacher,student
```
##### 创建对应的mapper.xml文件即可
