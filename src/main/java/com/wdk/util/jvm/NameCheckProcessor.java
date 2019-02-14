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
@SupportedAnnotationTypes("*") //用*号表示 可以支持所有的annotations
@SupportedSourceVersion(SourceVersion.RELEASE_7) //只支持JDK1.7的java代码
public class NameCheckProcessor extends AbstractProcessor {

	private NameChecker nameChecker;
	
	/**
	 * 初始化名称检查插件
	 */
	@Override
	public void init(ProcessingEnvironment processingEnv){
		super.init(processingEnv);
		nameChecker = new NameChecker(processingEnv);
	}
	
	/**
	 * 对输入的语法树各个节点进行检查
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
