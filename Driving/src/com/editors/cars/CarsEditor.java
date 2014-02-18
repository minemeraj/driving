package com.editors.cars;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.RepaintManager;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import com.editors.CustomEditor;
import com.editors.DoubleTextField;
import com.editors.Editor;
import com.editors.cars.data.Car;
import com.editors.object.ObjectEditorPreviewPanel;

public class CarsEditor extends CustomEditor implements MenuListener, ActionListener, KeyListener, MouseWheelListener{
	
	public static int HEIGHT=700;
	public static int WIDTH=800;
	public int RIGHT_BORDER=330;
	public int BOTTOM_BORDER=100;

	CarsEditorJPanel center=null;
	private JMenuBar jmb;
	private JMenu jm_file;
	private JMenuItem jmt_load_file;
	private JMenuItem jmt_save_file;
	
	public boolean redrawAfterMenu=false;

	private JMenu jm_view;
	private JMenuItem jmt_preview;
	private JMenu jm_change;
	private JMenuItem jmt_undo_last;
	private JMenuItem jmt_save_mesh;

	private DoubleTextField x_side;
	private DoubleTextField y_side;
	private DoubleTextField z_side;
	private DoubleTextField back_width;
	private DoubleTextField back_length;
	private DoubleTextField back_height;
	private DoubleTextField front_width;
	private DoubleTextField front_length;	
	private DoubleTextField front_height;
	private DoubleTextField roof_height;
	private JButton generate;
	
	JFileChooser fc = new JFileChooser();
	File currentDirectory=new File("lib");
	
	public Stack oldCar=null;
	int max_stack_size=10;
	
	Car car=null;
	
	

	

	
	
