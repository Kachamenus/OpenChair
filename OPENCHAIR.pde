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
  
  
void setup(){
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
  
  size(displayWidth, displayHeight);
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

void draw(){
  //INTRO
  if(this.q < 1){
    println(q);
    background(255);
    textAlign(CENTER);
    image(logo, (width/2 - logo.width/2/30), (height/2 - logo.height/2/30), logo.width/30, logo.height/30);
    surface.setIcon(logo);
    drawType(width * 0.5);
    this.q++;
  }
  else if(this.q == 1){
    delay(3000);
    q++;
  }
  //AFTER INTRO
  else{
    if(timespoken.isRunning() == true){
        widthmultiplier = (width/2) * (spokentimertimerincrementor) / spokentimermax * 0.01;
        if(widthmultiplier != 1){
          println((widthmultiplier));
          loadingbarinside.resize((int)widthmultiplier, loadingbar.height);
          image(loadingbarinside, width / 4, 2 * height / 3);
        }
    } 
    if(modcaucustime.isRunning() == true){
        try{ //<>//
          widthmultiplier = (width / 4) + (width/2) * (caucustimertimerincrementor) / caucustimermax; //<>//
        } 
        catch (ArithmeticException ex){ //<>//
          setmaxcaucus.setText("");; //<>//
        }
        
        //println(widthmultiplier);
        loadingbarcaucus.resize((int) (0.01 * widthmultiplier) + 1, loadingbar.height);
        image(loadingbarcaucus, width / 4, 2 * height / 3 + loadingbar.height);
    }
 }
}

void drawType(float x) {
  fill(0);
  textSize(60);
  text("OpenChair", width/2, height/2 + logo.height/2/30 + height / 12); 
  textSize(18);
  text("Alex Shojania, 2019", width/2, height/2 + height / 5);
}

void customGUI(){
  
}
