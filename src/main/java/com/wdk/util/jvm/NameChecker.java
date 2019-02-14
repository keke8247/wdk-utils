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
	 * 	 对java命名格式进行检查,	
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
	 *	名称检查器实现类 继承了JDK1.7提供的ElementScanner7
	 *	将会以visitor模式访问抽象语法树中的元素
	 *  @author wangdk,wangdk@erongdu.com
	 *  @CreatTime 2018年5月17日 上午9:09:07
	 *  @since version 1.0.0
	 */
	private class NameCheckScanner extends ElementScanner7<Void, Void>{
		
		/**
		 * 此方法用于java类的检查
		 * */
		@Override
		public Void visitType(TypeElement e,Void p){
			scan(e.getTypeParameters(),p);
			checkCamelCase(e,true);
			super.visitType(e, p);
			return null;
		}
		
		/**
		 * 检查方法名是否合法
		 * */
		@Override
		public Void visitExecutable(ExecutableElement e,Void p){
			if(e.getKind() == ElementKind.METHOD){
				Name name = e.getSimpleName();
				if(name.contentEquals(e.getEnclosingElement().getSimpleName())){
					messager.printMessage(Kind.WARNING, "一个普通方法"+name+"不应该与类名重复,避免与构造函数产生混淆.",e);
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
		 * 检查传入的Element 是否符合驼峰式命名法,如不符合,则输出警告信息
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
					messager.printMessage(Kind.WARNING, "名称"+name+"应当以小写字母开头",e);
					return;
				}
			}else if(Character.isLowerCase(firstCodePoint)){
				if(initialCaps){
					messager.printMessage(Kind.WARNING, "名称"+name+"应当以大写字母开头",e);
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
				messager.printMessage(Kind.WARNING, "名称"+name+"应当符合驼峰式命名法",e);
			}
		}
		
		/**
		 * @description
		 * 大写命名检查,要求第一个字母必须是大写英文字母,其他字母可以是下划线或者大写英文字母
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
				messager.printMessage(Kind.WARNING, "常量"+name+"应当全部以大写字母或者下划线命名,并且以字母开头",e);
			}
		}
	}

}
