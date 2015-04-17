package com.talklife.common.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlHelper {
	@SuppressWarnings("unchecked")
	public static Map<String, Map<String, String>> GetConData(String confile) {
		Map<String, Map<String, String>> conData = new HashMap<String, Map<String, String>>();

		SAXReader saxReader = new SAXReader();
		Document document;
		try {
			document = saxReader.read(confile);
			Element rootElement = document.getRootElement();// 获取根节点数据信息
			List<Attribute> rootAttribute = rootElement.attributes();
			Map<String, String> accountInfo = new HashMap<String, String>();
			for (int i = 0; i < rootAttribute.size(); ++i) {// 添加用户属性信息
				accountInfo.put(rootAttribute.get(i).getName(), rootAttribute
						.get(i).getValue());
			}
			conData.put("accountInfo", accountInfo);// 添加用户信息到结果参数

			// 读取子节点信息
			Iterator<Element> rootIter = rootElement.elementIterator();
			while (rootIter.hasNext()) {
				Element connection = rootIter.next();
				Iterator<Element> childIter = connection.elementIterator();
				Map<String, String> templateInfo = new HashMap<String, String>();
				List<Attribute> attributes = connection.attributes();
				for (int i = 0; i < attributes.size(); ++i) { // 添加节点属性
					templateInfo.put(attributes.get(i).getName(), attributes
							.get(i).getValue());
				}
				while (childIter.hasNext()) { // 添加子节点
					Element attr = childIter.next();
					templateInfo.put(attr.getName().trim(), attr.getText()
							.trim());
				}
				conData.put(templateInfo.get("type"), templateInfo);
			}

		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return conData;
	}
}
