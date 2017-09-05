# SparkMini

#### [Spark][1] - 一个基于 Java 8的小型Web框架
#### [Spark][1] - A tiny web framework for Java 8


注意 : 这里的Spark不是用于大数据处理的Spark, 而是一个简单的Web框架, [这里是官方Github地址][1].  
Notice : This Spark is not used for large data processing Spark, but a simple Web framework, [here][1] is the official Github address.  

这个项目是一个简单的封装, 为的是提供一种简单的启动方式, 而不是利用"繁重"的Web容器来提供Rest接口.
This project is a simple wrapper, in order to provide a simple way to start, rather than the use of "heavy" Web container to provide Rest interface.  

 - 整个项目的使用方法非常简单, 只需要在控制器上继承```Router```类即可.  
   The use of the entire project is very simple, only need to inherit the ```Router``` class can be on the controller.

    示例 :
    ```
    public class IndexController implements Router {
    
    	@Override
    	public void route() {
			// 这里的Spark是Spark框架提供的, 相关文档请参考Sprak文档. http://sparkjava.com/documentation
			Spark.get("/", (request, response) -> {
    			return "Hello, this is index!";
    		});
    		Spark.path("/api", () -> {
    			Spark.get("/account", IndexController::api);
    		});
    	}
    
    	private static String api(Request requset, Response response) {
    		return "This is api.";
    	}
    	
    }
    ```
    启动 :
    ```
    
    ```

  [1]: https://github.com/perwendel/spark
  [2]: http://sparkjava.com/documentation