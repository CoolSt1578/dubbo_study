package dubbo.study.consumer;

import dubbo.study.service.Calculator;
import dubbo.study.service.impl.CalculatorRemoteImpl;

/**
 * Created by guo on 2018/6/27.
 */
public class ConsumerApp {

	public static void main(String[] args) {
		Calculator calculator = new CalculatorRemoteImpl();
		int resut = calculator.add(1, 2);
		System.out.println(resut);
	}
}
