/**
Apache License V2.0
--------------------------------------------------
Copyright [2017] [Liloo liloo@liloo.top]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
--------------------------------------------------
 */
package cc.liloo.spark.router;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import com.xiaoleilu.hutool.util.CollectionUtil;

import cc.liloo.spark.common.Static;

/**
 * Author Written by Kim.<br>
 * Email liloo@liloo.top<br>
 * Date 2017-08-20
 */
public interface Router {

	/**
	 * 所有路由的注册均在此方法内完成<br>
	 * 若有多个路由需要注册, 新建类并实现Router接口即可.
	 */
	void route();

	/**
	 * <pre><code>
	 * 使用示例
	 * Map&lt;String, Object&gt; json = json(200, "success");
	 * json.put("some other information", ...);
	 * return JSONUtil.toJsonStr(json);
	 * </code></pre>
	 * @param status   json返回状态码
	 * @param response 响应
	 * @return 标准json格式数据<br>{ "status" : int, "response" : object, "time" : "Server time/yyyy-MM-dd HH:mm:ss:SSS", etc... }
	 */
	default Map<String, Object> json(int status, Object response) {
		Map<String, Object> json = CollectionUtil.newHashMap();
		json.put("status", response);
		json.put("response", response);
		json.put("time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")));
		return json;
	}
	
	default void register(String uri) {
		register(false, uri);
	}
	
	/**
	 * 方便调试, 显示注册的URI地址
	 * @param off 关闭日志
	 * @param uri 注册的URI地址
	 */
	default void register(boolean off, String uri) {
		if (!off) if (Static.log.isDebugEnabled()) Static.log.debug("Register uri -> {}", uri);
	}
	
}
