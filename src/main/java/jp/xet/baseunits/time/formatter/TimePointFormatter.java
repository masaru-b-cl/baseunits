/*
 * Copyright 2011 Daisuke Miyamoto. (http://d.hatena.ne.jp/daisuke-m)
 * Created on 2011/11/22
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.xet.baseunits.time.formatter;

import java.util.Locale;
import java.util.TimeZone;

import jp.xet.baseunits.time.TimePoint;

/**
 * TODO for daisuke
 */
public interface TimePointFormatter {
	
	@SuppressWarnings("javadoc")
	String format(TimePoint target);
	
	@SuppressWarnings("javadoc")
	String format(TimePoint target, Locale locale);
	
	/**
	 * TODO for daisuke
	 * 
	 * @param target
	 * @param locale
	 * @param timeZone 
	 * @throws IllegalArgumentException 引数に{@code null}を与えた場合
	 * @return
	 */
	String format(TimePoint target, Locale locale, TimeZone timeZone);
	
	@SuppressWarnings("javadoc")
	String format(TimePoint target, TimeZone timeZone);
}