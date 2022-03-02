package com.pj.ad.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.util.StringUtils;

public class CoreUtils {

	public static LocalDate parseLocalDate(String text) {
		return LocalDate.parse(trim(text), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public static LocalDateTime parseLocalDateTime(String text) {
		return LocalDateTime.parse(trim(text), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	}

	public static Date toDate(LocalDate localDate) {
		Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);
		return date;
	}

	public static Date atStartOfDay(LocalDate localDate) {
		return toDate(localDate.atStartOfDay());
	}

	public static Date atEndOfDay(LocalDate localDate) {
		return toDate(localDate.atStartOfDay().with(LocalTime.MAX));
	}

	public static Date toDate(LocalDateTime localDateTime) {
		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(instant);
	}

	public static String format(Date date, String pattern) {
		DateFormat dateFormat = new SimpleDateFormat(pattern, Locale.US);
		return dateFormat.format(date);
	}

	public static Date parse(String source, String pattern) {
		DateFormat dateFormat = new SimpleDateFormat(pattern, Locale.US);
		try {
			return dateFormat.parse(source);
		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static String format(Date date, String pattern, Locale locale) {
		DateFormat dateFormat = new SimpleDateFormat(pattern, locale);
		return dateFormat.format(date);
	}

	public static String getCurrentDateTime(String sFormat) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(sFormat, Locale.US);
		return simpleDateFormat.format(new Date());
	}
	
	public static String getCurrentTimeMill() {
		return String.valueOf(new Date().getTime());
	}

	public static boolean isNotEmpty(Map<?, ?> value) {
		return value != null && !value.isEmpty();
	}

	public static boolean isNotEmpty(String value) {
		return StringUtils.hasLength(trim(value)) && !value.toLowerCase().equals("null") && !value.toLowerCase().equals("undefined");
	}

	public static boolean isNotEmpty(Object[] value) {
		return value != null && value.length != 0;
	}

	public static boolean isEmpty(String value) {
		return StringUtils.isEmpty(trim(value));
	}

	public static String trim(String text) {
		return StringUtils.trimWhitespace(text);
	}

	public static boolean isNotEmpty(Collection<?> collection) {
		return collection != null && !collection.isEmpty();
	}

	public static boolean isEmpty(Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}

	public static boolean isNull(Object object) {
		return object == null;
	}

	public static boolean isNotNull(Object object) {
		return !isNull(object);
	}

	public static List<Integer> SearchArray(Object[] array, Object value, int size) {
		List<Integer> intList = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			if (array[i].equals(value)) {
				intList.add(i);
			}
		}
		return intList;
	}
	
	public static boolean isNotEmpty(final BigDecimal d) {
		final boolean b = isNotNull(d);
		return (b);
	}

	public static boolean isNotEmpty(final Byte byt) {
		final boolean b = isNotNull(byt);
		return (b);
	}

	public static boolean isNotEmpty(final Character c) {
		final boolean b = isNotNull(c);
		return (b);

	}

	public static boolean isNotEmpty(final Double d) {
		final boolean b = isNotNull(d);
		return (b);

	}

	public static boolean isNotEmpty(final Float f) {
		final boolean b = isNotNull(f);
		return (b);

	}

	public static boolean isNotEmpty(final Integer integer) {
		final boolean b = isNotNull(integer);
		return (b);

	}

	public static boolean isNotEmpty(final List<?> ls) {
		boolean b = false;
		if ((ls != null) && !ls.isEmpty()) {
			b = true;
		}
		return (b);

	}

	public static boolean isNotEmpty(final Long l) {
		final boolean b = isNotNull(l);
		return (b);

	}

	public static boolean isNotEmpty(final Number num) {
		final boolean b = isNotNull(num);
		return (b);

	}

	public static boolean isNotEmpty(final Object obj) {
		boolean b = false;
		if (obj != null) {
			b = true;
		}
		return (b);
	}

	public static boolean isNotEmpty(final Short s) {
		final boolean b = isNotNull(s);
		return (b);
	}
	
	public static boolean isNotEmpty(final String[] st) {
		boolean b = true;
		if ((st == null) || (st.length == 0)) {
			b = false;
		}
		return (b);
	}
}
