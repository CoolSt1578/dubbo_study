package dubbo.study.request;

import java.io.Serializable;

/**
 * Created by guo on 2018/6/27.
 */
public class CalculateRpcRequest implements Serializable {

	private int a;
	private int b;

	public CalculateRpcRequest() {
	}

	public CalculateRpcRequest(int aa, int bb){
		this.a = aa;
		this.b = bb;
	}

	public String getMethod() {
		return "add";
	}

	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}

	@Override
	public String toString() {
		return "CalculateRpcRequest{" +
				"a=" + a +
				", b=" + b +
				'}';
	}
}
