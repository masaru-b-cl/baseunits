/*
 * Copyright 2011 Daisuke Miyamoto. (http://d.hatena.ne.jp/daisuke-m)
 * Copyright 2010-2011 TRICREO, Inc. (http://tricreo.jp/)
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
 * ----
 * Copyright (c) 2004 Domain Language, Inc. (http://domainlanguage.com)
 * This free software is distributed under the "MIT" licence.
 * For more information, see http://timeandmoney.sourceforge.net.
 */
package jp.xet.baseunits.timeutil;

import java.util.TimeZone;

import jp.xet.baseunits.time.CalendarDate;
import jp.xet.baseunits.time.TimePoint;
import jp.xet.baseunits.time.TimeSource;

/**
 * 時計を表すクラス。
 * 
 * <p>このクラスはステートを持つstaticなユーティリティクラスである。</p>
 * 
 * @since 1.0
 */
public final class Clock {
	
	private static TimeSource timeSource;
	
	/** 日付の算出に使用する {@link TimeZone} */
	private static TimeZone defaultTimeZone;
	
	
	/**
	 * この時計が日付の算出に使用する {@link TimeZone} を取得する。
	 * 
	 * @return 日付の算出に使用する {@link TimeZone}
	 * @since 1.0
	 */
	public static TimeZone defaultTimeZone() {
		// There is no reasonable automatic default.
		return defaultTimeZone;
	}
	
	/**
	 * 現在時刻を取得する。
	 * 
	 * @return 現在時刻
	 * @since 1.0
	 */
	public static TimePoint now() {
		return timeSource().now();
	}
	
	/**
	 * このクラスが保持するステートをリセットする。
	 * 
	 * <p>このクラスは、staticに {@link TimeZone} と {@link TimeSource} を保持している。</p>
	 * @since 1.0
	 */
	public static void reset() {
		defaultTimeZone = null;
		timeSource = null;
	}
	
	/**
	 * 日付の算出に使用する {@link TimeZone} を設定する。
	 * 
	 * @param defaultTimeZone 日付の算出に使用する {@link TimeZone}
	 * @since 1.0
	 */
	public static void setDefaultTimeZone(TimeZone defaultTimeZone) {
		Clock.defaultTimeZone = defaultTimeZone;
	}
	
	/**
	 * このクラスに対する時刻の問い合わせに応答する {@link TimeSource} を設定する。
	 * 
	 * @param timeSource このクラスに対する時刻の問い合わせに応答する {@link TimeSource}
	 * @since 1.0
	 */
	public static void setTimeSource(TimeSource timeSource) {
		Clock.timeSource = timeSource;
	}
	
	/**
	 * {@link TimeSource}を取得する。
	 * 
	 * <p>デフォルトでは {@link SystemClock} を使用する。</p>
	 * 
	 * @return {@link TimeSource}
	 * @since 1.0
	 */
	public static TimeSource timeSource() {
		if (timeSource == null) {
			setTimeSource(SystemClock.timeSource());
		}
		return timeSource;
	}
	
	/**
	 * 今日の日付を取得する。
	 * 
	 * <p>日付は、あらかじめ設定済みの {@link TimeZone} に基づき計算する。
	 * {@link TimeZone}を未設定の状態でこのメソッドを呼び出してはならない。</p>
	 * 
	 * @return 今日の日付
	 * @throws IllegalStateException {@link TimeZone}が未設定の場合
	 * @since 1.0
	 */
	public static CalendarDate today() {
		if (defaultTimeZone() == null) {
			throw new IllegalStateException("CalendarDate cannot be computed without setting a default TimeZone.");
		}
		return today(defaultTimeZone());
	}
	
	/**
	 * 今日の日付を取得する。
	 * 
	 * @param timeZone タイムゾーン
	 * @return 今日の日付
	 * @since 1.0
	 */
	public static CalendarDate today(TimeZone timeZone) {
		return now().calendarDate(timeZone);
	}
	
	private Clock() {
	}
}