	public CarsEditor(){
		
		setTitle("Cars editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setSize(WIDTH+RIGHT_BORDER,HEIGHT+BOTTOM_BORDER);
		
		center=new CarsEditorJPanel();
		center.setBounds(0,0,WIDTH,HEIGHT);
		add(center);
		
	
		buildMenuBar();
		
		buildRightPanel();
		
		addKeyListener(this);
		addMouseWheelListener(this);
		
		RepaintManager.setCurrentManager( 
				new RepaintManager(){

					public void paintDirtyRegions() {


						super.paintDirtyRegions();
						if(redrawAfterMenu ) {
							center.draw(car);
							redrawAfterMenu=false;						    
						}
					}

				}				
		);
		
		setVisible(true);
		
		initialize();
	}

	
	public void initialize() {
		
		center.initialize();
	}
	

	
	public void paint(Graphics arg0) {
		super.paint(arg0);
		draw();
		
	}

	private void draw() {
		
		center.draw(car);
		
	}
	
	public void buildRightPanel() {
		
		
		JPanel right=new JPanel(null);
		
		right.setBounds(WIDTH,0,RIGHT_BORDER,HEIGHT);
		
		int r=10;
		
		int column=100;
		

		JLabel jlb=new JLabel("Body DX");
		jlb.setBounds(5, r, 100, 20);
		right.add(jlb);
		x_side=new DoubleTextField();
		x_side.setBounds(column, r, 100, 20);
		x_side.addKeyListener(this);
		right.add(x_side);

		r+=30;
		
		jlb=new JLabel("Body DY");
		jlb.setBounds(5, r, 100, 20);
		right.add(jlb);
		y_side=new DoubleTextField();
		y_side.setBounds(column, r, 100, 20);
		y_side.addKeyListener(this);
		right.add(y_side);
		
		r+=30;
		
		jlb=new JLabel("Body DZ");
		jlb.setBounds(5, r, 100, 20);
		right.add(jlb);
		z_side=new DoubleTextField();
		z_side.setBounds(column, r, 100, 20);
		z_side.addKeyListener(this);
		right.add(z_side);
		
		r+=30;
		
		jlb=new JLabel("Back width");
		jlb.setBounds(5, r, 100, 20);
		right.add(jlb);
		back_width=new DoubleTextField();
		back_width.setBounds(column, r, 100, 20);
		back_width.addKeyListener(this);
		right.add(back_width);
		
		r+=30;
		
		jlb=new JLabel("Back length");
		jlb.setBounds(5, r, 100, 20);
		right.add(jlb);
		back_length=new DoubleTextField();
		back_length.setBounds(column, r, 100, 20);
		back_length.addKeyListener(this);
		right.add(back_length);
		
		r+=30;
		
		jlb=new JLabel("Back height");
		jlb.setBounds(5, r, 100, 20);
		right.add(jlb);
		back_height=new DoubleTextField();
		back_height.setBounds(column, r, 100, 20);
		back_height.addKeyListener(this);
		right.add(back_height);
		
		r+=30;
		
		jlb=new JLabel("Front width");
		jlb.setBounds(5, r, 100, 20);
		right.add(jlb);
		front_width=new DoubleTextField();
		front_width.setBounds(column, r, 100, 20);
		front_width.addKeyListener(this);
		right.add(front_width);
		
		r+=30;
		
		jlb=new JLabel("Front length");
		jlb.setBounds(5, r, 100, 20);
		right.add(jlb);
		front_length=new DoubleTextField();
		front_length.setBounds(column, r, 100, 20);
		front_length.addKeyListener(this);
		right.add(front_length);
		
		r+=30;
		
		jlb=new JLabel("Front height");
		jlb.setBounds(5, r, 100, 20);
		right.add(jlb);
		front_height=new DoubleTextField();
		front_height.setBounds(column, r, 100, 20);
		front_height.addKeyListener(this);
		right.add(front_height);
		
		r+=30;
		
		jlb=new JLabel("Roof height");
		jlb.setBounds(5, r, 100, 20);
		right.add(jlb);
		roof_height=new DoubleTextField();
		roof_height.setBounds(column, r, 100, 20);
		roof_height.addKeyListener(this);
		right.add(roof_height);
		
		r+=30;
			
        generate=new JButton("Update");
        generate.setBounds(10,r,100,20);
        generate.addActionListener(this);
        generate.addKeyListener(this);
        right.add(generate);
		
		add(right);
		
		initRightData();
		
	}
	

	public void initRightData() {
		
	
		x_side.setText(100);
		y_side.setText(200);
		z_side.setText(100);
		back_width.setText(80);
		back_length.setText(30);
		back_height.setText(80);
		front_width.setText(80);
		front_length.setText(50);
		front_height.setText(80);
		roof_height.setText(60);
	}
	
	private void setRightData(Car car) {
	
		x_side.setText(car.getX_side());
		y_side.setText(car.getY_side());
		z_side.setText(car.getZ_side());
		back_width.setText(car.getBack_width());
		back_length.setText(car.getBack_length());
		back_height.setText(car.getBack_height());
		front_width.setText(car.getFront_width());
		front_length.setText(car.getFront_length());
		front_height.setText(car.getFront_height());
		roof_height.setText(car.getRoof_height());
	}

	
	public void buildMenuBar() {
		
		jmb=new JMenuBar();
		
		jm_file=new JMenu("File");
		jm_file.addMenuListener(this);
		jmb.add(jm_file);
		
		jmt_load_file = new JMenuItem("Load file");
		jmt_load_file.addActionListener(this);
		jm_file.add(jmt_load_file);
		
		jmt_save_file = new JMenuItem("Save file");
		jmt_save_file.addActionListener(this);
		jm_file.add(jmt_save_file);
		
		jm_file.addSeparator();
		

		
		jmt_save_mesh = new JMenuItem("Save mesh");
		jmt_save_mesh.addActionListener(this);
		jm_file.add(jmt_save_mesh);
		
		jm_change=new JMenu("Change");
		jm_change.addMenuListener(this);
		jmb.add(jm_change);
		
		jmt_undo_last = new JMenuItem("Undo last");
		jmt_undo_last.setEnabled(false);
		jmt_undo_last.addActionListener(this);
		jm_change.add(jmt_undo_last);
		
		
		jm_view=new JMenu("View");
		jm_view.addMenuListener(this);
		jmb.add(jm_view);
		
		jmt_preview = new JMenuItem("Preview");
		jmt_preview.addActionListener(this);
		jm_view.add(jmt_preview);

		
		setJMenuBar(jmb);
		
	}
	
	public static void main(String[] args) {
		
		CarsEditor be=new CarsEditor();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Object obj = arg0.getSource();
		
		if(obj==jmt_load_file){
			
			loadData();
			
			
			
		}else if(obj==jmt_save_file){
			
			saveData();
			
		}else if(obj==jmt_save_mesh){
			
			saveMesh(); 
			
		}
		else if(obj==jmt_preview){
			
			preview();
			
		}else if (obj==jmt_undo_last){
			
			undo();
			
		}else if(obj==generate){
			
			generate();
		}
		
	}
	
	public void preview() {
		
		if(car==null)
			return;
		
		Editor editor=new Editor();
		editor.meshes[0]=car.buildMesh();
		
		ObjectEditorPreviewPanel oepp=new ObjectEditorPreviewPanel(editor);
		
	}
	
	public void generate() {
			
			double xside=x_side.getvalue();
			double yside=y_side.getvalue();
			double zside=z_side.getvalue();
			double backWidth=back_width.getvalue();
			double backLength=back_length.getvalue();
			double backHeight=back_height.getvalue();
			double frontWidth=front_width.getvalue();
			double frontLength=front_length.getvalue();
			double frontHeight=front_height.getvalue();
			double roofHeight=roof_height.getvalue();
			
			if(car==null){
				
				car=new Car(xside,yside,zside,frontWidth,frontLength,frontHeight,backWidth,backLength,backHeight,roofHeight);
				
			}else{
				
				Car expCar = new Car(xside,yside,zside,frontWidth,frontLength,frontHeight,backWidth,backLength,backHeight,roofHeight);
				
				car=expCar;
			}
			
			draw();
			setRightData(car);
	}
	
	public void loadData() {

		fc.setDialogType(JFileChooser.SAVE_DIALOG);
		fc.setDialogTitle("Load Track");
		if(currentDirectory!=null)
			fc.setCurrentDirectory(currentDirectory);
		
		int returnVal = fc.showOpenDialog(this);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			loadData(file);
			draw();	
			currentDirectory=new File(file.getParent());
		} 
		
	}

