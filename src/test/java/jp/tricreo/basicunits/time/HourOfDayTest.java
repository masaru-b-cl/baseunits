/*
 * Copyright 2010 TRICREO, Inc. (http://tricreo.jp/)
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
 * Copyright (c) 2005 Domain Language, Inc. (http://domainlanguage.com) This
 * free software is distributed under the "MIT" licence.
 * For more information, see http://timeandmoney.sourceforge.net.
 */
package jp.tricreo.basicunits.time;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import jp.tricreo.basicunits.time.HourOfDay;

import org.junit.Test;

/**
 * {@link HourOfDay}のテストクラス。
 */
public class HourOfDayTest {
	
	/**
	 * {@link HourOfDay#of(int)}のテスト。
	 * 
	 * @throws Exception 例外が発生した場合
	 */
	@Test
	public void test01_24Simple() throws Exception {
		assertThat(HourOfDay.of(22).breachEncapsulationOfValue(), is(22));
	}
	
	/**
	 * {@link HourOfDay#value(int, String)}のテスト。
	 * 
	 * @throws Exception 例外が発生した場合
	 */
	@Test
	public void test02_12Simple() throws Exception {
		assertThat(HourOfDay.value(10, "PM"), is(HourOfDay.of(22)));
		assertThat(HourOfDay.value(3, "am"), is(HourOfDay.of(3)));
	}
	
	/**
	 * {@link HourOfDay#of(int)}の不正引数テスト。
	 * 
	 * @throws Exception 例外が発生した場合
	 */
	@Test
	public void test03_24IllegalLessThanZero() throws Exception {
		try {
			HourOfDay.of(-1);
			fail();
		} catch (IllegalArgumentException ex) {
			// success
		}
	}
	
	/**
	 * {@link HourOfDay#of(int)}の不正引数テスト。
	 * 
	 * @throws Exception 例外が発生した場合
	 */
	@Test
	public void test04_24GreaterThan() throws Exception {
		try {
			HourOfDay.of(24);
			fail();
		} catch (IllegalArgumentException ex) {
			// success
		}
	}
	
	/**
	 * {@link HourOfDay#value(int, String)}の不正引数テスト。
	 * 
	 * @throws Exception 例外が発生した場合
	 */
	@Test
	public void test05_12IllegalLessThanZero() throws Exception {
		try {
			HourOfDay.value(-1, "PM");
			fail();
		} catch (IllegalArgumentException ex) {
			// success
		}
	}
	
	/**
	 * {@link HourOfDay#value(int, String)}の不正引数テスト。
	 * 
	 * @throws Exception 例外が発生した場合
	 */
	@Test
	public void test06_12GreaterThan() throws Exception {
		try {
			HourOfDay.value(13, "AM");
			fail();
		} catch (IllegalArgumentException ex) {
			// success
		}
	}
	
	/**
	 * {@link HourOfDay#value(int, String)}の不正引数テスト。
	 * 
	 * @throws Exception 例外が発生した場合
	 */
	@Test
	public void test07_12BadAmPm() throws Exception {
		try {
			HourOfDay.value(5, "FD");
			fail();
		} catch (IllegalArgumentException ex) {
			// success
		}
	}
	
	/**
	 * {@link HourOfDay#isAfter(HourOfDay)}のテスト。
	 * 
	 * @throws Exception 例外が発生した場合
	 */
	@Test
	public void test08_LaterAfterEarlier() throws Exception {
		HourOfDay later = HourOfDay.of(8);
		HourOfDay earlier = HourOfDay.of(6);
		assertThat(later.isAfter(earlier), is(true));
	}
	
	/**
	 * {@link HourOfDay#isAfter(HourOfDay)}のテスト。
	 * 
	 * @throws Exception 例外が発生した場合
	 */
	@Test
	public void test09_EarlierAfterLater() throws Exception {
		HourOfDay earlier = HourOfDay.of(8);
		HourOfDay later = HourOfDay.of(20);
		assertThat(earlier.isAfter(later), is(false));
	}
	
	/**
	 * {@link HourOfDay#isAfter(HourOfDay)}のテスト。
	 * 
	 * @throws Exception 例外が発生した場合
	 */
	@Test
	public void test10_EqualAfterEqual() throws Exception {
		HourOfDay anHour = HourOfDay.of(8);
		HourOfDay anotherHour = HourOfDay.of(8);
		assertThat(anHour.isAfter(anotherHour), is(false));
	}
	
	/**
	 * {@link HourOfDay#isBefore(HourOfDay)}のテスト。
	 * 
	 * @throws Exception 例外が発生した場合
	 */
	@Test
	public void test11_LaterBeforeEarlier() throws Exception {
		HourOfDay later = HourOfDay.of(8);
		HourOfDay earlier = HourOfDay.of(6);
		assertThat(later.isBefore(earlier), is(false));
	}
	
	/**
	 * {@link HourOfDay#isBefore(HourOfDay)}のテスト。
	 * 
	 * @throws Exception 例外が発生した場合
	 */
	@Test
	public void test12_EarlierBeforeLater() throws Exception {
		HourOfDay earlier = HourOfDay.of(8);
		HourOfDay later = HourOfDay.of(20);
		assertThat(earlier.isBefore(later), is(true));
	}
	
	/**
	 * {@link HourOfDay#isBefore(HourOfDay)}のテスト。
	 * 
	 * @throws Exception 例外が発生した場合
	 */
	@Test
	public void test13_EqualBeforeEqual() throws Exception {
		HourOfDay anHour = HourOfDay.of(8);
		HourOfDay anotherHour = HourOfDay.of(8);
		assertThat(anHour.isBefore(anotherHour), is(false));
	}
}