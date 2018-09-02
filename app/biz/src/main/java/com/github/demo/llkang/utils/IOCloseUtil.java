package com.github.demo.llkang.utils;

import java.io.Closeable;
import java.io.IOException;

public class IOCloseUtil {

	public static void close(Closeable... closeable) {
		for (int i = 0; i < closeable.length; i++) {
			if (null != closeable[i]) {
				try {
					closeable[i].close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
