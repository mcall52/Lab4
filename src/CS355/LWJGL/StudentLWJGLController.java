package CS355.LWJGL;


//You might notice a lot of imports here.
//You are probably wondering why I didn't just import org.lwjgl.opengl.GL11.*
//Well, I did it as a hint to you.
//OpenGL has a lot of commands, and it can be kind of intimidating.
//This is a list of all the commands I used when I implemented my project.
//Therefore, if a command appears in this list, you probably need it.
//If it doesn't appear in this list, you probably don't.
//Of course, your milage may vary. Don't feel restricted by this list of imports.
import org.lwjgl.input.Keyboard;

import com.sun.corba.se.spi.logging.LogWrapperFactory;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3d;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import java.util.Iterator;

/**
 *
 * @author Brennan Smith
 */
public class StudentLWJGLController implements CS355LWJGLController 
{
  
  //This is a model of a house.
  //It has a single method that returns an iterator full of Line3Ds.
  //A "Line3D" is a wrapper class around two Point2Ds.
  //It should all be fairly intuitive if you look at those classes.
  //If not, I apologize.
  private WireFrame model = new HouseModel();
  private Point3D camerapos;
  private float camangle;

  //This method is called to "resize" the viewport to match the screen.
  //When you first start, have it be in perspective mode.
  @Override
  public void resizeGL() 
  {
	  glMatrixMode(GL_PROJECTION);
	  glLoadIdentity();
	  glViewport(0, 0, LWJGLSandbox.DISPLAY_WIDTH,LWJGLSandbox.DISPLAY_HEIGHT);
	  gluPerspective((float)65.0, (float) LWJGLSandbox.DISPLAY_WIDTH/LWJGLSandbox.DISPLAY_HEIGHT, 
			  (float)1, (float)1000);
	  
	  camerapos = new Point3D(0, -3, -20);
	  camangle = 0;
	  rebuildModel();
  }

    @Override
    public void update() 
    {
        //nothing for Lab 4
    }

    //This is called every frame, and should be responsible for keyboard updates.
    //An example keyboard event is captured below.
    //The "Keyboard" static class should contain everything you need to finish
    // this up.
    @Override
    public void updateKeyboard() 
    {
        if(Keyboard.isKeyDown(Keyboard.KEY_W)) 
        {
            System.out.println("You are pressing W!");
            //change camera position
            //rebuild model from scratch in relation to the camera position
            camerapos.x -= 1 * (float)Math.sin(Math.toRadians(camangle));
        	camerapos.z += 1 * (float)Math.cos(Math.toRadians(camangle));
//            camerapos.z += 1;
            rebuildModel();
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_A)) 
        {
            System.out.println("You are pressing A!");
//            glTranslatef(1, 0, 0);
            camerapos.x += 1 *  (float)Math.cos(Math.toRadians(camangle));
        	camerapos.z += 1 * (float)Math.sin(Math.toRadians(camangle));
//            camerapos.x += 1;

            rebuildModel();
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S)) 
        {
            System.out.println("You are pressing S!");
//            glTranslatef(0, 0, -1);
            camerapos.x += 1 *  (float)Math.sin(Math.toRadians(camangle));
        	camerapos.z -= 1 * (float)Math.cos(Math.toRadians(camangle));
//            camerapos.z += -1;
            rebuildModel();
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D)) 
        {
            System.out.println("You are pressing D!");
//            glTranslatef(-1, 0, 0);
            camerapos.x -= 1 *  (float)Math.cos(Math.toRadians(camangle));
        	camerapos.z -= 1 * (float)Math.sin(Math.toRadians(camangle));
//            camerapos.x += -1;
            rebuildModel();
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_Q)) 
        {
            System.out.println("You are pressing Q!");
//            glRotatef(5, 0, 1, 0);
            camangle += -1;
            rebuildModel();
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_E)) 
        {
            System.out.println("You are pressing E!");
//            glRotatef(-5, 0, (float) camerapos.y, 0);
            camangle += 1;
            rebuildModel();
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_R)) 
        {
            System.out.println("You are pressing R!");
//            glTranslatef(0, -1, 0);
            camerapos.y += -1;
            rebuildModel();
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_F)) 
        {
            System.out.println("You are pressing F!");
//            glTranslatef(0, 1, 0);
            camerapos.y += 1;
            rebuildModel();
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_H)) 
        {
            System.out.println("You are pressing H!");
            resizeGL();
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_O)) 
        {
            System.out.println("You are pressing O!");
//            glLoadIdentity();
//      	  	glViewport(0, 0, LWJGLSandbox.DISPLAY_WIDTH,LWJGLSandbox.DISPLAY_HEIGHT);
//            resizeGL();
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glOrtho(-32.5f, 32.5f, -32.5f, 32.5f, 1.0f, 1000.0f);
            glMatrixMode(GL_MODELVIEW);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_P)) 
        {
            System.out.println("You are pressing P!");
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            gluPerspective((float)65.0, (float) LWJGLSandbox.DISPLAY_WIDTH/LWJGLSandbox.DISPLAY_HEIGHT, 
      			  (float)1, (float)1000);
            glMatrixMode(GL_MODELVIEW);
        }
    }

    //This method is the one that actually draws to the screen.
    @Override
    public void render() 
    {
    	glClear(GL_COLOR_BUFFER_BIT);

        float houseSpace = 30;
        
        for(float i = 0.0f; i < 10f; i += 1.0f){
        	for(float j = 0.0f; j <= 1.0f; j += 1.0f){
        		glPushMatrix();
        		glMatrixMode(GL_MODELVIEW);
        		glTranslatef(j * houseSpace, 0, i * -houseSpace);
        		float rotation = (j == 1) ? 270.0f : 90.0f;
        		glRotatef(rotation, 0, 1, 0);
        		glColor3f(j, i/10, 1.0f);
        		drawHouse();
        		glPopMatrix();
        	}
        }
    }
    
    private void drawHouse(){
        
        //Do your drawing here.
        Iterator<Line3D> iter = model.getLines();
        while(iter.hasNext()){
        	Line3D line = iter.next();
        	drawline(line.start, line.end);
        }
    }
    
    private void drawline(Point3D start, Point3D end){
    	glBegin(GL_LINES); 
    	glVertex3d(start.x,start.y,start.z); 
    	glVertex3d(end.x,end.y,end.z); 
    	glEnd();
    }
    
    private void rebuildModel(){
    	//translate then rotate according to the camera position
    	glMatrixMode(GL_MODELVIEW);
    	glLoadIdentity();
    	glRotatef(camangle, 0, 1, 0);
    	glTranslatef((float) camerapos.x, (float) camerapos.y, (float) camerapos.z);
    	
    	//gl_matrixmode(modelview)
    	//gl_rotate(-angle)
    	//gl)translate(-pos)
    	//gl_translate(-cx, -cy, -cz)
    }
    
//    private float getAngleRadians(){
//    	return (float) (camangle*Math.PI/180);
//    }
}
