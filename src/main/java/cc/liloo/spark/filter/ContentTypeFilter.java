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
package cc.liloo.spark.filter;

import com.xiaoleilu.hutool.util.StrUtil;

import cc.liloo.spark.common.Static;
import spark.Filter;
import spark.Request;
import spark.Response;

/**
 * Author Written by Kim.<br>
 * Email liloo@liloo.top<br>
 * Date 2017-08-19
 * Default content type is 'application/json; charset=UTF-8'
 */
public class ContentTypeFilter implements Filter {

	private String type;

	/**
	 * 默认响应类型为JSON, UTF-8编码.
	 */
	public ContentTypeFilter() {
		this.type = Static.CONTENT_TYPE_JSON;
	}
	
	/**
	 * 指定响应类型
	 * @param type Http类型
	 * @see Static
	 */
	public ContentTypeFilter(String type) {
		if (StrUtil.isNotBlank(type)) this.type = type;
		else throw new NullPointerException("Content type can not be null!");
	}

	@Override
	public void handle(Request request, Response response) throws Exception {
		if (Static.log.isDebugEnabled()) Static.log.debug("Set application type to {}.", type);
		response.raw().setContentType(type);
	}

}
