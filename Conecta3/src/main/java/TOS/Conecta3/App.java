package TOS.Conecta3;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
    	Enumeration e;
		try {
			e = NetworkInterface.getNetworkInterfaces();
	    	while(e.hasMoreElements())
	    	{
	    	    NetworkInterface n = (NetworkInterface) e.nextElement();
	    	    if(n.getName().equals("wlp2s0")) {
	    	    	List<InterfaceAddress> lista = n.getInterfaceAddresses();
	    	    	System.out.println(lista.get(1).getAddress().getHostAddress());
	    	    }
	    	}
		} catch (SocketException e1) {
			
			e1.printStackTrace();
		}

		
    }
}
