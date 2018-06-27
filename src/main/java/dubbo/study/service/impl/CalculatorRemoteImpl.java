package dubbo.study.service.impl;

import dubbo.study.request.CalculateRpcRequest;
import dubbo.study.service.Calculator;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guo on 2018/6/27.
 */
public class CalculatorRemoteImpl implements Calculator {

	@Override
	public int add(int a, int b) {

		List<String> addressList = lookupProviders("Calculator.add");
		String address = chooseTarget(addressList);
		try{
			Socket socket = new Socket(address, 9090);

			//将请求序列化
			CalculateRpcRequest calculateRpcRequest = generateRequest(a, b);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

			//将请求发送给服务提供方
			objectOutputStream.writeObject(calculateRpcRequest);

			//将响应反序列化
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			Object response = objectInputStream.readObject();

			if(response instanceof Integer){
				return (Integer)response;
			}else{
				throw new InternalError();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 得到序列化的请求
	 * @param a
	 * @param b
	 * @return
	 */
	private CalculateRpcRequest generateRequest(int a, int b) {
		return new CalculateRpcRequest(a,b);
	}

	/**
	 * 负载均衡策略
	 * @param addressList
	 * @return
	 */
	private String chooseTarget(List<String> addressList) {
		return addressList.get(0);
	}

	/**
	 * 查询提供者地址
	 * @param s
	 * @return
	 */
	private List<String> lookupProviders(String s) {
		List<String> list = new ArrayList<String>();
		list.add("127.0.0.1");
		return list;
	}
}
