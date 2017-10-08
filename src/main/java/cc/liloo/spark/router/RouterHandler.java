/**
Apache License V2.0
--------------------------------------------------
Copyright (c) 2017-2017 Liloo (liloo@liloo.top)

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

import java.util.List;

import cc.liloo.spark.clazz.ClassSearcher;
import cc.liloo.spark.common.Static;

/**
 * Author Written by Kim.<br>
 * Email liloo@liloo.top<br>
 * Date 2017-08-20
 */
public class RouterHandler {

	/**
	 * 扫描所有子类
	 * 
	 * @param scanJar 是否扫描jar包中的类
	 * @return Router子类集合
	 */
	public static List<Class<? extends Router>> getRouters(boolean scanJar) {
		List<Class<? extends Router>> classes = ClassSearcher.of(Router.class).includeAllJarsInLib(scanJar).search();
		if (Static.log.isDebugEnabled()) {
			classes.stream().forEach(cls -> {
				Static.log.debug("Bind Route Class -> {}", cls.getName());
			});
		}
		return classes;
	}

}
