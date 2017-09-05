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
package com.liloo.spark.main;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.liloo.spark.common.Static;
import com.liloo.spark.router.Router;
import com.liloo.spark.router.RouterHandler;
import com.xiaoleilu.hutool.util.CollectionUtil;

/**
 * @Author Written by Kim.
 * @Email liloo@liloo.top
 * @Date 2017-08-17
 */
public class Start {

	/**
	 * 核心启动类
	 */
	public static void main(String[] args) {
		// 扫描包, 寻找Router绑定的对象.
		List<Class<? extends Router>> classes = RouterHandler.getRouters(true);
		if (CollectionUtil.isNotEmpty(classes)) {
			classes.stream().forEach(cls -> {
				try {
					// 反射执行
					Router server = cls.newInstance();
					server.getClass().getMethod("route").invoke(server);
				} catch (InstantiationException | NoSuchMethodException | SecurityException | IllegalArgumentException| IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
			});
		} else {
			Static.log.warn("No router found.");
		}
	}

}
