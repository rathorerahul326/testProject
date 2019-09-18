package p;

public class TestHasA {
	
	Sample sample;//Interface Injection
	
	public void setSample(Sample sample)
	{
		
		this.sample=sample;
		
	}

	public void check()
	{
		sample.m1();
		sample.m2();
	}
}
