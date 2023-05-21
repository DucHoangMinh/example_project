import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class brick {
	//2 Mảng lưu hoành độ và tung độ của các viên gạch mềm (có thể bắn vỡ)
	//Kích thước các viên gạch (và cả xe tăng đều là 50*50px)
	//Trục tọa độ là đỉnh trên bên trái của Frame
	//Ví dụ : viên gạch đầu tiên có tọa độ x = 50,y = 50 sẽ cách cạnh trên cùng 50px,và cách cạnh bên trái 50px
	int bricksXPos[] = {50,350,450,550,50,300,350,450,550,150,150,450,550,
						250,50,100,150,550,250,350,450,550,50,250,350,550,
						50,150,250,300,350,550,50,150,250,350,450,550,50,
						250,350,550};
	
	int bricksYPos[] = {50,50,50,50,100,100,100,100,100,150,200,200,200,250,
						300,300,300,300,350,350,350,350,400,400,400,400,450,
						450,450,450,450,450,500,500,500,500,500,500,550,550,
						550,550};
	
	//Mảng lưu vị trí (tọa độ + tung độ) của các viên gạch cứng
	int solidBricksXPos[] = {500,350,150,500,450,300,600,400,350,200,0,200,500};
	
	int solidBricksYPos[] = {0,0,50,100,150,200,200,250,300,350,400,400,450};
	
	//Mảng lưu trạng thái của các viên gạch,ở đây đang là 42 viên gạch
	//Khi bắt đầu trò chơi,tất cả viên gạch đều được hiện lên thì trạng thái của nó là 1
	int brickON[] = new int[42];
	

	//Hai biến kiểu ImagaIcon(anh em tự tìm hiểu thư viện này) lưu hình ảnh gạch mềm và cứng
	private ImageIcon breakBrickImage;
	private ImageIcon solidBrickImage;
	
	//Khai báo địa chỉ hình ảnh của hai loại gạch,và set cho tất cả viên gạch hiện lên
	public brick()
	{
		breakBrickImage=new ImageIcon("break_brick.jpg");
		solidBrickImage=new ImageIcon("solid_brick.jpg");	
		//Vòng lặp này set cho trạng thái tất cả các viên gạch là 1,tức là cho trạng thái của chúng đang là hiện lên
		for(int i=0; i < brickON.length;i++)
		{
			brickON[i] = 1;
		}
	}
	
	//Phương thức draw lặp qua mảng brickON 1 lần để xem viên gạch nào có trạng thái là 1,thì nó sẽ được chính thức
	//vẽ lên màn hình chính
	public void draw(Component c, Graphics g)
	{
		for(int i=0; i< brickON.length;i++)
		{
			if(brickON[i]==1)
			{
				breakBrickImage.paintIcon(c, g, bricksXPos[i],bricksYPos[i]);
			}
		}
	}
	//Cũng như cái trên nma để vẽ gạch cứng
	public void drawSolids(Component c, Graphics g)
	{
		for(int i=0; i< solidBricksXPos.length;i++)
		{			
			solidBrickImage.paintIcon(c, g, solidBricksXPos[i],solidBricksYPos[i]);
		}
	}
	
	// 2 Phương thức dưỡi đây dùng để kiểm tra xem có sự va chạm giữa đạn và gạch không
	//Hai hàm dưới đây có đối số truyền vào là x,y để sau này truyền vào tọa độ và tung độ của viên đạn
	public boolean checkCollision(int x, int y)
	{
		boolean collided = false;//Biến này lưu trạng thái có va chạm không,nếu có set là true và trả về
									//Không thì để là false
		for(int i=0; i< brickON.length;i++)
		{
			if(brickON[i]==1)
			{
				//Anh em tự search và tìm hiểu lớp Rectangle.Đại khái,lớp này sẽ tạo ra một hình chữ nhật tại tọa độ x,y và kích thước width,height
				//Lớp này cũng hộ trợ một phương thức cực kỳ tiện là intersects để kiểm tra hai Rectangle có chạm vào nhau không,có thì trả về true
				if(new Rectangle(x, y, 10, 10).intersects(new Rectangle(bricksXPos[i], bricksYPos[i], 50, 50)))
				{	
					//Nếu có va chạm,set cho trạng thái của viên gạch bị va chạm trạng thái là 0,tức là nó sẽ không xuất hiện trên bản đồ nữa
					brickON[i] = 0;
					collided = true;
					break;
				}
			}
		}
		
		return collided;
	}
	
	//Tương tự như cái trên,cái này cũng kiểm tra va chạm của đạn với gạch cứng.Tuy nhiên,nếu có va chạm,gạch
	//cứng sẽ không biến mất như gạch mềm
	public boolean checkSolidCollision(int x, int y)
	{
		boolean collided = false;
		for(int i=0; i< solidBricksXPos.length;i++)
		{		
			if(new Rectangle(x, y, 10, 10).intersects(new Rectangle(solidBricksXPos[i], solidBricksYPos[i], 50, 50)))
			{		
				collided = true;
				break;
			}			
		}
		
		return collided;
	}
}
