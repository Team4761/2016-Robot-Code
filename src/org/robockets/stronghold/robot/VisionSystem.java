import com.ni.vision.NIVision;
import edu.wpi.first.wpilibj.vision.USBCamera;
class VisionSystem
{
	CameraServer server;
	USBCamera[] cameras;
	USBCamera camera;
	int cameraNumber;
	NIVision.Image image;
	public VisionSystem(String...cameraNames)
	{
		cameras = new USBCamera[cameraNames.length];
		for (int z = 0; z < cameraNames.length; z++)
			cameras[z] = new USBCamera(cameraNames[z++]);
		camera = cameras[0];
		server = CameraServer.getInstance();
	}
	public void beginServer()
	{
		//server.serve();
		new Thread(new Handler(this)).start();
	}
	public void setCamera(int cameraNumber)
	{
		camera = cameras[cameraNumber];
	}
	public void updateImage()
	{
		camera.getImage(image);
		server.setImage(image);
	}
	class Handler implements Runnable
	{
		VisionSystem system;
		public Handler(VisionSystem system)
		{
			this.system = system;
		}
		public void run()
		{
			while (true)
			{
				system.updateImage();
				try {
					Thread.sleep(33);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
