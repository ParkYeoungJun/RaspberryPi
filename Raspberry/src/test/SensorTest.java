package test;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/*
 * PIR: PyroElectric Infra Red
 */
public class SensorTest
{
	final GpioController gpioSensor = GpioFactory.getInstance();
	final GpioPinDigitalInput sensor = gpioSensor.provisionDigitalInputPin(RaspiPin.GPIO_04, PinPullResistance.PULL_DOWN);
	
	public SensorTest()
	{
		sensor.addListener(new GpioPinListenerDigital(){

			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				// TODO Auto-generated method stub
				
				if(event.getState().isHigh())
				{
					System.out.println("Motion Detected!!!!");
				}
				else if(event.getState().isLow())
				{
					System.out.println("All is quiet....");
				}
			}
		});
		
		try
		{
			for(;;)
			{
				Thread.sleep(500);
			}
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
	}
	
	public static void main(String[] args)
	{
		SensorTest temp = new SensorTest();
	}
}