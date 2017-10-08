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
package cc.liloo.spark.filter;

import com.xiaoleilu.hutool.util.StrUtil;

import cc.liloo.spark.common.Static;
import spark.Filter;
import spark.Request;
import spark.Response;

/**
 * Author Written by Kim.<br>
 * Email liloo@liloo.top<br>
 * Date 2017-08-19<br>
 * Default charset is 'UTF-8'
 */
public class CharsetFilter implements Filter {

	private String charset;

	/**
	 * 默认编码UTF-8
	 */
	public CharsetFilter() {
		this.charset = Static.CHARSET_UTF8;
	}

	/**
	 * 指定编码
	 * 
	 * @param charset 编码
	 */
	public CharsetFilter(String charset) {
		if (StrUtil.isNotBlank(charset)) this.charset = charset;
		else throw new NullPointerException("Charset can not be null!");
	}

	@Override
	public void handle(Request request, Response response) throws Exception {
		if (Static.log.isDebugEnabled()) Static.log.debug("Set charset to {}.", charset);
		request.raw().setCharacterEncoding(charset);
	}

}
