package com.editors.buildings;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.RepaintManager;

import com.PolygonMesh;
import com.editors.CustomEditor;
import com.editors.DoubleTextField;
import com.editors.ValuePair;
import com.editors.buildings.data.BuildingPlan;

public class BuildingsEditor extends CustomEditor implements  MouseListener, ItemListener{
	
	public static int HEIGHT=700;
	public static int WIDTH=800;
	public int RIGHT_BORDER=330;
	public int BOTTOM_BORDER=100;
	
	public BuildingPlan plan =null;	
	
	private DoubleTextField x_side;
	private DoubleTextField y_side;
	private DoubleTextField z_side;
	private JComboBox chooseRoof;
	private DoubleTextField roof_top_height;
	private DoubleTextField roof_top_width;
	private DoubleTextField roof_top_length;
	
	public Stack<BuildingPlan> oldPlan=null;
	int max_stack_size=10;
	private DoubleTextField roof_rim;
	
	public BuildingsEditor(){
		
		setTitle("Buildings editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setSize(WIDTH+RIGHT_BORDER,HEIGHT+BOTTOM_BORDER);
		
		center=new BuildingJPanel();
		center.setBounds(0,0,WIDTH,HEIGHT);
		add(center);
		center.addMouseListener(this);
		
		buildMenuBar();
		
		buildRightPanel();
		
		addKeyListener(this);
		addMouseWheelListener(this);
		
		
		
		RepaintManager.setCurrentManager( 
				new RepaintManager(){
					@Override
					public void paintDirtyRegions() {


						super.paintDirtyRegions();
						if(redrawAfterMenu ) {
							center.draw(plan);
							redrawAfterMenu=false;						    
						}
					}

				}				
		);
		
		setVisible(true);
		initialize();
	}
	@Override
	public void buildRightPanel() {
		
		
		JPanel right=new JPanel(null);
		
		right.setBounds(WIDTH,0,RIGHT_BORDER,HEIGHT);
		
		int r=10;
		
		int column=100;
		

		JLabel jlb=new JLabel("X side");
		jlb.setBounds(5, r, 100, 20);
		right.add(jlb);
		x_side=new DoubleTextField();
		x_side.setBounds(column, r, 100, 20);
		x_side.addKeyListener(this);
		right.add(x_side);

		r+=30;
		
		jlb=new JLabel("Y side");
		jlb.setBounds(5, r, 100, 20);
		right.add(jlb);
		y_side=new DoubleTextField();
		y_side.setBounds(column, r, 100, 20);
		y_side.addKeyListener(this);
		right.add(y_side);
		
		r+=30;
		
		jlb=new JLabel("Z side");
		jlb.setBounds(5, r, 100, 20);
		right.add(jlb);
		z_side=new DoubleTextField();
		z_side.setBounds(column, r, 100, 20);
		z_side.addKeyListener(this);
		right.add(z_side);
		
		r+=30;
		
		jlb=new JLabel("Roof type:");
		jlb.setBounds(5, r, 120, 20);
		right.add(jlb);

		chooseRoof=new JComboBox();
		chooseRoof.setBounds(column, r, 100, 20);
		chooseRoof.addKeyListener(this);
		chooseRoof.addItem(new ValuePair("-1",""));
		chooseRoof.addItem(new ValuePair(""+BuildingPlan.ROOF_TYPE_FLAT,"Flat"));		
		chooseRoof.addItem(new ValuePair(""+BuildingPlan.ROOF_TYPE_GABLE,"Gable"));
		chooseRoof.addItem(new ValuePair(""+BuildingPlan.ROOF_TYPE_GAMBREL,"Gambrel"));
		chooseRoof.addItem(new ValuePair(""+BuildingPlan.ROOF_TYPE_HIP,"Hip"));
		chooseRoof.addItem(new ValuePair(""+BuildingPlan.ROOF_TYPE_MANSARD,"Mansard"));
		chooseRoof.addItem(new ValuePair(""+BuildingPlan.ROOF_TYPE_SHED,"Shed"));
		
		chooseRoof.setSelectedIndex(0);
		right.add(chooseRoof);
		
		chooseRoof.addItemListener(this);
		
		r+=30;
		
		jlb=new JLabel("Roof top height");
		jlb.setBounds(5, r, 150, 20);
		right.add(jlb);
		roof_top_height=new DoubleTextField();
		roof_top_height.setBounds(column, r, 160, 20);
		roof_top_height.addKeyListener(this);
		right.add(roof_top_height);
		
		r+=30;
		
		jlb=new JLabel("Roof top width");
		jlb.setBounds(5, r, 150, 20);
		right.add(jlb);
		roof_top_width=new DoubleTextField();
		roof_top_width.setBounds(column, r, 160, 20);
		roof_top_width.addKeyListener(this);
		right.add(roof_top_width);
		
		r+=30;
		
		jlb=new JLabel("Roof top length");
		jlb.setBounds(5, r, 150, 20);
		right.add(jlb);
		roof_top_length=new DoubleTextField();
		roof_top_length.setBounds(column, r, 160, 20);
		roof_top_length.addKeyListener(this);
		right.add(roof_top_length);
		
		r+=30;
		
		jlb=new JLabel("Roof rim");
		jlb.setBounds(5, r, 150, 20);
		right.add(jlb);
		roof_rim=new DoubleTextField();
		roof_rim.setBounds(column, r, 160, 20);
		roof_rim.addKeyListener(this);
		right.add(roof_rim);
		
		r+=30;
		
		
		
        generate=new JButton("Update");
        generate.setBounds(10,r,100,20);
        generate.addActionListener(this);
        generate.addKeyListener(this);
        right.add(generate);
        
        r+=30;
		
		jlb=new JLabel("Scale");
		jlb.setBounds(5, r, 100, 20);
		right.add(jlb);
		scaleValue=new DoubleTextField();
		scaleValue.setBounds(column, r, 100, 20);
		scaleValue.addKeyListener(this);
		scaleValue.setToolTipText("If empty=1.0");
		right.add(scaleValue);
		
		add(right);
		
		initRightData();
		
	}
	
	@Override
	public void initRightData() {
		

		x_side.setText(100);
		y_side.setText(200);
		z_side.setText(100);
		roof_top_height.setText(30);
		roof_top_length.setText(100);
		roof_top_width.setText(100);
		roof_rim.setText(20);
	}

	private void setRightData(BuildingPlan plan) {

		
		for (int i = 0; i < chooseRoof.getItemCount(); i++) {
			ValuePair vp= (ValuePair) chooseRoof.getItemAt(i);
			if(vp.getId().equals(""+plan.getRoof_type()))
			{
				chooseRoof.setSelectedIndex(i);
				break;
			}	
		}
		
		
		x_side.setText(plan.getX_side());
		y_side.setText(plan.getY_side());
		z_side.setText(plan.getZ_side());
		
		roof_top_height.setText(""+plan.getRoof_top_height());
		roof_top_length.setText(""+plan.getRoof_top_length());
		roof_top_width.setText(""+plan.getRoof_top_width());
	}

	

	private void cleanRightData() {
		
		x_side.setText("");
		y_side.setText("");
		z_side.setText("");
		roof_top_height.setText("");
		roof_top_length.setText("");
		chooseRoof.setSelectedIndex(0);
	}
	@Override
	public void initialize() {
		
		center.initialize();
		
		oldPlan=new Stack();
	}

	public static void main(String[] args) {
		
		BuildingsEditor be=new BuildingsEditor();
	}
	
	@Override
	public void generate() {
		
		prepareUndo();
		
		double xside=x_side.getvalue();
		double yside=y_side.getvalue();
		double zside=z_side.getvalue();
		double roofHeight=roof_top_height.getvalue();       
		double roofLength= roof_top_length.getvalue();
		double roofWidth=roof_top_width.getvalue();
		double roofRim=roof_rim.getvalue();
		
		
		if(plan==null){
			
			
				
		    BuildingPlan expPlan = new BuildingPlan(xside,yside,zside);
		    
		    ValuePair vp= (ValuePair)chooseRoof.getSelectedItem();
		    
		    int val=Integer.parseInt(vp.getId());
		    if(val<0)
		    	val=BuildingPlan.ROOF_TYPE_HIP;
		    
		    expPlan.setRoof(val,roofHeight,roofWidth,roofLength,roofRim);
		    plan=expPlan;
			
				
			
			
		}else{
			
				

			
			plan = new BuildingPlan(xside,yside,zside);
		    
		    ValuePair vp= (ValuePair)chooseRoof.getSelectedItem();
		    
		    int val=Integer.parseInt(vp.getId());
		    if(val<0)
		    	val=BuildingPlan.ROOF_TYPE_HIP;
			
		    plan.setRoof(val,roofHeight,roofWidth,roofLength,roofRim);
			
		}
		draw();
		setRightData(plan);
		
	}


	@Override
	public void draw() {
		center.draw(plan);
		
	}
	@Override
	public void paint(Graphics arg0) {
		super.paint(arg0);
		draw();
	}
	
	@Override
	public void loadData(File file) {
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));

			
			String str=null;
			
