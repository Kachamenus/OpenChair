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
    loadingbarcaucus.resize((int) (0.01 * widthmultiplier) + 1, loadingbar.height);
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
