package testcases;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.ExtensionConnection;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.internal.SocketLock;
import org.openqa.selenium.logging.LocalLogs;
//import org.openqa.selenium.logging.LocalLogs;
import org.openqa.selenium.net.NetworkUtils;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.Response;

public class ReusableFirefoxDriver extends FirefoxDriver {
	
	private HttpCommandExecutor httpClient;
	
	public static void main(String...a) throws Exception {
		RemoteWebDriver driver = new ReusableFirefoxDriver();
		driver.get("http://dq13m.cn.ibm.com");
		System.out.println("Start second driver (Press enter)?");
		System.in.read();
		//The below code could be elsewhere in a different JVM
		driver = new ReusableFirefoxDriver();
		driver.get("http://www.bing.com");
	}  
	
	@Override
	protected void startSession( Capabilities desiredCapabilities ) {
		if( localServerURL!=null ) {
			httpClient = new HttpCommandExecutor( localServerURL );
		}
		super.startSession( desiredCapabilities );
	}

	@Override
	protected ExtensionConnection connectTo( FirefoxBinary binary, FirefoxProfile profile, String host ) {
		localServerURL = getURLofExistingLocalServer();
		if ( localServerURL!=null ) {
			return new ExtensionConnection() {
				@Override
				public Response execute( Command command ) throws IOException {
					return httpClient.execute(command);
				}
				
				@Override
				public void start() throws IOException {
					//NOOP
				}
				
				@Override
				public void quit() {
					//NOOP
				}
				
				@Override
				public boolean isConnected() {					
					try {
						httpClient.getAddressOfRemoteServer().openConnection().connect();
						return true;
					} catch (IOException e) {
						return false;
					}
				}

//				@Override
//				public void setLocalLogs(LocalLogs arg0) {
//					// TODO Auto-generated method stub
//					
//				}

//				@Override
//				public void setLocalLogs( LocalLogs logs ) {
//					//NOOP					
//				}
			};
		}
		return super.connectTo(binary, profile, host);
	}
	
	private URL localServerURL = null;
	
	private URL getURLofExistingLocalServer() {
		Socket socket = new Socket();
		try {
			socket.bind(new InetSocketAddress("localhost", SocketLock.DEFAULT_PORT));
			return null; //Able to connect on default port (Assuming that default FF driver is not running)
		} catch (IOException e) {
			// noop
		}finally{
			try {
				socket.close();
			} catch (IOException e) {
			}
		}
		try {
			return new URL("http",new NetworkUtils().obtainLoopbackIp4Address(), SocketLock.DEFAULT_PORT,"/hub");
		} catch (MalformedURLException e) {
			throw new WebDriverException(e);
		}
	}
	
}
