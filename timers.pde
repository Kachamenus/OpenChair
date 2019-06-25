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
