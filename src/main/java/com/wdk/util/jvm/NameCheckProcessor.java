package com.wdk.util.jvm;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/5/16 18:30
 * @Since version 1.0.0
 */
@SupportedAnnotationTypes("*") //��*�ű�ʾ ����֧�����е�annotations
@SupportedSourceVersion(SourceVersion.RELEASE_7) //ֻ֧��JDK1.7��java����
public class NameCheckProcessor extends AbstractProcessor {

	private NameChecker nameChecker;
	
	/**
	 * ��ʼ�����Ƽ����
	 */
	@Override
	public void init(ProcessingEnvironment processingEnv){
		super.init(processingEnv);
		nameChecker = new NameChecker(processingEnv);
	}
	
	/**
	 * ��������﷨�������ڵ���м��
	 */
	@Override
	public boolean process(Set<? extends TypeElement> annotations,	RoundEnvironment roundEnv) {
		if(!roundEnv.processingOver()){
			for(Element element : roundEnv.getRootElements()){
				nameChecker.checkNames(element);
			}
		}
		
		return false;
	}

}
