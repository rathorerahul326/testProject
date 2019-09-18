package p;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TestMain {

	public static void main(String[] args) throws Exception {

		// Sample sample=new Sample();//object creation at run time but binding is
		// Static
		
		  Class c=Class.forName("p.Sample");
		 
		  Constructor cons=c.getDeclaredConstructor(int.class);
		  Object o=cons.newInstance(20);
		  
		  Class c1=Class.forName("p.TestHasA");
		  Object o1=c1.newInstance();//Dynamic binding
		  
		  
		  
		  
		  Method m=c1.getDeclaredMethod("setSample","Sample.class".getClass());
		  m.invoke(o1,o);
		  
		  c1.getDeclaredMethod("check",null).invoke(o1, null);
		  
		 

		/*Sample sample = new Sample(10);

		TestHasA a = new TestHasA();
		a.setSample(sample);

		a.check();*/

	}

}
