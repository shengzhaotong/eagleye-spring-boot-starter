<h1 align="center"> eagleye-spring-boot-starter </h1>

<div align="center"><img src="https://s2.loli.net/2022/04/17/bAtsP6yHpmgNQ9L.jpg" alt="vblogo.jpg" height="180px" width="180px"></div>




![mybatis-plus](https://img.shields.io/badge/mybatis--plus-3.4.2-green)![fastjson](https://img.shields.io/badge/fastjson-1.2.68-green)![spring-boot](https://img.shields.io/badge/spring--boot-2.6.3-green)![version](https://img.shields.io/badge/version-1.0.6-green)

![licence](https://img.shields.io/badge/licence-apache--2.0-yellow)![author](https://img.shields.io/badge/author-shengzhaotong-yellow)![team](https://img.shields.io/badge/team-v_byte-yellow)

[![github](https://img.shields.io/badge/github-click here-black)](https://github.com/shengzhaotong/eagleye-spring-boot-starter)[![gitee](https://img.shields.io/badge/gitee-click here-red)](https://gitee.com/shengzhaotong/eagleye-demo)

eagleye是一个在生产环境下可以动态向数据库中添加字段的工具，其本质是利用两张专门的表来记录所需要被添加的字段。

同时，根据银行提出的要求，我们在eagleye中添加了风险决策引擎的功能，项目管理者可以向数据库中提交一个逻辑公式，

然后仅需调用 formula.policyDecision(user.getId()); 即可得到该用户是否通过决策引擎的布尔结果。

以下示例代码均可在gitee中找到https://gitee.com/shengzhaotong/eagleye-demo

在生产环境中，如果我们想给数据库表动态添加字段，主要有以下几种方案：

### 1. 生产环境下增加数据库表

利用表来代替字段，对应到其他的数据库表

缺点：当额外字段多的时候，造成的多联表查询对性能的损耗十分严重

### 2. 预留字段

在涉及表结构的实现，预先预留几个字段，比如FIELD1，FIELD2，FIELD3，那在表结构需要扩展字段的时候，就直接使用之前预留的字段。

缺点：在不需要使用额外字段时，预留字段会造成io性能和网络带宽的损耗，在需要的额外字段过多时，预留字段可能会出现不够的情况

### 3. JSON格式保存

把需要扩展的字段统一放到一个字段里面。各个字段通过JSON的方式组成一个大的字符串。这样在扩展字段的时候，只需要修改这个JSON即可。

缺点：在额外字段较多的情况下，如果只需要修改其中一个字段，也要对全部的json进行反序列化和重新序列化，造成性能的不必要损耗

### 4. 直接在表中动态添加字段

显然，在表中有数据的时候，这样做是行不通的



考虑到以上几种方案的缺点，eagleye仅用两张额外的数据库表，就可以实现对所有类型附加字段的存储，无论多少额外字段，仅做一次联表查询，也仅需一次序列化和反序列化，自动建表，将依赖引入项目中即可使用。



## 使用方式

在maven中导入依赖

```xml
<dependency>
	<groupId>io.github.shengzhaotong</groupId>
	<artifactId>eagleye-spring-boot-starter</artifactId>
	<version>1.0.8</version>
</dependency>
```

在需要附加字段的表中依赖该类，此处拿user表来举例：

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userId;
    private String username;
    private List<ExpandField> expandFields;
}
```

然后在控制器中通过前端传入数据的方式动态添加user表的字段，后端可根据前端传入的typeName进行自动类型判定与转换。

控制器使用方式：

```java
@RestController
public class UserController {
    
    @Autowired
    UserService userService;

    @PutMapping ("/update")
    public Boolean insert (@RequestBody User user) {
        return userService.updateById(user);
    }

}
```

前端需要提交的关于user的json数据：

```json
{
	"expandFields": [
		{
			"jsonValue": "",//json序列化之后的字段值数据
			"name": "",//字段名
			"tableId": "",//该字段所对应的主表的数据id
			"tableName": "",//表名
			"typeName": "",//字段的类型名，给出一个JAVA类的全限定名，如字符串为：java.lang.String。
		}
	],
	"id": "",
	"username": ""
}
```

您还可以对表的其他字段进行全文检索：(返回数据为该表对应的所有数据的id)

```java
@Test
void search () {
    List<String> search = service.search("表名", "全文检索关键字");
}
```

当然，在没有数据的情况下，您还可以只是添加字段类型，用来给前端渲染表单使用：

```java
@RestController
public class FieldController {

    @Autowired
    FieldService fieldService;

    @PostMapping("/insert")
    public Boolean insert (@RequestBody FieldTable table) {
        return this.fieldService.save(table);
    }
    
    @GetMapping("/select")
    public List<FieldTable> getField (String tableName) {
        return fieldService.select(tableName);
    }

}
```

需要提交的数据格式如下：

```json
{
	"name": "",//字段名
	"notes": "",//该字段的注释
	"tableName": "",//表名
	"typeName": ""//类型名，此处也给java类的全限定名
}
```

### 决策引擎功能

该决策引擎对于初筛判定的结果是由一串逻辑公式所决定的，例如，条件a为：年龄<18，条件b为：该用户在企业黑名单，

项目管理员可以逻辑符号链接这两个条件例如：a&b 或者 a|b 来组成总的初筛判定逻辑，其中的条件a和条件b均可由项目

后台管理员自由配置，同时，eagleye也支持将两个条件合并为一个总的条件，例如：a&(b|c) 这种形式。

#### 使用方式

条件和公式均由前端经控制器向后端提交，其基础代码如下：

```java
@RestController
public class RuleController {

    @Autowired
    OperandService operandService;

    @Autowired
    ConditionService conditionService;

    @Autowired
    Formula formula;

    @PostMapping("/insert-operand")
    public Boolean insertOperand (@RequestBody Operand operand) {
        return operandService.save(operand);
    }

    @PostMapping("/insert-condition")
    public Boolean insertCondition (@RequestBody Condition condition) {
        return conditionService.save(condition);
    }

    @PostMapping("/save-formula")
    public Boolean saveFormula (@RequestBody List<Element> list) throws ClassNotFoundException {
        return formula.saveFormula(list);
    }

}
```

以下是前端需要提交的json数据及其解释：

operand：

```json
{
	"fieldName": "",//字段名，即需要被校验的字段
	"tableName": ""//表名，一般为user
}
```

condition:

```json
{
	"dOperand": "",//目标操作数，如条件 年龄<18，这里的18即为目标操作数
	"operator": 0,//操作符号，由数字表示，其对应如下：0:>,1:<,2:=,3:!=,4:>=,5:<=,6:包含(通常用于字符串),7:不包含,8:&,9:|
	"sOperand": 0//源操作数，为数据operand的id，对应一个operand的值，即确定user表中一个需要被校验的字段
}
```

element list:

```json
[
	{
		"conditionOrOperator": 0,//若isOperand==true，该字段为一个condition数据的id，表示操作数，否则为一个operator数字表示的操作符
		"isOperand": true,//该字段表示整个json是否为一个操作数（condition）
	}
]
```

完成上述操作后，调用如下一行代码即可得到对用户风险决策判定的结果

```java
boolean result = formula.policyDecision(user.getId());
```

