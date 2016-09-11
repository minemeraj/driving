package com.editors.road;

/**
 * 
 * MAIN PROGRAM ENGINE, TRY TO SET A CONSTANT FRAME RATE OF 20
 * @author Piazza Francesco Giovanni ,Betacom s.r.l. http://www.betacom.it
 *
 */


public class RoadEditorThread extends Thread{

	private RoadEditor editor;


	public RoadEditorThread(RoadEditor editor) {
		super();
		this.editor = editor;

	}

	public void run() {

		editor.setWaitBeforeMovingMouse(true);
		editor.draw();
		editor.setWaitBeforeMovingMouse(false);
		
		editor.setMouseMoved(false);
		

	}



}