			while((str=br.readLine())!=null){
				
				int indx=str.indexOf("G=");
				if(indx>=0){
					
					String value=str.substring(indx+2);
					plan=BuildingPlan.buildPlan(value);
					
					
				}
				int indx2=str.indexOf("R=");
				if(indx2>=0){
					String value=str.substring(indx2+2);
					plan.buildRoof(value);
					setRightData(plan);
				}
			}
			br.close();
			

			
		} catch (Exception e) { 
		
			e.printStackTrace();
		}
		
	}

	@Override
	public void saveMesh(File file) {
		
		
		if(plan==null)
			return;
		
	
		PrintWriter pw;
		try {

			meshes[0]=buildMesh();
			pw = new PrintWriter(new FileOutputStream(file));
			forceReading=true;
			saveLines(pw);
			pw.close();
			
		} catch (Exception e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		
	}
	
	@Override
	public PolygonMesh buildMesh() {
		
		if(scaleValue!=null){
			
			double val=scaleValue.getvalue();
			
			if(val>0)
				scale=val;
			else
				scale=1.0;
		}
		return plan.buildMesh(scale);
	}
	@Override
	public void saveData(File file) {
		
		
		if(plan==null)
			return;
		
		PrintWriter pw;
		try {
			pw = new PrintWriter(file);			

			
			
			pw.println("G="+plan.toString());
			pw.println("R="+plan.getRoofData());
			pw.close();
						
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		
		Point p=e.getPoint();
		
		
		if(plan!=null){
			
			//cleanRightData();
			//Point pt=new Point((int)center.invertX(p.x,p.y),(int)center.invertY(p.x,p.y));
			
			/*for (int i = 0; i < plan.getXnum(); i++) {
				
				for (int j = 0; j < plan.getYnum(); j++) {
					BuildingCell bc=plan.cells[i][j];
					if(bc.contains(pt)){
						bc.setSelected(true);
						setRightData(bc);
					}	
					else
						bc.setSelected(false);
						
				}
				
			}*/
			
			draw();
		}
		
	}

	@Override
	public void preview() {
		
		if(plan==null)
			return;
		
		super.preview();
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void undo() {

		if(oldPlan.size()>0)
			plan=(BuildingPlan) oldPlan.pop();
		
		if(oldPlan.size()==0)
			jmt_undo_last.setEnabled(false);
		
		draw();
	}
	@Override
	public void prepareUndo() {
		
		if(plan==null)
			return;
		
		jmt_undo_last.setEnabled(true);
		
		if(oldPlan.size()>=max_stack_size)
			oldPlan.removeElementAt(0);
		
		oldPlan.push((BuildingPlan) plan.clone());
		
		
	}
	
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		Object obj = arg0.getSource();
		
		if(obj==chooseRoof){
			
			center.setTeta(0);
			
		}	

	}
}
