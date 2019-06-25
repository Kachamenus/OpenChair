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
