package com.wdk.util.jvm;

import java.util.Arrays;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementScanner7;
import javax.tools.Diagnostic.Kind;

public class NameChecker {
	
	private final Messager messager;

	private NameCheckScanner nameCheckScanner = new NameCheckScanner();
	
	public NameChecker(ProcessingEnvironment processingEnv) {
		this.messager = processingEnv.getMessager();
	}
	
	/**
	 * @description
	 * 	 ��java������ʽ���м��,	
	 * @param element
	 * @author wangdk
	 * @return void
	 * @since  1.0.0
	*/
	public void checkNames(Element element){
		nameCheckScanner.scan(element);
	}
	
	/**
	 *	@Description
	 *	���Ƽ����ʵ���� �̳���JDK1.7�ṩ��ElementScanner7
	 *	������visitorģʽ���ʳ����﷨���е�Ԫ��
	 *  @author wangdk,wangdk@erongdu.com
	 *  @CreatTime 2018��5��17�� ����9:09:07
	 *  @since version 1.0.0
	 */
	private class NameCheckScanner extends ElementScanner7<Void, Void>{
		
		/**
		 * �˷�������java��ļ��
		 * */
		@Override
		public Void visitType(TypeElement e,Void p){
			scan(e.getTypeParameters(),p);
			checkCamelCase(e,true);
			super.visitType(e, p);
			return null;
		}
		
		/**
		 * ��鷽�����Ƿ�Ϸ�
		 * */
		@Override
		public Void visitExecutable(ExecutableElement e,Void p){
			if(e.getKind() == ElementKind.METHOD){
				Name name = e.getSimpleName();
				if(name.contentEquals(e.getEnclosingElement().getSimpleName())){
					messager.printMessage(Kind.WARNING, "һ����ͨ����"+name+"��Ӧ���������ظ�,�����빹�캯����������.",e);
					checkCamelCase(e, false);
				}
				super.visitExecutable(e, p);
			}
			return null;
		}
		
		@Override
		public Void visitVariable(VariableElement e,Void p){
			if(e.getKind() == ElementKind.ENUM_CONSTANT || e.getConstantValue() != null || heuristicallyConstant(e)){
				checkAllCaps(e);
			}else{
				checkCamelCase(e, false);
			}
			return null;
		}
		
		private boolean heuristicallyConstant(VariableElement e){
			if(e.getEnclosingElement().getKind() == ElementKind.INTERFACE){
				return true;
			} else if (e.getKind() == ElementKind.FIELD && e.getModifiers().containsAll(Arrays.asList("PUBLIC","STATIC","FINAL"))){
				return true;
			}else{
				return false;
			}
		}
		
		
		/**
		 * @description
		 * ��鴫���Element �Ƿ�����շ�ʽ������,�粻����,�����������Ϣ
		 * @param e
		 * @param initialCaps
		 * @author wangdk
		 * @return void
		 * @since  1.0.0
		*/
		private void checkCamelCase(Element e,boolean initialCaps){
			String name = e.getSimpleName().toString();
			boolean previousUpper = false;
			boolean conventional = true;
			int firstCodePoint = name.codePointAt(0);
			if(Character.isUpperCase(firstCodePoint)){
				previousUpper = true;
				if(!initialCaps){
					messager.printMessage(Kind.WARNING, "����"+name+"Ӧ����Сд��ĸ��ͷ",e);
					return;
				}
			}else if(Character.isLowerCase(firstCodePoint)){
				if(initialCaps){
					messager.printMessage(Kind.WARNING, "����"+name+"Ӧ���Դ�д��ĸ��ͷ",e);
					return;
				}
			}else{
				conventional = false;
			}
			
			if(conventional){
				int cp = firstCodePoint;
				for(int i = Character.charCount(cp);i<name.length();i+=Character.charCount(cp)){
					cp = name.codePointAt(i);
					if(Character.isUpperCase(cp)){
						if(previousUpper){
							conventional = false;
							break;
						}
						previousUpper = true;
					}else{
						previousUpper = false;
					}
				}
			}
			
			if(!conventional){
				messager.printMessage(Kind.WARNING, "����"+name+"Ӧ�������շ�ʽ������",e);
			}
		}
		
		/**
		 * @description
		 * ��д�������,Ҫ���һ����ĸ�����Ǵ�дӢ����ĸ,������ĸ�������»��߻��ߴ�дӢ����ĸ
		 * @param e
		 * @author wangdk
		 * @return void
		 * @since  1.0.0
		*/
		private void checkAllCaps(Element e){
			String name = e.getSimpleName().toString();
			boolean conventional = true;
			int firstCodePoint = name.codePointAt(0);
			if(!Character.isUpperCase(firstCodePoint)){
				conventional = false;
			}else{
				boolean previousUnderscore = false;
				int cp = firstCodePoint;
				for(int i=Character.charCount(cp);i<name.length();i+=Character.charCount(cp)){
					cp = name.codePointAt(i);
					if(cp == (int)'_'){
						if(previousUnderscore){
							conventional = false;
							break;
						}
						previousUnderscore = true;
					}else{
						previousUnderscore = false;
						if(!Character.isUpperCase(cp) && !Character.isDigit(cp)){
							conventional = false;
							break;
						}
					}
				}
			}
			if(!conventional){
				messager.printMessage(Kind.WARNING, "����"+name+"Ӧ��ȫ���Դ�д��ĸ�����»�������,��������ĸ��ͷ",e);
			}
		}
	}

}