	public void loadData(File file) {
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));

			
			String str=null;
			
			while((str=br.readLine())!=null){
				
				int indx=str.indexOf("C=");
				if(indx>=0){
					
					String value=str.substring(indx+2);
					car=Car.buildCar(value);
					setRightData(car);
					
				}
	
			}
			br.close();
			

			
		} catch (Exception e) { 
		
			e.printStackTrace();
		}
		
	}


	private void saveMesh() {
		
		fc.setDialogType(JFileChooser.SAVE_DIALOG);
		fc.setDialogTitle("Save mesh");
		if(currentDirectory!=null)
			fc.setCurrentDirectory(currentDirectory);
		int returnVal = fc.showOpenDialog(this);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			saveMesh(file);
			currentDirectory=new File(file.getParent());
		} 
		
	}


	public void saveMesh(File file) {
		
		
		if(car==null)
			return;
		
	
		PrintWriter pw;
		try {
			Editor editor=new Editor();
			editor.meshes[0]=car.buildMesh();
			pw = new PrintWriter(new FileOutputStream(file));
			editor.forceReading=true;
			editor.saveLines(pw);
			pw.close();
			
		} catch (Exception e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		
	}

	public void saveData() {
		fc.setDialogType(JFileChooser.SAVE_DIALOG);
		fc.setDialogTitle("Save data");
		if(currentDirectory!=null)
			fc.setCurrentDirectory(currentDirectory);
		int returnVal = fc.showOpenDialog(this);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			saveData(file);
			currentDirectory=new File(file.getParent());
		} 
		
	}

	public void saveData(File file) {
		
		
		if(car==null)
			return;
		
		PrintWriter pw;
		try {
			pw = new PrintWriter(file);			

			pw.println(car.toString());
			
			pw.close();
						
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void undo() {

		/*if(oldplan.size()>0)
			plan=(Buildingplan) oldplan.pop();
		
		if(oldplan.size()==0)
			jmt_undo_last.setEnabled(false);*/
		
		draw();
	}
	
	public void prepareUndo() {
		
		jmt_undo_last.setEnabled(true);
		
		if(oldCar.size()>=max_stack_size)
			oldCar.removeElementAt(0);
		
		oldCar.push(car.clone());
		
		
	}

	@Override
	public void menuCanceled(MenuEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuDeselected(MenuEvent arg0) {
		redrawAfterMenu=true;
		
	}

	@Override
	public void menuSelected(MenuEvent arg0) {
		redrawAfterMenu=false;
		
	}


	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		
		if(arg0.getUnitsToScroll()<0)
			center.translate(0,-1);
		else
			center.translate(0,1);
		
		draw();
		
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		
		int code=arg0.getKeyCode();
		
		
		if(code==KeyEvent.VK_LEFT)
			center.translate(+1,0);
		else if(code==KeyEvent.VK_RIGHT)
			center.translate(-1,0);
		else if(code==KeyEvent.VK_UP)
			center.translate(0,-1);
		else if(code==KeyEvent.VK_DOWN)
			center.translate(0,+1);
		else if(code==KeyEvent.VK_F1)
			center.zoom(+1);		
		else if(code==KeyEvent.VK_F2)
			center.zoom(-1);
		
		draw();
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
