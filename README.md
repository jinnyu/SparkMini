# SparkMini
[![SparkMini](https://img.shields.io/badge/build-passing-green.svg)]() [![License](https://img.shields.io/badge/License-Apache2-blue.svg)]() 

### [Spark][1] - 一个基于 Java 8的小型Web框架 /A tiny web framework for Java 8

**注意 : 这里的Spark不是用于大数据处理的Spark, 而是一个简单的Web框架, [这里][1]是官方Github地址.**  
**Notice : This Spark is not used for large data processing Spark, but a simple Web framework, [here][1] is the official Github page.**

**这个项目是一个简单的封装, 为的是提供一种简单的REST接口启动方式.**  
**This project is a simple wrapper, in order to provide a simple way to start the REST interface.**

- 环境要求/Environment :
> Java8 + Maven3.3

- Maven :  
```
<!-- https://mvnrepository.com/artifact/cc.liloo/spark-mini -->
<dependency>
	<groupId>cc.liloo</groupId>
	<artifactId>spark-mini</artifactId>
	<version>${version}</version>
</dependency>
```

- 示例/Example :  
```java
--------------------------------------------------
启动类/Start
public static void main(String[] args) {
	List<Class<? extends Router>> list = RouterHandler.getRouters(true);
	list.stream().forEach(cls -> {
		// 反射执行
		try {
			Router server = cls.newInstance();
			server.getClass().getMethod("route").invoke(server);
		} catch (InstantiationException e) {
			// ignore
		} catch (NoSuchMethodException e) {
			// ignore
		} catch (SecurityException e) {
			// ignore
		} catch (IllegalArgumentException e) {
			// ignore
		} catch (InvocationTargetException e) {
			// ignore
		} catch (IllegalAccessException e) {
			// ignore
		}
	});
}
--------------------------------------------------
控制器/Controller
/**
 * 整个项目的使用方法非常简单, 只需要在控制器上实现Router类即可.
 * The use of the entire project is very simple, only need to inherit the Router class can be on the controller.
 */
public class IndexRouter implements Router {
   	@Override
   	public void route() {
		// 这里的Spark是Spark框架提供的, 相关文档请参考Sprak官网. http://sparkjava.com/documentation
		// Spark is provided by the Spark framework. Please refer to the Sprak website for documentation. Http://sparkjava.com/documentation
		Spark.get("/", (request, response) -> {
   			return "Hello, this is index!";
   		});
   		Spark.path("/api", () -> {
   			Spark.get("/account", IndexController::api);
   		});
   	}
   	private static String api(Request requset, Response response) {
   		return "Hello, this is api.";
   	}
}
--------------------------------------------------
```

**感谢Spark和Hutool给我们带来这么好的开源框架.**  
**Thanks to Spark and Hutool for bringing us such a good open source framework.**

 - Spark  Github https://github.com/perwendel/spark
 - Hutool Github https://github.com/looly/hutool

  [1]: https://github.com/perwendel/spark