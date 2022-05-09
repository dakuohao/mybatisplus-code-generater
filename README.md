# 代码生成
读取数据库表信息，然后自动生产CURD模板代码，ORM框架基于Mybatis-Plus实现。

因为业务代码大部分的curd，手写比较恶心，自己封装了这个自动生产代码的工具，
优点是100%定制化，提高开发效率

## 自定义模板
- [controller](src/main/resources/mybatis-plus-curd-no-service-impl/controller.java.vm)
- [serviceImpl](src/main/resources/mybatis-plus-curd-no-service-impl/serviceImpl.java.vm)
- [entity](src/main/resources/mybatis-plus-curd-no-service-impl/entity.java.vm)
- [mapper.java](src/main/resources/mybatis-plus-curd-no-service-impl/mapper.java.vm)
- [mapper.xml](src/main/resources/mybatis-plus-curd-no-service-impl/mapper.xml.vm)

## 进阶-实现原理
原理也很简单，读取数据库的元信息，根据table的元信息，生成对应的curd代码

核心是生成的代码模板必须高度定制化，跟你的团队和开发习惯相匹配

网上生成的工具一大堆，但是都不能定制化

使用mybatis-plus的时候发现作者实现了一个，拿来主义简单封装了下，留作自己备用

节省时间