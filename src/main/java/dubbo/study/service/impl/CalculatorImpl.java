package dubbo.study.service.impl;

import dubbo.study.service.Calculator;

/**
 * Created by guo on 2018/6/27.
 */
public class CalculatorImpl implements Calculator {

	@Override
	public int add(int a, int b) {
		return a + b;
	}
}
