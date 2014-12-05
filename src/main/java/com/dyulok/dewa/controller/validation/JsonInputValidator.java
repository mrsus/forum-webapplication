package com.dyulok.dewa.controller.validation;


import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dyulok.dewa.controller.validation.UiErrorCode;

public class JsonInputValidator {


	@SuppressWarnings("rawtypes")
	private Map<Class, Class> superClassSubClassMap=new HashMap<Class,Class>();
	
	public JsonInputValidator() {
		super();
		
	}

	public ValidationErrorMessage validate(Class cl, Map map) {
		
		// Replacing the superClass (Declared Class) with the Implementing Class
		
		if(superClassSubClassMap.containsKey(cl)){
			cl=superClassSubClassMap.get(cl);
		}

		ValidationErrorMessage message = null;
		for (Method method : cl.getMethods()) {

			if (method.getName().startsWith("get")
					&& !method.getName().equals("getClass")) {

				System.out.println(" methods :- "
						+ method.getName().substring(3));

				Class fieldclass = method.getReturnType();
				System.out.println(" Field type " + fieldclass.getName());
				message = validateForType(method,map);
				if (message != null) {
					return message;
				}

			}
		}
		return message;

	}

	private ValidationErrorMessage validateForType(Method getMethod,Map map) {

		Class fieldClass = getMethod.getReturnType();
		
		String fieldName = getFieldNameFromGetter(getMethod.getName());
       
		if (!map.containsKey(fieldName)) {
			return null;
		}

	
		Object value = map.get(fieldName);

		switch (fieldClass.getName()) {

		case "java.lang.Boolean":

			if (value instanceof Boolean) {
				break;
			} else if (value instanceof String) {
				String strValue = (String) value;
				if (strValue.equalsIgnoreCase("true")
						|| strValue.equalsIgnoreCase("false")) {
					break;
				}
			}

			return new ValidationErrorMessage(UiErrorCode.validation,
					"Boolean field must contain either true or false value");

		case "java.lang.Integer":
			

			if (value instanceof Integer) {
				break;
			} else if (value instanceof Double) {
				break;
			} else if (value instanceof Long) {
				break;
			} else if (value instanceof String) {
				String strValue = (String) value;
				if (strValue.matches("(\\+|-)?([0-9]+)")) {
					break;
				}
			}

			return new ValidationErrorMessage(UiErrorCode.validation,
					"Integer Field must contain only digits from 0 to 9");

            case "java.lang.Long":
			

			 if (value instanceof Double) {
				break;
			} else if (value instanceof Long) {
				break;
			} else if (value instanceof String) {
				String strValue = (String) value;
				if (strValue.matches("\\d+")) {
					break;
				}
			}

			return new ValidationErrorMessage(UiErrorCode.validation,
					"Long Field must contain only digits from 0 to 9");
			
		case "java.util.Date":

			if (value instanceof Date) {
				break;

			} else if (value instanceof String) {
				try {
					DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
					String datestring = (String) value;
					Date date = (Date) formatter.parse(datestring);

				} catch (ParseException e) {
					e.printStackTrace();
					return new ValidationErrorMessage(UiErrorCode.validation,
							"Date should be in format dd-MM-yyyy (day-month-year)");
				}
				break;
			}
			return new ValidationErrorMessage(UiErrorCode.validation,
					"Date should be in format dd-MM-yyyy (day-month-year)");
		
			
		case "java.util.List":
			
			
			if(((ArrayList)value).size()==0){
				break;
			}
			
			Type t=getMethod.getGenericReturnType();
	        Type[] actuall= ((ParameterizedType)t).getActualTypeArguments();
	        Class classSub=(Class) actuall[0];
	        
     		List valueList = (List)value;
     		for(Object obj : valueList) {
     			Map valueMap = (Map)obj;
     			
	    		ValidationErrorMessage errormessage = validate(classSub, valueMap);
	    		if(errormessage!=null){
	    		   
	    			return errormessage;
	    		}
     		}
    		
        }

		return null;
	}

//	private Class getListClass(String fieldName) throws ClassNotFoundException {
//		// TODO Auto-generated method stub
//		String classnamewithoutlist=fieldName.replaceFirst("list", "");
//		String firstchar=classnamewithoutlist.substring(0, 1);
//		String className=classnamewithoutlist.replaceFirst(firstchar, firstchar.toUpperCase());
//		Class cl=Class.forName(className);
//		return cl;
//	}

	private static String getFieldNameFromGetter(String name) {
		
		String fieldMethodWithoutGet = name.replaceFirst("get", "");
		String firstCharacter = fieldMethodWithoutGet.substring(0, 1);
		String fieldName = fieldMethodWithoutGet.replaceFirst(firstCharacter,
				firstCharacter.toLowerCase());
	

		return fieldName;
	}
	
	public <T> void addImplementingClass(Class<T> cl, Class<? extends T> subcl){
		
		
		superClassSubClassMap.put(cl, subcl);
		
	}

}
