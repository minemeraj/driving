package com.editors.road;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.LineData;
import com.Polygon3D;
import com.PolygonMesh;
import com.editors.Editor;
import com.editors.ValuePair;
import com.main.Renderer3D;


public class RoadEditorPolygonDetail extends JDialog implements ActionListener{

	private int WIDTH=440;
	private int HEIGHT=340;
	
	private LineData modifiedLineData=null;
	private Editor editor=null;
	
	private JButton movePoints;
	private JButton invertPoints;
	private JTable table;
	
	private boolean saved=false;
	private JButton save;
	private JComboBox chooseFace;
	private JButton delete;

	
	public RoadEditorPolygonDetail(Editor roadEditor, LineData ld) {
		

		modifiedLineData=ld.clone();
		this.editor=roadEditor;
	
		
		setTitle("Polygon detail");
		setSize(WIDTH,HEIGHT);
		setLayout(null);
		
		table=new JTable(){
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			 
		};
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		DefaultTableModel model = buildTableModel(modifiedLineData);		
		table.setModel(model);
		
		int r=10;
		
		movePoints=new JButton("Move pts");
		movePoints.setBounds(10,r,100,20);
		movePoints.addActionListener(this);
		add(movePoints);
		
		invertPoints=new JButton("Invert pts");
		invertPoints.setBounds(120,r,100,20);
		invertPoints.addActionListener(this);
		add(invertPoints);
		
		JLabel jlFace=new JLabel("Face:");
		jlFace.setBounds(230,r,40,20);
		add(jlFace);
		
		chooseFace=new JComboBox(); 
		chooseFace.setBounds(270,r,50,20);
		for (int i =0; i< Renderer3D.faceIndexes.length; i++) {
			int val=Renderer3D.faceIndexes[i];
			String desc=Renderer3D.faceDesc[i];
			chooseFace.addItem(new ValuePair(Integer.toString(val),desc));
		}
		if(ld.getData()!=null)
			chooseFace.setSelectedIndex(Integer.parseInt(ld.getData()));
		add(chooseFace);
		
		r+=30;
		
		save=new JButton("Save");
		save.setBounds(10,r,80,20);
		save.addActionListener(this);
		add(save);
		
		
		delete=new JButton("Del pts");
		delete.setBounds(230,r,80,20);
		delete.addActionListener(this);
		add(delete);

		
		r+=30;
		
		JScrollPane jscp=new JScrollPane(table); 
		
		jscp.setBounds(10,r,400,200);
		add(jscp);
		
		
		
		setModal(true);
		
		setVisible(true);
	}
	





	private DefaultTableModel buildTableModel(LineData ld) {
		
		PolygonMesh mesh=editor.getMeshes()[editor.getACTIVE_PANEL()];
		
		Polygon3D polygon3d = editor.buildPolygon(ld,mesh.xpoints,mesh.ypoints,mesh.zpoints,true);

		
		DefaultTableModel model=new DefaultTableModel();
		
		Vector<String> columns=new Vector<String>();
		columns.add("P Index");
		columns.add("X");
		columns.add("Y");
		columns.add("Z");
		

		Vector data=new Vector();
		
		for (int i = 0; i < polygon3d.npoints; i++) {
			
			Vector<String> record=new Vector<String>();
			
			record.add(" "+i);
			record.add(" "+polygon3d.xpoints[i]);
			record.add(" "+polygon3d.ypoints[i]);
			record.add(" "+polygon3d.zpoints[i]);
			
			
			data.add(record);
		}
		
		model.setDataVector(data,columns);	

		return model;
		

	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		
		if(obj==movePoints){
			
			movePoints();
		}
		else if(obj==invertPoints){
			
			invertPoints();
		}
		else if(obj==delete){
			
			deletePoints();
		}
		else if(obj==save){
			
			savePoints();
		}
		
	}








	private void savePoints() {
		
		ValuePair vp=(ValuePair) chooseFace.getSelectedItem();
		
		modifiedLineData.setData(vp.getId());
		
		saved=true;
		dispose();
		
	}

	private void invertPoints() {

		
		
		LineData ld=new LineData();
		
		for (int i = modifiedLineData.size()-1; i >=0; i--) {
			
		
			ld.addIndex(modifiedLineData.getIndex(i));
			
		}
		ld.setTexture_index(modifiedLineData.getTexture_index());
		modifiedLineData=ld;
		
		table.setModel(buildTableModel(modifiedLineData));

		repaint();
		
	}

	private void movePoints() {
		

		LineData ld=new LineData();
		
		for (int i = 0; i < modifiedLineData.size(); i++) {
			
			ld.addIndex(modifiedLineData.getIndex((i+1)%modifiedLineData.size()));
		}
		ld.setTexture_index(modifiedLineData.getTexture_index());
		modifiedLineData=ld;
		
		table.setModel(buildTableModel(modifiedLineData));

		repaint();

		
		
	}


	private void deletePoints() {
		
		
	LineData ld=new LineData();
	
	int indx=table.getSelectedRow();

		for (int i = 0; i < modifiedLineData.size(); i++) {
			
			if(indx==i)
				continue;
			
			ld.addIndex(modifiedLineData.getIndex(i));
					

		}
		ld.setTexture_index(modifiedLineData.getTexture_index());
		modifiedLineData=ld;
		
		table.setModel(buildTableModel(modifiedLineData));

		repaint();
		
	}



	public LineData getModifiedLineData() {
		
		if(!saved)
			return null;
		
		return modifiedLineData;
	}






	public void setModifiedLineData(LineData modifiedLineData) {
		this.modifiedLineData = modifiedLineData;
	}
	
}
