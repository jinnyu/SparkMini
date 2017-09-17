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

import javax.servlet.http.HttpServletRequest;

import com.xiaoleilu.hutool.json.JSONArray;
import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.StrUtil;

import cc.liloo.spark.common.Static;
import spark.Filter;
import spark.Request;
import spark.Response;

/**
 * Author Written by Kim.<br>
 * Email liloo@liloo.top<br>
 * Date 2017-08-19
 */
public class IPFilter implements Filter {

	private int			statusCode;
	private JSONObject	obj;

	/**
	 * JSON结构如下, 拦截器会根据定义的规则来进行拦截.
	 * <pre>
	 * {
	 *    "count" : { "extra" : 2, "range" : 2 },
	 *    "extra" : [ "127.0.0.1", "8.8.8.8" ],
	 *    "range" : [ { "start" : "10.0.0.0", "end" : "10.0.0.255" }, { "start" : "192.168.1.1", "end" : "192.168.1.255" } ]
	 * }
	 * </pre>
	 * 
	 * @param statusCode 返回状态码
	 * @param obj IP数据<br>
	 * 计数字段不能为空, 始终为 { "extra" : ?, "range" : ? }, ? 为零或者正整数.<br>
	 * 若例外(extra)IP为空, 则extra字段传空的JSONArray. 范围(range)IP同理传空的JSONObject.
	 */
	public IPFilter(int statusCode, JSONObject obj) {
		if (CollectionUtil.isNotEmpty(obj)) this.obj = obj;
		else this.obj = new JSONObject();
	}

	@Override
	public void handle(Request request, Response response) throws Exception {

		String ip = getIPAddress(request.raw());
		if (!checkIP(ip)) {
			response.status(statusCode);
		}

	}

	public boolean checkIP(String ip) {
		if ("127.0.0.1".equals(ip) || "localhost".equals(ip)) return true;
		JSONObject count = (JSONObject) obj.get("count");
		if (count != null) {
			// 判断是否在独立IP中
			Integer extra = count.getInt("extra");
			if (extra != null && extra > 0) {
				JSONArray eArray = obj.getJSONArray("extra");
				for (Object o : eArray) {
					if (((String) o).equals(ip) || Static.IPV4_PUBLIC.equals((String) o)) { return true; }
				}
			}
			// 判断是否在IP范围内
			Integer range = count.getInt("range");
			if (range != null && range > 0) {
				JSONArray rArray = obj.getJSONArray("range");
				for (Object o : rArray) {
					JSONObject object = (JSONObject) o;
					if (isInRange(ip, object.getStr("start"), object.getStr("end"))) { return true; }
				}
			}
		}
		return false;
	}

	public String getIPAddress(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals(Static.IPV6_LOCALHOST_LONG)) ipAddress = Static.IPV4_LOCALHOST;
		}
		if (ipAddress != null && ipAddress.length() > 15) {
			if (ipAddress.indexOf(",") > 0) ipAddress = ipAddress.substring(0, ipAddress.indexOf(",")); // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		}
		return ipAddress;
	}

	public boolean isInRange(String ip, String start, String end) {
		if (!StrUtil.isAllBlank(ip, start, end) && isIPLegal(ip, start, end)) {
			// 任意IP
			if (ip.equals(Static.IPV4_PUBLIC)) return true;
			String[] ipStr = ip.split("\\.");
			String[] startStr = start.split("\\.");
			String[] endStr = end.split("\\.");
			if (ipStr.length == 4 && startStr.length == 4 && endStr.length == 4) {
				long ipL = IPtoLong(ip);
				long startL = IPtoLong(start);
				long endL = IPtoLong(end);
				if (startL < endL) if (ipL > startL && ipL < endL) return true;
			}
		}
		return false;
	}

	public boolean isIPLegal(String... ips) {
		for (String ip : ips) {
			if (StrUtil.isNotBlank(ip)) {
				String[] str = ip.split("\\.");
				if (str.length == 4) {
					for (int i = 3; i >= 0; i--) {
						try {
							Long.parseLong(str[i]);
						} catch (NumberFormatException e) {
							return false;
						}
					}
					return true;
				}
			}
		}
		return false;
	}

	public long IPtoLong(String ip) {
		String[] str = ip.split("\\.");
		if (str.length != 4) return -1;
		long result = 0;
		for (int i = 3; i >= 0; i--)
			result |= Long.parseLong(str[3 - i]) << (i * 8);
		return result;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}

}
