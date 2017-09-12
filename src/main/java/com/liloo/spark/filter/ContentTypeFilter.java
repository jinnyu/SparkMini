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
package com.liloo.spark.filter;

import com.liloo.spark.common.Static;
import com.xiaoleilu.hutool.util.StrUtil;

import spark.Filter;
import spark.Request;
import spark.Response;

/**
 * <p>Author Written by Kim.</p>
 * <p>Email liloo@liloo.top</p>
 * <p>Date 2017-08-19</p>
 */
public class ContentTypeFilter implements Filter {

	private String type;

	/**
	 * @param type Http类型
	 */
	public ContentTypeFilter(String type) {
		if (StrUtil.isNotBlank(type)) this.type = type;
	}

	@Override
	public void handle(Request request, Response response) throws Exception {
		if (Static.log.isDebugEnabled()) Static.log.debug("Set application type to {}.", type == null ? Static.CONTENT_TYPE_JSON : type);
		response.raw().setContentType(type);
	}

}
