package dubbo.study.provider;

import dubbo.study.request.CalculateRpcRequest;
import dubbo.study.service.Calculator;
import dubbo.study.service.impl.CalculatorImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by guo on 2018/6/27.
 */
public class ProviderApp {

	private Calculator calculator = new CalculatorImpl();

	public static void main(String[] args) throws IOException {
		new ProviderApp().run();
	}

	private void run() throws IOException {
		ServerSocket listener = new ServerSocket(9090);
		try {
			while (true){
				Socket socket = listener.accept();
				try{

					//将请求反序列化
					ObjectInputStream outInputStream = new ObjectInputStream(socket.getInputStream());
					Object object = outInputStream.readObject();

					System.out.println("request is : " + object);

					int result = 0;
					if(object instanceof CalculateRpcRequest){
						CalculateRpcRequest calculateRpcRequest = (CalculateRpcRequest) object;
						if("add".equals(calculateRpcRequest.getMethod())){
							System.out.println(calculateRpcRequest.toString());
							result = calculator.add(calculateRpcRequest.getA(), calculateRpcRequest.getB());
							System.out.println(result);
						}else {
							throw new UnsupportedOperationException();
						}
					}

					//返回结果
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
					objectOutputStream.writeObject(result);
				}catch (Exception e){
					System.out.println("fail :  " + e.getMessage());
				}finally {
					socket.close();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			listener.close();
		}
	}
}
