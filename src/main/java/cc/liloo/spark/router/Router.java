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
	public void route();

}
