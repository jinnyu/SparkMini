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
 * <p>Author Written by Kim.</p>
 * <p>Email liloo@liloo.top</p>
 * <p>Date 2017-08-19</p>
 */
public class EncodingFilter implements Filter {

	private String encode;

	/**
	 * 默认编码UTF-8
	 */
	public EncodingFilter() {
		this.encode = Static.CHARSET_UTF8;
	}

	/**
	 * 指定编码
	 * 
	 * @param encode 编码
	 */
	public EncodingFilter(String encode) {
		if (StrUtil.isNotBlank(encode)) this.encode = encode;
		else throw new NullPointerException("Encode can not be null!");
	}

	@Override
	public void handle(Request request, Response response) throws Exception {
		if (Static.log.isDebugEnabled()) Static.log.debug("Set charset to {}.", encode);
		request.raw().setCharacterEncoding(encode);
	}

}
