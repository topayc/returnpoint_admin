package com.returnp.admin.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class ReflectionManager {
	public static final ArrayList<String[]> beanPeorperties;

	static {
		beanPeorperties = new ArrayList<String[]>();

	}

	/**
	 * 타켓 객체의 모든 필드를 복사.
	 * 
	 * @param sourceObj
	 *            getter 을 호출할 원본 오브젝트
	 * @param targetObj
	 *            setter 를 호출하여 복사하고자 하는 타켓 오브젝트 ,
	 */
	public static void reflectionCopy(Object sourceObj, Object targetObj) {
		Class<?> targetClass = targetObj.getClass();
		Class<?> sourceClass = sourceObj.getClass();

		Method[] targetMethods = targetClass.getDeclaredMethods();

		Class<?>[] targetParameters = null;
		String mName = null;

		for (int i = 0; i < targetMethods.length; i++) {
			mName = targetMethods[i].getName();
			targetParameters = targetMethods[i].getParameterTypes();

			if (mName.indexOf("set") == 0 && mName.length() > 3 && targetParameters.length == 1) {
				String propertyName = mName.substring(3);
				String sourceMethodName = "get" + propertyName;
				try {
					/*
					 * sourceMethod =
					 * sourceClass.getDeclaredMethod(sourceMethodName,targetParameters[0]);
					 */
					Object value = null;/* sourceMethod.invoke(sourceObj); */
					for (Method m : sourceClass.getDeclaredMethods()) {
						if (m.getName().equals(sourceMethodName)) {
							value = m.invoke(sourceObj);
							Object[] callParameter = null;

							/*
							 * 아래의 기본 타입의 프로퍼티 복사를 허용한다. 다른 타입의 복사를 원하면 관련 조건문을 추가
							 */
							if (targetParameters[0] == String.class) {
								callParameter = new Object[] { (String) value };
							} else if (targetParameters[0] == int.class) {
								callParameter = new Object[] { (Integer) value };
							} else if (targetParameters[0] == long.class) {
								callParameter = new Object[] { (Long) value };
							} else if (targetParameters[0] == double.class) {
								callParameter = new Object[] { (Double) value };
							} else if (targetParameters[0] == float.class) {
								callParameter = new Object[] { (Float) value };
							} else if (targetParameters[0] == Boolean.class) {
								callParameter = new Object[] { (Boolean) value };
							} else if (targetParameters[0] == Integer.class) {
								callParameter = new Object[] { (Integer) value };
							} else if (targetParameters[0] == Float.class) {
								callParameter = new Object[] { (Float) value };
							}
							if (callParameter != null) {
								targetMethods[i].invoke(targetObj, callParameter);
								break;
							}
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}


}
