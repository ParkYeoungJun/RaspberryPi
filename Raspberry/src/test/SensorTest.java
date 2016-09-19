package test;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.trigger.GpioCallbackTrigger;

import java.io.IOException;
import java.util.concurrent.Callable;
 
/**
 * Use the pi4j classes to watch a gpio trigger. This uses the pin number scheme as outlined in:
 * http://pi4j.com/pins/model-2b-rev1.html
 */

public class SensorTest {
    public static void main(String[] args) throws InterruptedException {
 
        System.out.printf("PIR Module Test (CTRL+C to exit)\n");
 
		try {
			Process p = Runtime.getRuntime().exec("xset dpms force off");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();
        
        // provision gpio pin #29, (header pin 40) as an input pin with its internal pull down resistor enabled
        final GpioPinDigitalInput pir = gpio.provisionDigitalInputPin(RaspiPin.GPIO_29);
        System.out.printf("Ready\n");
 
        // create a gpio callback trigger on the gpio pin
        Callable<Void> callback = () -> {
        	
			Process d = Runtime.getRuntime().exec("xset dpms force on");
			
            System.out.println(" --> GPIO TRIGGER CALLBACK RECEIVED ");
            return null;
        };
        
        // create a gpio callback trigger on the PIR device pin for when it's state goes high
        pir.addTrigger(new GpioCallbackTrigger(PinState.HIGH, callback));
 
        // stop all GPIO activity/threads by shutting down the GPIO controller
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("Interrupted, stopping...\n");
                gpio.shutdown();
            }
        });
 
        // keep program running until user aborts (CTRL-C)
        for (;;) {
            Thread.sleep(100);
        }
 
    }
}