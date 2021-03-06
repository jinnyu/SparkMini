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
package cc.liloo.spark.log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

import com.xiaoleilu.hutool.util.StrUtil;

import cc.liloo.spark.common.Static;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * Author Written by Kim.<br>
 * Email liloo@liloo.top<br>
 * Date 2017-09-23<br>
 * 利用Logback的拦截器功能实现对自定义日志信息的拦截<br>
 * 设置需要执行的方法<code>setMethod</code>.<br>
 * 如果有参数, 设置参数<code>setArgs</code><br>
 * 
 * <pre>
 * logback配置文件在<code>appender</code>节点中添加如下信息
 * &lt;!-- 拦截器类 --&gt;
 * &lt;filter class="cc.liloo.spark.log.LogbackFilter"&gt;
 *   &lt;!-- 线程名称 --&gt;
 *   &lt;name&gt;thread name&lt;/name&gt;
 * &lt;/filter&gt;
 * </pre>
 */
public class LogbackFilter extends Filter<ILoggingEvent> {

	/** 方法是否注入 */
	private static boolean	method_inject;
	/** 参数是否注入 */
	private static boolean	args_inject;
	/** 被注入的方法 */
	private static Method	method;
	/** 被注入的参数 */
	private static Object[]	args	= null;

	@Override
	public FilterReply decide(ILoggingEvent event) {
		String thread = event.getThreadName();
		if (StrUtil.isNotBlank(thread) && thread.equalsIgnoreCase(getName())) {
			try {
				if (method_inject) {
					if (!args_inject) method.invoke(method);
					else method.invoke(method, args);
				} else {
					Static.log.warn("No method for invoke, set method for invoke");
				}
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (InvocationTargetException e) {
				throw new RuntimeException(e);
			}
		}
		return FilterReply.NEUTRAL;
	}

	public static void setMethod(Method method) {
		if (!Objects.isNull(method) && !method_inject) {
			LogbackFilter.method = method;
			method_inject = true;
		} else {
			throw new NullPointerException("Method can not be null!");
		}
	}

	public static void setArgs(Object[] args) {
		if (!args_inject) {
			for (Object o : args) {
				if (Objects.isNull(o)) throw new NullPointerException("Arg can not be null!");
			}
			LogbackFilter.args = args;
			args_inject = true;
		}
	}

}
