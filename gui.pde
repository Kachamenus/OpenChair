/* =========================================================
 * ====                   WARNING                        ===
 * =========================================================
 * The code in this tab has been generated from the GUI form
 * designer and care should be taken when editing this file.
 * Only add/edit code inside the event handlers i.e. only
 * use lines between the matching comment tags. e.g.

 void myBtnEvents(GButton button) { //_CODE_:button1:12356:
     // It is safe to enter your event code here  
 } //_CODE_:button1:12356:
 
 * Do not rename this tab!
 * =========================================================
 */
public void rollcallclick(GButton source, GEvent event) { //_CODE_:rollcall:602588: //<>//
  println("button1 - GButton >> GEvent." + event + " @ " + millis());
  background(backgroundcolour);
  clearscreen();
  modetype = "rollcall";
  speakerdrop = new GDropList(this, 0, 40, 500, 600, 10, 12);
  speakerdrop.setItems(positionnames, 0);
  speakerdrop.addEventHandler(this, "speakerdrop");
  ispresent = new GCheckbox(this, width - width / 3, height / 4 - setmaxspeak.getHeight(), 250, 30, "Present");
  ispresent.setOpaque(false);
  ispresent.addEventHandler(this, "ispresentclicked");
} //_CODE_:rollcall:602588: //<>//

public void motionsclick(GButton source, GEvent event) { //_CODE_:motions:500919:
  println("button2 - GButton >> GEvent." + event + " @ " + millis());
  modetype = "motions";
  clearscreen();
  background(backgroundcolour);
      textSize(24);
      mode.setText("Motions");
      textSize(12);
} //_CODE_:motions:500919:

public void speakerlistclick(GButton source, GEvent event) { //_CODE_:speakerlist:275163:
  println("button3 - GButton >> GEvent." + event + " @ " + millis());
  modetype = "speakerlist";
  clearscreen();
  background(backgroundcolour);
      textSize(24);
      mode.setText("Speaker's List");
      textSize(12);
} //_CODE_:speakerlist:275163:

public void singlespeakerclick(GButton source, GEvent event) { //_CODE_:singlespeaker:288825:
  clearscreen();
  background(backgroundcolour);
  modetype = "singlespeaker";
  println("singlespeaker - GButton >> GEvent." + event + " @ " + millis());
      textSize(24);
      mode.setText("Single Speaker");
      textSize(12);

} //_CODE_:singlespeaker:288825:

public void notesclick(GButton source, GEvent event) { //_CODE_:notes:768397:
  clearscreen();
  background(backgroundcolour);
  modetype = "notes";
  println("notes - GButton >> GEvent." + event + " @ " + millis());
      textSize(24);
      mode.setText("Notes");
      textSize(12);
} //_CODE_:notes:768397:

public void imagespeakingclick(GImageButton source, GEvent event) { //_CODE_:imagespeaking:549857:
  println("imagespeaking - GImageButton >> GEvent." + event + " @ " + millis());
} //_CODE_:imagespeaking:549857:

public void speaktimechange(GTextField source, GEvent event) { //_CODE_:speaktime:253871:
  println("speaktime - GTextField >> GEvent." + event + " @ " + millis());
} //_CODE_:speaktime:253871:



// Create all the GUI controls. 
// autogenerated do not edit
public void createGUI(ArrayList positionarray){
  G4P.messagesEnabled(false);
  G4P.setGlobalColorScheme(GCScheme.BLUE_SCHEME);
  G4P.setMouseOverEnabled(false);
  surface.setTitle("Sketch Window");
  rollcall = new GButton(this, 0, 0, width / 7, 40);
  rollcall.setText("Roll Call");
  rollcall.addEventHandler(this, "rollcallclick");
  motions = new GButton(this, (2 * width / 7) - width / 7, 0, width / 7, 40);
  motions.setText("Record Motions");
  motions.addEventHandler(this, "motionsclick");
  speakerlist = new GButton(this, (3 * width / 7) - width / 7, 0, width / 7, 40);
  speakerlist.setText("Speaker's List");
  speakerlist.addEventHandler(this, "speakerlistclick");
  modcaucus = new GButton(this, (4 * width / 7) - width / 7, 0, width / 7, 40);
  modcaucus.setText("Moderated Caucus");
  modcaucus.addEventHandler(this, "modcaucusclick");
  unmodcaucus = new GButton(this, (5 * width / 7) - width / 7, 0, width / 7, 40);
  unmodcaucus.setText("Unmoderated Caucus");
  unmodcaucus.addEventHandler(this, "unmodcaucusclick");
  singlespeaker = new GButton(this, (6 * width / 7) - width / 7, 0, width / 7, 40);
  singlespeaker.setText("Single Speaker");
  singlespeaker.addEventHandler(this, "singlespeakerclick");
  notes = new GButton(this, width - width / 7, 0, width / 7, 40);
  notes.setText("Notes");
  notes.addEventHandler(this, "notesclick");
  positionspeaking = new GLabel(this, width / 5, height / 2 , 600, 200);
  positionspeaking.setFont(new Font("Arial", Font.PLAIN, 48));
  positionspeaking.setTextAlign(GAlign.CENTER, GAlign.TOP);
  positionspeaking.setText(" ");
  positionspeaking.setOpaque(false);
  mode = new GLabel(this, width / 2, 50, 500, 200);
  mode.setFont(new Font("Arial", Font.PLAIN, 40));
  mode.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
  mode.setText("");
  mode.setOpaque(false);
  timespoken = new GTimer(this, this, "timespokenaction", 1000);
  timespoken.setInitialDelay(1);
  spokentimertimer = new GTimer(this, this, "spokentimertimer", 10);
  spokentimertimer.setInitialDelay(1);
  caucustimertimer = new GTimer(this, this, "caucustimertimer", 10);
  caucustimertimer.setInitialDelay(1);
  modcaucustime = new GTimer(this, this, "modcaucustimeaction", 1000);
  modcaucustime.setInitialDelay(1);
  minutely = new GTimer(this, this, "minutelyaction", 60000);
  minutely.setInitialDelay(1);
  minutely.start();
  caucustimerbarlabel = new GLabel(this, 0, height/2 + height / 5, 500, 50);
  spokentimerbarlabel = new GLabel(this, 0, height/2 + height / 6, 500, 50);
  savedata = new GButton(this, width - width / 7, height - 80, width / 7, 40);
  savedata.setText("Save");
  savedata.addEventHandler(this, "saveclick");
}

// Variable declarations 
// autogenerated do not edit
GButton rollcall; 
GButton motions; 
GButton speakerlist; 
GButton modcaucus; 
GButton unmodcaucus; 
GButton singlespeaker; 
GButton notes; 
GLabel positionspeaking; 
GTextField speaktime; 
GLabel mode; 
GTimer timespoken; 
GTimer modcaucustime;
GTimer minutely;
GTimer spokentimertimer;
GTimer caucustimertimer;
GButton start;
GButton next;
GButton pause;
GButton reset;
GLabel spokentimerbarlabel;
GLabel caucustimerbarlabel;
GButton savedata;
GDropList speakerdrop; 
GCheckbox perpetual;
GCheckbox ispresent;
GTextField setmaxspeak;
GTextField setmaxcaucus;
