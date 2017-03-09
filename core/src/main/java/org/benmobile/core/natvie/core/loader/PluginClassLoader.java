/*
 * Syknet Project
 * Copyright (c) 2016 chunquedong
 * Licensed under the LGPL(http://www.gnu.org/licenses/lgpl.txt), Version 3
 */
package org.benmobile.core.natvie.core.loader;

import org.benmobile.core.natvie.core.bean.PluginInfo;

import dalvik.system.DexClassLoader;

public class PluginClassLoader extends DexClassLoader {
	/**
	 * 当宿主和插件包含相同类时：0表示宿主优先，1表示插件优先
	 */
	static int classLoaderType = 0;

	public static void setClassLoaderType(int type) {
		classLoaderType = type;
	}

	public PluginInfo plugin;
	ClassLoader appClassLoader;
	/**
	 * 
	 * 方法名：make<BR>  
	 * 此方法描述的是：   创建插件加载器实例
	 * @param dexPath
	 * @param optimizedDirectory
	 * @param libraryPath
	 * @param parent
	 * @return  PluginClassLoader
	 */
	public static PluginClassLoader make(String dexPath,
			String optimizedDirectory, String libraryPath, ClassLoader parent) {
		if (parent instanceof SyknetAppClassLoader) {
			parent = parent.getParent();
		}
		
		if (classLoaderType == 0) {
			return new PluginClassLoader(dexPath, optimizedDirectory,
					libraryPath, parent);
		} else {
			PluginClassLoader cl = new PluginClassLoader(dexPath,
					optimizedDirectory, libraryPath,
					ClassLoader.getSystemClassLoader());
			cl.appClassLoader = parent;
			return cl;
		}
	}

	private PluginClassLoader(String dexPath, String optimizedDirectory,
			String libraryPath, ClassLoader parent) {
		super(dexPath, optimizedDirectory, libraryPath, parent);
	}

	@Override
	protected Class<?> loadClass(String className, boolean resolve)
			throws ClassNotFoundException {
		if (classLoaderType == 0) {
			return super.loadClass(className, resolve);
		} else {
			try {
				return super.loadClass(className, resolve);
			} catch (ClassNotFoundException e) {
				try {
					return appClassLoader.loadClass(className);
				} catch (ClassNotFoundException e2) {
					throw e;
				}
			}
		}
	}

}
