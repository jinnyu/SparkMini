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
package cc.liloo.spark.common;

import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;

/**
 * Author Written by Kim.<br>
 * Email liloo@liloo.top<br>
 * Date 2017-08-19
 */
public class Static {

	// Log
	public static final Log		log						= LogFactory.get();

	// IP
	public static final String	IP_LOCALHOST			= "localhost";

	public static final String	IPV4_LOCALHOST			= "127.0.0.1";
	public static final String	IPV4_PUBLIC				= "0.0.0.0";

	public static final String	IPV6_LOCALHOST_SHORT	= "::1";
	public static final String	IPV6_LOCALHOST_LONG		= "0:0:0:0:0:0:0:1";
	public static final String	IPV6_PUBLIC_SHORT		= "::";
	public static final String	IPV6_PUBLIC_LONG		= "0:0:0:0:0:0:0:0";

	// Charset
	public static final String	CHARSET_UTF8			= "UTF-8";

	// Content type
	public static final String	CONTENT_TYPE_HTML		= "text/html";
	public static final String	CONTENT_TYPE_XML		= "application/xml";
	public static final String	CONTENT_TYPE_JSON		= "application/json";

	public static final String	CONTENT_TYPE_CSS		= "text/css";
	public static final String	CONTENT_TYPE_JS			= "application/x-javascript";

	public static final String	CONTENT_TYPE_JPG		= "image/jpeg";
	public static final String	CONTENT_TYPE_PNG		= "image/png";
	public static final String	CONTENT_TYPE_GIF		= "image/gif";
	public static final String	CONTENT_TYPE_WEBP		= "image/webp";
	public static final String	CONTENT_TYPE_SVG		= "image/svg+xml";

	public static final String	CONTENT_TYPE_STREAM		= "application/octet-stream";

}
