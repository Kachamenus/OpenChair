import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.*; 
import java.io.*; 
import java.io.File; 
import java.io.Serializable; 
import java.util.List; 
import java.io.FilenameFilter; 
import java.util.regex.Pattern; 
import g4p_controls.*; 
import processing.serial.*; 
import java.awt.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class OpenChair extends PApplet {











JSONObject json;
PFont f;
PImage logo;
PImage positionspeakingimage;
PImage loadingbar;
PImage loadingbar2;
PImage loadingbarinside;
PImage loadingbarcaucus;

class positions {
    private int id;
    private String position;
    private int speaktime;
    private int speeches;
    private String chairnotes;
    private boolean attendance;
    private String imageurl;
    private PImage positionimage;
    public positions(int id, String position, int speaktime, int speeches, String chairnotes, boolean attendance, String imageurl) {
      this.id = id;
      this.position = position;
      this.speaktime = speaktime;
      this.speeches = speeches;
      this.chairnotes = chairnotes;
      this.attendance = attendance;
      this.imageurl = imageurl;
      this.positionimage = loadImage(imageurl);
    }
    public int getId() {
      return id;
    }
    public void setId(int id) {
      this.id = id;
    }
    public String getPosition() {
      return position;
    }
    public void setPosition(String position) {
      this.position = position;
    }
    public int getSpeaktime() {
      return speaktime;
    }
    public void setSpeaktime(int speaktime) {
      this.speaktime = speaktime;
    }    
    public int getSpeeches() {
      return speeches;
    }
    public void setSpeeches(int speeches) {
      this.speeches = speeches;
    }   
    public String getChairnotes() {
      return chairnotes;
    }
    public void setChairnotes(String chairnotes) {
      this.chairnotes = chairnotes;
    }
    public boolean getAttendance() {
      return attendance;
    }
    public void setAttendance(boolean attendance) {
      this.attendance = attendance;
    }
    public String getImageurl() {
      return imageurl;
    }
    public void setImageurl(String imageurl) {
      this.imageurl = imageurl;
    }
    public PImage getImage() {
      return positionimage;
    }
    public void setImage(PImage positionimage) {
      this.positionimage = positionimage;
    }
  }
  
  //ARRAY MADE
  ArrayList<positions> positionarray = new ArrayList<positions>();
  
  
public void setup(){
  File dir = new File(dataPath("") + "/positions"); 
  File[] fileList = dir.listFiles(); 
  println(dataPath(""));
  println(Arrays.toString(fileList));
  //filesList now contains all the files in sorted order

  ///ARRAY THAT HOLDS ALL COUNTRIES
  println(Arrays.toString(fileList));
  println(fileList.length);
  println(fileList[0]);
  String[] newFileList = new String[fileList.length];
  
  for(int i = 0; i < fileList.length; i++) {
    newFileList[i] = (fileList[i].toString().substring(dataPath("").length() + 1));
    //MAKES A NEW ARRAY FROM FILELIST
  }
  
  
  for(int i = 0; i < fileList.length; i++) {
    json = loadJSONObject(newFileList[i]);
    //ADD THE CONTENTS OF EACH JSON TO ARRAY
    positionarray.add(new positions(json.getInt("id"), json.getString("position"), json.getInt("speaktime"), json.getInt("speeches"), json.getString("chairnotes"), json.getBoolean("attendance"), json.getString("imageurl")));
  }

  json = loadJSONObject("stats.json");
  totaltimespoken = json.getInt("totaltimespoken");
  totalspeeches = json.getInt("totalspeeches");
  
  
  //fullScreen();
  //size(1280, 720);
  
  // Create the font
  printArray(PFont.list());
  f = createFont("MyriadPro-Regular", 315);
  textFont(f);
  background(255);
  logo = loadImage("images/logo.png");
 
  for(int i = 0; i < positionarray.size();i++){
    positionnames.add(positionarray.get(i).getPosition());
    println(positionnames.get(i));
  }
 
  for(int i = 0; i < newFileList.length; i++) {
    println(positionarray.get(i).getPosition());
  }
  createGUI(positionarray);
  customGUI();
  
}

ArrayList<String> positionnames = new ArrayList<String>();
int backgroundcolour = 255;
int q = 0;
int numspeak = 0;
int spokentimer = 0;
int caucustimer = 0;
int spokentimermax = 60;
int caucustimermax = 600;
String spokentimerbar = "|";
String caucustimerbar = "|";
int totaltimespoken;
int totalspeeches;
int spokentimertimerincrementor;
int caucustimertimerincrementor;
double widthmultiplier;
String modetype;

public void draw(){
  //INTRO
  if(this.q < 1){
    println(q);
    background(255);
    textAlign(CENTER);
    image(logo, (width/2 - logo.width/2/30), (height/2 - logo.height/2/30), logo.width/30, logo.height/30);
    surface.setIcon(logo);
    drawType(width * 0.5f);
    this.q++;
  }
  else if(this.q == 1){
    delay(3000);
    q++;
  }
  //AFTER INTRO
  else{
    if(timespoken.isRunning() == true){
        widthmultiplier = (width/2) * (spokentimertimerincrementor) / spokentimermax * 0.01f;
        if(widthmultiplier != 1){
          println((widthmultiplier));
          loadingbarinside.resize((int)widthmultiplier, loadingbar.height);
          image(loadingbarinside, width / 4, 2 * height / 3);
        }
    } 
    if(modcaucustime.isRunning() == true){
        try{
          widthmultiplier = (width / 4) + (width/2) * (caucustimertimerincrementor) / caucustimermax;
        } 
        catch (ArithmeticException ex){
          setmaxcaucus.setText("");;
        }
        
        //println(widthmultiplier);
        loadingbarcaucus.resize((int) (0.01f * widthmultiplier) + 1, loadingbar.height);
        image(loadingbarcaucus, width / 4, 2 * height / 3 + loadingbar.height);
    }
 }
}

public void drawType(float x) {
  fill(0);
  textSize(60);
  text("OpenChair", width/2, height/2 + logo.height/2/30 + height / 12); 
  textSize(18);
  text("Alex Shojania, 2019", width/2, height/2 + height / 5);
}

public void customGUI(){
  
}
public void speakerdrop(GDropList source, GEvent event) { //_CODE_:dropList1:923009:
  println("dropList1 - GDropList >> GEvent." + event + " @ " + millis());
  if (modetype.equals("modcaucus")){
    background(backgroundcolour);
    timespoken.stop();
    caucustimertimer.stop();
    modcaucustime.stop();
    spokentimerbarlabel.setText("");
    numspeak = speakerdrop.getSelectedIndex();
    loadingbar = loadImage("images/loadingbar.png");
    spokentimertimerincrementor = 0;
    image(loadingbar, width / 4, 2 * height / 3, width / 2, loadingbar.height);
    positionspeaking.setText(this.positionarray.get(numspeak).getPosition());
    positionspeakingimage = loadImage("images/" + positionarray.get(numspeak).getImageurl());
    image(positionspeakingimage, (width / 2 - positionspeakingimage.width / 2/10) - width/6, ((height / 2) - positionspeakingimage.height/2/10) - height/5, positionspeakingimage.width/8, positionspeakingimage.height/8);
    loadingbar = loadImage("images/loadingbar.png");
    image(loadingbar, width / 4, 2 * height / 3, width / 2, loadingbar.height);
    loadingbar2 = loadImage("images/loadingbar.png");
    image(loadingbar2, width / 4, 2 * height / 3 + loadingbar.height, width / 2, loadingbar.height);
    loadingbarcaucus.resize((int) (0.01f * widthmultiplier) + 1, loadingbar.height);
    image(loadingbarcaucus, width / 4, 2 * height / 3 + loadingbar.height);
  }
  if (modetype.equals("rollcall")){
      background(backgroundcolour);
      numspeak = speakerdrop.getSelectedIndex();
  }
} //_CODE_:dropList1:923009:

public void perpetualclicked(GCheckbox source, GEvent event) { //_CODE_:checkbox1:596268:
  println("checkbox1 - GCheckbox >> GEvent." + event + " @ " + millis());
} //_CODE_:checkbox1:596268:

public void ispresentclicked(GCheckbox source, GEvent event) { //_CODE_:checkbox1:596268:
  println("checkbox1 - GCheckbox >> GEvent." + event + " @ " + millis());
  positionarray.get(numspeak).setAttendance(!positionarray.get(numspeak).getAttendance());
} //_CODE_:checkbox1:596268:
  
/*JSONObject json;

void setup() {

  String position = "Japan";
  json = new JSONObject();
  json.setInt("id", 0);
  json.setString("position", position);
  json.setInt("speaktime", 0);
  json.setInt("speeches", 0);
  json.setString("chairnotes", "");
  json.setBoolean("attendance", false);
  
  String jpeg = (position + ".jpg");
  json.setString("imageurl", jpeg);

  String savefile = ("data/positions/" + position + ".json");
  saveJSONObject(json, savefile);

}*/
public void clearscreen(){
  background(backgroundcolour);
  positionspeaking.setText(""); 
  mode.setText("");
  positionspeaking.setText("");
  modcaucustime.stop();
  timespoken.stop();
  caucustimertimer.stop();
  spokentimertimer.stop();
  if(positionspeaking != null && positionspeaking.isVisible()){  
    positionspeaking.setVisible(false);
  }
  if(speakerdrop != null && speakerdrop.isVisible()){
    speakerdrop.setVisible(false);
  }
  if(start != null && start.isVisible()){
    start.setVisible(false);
  }
  if(next != null && next.isVisible()){
    next.setVisible(false);
  }
  if(reset != null && reset.isVisible()){
    reset.setVisible(false);
  }
  if(pause != null && pause.isVisible()){
    pause.setVisible(false);
  }
  if(speaktime != null && speaktime.isVisible()){
    speaktime.setVisible(false);
  }
  if(perpetual != null && perpetual.isVisible()){
    perpetual.setVisible(false);
  }
  spokentimerbarlabel.setText("");
  caucustimerbarlabel.setText("");
  if(timespoken.isRunning() == false){
    timespoken.stop();    
  }
  if(modcaucustime.isRunning() == false){
    modcaucustime.stop();    
  }
  if(spokentimertimer.isRunning() == false){
    spokentimertimer.stop();    
  }
  if(caucustimertimer.isRunning() == false){
    caucustimertimer.stop();    
  }
  if(perpetual != null && perpetual.isVisible()){
    perpetual.setVisible(false);
  }
}
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
public void rollcallclick(GButton source, GEvent event) { //_CODE_:rollcall:602588:
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
} //_CODE_:rollcall:602588:

public void motionsclick(GButton source, GEvent event) { //_CODE_:motions:500919:
  println("button2 - GButton >> GEvent." + event + " @ " + millis());
  clearscreen();
  background(backgroundcolour);
      textSize(24);
      mode.setText("Motions");
      textSize(12);
} //_CODE_:motions:500919:

public void speakerlistclick(GButton source, GEvent event) { //_CODE_:speakerlist:275163:
  println("button3 - GButton >> GEvent." + event + " @ " + millis());
  clearscreen();
  background(backgroundcolour);
      textSize(24);
      mode.setText("Speaker's List");
      textSize(12);
} //_CODE_:speakerlist:275163:

public void singlespeakerclick(GButton source, GEvent event) { //_CODE_:singlespeaker:288825:
  clearscreen();
  background(backgroundcolour);
  println("singlespeaker - GButton >> GEvent." + event + " @ " + millis());
      textSize(24);
      mode.setText("Single Speaker");
      textSize(12);

} //_CODE_:singlespeaker:288825:

public void notesclick(GButton source, GEvent event) { //_CODE_:notes:768397:
  clearscreen();
  background(backgroundcolour);
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
GImageButton imagespeaking; 
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
/*public void loadingbar(int percent){
  loadingbarinside.resize(width / 200 * percent, loadingbar.height);
  image(loadingbarinside, width / 4, 2 * height / 3);
}*/
public void modcaucusclick(GButton source, GEvent event) { //_CODE_:modcaucus:743149:
  background(backgroundcolour);
  clearscreen();
  modetype = "modcaucus";
  println("button4 - GButton >> GEvent." + event + " @ " + millis());
      mode = new GLabel(this, width / 2, 50, 500, 200);
      mode.setFont(new Font("Arial", Font.PLAIN, 40));
      mode.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
      mode.setText("");
      mode.setText("Moderated Caucus");
  positionspeakingimage = loadImage("images/" + positionarray.get(numspeak).getImageurl());
  image(positionspeakingimage, (width / 2 - positionspeakingimage.width / 2/10) - width/6, ((height / 2) - positionspeakingimage.height/2/10) - height/5, positionspeakingimage.width/8, positionspeakingimage.height/8);
  positionspeaking.setTextAlign(GAlign.CENTER, GAlign.TOP);
  positionspeaking.setText(this.positionarray.get(numspeak).getPosition());
  positionspeaking.setOpaque(false);
  positionspeaking.setVisible(true);
  setmaxspeak = new GTextField(this, 2 * width /3, height / 4, 80, 30);
  setmaxspeak.setOpaque(true);
  setmaxspeak.addEventHandler(this, "setmaxspeakchange");
  setmaxcaucus = new GTextField(this, 2 * width /3, height / 4 + setmaxspeak.getHeight(), 80, 30);
  setmaxcaucus.setOpaque(true);
  setmaxcaucus.addEventHandler(this, "setmaxcaucuschange");
  loadingbar = loadImage("images/loadingbar.png");
  image(loadingbar, width / 4, 2 * height / 3, width / 2, loadingbar.height);
  loadingbar2 = loadImage("images/loadingbar.png");
  image(loadingbar2, width / 4, 2 * height / 3 + loadingbar.height, width / 2, loadingbar.height);
  loadingbarinside = loadImage("images/loadingbarinside.png");
  loadingbarcaucus = loadImage("images/loadingbarinside.png");
  start = new GButton(this, width / 2 - 2 * width / 7, 6 * height / 7, width / 7, 40);
  start.setText("Start");
  start.addEventHandler(this, "startclick");
  next = new GButton(this, width / 2 - width / 7, 6 * height / 7, width / 7, 40);
  next.setText("Next");
  next.addEventHandler(this, "nextclick");
  reset = new GButton(this, width / 2, 6 * height / 7, width / 7, 40);
  reset.setText("Reset");
  reset.addEventHandler(this, "resetclick");
  pause = new GButton(this, width / 2 +  width/7, 6 * height / 7, width / 7, 40);
  pause.setText("Pause");
  pause.addEventHandler(this, "pauseclick");
  G4P.messagesEnabled(false);
  G4P.setGlobalColorScheme(GCScheme.BLUE_SCHEME);
  G4P.setMouseOverEnabled(false);
  surface.setTitle("Sketch Window");
  speakerdrop = new GDropList(this, width - width / 3, height / 3, 250, 300, 10, 12);
  speakerdrop.setItems(positionnames, 0);
  speakerdrop.addEventHandler(this, "speakerdrop");
  perpetual = new GCheckbox(this, width - width / 3, height / 4 - setmaxspeak.getHeight(), 250, 30, "Perpetual? (Crisis)");
  perpetual.setOpaque(false);
  perpetual.addEventHandler(this, "perpetualclicked");
} //_CODE_:modcaucus:743149:
public void saveclick(GButton source, GEvent event) { //_CODE_:notes:768397:
  println("saving...");
  
  for(int i = 0; i < positionarray.size(); i++){
    JSONObject json;

    String position = positionarray.get(i).getPosition();
    json = new JSONObject();
    json.setInt("id", positionarray.get(i).getId());
    json.setString("position", positionarray.get(i).getPosition());
    json.setInt("speaktime", positionarray.get(i).getSpeaktime());
    json.setInt("speeches", positionarray.get(i).getSpeeches());
    json.setString("chairnotes", positionarray.get(i).getChairnotes());
    json.setBoolean("attendance", positionarray.get(i).getAttendance());
  
    String jpeg = (positionarray.get(i).getImageurl());
    json.setString("imageurl", jpeg);

    String savefile = ("data/positions/" + position + ".json");
    saveJSONObject(json, savefile);
  }
  json = new JSONObject();
  json.setInt("totaltimespoken", totaltimespoken);
  json.setInt("totalspeeches", totalspeeches);
  String savefile = ("data/stats.json");
  saveJSONObject(json, savefile);
  println("saved");
} //_CODE_:notes:768397:

public void save(){
  println("saving...");
  
  for(int i = 0; i < positionarray.size(); i++){
    String position = positionarray.get(i).getPosition();
    json = new JSONObject();
    json.setInt("id", positionarray.get(i).getId());
    json.setString("position", positionarray.get(i).getPosition());
    json.setInt("speaktime", positionarray.get(i).getSpeaktime());
    json.setInt("speeches", positionarray.get(i).getSpeeches());
    json.setString("chairnotes", positionarray.get(i).getChairnotes());
    json.setBoolean("attendance", positionarray.get(i).getAttendance());
  
    String jpeg = (positionarray.get(i).getImageurl());
    json.setString("imageurl", jpeg);

    String savefile = ("data/positions/" + position + ".json");
    saveJSONObject(json, savefile);
  }
  json = new JSONObject();
  json.setInt("totaltimespoken", totaltimespoken);
  json.setInt("totalspeeches", totalspeeches);
  String savefile = ("data/stats.json");
  saveJSONObject(json, savefile);
  println("saved");
}
public void startclick(GButton source, GEvent event) { //_CODE_:speakerlist:275163:
  println("button3 - GButton >> GEvent." + event + " @ " + millis());
  if(modetype == "modcaucus"){
    spokentimertimer.start();
    timespoken.start();
    positionarray.get(numspeak).setSpeeches(positionarray.get(numspeak).getSpeeches() + 1);
    totalspeeches++;
  }
  modcaucustime.start();
  caucustimertimer.start();
} //_CODE_:speakerlist:275163:

public void nextclick(GButton source, GEvent event) { //_CODE_:speakerlist:275163:
  println("button3 - GButton >> GEvent." + event + " @ " + millis());
  background(backgroundcolour);
  spokentimertimerincrementor = 0;
  modcaucustime.stop();
  timespoken.stop();
  spokentimertimer.stop();
  caucustimertimer.stop();
  spokentimer = 0;
  spokentimerbarlabel.setText("");
  if(numspeak < positionarray.size() - 1){
    numspeak++;
  }
  else{
    numspeak = 0;
  }
  positionspeaking.setText(this.positionarray.get(numspeak).getPosition());
  positionspeakingimage = loadImage("images/" + positionarray.get(numspeak).getImageurl());
  image(positionspeakingimage, (width / 2 - positionspeakingimage.width / 2/10) - width/6, ((height / 2) - positionspeakingimage.height/2/10) - height/5, positionspeakingimage.width/8, positionspeakingimage.height/8);
  loadingbar = loadImage("images/loadingbar.png");
  image(loadingbar, width / 4, 2 * height / 3, width / 2, loadingbar.height);
  loadingbar2 = loadImage("images/loadingbar.png");
  image(loadingbar2, width / 4, 2 * height / 3 + loadingbar.height, width / 2, loadingbar.height);
  image(loadingbarcaucus, width / 4, 2 * height / 3 + loadingbar.height);
}

public void resetclick(GButton source, GEvent event){
  caucustimer = 0;
  spokentimer = 0;
  modcaucustime.stop();
  timespoken.stop();
  caucustimertimer.stop();
  spokentimertimer.stop();
  caucustimertimerincrementor = 0;
  spokentimertimerincrementor = 0;
  numspeak = 0;
  background(backgroundcolour);
  positionspeakingimage = loadImage("images/" + positionarray.get(numspeak).getImageurl());
  image(positionspeakingimage, (width / 2 - positionspeakingimage.width / 2/10) - width/6, ((height / 2) - positionspeakingimage.height/2/10) - height/5, positionspeakingimage.width/8, positionspeakingimage.height/8);
  positionspeaking.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
  positionspeaking.setText(this.positionarray.get(numspeak).getPosition());
  positionspeaking.setOpaque(false);
  caucustimerbarlabel.setText("");
  spokentimerbarlabel.setText("");
  loadingbar = loadImage("images/loadingbar.png");
  image(loadingbar, width / 4, 2 * height / 3, width / 2, loadingbar.height);
  loadingbar2 = loadImage("images/loadingbar.png");
  image(loadingbar2, width / 4, 2 * height / 3 + loadingbar.height, width / 2, loadingbar.height);
}

int bob = 0;
public void pauseclick(GButton source, GEvent event){
  modcaucustime.stop();
  timespoken.stop();
  spokentimertimer.stop();
}
public void setmaxspeakchange(GTextField textfield, GEvent event) { //_CODE_:textarea1:333925:
  println("textfield1 - GTextArea >> GEvent." + event + " @ " + millis());
  if(isStringInt(setmaxspeak.getText())){
    spokentimermax = Integer.parseInt(setmaxspeak.getText());
  }
} //_CODE_:textarea1:333925:

public void setmaxcaucuschange(GTextField source, GEvent event) { //_CODE_:textfield1:427748:
  println("textfield1 - GTextField >> GEvent." + event + " @ " + millis());
  if(isStringInt(setmaxcaucus.getText())){
    caucustimermax = Integer.parseInt(setmaxcaucus.getText());
  }
  resetclick(reset, event);
  image(loadingbarcaucus, width / 4, 2 * height / 3 + loadingbar.height);
} //_CODE_:textfield1:427748:

public boolean isStringInt(String s){
    try{
        Integer.parseInt(s);
        return true;
    } catch (NumberFormatException ex){
        return false;
    }
}
public void timespokenaction(GTimer source) { //_CODE_:timespoken:449875:
  println("timer1 - GTimer >> an event occured @ " + millis());
  positionarray.get(numspeak).setSpeaktime(positionarray.get(numspeak).getSpeaktime() + 1);
  println(spokentimer);
  spokentimer++;
  spokentimerbar += "*";
  spokentimerbarlabel.setText(spokentimer + "/" + spokentimermax + spokentimerbar);
  if(spokentimer >= spokentimermax){
    timespoken.stop();
    spokentimertimer.stop();
  }
  totaltimespoken++;
} //_CODE_:timespoken:449875:


public void modcaucustimeaction(GTimer source) { //_CODE_:modcaucustime:275214:
  println("modcaucustime - GTimer >> an event occured @ " + millis());
  println(caucustimer);
  caucustimertimer.start();
  caucustimer++;
  if(caucustimer >= caucustimermax){
    modcaucustime.stop();
    caucustimertimer.stop();
  }
  caucustimerbar += "*";
  caucustimerbarlabel.setText(caucustimer + "/" + caucustimermax + caucustimerbar);
} //_CODE_:modcaucustime:275214:


public void minutelyaction(GTimer source) {
  println("Minute save");
  save();
}

public void spokentimertimer(GTimer source) {
  spokentimertimerincrementor++;
}

public void caucustimertimer(GTimer source) {
  caucustimertimerincrementor++;
}
public void unmodcaucusclick(GButton source, GEvent event) { //_CODE_:unmodcaucus:576276:
  clearscreen();
  background(backgroundcolour);
  mode = new GLabel(this, width / 2, 50, 500, 200);
  mode.setFont(new Font("Arial", Font.PLAIN, 40));
  mode.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
  mode.setText("");
  mode.setText("Unoderated Caucus");
  modetype = "unmodcaucus";
  println("unmodcaucus - GButton >> GEvent." + event + " @ " + millis());
      textSize(24);
      mode.setText("Unmoderated Caucus");
      textSize(12);
  loadingbar = loadImage("images/loadingbar.png");
  loadingbarcaucus = loadImage("images/loadingbarinside.png");
  image(loadingbar, width / 4, 2 * height / 3 + loadingbar.height, width / 2, loadingbar.height);
  start = new GButton(this, (width / 2 - width / 14) - width/7, 6 * height / 7, width / 7, 40);
  start.setText("Start");
  start.addEventHandler(this, "startclick");
  start.setVisible(true);
  reset = new GButton(this, width / 2 - width/14, 6 * height / 7, width / 7, 40);
  reset.setText("Reset");
  reset.addEventHandler(this, "resetclick");
  reset.setVisible(true);
  pause = new GButton(this, (width / 2 -width/14) +  width/7, 6 * height / 7, width / 7, 40);
  pause.setText("Pause");
  pause.addEventHandler(this, "pauseclick");
  pause.setVisible(true);
} //_CODE_:unmodcaucus:576276:   
  public void settings() {  size(displayWidth, displayHeight); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "OpenChair" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
