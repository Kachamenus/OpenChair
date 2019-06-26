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
  if(modetype == "modcaucus"){
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
  else if(modetype == "unmodcaucus"){
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
    caucustimerbarlabel.setText("");
    spokentimerbarlabel.setText("");
    loadingbar2 = loadImage("images/loadingbar.png");
    image(loadingbar2, width / 4, 2 * height / 3 + loadingbar.height, width / 2, loadingbar.height);
  }
}

int bob = 0;
public void pauseclick(GButton source, GEvent event){
  modcaucustime.stop();
  timespoken.stop();
  spokentimertimer.stop();
}
